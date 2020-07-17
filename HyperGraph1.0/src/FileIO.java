import java.io.*; 
import java.util.*; 

/**
 * 
 * @author alexrichter
 * @version 1.0 
 *
 */

public class FileIO {
	/**
	 * Makes a HyperGraph from a set of files. 
	 * <p>
	 * Needs two files: one for -hypernodes.txt and one ofr -hyperedges.txt. It reads through HyperNode file first 
	 * and add the nodes to a new HyperGraph object. It then reads through the HyperEdge file setting the tail and head 
	 * sets for the HyperEdges. The HyperEdges are then added to the graph. After adding the edges and nodes, will 
	 * print out the number of reactions that were skipped. 
	 * @param fileName String prefix for two files 
	 * @return new HyperGraph object 
	 * @throws FileNotFoundException
	 */
	public static HyperGraph makehyperGraph(String fileName) throws FileNotFoundException {
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
		HyperGraph graph = new HyperGraph(); 
		nodeScanner.close();
		
		Set<String> keys = hyperNodes.keySet();
		Map<String, String> map = new HashMap<String, String>();
		
		//Adding hyperNodes to the graph 
		String none = "None";
		for(String k : keys) {
			if (!k.equals(none)) {
				if(hyperNodes.get(k) != null) {
					map = new HashMap<>();
					for (String s : hyperNodes.get(k)) {
						map.put(k, s);
					}
					graph.addhyperNode(k, map);
				}
				else {
					graph.addhyperNode(k); 
				}
			}
		}
		
		//Initialize counters to check to see if reactions needed to be skipped 
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
				HashSet<HyperNode> tailSet = new HashSet<HyperNode>(); 
				for (String s : tail) {
					if (!s.equals(none)) {
						if(graph.gethyperNode(s) != null) {
							tailSet.add(graph.gethyperNode(s));
						}
						else {
							HyperNode node = new HyperNode(s); 
							if(!graph.nodes.contains(node)) {
								graph.nodes.add(node);
							}
							tailSet.add(node); 
						}
					}
				}
				//Creating the head set of a hyperEdge 
				HashSet<HyperNode> headSet = new HashSet<HyperNode>(); 
				for (String s : head) {
					if (!s.equals(none)) {
						if(graph.gethyperNode(s) != null) {
							headSet.add(graph.gethyperNode(s));
						}
						else {
							HyperNode node = new HyperNode(s); 
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
	
	/**
	 * Creates a toy HyperGraph to perform simple tests. 
	 * <p>
	 * This creates the HyperGraph outlined in Figure 8 of Franzese N, Groce A, Murali TM, Ritz A (2019) 
	 * Hypergraph-based connectivity measures for signaling pathway topologies. 
	 * PLOS Computational Biology 15(10): e1007384. https://doi.org/10.1371/journal.pcbi.1007384.
	 * This function allows for the user to test how the HyperGraph algorithms work and can compare the outcome 
	 * to a tangible figure for understanding. The figure is located in the ReadMe of the github. 
	 * @param graph HyperGraph 
	 */
	public static void buildToyHyperGraph(HyperGraph graph) {
		List<String> ls = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R");
		for (String i : ls) {
			graph.addhyperNode(i);  
		}
		
		HashSet<HyperNode> tail = new HashSet<HyperNode>(); 
		HashSet<HyperNode> head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(0));
		head.add(graph.nodes.get(2));
		graph.addhyperEdge(tail, head); 
		
		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(0));
		tail.add(graph.nodes.get(1));
		head.add(graph.nodes.get(3));
		graph.addhyperEdge(tail, head); 

		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(1));
		head.add(graph.nodes.get(4));
		graph.addhyperEdge(tail, head); 

		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(1));
		head.add(graph.nodes.get(5));
		graph.addhyperEdge(tail, head); 

		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(4));
		tail.add(graph.nodes.get(5));
		head.add(graph.nodes.get(8));
		graph.addhyperEdge(tail, head); 

		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(3));
		tail.add(graph.nodes.get(6));
		head.add(graph.nodes.get(11));
		graph.addhyperEdge(tail, head);  

		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(3));
		tail.add(graph.nodes.get(7));
		head.add(graph.nodes.get(12));
		graph.addhyperEdge(tail, head); 

		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(8));
		tail.add(graph.nodes.get(13));
		head.add(graph.nodes.get(14));
		graph.addhyperEdge(tail, head); 

		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(12));
		tail.add(graph.nodes.get(13));
		head.add(graph.nodes.get(16));
		graph.addhyperEdge(tail, head); 

		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(11));
		tail.add(graph.nodes.get(9));
		tail.add(graph.nodes.get(10));
		head.add(graph.nodes.get(15));
		graph.addhyperEdge(tail, head); 
		
		tail = new HashSet<HyperNode>(); 
		head = new HashSet<HyperNode>();
		
		tail.add(graph.nodes.get(15));
		tail.add(graph.nodes.get(16));
		head.add(graph.nodes.get(17));
		graph.addhyperEdge(tail, head); 
	}
}
