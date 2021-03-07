package com.gs.graph.dijkstra.single.source.shortest.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SingleSourceShortestPathTracePath {

  private class LocationDistance {
    private String location;
    private int distance;
    private List<String> trace;
    
    public LocationDistance(String location, int distance) {
      this(location, distance, null);
    }
    
    public LocationDistance(String location, int distance, List<String> trace) {
      this.location = location;
      this.distance = distance;
      this.trace = trace;
    }
  }

  public Map<String, List<String>> shortestPathTrace(String[][] paths, String source) {
    Map<String, List<LocationDistance>> graph = buildGraph(paths);
    Map<String, Integer> shotestPathMap = new HashMap<String, Integer>();
    Map<String, List<String>> shotestPathTraceMap = new HashMap<String, List<String>>();
    
    Comparator<LocationDistance> cmp = (a, b) -> a.distance - b.distance;
    PriorityQueue<LocationDistance> miniHeap = new PriorityQueue<LocationDistance>(cmp);
    miniHeap.offer(new LocationDistance(source, 0, new ArrayList<String>(Arrays.asList(source))));
    
    while(!miniHeap.isEmpty()) {
      LocationDistance current = miniHeap.poll();
      for(LocationDistance child  : graph.getOrDefault(current.location, Collections.emptyList())) {
        int distance = current.distance + child.distance;
        String location = child.location;
        if(distance < shotestPathMap.getOrDefault(location, Integer.MAX_VALUE)) {
          shotestPathMap.put(location, distance);
          List<String> trace = new ArrayList<String>(current.trace);
          trace.add(location);
          shotestPathTraceMap.put(location, trace);
          miniHeap.offer(new LocationDistance(location, distance, trace));
        }
        
      }
    }
    return shotestPathTraceMap;
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
