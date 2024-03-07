package Sesame.StateMachine;

import Sesame.GameWindow;

/**
 * <code>GameLoop</code> provides a simple game loop/state machine that is easy to use and manage.
 * Should be used in conjunction with instances of the <code>State</code> class.
 */
public class GameLoop {
    public static double frameRate = 30;
    private State currentState;
    private boolean running = true;

    /**
     * Constructs a new <code>GameLoop</code>.
     * @param startingState <code>State</code> by which the game loop will start in.
     *                      <p>
     *                          <STRONG>WARNING:</STRONG> cannot be a value of <code>null</code>
     *                      </p>
     * @param frameRate <code>double</code> value determining the number of times
     *                  that <code>GameLoop</code> runs per second.
     *                  <p>
     *                      <STRONG>Ex:</STRONG> a value of <code>60.0</code> will
     *                      run the <code>GameLoop</code> at 60fps
     *                  </p>
     */
    public GameLoop(State startingState, double frameRate) {
        currentState = startingState;
        GameLoop.frameRate = frameRate;
    }

    /**
     * Starts game loop.
     * @param gameWindow parent <code>GameWindow</code>
     */
    public void run(GameWindow gameWindow) {
        long lastTime = System.nanoTime();
        double amountOfTicks = frameRate;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                gameWindow.update();
                update();
                delta--;
            }
        }
    }

    /**
     * Calls current <code>State</code>'s <code>progress</code> method
     * and augments <code>currentState</code> if a new <code>State</code> is returned.
     */
    public void update() {
        State tempState = currentState.progress();

        if (tempState != null) {
            currentState = tempState;
        }
    }

    /**
     * Ends game loop.
     */
    public void stop() {
        running = false;
    }
}
