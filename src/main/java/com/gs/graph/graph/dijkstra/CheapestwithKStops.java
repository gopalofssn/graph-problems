package com.gs.graph.graph.dijkstra;

import java.util.*;

public class CheapestwithKStops {

	private class Edge{
        private final int city;
        private final int cost;
        private final int stop;
        public Edge(int city, int cost){
            this(city, cost, 0);
        }
        public Edge(int city, int cost, int stop){
            this.city = city;
            this.cost = cost;
            this.stop = stop;
        }
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if(flights == null || flights.length == 0) return 0;
        Map<Integer, List<Edge>> graph = buildGraph(flights);
        Map<Integer, Integer> costMap = new HashMap<Integer, Integer>();
        Comparator<Edge> cmp = (a, b) -> (a.cost - b.cost);
        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(cmp);
        Set<Integer> visited = new HashSet<Integer>();
        minHeap.offer(new Edge(src, 0, -1));
        while(!minHeap.isEmpty()){
            Edge current = minHeap.poll();
            System.out.println("current.city " + current.city + " and total stop" + current.stop);
            visited.add(current.city);
            if(current.stop > K) continue;
            int stop = current.stop + 1;
            for( Edge edge : graph.getOrDefault(current.city, Collections.emptyList()) ) {
                int city = edge.city;
                int currentToCity = current.cost  + edge.cost;
                int minCost = Math.min(costMap.getOrDefault(city, Integer.MAX_VALUE), currentToCity);
                costMap.put(city, minCost);
                if(!visited.contains(city)){
                    minHeap.offer(new Edge(city, minCost, stop));
                  }
            }  
        }
        System.out.println(costMap);
        return costMap.getOrDefault(dst, -1);
    }
    
    private Map<Integer, List<Edge>> buildGraph(int[][] flights){
        Map<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();
        for(int[] flight : flights){
            int src = flight[0];
            Edge edge = new Edge(flight[1], flight[2]);
            graph.putIfAbsent(src, new ArrayList<Edge>());
            graph.get(src).add(edge);
        }  
        return graph;
    }
	public static void main(String[] args) {
		int[][] data = {{0,1,100},{0,2,500},{1,2,100}};
		//System.out.println(new CheapestwithKStops().findCheapestPrice(3, data, 0, 2, 1));
		
		int[][] data1 = {
						{0,1,1},
						{0,2,5},
						{1,2,1},
						{2,3,1}
						};

		//System.out.println(new CheapestwithKStops().findCheapestPrice(4, data1, 0, 3, 1));
		int[][] data2 = {
				          {0,1,5},
				          {1,2,5},
				          {0,3,2},
				          {3,1,2},
				          {1,4,1},
				          {4,2,1}
				        };
		System.out.println(new CheapestwithKStops().findCheapestPrice(5, data2, 0, 2, 2)); // 7

	}

}
