package com.gs.graph.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class SingleSourceShortestPathTracePath {

	private class Edge{
		private final char name;
		private final int distance;
		private final List<Character> trace;
		public Edge(char name, int distance){
			this(name, distance, new LinkedList<Character>());
		}
		public Edge(char name, int distance, List<Character> trace){
			this.name = name;
			this.distance = distance;
			this.trace = trace;
		}
		@Override
		public String toString() {
			return "Edge [name=" + name + ", distance=" + distance + "]";
		}
	}
	
	private class TracePoints{
		private final List<Character> points;
		private final int distance;
		public TracePoints(List<Character> points, int distance){
			this.points = points;
			this.distance = distance;
		}
	}
	private Map<Character, TracePoints> findShortestPath(char[][] adjList, char source) {
		Map<Character, List<Edge>> graph = buildGraph(adjList);
		Map<Character, TracePoints> costMap = new HashMap<Character, TracePoints>();
		Comparator<Edge> cmp = (a, b) -> (a.distance - b.distance);
		PriorityQueue<Edge> miniHeap = new PriorityQueue<Edge>(cmp);
		miniHeap.offer(new Edge(source, 0));
		Set<Character> visited = new HashSet<Character>();
		while(!miniHeap.isEmpty()){
			Edge current = miniHeap.poll();
			visited.add(current.name);
			for(Edge neighbor : graph.getOrDefault(current.name, Collections.emptyList())){
				char name = neighbor.name;
				int distance = current.distance + neighbor.distance;
				
				LinkedList<Character> trace  = new LinkedList<>(current.trace);
				trace.addLast(name);
				if(costMap.containsKey(name)){
					if(distance < costMap.get(name).distance){
						costMap.put(name, new TracePoints(trace, distance)); 
					}
				}else{
					costMap.put(name, new TracePoints(trace, distance));
				}
				if(!visited.contains(name)){
					miniHeap.offer(new Edge(name, distance, trace));
				}
			}
		}
		return costMap;
	}
	
	private Map<Character, List<Edge>> buildGraph(char[][] adjList) {
		Map<Character, List<Edge>> graph = new HashMap<Character, List<Edge>>();
		for(char[] lst : adjList){
			char src =  lst[0];
			char vertex = lst[1];
			int distance = lst[2];
			if(Character.isDigit(lst[2])) // '5' - true,, just 5 is false
			  distance = Character.getNumericValue(lst[2]);
			graph.computeIfAbsent(src, k -> new ArrayList<>())
			     .add(new Edge(vertex,distance));
		
		}
		return graph;
	}

	public static void main(String[] args) {
		testCaseGeneliaToHerFriends();
	}

	private static void testCaseGeneliaToHerFriends() {
		char[][] adjList = {
							{'G', 'H', '2'},
							{'G', 'C', 5},
							{'H', 'C', '2'},
							{'C', 'S', '5'},
							{'C', 'N', '1'},
							{'N', 'S', 1}
						   };
		Map<Character, TracePoints> result = new SingleSourceShortestPathTracePath().findShortestPath(adjList, 'G');
		System.out.println("Single Source Shortest path from G");
		for(Map.Entry<Character, TracePoints> entry : result.entrySet()){
			System.out.println("G - " + entry.getKey() + " | " + entry.getValue().distance);
			System.out.println(entry.getValue().points);
		}
	}



}
