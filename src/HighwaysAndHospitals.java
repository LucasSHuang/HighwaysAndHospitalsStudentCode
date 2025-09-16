import java.util.ArrayList;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Lucas Huang
 *
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        int cost = 0;
        ArrayList<Integer> connected = new ArrayList<Integer>();

        // Create arraylist to keep track of hospitals
        ArrayList<Integer> hospitals = new ArrayList<Integer>();
        hospitals.add(1);
        for (int i = 2; i < n + 1; i++) {
            int cityConnections = findConnections(i, cities);
            if (connected.contains(i)) {
                continue;
            }
            else {

            }
            connected.add(cityConnections);
        }

        return cost;
    }

    public static int findConnections(int city,int cities[][]) {
        int connections = 0;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i][0] == city || cities[i][1] == city) {
                connections++;
            }
        }
        return connections;
    }

    public static int findMostConnections(ArrayList<Integer> connections) {
        int city = 0;
        for (int i = 0; i < connections.size(); i++) {

        }
        return city;
    }
}
