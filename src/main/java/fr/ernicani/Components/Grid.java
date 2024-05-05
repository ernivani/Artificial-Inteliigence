package fr.ernicani.Components;

import fr.ernicani.Graphics.Camera;
import fr.ernicani.Graphics.Component;
import fr.ernicani.Graphics.Game;
import fr.ernicani.Util.Constants;

import java.awt.*;
import java.awt.geom.Line2D;

public class Grid extends Component {

    Camera camera;
    public int gridWidth, gridHeight;
    private int numYLines = 31;
    private int numXLines = 31;
    public Grid() {
        this.camera = Game.getWindow().getCurrentScene().camera;
        this.gridWidth = Constants.TILE_WIDTH;
        this.gridHeight = Constants.TILE_HEIGHT;
    }

    @Override
    public void update(double dt) {
        this.camera = Game.getWindow().getCurrentScene().camera;
    }


    @Override
    public void draw(Graphics2D g2) {

        g2.setStroke(new BasicStroke(1f));
        g2.setColor(Color.GRAY);

        float bottom = Math.min(Constants.GROUND_Y - camera.position.y, Constants.SCREEN_HEIGHT);
        float startX = (float) Math.floor(camera.position.x / gridWidth) * gridWidth - camera.position.x ;
        float startY = (float) Math.floor(camera.position.y / gridHeight) * gridHeight - camera.position.y;

        for (int i = 0; i < numYLines; i++) {
            g2.draw(new Line2D.Float(startX, 0, startX, bottom));
            startX += gridWidth;
        }

        for (int i = 0; i < numXLines; i++) {
            if (camera.position.y + startY < Constants.GROUND_Y) {
                g2.draw(new Line2D.Float(0, startY, Constants.SCREEN_WIDTH, startY));
                startY += gridHeight;
            }
        }

    }
}
