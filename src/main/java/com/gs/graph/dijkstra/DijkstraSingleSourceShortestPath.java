package com.gs.graph.dijkstra;

import java.util.*; 

public class DijkstraSingleSourceShortestPath {
	
	/**
	 An object that hold vertex & weight from Source in Original Graph.
	 @Author Gopal Selvaraj
	 */
	private static class Edge implements Comparable<Edge>{
		int vertex;
		int weight;
		public Edge(int destination, int weight) {
			this.vertex = destination;
			this.weight = weight;
		}
		public int compareTo(Edge that) {
			return this.weight - that.weight;
		}
		
		@Override
		public String toString() {
			return "Edge [destination=" + vertex + ", weight=" + weight + "]";
		}
		
	}
	
	private static class ViaPoint{
		List<Integer> list = new ArrayList<Integer>();
		int distance = 0 ;
	}
	
	private static Map<Integer, Set<Edge>> buildGraph(int[][] nums) {
		Map<Integer, Set<Edge>>  graph = new  HashMap<Integer,  Set<Edge>>();
		
		for(int[] num : nums) {
			graph.computeIfAbsent(num[0], k -> new TreeSet<Edge>())
			     .add(new Edge(num[1], num[2]));
		}

		return graph;
	}
	
	/**
	 * An object that has vertex label & cost from source.
	 * @author Gopal Selvaraj
	 *
	 */
	private static class Vertex implements Comparable<Vertex>{
		int label;
		int cost;
		public Vertex(int label, int cost) {
			this.label = label;
			this.cost = cost;
		}
		public int compareTo(Vertex that) {
			return this.cost - that.cost;
		}
	}
	
	/**
	 * 
	 * @param data
	 * @param src
	 * @return
	 */
	private static Map<Integer, ViaPoint> findShortestPath(int[][] data , int src) {
		
		Map<Integer,  Set<Edge>> graph = buildGraph(data);
	

		Map<Integer, ViaPoint> result = new HashMap<Integer, ViaPoint>();
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(src, 0));
		
		Set<Integer> visited = new HashSet<Integer>();
		
		while(!pq.isEmpty()) {
			Vertex current = pq.poll();
			visited.add(current.label);
			for(Edge edge : graph.getOrDefault(current.label, Collections.emptySet()) ) {
				int cost = current.cost + edge.weight;
				
				if(result.containsKey(edge.vertex)) {
				   cost = Math.min(cost, result.get(edge.vertex).distance);
				}
				result.computeIfAbsent(edge.vertex, k -> new ViaPoint())
				      .list.add(current.label);
				ViaPoint viaPpint = result.get(edge.vertex);
				viaPpint.list.add(edge.vertex);
				viaPpint.distance = cost;
				
				if(!visited.contains(edge.vertex)) {
					pq.offer(new Vertex(edge.vertex, cost));
				}
				
			}
		}
		
		return result;
	}
	 
	public static void main(String[] args) {
		int[][] data = { { 0, 12, 28 }, { 5, 6, 39 }, { 8, 6, 59 }, { 13, 15, 7 }, { 13, 12, 38 }, { 10, 12, 35 },
				{ 15, 3, 23 }, { 7, 11, 26 }, { 9, 4, 65 }, { 10, 2, 38 }, { 4, 7, 7 }, { 14, 15, 31 }, { 2, 12, 44 },
				{ 8, 10, 34 }, { 13, 6, 29 }, { 5, 14, 89 }, { 11, 16, 13 }, { 7, 3, 46 }, { 10, 15, 19 },
				{ 12, 4, 58 }, { 13, 16, 11 }, { 16, 4, 76 }, { 2, 0, 12 }, { 15, 0, 22 }, { 16, 12, 13 }, { 7, 1, 29 },
				{ 7, 14, 100 }, { 16, 1, 14 }, { 9, 6, 74 }, { 11, 1, 73 }, { 2, 11, 60 }, { 10, 11, 85 }, { 2, 5, 49 },
				{ 3, 4, 17 }, { 4, 9, 77 }, { 16, 3, 47 }, { 15, 6, 78 }, { 14, 1, 90 }, { 10, 5, 95 }, { 1, 11, 30 },
				{ 11, 0, 37 }, { 10, 4, 86 }, { 0, 8, 57 }, { 6, 14, 68 }, { 16, 8, 3 }, { 13, 0, 65 }, { 2, 13, 6 },
				{ 5, 13, 5 }, { 8, 11, 31 }, { 6, 10, 20 }, { 6, 2, 33 }, { 9, 1, 3 }, { 14, 9, 58 }, { 12, 3, 19 },
				{ 11, 2, 74 }, { 12, 14, 48 }, { 16, 11, 100 }, { 3, 12, 38 }, { 12, 13, 77 }, { 10, 9, 99 },
				{ 15, 13, 98 }, { 15, 12, 71 }, { 1, 4, 28 }, { 7, 0, 83 }, { 3, 5, 100 }, { 8, 9, 14 }, { 15, 11, 57 },
				{ 3, 6, 65 }, { 1, 3, 45 }, { 14, 7, 74 }, { 2, 10, 39 }, { 4, 8, 73 }, { 13, 5, 77 }, { 10, 0, 43 },
				{ 12, 9, 92 }, { 8, 2, 26 }, { 1, 7, 7 }, { 9, 12, 10 }, { 13, 11, 64 }, { 8, 13, 80 }, { 6, 12, 74 },
				{ 9, 7, 35 }, { 0, 15, 48 }, { 3, 7, 87 }, { 16, 9, 42 }, { 5, 16, 64 }, { 4, 5, 65 }, { 15, 14, 70 },
				{ 12, 0, 13 }, { 16, 14, 52 }, { 3, 10, 80 }, { 14, 11, 85 }, { 15, 2, 77 }, { 4, 11, 19 },
				{ 2, 7, 49 }, { 10, 7, 78 }, { 14, 6, 84 }, { 13, 7, 50 }, { 11, 6, 75 }, { 5, 10, 46 }, { 13, 8, 43 },
				{ 9, 10, 49 }, { 7, 12, 64 }, { 0, 10, 76 }, { 5, 9, 77 }, { 8, 3, 28 }, { 11, 9, 28 }, { 12, 16, 87 },
				{ 12, 6, 24 }, { 9, 15, 94 }, { 5, 7, 77 }, { 4, 10, 18 }, { 7, 2, 11 }, { 9, 5, 41 }, { 9, 20, 4100 } };
		
		
		Map<Integer, ViaPoint> shortest = findShortestPath(data, 1);
		System.err.println("Shortest from node 1");
		for(Map.Entry<Integer, ViaPoint> entry : shortest.entrySet()) {
			System.err.println(entry.getKey() + " -> " + entry.getValue().distance);
			System.err.println("via point " + entry.getValue().list);
		}
	}

/*
 Shortest from node 1
0 -> 30
1 -> 36
2 -> 18
3 -> 45
4 -> 28
5 -> 67
6 -> 53
7 -> 7
8 -> 38
9 -> 52
10 -> 46
11 -> 30
12 -> 48
13 -> 24
14 -> 87
15 -> 31
16 -> 35
20 -> 4152

 */
	

}
