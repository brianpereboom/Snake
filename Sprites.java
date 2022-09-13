package Snake;

import java.util.HashMap;
import javax.swing.ImageIcon;

public class Sprites {
    protected static HashMap<String, ImageIcon> sprites = new HashMap<String, ImageIcon>();

    public Sprites() {
        initSprites();
    }

    private void initSprites() {
        String path = "/Snake/Assets/";
        sprites.put("Apple", createImageIcon(path + "apple.png", "Apple"));
        sprites.put("DL", createImageIcon(path + "down_left.png", "DL"));
        sprites.put("DR", createImageIcon(path + "down_right.png", "DR"));
        sprites.put("Empty", createImageIcon(path + "empty.png", "Empty"));
        sprites.put("Head D", createImageIcon(path + "head_down.png", "Head D"));
        sprites.put("Head L", createImageIcon(path + "head_left.png", "Head L"));
        sprites.put("Head R", createImageIcon(path + "head_right.png", "Head R"));
        sprites.put("Head U", createImageIcon(path + "head_up.png", "Head U"));
        sprites.put("Horizontal", createImageIcon(path + "horizontal.png", "Horizontal"));
        sprites.put("UL", createImageIcon(path + "up_left.png", "UL"));
        sprites.put("UR", createImageIcon(path + "up_right.png", "UR"));
        sprites.put("Tail D", createImageIcon(path + "tail_down.png", "Tail D"));
        sprites.put("Tail L", createImageIcon(path + "tail_left.png", "Tail L"));
        sprites.put("Tail R", createImageIcon(path + "tail_right.png", "Tail R"));
        sprites.put("Tail U", createImageIcon(path + "tail_up.png", "Tail U"));
        sprites.put("Vertical", createImageIcon(path + "vertical.png", "Vertical"));
    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void resizeSprites(int height, int width) {
        for (String i : sprites.keySet()) {
            sprites.put(i, Resize.resize(sprites.get(i), height, width));
        }
    }

    public static ImageIcon getSprite(String description) {
        return sprites.get(description);
    }

}
