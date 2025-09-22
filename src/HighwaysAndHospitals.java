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

        // For weight balancing, the size of a subgraph is represented as a negative number
        for (int i = 0; i < n+1; i++) {
            map_tree[i] = -1;
        }

        // Variable to store a city that isn't path-compressed when searching for their root
        int path_to_root;

        // For every potential highway
        for (int[] connection : cities) {
            // First number in connection will number one
            int root_number_one = connection[0];
            path_to_root = -1;
            // Find num one's root
            while (map_tree[root_number_one] > 0) {
                path_to_root = root_number_one;
                root_number_one = map_tree[root_number_one];
            }
            // If we found a non-path-compressed city along the way, compress it
            if (path_to_root != -1)
                map_tree[path_to_root] = root_number_one;


            // Second number in connection is number two
            int root_number_two = connection[1];
            path_to_root = -1;
            // Find num two's root
            while(map_tree[root_number_two] > 0) {
                path_to_root = root_number_two;
                root_number_two = map_tree[root_number_two];
            }
            // If we found a non-path-compressed city along the way, compress it
            if(path_to_root != -1)
                map_tree[path_to_root] = root_number_two;


            // If num one and two share a root, skip
            if (root_number_one == root_number_two) {
                continue;
            }

            // If num two's root has a smaller tree than num one's root
            if (map_tree[root_number_two] > map_tree[root_number_one]) {
                // Then num two will become a child of num one's root
                map_tree[root_number_one] += map_tree[root_number_two];
                map_tree[root_number_two] = root_number_one;
            } else {
                // Otherwise, num two will become the root of num one's tree
                map_tree[root_number_two] += map_tree[root_number_one];
                map_tree[root_number_one] = root_number_two;
            }
        }

        // Count ecosystems by counting empty spaces in the map array
        for (int city : map_tree) {
            if (city < 0) {
                ecosystem_amount++;
            }
        }

        // Don't count index zero as a city
        ecosystem_amount--;

        // Calculate and return the final cost
        long final_cost = (ecosystem_amount * hospitalCost) + ((n - ecosystem_amount) * highwayCost);

        return final_cost;
    }
}
