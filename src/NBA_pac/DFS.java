package NBA_pac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Depth-First Search (DFS) implementation for a weighted graph.
 * @param <T> the type of vertices in the graph.
 * @param <N> the type of weights on the edges of the graph.
 */
public class DFS<T extends Comparable<T>, N extends Comparable<N>> {
    // The weighted graph
    WeightedGraph<T, N> graph = new WeightedGraph<>();

    /**
     * Constructs a DFS object with the given weighted graph.
     * @param wg the weighted graph to perform DFS on.
     */
    public DFS(WeightedGraph<T, N> wg) {
        graph = wg;
    }

    /**
     * Performs Depth-First Search (DFS) traversal starting from the specified start vertex.
     * @param startVertex the starting vertex for DFS traversal.
     * @return the DFS traversal sequence as an ArrayList.
     */
    public ArrayList<T> dfs(T startVertex) {
        // Mark all vertices as unvisited
        Vertex<T, N> temp = graph.head;
        while (temp != null) {
            temp.setVisited(false);
            temp = temp.nextVertex;
        }

        // List to store traversal sequence
        ArrayList<T> sl = new ArrayList<>(graph.size);
        // Stack for DFS traversal
        Deque<T> stack = new LinkedList<>();
        stack.push(startVertex);

        // Perform DFS traversal
        while (!stack.isEmpty()) {
            T current = stack.pop();
            if (!graph.getVertex(current).isVisited()) {
                graph.getVertex(current).setVisited(true);
                sl.add(current);

                // Add neighboring vertices to the stack
                Queue<ComparableDistance<T, N>> pq = new PriorityQueue<>(Collections.reverseOrder());
                for (T nei : graph.getNeighbours(current)) {
                    pq.add(new ComparableDistance(graph.getEdgeWeight(current, nei), nei));
                }
                while (pq.peek() != null) {
                    T hold = pq.remove().name;
                    stack.push(hold);
                }
            }
        }
        return sl; // Return DFS traversal sequence
    }

    /**
     * Calculates the total weight of traversed edges in the DFS traversal sequence.
     * @param sl the DFS traversal sequence.
     * @return the total weight of traversed edges.
     */
    public int calcDFSTotal(ArrayList<T> sl) {
        int total = 0;
        for (int i = 0, j = 1; j < sl.size(); i++, j++) {
            if (graph.hasEdge(sl.get(i), sl.get(j))) {
                total += Integer.parseInt(graph.getEdgeWeight(sl.get(i), sl.get(j)).toString());
            } else {
                int k = i - 1, l = j - 1;
                // Backtrack to find the path and calculate total weight
                while (!graph.hasEdge(sl.get(k), sl.get(j))) {
                    total += Integer.parseInt(graph.getEdgeWeight(sl.get(k), sl.get(l)).toString());
                    k--;
                    l--;
                }
                total += Integer.parseInt(graph.getEdgeWeight(sl.get(k), sl.get(l)).toString());
                total += Integer.parseInt(graph.getEdgeWeight(sl.get(k), sl.get(j)).toString());
            }
        }
        // Print explanation
        System.out.println("\nDFS is a better selection in this situation, \n"
                + "as although BFS go through all the vertices,\n"
                + "it functions to find the unweighted shortest path between two vertices only.");
        return total; // Return total weight of traversed edges
    }
}
