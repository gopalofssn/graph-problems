package com.gs.graph.unionfind;

import java.util.*;

public class AccountMerge {

	 public List<List<String>> accountsMerge(List<List<String>> accounts) {
	        
	        Map<String, Set<String>> graph = new HashMap<String, Set<String>>(); // email , set<email>
	        Map<String, String> emailNameMap = new HashMap<String, String>(); // email, name
	        buildGraph(accounts, graph, emailNameMap);
	        Set<String> visited = new HashSet<String>();// visited emails
	        List<List<String>> result = new ArrayList<List<String>>();
	        /*for(Map.Entry<String, Set<String>> entry : graph.entrySet()){
	           String email = entry.getKey();
	           if(visited.contains(email)) continue;
	           List<String> account = new ArrayList<String>();
	           String name = emailNameMap.get(email);
	           account.add(name);
	           dfs(graph, email, visited, account);
	           result.add(account);
	        }*/
	        for(Map.Entry<String, String> entry : emailNameMap.entrySet()) {
	        	   String email = entry.getKey();
		           if(visited.contains(email)) continue;
		           List<String> account = new ArrayList<String>();
		           String name = emailNameMap.get(email);
		           account.add(name);
		           dfs(graph, email, visited, account);
		           result.add(account);
	        }
	        return result;
	    }
	    
	    private void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> account){
	    	if(visited.contains(email)) return;
	        account.add(email);
	        visited.add(email);
	        Set<String> neighboursEmails = graph.getOrDefault(email, Collections.emptySet());
	        for(String neighborEmail : neighboursEmails){
	            dfs(graph, neighborEmail, visited, account);
	        }
	    }
	    
	    private void buildGraph(List<List<String>> accounts, Map<String, Set<String>> graph, Map<String, String> emailNameMap){
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
		List<List<String>> result = new ArrayList<List<String>>();
		result.add(new ArrayList<String>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")));
		result.add(new ArrayList<String>(Arrays.asList("John", "johnnybravo@mail.com")));
		result.add(new ArrayList<String>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")));
		result.add(new ArrayList<String>(Arrays.asList("Mary", "mary@mail.com")));
		
		result = new AccountMerge().accountsMerge(result);
		for(List<String> acc : result) {
			System.out.println(acc);
		}
	}

}
