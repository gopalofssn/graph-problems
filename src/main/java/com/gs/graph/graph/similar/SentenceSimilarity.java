package com.gs.graph.graph.similar;

import java.util.*;

public class SentenceSimilarity {

	public static boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
		
		Map<String , Set<String>> graph = new HashMap<String , Set<String>>();
		for(String[] pair : pairs) {
			Set<String> groupWords = new HashSet<String>();
			for(int i = 0; i < pair.length; i++) {
				String word = pair[i];
				groupWords.add(word);
				graph.putIfAbsent(word, groupWords);
			}
		}
		System.out.println("graph " + graph);dd
		final int LEN = words1.length;
		for(int i = 0; i < LEN; i++) {
			String word1 = words1[i];
			String word2 = words2[i];
			if(!graph.containsKey(word1) && graph.containsKey(word2)) return false;
			if(graph.containsKey(word1) && !graph.containsKey(word2)) return false;
			if(!graph.get(word1).contains(word2) || !graph.get(word2).contains(word1)) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String[][] pairs = { 
								{"great", "fine"}, 
								{"acting","darna", "drama"}, 
								{"skills","talent"}
						  };
		String[] words1 = {"great","acting", "skills"};
		String[] words2 = {"fine", "drama", "talent"};
		System.out.println(areSentencesSimilar( words1, words2, pairs));
	}

}
