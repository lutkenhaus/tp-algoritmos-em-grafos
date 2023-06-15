package utilsFolder;

import java.util.LinkedList;

import Exception.NodeAlreadyAddedAsAdjacentException;
import Exception.NodeAlreadyExistsException;
import Exception.NodeNotFoundException;

public interface Graph {
        public void addEdge(utilsFolder.Node v1, utilsFolder.Node v2)
                        throws NodeNotFoundException, NodeAlreadyAddedAsAdjacentException,
                        NodeAlreadyExistsException;

        public void removeEdge(Node v1, Node v2) throws NodeNotFoundException;

        public boolean isAdjacentNodes(Node v1, Node v2) throws NodeNotFoundException;

        public boolean isAdjacentEdges(Node vA1, Node vA2, Node vB1, Node vB2)
                        throws NodeNotFoundException;

        public boolean edgeExists(Node v1, Node v2);

        public int nodeAmount() throws NodeNotFoundException;

        public int edgeAmount() throws NodeNotFoundException;

        public boolean isNull();

        public boolean isComplete();

        public LinkedList<Node> getNodes();

        public LinkedList<Node> adjacentNodes(Node v) throws NodeNotFoundException;

        public LinkedList<Edge> getEdges() throws NodeNotFoundException;

        public void undoNodeFlags();
}
