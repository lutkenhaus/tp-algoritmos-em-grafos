package ListaAdjacencia;

import java.util.LinkedList;
import java.util.stream.Collectors;

import Exception.VerticeJaAdicionadoComoAdjacente;
import Exception.VerticeJaExisteException;
import Exception.VerticeNaoEncontradoException;
import utils.*;

/**
 * Representação de grafos utilizando Lista de Adjacência
 */

public class Grafo implements utils.Grafo {
    private LinkedList<Adjacentes> listaAdjacentes;
    private LinkedList<Aresta> arestas;

    public Grafo() {
        this.listaAdjacentes = new LinkedList<>();
    }

    public Grafo(Vertice... vertices) throws VerticeJaExisteException {
        this.listaAdjacentes = new LinkedList<>();
        for (Vertice vertice : vertices) {
            this.criaExcecaoSeVerticeJaExistir(vertice);
            this.listaAdjacentes.add(new Adjacentes(vertice));
        }
    }

    /**
     * Adiciona um vertice ao grafo
     * 
     * @param v
     * @throws VerticeJaExisteException
     */
    public void adicionarVertice(Vertice v) throws VerticeJaExisteException {
        this.criaExcecaoSeVerticeJaExistir(v);
        Adjacentes novoItem = new Adjacentes(v);
        this.listaAdjacentes.add(novoItem);
    }

    /**
     * Remove um vertice do grafo
     * 
     * @param v
     * @throws VerticeNaoEncontradoException
     */
    public void removerVertice(Vertice v) throws VerticeNaoEncontradoException {
        this.criaExcecaoSeVerticeNaoExistir(v);
        Adjacentes verticesAdjacentes = this.buscaListaAdjacentes(v);
        this.listaAdjacentes.remove(verticesAdjacentes);
    }

    /**
     * Adiciona uma aresta entre um vertice convergente e um divergente considerando
     * que o divergente existe no grafo
     * 
     * @param v1
     * @param v2
     * @throws VerticeNaoEncontradoException
     */
    public void adicionarAresta(Vertice v1, Vertice v2)
            throws VerticeNaoEncontradoException, VerticeJaAdicionadoComoAdjacente, VerticeJaExisteException {
        this.criaExcecaoSeVerticeNaoExistir(v1);
        this.criaExcecaoSeVerticeNaoExistir(v2);

        Adjacentes verticesAdjacentesAV1 = this.buscaListaAdjacentes(v1);
        verticesAdjacentesAV1.adicionarVertice(v2);

        Adjacentes verticesAdjacentesAV2 = this.buscaListaAdjacentes(v2);
        verticesAdjacentesAV2.adicionarVertice(v1);
    }

    /**
     * Remove uma aresta entre um vertice convergente e um divergente considerando
     * que o divergente existe no grafo
     * 
     * @param v1
     * @param v2
     * @throws VerticeNaoEncontradoException
     */
    public void removerAresta(Vertice v1, Vertice v2) throws VerticeNaoEncontradoException {
        this.criaExcecaoSeVerticeNaoExistir(v1);
        this.criaExcecaoSeVerticeNaoExistir(v2);

        Adjacentes verticesAdjacentesAV1 = this.buscaListaAdjacentes(v1);
        verticesAdjacentesAV1.removerVertice(v2);

        Adjacentes verticesAdjacentesAV2 = this.buscaListaAdjacentes(v2);
        verticesAdjacentesAV2.removerVertice(v1);
    }

