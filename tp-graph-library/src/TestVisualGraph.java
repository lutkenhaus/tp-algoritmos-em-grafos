/* import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

public class GraphVisualization {

    public static void main(String[] args) {
        // Criar um objeto mxGraph
        mxGraph graph = new mxGraph();

        // Inicializar o modelo do grafo
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();

        try {
            // Adicionar nós
            Object node1 = graph.insertVertex(parent, null, "1", 20, 20, 40, 40);
            Object node2 = graph.insertVertex(parent, null, "2", 120, 20, 40, 40);
            
            // Adicionar arestas
            graph.insertEdge(parent, null, "", node1, node2);
            
            // Layout automático
            mxCircleLayout layout = new mxCircleLayout(graph);
            layout.execute(parent);
        } finally {
            graph.getModel().endUpdate();
        }

        // Criar um componente Swing para exibir o grafo
        mxGraphComponent graphComponent = new mxGraphComponent(graph);

        // Criar uma janela para exibir o componente do grafo
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.pack();
        frame.setVisible(true);
    }
}
 */