import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import java.util.*;


public class GraphLab {

        public static void bfs(Graph<Integer, String> graph, int startVertex) {
            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            queue.add(startVertex);
            visited.add(startVertex);
            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                System.out.print(currentVertex + " ");
                for (String edge : graph.getOutEdges(currentVertex)) {
                    int neighbor = graph.getDest(edge);
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        public static void dfs(Graph<Integer, String> graph, int startVertex) {
            Stack<Integer> stack = new Stack<>();
            Set<Integer> visited = new HashSet<>();
            stack.push(startVertex);
            visited.add(startVertex);
            while (!stack.isEmpty()) {
                int currentVertex = stack.pop();
                System.out.print(currentVertex + " ");
                for (String edge : graph.getOutEdges(currentVertex)) {
                    int neighbor = graph.getDest(edge);
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        stack.push(neighbor);
                    }
                }
            }
        }

        public static void main(String[] args) {

            Graph<Integer, String> graph = new DirectedSparseGraph<>();

            graph.addVertex(0);
            graph.addVertex(1);
            graph.addVertex(2);
            graph.addVertex(3);
            graph.addVertex(4);
            graph.addVertex(5);

            graph.addEdge("0-1", 0, 1);
            graph.addEdge("0-2", 0, 2);
            graph.addEdge("1-3", 1, 3);
            graph.addEdge("1-4", 1, 4);
            graph.addEdge("2-4", 2, 4);
            graph.addEdge("3-4", 3, 4);
            graph.addEdge("3-5", 3, 5);
            graph.addEdge("4-5", 4, 5);

            System.out.println("Breadth First Search:");
            bfs(graph, 0);

            System.out.println("\nDepth First Search:");
            dfs(graph, 0);
        }
    }






