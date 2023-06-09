package utils;

/**
 * Representacao de um item a ser utilizado dentro de um grafo
 */

public class ItemGrafo {
    private String rotulo;
    private int peso;
    private boolean visitado;
    private boolean descoberto;
    private int tempoDescoberta;
    private int low;

    public ItemGrafo() {
        this.rotulo = String.valueOf(this.hashCode());
        this.peso = 1;
        this.visitado = false;
        this.descoberto = false;
        this.tempoDescoberta = 0;
        this.low = 0;
    }

    public ItemGrafo(String rotulo) {
        this.rotulo = rotulo;
        this.peso = 1;
        this.visitado = false;
        this.descoberto = false;
        this.tempoDescoberta = 0;
        this.low = 0;
    }

    public ItemGrafo(String rotulo, int peso) {
        this.rotulo = rotulo;
        this.peso = peso;
        this.visitado = false;
        this.descoberto = false;
        this.tempoDescoberta = 0;
        this.low = 0;
    }

    /**
     * Retorna o rotulo
     * 
     * @return String
     */
    public String getRotulo() {
        return this.rotulo;
    }

    /**
     * Retorna o peso
     * 
     * @return int
     */
    public int getPeso() {
        return this.peso;
    }

    /**
     * Retorna se o item esta visitado
     * 
     * @return boolean
     */
    public boolean foiVisitado() {
        return this.visitado == true;
    }

    /**
     * Retorna se o item esta descoberto
     * 
     * @return boolean
     */
    public boolean foiDescoberto() {
        return this.descoberto == true;
    }

    /**
     * Retorna se o item nao esta visitado
     * 
     * @return boolean
     */
    public boolean naoFoiVisitado() {
        return this.visitado != true;
    }

    /**
     * Retorna se o item nao esta descoberto
     * 
     * @return boolean
     */
    public boolean naoFoiDescoberto() {
        return this.descoberto != true;
    }

    /**
     * Sinaliza o item como visitado
     * 
     * @return void
     */
    public void visita() {
        this.visitado = true;
    }

    /**
     * Sinaliza o item como descoberto
     * 
     * @return void
     */
    public void descobre() {
        this.descoberto = true;
    }

    /**
     * Marca o item como nao visitado
     * 
     * @return void
     */
    public void desfazVisita() {
        this.visitado = false;
    }

    /**
     * Marca o item como nao descoberto
     * 
     * @return void
     */
    public void desfazDescoberta() {
        this.descoberto = false;
    }

    /**
     * Redefine o tempo de desoberta do item
     */
    public void redefineTempoDescoberta() {
        this.tempoDescoberta = 0;
    }

    /**
     * Busca o tempo de descoberta do item
     * 
     * @return int
     */
    public int getTempoDescoberta() {
        return this.tempoDescoberta;
    }

    /**
     * Define o tempo de descoberta do item
     * 
     * @param disc
     */
    public void setTempoDescoberta(int disc) {
        this.tempoDescoberta = disc;
    }

    /**
     * Redefine a propriedade low do item
     */
    public void redefineLow() {
        this.low = 0;
    }

    /**
     * Define a propriedade low do item
     * 
     * @param l
     */
    public void setLow(int l) {
        this.low = l;
    }

    /**
     * Busca o valor da propriedade low
     * 
     * @return int
     */
    public int getLow() {
        return this.low;
    }

    /**
     * Sobrescreve a comaparacao de iguais realizando a comparacao entre os rotulos
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        ItemGrafo v = (ItemGrafo) o;
        if (v == null) {
            return false;
        }
        return this.rotulo.equals(v.getRotulo());
    }

    /**
     * Sobrescreve a geracao de uma string do objeto
     * 
     * @return String
     */
    @Override
    public String toString() {
        return this.rotulo;
    }
}
