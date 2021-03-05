package com.gs.graph.cycle.detection;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CycleDetection {

  public boolean isCycle(char[][] grid) {
    Map<Character, Set<Character>> graph = buildGraph(grid);
    Set<Character> visited = new HashSet<Character>();
    for (Map.Entry<Character, Set<Character>> entry : graph.entrySet()) {
      char vertex = entry.getKey();
      if (visited.contains(vertex)) {
        continue;
      }

      Set<Character> visiting = new HashSet<Character>();
      if (isCycleUtil(graph, vertex, visiting)) {
        return true;
      }
      visited.addAll(visiting);
    }
    return false;
  }

  private boolean isCycleUtil(Map<Character, Set<Character>> graph, char vertex,
      Set<Character> visiting) {
    if (visiting.contains(vertex)) {
      return true;
    }
    visiting.add(vertex);
    boolean isCycle = false;
    for (char child : graph.getOrDefault(vertex, Collections.emptySet())) {
      isCycle = isCycle || isCycleUtil(graph, child, visiting);
    }
    return isCycle;
  }

  private Map<Character, Set<Character>> buildGraph(char[][] grid) {
    Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
    for (char[] edge : grid) {
      char source = edge[0];
      char destination = edge[1];
      graph.computeIfAbsent(source, k -> new HashSet<Character>());
      graph.get(source).add(destination);
    }
    return graph;
  }

}
