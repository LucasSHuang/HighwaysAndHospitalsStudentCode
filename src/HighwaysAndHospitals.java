
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
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        // Variables
        long cost = 0;
        int highwayCount = 0;
        int hospitalCount = 0;
        int[] roots = new int[n + 1];

        // Base case where highways are more expensive so you put hospitals in every city
        if (highwayCost >= hospitalCost) {
            return (long) n * hospitalCost;
        }

        // Set every root to -1 for weight balancing
        for (int i = 1; i < roots.length; i++) {
            roots[i] = -1;
        }

        // For every connection in the cities array get both cities and do the union
        for (int i = 0; i < cities.length; i++) {
            int firstCity = cities[i][0];
            int connectedCity = cities[i][1];
            union(roots, firstCity, connectedCity);
        }

        // Get the hospital and highway count and then calculate the cost
        hospitalCount = findHospitalsCount(roots);
        highwayCount = n - hospitalCount;
        cost = (long) hospitalCount * hospitalCost + (long) highwayCount * highwayCost;
        return cost;
    }

    // Find the root/parent of every city
    public static int findRoot(int[] roots, int city) {

        // Use a while loop to iterate through the array until we get the actual root city
        int root = city;
        while (roots[root] > -1) {
            root = roots[root];
        }

        // Use path compression to just make the city point to the root so that it is much more efficient
        while (city != root) {
            int temp = roots[city];
            roots[city] = root;
            city = temp;
        }
        return root;
    }

    // Union-find sort method
    public static void union(int[] roots, int firstCity, int connectedCity) {

        // Get the roots of both cities
        int firstCityRoot = findRoot(roots, firstCity);
        int connectedCityRoot = findRoot(roots, connectedCity);

        // If both roots are equal you don't do anything
        if (firstCityRoot == connectedCityRoot) {
            return;
        }

        // Get the "weights" of both roots
        int firstOrder = roots[firstCityRoot];
        int connectedOrder = roots[connectedCityRoot];

        // Use weight balancing to combine smaller subtree with larger one to make it more efficient
        if (firstOrder <= connectedOrder) {
            roots[firstCityRoot] += connectedOrder;
            roots[connectedCityRoot] = firstCityRoot;
        }
        else {
            roots[connectedCityRoot] += firstOrder;
            roots[firstCityRoot] = connectedCityRoot;
        }
    }

    // Find the total hospitals
    public static int findHospitalsCount(int[] roots) {

        // Iterate through the roots array and when you find a city that has a negative root it is a hospital
        int count = 0;
        for (int i = 1; i < roots.length; i++) {
            if (roots[i] < 0) {
                count++;
            }
        }
        return count;
    }
}
