package utilsFolder;

/**
 * Representacao de uma aresta de um determinado grafo
 */

public class Edge extends GraphItem {
    private Node v1;
    private Node v2;

    public Edge(Node v1, Node v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(Node v1, Node v2, String rotulo) {
        super(rotulo);
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(Node v1, Node v2, String rotulo, int peso) {
        super(rotulo, peso);
        this.v1 = v1;
        this.v2 = v2;
    }

    /**
     * Obtem o primeiro vertice da aresta
     * 
     * @return Node
     */
    public Node getVertice1() {
        return this.v1;
    }

    /**
     * Obtem o segundo vertice da aresta
     * 
     * @return Node
     */
    public Node getVertice2() {
        return this.v2;
    }

    /**
     * Sobrescreve a comaparacao de iguais realizando a comparacao entre os rotulos
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        Edge a = (Edge) o;
        if (a == null) {
            return false;
        }
        return this.v1.equals(a.getVertice1()) && this.v2.equals(a.getVertice2());
    }
}
