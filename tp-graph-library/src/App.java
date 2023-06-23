import java.util.LinkedList;
import java.util.Random;

import Exception.NodeAlreadyAddedAsAdjacentException;
import Exception.NodeAlreadyExistsException;
import AdjacencyList.Graph;
import utilsFolder.Node;

public class App {

    public static void main(String[] args) throws Exception {

        executeRepresentation(100);
        System.out.println();
    }

    public static void executeRepresentation(int nodeQuantity) throws Exception {

        System.out.println("Executando exemplo para " + nodeQuantity + " vertices...");
        System.out.println("Criando grafo...");
        Graph g = generateRandomGraph(nodeQuantity);
        System.out.println("Gerando arestas...");
        generateRandomEdges(g);

        System.out.print("\nTipo do Grafo G: " + g.getClass() + "\n");
        boolean isComplete = g.isComplete();
        System.out.print("\nGrafo é completo? ");
        System.out.print(isComplete);
        int nAmount = g.nodeAmount();
        System.out.print("\nQuantidade de vértices: ");
        System.out.print(nAmount);
        int eAmount = g.nodeAmount();
        System.out.print("\nQuantidade de arestas: ");
        System.out.print(eAmount);

        long mstime = ((System.currentTimeMillis()) / 1000);
        System.out.println("\nTempo de execução em millissegundos: ");
        System.out.printf("%d%n", mstime);
    }

    public static Graph generateRandomGraph(int nodeQuantity) throws NodeAlreadyExistsException {
        Node[] vertices = new Node[nodeQuantity];
        for (int i = 0; i < nodeQuantity; i++) {
            vertices[i] = new Node(String.valueOf(i));
        }
        Graph g = new Graph(vertices);
        return g;
    }

    public static void generateRandomEdges(Graph g) throws Exception {
        Random r = new Random();
        int nodeQuantity = g.nodeAmount();
        int edgeAmountToBeGenerated = Math.min(nodeQuantity, 100);
        LinkedList<Node> vertices = g.getNodes();
        for (int i = 0; i < edgeAmountToBeGenerated; i++) {
            Node v1 = vertices.get(r.nextInt(nodeQuantity - 1));
            Node v2 = vertices.get(r.nextInt(nodeQuantity - 1));

            if (v1.equals(v2)) {
                i--;
                continue;
            }

            try {
                g.addEdge(v1, v2);
            } catch (NodeAlreadyAddedAsAdjacentException e) {
                i--;
            } catch (Exception e) {
                throw e;
            }
        }
    }
}