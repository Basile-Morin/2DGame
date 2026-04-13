package core;

public class GameLoop implements Runnable {
    private Thread gameThread;
    private final GamePanel panel;
    private final Game game;
    private boolean running;

    public GameLoop(GamePanel panel, Game game) {
        this.panel = panel;
        this.game = game;
    }

    public void startGameThread() {
        if (gameThread == null) {
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }



    @Override
    public void run() {
        long nextDrawTime = System.nanoTime();
        long drawInterval = (long) GameConfig.DRAWINTERVAL_NS;

        while (running) {
            game.update();
            panel.repaint();

            nextDrawTime += drawInterval;
            long remainingTime = nextDrawTime - System.nanoTime();

            if (remainingTime > 0) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(remainingTime / 1_000_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            } else {
                nextDrawTime = System.nanoTime();
            }
        }

        gameThread = null;
    }
}