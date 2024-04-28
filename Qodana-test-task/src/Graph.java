import java.util.*;

public class Graph {
    private final List<List<Integer>> adj;

    public Graph() {
        adj = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            adj.add(new LinkedList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adj.get(source).add(destination);
    }

    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recursiveStack) {
        if (recursiveStack[i])
            return true;

        if (visited[i])
            return false;

        visited[i] = true;
        recursiveStack[i] = true;

        List<Integer> children = adj.get(i);

        for (Integer child : children) {
            if (isCyclicUtil(child, visited, recursiveStack)) {
                return true;
            }
        }

        recursiveStack[i] = false;
        return false;
    }

    public boolean isCyclic() {
        boolean[] visited = new boolean[26];
        boolean[] recursiveStack = new boolean[26];

        for (int i = 0; i < 26; i++) {
            if (isCyclicUtil(i, visited, recursiveStack))
                return true;
        }
        return false;
    }

    void topologicalSortUtil(int v, boolean[] visited,
                             Stack<Integer> stack) {
        visited[v] = true;
        for (Integer i : adj.get(v)) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        stack.push(v);
    }

    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[26];
        for (int i = 0; i < 26; i++)
            visited[i] = false;
        for (int i = 0; i < 26; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        // Print contents of stack
        System.out.println("One of the possible permutations of the alphabet: ");

        ArrayList<Integer> unusedLetters = new ArrayList<>();
        while (stack.peek() != 0) {
            unusedLetters.add(stack.pop());
        }
        while (!stack.isEmpty()) {
            System.out.print((char) (stack.pop() + 'a'));
            System.out.print(" ");
        }
        for (Integer i : unusedLetters) {
            System.out.print((char) (i + 'a'));
            System.out.print(" ");
        }
    }

    public List<List<Integer>> getAdj() {
        return adj;
    }
}
