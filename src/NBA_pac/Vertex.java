package NBA_pac;

import java.util.ArrayList;

public class Vertex<T extends Comparable<T>, N extends Comparable<N>> {
    // Attributes
    T vertexInfo;          // Holds the information associated with the vertex
    T vertexInfo2;         // Additional information associated with the vertex
    int indeg;             // Represents the indegree of the vertex
    int outdeg;            // Represents the outdegree of the vertex
    Vertex<T, N> nextVertex;  // Reference to the next vertex in the graph
    Edge<T, N> firstEdge;      // Reference to the first edge incident on this vertex
    private int X;             // X coordinate of the vertex in a graphical representation
    private int Y;             // Y coordinate of the vertex in a graphical representation
    private boolean visited = false;  // Flag indicating whether the vertex has been visited during graph traversal
    private int parents = -2;          // Parent vertex index used in certain graph algorithms
    private ArrayList<T> path = new ArrayList<>();  // List storing the path to this vertex

    // Constructors
    public Vertex() {
        // Default constructor initializing all attributes to default values
        vertexInfo = null;
        vertexInfo2 = null;
        indeg = 0;
        outdeg = 0;
        nextVertex = null;
        firstEdge = null;
    }

    public Vertex(T vInfo, Vertex<T, N> next) {
        // Constructor to initialize the vertex with only vertexInfo and nextVertex
        vertexInfo = vInfo;
        vertexInfo2 = null;
        indeg = 0;
        outdeg = 0;
        nextVertex = next;
        firstEdge = null;
    }

    public Vertex(T vInfo, T vInfo2, Vertex<T, N> next) {
        // Constructor to initialize the vertex with both vertexInfo and vertexInfo2, along with nextVertex
        vertexInfo = vInfo;
        vertexInfo2 = vInfo2;
        indeg = 0;
        outdeg = 0;
        nextVertex = next;
        firstEdge = null;
    }

    // Getter and setter methods
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean v) {
        visited = v;
    }

    public int isParents() {
        return parents;
    }

    public void setParents(int p) {
        parents = p;
    }

    public ArrayList<T> isPath() {
        return path;
    }

    public void setPath(T p) {
        path.add(p);
    }

    public void setCoordinate(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
