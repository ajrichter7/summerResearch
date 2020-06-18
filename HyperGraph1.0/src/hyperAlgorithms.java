import java.util.*; 

public class hyperAlgorithms {

	//attempting to implement algorithm 1 from the paper 
	public static bObject bVisit(hyperGraph graph, HashSet<hyperNode> lsofNodes) {
		bObject results = new bObject(); 

		//initialize C[e] <-- counter of reached nodes in e's (where e c E) tail
		Hashtable<hyperEdge, Integer> count = new Hashtable<hyperEdge, Integer>(); 
		for (hyperEdge i : graph.edges) {
			count.put(i, 0);
		}
		//B <- HashSet<hyperNodes> 
		HashSet<hyperNode> B = new HashSet<hyperNode>(lsofNodes); 

		//X == HashSet<hyperEdge> initialized to 0 
		HashSet<hyperEdge> X = new HashSet<hyperEdge>(); 

		//Q <- want a queue of hyperNodes to traverse in the graph which comes from HashSet<String> lsofNodes
		Queue<hyperNode> Q = new LinkedList<hyperNode>(); 

		for (hyperNode i : lsofNodes) {
			Q.add(i); 
		}

		while(!Q.isEmpty()) {
			hyperNode n = Q.poll(); 
			HashSet<hyperEdge> e = graph.getOutgoingEdges(n.getId()); 
			for (hyperEdge i : e) {
				count.put(i, count.get(i) + 1);
				if (count.get(i) == i.tailSize()){
					HashSet<hyperNode> h = new HashSet<hyperNode>(); 
					for (hyperNode nodeinHead : i.getHead()) {
						h.add(nodeinHead); 
					}
					for (hyperNode nodeinHead : h) {
						if(!B.contains(nodeinHead)) {
							Q.add(nodeinHead);
						}
					}
					B.addAll(h); 
					X.add(i);
				}
			}
		}

		HashSet<hyperEdge> R = new HashSet<hyperEdge>(); 

		for (hyperEdge e : graph.edges) {
			if(count.get(e) >= 1 && count.get(e) < e.tailSize()) {
				R.add(e); 
			}
		}

		results.setB(B);
		results.setX(X);
		results.setR(R);

		return results; 
	}

	public void bRelaxation(hyperGraph graph) {

	}

}

