package core;

import javax.swing.*;
import java.awt.*;

public final class GamePanel extends JPanel {
Game game;
    public GamePanel(Game game){
        this.setPreferredSize(new Dimension(GameConfig.screenWidth, GameConfig.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        //this.addKeyListener();
        this.setFocusable(true);
        this.game=game;

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        game.render(g2);

        g2.dispose();
    }
}
