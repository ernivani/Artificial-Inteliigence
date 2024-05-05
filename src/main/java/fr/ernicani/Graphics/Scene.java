package fr.ernicani.Graphics;

import fr.ernicani.Util.Vector2;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    String name;
    public Camera camera;
    List<GameObject> gameObjects;
    Renderer renderer;

    public void Scene(String name) {
        this.name = name;
        this.camera = new Camera(new Vector2());
        this.gameObjects = new ArrayList<>();
        this.renderer = new Renderer(camera);
    }
    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
        renderer.submit(obj);
    }

    public abstract void init();
    public abstract void update(double dt);
    public abstract void draw(Graphics2D g2);


}