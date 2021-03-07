package com.gs.graph.topological.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Gopal Selvaraj 
 * Time : O(V+E) 
 * Space : O(V + E ); 
 * Approach : Depth first search
 */
public class TopologicalSort {

  public char[] topSort(char[][] grid) {
    Map<Character, List<Character>> graph = buildGraph(grid);
    LinkedList<Character> topList = new LinkedList<Character>();
    Set<Character> visited = new HashSet<Character>();
    for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
      char vertex = entry.getKey();
      if (visited.contains(vertex)) continue;
      dfs(graph, vertex, visited, topList);
    }
    return convertAndSend(topList);
  }

  private char[] convertAndSend(LinkedList<Character> topList) {
    char[] result = new char[topList.size()];
    int index = 0;
    for (char c : topList) {
      result[index] = c;
      index++;
    }
    return result;
  }

  private void dfs(Map<Character, List<Character>> graph, char vertex, Set<Character> visited, LinkedList<Character> topList) {
    if (visited.contains(vertex)) return;
    
    visited.add(vertex);
    for (char child : graph.getOrDefault(vertex, Collections.emptyList())) {
      dfs(graph, child, visited, topList);
    }
    topList.addFirst(vertex);
  }

  private Map<Character, List<Character>> buildGraph(char[][] grid) {
    Map<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
    for (char[] edge : grid) {
      char src = edge[0];
      char dest = edge[1];
      graph.putIfAbsent(src, new ArrayList<>());
      graph.get(src).add(dest);
    }
    return graph;
  }
}
