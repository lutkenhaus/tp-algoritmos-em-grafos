package utilsFolder;

/**
 * Representacao de um item a ser utilizado dentro de um grafo
 */

public class GraphItem {
    private String label;
    private int weight;
    private boolean visited;
    private boolean discovered;
    private int discoveryTime;
    private int low;

    public GraphItem() {
        this.label = String.valueOf(this.hashCode());
        this.weight = 1;
        this.visited = false;
        this.discovered = false;
        this.discoveryTime = 0;
        this.low = 0;
    }

    public GraphItem(String label) {
        this.label = label;
        this.weight = 1;
        this.visited = false;
        this.discovered = false;
        this.discoveryTime = 0;
        this.low = 0;
    }

    public GraphItem(String label, int weight) {
        this.label = label;
        this.weight = weight;
        this.visited = false;
        this.discovered = false;
        this.discoveryTime = 0;
        this.low = 0;
    }

    /**
     * Retorna o label
     * 
     * @return String
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Retorna o weight
     * 
     * @return int
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Retorna se o item esta visited
     * 
     * @return boolean
     */
    public boolean wasVisited() {
        return this.visited == true;
    }

    /**
     * Retorna se o item esta discovered
     * 
     * @return boolean
     */
    public boolean wasDiscovered() {
        return this.discovered == true;
    }

    /**
     * Retorna se o item nao esta visited
     * 
     * @return boolean
     */
    public boolean wasNotVisited() {
        return this.visited != true;
    }

    /**
     * Retorna se o item nao esta discovered
     * 
     * @return boolean
     */
    public boolean wasNotDiscovered() {
        return this.discovered != true;
    }

    /**
     * Sinaliza o item como visited
     * 
     * @return void
     */
    public void visit() {
        this.visited = true;
    }

    /**
     * Sinaliza o item como discovered
     * 
     * @return void
     */
    public void discover() {
        this.discovered = true;
    }

    /**
     * Marca o item como nao visited
     * 
     * @return void
     */
    public void undoVisit() {
        this.visited = false;
    }

    /**
     * Marca o item como nao discovered
     * 
     * @return void
     */
    public void undoDiscovery() {
        this.discovered = false;
    }

    /**
     * Redefine o tempo de desoberta do item
     */
    public void defaultDiscoveryTime() {
        this.discoveryTime = 0;
    }

    /**
     * Busca o tempo de descoberta do item
     * 
     * @return int
     */
    public int getDiscoveryTime() {
        return this.discoveryTime;
    }

    /**
     * Define o tempo de descoberta do item
     * 
     * @param disc
     */
    public void setDiscoveryTime(int disc) {
        this.discoveryTime = disc;
    }

    /**
     * Redefine a propriedade low do item
     */
    public void defaultLow() {
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
     * Sobrescreve a comaparacao de iguais realizando a comparacao entre os labels
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        GraphItem v = (GraphItem) o;
        if (v == null) {
            return false;
        }
        return this.label.equals(v.getLabel());
    }

    /**
     * Sobrescreve a geracao de uma string do objeto
     * 
     * @return String
     */
    @Override
    public String toString() {
        return this.label;
    }
}
