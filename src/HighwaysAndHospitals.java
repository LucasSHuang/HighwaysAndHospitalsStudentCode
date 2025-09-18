
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
        long cost = 0;
        int highwayCount = 0;
        int hospitalCount = 0;
        int[] roots = new int[n + 1];

        // Create arraylist to keep track of hospitals
        for (int i = 0; i < cities.length; i++) {
            int firstCity = cities[i][0];
            int connectedCity = cities[i][1];
            int firstCityRoot = findRoot(roots, firstCity);
            int connectedCityRoot = findRoot(roots, connectedCity);
            if (firstCityRoot != connectedCityRoot) {
                roots[connectedCityRoot] = firstCityRoot;
            }
        }
        hospitalCount = findRootsCount(roots);
        highwayCount = n - hospitalCount;
        cost = (long) hospitalCount * hospitalCost + (long) highwayCount * highwayCost;
        return cost;
    }

    public static int findRoot(int[] roots, int city) {
        if (roots[city] == 0) {
            return city;
        }
        roots[city] = findRoot(roots, roots[city]);
        return roots[city];
    }
    public static int findRootsCount(int[] roots) {
        int count = 0;
        for (int i = 1; i < roots.length; i++) {
            if (roots[i] == 0) {
                count++;
            }
        }
        return count;
    }
}
