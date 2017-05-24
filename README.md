# sedgewick_algorithms

Algorithm implementation examples from the book, Algorithms 4th Edition.

## Graph Algorithms

### Undirected Graphs

| Problem | Solution | Page |
| ------- | -------- | ---- |
| Single-Source Connectivity | [DepthFirstSearch](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/DepthFirstSearch.java) | 531 |
| Single-Source Paths | [DepthFirstPaths](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/DepthFirstPaths.java) | 536 |
| Single-Source Shortest Paths | [BreadthFirstPaths](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/BreadthFirstPaths.java) | 540 |
| Connectivity | [ConnectedComponents](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/ConnectedComponents.java) | 544 |
| Cycle Detection | [Cycle](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/Cycle.java) | 547 |
| Bipartiteness (Two-Colourability) | [Bipartite](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/Bipartite.java) | 547 |
| Named Vertices | [SymbolGraph](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/SymbolGraph.java) | 552 |
| Degrees of Separation | [DegreesOfSeparation](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/apps/DegreesOfSeparation.java) | 555 |

### Directed Graphs

| Problem | Solution | Page | Notes |
| ------- | -------- | ---- | ----- |
| Single- and Multiple-Source Reachability | [DirectedDepthFirstSearch](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/DirectedDepthFirstSearch.java) | 571 | |
| Single-Source Directed Paths | [DepthFirstDirectedPaths](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/DepthFirstDirectedPaths.java) | 573 | |
| Single-Source Shortest Directed Paths | [BreadthFirstDirectedPaths](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/BreadthFirstDirectedPaths.java) | 573 | |
| Directed Cycle Detection | [DirectedCycle](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/DirectedCycle.java) | 577 | |
| Depth-First Vertex Orders | [DepthFirstOrder](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/DepthFirstOrder.java) | 580 | |
| Precedence-Constrained Scheduling | [TopologicalSort](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/TopologicalSort.java) | 581 | |
| Topological Sort | [TopologicalSort](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/TopologicalSort.java) | 581 | |
| Strong Connectivity | [StronglyConnectedComponents](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/StronglyConnectedComponents.java) | 587 | [Kosaraju's Algorithm](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm) |
| All-Pairs Reachability | [TransitiveClosure](https://github.com/AshleyByeUK/sedgewick_algorithms/blob/master/src/main/java/uk/ashleybye/sedgewick/graph/TransitiveClosure.java) | 593 | |