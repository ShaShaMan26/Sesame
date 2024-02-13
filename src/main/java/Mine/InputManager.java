package Mine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputManager implements KeyListener {
    private final ArrayList<Integer> pressedKeys = new ArrayList<>();

    public boolean isKeyPressed(Integer key) {
        return pressedKeys.contains(key);
    }

    @Override
    public void keyTyped(KeyEvent e) {

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
