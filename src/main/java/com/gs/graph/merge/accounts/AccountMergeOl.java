package com.gs.graph.merge.accounts;

import java.util.*;

public class AccountMergeOl {


	private static List<List<String>> mergeAccounts(List<List<String>> accounts) {
		Map<String, String> emailNameMap = new HashMap<String, String>();
		Map<String, Set<String>> graph = new HashMap<String, Set<String>>();
		buildGraph(accounts, graph, emailNameMap);
System.err.println(graph);
		List<List<String>> mergedAccounts = new ArrayList<List<String>>();
		Set<String> visitedEmails = new HashSet<String>();
		for(Map.Entry<String, String> entry : emailNameMap.entrySet()){
			String name = entry.getValue();
			String email = entry.getKey();
			if(visitedEmails.contains(email)) continue;
			List<String> accountEmails = new ArrayList<String>();
			dfs(graph, email, visitedEmails, accountEmails);
			Collections.sort(accountEmails);
			accountEmails.add(0, name);
			mergedAccounts.add(accountEmails);
		}
		return mergedAccounts; 
	}
	
	private static void dfs(Map<String, Set<String>> graph, String email, Set<String> visitedEmails, List<String> accountEmails) {
		if(visitedEmails.contains(email)) return;
		accountEmails.add(email);
		visitedEmails.add(email);
		for(String neighbor : graph.getOrDefault(email, Collections.emptySet())){
			dfs(graph, neighbor, visitedEmails, accountEmails);
		}
	}

	private static void buildGraph(List<List<String>> accounts, Map<String, Set<String>> graph, Map<String, String> emailNameMap) {
		for(List<String> account : accounts){
			String name = account.get(0);
			for(int i = 1; i < account.size(); i++){
				String email = account.get(i);
				emailNameMap.put(email, name);
				graph.putIfAbsent(email, new HashSet<String>());
				if(i == 1) continue;
				String prevEmail = account.get(i - 1);
				graph.get(prevEmail).add(email);
				graph.get(email).add(prevEmail);
			}
		}
	}

	public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList<List<String>>();
		accounts.add(new ArrayList<String>(Arrays.asList("J", "jm@mail.com", "j@mail.com")));
		accounts.add(new ArrayList<String>(Arrays.asList("M", "m@mail.com", "mj@mail.com")));
		accounts.add(new ArrayList<String>(Arrays.asList("J", "j@mail.com", "js@mail.com")));
		accounts.add(new ArrayList<String>(Arrays.asList("L", "ml@mail.com", "l@mail.com")));
		
		accounts = mergeAccounts(accounts);
		for(List<String> ac : accounts){
			System.out.println(ac);
		}
	}


}
