package Sesame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * The <code>InputManager</code> class manages access to information regarding the user's keyboard.
 * Indented to act as a static helper class for <code>GameWindow</code>.
 * <p>
 *     <STRONG>NOTE:</STRONG> keeps a queue of typed keys. Collection must be started,
 *     stopped, and the queue must be emptied manually.
 * </p>
 */
public class InputManager implements KeyListener {
    private final ArrayList<Integer> pressedKeys = new ArrayList<>();
    private final ArrayList<Integer> typedKeys = new ArrayList<>();
    private boolean recording = false;

    /**
     * Indicates whether <code>InputManager</code> is currently collecting typed keys.
     * @return <code>true</code> if collecting typed keys; <code>false</code> if not
     */
    public boolean isRecording() {
        return recording;
    }
    /**
     * Opens <code>InputManager</code> to collecting typed keys.
     */
    public void startRecording() {
        recording = true;
    }
    /**
     * Closes <code>InputManager</code> to collecting typed keys.
     */
    public void stopRecording() {
        recording = false;
    }

    /**
     * Indicates whether a designated keyboard key is currently pressed.
     * <p>
     *     <STRONG>WARNING:</STRONG> only works with keys represented by a <code>keyChar</code>.
     * </p>
     * @param key <code>Integer</code> value correlating to a <code>keyChar</code>
     * @return <code>true</code> if <code>key</code> is pressed; <code>false</code> if not
     */
    public boolean isKeyPressed(Integer key) {
        return pressedKeys.contains(key);
    }

    /**
     * Indicates whether a designated keyboard key has been typed (as understood by user's OS).
     * <p>
     *     <STRONG>WARNING:</STRONG> only works with keys represented by a <code>keyChar</code>.
     * </p>
     * @param key <code>Integer</code> value correlating to a <code>keyChar</code>
     * @return <code>true</code> if <code>key</code> has been typed; <code>false</code> if not
     */
    public boolean isKeyTyped(Integer key) {
        return typedKeys.contains(key);
    }
    /**
     * Accesses all collected typed keys.
     * @return <code>ArrayList</code> of all collected typed keys
     */
    public ArrayList<Integer> getTypedKeys() {
        return typedKeys;
    }
    /**
     * Empties the queue of collected typed key.
     */
    public void clearTypedKeys() {
        typedKeys.clear();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (recording) {
            Integer key = (int) e.getKeyChar();

            typedKeys.add(key);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();

        if (!pressedKeys.contains(key)) {
            pressedKeys.add(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Integer key = e.getKeyCode();

        pressedKeys.remove(key);
    }
}
