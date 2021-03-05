package com.gs.graph.clone;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.val)
    	  .append(" -> [");
    	for(Node neighbor : this.neighbors) {
    		sb.append(neighbor.val)
    		  .append(", ");
    	}
    	sb.append("]");
    	return sb.toString();
    }
}
