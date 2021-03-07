package com.gs.graph.dijkstra.single.source.shortest.path;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
public class SingleSourceShortestPathTracePathTest {

  private SingleSourceShortestPathTracePath SingleSourceShortestPathTracePath = new SingleSourceShortestPathTracePath();
  
  @Test
  public void testCase1() {
    String[][] paths = {
        {"Genelia", "Hamshika", "2"},
        {"Genelia", "Charlie", "5"},
        {"Hamshika", "Charlie", "2"},
        {"Charlie", "Sandra", "5"},
        {"Charlie", "Nancy", "1"},
        {"Nancy", "Sandra", "1"}
     };
    Map<String, List<String>>  result =  SingleSourceShortestPathTracePath.shortestPathTrace(paths, "Genelia");
    assertLinesMatch(new ArrayList<String>(Arrays.asList("Genelia", "Hamshika", "Charlie")), result.get("Charlie"));
    assertLinesMatch(new ArrayList<String>(Arrays.asList("Genelia", "Hamshika", "Charlie","Nancy","Sandra")), result.get("Sandra"));
  }
}
