package DZ6;

import java.io.File;
import java.util.List;

public class Run {
    public static void main(String[] args) throws Exception {
        File maze1 = new File("maze1.txt");
        File maze2 = new File("maze2.txt");

        execute(maze1);
        execute(maze2);
    }

    private static void execute(File file) throws Exception {
        Maze maze = new Maze(file);
        bfs(maze);
    }

    private static void bfs(Maze maze) {
        BFSMazeSolver bfs = new BFSMazeSolver();
        List<Coordinate> path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }
}