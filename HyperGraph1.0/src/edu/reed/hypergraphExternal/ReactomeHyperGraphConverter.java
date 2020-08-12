package edu.reed.hypergraphExternal;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*; 

import org.gk.model.GKInstance;
import org.gk.model.ReactomeJavaConstants;
import org.gk.persistence.DiagramGKBReader;
import org.gk.persistence.MySQLAdaptor;
import org.gk.render.Node;
import org.gk.render.Renderable;
import org.gk.render.RenderablePathway;
import org.gk.schema.InvalidAttributeException;
import org.junit.Test;

import edu.reed.hypergraph.HyperEdge;
import edu.reed.hypergraph.HyperGraph;
import edu.reed.hypergraph.HyperNode;

/**
 * Construct a HyperGraph from a given pathway diagram.
 *
 * General procedure is as follows:
 *
 * (1) Get the diagram from invoking caller.
 * (2) Read in pathway from diagram reader.
 * (3) Iterate over all rendered components.
 * (4) If component is a Reactome HyperEdge, convert to their HyperEdge.
 *      (*) By converting inputs, catalysts, inhibitors, activators to 'tail'.
 *      (*) By converting output to 'head'.
 * (5) Add edges to HyperGraph.
 */
public class ReactomeHyperGraphConverter {
    private Map<Long, HyperNode> hyperNodes;

    public ReactomeHyperGraphConverter() {
        hyperNodes = new HashMap<Long, HyperNode>();
    }

    /**
     * Convert a Pathway Diagram to a HyperGraph.
     *
     * @param diagram
     * @return HyperGraph
     * @throws InvalidAttributeException
     * @throws Exception
     */
    public HyperGraph createHyperGraph(GKInstance diagram) throws InvalidAttributeException, Exception {
        HyperGraph graph = new HyperGraph();

        DiagramGKBReader reader = new DiagramGKBReader();
        RenderablePathway pathway = reader.openDiagram(diagram);
        List<Renderable> components = pathway.getComponents();

        // Return empty graph to avoid null exception.
        if (components == null || components.size() == 0)
            return graph;

        edu.reed.hypergraph.HyperEdge edge = null;

        for (Renderable component : components) {
            // Currently, the only nodes included in the HyperGraph are those
            // that are connected to at least one edge.
            if (component instanceof org.gk.render.HyperEdge) {
                edge = createHyperEdge((org.gk.render.HyperEdge) component);
                if (edge != null)
                    graph.edges.add(edge);
            }
        }

        return graph;
    }

    /**
     * Convert a Reactome HyperEdge to a Reed HyperEdge.
     *
     * @param org.gk.render.edge
     * @return edu.reed.HyperEdge
     * @throws InvalidAttributeException
     * @throws Exception
     */
    private edu.reed.hypergraph.HyperEdge createHyperEdge(org.gk.render.HyperEdge edge) throws InvalidAttributeException, Exception {
        if (edge == null)
            return null;

        // Handle conversions to Head.
        List<Node> outputs = edge.getOutputNodes();
        HashSet<HyperNode> head = createHyperNodeSet(outputs);

        // Handle conversions to Tail.
        List<Node> inputs = edge.getInputNodes();
        List<Node> activators = edge.getActivatorNodes();
        List<Node> catalysts= edge.getHelperNodes();
        List<Node> inhibitors= edge.getInhibitorNodes();
        HashSet<HyperNode> tail = createHyperNodeSet(inputs);
        tail.addAll(createHyperNodeSet(activators));
        tail.addAll(createHyperNodeSet(catalysts));
        tail.addAll(createHyperNodeSet(inhibitors));

        // Handle conversions to HyperEdge.
        edu.reed.hypergraph.HyperEdge hyperEdge = new edu.reed.hypergraph.HyperEdge();
        hyperEdge.setHead(head);
        hyperEdge.setTail(tail);

        return hyperEdge;
    }

    /**
     * Convert a list of Nodes to a set of HyperNodes.
     *
     * @param nides
     * @return HashSet
     * @throws InvalidAttributeException
     * @throws Exception
     */
    HashSet<HyperNode> createHyperNodeSet(List<Node> nodes) throws InvalidAttributeException, Exception {
        HyperNode hyperNode = null;
        HashSet<HyperNode> set = new HashSet<HyperNode>();

        for (Node node : nodes) {
            // Check if a new node needs to be created.
            Long dbid = node.getReactomeId();
            if (hyperNodes.containsKey(dbid))
                hyperNode = hyperNodes.get(dbid);

            else {
                hyperNode = createHyperNode(node);
                hyperNodes.put(dbid, hyperNode);
            }

            set.add(hyperNode);
        }

        return set;
    }

    /**
     * Convert a Node to a HyperNode.
     *
     * @param node
     * @return HyperNode
     * @throws Exception
     * @throws InvalidAttributeException
     */
    private HyperNode createHyperNode(Node node) throws InvalidAttributeException, Exception {
        if (node == null)
            return null;

        edu.reed.hypergraph.HyperNode hyperNode = new edu.reed.hypergraph.HyperNode(node.getDisplayName());
        Map<String, String> attributes = new HashMap<String, String>();

        // DBID
        attributes.put("DB_ID", node.getReactomeId() + "");

        // Stable Identifier
        GKInstance instance = node.getInstance();
        if (instance != null)
            attributes.put("stable_id", instance.getAttributeValue(ReactomeJavaConstants.stableIdentifier) + "");

	    hyperNode.setAttributes(attributes);
        return hyperNode;
    }

    private Properties loadProperties(String filename) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filename)) {
            properties.load(fis);
        }
        return properties;
    }

    @Test
    public void testCreateHyperGraph() throws Exception {
        Long diagramDbId = 5675705L;
        Properties properties = loadProperties("resources/converter.prop");
        String host = properties.getProperty("host");
        String name = properties.getProperty("name");
        String user = properties.getProperty("user");
        String pass = properties.getProperty("pass");
        MySQLAdaptor dba = new MySQLAdaptor(host, name, user, pass);

        GKInstance diagram = dba.fetchInstance(diagramDbId);
        HyperGraph graph = createHyperGraph(diagram);
        System.out.println("Edges:");
        graph.printEdges();
        System.out.println("Nodes:");
        graph.printNodes();
    }

}