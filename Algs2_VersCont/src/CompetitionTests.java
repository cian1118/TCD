/**
 * QUESTIONS:
 * 1.
 * --DATA-STRUCTURES--
 * DIJKSTRA:
 * Data structure used: TreeMap<Integer, Node>
 * A treemap of nodes, with each node having an Arraylist of routes from this node to other nodes.
 * This data structure is efficient for sparse graphs. Expandable data structure, ie. add more nodes. TreeMaps are very
 * closely related the binary trees and using
 *
 * FLOYD-WARSHALL:
 * double[][]
 * 2D array of doubles, represents an edge [from][to]. The 2D array mimics the adjacency matrix of the graph.
 * Array implementations are optimal for dense graphs. Size of the graph VxV (vertices). Storage space may be wasted on
 * large sparse graphs!
 *
 * 2.
 * --PERFORMANCE--
 * DIJKSTRA:
 * O(V^2 x E) the number of vertices squared by the edges. More edges means slower performance and so the density of the
 * graph effects the performance. Would perform better in a sparse graph (few edges)
 *
 * FLOYD-WARSHALL:
 * O(V^3) the number of vertices cubed - 3 nested for loops. Not multiplied by the edges so the density has no effect on
 * the runtime. Floyd-Warshall finds lowest cost from all sources to all destinations. Would perform better in very
 * dense graphs.
 *
 * Better choice for this assignment: DIJKSTRA
 * The performance of the Dijkstra algorithm is likely to be better in all cases as the graph used is a representation
 * of a city's streets and intersections. Real life cities do not have incredibly dense graphs - ie. the usual max number
 * of edges for each vertex would be 4 (such as a 4 road junction).
 *
*/
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        CompetitionDijkstra competitionDijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 80, 60);
        assertEquals("fail", competitionDijkstra.slowest, 50);
    }

    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall competitionFW = new CompetitionFloydWarshall("input-I.txt", 60,70,84);
        assertEquals("fail", competitionFW.slowest, 60);
    }

    @Test
    public void testDijkstra() {
        CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50,80,60);
        assertEquals(38, dijkstra.timeRequiredforCompetition());

        dijkstra = new CompetitionDijkstra("TINYsdfgdfgEWD.txt", 50, 80, 60);
        assertEquals("filename wrong", -1, dijkstra.timeRequiredforCompetition());

        dijkstra = new CompetitionDijkstra("tinyEWD.txt", -5, 88, 60);
        assertEquals(-1, dijkstra.timeRequiredforCompetition());

        dijkstra = new CompetitionDijkstra(null, 50, 80, 60);
        assertEquals(-1, dijkstra.timeRequiredforCompetition());

        dijkstra = new CompetitionDijkstra("tinyEWD-2.txt", 50, 80, 60);
        assertEquals(-1, dijkstra.timeRequiredforCompetition());

        dijkstra = new CompetitionDijkstra("input-J.txt", 98, 70, 84);
        assertEquals(-1, dijkstra.timeRequiredforCompetition());

        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 1, 80, 60);
        assertEquals("speed out of bounds", -1, dijkstra.timeRequiredforCompetition());
    }

    @Test
    public void testFloydWarshall() {
        CompetitionFloydWarshall fW = new CompetitionFloydWarshall("tinyEWD.txt", 50,80,60);
        assertEquals(38, fW.timeRequiredforCompetition());

        fW = new CompetitionFloydWarshall("TINYsdfgdfgEWD.txt", 50, 80, 60);
        assertEquals(-1, fW.timeRequiredforCompetition());

        fW = new CompetitionFloydWarshall("tinyEWD.txt", -5, 70, 55);
        assertEquals("neg speed", -1, fW.timeRequiredforCompetition());

        fW = new CompetitionFloydWarshall(null, 50, 99, 60);
        assertEquals(-1, fW.timeRequiredforCompetition());

        fW = new CompetitionFloydWarshall("tinyEWD-2.txt", 50, 80, 60);
        assertEquals(-1, fW.timeRequiredforCompetition());

        fW = new CompetitionFloydWarshall("input-J.txt", 98, 70, 84);
        assertEquals(-1, fW.timeRequiredforCompetition());

        fW = new CompetitionFloydWarshall("tinyEWD.txt", 9, 80, 60);
        assertEquals(-1, fW.timeRequiredforCompetition());
    }

}
