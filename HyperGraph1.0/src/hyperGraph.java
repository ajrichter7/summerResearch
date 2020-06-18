import java.util.*;

public class hyperGraph {
	public ArrayList<hyperNode>nodes; 
	public ArrayList<hyperEdge>edges; 
	
	//constructor 
	public hyperGraph() {
		nodes = new ArrayList<hyperNode>();
		edges = new ArrayList<hyperEdge>(); 
	}
	
	//method to get the list of hyperNodes in the hyperGraph
	public ArrayList<hyperNode> getNodes(){
		return nodes; 
	}
	
	//methog to get the list of hyperEdges in the hyperGraph
	public ArrayList<hyperEdge> getEdges(){
		return edges; 
	}
	
	//method to print out the ID (or string associated with) each node in the hyperGraph
	public void printNodes() {
		for (hyperNode i : nodes) {
			System.out.println(i.getId());
		}
		System.out.println();
	}
	
	//method to print out ALL of the edges in the graph 
	//		format
	//	-------------
	//hyperEdge@memorylocation
	//Tail : list of hyperNodes in the tail (will return string associated with each node) 
	//Head : list of hyperNodes in the head (will return string associated with each node) 
	public void printEdges() {
		for (hyperEdge i : edges) {
			System.out.println(i);
			for (hyperNode j : i.getTail()) {
				System.out.println("Tail: " + j.getId());
			}
			for (hyperNode j : i.getHead()) {
				System.out.println("Head: " + j.getId());
			}
			System.out.println();
		}
	}
	
	//method to print out edge objects in the class 
	//		format
	//	-------------
	//Edge # : hyperEdge@memorylocation
	public void printhyperEdgeObjects() {
		for (int i = 0; i < edges.size(); i++) {
			System.out.println("Edge " + i + " : " + edges.get(i));
		}
		System.out.println();
	}
	
	//method to print out the edges leaving a specific hyperNode (ie will print out all the hyperEdges where node c tail)
	//		format
	//	-------------
	// "hyperEdges with hyperNode X in the tail"
	//Edge: hyperEdge@memorylocation (or edge number #) contains hyperNode X. 
	// 
	// Note: if no hyperEdges contain X in the tail set, then it will print:
	// "There are no hyperEdges with hyperNode X in the tail."
	public void printOutgoingEdges(String nodeID) {
		System.out.println("hyperEdges with hyperNode " + nodeID + " in the tail.");
		int count = 0; 
		for (int i = 0 ; i < edges.size(); i ++) {
			hyperEdge e = edges.get(i);
			for (hyperNode j : e.getTail()) {
				if (j.getId() == nodeID) {
					System.out.println("Edge: " + e + " (or edge number " + i + ") contains hyperNode " + nodeID);
					count ++; 
				}
			}
		}if (count == 0) {
			System.out.println("There are no hyperEdges with hyperNode " + nodeID + " in the tail.");
		}
		System.out.println();
	}
	
	//method to return all of the edges that contain hyperNode X in the tail as a HashSet<hyperEdge> 
	public HashSet<hyperEdge> getOutgoingEdges(String nodeID){
		HashSet<hyperEdge> outEdges = new HashSet<hyperEdge>();
		for (int i = 0 ; i < edges.size(); i ++) {
			hyperEdge e = edges.get(i);
			for (hyperNode j : e.getTail()) {
				if (j.getId() == nodeID) {
					outEdges.add(e);
				}
			}
		}
		return outEdges; 
	}
	
	//method to print out the edges entering a specific hyperNode (ie will print out all the hyperEdges where node c head)
	//		format
	//	-------------
	// "hyperEdges with hyperNode X in the head"
	//Edge: hyperEdge@memorylocation (or edge number #) contains hyperNode X. 
	// 
	// Note: if no hyperEdges contain X in the head set, then it will print:
	// "There are no hyperEdges with hyperNode X in the head."	
	public void printIncomingEdges(String nodeID) {
		System.out.println("hyperEdges with hyperNode " + nodeID + " in the head.");
		int count = 0; 
		for (int i = 0 ; i < edges.size(); i ++) {
			hyperEdge e = edges.get(i);
			for (hyperNode j : e.getHead()) {
				if (j.getId() == nodeID) {
					System.out.println("Edge: " + e + " (or edge number " + i + ") contains hyperNode " + nodeID);
					count ++; 
				}
			}
		}if (count == 0) {
			System.out.println("There are no hyperEdges with hyperNode " + nodeID + " in the head.");
		}
		System.out.println();
	}
	
	//method to return all of the edges that contain hyperNode X in the head as a HashSet<hyperEdge> 
	public HashSet<hyperEdge> getIncomingEdges(String nodeID){
		HashSet<hyperEdge> inEdges = new HashSet<hyperEdge>();
		for (int i = 0 ; i < edges.size(); i ++) {
			hyperEdge e = edges.get(i);
			for (hyperNode j : e.getHead()) {
				if (j.getId() == nodeID) {
					inEdges.add(e);
				}
			}
		}
		return inEdges; 		
	}
	
