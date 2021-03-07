package com.gs.graph.topological.sort;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

  private TopologicalSort topologicalSort = new TopologicalSort();
  
  @Test
  public void testScnerio1() {
    char[][] grid = {
        {'A','D'}, 
        {'E','A'},
        {'E','D'},
        {'E', 'F'},
        {'C','A'},
        {'C', 'B'},
        {'B', 'D'},
        {'D','H'},
        {'D', 'G'},
        {'F', 'K'},
        {'F', 'J'},
        {'K', 'J'},
        {'H', 'J'},
        {'H','I'},
        {'J', 'M'},
        {'J', 'L'}
    };
    
    char[] expected = {'E', 'F', 'K', 'C', 'B', 'A', 'D', 'G', 'H', 'I', 'J', 'L', 'M'};
    assertArrayEquals(expected, topologicalSort.topSort(grid));
  }
  
  @Test
  public void testScnerio2() {
    char[][] grid = {
                        {'A','B'},
                        {'B', 'C'}
                    };
    assertArrayEquals(new char[]{'A', 'B', 'C'}, topologicalSort.topSort(grid));
  }
  
}
