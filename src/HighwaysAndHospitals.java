import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

        // Create the graph, where each index is an array representing a city, which consists of the numbers of the cities
        // it is connected to.
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 1; i <= n + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int[] highway : cities) {
            graph.get(highway[0]).add(highway[1]);
            graph.get(highway[1]).add(highway[0]);
        }

        // Create array for undiscovered cities that have yet to be classified as apart of a given "ecosystem"
        int[] undiscovered_cities = new int[n+1];
        undiscovered_cities[0] = -1;
        for (int i = 0; i < n; i++) {
            // MAKE SURE TO NOT TREAT 0 AS A CITY
            undiscovered_cities[i] = i;
        }

        // Variables to keep track of how many cities and ecosystems have been located
        long ecosystem_amount = 0;
        int found_city_count = 0;

        // Keeps track of roughly the "lowest" value of a city that has yet to be discovered
        int lowest_undiscovered_city = 1;

        while (found_city_count < n) {
            // Find the first item in the undiscovered cities array to be undiscovered (equal to -1)
            while (undiscovered_cities[lowest_undiscovered_city] == -1 && lowest_undiscovered_city <= n) {
                lowest_undiscovered_city++;
            }
            int first = undiscovered_cities[lowest_undiscovered_city];

            // Create queue that will contain all connected cities to the "first" city.
            Queue<Integer> queue = new LinkedList<Integer>();

            queue.add(first);
            undiscovered_cities[first] = -1;
            found_city_count++;

            while (!queue.isEmpty()) {
                int queue_item = queue.remove();
                // Look at all connected cities to a given city
                for (int connection : graph.get(queue_item)) { // order of O(1)
                    // If we haven't added this connected city to something yet
                    if (undiscovered_cities[connection] != -1) { // O(n)
                        // Add it to the queue, remove it from undiscovered cities
                        undiscovered_cities[connection] = -1; //O(n)
                        queue.add(connection);
                        found_city_count++;
                    }
                }
            }
            ecosystem_amount++;
        }
        // necessary highways = amount of cities in an ecosystem minus 1
        // total necessary highways = amount of total cities - amount of ecosystems

        System.out.println("hospitals: " + ecosystem_amount);
        System.out.println("highways: " + (n - ecosystem_amount));

        long final_cost = (ecosystem_amount * hospitalCost) + ((n - ecosystem_amount) * highwayCost);

        System.out.println("cost: " + final_cost);

        return final_cost;
    }
}
