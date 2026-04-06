package core;

import core.input.InputListener;

import javax.swing.*;

public final class Main {
    public static void main(String[] args) {

        InputListener il = new InputListener();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Jeu");

        Game game = new Game(il.getInputState());
        game.initialize();

        GamePanel gamePanel = new GamePanel(game);
        GameLoop gameLoop = new GameLoop(gamePanel,game);
        gamePanel.addKeyListener(il);

        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gamePanel.requestFocusInWindow();

        gameLoop.startGameThread();
    }
}