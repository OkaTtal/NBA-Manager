package NBA_pac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Breadth-First Search (BFS) implementation for a weighted graph
 * @param <T> The type of the vertex (must be comparable)
 * @param <N> The type of the edge weight (must be comparable)
 */
public class BFS<T extends Comparable<T>, N extends Comparable<N>> {
    // Weighted graph to perform BFS on
    WeightedGraph<T, N> graph = new WeightedGraph<>();

    // Constructor to initialize the BFS with a given weighted graph
    public BFS(WeightedGraph<T, N> wg) {
        graph = wg;
    }

    // Method to perform BFS starting from a given vertex
    public ArrayList<T> bfs(T startVertex) {
        // Mark all vertices as not visited
        Vertex<T, N> temp = graph.head;
        while (temp != null) {
            temp.setVisited(false);
            temp = temp.nextVertex;
        }

        // List to store the BFS traversal order
        ArrayList<T> sl = new ArrayList<>();
        // Queue to manage the BFS process
        Queue<T> queue = new LinkedList<>();
        queue.add(startVertex);
        
        // Initialize the starting vertex properties
        graph.getVertex(startVertex).setParents(0);
        graph.getVertex(startVertex).setPath(startVertex);

        // Perform BFS
        while (!queue.isEmpty()) {
            T current = queue.poll();
            if (!graph.getVertex(current).isVisited()) {
                graph.getVertex(current).setVisited(true);
                sl.add(current);

                // Priority queue to handle neighbors by their edge weights
                Queue<ComparableDistance<T, N>> pq = new PriorityQueue<>();
                for (T nei : graph.getNeighbours(current)) {
                    pq.add(new ComparableDistance(graph.getEdgeWeight(current, nei), nei));
                    if (graph.getVertex(nei).isParents() == -2) {
                        graph.getVertex(nei).setParents(graph.getVertex(current).isParents() + 1);
                        for (T pa : graph.getVertex(current).isPath())
                            graph.getVertex(nei).setPath(pa);
                        graph.getVertex(nei).setPath(nei);
                    }
                }

                // Add neighbors to the queue in priority order
                while (pq.peek() != null) {
                    T hold = pq.remove().name;
                    queue.add(hold);
                }
            }
        }
        return sl;
    }

    // Method to calculate the total BFS path cost
    public int calcBFSTotal(ArrayList<T> sl) {
        int total = 0, minus = 0;
        ArrayList<T> pac = graph.getVertex(sl.get(0)).isPath();
        for (int i = 1; i < sl.size(); i++) {
            ArrayList<T> pa = graph.getVertex(sl.get(i)).isPath();
            for (int j = 0, k = 1; k < pa.size(); j++, k++) {
                if (pac.size() == pa.size() && pa.size() >= 3 && pa.get(pa.size() - 2).compareTo(pac.get(pac.size() - 2)) == 0) {
                    total += Integer.parseInt(graph.getEdgeWeight(pa.get(pa.size() - 2), pa.get(pa.size() - 1)).toString());
                    if (i == sl.size() - 1) {
                        minus += Integer.parseInt(graph.getEdgeWeight(pa.get(pa.size() - 2), pa.get(pa.size() - 1)).toString());
                        for (int m = pa.size() - 2, n = m - 1; n != -1; n--, m--) {
                            minus += Integer.parseInt(graph.getEdgeWeight(pa.get(n), pa.get(m)).toString());
                        }
                    }
                    break;
                } else {
                    if (i == sl.size() - 1) minus += Integer.parseInt(graph.getEdgeWeight(pa.get(j), pa.get(k)).toString());
                    total += Integer.parseInt(graph.getEdgeWeight(pa.get(j), pa.get(k)).toString());
                }
            }
            pac = pa;
        }
        total = total * 2;
        total = total - minus;
        return total;
    }

    // Method to get the logical BFS path
    public ArrayList<T> BFSLogicalPath(ArrayList<T> sl) {
        ArrayList<T> logicalPath = new ArrayList<>();
        for (int i = 1; i < sl.size(); i++) {
            ArrayList<T> pa = graph.getVertex(sl.get(i)).isPath();
            ArrayList<T> previous = graph.getVertex(sl.get(i - 1)).isPath();
            ArrayList<T> next = null;
            if (i != sl.size() - 1) {
                next = graph.getVertex(sl.get(i + 1)).isPath();
            }
            for (int j = 0, k = 0; j < pa.size() * 2; j++) {
                if (j < pa.size()) {
                    if (previous.size() == pa.size() && pa.size() >= 3 && pa.get(pa.size() - 2).compareTo(previous.get(previous.size() - 2)) == 0) {
                        if (k < pa.size() - 2) {
                            k++;
                            continue;
                        }
                    }
                    logicalPath.add(pa.get(k));
                    k++;
                } else {
                    if (i == sl.size() - 1) break;
                    k--;
                    if (next.size() == pa.size() && pa.size() >= 3 && pa.get(pa.size() - 2).compareTo(next.get(next.size() - 2)) == 0) {
                        if (k < pa.size() - 2) continue;
                    }
                    logicalPath.add(pa.get(k));
                }
            }
        }
        return logicalPath;
    }
}
