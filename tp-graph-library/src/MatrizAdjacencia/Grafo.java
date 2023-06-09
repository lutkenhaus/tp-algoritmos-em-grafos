package MatrizAdjacencia;

import java.util.LinkedList;
import java.util.stream.Collectors;

import Exception.VerticeNaoEncontradoException;
import utils.Aresta;

/**
 * Representação de grafos utilizando Matriz de Adjacência
 */

public class Grafo implements utils.Grafo {
    private LinkedList<Vertice> vertices;
    private int[][] matrizAdjacencia;

    public Grafo(utils.Vertice... vertices) {
        this.vertices = new LinkedList<>();
        for (utils.Vertice vertice : vertices) {
            this.vertices.add(new Vertice(vertice.getRotulo(), vertice.getPeso(), this.vertices.size()));
        }
        this.matrizAdjacencia = new int[this.vertices.size()][this.vertices.size()];
        this.inicializaElementosMatrizAdjacencia();
    }

    /**
     * Adiciona uma aresta entre um vertice convergente e um divergente considerando
     * que o divergente existe no grafo
     * 
     * @param v1
     * @param v2
     * @throws VerticeNaoEncontradoException
     */
    public void adicionarAresta(utils.Vertice v1, utils.Vertice v2)
            throws VerticeNaoEncontradoException {
        Vertice vm1 = this.buscaVertice(v1);
        Vertice vm2 = this.buscaVertice(v2);
        this.matrizAdjacencia[vm1.getPosicaoMatriz()][vm2.getPosicaoMatriz()] = 1;
        this.matrizAdjacencia[vm2.getPosicaoMatriz()][vm1.getPosicaoMatriz()] = 1;
    }

    /**
     * Remove uma aresta entre um vertice convergente e um divergente considerando
     * que o divergente existe no grafo
     * 
     * @param v1
     * @param v2
     * @throws VerticeNaoEncontradoException
     */
    public void removerAresta(utils.Vertice v1, utils.Vertice v2) throws VerticeNaoEncontradoException {
        Vertice vm1 = this.buscaVertice(v1);
        Vertice vm2 = this.buscaVertice(v2);
        this.matrizAdjacencia[vm1.getPosicaoMatriz()][vm2.getPosicaoMatriz()] = 0;
        this.matrizAdjacencia[vm2.getPosicaoMatriz()][vm1.getPosicaoMatriz()] = 0;
    }

