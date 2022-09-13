package Snake;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.util.concurrent.TimeUnit;

public class Game extends Slither implements KeyListener, WindowListener {

    private static int WINDOW_HEIGHT = 1024;
    private static int WINDOW_WIDTH = 1024;
    private static int cell_width = WINDOW_WIDTH / GRIDWIDTH;
    private static int cell_height = WINDOW_HEIGHT / GRIDHEIGHT;

    private static JFrame frame = new JFrame("Snake");
    private static JLabel[] cells;

    private static boolean windowOpen = true;
    
    public Game() {
        initComponents();
    }

    private void initComponents() {

        frame.addKeyListener(this);
        frame.addWindowListener(this);
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(GRIDHEIGHT, GRIDWIDTH);
        panel.setLayout(layout);
        cells = new JLabel[GRIDSIZE];
        for (int i = 0; i < GRIDSIZE; i++) {
            cells[i] = new JLabel();
            cells[i].setSize(cell_width, cell_height);
            panel.add(cells[i]);
        }
        frame.add(panel);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        resizeSprites(cell_height, cell_width);
    }

    public static void setSprite(int coordinates, String description) {
        cells[coordinates].setIcon(getSprite(description));
    }

    public void keyPressed (KeyEvent arg0) {
        int keyCode = arg0.getKeyCode();
        if ((keyCode == KeyEvent.VK_UP || keyCode == 'W') && direction.getLast() != 'D') {
            turn = 'U';
        } else if ((keyCode == KeyEvent.VK_LEFT || keyCode == 'A') && direction.getLast() != 'R') {
            turn = 'L';
        } else if ((keyCode == KeyEvent.VK_DOWN || keyCode == 'S') && direction.getLast() != 'U') {
            turn = 'D';
        } else if ((keyCode == KeyEvent.VK_RIGHT || keyCode == 'D') && direction.getLast() != 'L') {
            turn = 'R';
        }
    }
    public void keyTyped(KeyEvent arg0) {}
    public void keyReleased(KeyEvent arg0) {}

    public void windowClosing(WindowEvent arg0) {}
    public void windowDeiconified(WindowEvent arg0) {}
    public void windowOpened(WindowEvent arg0) {}
    public void windowActivated(WindowEvent arg0) {}
    public void windowClosed(WindowEvent arg0) {
        windowOpen = false;
    }
    public void windowDeactivated(WindowEvent arg0) {}
    public void windowIconified(WindowEvent arg0) {}

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new Game();
        int wait = 100;
        setSprite(apple, "Apple");
        for (int i = 1; i < snake.size() - 1; i++) {
            setSprite(i, "Horizontal");
        }
        char previousDirection = direction.get(direction.size() - 2);
        do {
            int tail = move();
            setSprite(tail, "Empty");
            if (snake.getLast() == apple) {
                newApple();
                setSprite(apple, "Apple");
                wait = Math.max(0, wait - 1);
            } else {
                direction.removeFirst();
                snake.removeFirst();
            }
            setSprite(snake.getLast(), "Head " + direction.getLast());
            if (previousDirection == direction.getLast()) {
                if (direction.getLast() == 'U' || direction.getLast() == 'D') {
                    setSprite(snake.get(snake.size() - 2), "Vertical");
                } else {
                    setSprite(snake.get(snake.size() - 2), "Horizontal");
                }
            } else if (direction.getLast() == 'U' || direction.getLast() == 'D') {
                setSprite(snake.get(snake.size() - 2), String.valueOf(direction.getLast()) + ((previousDirection == 'R') ? "L" : "R"));
            } else {
                setSprite(snake.get(snake.size() - 2), ((previousDirection == 'U') ? "D" : "U") + String.valueOf(direction.getLast()));
            }
            setSprite(snake.getFirst(), "Tail " + direction.get(1));
            previousDirection = direction.getLast();
            try {
                TimeUnit.MILLISECONDS.sleep(wait);
            } catch (Exception e) {}
        } while (windowOpen && alive);
        
    }
}