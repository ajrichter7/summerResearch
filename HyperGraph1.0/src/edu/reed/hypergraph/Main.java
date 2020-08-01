package edu.reed.hypergraph;
import java.util.*;
import java.io.*; 

/**
 * 
 * @author alexrichter
 * @version 1.0 
 *
 */

public class Main {
	

	public static void main(String[] args) throws FileNotFoundException {
		// Below are some tests that I have conducted to demonstrate the methods and algorithms outlined, 
		// feel free to comment out the ones that are not relevant to what you need. 
		HyperGraph graph = new HyperGraph(); 
		
		//Use of String[] args parameter if equal to "Toy" buildToyHyperGraph else create 
		//HyperGraph from file 
		if (args[0].equals("Toy")) {
			FileIO.buildToyHyperGraph(graph); 
			System.out.println("Nodes in graph:");
			System.out.println("_______________");
			graph.printNodes();
			System.out.println("Edges in graph:");
			System.out.println("_______________");
			graph.printEdges();
		}
		
		if (args[0].equals("File")){
			System.out.println("Results for small_molecule_filter hypergraph construction:");
			System.out.println("__________________________________________________________");
			HyperGraph graph2 = FileIO.makehyperGraph("small_molecule_filter"); 
			
			for (HyperNode n : graph2.nodes) {
				if (!n.getAttributes().equals(null)) {
					System.out.println(n.getAttributesValues());
				}
			}
			System.out.println();
			System.out.println("Results for reactome hypergraph construction:");
			System.out.println("_____________________________________________");
			FileIO.makehyperGraph("reactome"); 
			

		}
		
		// Running and understanding BVisit and BRelaxation Algorithms (lines 36-92) 
		// Note: this will not work if args[0] is "File;" hard coded and must be run on toy HyperGraph to work 
		HashSet<HyperNode> n = new HashSet<HyperNode>(); 
		
		System.out.println("bVisit run on source set -----------------");
		System.out.println();
		for (int i = 0 ; i <2; i ++) {
			HyperNode j = graph.nodes.get(i); 
			n.add(j); 
		}
		
		HashSet<String> sourceSet = new HashSet<String>(); 
		for (HyperNode node : n) {
			sourceSet.add(node.getId());
		}
		System.out.println("n is the source set containing : " + sourceSet);
		System.out.println();
		
		Bobject x = HyperAlgorithms.bVisit(graph, n); 
		
	
		HashSet<String> B = new HashSet<String>(); 
		for (HyperNode i : x.getBset()) {
			B.add(i.getId());
		}
		System.out.println("B Set (nodes that are B-connected): " + B);
		System.out.println(); System.out.println();
		System.out.println("X Set (edges that have been traversed): ");
		System.out.println("________________________________________");
		for(HyperEdge e : x.getXset()) {
			e.printHyperEdge();
		}
		System.out.println();System.out.println();
		System.out.println("R Set (edges reached but not traversed): ");
		System.out.println("_________________________________________");
		for(HyperEdge e: x.getRset()) {
			e.printHyperEdge();
		}
		
		System.out.println();
		System.out.println("bRelaxation distances");
		System.out.println("_____________________");

		Hashtable<HyperNode, Integer>dist = HyperAlgorithms.bRelaxation(graph,n);
		
		Set<HyperNode> nodes = dist.keySet();
		TreeSet<String> sorted = new TreeSet<String>(); 
		for (HyperNode nee : nodes) {
			sorted.add(nee.getId());
		}
		for (String s : sorted) {
			if (dist.get(graph.gethyperNode(s)) == Integer.MAX_VALUE) {
				System.out.println("Node " + s + " : INFINITY"); 
			} else {
			System.out.println("Node " + s + " : " + dist.get(graph.gethyperNode(s)));
			}
		}
		
	}

}