    /**
     * Obtem uma lista dos vertices que existem no grafo
     * 
     * @return LinkedList<Vertice>
     */
    public LinkedList<Vertice> getVertices() {
        return this.listaAdjacentes
                .stream()
                .map(Adjacentes::getVertice)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Busca a lista de adjacencia de um vertice do grafo. Caso nao exista nulo e
     * retornado
     * 
     * @param v
     * @return Adjacentes
     */
    public Adjacentes buscaListaAdjacentes(Vertice v) throws VerticeNaoEncontradoException {
        return this.listaAdjacentes
                .stream()
                .filter((item) -> item.ehDoVertice(v))
                .findAny()
                .orElseThrow(VerticeNaoEncontradoException::new);
    }

    /**
     * Busca a lista de adjacencia de um vertice do grafo. Caso nao exista nulo e
     * retornado
     * 
     * @param v
     * @return LinkedList<Vertice>
     */
    public LinkedList<utils.Vertice> adjacentes(Vertice v) throws VerticeNaoEncontradoException {
        return this.buscaListaAdjacentes(v).getVertices();
    }

    /**
     * Checa se dois vertices sao adjacentes
     * 
     * @param v1
     * @param v2
     * @return boolean
     */
    public boolean verticesSaoAdjacentes(Vertice v1, Vertice v2) {
        try {
            this.criaExcecaoSeVerticeNaoExistir(v1);
            this.criaExcecaoSeVerticeNaoExistir(v2);

            Adjacentes adjacentesAV1 = this.buscaListaAdjacentes(v1);
            return adjacentesAV1.existeAdjacencia(v2);
        } catch (VerticeNaoEncontradoException e) {
            return false;
        }
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
    public boolean arestasSaoAdjacentes(Vertice vA1, Vertice vA2, Vertice vB1,
            Vertice vB2) throws VerticeNaoEncontradoException {
        this.criaExcecaoSeVerticeNaoExistir(vA1);
        this.criaExcecaoSeVerticeNaoExistir(vA2);
        this.criaExcecaoSeVerticeNaoExistir(vB1);
        this.criaExcecaoSeVerticeNaoExistir(vB2);

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
    public boolean existeAresta(Vertice v1, Vertice v2) {
        try {
            this.criaExcecaoSeVerticeNaoExistir(v1);
            this.criaExcecaoSeVerticeNaoExistir(v2);

            Adjacentes adjacentesAV1 = this.buscaListaAdjacentes(v1);
            if (adjacentesAV1.existeAdjacencia(v2)) {
                return true;
            }

            Adjacentes adjacentesAV2 = this.buscaListaAdjacentes(v2);
            return adjacentesAV2.existeAdjacencia(v1);
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
        return this.listaAdjacentes.size();
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
        this.arestas = new LinkedList<>();
        LinkedList<Vertice> vertices = this.getVertices();
        this.desfazSinalizacoesVertices();

        // Realiza busca em largura para identificacao das arestas
        for (Vertice v : vertices) {
            if (v.naoFoiVisitado()) {
                setArestasAdjacentes(v);
            }
        }
        return this.arestas;
    }

    /**
     * Percorre os vertices adjacentes a um vertice do grafo granvando-os em uma
     * lista
     * 
     * @param v
     * @throws VerticeNaoEncontradoException
     */
    public void setArestasAdjacentes(Vertice v) throws VerticeNaoEncontradoException {
        v.descobre();
        for (Vertice adjacente : this.adjacentes(v)) {
            if (adjacente.naoFoiDescoberto() || (v.naoFoiVisitado() && adjacente.naoFoiVisitado())) {
                this.arestas.add(new Aresta(v, adjacente));
                adjacente.descobre();
            }
        }
        v.visita();
        return;
    }

    /**
     * Verifica se o grafo eh nulo. Um grafo nulo nao possui arestas
     * 
     * @return boolean
     */
    public boolean ehNulo() {
        for (Adjacentes adjacentes : this.listaAdjacentes) {
            if (adjacentes.possuiVerticesAdjacentes()) {
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
    public boolean ehCompleto() {
        LinkedList<Vertice> vertices = this.getVertices();
        for (Adjacentes adjacentes : this.listaAdjacentes) {
            for (Vertice vertice : vertices) {
                if (!adjacentes.ehDoVertice(vertice) && !adjacentes.existeAdjacencia(vertice)) {
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
     * @throws VerticeNaoEncontradoException
     */
    public void criaExcecaoSeVerticeNaoExistir(Vertice v) throws VerticeNaoEncontradoException {
        for (Adjacentes adjacentes : this.listaAdjacentes) {
            if (adjacentes.getVertice().equals(v)) {
                return;
            }
        }
        throw new VerticeNaoEncontradoException();
    }

    /**
     * Levanta uma excecao se o vertice em questao nao existir no grafo
     * 
     * @param v
     * @throws VerticeJaExisteException
     */
    public void criaExcecaoSeVerticeJaExistir(Vertice v) throws VerticeJaExisteException {
        for (Adjacentes adjacentes : this.listaAdjacentes) {
            if (adjacentes.getVertice().equals(v)) {
                throw new VerticeJaExisteException();
            }
        }
    }

    /**
     * Desfaz sinalizacoes realizadas nos vertices em processos anteriores
     */
    public void desfazSinalizacoesVertices() {
        for (Vertice v : this.getVertices()) {
            v.desfazDescoberta();
            v.desfazVisita();
        }
    }
}
