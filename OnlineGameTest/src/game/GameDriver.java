package game;

public class GameDriver implements Runnable {

    private static final double TICK_RATE = 30;

    private boolean running;
    private Thread thread;

    public GameDriver() {

        running = false;

    }

    public void start() {

        if(running) return;

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public void stop() {

        if(!running) return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        long initialTime = System.nanoTime();
        double timeF = 1000000000 / TICK_RATE;
        double deltaF = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running) {

            long currentTime = System.nanoTime();
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if(deltaF >= 1) {

                //update game

                frames++;
                deltaF--;

            }

            if(System.currentTimeMillis() - timer > 1000) {

                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;

            }

        }

    }
}
