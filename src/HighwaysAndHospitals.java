/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Jack Edwards
 *
 */

public class HighwaysAndHospitals {


    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {

        // Test case for if it's cheaper to just build a hospital everywhere
        if (hospitalCost < highwayCost) {
            return ((long)hospitalCost * n);
        }

        long ecosystem_amount = 0;

        // Create map to house the root of a given city
        int[] map_tree = new int[n+1];

        // Array to store the cities we look at when searching for their root
//        int[] path_to_root = new int[n];
        int path_to_root = 0;

        for (int[] connection : cities) {
            // find parent's root
            int root_of_parent = connection[0];
            path_to_root = -1;

            while (map_tree[root_of_parent] != 0) {
                path_to_root = root_of_parent;
                root_of_parent = map_tree[root_of_parent];
            }
            if (path_to_root != -1)
                map_tree[path_to_root] = root_of_parent;

            // find child's root
            int new_child = connection[1];
            path_to_root = -1;

            while(map_tree[new_child] != 0) {
//                path_to_root[i] = new_child;
//                i++;
                path_to_root = new_child;
                new_child = map_tree[new_child];
            }

            if(path_to_root != -1)
                map_tree[path_to_root] = new_child;

            // check if same root
            if (root_of_parent == new_child) {
                continue;
            }

            map_tree[new_child] = connection[0];
        }

        for (int city : map_tree) {
            if (city == 0) {
                ecosystem_amount++;
            }
        }

        ecosystem_amount--;

        long final_cost = (ecosystem_amount * hospitalCost) + ((n - ecosystem_amount) * highwayCost);

        return final_cost;
    }
}
