package core;

import java.util.concurrent.locks.LockSupport;

public class GameLoop implements Runnable {
    private static final long MAX_ELAPSED_TIME_NS = 250_000_000L;

    private Thread gameThread;
    private final GamePanel panel;
    private final Game game;
    private volatile boolean running;

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
        long previousTime = System.nanoTime();
        long accumulator = 0L;

        while (running) {
            long currentTime = System.nanoTime();
            long elapsedTime = Math.min(currentTime - previousTime, MAX_ELAPSED_TIME_NS);
            previousTime = currentTime;
            accumulator = Math.min(accumulator + elapsedTime, MAX_ELAPSED_TIME_NS);

            boolean updated = false;
            while (accumulator >= GameConfig.UPDATE_INTERVAL_NS && running) {
                game.update(GameConfig.FIXED_DELTA_SECONDS);
                accumulator -= GameConfig.UPDATE_INTERVAL_NS;
                updated = true;
            }

            if (updated) {
                panel.repaint();
            }

            if (Thread.currentThread().isInterrupted()) {
                break;
            }

            long remainingTime = GameConfig.UPDATE_INTERVAL_NS - accumulator;
            if (remainingTime > 0) {
                LockSupport.parkNanos(remainingTime);
            }
        }

        gameThread = null;
    }
}
