package com.gs.graph.cheap.flights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestWithUptoKStop {

  private class CityCost {
    private String city;
    private int cost;
    private int numberOfStops;

    public CityCost(String city, int cost) {
      this(city, cost, 0);
    }

    public CityCost(String city, int cost, int numberOfStops) {
      this.city = city;
      this.cost = cost;
      this.numberOfStops = numberOfStops;
    }

    @Override
    public String toString() {
      return "CityCost [city=" + city + ", cost=" + cost + ", numberOfStops=" + numberOfStops + "]";
    }

  }

  public int cheapestflight(String[][] flights, String source, String destination, int k) {
    Map<String, List<CityCost>> graph = buildGraph(flights);
    Map<String, Integer> costMap = buildCostMap(graph, source, k); // aka cost from source
    return costMap.getOrDefault(destination, -1);
  }

  private Map<String, List<CityCost>> buildGraph(String[][] flights) {
    Map<String, List<CityCost>> graph = new HashMap<String, List<CityCost>>();
    for (String[] flight : flights) {
      String source = flight[0];
      String destination = flight[1];
      int cost = Integer.parseInt(flight[2]);
      graph.putIfAbsent(source, new ArrayList<CityCost>());
      graph.get(source).add(new CityCost(destination, cost));
    }
    return graph;
  }


  private Map<String, Integer> buildCostMap(Map<String, List<CityCost>> graph, final String source,
      final int k) {
    Map<String, Integer> costMap = new HashMap<String, Integer>(); // aka cost from source

    Comparator<CityCost> cmp = (a, b) -> a.cost - b.cost;
    PriorityQueue<CityCost> miniHeap = new PriorityQueue<CityCost>(cmp);
    miniHeap.offer(new CityCost(source, 0, -1));
    costMap.put(source, 0);
    
    while (!miniHeap.isEmpty()) {

      CityCost current = miniHeap.poll();
      int stop = current.numberOfStops;
      if (stop >= k)
        continue;
      String currentCity = current.city;
      int currentCost = current.cost;

      for (CityCost child : graph.getOrDefault(currentCity, Collections.emptyList())) {
        int cost = currentCost + child.cost;
        if (cost < costMap.getOrDefault(child.city, Integer.MAX_VALUE)) {
          costMap.put(child.city, cost); 
        }
        miniHeap.offer(new CityCost(child.city, cost, stop + 1));
      }
    }

    return costMap;
  }
}
