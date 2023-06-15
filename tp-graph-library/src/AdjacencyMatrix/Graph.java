package AdjacencyMatrix;

import java.util.LinkedList;
import java.util.stream.Collectors;

import Exception.NodeNotFoundException;
import utilsFolder.Edge;

/**
 * Representação de grafos utilizando Matriz de Adjacência
 */

public class Graph implements utilsFolder.Graph {
    private LinkedList<Node> nodes;
    private int[][] adjacentMatrix;

    public Graph(utilsFolder.Node... nodes) {
        this.nodes = new LinkedList<>();
        for (utilsFolder.Node node : nodes) {
            this.nodes.add(new Node(node.getLabel(), node.getWeight(), this.nodes.size()));
        }
        this.adjacentMatrix = new int[this.nodes.size()][this.nodes.size()];
        this.initializeElementsAdjacentMatrix();
    }

    /**
     * Adiciona uma aresta entre um vertice convergente e um divergente considerando
     * que o divergente existe no grafo
     * 
     * @param v1
     * @param v2
     * @throws NodeNotFoundException
     */
    public void addEdge(utilsFolder.Node v1, utilsFolder.Node v2)
            throws NodeNotFoundException {
        Node vm1 = this.searchNode(v1);
        Node vm2 = this.searchNode(v2);
        this.adjacentMatrix[vm1.getMatrixPosition()][vm2.getMatrixPosition()] = 1;
        this.adjacentMatrix[vm2.getMatrixPosition()][vm1.getMatrixPosition()] = 1;
    }

    /**
     * Remove uma aresta entre um vertice convergente e um divergente considerando
     * que o divergente existe no grafo
     * 
     * @param v1
     * @param v2
     * @throws NodeNotFoundException
     */
    public void removeEdge(utilsFolder.Node v1, utilsFolder.Node v2) throws NodeNotFoundException {
        Node vm1 = this.searchNode(v1);
        Node vm2 = this.searchNode(v2);
        this.adjacentMatrix[vm1.getMatrixPosition()][vm2.getMatrixPosition()] = 0;
        this.adjacentMatrix[vm2.getMatrixPosition()][vm1.getMatrixPosition()] = 0;
    }

