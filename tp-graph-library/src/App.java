import java.util.LinkedList;
import java.util.Random;

import Exception.VerticeJaAdicionadoComoAdjacente;
import Exception.VerticeJaExisteException;
import ListaAdjacencia.Grafo;
import utils.Vertice;

public class App {
    public static void main(String[] args) throws Exception {
        executaIlustracao(100);
        System.out.println();
    }

    public static void executaIlustracao(int quantidadeVertices) throws Exception {
        System.out.println("Executando exemplo para " + quantidadeVertices + " vertices...");
        System.out.println("Criando grafo...");
        Grafo g = criaGrafoAletorio(quantidadeVertices);
        System.out.println("Gerando arestas aleatorias...");
        criaArestasAleatorias(g);

        long mstime = System.currentTimeMillis();
        System.out.printf("\nTEMPO em milisegundos: ", mstime);
        System.out.println("Work in progress...");
    }

    public static Grafo criaGrafoAletorio(int quantidadeVertices) throws VerticeJaExisteException {
        Vertice[] vertices = new Vertice[quantidadeVertices];
        for (int i = 0; i < quantidadeVertices; i++) {
            vertices[i] = new Vertice(String.valueOf(i));
        }
        Grafo g = new Grafo(vertices);
        return g;
    }

    public static void criaArestasAleatorias(Grafo g) throws Exception {
        Random r = new Random();
        int quantidadeVertices = g.quantidadeVertices();
        int quantidadeArestasACriar = Math.min(quantidadeVertices, 100); // Limitando a 100 arestas para poupar recursos
        LinkedList<Vertice> vertices = g.getVertices();
        for (int i = 0; i < quantidadeArestasACriar; i++) {
            Vertice v1 = vertices.get(r.nextInt(quantidadeVertices - 1));
            Vertice v2 = vertices.get(r.nextInt(quantidadeVertices - 1));

            if (v1.equals(v2)) {
                i--;
                continue;
            }

            try {
                g.adicionarAresta(v1, v2);
            } catch (VerticeJaAdicionadoComoAdjacente e) {
                i--;
            } catch (Exception e) {
                throw e;
            }
        }
    }
}