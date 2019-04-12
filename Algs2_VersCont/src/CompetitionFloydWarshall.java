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
 * This class implements the competition using Floyd-Warshall algorithm
 */

import java.io.BufferedReader;
import java.io.FileReader;

public class CompetitionFloydWarshall {
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */

    private static final double INF = Integer.MAX_VALUE / 2;
    private double array2D[][];    // [from][to]
    private boolean usableFile = true;
    private int sA, sB, sC;
    private int intersections;
    private int streets;
    int slowest;

    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        this.makeArray(filename);
    }

    private void makeArray(String filename) {
        slowest = Math.min(Math.min(sA, sB), sC);
        try {
            if (filename == null) {
                usableFile = false;
                slowest = -1;
            } else {
                FileReader fReader = new FileReader(filename);
                BufferedReader bReader = new BufferedReader(fReader);
                intersections = Integer.parseInt(bReader.readLine());
                streets = Integer.parseInt(bReader.readLine());

                if (intersections == 0 || streets == 0) {
                    usableFile = false;
                }

                array2D = new double[intersections][streets];
                for (int i = 0; i < intersections; i++){
                    for (int j = 0; j < intersections; j++){
                        array2D[i][j] = INF;
                    }
                }

                String line = bReader.readLine();
                while((line != null)){
                    String[] splitLine = line.trim().split(" ");
                    array2D[Integer.parseInt(splitLine[0])][Integer.parseInt(splitLine[1])] = Double.parseDouble(splitLine[2]);
                    line = bReader.readLine();
                }
                bReader.close();
            }
        } catch (Exception e) {
            usableFile = false;
            slowest = -1;
            e.printStackTrace();
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        if((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50)) {
            return -1;
        }

        if(!usableFile){
            return -1;
        }

        for (int k = 0; k < intersections; k++){
            for (int i = 0; i < intersections; i++){
                for (int j = 0; j < intersections; j++){
                    if(array2D[i][k] + array2D[k][j] < array2D[i][j]){
                        array2D[i][j] = array2D[i][k] + array2D[k][j];
                    }
                }
            }
        }
        double dist = getMax();
        if(dist == INF){
            return -1;
        }
        dist = dist * 1000;   //KM to metres

        return (int) Math.ceil(dist / slowest); //dist / speed = time
    }

    private double getMax(){
        double dist = -1;
        for (int i = 0; i < intersections; i++){
            for (int j = 0; j < intersections; j++){
                if(array2D[i][j] > dist && i != j){
                    dist = array2D[i][j];
                }
            }
        }
        return dist;
    }
}