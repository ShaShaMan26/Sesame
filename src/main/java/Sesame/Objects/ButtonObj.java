package Sesame.Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ButtonObj extends Obj {
    public enum SelectionState {
        INACTIVE, SELECTED, ACTIVE
    }
    protected SelectionState selectionState = SelectionState.INACTIVE;
    protected BufferedImage selectedSprite = null;
    protected BufferedImage activeSprite = null;

    protected ButtonObj() {}
    protected ButtonObj(int x, int y) {
        super(x, y);
    }
    protected ButtonObj(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }
    protected ButtonObj(BufferedImage sprite, BufferedImage selectedSprite, BufferedImage activeSprite) {
        super(sprite);
        this.selectedSprite = selectedSprite;
        this.activeSprite = activeSprite;
    }
    protected ButtonObj(int x, int y, BufferedImage sprite, BufferedImage selectedSprite, BufferedImage activeSprite) {
        super(x, y, sprite);
        this.selectedSprite = selectedSprite;
        this.activeSprite = activeSprite;
    }

    public void setSelectedSprite(BufferedImage selectedSprite) {
        this.selectedSprite = selectedSprite;
    }
    public void setActiveSprite(BufferedImage activeSprite) {
        this.activeSprite = activeSprite;
    }
    public void setSelectionState(SelectionState selectionState) {
        this.selectionState = selectionState;
    }

    public BufferedImage getSelectedSprite() {
        return selectedSprite;
    }
    public BufferedImage getActiveSprite() {
        return activeSprite;
    }
    public SelectionState getSelectionState() {
        return selectionState;
    }

    /**
     * Abstract method intended to house <code>ButtonObj</code>'s function when activated.
     */
    public abstract void activeEvent();

    /**
     * Calls <code>activeEvent</code> method if <code>selectionState</code> is <code>ACTIVE</code>.
     */
    @Override
    public void update() {
        if (selectionState.equals(SelectionState.ACTIVE)) {
            activeEvent();
        }
    }
    @Override
    public void paint(Graphics g) {
        BufferedImage storedSprite = sprite;
        switch (selectionState) {
            case SELECTED -> sprite = selectedSprite;
            case ACTIVE -> sprite = activeSprite;
        }

        if (sprite == null) {
            sprite = storedSprite;
        }

        super.paint(g);

        sprite = storedSprite;
    }
}
