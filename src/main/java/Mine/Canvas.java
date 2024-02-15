package Mine;

import Mine.Objects.Obj;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Canvas extends JPanel {
    private Color bgColor = Color.WHITE;
    private BufferedImage bgImg = null;
    public static Dimension defDim = new Dimension(640, 480);
    private final ArrayList<Obj>[] layers = new ArrayList[5];

    public Canvas() {
        this.setLayout(null);
        this.setSize(defDim);

        for (int i = 0; i < layers.length; i++) {
            layers[i] = new ArrayList<>();
        }
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
    public void setBgImg(BufferedImage bgImg) {
        this.bgImg = bgImg;
    }

    public Color getBgColor() {
        return bgColor;
    }
    public BufferedImage getBgImg() {
        return bgImg;
    }

    public void changeObjLayer(Obj obj, int newLayer) {
        if (newLayer > 4 || newLayer < 0) {
            throw new IllegalArgumentException("Value of layer must be between 0 and 4 (inclusive).");
        }

        for (ArrayList<Obj> layer : layers) {
            if (layer.contains(obj)) {
                layer.remove(obj);
                this.add(obj, newLayer);
            }
        }
    }
    public void add(Obj obj) {
        layers[0].add(obj);
    }
    public void add(Obj obj, int layer) {
        if (layer > 4 || layer < 0) {
            throw new IllegalArgumentException("Value of layer must be between 0 and 4 (inclusive).");
        }
        layers[layer].add(obj);
    }
    public void clear() {
        for (ArrayList<Obj> layer : layers) {
            layer.clear();
        }
    }


    public void update() {
        for (ArrayList<Obj> layer : layers) {
            for (Obj obj : layer) {
                obj.update();
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        // make scale <3
        g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), null);

        for (ArrayList<Obj> layer : layers) {
            for (Obj obj : layer) {
                obj.paint(g);
            }
        }
    }
}
