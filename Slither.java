package Snake;

import java.util.LinkedList;

public class Slither extends Sprites {

    public static int GRIDHEIGHT = 64;
    public static int GRIDWIDTH = 64;
    public static int GRIDSIZE = GRIDHEIGHT * GRIDWIDTH;

    public static int apple = (int)(GRIDSIZE * Math.random());

    public static char turn = 'R';
    public static boolean alive = true;

    public static LinkedList<Integer> snake = new LinkedList<Integer>();
    public static LinkedList<Character> direction = new LinkedList<Character>();

    public Slither() {
        initSlither();
    }

    private void initSlither() {
        snake.add(0);
        direction.add('R');
        snake.add(1);
        direction.add('R');
        snake.add(2);
        direction.add('R');
        snake.add(3);
        direction.add('R');
        snake.add(4);
        direction.add('R');
        snake.add(5);
        direction.add('R');
    }

    public static void newApple() {
        do {
            apple = (int)(GRIDSIZE * Math.random());
        } while (appleCheck());
    }

    private static boolean appleCheck() {
        for (int i : snake) {
            if (i == apple) {
                return true;
            }
        }
        return false;
    }

    private static void snakeCheck(int head) {
        for (int i = snake.size(); i > 0;) {
            i--;
            if (snake.get(i) == head) {
                alive = false;
            }
        }
    }

    public static int move() {
        int previous = snake.getLast();
        int current = previous;
        switch (turn) {
            case 'L':
                {
                    current -= 1;
                    if (current % GRIDWIDTH > previous % GRIDWIDTH) {
                        alive = false;
                    }
                }
                break;
            case 'R':
                {
                    current += 1;
                    if (current % GRIDWIDTH < previous % GRIDWIDTH) {
                        alive = false;
                    }
                }
                break;
            case 'U':
                {
                    current -= GRIDWIDTH;
                    if (current < 0) {
                        alive = false;
                    }
                }
                break;
            case 'D':
                {
                    current += GRIDWIDTH;
                    if (current > GRIDSIZE) {
                        alive = false;
                    }
                }
                break;
        }
        snakeCheck(current);
        if (alive) {
            direction.add(turn);
            snake.add(current);
        }
        int tail = snake.getFirst();

        return tail;
    }

}
