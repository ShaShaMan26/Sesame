package Mine.StateMachine;

public class GameLoop {
    public static double frameRate = 30;
    private State currentState = null;
    private boolean running = false;

    GameLoop(State startingState, double frameRate) {
        currentState = startingState;
        GameLoop.frameRate = frameRate;
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = frameRate;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                update();
                delta--;
            }
        }
    }

    public void update() {
        State tempState = currentState.progress();

        if (tempState != null) {
            currentState = tempState;
        }
    }

    public void stop() {
        running = false;
    }
}
