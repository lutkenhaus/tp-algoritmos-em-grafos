package MatrizAdjacencia;

/**
 * Representacao de um vertice de uma matriz de adjacencia. Possui uma indicacao
 * extra da posicao do mesmo na estrutura de dados
 */

public class Vertice extends utils.Vertice {
    private int posicaoMatriz;

    public Vertice(String rotulo) {
        super(rotulo);
        this.posicaoMatriz = 0;
    }

    public Vertice(String rotulo, int peso) {
        super(rotulo, peso);
        this.posicaoMatriz = 0;
    }

    public Vertice(String rotulo, int peso, int posicaoMatriz) {
        super(rotulo, peso);
        this.posicaoMatriz = posicaoMatriz;
    }

    /**
     * Retorna a posicao do vertice na estrutura de dados
     * 
     * @return int
     */
    public int getPosicaoMatriz() {
        return this.posicaoMatriz;
    }

    /**
     * Retorna um equivalente do vertice em sua forma original
     * 
     * @return utils.Vertice
     */
    public utils.Vertice toVerticeOriginal() {
        return (utils.Vertice) this;
    }
}
