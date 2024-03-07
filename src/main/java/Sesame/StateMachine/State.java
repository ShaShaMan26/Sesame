package Sesame.StateMachine;

/**
 * <code>State</code> is an abstract class intended to be used in
 * conjunction with the <code>GameLoop</code> state machine class.
 * This class requires implementation of the <code>progress</code> method,
 * which should be home to the states update loop code.
 */
public abstract class State {
    /**
     * Runs <code>State</code>'s update loop code and returns a new <code>State</code>.
     * <p>
     *     A new <code>State</code> is returned in order to be fed to an instance of <code>GameLoop</code>.
     *     As such, a value of <code>null</code> should be returned while remaining in current <code>State</code>
     *     between loops.
     * </p>
     * @return new game <code>State</code>. <code>null</code> to remain in current state
     * (when used with <code>GameLoop</code>).
     */
    public abstract State progress();
}
