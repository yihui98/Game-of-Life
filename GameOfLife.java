package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {
    public GameOfLife(char[][] map, int count, int lines, int alive) {

        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
        JLabel gen = new JLabel();
        gen.setText("Generation: " + count);
        gen.setName("GenerationLabel");
        JLabel aliveL = new JLabel("Alive: ");
        aliveL.setText("Alive: " + alive);
        aliveL.setName("AliveLabel");
        JLabel space = new JLabel("               ");
        setVisible(true);
        setLocationRelativeTo(null);


        JPanel buttonPanel = new JPanel();
        JToggleButton play = new JToggleButton("Play");
        play.setName("PlayToggleButton");
        JButton reset = new JButton("Reset");
        reset.setName("ResetButton");
        //buttonPanel.add(space);
        buttonPanel.add(play);
        buttonPanel.add(reset);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset.setName("Resetted");
            }
        });
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


        JPanel headPanel = new JPanel();
        headPanel.setAlignmentX(0.05f);
        headPanel.setAlignmentY(0.05f);
        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
        headPanel.add(gen);
        headPanel.add(space);
        headPanel.add(aliveL);

        Main.Grid grid = new Main.Grid();
        add(buttonPanel);
        add(headPanel);
        add(grid);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < lines; j++) {
                if (map[i][j] == 'O') {
                    grid.fillCell(i, j);
                }
            }
        }
    }
}


