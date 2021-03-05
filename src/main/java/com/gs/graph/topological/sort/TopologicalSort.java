package com.gs.topological.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
   Can be performed only on DAG ( directed acyclic graph)
   
   E -> F -> K 
   |    \
   .     ---------|
   A -> D -> H -> J -> M
   .   . \   \
   |   |  \    \
   C-> B   G  -> I -> L 
   
   
   Time : ?
   Space : ? 
   
   approach : DFS recurssive
 */
public class TopologicalSort {

	private static char[] topSort(char[][] grid) {
		Map<Character, List<Character>> graph = new HashMap<>();
	    buildGraph(grid ,graph);
		char[] result = new char[numberOfVetices];
		int count = (numberOfVetices - 1);
		Set<Character> visited = new HashSet<Character>();
		for(Map.Entry<Character, List<Character>> entry : graph.entrySet()){
			char vertex = entry.getKey();
			if(visited.contains(vertex)) continue;
			List<Character> visiting = new ArrayList<>();
			dfs(graph, vertex, visited, visiting);
			System.out.println("visiting " + visiting);
			for(char visit : visiting){
				result[count--] = visit;
			}
		}
		return result;
	}

	private static void dfs(Map<Character, List<Character>> graph, char src, Set<Character> visited, List<Character> visiting) {
		if(visited.contains(src)) return;
		visited.add(src);
		for(char neighborVertex : graph.getOrDefault(src,  Collections.emptyList())){
			dfs(graph, neighborVertex, visited, visiting);
		}
		visiting.add(src);
	}

	private static void buildGraph(char[][] grid, Map<Character, List<Character>> graph) {
		for(char[] edge : grid){
			char src = edge[0];
			char dest = edge[1];
			graph.putIfAbsent(src, new ArrayList<>());
			graph.get(src).add(dest);
		}
	}

	public static void main(String[] args) {
		    // src and dest
		char[][] grid = {
							{'A','D'}, 
							{'E','A'},
							{'E','D'},
							{'E', 'F'},
							{'C','A'},
							{'C', 'B'},
							{'B', 'D'},
							{'D','H'},
							{'D', 'G'},
							{'F', 'K'},
							{'F', 'J'},
							{'K', 'J'},
							{'H', 'J'},
							{'H','I'},
							{'J', 'M'},
							{'J', 'L'},
							{'M', 'E'}
						};
		System.out.println("Top Sort - " + Arrays.toString( topSort(grid, 13) ));
	}
}