    /**
     * Obtem uma lista dos vertices que existem no grafo
     * 
     * @return LinkedList<Node>
     */
    public LinkedList<utilsFolder.Node> getNodes() {
        return this.nodes
                .stream()
                .map((node) -> node.toOriginalNode())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Checa se dois vertices sao adjacentes
     * 
     * @param v1
     * @param v2
     * @return boolean
     */
    public boolean isAdjacentNodes(utilsFolder.Node v1, utilsFolder.Node v2) throws NodeNotFoundException {
        Node vm1 = this.searchNode(v1);
        Node vm2 = this.searchNode(v2);
        return this.adjacentMatrix[vm1.getMatrixPosition()][vm2.getMatrixPosition()] == 1;
    }

    /**
     * Checa se duas arestas sao adjacentes
     * 
     * @param vA1
     * @param vA2
     * @param vB1
     * @param vB2
     * @return boolean
     * @throws NodeNotFoundException
     */
    public boolean isAdjacentEdges(utilsFolder.Node vA1, utilsFolder.Node vA2, utilsFolder.Node vB1,
            utilsFolder.Node vB2) throws NodeNotFoundException {
        Node vmA1 = this.searchNode(vA1);
        Node vmA2 = this.searchNode(vA2);
        Node vmB1 = this.searchNode(vB1);
        Node vmB2 = this.searchNode(vB2);

        return vmA1.equals(vmB1) || vmA2.equals(vmB1)
                || vmB1.equals(vmA2) || vmB2.equals(vmA1);
    }

    /**
     * Checa se existe uma aresta entre os dois nodes
     * 
     * @param v1
     * @param v2
     * @return boolean
     */
    public boolean edgeExists(utilsFolder.Node v1, utilsFolder.Node v2) {
        try {
            if (this.isAdjacentNodes(v1, v2)) {
                return true;
            }

            return this.isAdjacentNodes(v2, v1);
        } catch (NodeNotFoundException e) {
            return false;
        }
    }

    /**
     * Calcula a quantidade de nodes que existem no grafo
     * 
     * @return int
     */
    public int nodeAmount() {
        return this.nodes.size();
    }

    /**
     * Retorna a quantidade armazenada de arestas
     * 
     * @return int
     * @throws NodeNotFoundException
     */
    public int edgeAmount() throws NodeNotFoundException {
        return this.getEdges().size();
    }

    /**
     * Retorna uma lista com as arestas existentes no grafo
     * 
     * @return LinkedList<Aresta>
     * @throws NodeNotFoundException
     */
    public LinkedList<Edge> getEdges() throws NodeNotFoundException {
        LinkedList<Edge> edges = new LinkedList<>();
        LinkedList<utilsFolder.Node> nodes = this.getNodes();
        for (utilsFolder.Node v : nodes) {
            v.undoDiscovery();
        }
        for (int i = 0; i < this.adjacentMatrix.length; i++) {
            Node vI = this.getNodeByMatrixPosition(i);
            vI.discover();
            for (int j = 0; j < this.adjacentMatrix[i].length; j++) {
                Node vJ = this.getNodeByMatrixPosition(j);
                if (this.adjacentMatrix[i][j] == 1 && vJ.wasNotDiscovered()) {
                    edges.add(new Edge(vI, vJ));
                }
            }
        }
        return edges;
    }

    /**
     * Verifica se o grafo eh nulo. Um grafo nulo nao possui arestas
     * 
     * @return boolean
     */
    public boolean isNull() {
        for (int i = 0; i < this.adjacentMatrix.length; i++) {
            for (int j = 0; j < this.adjacentMatrix[i].length; j++) {
                if (this.adjacentMatrix[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifica se o grafo eh completo. Em um grafo completo um node esta
     * conectado a todos os demais
     * 
     * @return boolean
     */
    public boolean isComplete() {
        for (int i = 0; i < this.adjacentMatrix.length; i++) {
            for (int j = 0; j < this.adjacentMatrix[i].length; j++) {
                if (i != j && this.adjacentMatrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Busca dentre os nodes do grafo uma correspondencia com um node original
     * 
     * @param v
     * @return Node
     * @throws NodeNotFoundException
     */
    public Node searchNode(utilsFolder.Node v) throws NodeNotFoundException {
        return this.nodes
                .stream()
                .filter((node) -> node.equals(v))
                .findAny()
                .orElseThrow(NodeNotFoundException::new);
    }

    /**
     * Obtem detalhes sobre um node a partir de sua posicao na matriz
     * 
     * @param matrixPosition
     * @return Node
     * @throws NodeNotFoundException
     */
    public Node getNodeByMatrixPosition(int matrixPosition) throws NodeNotFoundException {
        return this.nodes
                .stream()
                .filter((node) -> node.getMatrixPosition() == matrixPosition)
                .findAny()
                .orElseThrow(NodeNotFoundException::new);
    }

    /**
     * Obtem os vertices adjacentes a um determinado vertice
     * 
     * @param v
     * @return utilsFolder.Node
     * @throws NodeNotFoundException
     */
    public LinkedList<utilsFolder.Node> adjacentNodes(utilsFolder.Node v) throws NodeNotFoundException {
        Node vm = this.searchNode(v);
        LinkedList<Node> adjacentNodesAN1 = new LinkedList<>();
        for (int i = 0; i < this.adjacentMatrix[vm.getMatrixPosition()].length; i++) {
            if (this.adjacentMatrix[vm.getMatrixPosition()][i] == 1) {
                adjacentNodesAN1.push(this.getNodeByMatrixPosition(i));
            }
        }
        return adjacentNodesAN1
                .stream()
                .map((node) -> node.toOriginalNode())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Desfaz sinalizacoes realizadas nos nodes em processos anteriores
     */
    public void undoNodeFlags() {
        for (Node v : this.nodes) {
            v.undoDiscovery();
            v.undoVisit();
        }
    }

    /**
     * Inicializa as relacoes de adjacencia da matriz com valores zerados
     */
    private void initializeElementsAdjacentMatrix() {
        for (int i = 0; i < this.adjacentMatrix.length; i++) {
            for (int j = 0; j < this.adjacentMatrix[i].length; j++) {
                this.adjacentMatrix[i][j] = 0;
            }
        }
    }
}
