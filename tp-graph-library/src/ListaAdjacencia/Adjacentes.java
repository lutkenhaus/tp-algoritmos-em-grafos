package ListaAdjacencia;

import java.util.LinkedList;

import Exception.VerticeJaAdicionadoComoAdjacente;
import utils.*;

/**
 * Representação de Item de uma lista de Vertices adjacentes a um determinado
 * Vertice
 */

public class Adjacentes {
    private Vertice vertice;
    private LinkedList<Vertice> verticesAdjacentes;

    public Adjacentes(Vertice v) {
        this.vertice = v;
        this.verticesAdjacentes = new LinkedList<>();
    }

    /**
     * Adiciona um vertice a lista de adjacentes
     * 
     * @param v
     */
    public void adicionarVertice(Vertice v) throws VerticeJaAdicionadoComoAdjacente {
        if (this.verticesAdjacentes.contains(v)) {
            throw new VerticeJaAdicionadoComoAdjacente();
        }
        this.verticesAdjacentes.add(v);
    }

    /**
     * Remove um vertice da lista de adjacentes
     * 
     * @param v
     */
    public void removerVertice(Vertice v) {
        this.verticesAdjacentes.remove(v);
    }

    /**
     * Compara se a lista e do vertice v
     * 
     * @param v
     * @return boolean
     */
    public boolean ehDoVertice(Vertice v) {
        return this.vertice.equals(v);
    }

    /**
     * Retorna os vertices adjacentes
     * 
     * @return LinkedList<Vertice2>
     */
    public LinkedList<Vertice> getVertices() {
        return this.verticesAdjacentes;
    }

    /**
     * Verifica se o vertice possui adjacentes
     * 
     * @return boolean
     */
    public boolean possuiVerticesAdjacentes() {
        return !this.verticesAdjacentes.isEmpty();
    }

    /**
     * Retorna a qual vertice os demais sao adjacentes
     * 
     * @return Vertice
     */
    public Vertice getVertice() {
        return this.vertice;
    }

    /**
     * Chace se um determinado vertice pertence a lista de adjacencia
     * 
     * @param v
     * @return boolean
     */
    public boolean existeAdjacencia(Vertice v) {
        return this.verticesAdjacentes
                .stream()
                .anyMatch((vertice) -> vertice.equals(v));
    }

    /**
     * Sobrescreve a comaparacao de iguais realizando a comparacao entre as listas
     * de adjacencia
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        Adjacentes i = (Adjacentes) o;
        if (i == null) {
            return false;
        }
        return this.vertice.equals(i.getVertice()) && this.verticesAdjacentes.equals(i.getVertices());
    }
}
