package utilsFolder;

/**
 * Representação de um vertice de um grafo
 */

public class Node extends GraphItem {
    public Node(String label) {
        super(label);
    }

    public Node(String label, int weight) {
        super(label, weight);
    }
}
