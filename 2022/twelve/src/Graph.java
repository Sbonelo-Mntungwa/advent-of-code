import java.util.ArrayList;

public class Graph {
    private Node[] nodes;
    private int noOfNodes;
    private Edge[] edges;
    private int noOfEdges;

    public Graph(Edge[] edges) {
        this.edges = edges;
        this.noOfNodes = calculateNoOfNodes(edges);
        this.nodes = new Node[this.noOfNodes];

        for (int i = 0; i < this.noOfNodes; i++) {
            this.nodes[i] = new Node();
        }

        this.noOfEdges = edges.length;

        for (int i = 0; i < this.noOfEdges; i++) {
            this.nodes[edges[i].getFromNodeIndex()].getEdges().add(edges[i]);
            this.nodes[edges[i].getToNodeIndex()].getEdges().add(edges[i]);
        }
    }

    private int calculateNoOfNodes(Edge[] edges) {
        int noOfNodes = 0;
        for (Edge edge : edges) {
            if (edge.getToNodeIndex() > noOfNodes) {
                noOfNodes = edge.getToNodeIndex();
            }
            if (edge.getFromNodeIndex() > noOfNodes) {
                noOfNodes = edge.getFromNodeIndex();
            }
        }
        noOfNodes++;
        return noOfNodes;
    }

    public void calculateShortestDistance() {
        this.nodes[0].setDistanceFromSource(0);
        int nextNode = 0;
        for (int i = 0; i < this.nodes.length; i++) {
            ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
            for (int j = 0; j < currentNodeEdges.size(); j++) {
                int neighbourIndex = currentNodeEdges.get(j).getNeighbourIndex(nextNode);

                if (!this.nodes[neighbourIndex].isVisited()) {
                    int tentative = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdges.get(j).getLength();

                    if (tentative < nodes[neighbourIndex].getDistanceFromSource()) {
                        nodes[neighbourIndex].setDistanceFromSource(tentative);
                    }
                }
            }

            nodes[nextNode].setVisited(true);

            nextNode = getNodeShortestDistance();
        }
    }

    private int getNodeShortestDistance() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;

        for (int i = 0; i < this.nodes.length; i++) {
            int currentDist = this.nodes[i].getDistanceFromSource();

            if (!this.nodes[i].isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }

    public void printResult() {
        String output = "Number of nodes = " + this.nodes.length;
        output += "\nNumber of edges = " + this.noOfEdges;

        for (int i = 0; i < this.nodes.length; i++) {
            output += ("\nThe shortezt distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource());
        }
        System.out.println(output);
    }

    public Node[] getNodes() {
        return nodes;
    }

    public int getNoOfNodes() {
        return noOfNodes;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public int getNoOfEdges() {
        return noOfEdges;
    }

}
