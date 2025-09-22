
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

        // Base Case
        if (highwayCost >= hospitalCost) {
            return (long) n * hospitalCost;
        }

        for (int i = 1; i < roots.length; i++) {
            roots[i] = -1;
        }

        for (int i = 0; i < cities.length; i++) {
            int firstCity = cities[i][0];
            int connectedCity = cities[i][1];
            union(roots, firstCity, connectedCity);
        }
        hospitalCount = findRootsCount(roots);
        highwayCount = n - hospitalCount;
        cost = (long) hospitalCount * hospitalCost + (long) highwayCount * highwayCost;
        return cost;
    }

    public static int findRoot(int[] roots, int city) {
        int root = city;
        while (roots[root] > -1) {
            root = roots[root];
        }

        while (city != root) {
            int temp = roots[city];
            roots[city] = root;
            city = temp;
        }
        return root;
    }

    public static void union(int[] roots, int firstCity, int connectedCity) {
        int firstCityRoot = findRoot(roots, firstCity);
        int connectedCityRoot = findRoot(roots, connectedCity);
        if (firstCityRoot == connectedCityRoot) {
            return;
        }
        int firstOrder = roots[firstCityRoot];
        int connectedOrder = roots[connectedCityRoot];
        if (firstOrder <= connectedOrder) {
            roots[firstCityRoot] += connectedOrder;
            roots[connectedCityRoot] = firstCityRoot;
        }
        else {
            roots[connectedCityRoot] += firstOrder;
            roots[firstCityRoot] = connectedCityRoot;
        }
    }
    public static int findRootsCount(int[] roots) {
        int count = 0;
        for (int i = 1; i < roots.length; i++) {
            if (roots[i] < 0) {
                count++;
            }
        }
        return count;
    }
}
