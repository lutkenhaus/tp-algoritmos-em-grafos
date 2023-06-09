package utils;

/**
 * Representação de um vertice de um grafo
 */

public class Vertice extends ItemGrafo {
    public Vertice(String rotulo) {
        super(rotulo);
    }

    public Vertice(String rotulo, int peso) {
        super(rotulo, peso);
    }
}
