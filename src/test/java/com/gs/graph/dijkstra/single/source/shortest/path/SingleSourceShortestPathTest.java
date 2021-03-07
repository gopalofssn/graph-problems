package com.gs.graph.dijkstra.single.source.shortest.path;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class SingleSourceShortestPathTest {

  private SingleSourceShortestPath singleSourceShortestPath = new SingleSourceShortestPath();
  
  @Test
  public void testCycleGraph() {
    String[][] paths = { 
        {"NYC", "Philly", "200"},
        {"NYC", "Edison", "30"},
        {"Edison", "Trenton", "30"},
        {"Trenton", "Philly", "60"},
        {"NYC", "Trenton", "65"},
        {"Trenton", "NYC", "60"}, // not work with cycle???
    };
    Map<String, Integer>  expectedShotestPath = new HashMap<String, Integer>(){{
                                                  put("Philly", 120);
                                                  put("Edison", 30);
                                                  put("Trenton", 60);
                                                  put("NYC", 0);
                                                }};
    assertTrue(expectedShotestPath.equals(singleSourceShortestPath.shortestPath(paths, "NYC")));
  }
  
  @Test
  public void testScnerio1() {
    String[][] paths = {
                          {"Genelia", "Hamshika", "2"},
                          {"Genelia", "Charlie", "5"},
                          {"Hamshika", "Charlie", "2"},
                          {"Charlie", "Sandra", "5"},
                          {"Charlie", "Nancy", "1"},
                          {"Nancy", "Sandra", "1"}
                       };
    Map<String, Integer>  result =  singleSourceShortestPath.shortestPath(paths, "Genelia");
    assertEquals(4, result.get("Charlie"));
  }
}
