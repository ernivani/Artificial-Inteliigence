package fr.ernicani.Graphics;

import fr.ernicani.Components.*;
import fr.ernicani.Util.Constants;
import fr.ernicani.Util.Vector2;
import fr.ernicani.dataStructure.Transform;

import java.awt.*;

public class LevelScene extends Scene{

    static LevelScene currentScene;
    public GameObject player;
    public LevelScene(String name) {
        super.Scene(name);
    }

    public static LevelScene getScene() {
        if (currentScene == null) {
            currentScene = new LevelScene("LevelEditorScene");
        }
        return currentScene;
    }
    @Override
    public void init() {
        player = new GameObject("testObj", new Transform(new Vector2(500, 350)));
        Spritesheet layerOne = new Spritesheet("assets/player/layerOne.png",
                42, 42, 2, 13, 13*5);
        Spritesheet layerTwo = new Spritesheet("assets/player/layerTwo.png",
                42, 42, 2, 13, 13*5);
        Spritesheet layerThree = new Spritesheet("assets/player/layerThree.png",
                42, 42, 2, 13, 13*5);
        Player playerComp = new Player(layerOne.sprites.get(0), layerTwo.sprites.get(0), layerThree.sprites.get(0), Color.RED, Color.GREEN);
        player.addComponent(playerComp);
        player.addComponent(new Rigidbody(new Vector2(Constants.PLAYER_SPEED, 0)));
        player.addComponent(new BoxBounds(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));

        GameObject ground;

        ground = new GameObject("ground", new Transform(
                new Vector2(0, Constants.GROUND_Y)));
        ground.addComponent(new Ground());

        addGameObject(player);
        addGameObject(ground);


    }

    @Override
    public void update(double dt) {

        if (player.transform.position.x - camera.position.x > Constants.CAMERA_OFFSET_X) {
            camera.position.x = player.transform.position.x - Constants.CAMERA_OFFSET_X;
        }
        camera.position.y = player.transform.position.y - Constants.CAMERA_OFFSET_Y;

        if (camera.position.y > Constants.CAMERA_OFFSET_GROUND_Y) {
            camera.position.y = Constants.CAMERA_OFFSET_GROUND_Y;
        }

        for (GameObject g : gameObjects) {
            g.update(dt);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Constants.BG_COLOR);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        renderer.render(g2);

    }
}
