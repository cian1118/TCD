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
