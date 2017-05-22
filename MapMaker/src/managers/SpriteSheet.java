package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {

    private ArrayList<BufferedImage> sprites;

    public SpriteSheet(ArrayList<BufferedImage> sprites) {
        this.sprites = new ArrayList<>(sprites);
    }

    public int count() {
        return sprites.size();
    }

    public BufferedImage getSprite(int frame) {
        return sprites.get(frame);
    }

}