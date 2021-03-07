package com.gs.graph.dijkstra.single.source.shortest.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SingleSourceShortestPath {

  private class LocationDistance {
    private String location;
    private int distance;

    public LocationDistance(String location, int distance) {
      this.location = location;
      this.distance = distance;
    }
    
  }

 
  public Map<String, Integer> shortestPath(String[][] paths, String source) {
    Map<String, List<LocationDistance>> graph = buildGraph(paths);
    Map<String, Integer> shotestPathMap = new HashMap<String, Integer>();
    
    Comparator<LocationDistance> cmp = (a, b) -> a.distance - b.distance;
    PriorityQueue<LocationDistance> miniHeap = new PriorityQueue<LocationDistance>(cmp);
    shotestPathMap.put(source, 0);
    miniHeap.offer(new LocationDistance(source, 0));
    
    while(!miniHeap.isEmpty()) {
      LocationDistance current = miniHeap.poll();
      for(LocationDistance child : graph.getOrDefault(current.location, Collections.emptyList())) {
        int distance = current.distance + child.distance;
        String location = child.location;
        if(distance < shotestPathMap.getOrDefault(location, Integer.MAX_VALUE)) {
          shotestPathMap.put(location, distance);
          miniHeap.offer(new LocationDistance(location, distance));
        }
      }
    }
    
    return shotestPathMap;
  }

  private Map<String, List<LocationDistance>> buildGraph(String[][] paths) {
    Map<String, List<LocationDistance>> graph = new HashMap<String, List<LocationDistance>>();
    for (String[] path : paths) {
      String source = path[0];
      String destination = path[1];
      int distance = Integer.parseInt(path[2].trim());
      graph.putIfAbsent(source, new ArrayList<LocationDistance>());
      graph.get(source).add(new LocationDistance(destination, distance));
    }
    return graph;
  }
}
