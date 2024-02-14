package Mine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputManager implements KeyListener {
    private final ArrayList<Integer> pressedKeys = new ArrayList<>();
    private final ArrayList<Integer> invalidKeys = new ArrayList<>();

    public boolean isKeyPressed(Integer key) {
        return pressedKeys.contains(key);
    }

    public void invalidateKeys(Integer key) {
        if (!invalidKeys.contains(key)) {
            invalidKeys.add(key);
            pressedKeys.remove(key);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();

        if (!pressedKeys.contains(key) && !invalidKeys.contains(key)) {
            pressedKeys.add(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Integer key = e.getKeyCode();

        invalidKeys.remove(key);
        pressedKeys.remove(key);
    }
}
