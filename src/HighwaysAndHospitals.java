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
        int cost = 0;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        for (int i = 1; i <= n + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for(int[] highway : cities) {
            graph.get(highway[0]).add(highway[1]);
            graph.get(highway[1]).add(highway[0]);
        }

        ArrayList<Integer> undiscovered_cities = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            undiscovered_cities.add(i+1);
            // MAKE SURE TO NOT TREAT 0 AS A CITY
        }


        // bc we dont gaf if 0 is in the arraylist

        int ecosystem = 0;
        ArrayList<ArrayList<Integer>> ecosystems = new ArrayList<ArrayList<Integer>>();

        while (undiscovered_cities.size() > 1) {
            int first = undiscovered_cities.remove(0);

            Queue<Integer> queue = new LinkedList<Integer>();

            queue.add(first);

            ArrayList<Integer> temp_ecosystem = new ArrayList<Integer>();
            while (!queue.isEmpty()) {
                int queue_item = queue.remove();
                temp_ecosystem.add(queue_item);
                ArrayList<Integer> connected_cities = new ArrayList<Integer>();
                for (int connection : graph.get(queue_item)) {
                    // If we haven't added this city to something yet
                    if (undiscovered_cities.contains(connection)) {
                        // Add it to the queue, remove it from undiscovered cities
                        undiscovered_cities.remove(undiscovered_cities.indexOf(connection));
                        queue.add(connection);
                    }
                }
            }
            ecosystems.add(temp_ecosystem);
        }

        System.out.println();

        return cost;
    }
}
