package AdjacencyList;

import java.util.LinkedList;
import java.util.stream.Collectors;

import Exception.NodeAlreadyAddedAsAdjacentException;
import Exception.NodeAlreadyExistsException;
import Exception.NodeNotFoundException;
import utilsFolder.*;

/**
 * Representação de grafos utilizando Lista de Adjacência
 */

public class Graph implements utilsFolder.Graph {
    private LinkedList<Adjacents> adjacentList;
    private LinkedList<Edge> edges;

    public Graph() {
        this.adjacentList = new LinkedList<>();
    }

    public Graph(Node... nodes) throws NodeAlreadyExistsException {
        this.adjacentList = new LinkedList<>();
        for (Node node : nodes) {
            this.createsExceptionIfNodeExists(node);
            this.adjacentList.add(new Adjacents(node));
        }
    }

    /**
     * Adiciona um vertice ao grafo
     * 
     * @param v
     * @throws NodeAlreadyExistsException
     */
    public void addNode(Node v) throws NodeAlreadyExistsException {
        this.createsExceptionIfNodeExists(v);
        Adjacents newItem = new Adjacents(v);
        this.adjacentList.add(newItem);
    }

    /**
     * Remove um vertice do grafo
     * 
     * @param v
     * @throws NodeNotFoundException
     */
    public void removeNode(Node v) throws NodeNotFoundException {
        this.createsExceptionIfNodeDoesntExists(v);
        Adjacents adjacentNodes = this.searchAdjacentList(v);
        this.adjacentList.remove(adjacentNodes);
    }

    /**
     * Adiciona uma aresta entre um vertice convergente e um divergente considerando
     * que o divergente existe no grafo
     * 
     * @param v1
     * @param v2
     * @throws NodeNotFoundException
     */
    public void addEdge(Node v1, Node v2)
            throws NodeNotFoundException, NodeAlreadyAddedAsAdjacentException, NodeAlreadyExistsException {
        this.createsExceptionIfNodeDoesntExists(v1);
        this.createsExceptionIfNodeDoesntExists(v2);

        Adjacents adjacentNodesAN1 = this.searchAdjacentList(v1);
        adjacentNodesAN1.addNode(v2);

        Adjacents adjacentNodesAN2 = this.searchAdjacentList(v2);
        adjacentNodesAN2.addNode(v1);
    }

    /**
     * Remove uma aresta entre um vertice convergente e um divergente considerando
     * que o divergente existe no grafo
     * 
     * @param v1
     * @param v2
     * @throws NodeNotFoundException
     */
    public void removeEdge(Node v1, Node v2) throws NodeNotFoundException {
        this.createsExceptionIfNodeDoesntExists(v1);
        this.createsExceptionIfNodeDoesntExists(v2);

        Adjacents adjacentNodesAN1 = this.searchAdjacentList(v1);
        adjacentNodesAN1.removeNode(v2);

        Adjacents adjacentNodesAN2 = this.searchAdjacentList(v2);
        adjacentNodesAN2.removeNode(v1);
    }

