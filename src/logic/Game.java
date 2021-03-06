package logic;

import gui.GameGUI;
import logic.objects.active.Hero;
import logic.world.EnviromentCollection;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Game implements Runnable {
    private boolean pause = true;
    private Hero hero;
    private EnviromentCollection environment;
    private HashMap<Integer, Boolean> keyMaps;
    public static Game game;
    private int level = 1;

    public static final int SIZE = GameGUI.gameGUI.getHeight() / 7;
//public static final int SIZE = 32;
    public Game() {
        game = this;
    }

    public void init() {
        keyMaps = new HashMap<>();
//        keyMaps.put((int)'w', false);
        keyMaps.put((int)'a', false);
//        keyMaps.put((int)'s', false);
        keyMaps.put((int)'d', false);
        keyMaps.put((int) ' ', false);
        hero = new Hero();
        environment = new EnviromentCollection(level);
    }

    public void unpause() {
        pause = false;
    }

    public void pause() {
        pause = true;
    }


    public void setKeyStatus(int num, boolean status) {
        if ('w' == num || 'W' == num || 'ц' == num || 'Ц' == num) {
            keyMaps.put((int) 'w', status);
        }

        if ('a' == num || 'A' == num || 'Ф' == num || 'ф' == num) {
            keyMaps.put((int) 'a', status);
        }

        if ('s' == num || 'S' == num || 'ы' == num || 'Ы' == num) {
            keyMaps.put((int) 's', status);
        }

        if ('d' == num || 'D' == num || 'в' == num || 'В' == num) {
            keyMaps.put((int) 'd', status);
        }

        if (num == ' '){
            keyMaps.put((int)' ', status);
        }
    }

    public void updateAll() {
        hero.update();
        environment.update();
        //todo: world update
    }

    @Override
    public void run() {
        while (true) {
            while (!isPause()) {
                try {
                    long time = System.currentTimeMillis();
                    updateAll();
                    //todo: correct timeout
                    TimeUnit.MILLISECONDS.sleep(16 - (System.currentTimeMillis() - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isPause() {
        return pause;
    }
    public void setPause(boolean pause) {
        this.pause = pause;
    }
    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    public EnviromentCollection getEnvironment() {
        return environment;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public HashMap<Integer, Boolean> getKeyMaps() {
        return keyMaps;
    }
    public void setKeyMaps(HashMap<Integer, Boolean> keyMaps) {
        this.keyMaps = keyMaps;
    }
}
