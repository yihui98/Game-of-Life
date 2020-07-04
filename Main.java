package life;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int lines = 30;
        int times = 100;

        char[][] map = new char[lines][lines];
        Random random = new Random();
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < lines; j++) {
                if (random.nextBoolean()) {
                    map[i][j] = 'O';
                } else {
                    map[i][j] = ' ';
                }
            }
        }
        printer(map,times,lines);

    }


    public static void printer(char[][] map, int times, int lines) {
        char[][] newMap = map;
        int count = 0;
        for (int i = 0; i < times; i++) {
            newMap = generation(newMap, lines);
            count++;
            int alive = countAlive(newMap, lines);
            System.out.println("Generation #" + count);
            System.out.println("Alive: " + alive);
            new GameOfLife(newMap,count,lines,alive);
            /*for (char[] row : newMap) {
                System.out.println(row);
            } */
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            } catch (IOException | InterruptedException e) {
            }
        }
    }


    public static char[][] generation(char[][] map, int lines) {
        char[][] newMap = Arrays.stream(map).map(char[]::clone).toArray(char[][]::new);

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < lines; j++) {//for each cell
                int count = 0;
                for (int a = -1; a < 2; a++) { //row to check
                    for (int b = -1; b < 2; b++) {//column to check

                        int x = a;
                        int y = b;
                        if (a + i < 0) {
                            x = lines - 1;
                        }
                        if (b + j == lines) {
                            y = -(lines - 1);
                        }
                        if (b + j < 0) {
                            y = lines - 1;
                        }
                        if (a + i == lines) {
                            x = -(lines - 1);
                        }

                        if (map[i + x][j + y] == 'O') { //general case
                            count += 1;
                        }
                        //System.out.println("I: " + (i+x) + "J: " + (j+y) + " Count: " + count);
                    }
                }
                if (map[i][j] == 'O') {
                    count -= 1;
                }
                //System.out.println("I: " + i + " J: " + j);
                //System.out.println(count);
                if (map[i][j] == 'O') {
                    if (count == 2 || count == 3) {
                        newMap[i][j] = 'O'; //Alive cell survives
                    } else {
                        newMap[i][j] = ' ';
                    }
                } else if (map[i][j] == ' ') {
                    if (count == 3) {
                        newMap[i][j] = 'O'; //dead cell revives
                    } else {
                        newMap[i][j] = ' ';
                    }
                }
            }
        }
        return newMap;
    }


    public static int countAlive(char[][] map, int lines) {
        int count = 0;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < lines; j++) {
                if (map[i][j] == 'O') {
                    count++;
                }
            }
        }
        return count;
    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static class Grid extends JPanel {

        private List<Point> fillCells;

        public Grid() {
            fillCells = new ArrayList<>(25);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Point fillCell : fillCells) {
                int cellX = 10 + (fillCell.x * 10);
                int cellY = 10 + (fillCell.y * 10);
                g.setColor(Color.BLACK);
                g.fillRect(cellX, cellY, 10, 10);
            }
            g.setColor(Color.BLACK);
            g.drawRect(10, 10, 300, 300);

            for (int i = 10; i <= 310; i += 10) {
                g.drawLine(i, 10, i, 310);
            }

            for (int i = 10; i <= 310; i += 10) {
                g.drawLine(10, i, 310, i);
            }
        }

        public void fillCell(int x, int y) {
            fillCells.add(new Point(x, y));
            repaint();
        }

    }

}



