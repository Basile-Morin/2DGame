package core;

public class GameLoop implements Runnable{
    Thread gameThread;
    GamePanel panel;
    Game game;


    public GameLoop(GamePanel panel, Game game){
        this.panel=panel;
        this.game =game;
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double nextDrawTime = System.currentTimeMillis()+ GameConfig.drawInterval;
        double remainingTime;

        while(gameThread!=null){
//            long drawStart = System.nanoTime();
            game.update();

            panel.repaint();
//            long drawEnd = System.nanoTime();
//            long drawTime = drawEnd-drawStart;
//            System.out.println(drawTime);

            try
            {
                remainingTime = nextDrawTime - System.currentTimeMillis();
                if(remainingTime<0){remainingTime=0;}
                Thread.sleep((long) remainingTime);
                nextDrawTime += GameConfig.drawInterval;

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }




}
