import java.util.*;
import java.io.*; 

public class main {
	public static hyperGraph makehyperGraph(String fileName) throws FileNotFoundException {
		Hashtable<String, String[]> hyperNodes = new Hashtable<String, String[]>(); 
		String delim = ";"; 
		String sep = "\t"; 

		
		//Reading through the hyperNode file 
		Scanner nodeScanner = new Scanner(new File(fileName + "-hypernodes.txt"));
		while(nodeScanner.hasNextLine()) {
			String line = nodeScanner.nextLine(); 
			Scanner temp = new Scanner(line); 
			if(temp.next() == "#") {
				continue;
			}
			String[] strippedls = line.trim().split(sep);
			List<String> rowList = new ArrayList<String>(strippedls.length);
			rowList.addAll(Arrays.asList(strippedls));

			if (strippedls.length == 1) {
				String[] strArray = new String[] {"OtherComplexes-FIX"};
				hyperNodes.put(rowList.get(0), strArray);
			}
			else {
				hyperNodes.put(rowList.get(0), rowList.get(1).split(delim));
			}
			temp.close();
		}
		
		//Construct a new instance of a hyperGraph 
		hyperGraph graph = new hyperGraph(); 
		nodeScanner.close();
		
		Set<String> keys = hyperNodes.keySet();
		
		//Adding hyperNodes to the graph 
		String none = "None";
		for(String k : keys) {
			if (!k.equals(none)) {
				if(hyperNodes.get(k) != null) {
					graph.addhyperNode(k, hyperNodes.get(k));
				}
				else {
					graph.addhyperNode(k); 
				}
			}
		}
		
		//Initialize counters to check to see if reactions needed to be skipped 
		//TODO: make sure this counter actually works because for Reactome file it returns 0 
		int skipped1 = 0; 
		int skipped2 = 0; 
		
		//Reading the hyperEdge file
		Scanner edgeScanner = new Scanner(new File(fileName + "-hyperedges.txt"));
		while(edgeScanner.hasNextLine()) {
			String line = edgeScanner.nextLine(); 
			Scanner temp = new Scanner(line); 
			if(temp.next() == "#") {
				continue;
			}	
			String[] strippedls = line.trim().split(sep);
			List<String> rowList = new ArrayList<String>(strippedls.length);
			rowList.addAll(Arrays.asList(strippedls));
			HashSet<String> tail = new HashSet<String>(); 
			HashSet<String> head = new HashSet<String>();
			if(!rowList.get(0).equals(none)) {
				for(String s : rowList.get(0).split(delim)) {
					tail.add(s);
				}
			}
			if(!rowList.get(1).equals(none)) {
				for(String s : rowList.get(1).split(delim)) {
					head.add(s);
				}
			}
			if(!rowList.get(2).equals(none)) {
				for(String s : rowList.get(2).split(delim)) {
					tail.add(s);
				}
			}
			if(!rowList.get(3).equals(none)) {
				for(String s : rowList.get(3).split(delim)) {
					tail.add(s);
				}
			}
			if (tail.contains("HSA") || head.contains("HSA")) {
				skipped1 += 1; 
			}
			else if(tail.size() == 0 || head.size() == 0){
				skipped2 += 1; 
			}
			else {
				//Creating the tail set of a hyperEdge 
				HashSet<hyperNode> tailSet = new HashSet<hyperNode>(); 
				for (String s : tail) {
					if (!s.equals(none)) {
						if(graph.gethyperNode(s) != null) {
							tailSet.add(graph.gethyperNode(s));
						}
						else {
							hyperNode node = new hyperNode(s); 
							if(!graph.nodes.contains(node)) {
								graph.nodes.add(node);
							}
							tailSet.add(node); 
						}
					}
				}
				//Creating the head set of a hyperEdge 
				HashSet<hyperNode> headSet = new HashSet<hyperNode>(); 
				for (String s : head) {
					if (!s.equals(none)) {
						if(graph.gethyperNode(s) != null) {
							headSet.add(graph.gethyperNode(s));
						}
						else {
							hyperNode node = new hyperNode(s); 
							if(!graph.nodes.contains(node)) {
								graph.nodes.add(node);
							}
							headSet.add(node); 
						}
					}
				}
				//Adding new hyperEdge to the class 
				graph.addhyperEdge(tailSet, headSet);
			}
			temp.close();
		}
		

		System.out.println(skipped1 + " reactions skipped because of Reactome identifier.");
		System.out.println(skipped2 + " reactions skipped because of empty tail or head.");
		
		//Returns the hyperGraph created from the files 
		return graph; 
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		// Below are some tests that I have conducted to demonstrate the methods and algorithms outlined, 
		// feel free to comment out the ones that are not relevant to what you need. 
		hyperGraph graph = new hyperGraph(); 
		graph.buildhyperGraph(); 
		
		graph.printIncomingEdges("D");
		
		HashSet<hyperEdge> in = graph.getIncomingEdges("D");
		System.out.println("graph.getIncomingEdges() function output: " + in);
		System.out.println();
		
		graph.printOutgoingEdges("A"); 
		
		HashSet<hyperEdge> out = graph.getOutgoingEdges("A");
		System.out.println("graph.getOutgoingEdges() function output for 'A' : " + out);
		System.out.println();
		
		graph.printEdges(); 
		graph.printhyperEdgeObjects(); 
		graph.printNodes();
		
		HashSet<hyperNode> n = new HashSet<hyperNode>(); 
		
		System.out.println("bVisit run on source set -----------------");
		System.out.println();
		for (int i = 0 ; i <2; i ++) {
			hyperNode j = graph.nodes.get(i); 
			n.add(j); 
		}
		
		HashSet<String> sourceSet = new HashSet<String>(); 
		for (hyperNode node : n) {
			sourceSet.add(node.getId());
		}
		System.out.println("n is the source set containing : " + sourceSet);
		System.out.println();
		
		bObject x = hyperAlgorithms.bVisit(graph, n); 
		
	
		HashSet<String> B = new HashSet<String>(); 
		for (hyperNode i : x.getBset()) {
			B.add(i.getId());
		}
		System.out.println("B Set (nodes that are B-connected): " + B);
		System.out.println(); System.out.println();
		System.out.println("X Set (edges that have been traversed): ");
		System.out.println("________________________________________");
		for(hyperEdge e : x.getXset()) {
			e.printHyperEdge();
		}
		System.out.println();System.out.println();
		System.out.println("R Set (edges reached but not traversed): ");
		System.out.println("_________________________________________");
		for(hyperEdge e: x.getRset()) {
			e.printHyperEdge();
		}
		
		System.out.println();
		System.out.println("bRelaxation distances");
		System.out.println("_____________________");

		Hashtable<hyperNode, Integer>dist = hyperAlgorithms.bRelaxation(graph,n);
		
		Set<hyperNode> nodes = dist.keySet();
		TreeSet<String> sorted = new TreeSet<String>(); 
		for (hyperNode nee : nodes) {
			sorted.add(nee.getId());
		}
		for (String s : sorted) {
			if (dist.get(graph.gethyperNode(s)) == Integer.MAX_VALUE) {
				System.out.println("Node " + s + " : INFINITY"); 
			} else {
			System.out.println("Node " + s + " : " + dist.get(graph.gethyperNode(s)));
			}
		}
		
		System.out.println();
		System.out.println("Results for small_molecule_filter hypergraph construction: ");
		System.out.println("___________________________________________________________");
		makehyperGraph("small_molecule_filter"); 
		
		System.out.println();
		System.out.println("Results for reactome hypergraph construction: ");
		System.out.println("______________________________________________");
		makehyperGraph("reactome"); 

	}

}
