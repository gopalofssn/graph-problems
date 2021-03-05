package com.gs.graph.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class SingleSourceShortestPath {

	private class Edge{
		private final char name;
		private final int distance;
		public Edge(char name, int distance){
			this.name = name;
			this.distance = distance;
		}
		@Override
		public String toString() {
			return "Edge [name=" + name + ", distance=" + distance + "]";
		}
	}
	
	private Map<Character, Integer> findShortestPath(char[][] adjList, char source) {
		Map<Character, List<Edge>> graph = buildGraph(adjList);
		Map<Character, Integer> costMap = new HashMap<Character, Integer>();
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
				if(costMap.containsKey(name)){
					distance = Math.min(distance, costMap.get(name));
				}
				costMap.put(name, distance);
				if(!visited.contains(name)){
					miniHeap.offer(new Edge(name, distance));
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
		Map<Character, Integer> result = new SingleSourceShortestPath().findShortestPath(adjList, 'G');
		System.out.println("Single Source Shortest path from G");
		for(Map.Entry<Character, Integer> entry : result.entrySet()){
			System.out.println("G - " + entry.getKey() + " | " + entry.getValue());
		}
	}
	
}
