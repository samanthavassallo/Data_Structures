//A common security method used for online banking is to ask the user for three random
//characters from a passcode. For example, if the passcode was 531278, they may ask
//for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.
//
//The text file, keylog.txt, contains fifty successful login attempts.
//
//Given that the three characters are always asked for in order, analyse the file so as to
//determine the shortest possible secret passcode of unknown length.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Cracking {


        private static List<String> readAttemptsFromFile(String filename) throws IOException {
            List<String> attempts = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    attempts.add(line);
                }
            }
            return attempts;
        }


    private static Graph<Character, String> constructGraph(List<String> attempts) {
        Graph<Character, String> graph = new DirectedSparseGraph<>();
        int counter = 1;
        for (String attempt : attempts) {
            char[] chars = attempt.toCharArray();
            char first = chars[0];
            char second = chars[1];
            char third = chars[2];
            String edge1 = "Edge_" + counter++;
            String edge2 = "Edge_" + counter++;
            graph.addEdge(edge1, first, second, EdgeType.DIRECTED);
            graph.addEdge(edge2, second, third, EdgeType.DIRECTED);
        }
        return graph;
    }


        private static List<Character> findShortestPasscode(List<String> attempts) {
            Graph<Character, String> graph = constructGraph(attempts);
            Map<Character, Integer> indegrees = calculateIndegrees(graph);
            return topologicalSort(graph, indegrees);
        }


        private static Map<Character, Integer> calculateIndegrees(Graph<Character, String> graph) {
            Map<Character, Integer> indegrees = new HashMap<>();
            for (Character vertex : graph.getVertices()) {
                indegrees.put(vertex, 0);
            }
            for (String edge : graph.getEdges()) {
                char dest = graph.getDest(edge);
                indegrees.put(dest, indegrees.get(dest) + 1);
            }
            return indegrees;
        }


        private static List<Character> topologicalSort(Graph<Character, String> graph, Map<Character, Integer> indegrees) {
            List<Character> passcode = new ArrayList<>();
            Queue<Character> queue = new LinkedList<>();
            for (Map.Entry<Character, Integer> entry : indegrees.entrySet()) {
                if (entry.getValue() == 0) {
                    queue.add(entry.getKey());
                }
            }
            while (!queue.isEmpty()) {
                char vertex = queue.poll();
                passcode.add(vertex);
                for (String successor : graph.getOutEdges(vertex)) {
                    char neighbor = graph.getDest(successor);
                    indegrees.put(neighbor, indegrees.get(neighbor) - 1);
                    if (indegrees.get(neighbor) == 0) {
                        queue.add(neighbor);
                    }
                }
            }
            return passcode;
        }
    public static void main(String[] args) {
        try {
            List<String> attempts = readAttemptsFromFile("keylog.txt");
            List<Character> passcode = findShortestPasscode(attempts);
            System.out.println("Shortest possible passcode: " + passcode +(" To check on webiste eneter the numbers with no commas or []"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

