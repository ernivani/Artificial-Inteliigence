package fr.ernicani.Graphics;

import fr.ernicani.Util.Constants;
import fr.ernicani.Util.Time;

import javax.swing.JFrame;
import java.awt.*;

public class Game extends JFrame implements Runnable {
    private static Game window = null;
    private static boolean isRunning = true;

    public static KL keyListener = new KL();
    public static ML mouseListener = new ML();
    private Scene currentScene = null;
    private Image doubleBufferImage = null;
    private Graphics doubleBufferGraphics = null;
    public boolean isInEditorMode = false;


    public Game() {
        this.setTitle(Constants.SCREEN_TITLE);
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
    }

    public static Game getWindow() {
        if (window == null) {
            window = new Game();
        }
        return window;
    }

    public void init() {
        changeScene(0);
    }
    public void changeScene(int scene) {
        switch (scene) {
            case 0:
                currentScene = new LevelEditorScene("LevelEditorScene");
                currentScene.init();

                isInEditorMode = true;
                break;
            case 1:
                currentScene = new LevelScene("LevelScene");
                currentScene.init();
                isInEditorMode = false;
                break;
            default:
                currentScene = null;
                break;
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
    private void update(double deltaTime) {
        if (currentScene == null) return;
        currentScene.update(deltaTime);
        draw((getGraphics()));
    }

    public void draw (Graphics g) {
        if (doubleBufferImage == null) {
            doubleBufferImage = createImage(getWidth(), getHeight());
            doubleBufferGraphics = doubleBufferImage.getGraphics();
        }

        renderOffScreen(doubleBufferGraphics);

        g.drawImage(doubleBufferImage, 0, 0, getWidth(), getHeight(),null);
    }

    public void renderOffScreen(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        currentScene.draw(g2);
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        try {
            while(isRunning) {
                double time = Time.getTime();
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;

                update(deltaTime);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
