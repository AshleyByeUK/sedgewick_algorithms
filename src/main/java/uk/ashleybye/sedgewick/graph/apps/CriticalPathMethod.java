package uk.ashleybye.sedgewick.graph;

import java.util.Scanner;

public class CriticalPathMethod {

  public static void main(String[] args) {
    if (args.length == 1 && args[0].toLowerCase().equals("--help")) {
      System.out.println("Usage java CriticalPathMethod < jobsPC.txt");
      System.exit(0);
    }

    Scanner scanner = new Scanner(System.in);
    int numTasks = Integer.parseInt(scanner.nextLine());

    EdgeWeightedDigraph schedule = new EdgeWeightedDigraph((2 * numTasks) + 2);

    /*
     * Build the schedule (an edge-weighted DAG):
     * A source vertex, from which edges to all task start vertices originate. Edges have 0 weight.
     * A sink vertex, to which edges from all task end vertices finish. Edges have 0 weight.
     * A taskStart and taskEnd vertex for each task. Edge from start to end has duration weight.
     * An edge of zero weight from taskEnd vertices to any successor tasks (those for which a task
     *     must be completed prior to it beginning).
     */
    int source = 2 * numTasks;
    int sink = (2 * numTasks) + 1;
    for (int taskStart = 0; taskStart < numTasks; taskStart++) {
      String[] taskItem = scanner.nextLine().split("\\s+");
      double duration = Double.parseDouble(taskItem[0]);

      int taskEnd = taskStart + numTasks; // Not needed, but aids clarity of intent.
      schedule.addEdge(new DirectedEdge(taskStart, taskEnd, duration));
      schedule.addEdge(new DirectedEdge(source, taskStart, 0.0));
      schedule.addEdge(new DirectedEdge(taskEnd, sink, 0.0));

      for (int successorTask = 1; successorTask < taskItem.length; successorTask++) {
        int successor = Integer.parseInt(taskItem[successorTask]);
        schedule.addEdge(new DirectedEdge(taskEnd, successor, 0.0));
      }
    }

    /*
     * It is then just a matter of using AcyclicLongestPath to compute the longest paths tree and
     * printing out the results. The times shown are the required start times for each task.
     */
    AcyclicLongestPath startTimes = new AcyclicLongestPath(schedule, source);

    System.out.println("Start Times:");
    for (int task = 0; task < numTasks; task++) {
      System.out.printf("%4d: %5.1f\n", task, startTimes.getDistanceTo(task));
    }
    System.out.printf("Finish Time: %5.1f\n", startTimes.getDistanceTo(sink));
  }
}
