/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants'
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class CompetitionDijkstra {
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    private int sA, sB, sC; //static?
    private int slowest;
    //metres per minute: >= 50 and <= 100

    private TreeMap<Integer, Node> treeMap;

    CompetitionDijkstra (String filename, int sA, int sB, int sC){
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        this.readInFile(filename);
    }

    private void readInFile(String filename) {

        slowest = Math.min(sA, sB);
        slowest = Math.min(slowest, sC);
        if (filename == null) slowest = -1;
        treeMap = new TreeMap<>();

        try {
            FileReader fReader = new FileReader(filename);
            Scanner scanner = new Scanner(fReader);

            int intersections = scanner.nextInt();
            int streets = scanner.nextInt();

            for (int i = 0; i < streets; i++) {
                if (scanner.hasNext()) {
                    int intersection1 = scanner.nextInt();
                    int intersection2 = scanner.nextInt();

                    double dist = scanner.nextDouble() * 1000; //*1000 to change from KM to metres
                    Node n1, n2;

                    if (treeMap.get(intersection1) == null) {
                        n1 = new Node(intersection1);
                        treeMap.put(intersection1, n1);
                    } else {
                        n1 = treeMap.get(intersection1);
                    }

                    if (treeMap.get(intersection2) == null) {
                        n2 = new Node(intersection2);
                        treeMap.put(intersection2, n2);
                    } else n2 = treeMap.get(intersection2);

                    n1.addAdj(n2, dist);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double getLowestCost(int start) {

        LinkedList<Node> nodes = new LinkedList<>();
        for (Node n : treeMap.values()) {
            if (n.id == start) {
                n.cost = 0;
            } else {
                n.cost = Double.MAX_VALUE;
            }
            nodes.add(n);
        }

        for (int i = 0; i < treeMap.values().size(); i++) {
            for (Node n : nodes) {
                for (Route route : n.paths) {
                    double newCost = n.cost + route.cost;
                    if (newCost < route.dest.cost) {
                        route.dest.cost = newCost;
                    }
                }
            }
        }

        double maximum = Double.MIN_VALUE;
        for (Node n : treeMap.values()) {
            if (n.cost == Double.MAX_VALUE) { //unknown cost
                return n.cost;
            } else if (n.cost > maximum) {
                maximum = n.cost;
            }
        }
        return maximum;
    }

    private class Node {
        int id;
        double cost = Double.MAX_VALUE; //unknown cost
        ArrayList<Route> paths = new ArrayList<>();

        Node(int id) {
            this.id = id;
        }

        void addAdj(Node node, double cost) {
            paths.add(new Route(node, cost));
        }
    }

    private class Route {
        Node dest;
        double cost;

        Route(Node dest, double cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        if (treeMap.size() == 0 || slowest <= 0) {
            return -1;
        }

        double longestDist = -1;
        for (Node n : treeMap.values()) {
            double dist = getLowestCost(n.id);
            if (dist == Double.MAX_VALUE) {
                return -1;
            }
            longestDist = Math.max(longestDist, dist);
        }
        return (int) Math.ceil(longestDist / slowest); // dist / speed = time
    }

}