    /**
     * Obtem uma lista dos vertices que existem no grafo
     * 
     * @return LinkedList<Vertice>
     */
    public LinkedList<utils.Vertice> getVertices() {
        return this.vertices
                .stream()
                .map((vertice) -> vertice.toVerticeOriginal())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Checa se dois vertices sao adjacentes
     * 
     * @param v1
     * @param v2
     * @return boolean
     */
    public boolean verticesSaoAdjacentes(utils.Vertice v1, utils.Vertice v2) throws VerticeNaoEncontradoException {
        Vertice vm1 = this.buscaVertice(v1);
        Vertice vm2 = this.buscaVertice(v2);
        return this.matrizAdjacencia[vm1.getPosicaoMatriz()][vm2.getPosicaoMatriz()] == 1;
    }

    /**
     * Checa se duas arestas sao adjacentes
     * 
     * @param vA1
     * @param vA2
     * @param vB1
     * @param vB2
     * @return boolean
     * @throws VerticeNaoEncontradoException
     */
    public boolean arestasSaoAdjacentes(utils.Vertice vA1, utils.Vertice vA2, utils.Vertice vB1,
            utils.Vertice vB2) throws VerticeNaoEncontradoException {
        Vertice vmA1 = this.buscaVertice(vA1);
        Vertice vmA2 = this.buscaVertice(vA2);
        Vertice vmB1 = this.buscaVertice(vB1);
        Vertice vmB2 = this.buscaVertice(vB2);

        return vmA1.equals(vmB1) || vmA2.equals(vmB1)
                || vmB1.equals(vmA2) || vmB2.equals(vmA1);
    }

    /**
     * Checa se existe uma aresta entre os dois vertices
     * 
     * @param v1
     * @param v2
     * @return boolean
     */
    public boolean existeAresta(utils.Vertice v1, utils.Vertice v2) {
        try {
            if (this.verticesSaoAdjacentes(v1, v2)) {
                return true;
            }

            return this.verticesSaoAdjacentes(v2, v1);
        } catch (VerticeNaoEncontradoException e) {
            return false;
        }
    }

    /**
     * Calcula a quantidade de vertices que existem no grafo
     * 
     * @return int
     */
    public int quantidadeVertices() {
        return this.vertices.size();
    }

    /**
     * Retorna a quantidade armazenada de arestas
     * 
     * @return int
     * @throws VerticeNaoEncontradoException
     */
    public int quantidadeArestas() throws VerticeNaoEncontradoException {
        return this.getArestas().size();
    }

    /**
     * Retorna uma lista com as arestas existentes no grafo
     * 
     * @return LinkedList<Aresta>
     * @throws VerticeNaoEncontradoException
     */
    public LinkedList<Aresta> getArestas() throws VerticeNaoEncontradoException {
        LinkedList<Aresta> arestas = new LinkedList<>();
        LinkedList<utils.Vertice> vertices = this.getVertices();
        for (utils.Vertice v : vertices) {
            v.desfazDescoberta();
        }
        for (int i = 0; i < this.matrizAdjacencia.length; i++) {
            Vertice vI = this.getVerticePorPosicaoMatriz(i);
            vI.descobre();
            for (int j = 0; j < this.matrizAdjacencia[i].length; j++) {
                Vertice vJ = this.getVerticePorPosicaoMatriz(j);
                if (this.matrizAdjacencia[i][j] == 1 && vJ.naoFoiDescoberto()) {
                    arestas.add(new Aresta(vI, vJ));
                }
            }
        }
        return arestas;
    }

    /**
     * Verifica se o grafo eh nulo. Um grafo nulo nao possui arestas
     * 
     * @return boolean
     */
    public boolean ehNulo() {
        for (int i = 0; i < this.matrizAdjacencia.length; i++) {
            for (int j = 0; j < this.matrizAdjacencia[i].length; j++) {
                if (this.matrizAdjacencia[i][j] == 1) {
                    return false;
                }
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
    public boolean ehCompleto() {
        for (int i = 0; i < this.matrizAdjacencia.length; i++) {
            for (int j = 0; j < this.matrizAdjacencia[i].length; j++) {
                if (i != j && this.matrizAdjacencia[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Busca dentre os vertices do grafo uma correspondencia com um vertice original
     * 
     * @param v
     * @return Vertice
     * @throws VerticeNaoEncontradoException
     */
    public Vertice buscaVertice(utils.Vertice v) throws VerticeNaoEncontradoException {
        return this.vertices
                .stream()
                .filter((vertice) -> vertice.equals(v))
                .findAny()
                .orElseThrow(VerticeNaoEncontradoException::new);
    }

    /**
     * Obtem detalhes sobre um vertice a partir de sua posicao na matriz
     * 
     * @param posicaoMatriz
     * @return Vertice
     * @throws VerticeNaoEncontradoException
     */
    public Vertice getVerticePorPosicaoMatriz(int posicaoMatriz) throws VerticeNaoEncontradoException {
        return this.vertices
                .stream()
                .filter((vertice) -> vertice.getPosicaoMatriz() == posicaoMatriz)
                .findAny()
                .orElseThrow(VerticeNaoEncontradoException::new);
    }

    /**
     * Obtem os vertices adjacentes a um determinado vertice
     * 
     * @param v
     * @return utils.Vertice
     * @throws VerticeNaoEncontradoException
     */
    public LinkedList<utils.Vertice> adjacentes(utils.Vertice v) throws VerticeNaoEncontradoException {
        Vertice vm = this.buscaVertice(v);
        LinkedList<Vertice> verticesAdjacentesAV1 = new LinkedList<>();
        for (int i = 0; i < this.matrizAdjacencia[vm.getPosicaoMatriz()].length; i++) {
            if (this.matrizAdjacencia[vm.getPosicaoMatriz()][i] == 1) {
                verticesAdjacentesAV1.push(this.getVerticePorPosicaoMatriz(i));
            }
        }
        return verticesAdjacentesAV1
                .stream()
                .map((vertice) -> vertice.toVerticeOriginal())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Desfaz sinalizacoes realizadas nos vertices em processos anteriores
     */
    public void desfazSinalizacoesVertices() {
        for (Vertice v : this.vertices) {
            v.desfazDescoberta();
            v.desfazVisita();
        }
    }

    /**
     * Inicializa as relacoes de adjacencia da matriz com valores zerados
     */
    private void inicializaElementosMatrizAdjacencia() {
        for (int i = 0; i < this.matrizAdjacencia.length; i++) {
            for (int j = 0; j < this.matrizAdjacencia[i].length; j++) {
                this.matrizAdjacencia[i][j] = 0;
            }
        }
    }
}
