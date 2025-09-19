/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 *
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {

        // Test case for if it's cheaper to just build a hospital everywhere
        if (hospitalCost < highwayCost) {
            return ((long)hospitalCost * n);
        }

        long ecosystem_amount = 0;

        int[] map_tree = new int[n+1];

        int[] path_to_root = new int[n];

        for (int[] connection : cities) {
            // find parent's root
            int root_of_parent = connection[0];
            int i = 0;
            if(map_tree[root_of_parent] == 0)
                i = -1;

            while (map_tree[root_of_parent] != 0) {
                path_to_root[i] = root_of_parent;
                i++;
                root_of_parent = map_tree[root_of_parent];
            }
            while (--i >= 0) {
                map_tree[path_to_root[i]] = root_of_parent;
                i--;
            }

            // find child's root
            int new_child = connection[1];
            i = 0;
            if(map_tree[new_child] == 0)
                i = -1;

            while(map_tree[new_child] != 0) {
                path_to_root[i] = new_child;
                i++;
                new_child = map_tree[new_child];
            }
            while (--i >= 0) {
                map_tree[path_to_root[i]] = new_child;
                i--;
            }

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


        // necessary highways = amount of cities in an ecosystem minus 1
        // total necessary highways = amount of total cities - amount of ecosystems

//        System.out.println("hospitals: " + ecosystem_amount);
//        System.out.println("highways: " + (n - ecosystem_amount));

        long final_cost = (ecosystem_amount * hospitalCost) + ((n - ecosystem_amount) * highwayCost);

//        System.out.println("cost: " + final_cost);

        return final_cost;
    }
}
