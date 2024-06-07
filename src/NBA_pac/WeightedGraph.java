// Importing necessary packages
package NBA_pac;

import java.util.*;

// Weighted graph class to represent a graph with weighted edges
public class WeightedGraph<T extends Comparable<T>, N extends Comparable<N>> {
    Vertex<T, N> head; // Reference to the head vertex
    int size; // Number of vertices in the graph
    int[][] matrix; // Adjacency matrix representation of the graph (not used in this implementation)

    // Constructor to initialize the weighted graph
    public WeightedGraph() {
        head = null;
        size = 0;
    }

    // Method to clear the graph
    public void clear() {
        head = null;
    }

    // Method to get the size of the graph
    public int getSize() {
        return this.size;
    }

    // Method to get the in-degree of a vertex
    public int getIndeg(T v) {
        if (hasVertex(v)) {
            Vertex<T, N> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0)
                    return temp.indeg;
                temp = temp.nextVertex;
            }
        }
        return -1;
    }

    // Method to get the out-degree of a vertex
    public int getOutdeg(T v) {
        if (hasVertex(v)) {
            Vertex<T, N> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0)
                    return temp.outdeg;
                temp = temp.nextVertex;
            }
        }
        return -1;
    }

    // Method to check if a vertex exists in the graph
    public boolean hasVertex(T v) {
        if (head == null)
            return false;
        Vertex<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0)
                return true;
            temp = temp.nextVertex;
        }
        return false;
    }

    // Method to add a vertex to the graph
    public boolean addVertex(T v, T v2) {
        if (!hasVertex(v)) {
            Vertex<T, N> temp = head;
            Vertex<T, N> newVertex = new Vertex<>(v, v2, null);
            if (head == null)
                head = newVertex;
            else {
                Vertex<T, N> previous = head;
                while (temp != null) {
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex;
            }
            size++;
            return true;
        } else
            return false;
    }

    // Method to get the index of a vertex
    public int getIndex(T v) {
        Vertex<T, N> temp = head;
        int pos = 0;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0)
                return pos;
            temp = temp.nextVertex;
            pos += 1;
        }
        return -1;
    }

    // Method to get a list of all vertex objects in the graph
    public ArrayList<T> getAllVertexObjects() {
        ArrayList<T> list = new ArrayList<>();
        Vertex<T, N> temp = head;
        while (temp != null) {
            list.add(temp.vertexInfo);
            temp = temp.nextVertex;
        }
        return list;
    }

    // Method to get a vertex at a specific position
    public T getVertex(int pos) {
        if (pos > size - 1 || pos < 0)
            return null;
        Vertex<T, N> temp = head;
        for (int i = 0; i < pos; i++)
            temp = temp.nextVertex;
        return temp.vertexInfo;
    }

    // Method to get a vertex with a specific value
    public Vertex<T, N> getVertex(T n) {
        if (head == null)
            return null;
        if (!hasVertex(n))
            return null;
        Vertex<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(n) == 0)
                return temp;
            temp = temp.nextVertex;
        }
        return null;
    }

    // Method to add a weighted edge between two vertices
    public boolean addEdge(T source, T destination, N w) {
        if (head == null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Vertex<T, N> destinationVertex = head;
                while (destinationVertex != null) {
                    if (destinationVertex.vertexInfo.compareTo(destination) == 0) {
                        Edge<T, N> currentEdge = sourceVertex.firstEdge;
                        Edge<T, N> newEdge = new Edge<>(destinationVertex, w, currentEdge);
                        sourceVertex.firstEdge = newEdge;
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    // Method to add an undirected weighted edge between two vertices
    public boolean addUndirectedEdge(T v1, T v2, N w) {
        if (this.addEdge(v1, v2, w))
            return this.addEdge(v2, v1, w);
        return false;
    }

    // Method to check if an edge exists between two vertices
    public boolean hasEdge(T source, T destination) {
        if (head == null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Edge<T, N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0)
                        return true;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    // Method to get the weight of an edge between two vertices
    public N getEdgeWeight(T source, T destination) {
        N notFound = null;
        if (head == null)
            return notFound;
        if (!hasVertex(source) || !hasVertex(destination))
            return notFound;
        Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Edge<T, N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0)
                        return currentEdge.weight;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return notFound;
    }

 public boolean removeEdge(T source, T destination) {
            if (head == null)
                return false;
            if (!(hasVertex(source) && hasVertex(destination)))
                return false;
            Vertex<T, N> sourceVertex = head;
            while (sourceVertex != null) {
                if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                    Edge<T, N> currentEdge = sourceVertex.firstEdge;
                    while (currentEdge != null) {
                        if (currentEdge.nextEdge.toVertex.vertexInfo.compareTo(destination) == 0) {
                            currentEdge.nextEdge = currentEdge.nextEdge.nextEdge;
                            return true;
                        }
                        currentEdge = currentEdge.nextEdge;
                    }
                }
                sourceVertex = sourceVertex.nextVertex;
            }
            return false;
        }

        // Method to get a list of neighbors of a vertex
        public ArrayList<T> getNeighbours(T v) {
            if (!hasVertex(v))
                return null;
            ArrayList<T> list = new ArrayList<>();
            Vertex<T, N> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0) {
                    Edge<T, N> currentEdge = temp.firstEdge;
                    while (currentEdge != null) {
                        list.add(currentEdge.toVertex.vertexInfo);
                        currentEdge = currentEdge.nextEdge;
                    }
                }
                temp = temp.nextVertex;
            }
            return list;
        }

        // Method to print the edges of the graph
        public void printEdges() {
            Vertex<T, N> temp = head;
            while (temp != null) {
                System.out.print("# " + temp.vertexInfo2 + " " + temp.vertexInfo + " : ");
                Edge<T, N> currentEdge = temp.firstEdge;
                while (currentEdge != null) {
                    System.out.print("[" + currentEdge.weight + "km--" + currentEdge.toVertex.vertexInfo + "]");
                    currentEdge = currentEdge.nextEdge;
                }
                System.out.println("\n");
                temp = temp.nextVertex;
            }
        }

        // Method to create a weighted graph representing NBA teams and cities
        public WeightedGraph<String, Integer> createNBAGraph() {
            // Arrays representing cities and corresponding NBA teams
            String[] cities = {"San Antonio", "Golden State", "Boston", "Miami", "Los Angeles", "Phoenix", "Orlando", "Denver", "Oklahoma City", "Houston"};
            String[] teams = {"Spurs", "Warriors", "Celtics", "Heat", "Lakers", "Suns", "Magic", "Nuggets", "Thunder", "Rockets"};

            // Creating a new weighted graph
            WeightedGraph<String, Integer> nba = new WeightedGraph<>();

            // Adding vertices (teams) to the graph and setting their coordinates
            for (int i = 0; i < cities.length; i++)
                nba.addVertex(teams[i], cities[i]);

            nba.getVertex("Spurs").setCoordinate(330, 400);
            nba.getVertex("Warriors").setCoordinate(20, 20);
            nba.getVertex("Celtics").setCoordinate(775, -20);
            nba.getVertex("Heat").setCoordinate(740, 410);
            nba.getVertex("Lakers").setCoordinate(5, 170);
            nba.getVertex("Suns").setCoordinate(200, 270);
            nba.getVertex("Magic").setCoordinate(620, 330);
            nba.getVertex("Nuggets").setCoordinate(355, 30);
            nba.getVertex("Thunder").setCoordinate(360, 230);
            nba.getVertex("Rockets").setCoordinate(540, 190);

            // Adding undirected edges (with weights) between NBA teams representing distances between cities
            nba.addUndirectedEdge("Spurs", "Suns", 500);
            nba.addUndirectedEdge("Spurs", "Thunder", 678);
            nba.addUndirectedEdge("Spurs", "Rockets", 983);
            nba.addUndirectedEdge("Spurs", "Magic", 1137);
            nba.addUndirectedEdge("Lakers", "Suns", 577);
            nba.addUndirectedEdge("Lakers", "Warriors", 554);
            nba.addUndirectedEdge("Lakers", "Thunder", 1901);
            nba.addUndirectedEdge("Thunder", "Warriors", 2214);
            nba.addUndirectedEdge("Thunder", "Nuggets", 942);
            nba.addUndirectedEdge("Warriors", "Nuggets", 1507);
            nba.addUndirectedEdge("Nuggets", "Celtics", 2845);
            nba.addUndirectedEdge("Celtics", "Rockets", 2584);
            nba.addUndirectedEdge("Celtics", "Heat", 3045);
            nba.addUndirectedEdge("Heat", "Magic", 268);
            nba.addUndirectedEdge("Magic", "Rockets", 458);
            nba.addUndirectedEdge("Rockets", "Thunder", 778);

            return nba;
        }
}

