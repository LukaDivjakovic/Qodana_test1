import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number from 1 to 100:");
        int n = 0;
        while (n < 1 || n > 100) {
            n = Integer.parseInt(sc.nextLine());
            if (n < 1 || n > 100) {
                System.out.println("Enter number from 1 to 100:");
            }
        }
        String[] names = new String[n + 1];
        names[0] = "alex";

        for (int i = 1; i < n + 1; i++) {
            System.out.println("Enter name " + i + ":");
            String name = sc.nextLine();
            if (Arrays.asList(names).contains(name)) {
                System.out.println("Repeating name, please try again");
                i--;
            }
            names[i] = name;
        }

        System.out.println(Arrays.toString(names));

        sc.close();

        Graph graph = new Graph();

        for (int i = 0; i < n - 1; i++) {
            String current = names[i];
            String next = names[i + 1];
            int minLength = Math.min(current.length(), next.length());
            for (int j = 0; j < minLength; j++) {
                if (current.charAt(j) != next.charAt(j)) {
                    int c1 = current.charAt(j) - 'a';
                    int c2 = next.charAt(j) - 'a';
                    if (!graph.getAdj().get(c1).contains(c2)) {
                        graph.addEdge(c1, c2);
                    }
                    break;
                }
            }
        }
//        check if graph has a cycle in it
        if (graph.isCyclic()) {
            System.out.println("Cyclic Graph, impossible alphabet");
            System.exit(0);
        } else {
            System.out.println("Graph is acyclic");
        }

//        do topological sorting and print result
        graph.topologicalSort();
    }
}
