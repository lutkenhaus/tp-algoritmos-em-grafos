package utils;

/**
 * Representacao de uma aresta de um determinado grafo
 */

public class Aresta extends ItemGrafo {
    private Vertice v1;
    private Vertice v2;

    public Aresta(Vertice v1, Vertice v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Aresta(Vertice v1, Vertice v2, String rotulo) {
        super(rotulo);
        this.v1 = v1;
        this.v2 = v2;
    }

    public Aresta(Vertice v1, Vertice v2, String rotulo, int peso) {
        super(rotulo, peso);
        this.v1 = v1;
        this.v2 = v2;
    }

    /**
     * Obtem o primeiro vertice da aresta
     * 
     * @return Vertice
     */
    public Vertice getVertice1() {
        return this.v1;
    }

    /**
     * Obtem o segundo vertice da aresta
     * 
     * @return Vertice
     */
    public Vertice getVertice2() {
        return this.v2;
    }

    /**
     * Sobrescreve a comaparacao de iguais realizando a comparacao entre os rotulos
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        Aresta a = (Aresta) o;
        if (a == null) {
            return false;
        }
        return this.v1.equals(a.getVertice1()) && this.v2.equals(a.getVertice2());
    }
}
