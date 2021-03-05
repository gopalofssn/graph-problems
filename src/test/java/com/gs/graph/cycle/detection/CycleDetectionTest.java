package com.gs.graph.cycle.detection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CycleDetectionTest {
  
  private CycleDetection cycleDetection = new CycleDetection();
  
 // @Test
  public void testAcyclicGraph() {
    char[][] grid = {
        {'A','B'}, 
        {'B','C'},
        {'C','D'},
    };
    assertFalse(cycleDetection.isCycle(grid));
  }
  
  @Test
  public void testCyclicGraph() {
    char[][] grid = {
        {'A','K'}, 
        {'A','B'}, 
        {'B','C'},
        {'C','D'},
        {'D','A'}
    };
    assertTrue(cycleDetection.isCycle(grid));
  }
  
}