    /**
     * Obtem uma lista dos vertices que existem no grafo
     * 
     * @return LinkedList<Node>
     */
    public LinkedList<Node> getNodes() {
        return this.adjacentList
                .stream()
                .map(Adjacents::getNode)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Busca a lista de adjacencia de um vertice do grafo. Caso nao exista nulo e
     * retornado
     * 
     * @param v
     * @return Adjacents
     */
    public Adjacents searchAdjacentList(Node v) throws NodeNotFoundException {
        return this.adjacentList
                .stream()
                .filter((item) -> item.isItFromNode(v))
                .findAny()
                .orElseThrow(NodeNotFoundException::new);
    }

    /**
     * Busca a lista de adjacencia de um vertice do grafo. Caso nao exista nulo e
     * retornado
     * 
     * @param v
     * @return LinkedList<Node>
     */
    public LinkedList<utilsFolder.Node> adjacentNodes(Node v) throws NodeNotFoundException {
        return this.searchAdjacentList(v).getNodes();
    }

    /**
     * Checa se dois vertices sao adjacents
     * 
     * @param v1
     * @param v2
     * @return boolean
     */
    public boolean isAdjacentNodes(Node v1, Node v2) {
        try {
            this.createsExceptionIfNodeDoesntExists(v1);
            this.createsExceptionIfNodeDoesntExists(v2);

            Adjacents adjacentNodesAN1 = this.searchAdjacentList(v1);
            return adjacentNodesAN1.isThereAdjacency(v2);
        } catch (NodeNotFoundException e) {
            return false;
        }
    }

    /**
     * Checa se duas arestas sao adjacents
     * 
     * @param vA1
     * @param vA2
     * @param vB1
     * @param vB2
     * @return boolean
     * @throws NodeNotFoundException
     */
    public boolean isAdjacentEdges(Node vA1, Node vA2, Node vB1,
            Node vB2) throws NodeNotFoundException {
        this.createsExceptionIfNodeDoesntExists(vA1);
        this.createsExceptionIfNodeDoesntExists(vA2);
        this.createsExceptionIfNodeDoesntExists(vB1);
        this.createsExceptionIfNodeDoesntExists(vB2);

        return vA1.equals(vB1) || vA2.equals(vB1)
                || vB1.equals(vA2) || vB2.equals(vA1);
    }

    /**
     * Checa se existe uma aresta entre os dois vertices
     * 
     * @param v1
     * @param v2
     * @return boolean
     */
    public boolean edgeExists(Node v1, Node v2) {
        try {
            this.createsExceptionIfNodeDoesntExists(v1);
            this.createsExceptionIfNodeDoesntExists(v2);

            Adjacents adjacentNodesAN1 = this.searchAdjacentList(v1);
            if (adjacentNodesAN1.isThereAdjacency(v2)) {
                return true;
            }

            Adjacents adjacentNodesAN2 = this.searchAdjacentList(v2);
            return adjacentNodesAN2.isThereAdjacency(v1);
        } catch (NodeNotFoundException e) {
            return false;
        }
    }

    /**
     * Calcula a quantidade de vertices que existem no grafo
     * 
     * @return int
     */
    public int nodeAmount() {
        return this.adjacentList.size();
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
        this.edges = new LinkedList<>();
        LinkedList<Node> nodes = this.getNodes();
        this.undoNodeFlags();

        // Realiza busca em largura para identificacao das arestas
        for (Node v : nodes) {
            if (v.wasNotVisited()) {
                setAdjacentEdges(v);
            }
        }
        return this.edges;
    }

    /**
     * Percorre os vertices adjacents a um vertice do grafo granvando-os em uma
     * lista
     * 
     * @param v
     * @throws NodeNotFoundException
     */
    public void setAdjacentEdges(Node v) throws NodeNotFoundException {
        v.discover();
        for (Node adjacent : this.adjacentNodes(v)) {
            if (adjacent.wasNotDiscovered() || (v.wasNotVisited() && adjacent.wasNotVisited())) {
                this.edges.add(new Edge(v, adjacent));
                adjacent.discover();
            }
        }
        v.visit();
        return;
    }

    /**
     * Verifica se o grafo eh nulo. Um grafo nulo nao possui arestas
     * 
     * @return boolean
     */
    public boolean isNull() {
        for (Adjacents adjacents : this.adjacentList) {
            if (adjacents.haveAdjacentNodes()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se o grafo eh completo. Em um grafo completo um vertice esta
     * conectado a todos os demais
     * 
     * @return boolean
     */
    public boolean isComplete() {
        LinkedList<Node> nodes = this.getNodes();
        for (Adjacents adjacents : this.adjacentList) {
            for (Node node : nodes) {
                if (!adjacents.isItFromNode(node) && !adjacents.isThereAdjacency(node)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Levanta uma excecao se o vertice em questao nao existir no grafo
     * 
     * @param v
     * @throws NodeNotFoundException
     */
    public void createsExceptionIfNodeDoesntExists(Node v) throws NodeNotFoundException {
        for (Adjacents adjacents : this.adjacentList) {
            if (adjacents.getNode().equals(v)) {
                return;
            }
        }
        throw new NodeNotFoundException();
    }

    /**
     * Levanta uma excecao se o vertice em questao nao existir no grafo
     * 
     * @param v
     * @throws NodeAlreadyExistsException
     */
    public void createsExceptionIfNodeExists(Node v) throws NodeAlreadyExistsException {
        for (Adjacents adjacents : this.adjacentList) {
            if (adjacents.getNode().equals(v)) {
                throw new NodeAlreadyExistsException();
            }
        }
    }

    /**
     * Desfaz sinalizacoes realizadas nos vertices em processos anteriores
     */
    public void undoNodeFlags() {
        for (Node v : this.getNodes()) {
            v.undoDiscovery();
            v.undoVisit();
        }
    }
}
