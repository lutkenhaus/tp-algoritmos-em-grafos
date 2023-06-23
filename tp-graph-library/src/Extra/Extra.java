package Extra;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import AdjacencyList.Graph;
import Exception.NodeAlreadyAddedAsAdjacentException;
import Exception.NodeAlreadyExistsException;
import utilsFolder.Node;

public class Extra {
        public static void main(String[] args) throws Exception {

        createGraph(5);
        System.out.println();

        List<String> li1 = new LinkedList<String>(){
            {
                this.add("animal");
                this.add("nuts");
                this.add("java");
            }
        };
        Extra mtc = new Extra();
        System.out.println(mtc.getListAsCsvString(li1));
        List<String> li2 = new LinkedList<String>(){
            {
                this.add("java");
                this.add("unix");
                this.add("c++");
            }
        };
        System.out.println(mtc.getListAsCsvString(li2));
    }

     
    public String getListAsCsvString(List<String> list) {

        StringBuilder sb = new StringBuilder();
        for(String str:list){
            if(sb.length() != 0){
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static void createGraph(int nodeQuantity) throws Exception {

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

        System.out.println();
        System.out.println(g.getEdges());

        List<String> listaaa = new LinkedList<String>() {};
        listaaa.add(g.getEdges().toString());

        System.out.println("ok");
        System.out.println(listaaa);

        writeDataToCSV("./database/dados.csv", listaaa);

        LinkedList<utilsFolder.Edge> lista_de_arestas = new LinkedList<utilsFolder.Edge>(g.getEdges());

        List<String> edgesList = new LinkedList<String>() {
            {
                this.add(lista_de_arestas.toString());
            }
        };

        Extra mtc = new Extra();
        System.out.println(mtc.getListAsCsvString(edgesList));
    }

    private static void writeDataToCSV(String filePath, List<String> data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String row : data) {
                writer.write(row + "\n");
            }
            System.out.println("Arquivo CSV criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever o arquivo CSV: " + e.getMessage());
        }
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