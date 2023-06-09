package utils;

import java.util.LinkedList;

import Exception.VerticeJaAdicionadoComoAdjacente;
import Exception.VerticeJaExisteException;
import Exception.VerticeNaoEncontradoException;

public interface Grafo {
        public void adicionarAresta(utils.Vertice v1, utils.Vertice v2)
                        throws VerticeNaoEncontradoException, VerticeJaAdicionadoComoAdjacente,
                        VerticeJaExisteException;

        public void removerAresta(Vertice v1, Vertice v2) throws VerticeNaoEncontradoException;

        public boolean verticesSaoAdjacentes(Vertice v1, Vertice v2) throws VerticeNaoEncontradoException;

        public boolean arestasSaoAdjacentes(Vertice vA1, Vertice vA2, Vertice vB1, Vertice vB2)
                        throws VerticeNaoEncontradoException;

        public boolean existeAresta(Vertice v1, Vertice v2);

        public int quantidadeVertices() throws VerticeNaoEncontradoException;

        public int quantidadeArestas() throws VerticeNaoEncontradoException;

        public boolean ehNulo();

        public boolean ehCompleto();

        public LinkedList<Vertice> getVertices();

        public LinkedList<Vertice> adjacentes(Vertice v) throws VerticeNaoEncontradoException;

        public LinkedList<Aresta> getArestas() throws VerticeNaoEncontradoException;

        public void desfazSinalizacoesVertices();
}
