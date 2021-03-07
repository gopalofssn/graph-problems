package com.gs.graph.graph.unionfind.account.merge;
import java.util.*;

public class AccountMergeByEmail {cc

   // i/p : "John", "Johny@gmail", "J@email.com"...
  public void merge(String[][] accounts) {
    Map<String, List<String>> graph = buildGraph(accounts);
    
    System.out.println(graph);
  }

  private Map<String, List<String>> buildGraph(String[][] accounts) {
    Map<String, List<String>> graph =  new HashMap<String, List<String>>();
    for(String[] account : accounts) {
      String name = account[0];
      List<String> emailList = new ArrayList<String>();
      for(int index = 1; index < account.length; index++) {
        String email = account[index];
        emailList.add(email);
        graph.putIfAbsent(email, emailList);
      }
    }
    return graph;
  }
  
  public static void main(String[] args) {
    String[][] accounts = {
                                {"J", "jm@mail.com", "j@mail.com"},
                                {"M", "m@mail.com", "mj@mail.com"},
                                {"J", "j@mail.com", "js@mail.com"},
                                {"L", "ml@mail.com", "l@mail.com"},
                            };
    new AccountMergeByEmail().merge(accounts);
  }
}
