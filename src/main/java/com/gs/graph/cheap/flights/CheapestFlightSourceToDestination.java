package com.gs.graph.cheap.flights;
import java.util.*;
/**
 * 
 * @author Gopal Selvaraj
 * Given a weighted graph , find cheapest flight between src to dest.
 *  ip : city1 -> city with cost $$$
 *  dijkstra algorithm
 *  Steps to impl
 *    - build graph
 *    - build mini heap
 *    - cost map
 */
public class CheapestFlightSourceToDestination {

  private class CityAndCost {
    private String city;
    private int cost;
    
    public CityAndCost(String city,  int cost) {  
      this.city = city; 
      this.cost = cost;
    } 
  }

  public int cheapFlight(String[][] flights, final String source, final String destination) {
    
    Map<String, List<CityAndCost>> graph = buildGraph(flights);
    System.out.println(graph);
    Comparator<CityAndCost> cmp = (( a, b) -> a.cost - b.cost);
    PriorityQueue<CityAndCost> lowestCostHeap = new PriorityQueue<CityAndCost>(cmp);
    lowestCostHeap.offer(new CityAndCost(source, 0));
    
    Map<String, Integer> costMap = new HashMap<String, Integer>();
    
    while(!lowestCostHeap.isEmpty()) {
      CityAndCost current = lowestCostHeap.poll();
        
      for(CityAndCost child : graph.getOrDefault(current.city, Collections.emptyList())) {
        int newCost = current.cost + child.cost;
        int existingCost = costMap.getOrDefault(child.city, Integer.MAX_VALUE);
        if(newCost < existingCost){
          costMap.put(child.city, newCost);
          lowestCostHeap.offer(new CityAndCost(child.city, newCost));
        }else {
          lowestCostHeap.offer(new CityAndCost(child.city, existingCost));
        }
        
      }
      
    }
    
    return costMap.getOrDefault(destination, Integer.MAX_VALUE);
    
  }

  private Map<String, List<CityAndCost>> buildGraph(String[][] flights) {
    Map<String, List<CityAndCost>> graph = new HashMap<String, List<CityAndCost>>();
    for(String[] flight : flights) {
      String source = flight[0];
      String destination = flight[1];
      int cost = Integer.parseInt(flight[2].trim());
      graph.computeIfAbsent(source, K -> new ArrayList<CityAndCost>());
      graph.get(source).add(new CityAndCost( destination, cost));
    }
    return graph;
  }

}
