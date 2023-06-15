package AdjacencyMatrix;

/**
 * Representacao de um vertice de uma matriz de adjacencia. Possui uma indicacao
 * extra da posicao do mesmo na estrutura de dados
 */

public class Node extends utilsFolder.Node {
    private int matrixPosition;

    public Node(String label) {
        super(label);
        this.matrixPosition = 0;
    }

    public Node(String label, int weight) {
        super(label, weight);
        this.matrixPosition = 0;
    }

    public Node(String label, int weight, int matrixPosition) {
        super(label, weight);
        this.matrixPosition = matrixPosition;
    }

    /**
     * Retorna a posicao do vertice na estrutura de dados
     * 
     * @return int
     */
    public int getMatrixPosition() {
        return this.matrixPosition;
    }

    /**
     * Retorna um equivalente do vertice em sua forma original
     * 
     * @return utilsFolder.Node
     */
    public utilsFolder.Node toOriginalNode() {
        return (utilsFolder.Node) this;
    }
}
