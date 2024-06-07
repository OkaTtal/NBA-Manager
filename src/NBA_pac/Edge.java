package NBA_pac;

public class Edge<T extends Comparable<T>, N extends Comparable<N>> {
    // Instance variables
    Vertex<T, N> toVertex;
    N weight;
    Edge<T, N> nextEdge;

    // Default constructor
    public Edge() {
        toVertex = null;
        weight = null;
        nextEdge = null;
    }

    // Parameterized constructor
    public Edge(Vertex<T, N> destination, N w, Edge<T, N> next) {
        toVertex = destination;
        weight = w;
        nextEdge = next;
    }
}
