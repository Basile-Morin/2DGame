package core;

import javax.swing.*;

public final class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Jeu");

        Game game = new Game();
        game.initialize();

        GamePanel gamePanel = new GamePanel(game);
        GameLoop gameLoop = new GameLoop(gamePanel,game);

        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gamePanel.requestFocusInWindow();

        gameLoop.startGameThread();
    }
}