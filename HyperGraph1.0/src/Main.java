
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
		edu.reed.hypergraph.HyperGraph graph = new edu.reed.hypergraph.HyperGraph(); 
		
		//Use of String[] args parameter if equal to "Toy" buildToyHyperGraph else create 
		//HyperGraph from file 
		if (args[0].equals("Toy")) {
			edu.reed.hypergraph.FileIO.buildToyHyperGraph(graph); 
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
			edu.reed.hypergraph.HyperGraph graph2 = edu.reed.hypergraph.FileIO.makehyperGraph("small_molecule_filter"); 
			
			for (edu.reed.hypergraph.HyperNode n : graph2.nodes) {
				if (!n.getAttributes().equals(null)) {
					System.out.println(n.getAttributesValues());
				}
			}
			System.out.println();
			System.out.println("Results for reactome hypergraph construction:");
			System.out.println("_____________________________________________");
			edu.reed.hypergraph.FileIO.makehyperGraph("reactome"); 
			

		}
		
		// Running and understanding BVisit and BRelaxation Algorithms (lines 36-92) 
		// Note: this will not work if args[0] is "File;" hard coded and must be run on toy HyperGraph to work 
		HashSet<edu.reed.hypergraph.HyperNode> n = new HashSet<edu.reed.hypergraph.HyperNode>(); 
		
		System.out.println("bVisit run on source set -----------------");
		System.out.println();
		for (int i = 0 ; i <2; i ++) {
			edu.reed.hypergraph.HyperNode j = graph.nodes.get(i); 
			n.add(j); 
		}
		
		HashSet<String> sourceSet = new HashSet<String>(); 
		for (edu.reed.hypergraph.HyperNode node : n) {
			sourceSet.add(node.getId());
		}
		System.out.println("n is the source set containing : " + sourceSet);
		System.out.println();
		
		edu.reed.hypergraph.Bobject x = edu.reed.hypergraph.HyperAlgorithms.bVisit(graph, n); 
		
	
		HashSet<String> B = new HashSet<String>(); 
		for (edu.reed.hypergraph.HyperNode i : x.getBset()) {
			B.add(i.getId());
		}
		System.out.println("B Set (nodes that are B-connected): " + B);
		System.out.println(); System.out.println();
		System.out.println("X Set (edges that have been traversed): ");
		System.out.println("________________________________________");
		for(edu.reed.hypergraph.HyperEdge e : x.getXset()) {
			e.printHyperEdge();
		}
		System.out.println();System.out.println();
		System.out.println("R Set (edges reached but not traversed): ");
		System.out.println("_________________________________________");
		for(edu.reed.hypergraph.HyperEdge e: x.getRset()) {
			e.printHyperEdge();
		}
		
		System.out.println();
		System.out.println("bRelaxation distances");
		System.out.println("_____________________");

		Hashtable<edu.reed.hypergraph.HyperNode, Integer>dist = edu.reed.hypergraph.HyperAlgorithms.bRelaxation(graph,n);
		
		Set<edu.reed.hypergraph.HyperNode> nodes = dist.keySet();
		TreeSet<String> sorted = new TreeSet<String>(); 
		for (edu.reed.hypergraph.HyperNode nee : nodes) {
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
