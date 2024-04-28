[Github link](https://github.com/LukaDivjakovic/Qodana_test1)

In my solution, I take in an array of names. For every i-th and i+1st name, I check where they are first different and make a Directed graph where there is a path from the lexicographically smaller letter to the lexicographically bigger one.
Then I have to check if the graph has any cycles because that would mean the solution is impossible to find.
If there are no cycles, in other words, the graph is a DAG (Directed acyclical graph), I perform a topological sorting algorithm on the graph and print out a result.
