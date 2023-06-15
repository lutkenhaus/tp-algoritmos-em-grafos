package AdjacencyList;

import java.util.LinkedList;

import Exception.NodeAlreadyAddedAsAdjacentException;
import utilsFolder.*;

/**
 * Representação de Item de uma lista de Vertices adjacentes a um determinado
 * Vertice
 */

public class Adjacents {
    private Node node;
    private LinkedList<Node> adjacentNodes;

    public Adjacents(Node v) {
        this.node = v;
        this.adjacentNodes = new LinkedList<>();
    }

    /**
     * Adiciona um vertice a lista de adjacentes
     * 
     * @param v
     */
    public void addNode(Node v) throws NodeAlreadyAddedAsAdjacentException {
        if (this.adjacentNodes.contains(v)) {
            throw new NodeAlreadyAddedAsAdjacentException();
        }
        this.adjacentNodes.add(v);
    }

    /**
     * Remove um vertice da lista de adjacentes
     * 
     * @param v
     */
    public void removeNode(Node v) {
        this.adjacentNodes.remove(v);
    }

    /**
     * Compara se a lista e do vertice v
     * 
     * @param v
     * @return boolean
     */
    public boolean isItFromNode(Node v) {
        return this.node.equals(v);
    }

    /**
     * Retorna os vertices adjacentes
     * 
     * @return LinkedList<Vertice2>
     */
    public LinkedList<Node> getNodes() {
        return this.adjacentNodes;
    }

    /**
     * Verifica se o vertice possui adjacentes
     * 
     * @return boolean
     */
    public boolean haveAdjacentNodes() {
        return !this.adjacentNodes.isEmpty();
    }

    /**
     * Retorna a qual vertice os demais sao adjacentes
     * 
     * @return Node
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * Chace se um determinado vertice pertence a lista de adjacencia
     * 
     * @param v
     * @return boolean
     */
    public boolean isThereAdjacency(Node v) {
        return this.adjacentNodes
                .stream()
                .anyMatch((node) -> node.equals(v));
    }

    /**
     * Sobrescreve a comaparacao de iguais realizando a comparacao entre as listas
     * de adjacencia
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        Adjacents i = (Adjacents) o;
        if (i == null) {
            return false;
        }
        return this.node.equals(i.getNode()) && this.adjacentNodes.equals(i.getNodes());
    }
}