	//method to build the hyperGraph outlined in Figure 8.
	//Note, I need to work on this to figure out how to make it using a loop. Also, I am not using the addhyperEdge function 
	//at all in the code so far. I should make sure it works, if it doesn't fix it and then use that in later issues. 
	public void buildhyperGraph(hyperGraph graph) {
		List<String> ls = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R");
		for (String i : ls) {
			new hyperNode(graph, i);  
		}
		
		HashSet<hyperNode> tail = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(0));
		head.add(nodes.get(2));
		hyperEdge edge0 = new hyperEdge();
		edge0.setHead(head);
		edge0.setTail(tail);
		edges.add(edge0); 
		
		tail = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head1 = new HashSet<hyperNode>();
		
		tail.add(nodes.get(0));
		tail.add(nodes.get(1));
		head1.add(nodes.get(3));
		hyperEdge edge1 = new hyperEdge();
		edge1.setHead(head1);
		edge1.setTail(tail);
		edges.add(edge1); 

		HashSet<hyperNode> tail2 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head2 = new HashSet<hyperNode>();
		
		tail2.add(nodes.get(1));
		head2.add(nodes.get(4));
		hyperEdge edge2 = new hyperEdge();
		edge2.setHead(head2);
		edge2.setTail(tail2);
		edges.add(edge2); 

		HashSet<hyperNode> tail3 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head3 = new HashSet<hyperNode>();
		
		tail3.add(nodes.get(1));
		head3.add(nodes.get(5));
		hyperEdge edge3 = new hyperEdge();
		edge3.setHead(head3);
		edge3.setTail(tail3);
		edges.add(edge3); 

		HashSet<hyperNode> tail4 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head4 = new HashSet<hyperNode>();
		
		tail4.add(nodes.get(4));
		tail4.add(nodes.get(5));
		head4.add(nodes.get(8));
		hyperEdge edge4 = new hyperEdge();
		edge4.setHead(head4);
		edge4.setTail(tail4);
		edges.add(edge4); 

		HashSet<hyperNode> tail5 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head5 = new HashSet<hyperNode>();
		
		tail5.add(nodes.get(3));
		tail5.add(nodes.get(6));
		head5.add(nodes.get(11));
		hyperEdge edge5 = new hyperEdge();
		edge5.setHead(head5);
		edge5.setTail(tail5);
		edges.add(edge5); 

		HashSet<hyperNode> tail6 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head6 = new HashSet<hyperNode>();
		
		tail6.add(nodes.get(3));
		tail6.add(nodes.get(7));
		head6.add(nodes.get(12));
		hyperEdge edge6 = new hyperEdge();
		edge6.setHead(head6);
		edge6.setTail(tail6);
		edges.add(edge6); 

		HashSet<hyperNode> tail7 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head7 = new HashSet<hyperNode>();
		
		tail7.add(nodes.get(8));
		tail7.add(nodes.get(13));
		head7.add(nodes.get(14));
		hyperEdge edge7 = new hyperEdge();
		edge7.setHead(head7);
		edge7.setTail(tail7);
		edges.add(edge7); 

		HashSet<hyperNode> tail8 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head8 = new HashSet<hyperNode>();
		
		tail8.add(nodes.get(12));
		tail8.add(nodes.get(13));
		head8.add(nodes.get(16));
		hyperEdge edge8 = new hyperEdge();
		edge8.setHead(head8);
		edge8.setTail(tail8);
		edges.add(edge8); 

		HashSet<hyperNode> tail9 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head9 = new HashSet<hyperNode>();
		
		tail9.add(nodes.get(11));
		tail9.add(nodes.get(9));
		tail9.add(nodes.get(10));
		head9.add(nodes.get(15));
		hyperEdge edge9 = new hyperEdge();
		edge9.setHead(head9);
		edge9.setTail(tail9);
		edges.add(edge9); 
		
		HashSet<hyperNode> tail10 = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head10 = new HashSet<hyperNode>();
		
		tail10.add(nodes.get(15));
		tail10.add(nodes.get(16));
		head10.add(nodes.get(17));
		hyperEdge edge10 = new hyperEdge();
		edge10.setHead(head10);
		edge10.setTail(tail10);
		edges.add(edge10); 
	}
	
	public void addhyperEdge(HashSet<hyperNode> tail, HashSet<hyperNode> head){
		hyperEdge edge = new hyperEdge();
		edge.setHead(head);
		edge.setTail(tail);
		edges.add(edge); 
		for (hyperNode i : tail) {
			if (!nodes.contains(i)) {
				nodes.add(i);
			}
		}
		for (hyperNode i : head) {
			if (!nodes.contains(i)) {
				nodes.add(i);
			}
		}
	}

	//main method 
	public static void main(String[] args) {
		hyperGraph graph = new hyperGraph(); 
		graph.buildhyperGraph(graph); 
		
		graph.printIncomingEdges("D");
		
		HashSet<hyperEdge> in = graph.getIncomingEdges("D");
		System.out.println("graph.getIncomingEdges() function output: " + in);
		System.out.println();
		
		graph.printOutgoingEdges("A"); 
		
		HashSet<hyperEdge> out = graph.getOutgoingEdges("A");
		System.out.println("graph.getOutgoingEdges() function output: " + out);
		System.out.println();
		
		graph.printEdges(); 
		graph.printhyperEdgeObjects(); 
		graph.printNodes();
		
	}
	
}
