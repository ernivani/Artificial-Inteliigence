package fr.ernicani.Components;

import fr.ernicani.Graphics.Component;
import fr.ernicani.Graphics.Game;
import fr.ernicani.Util.Constants;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CameraControls extends Component {
    public float prevMx, prevMy;

    public CameraControls() {
        this.prevMx = 0.0f;
        this.prevMy = 0.0f;
    }

    @Override
    public void update(double dt) {
        Game.getWindow();
        if (Game.mouseListener.mousePressed && Game.mouseListener.mouseButton == MouseEvent.BUTTON2) {
            float dx = (Game.mouseListener.x + Game.mouseListener.dx - prevMx);
            float dy = (Game.mouseListener.y + Game.mouseListener.dy - prevMy);

            System.out.println(Game.mouseListener.x + " " + Game.mouseListener.y);

            System.out.println(Game.getWindow().getCurrentScene().camera.position.x + " " + Game.getWindow().getCurrentScene().camera.position.y);
            System.out.println(dx + " " + dy);

            Game.getWindow().getCurrentScene().camera.position.x -= dx;
            Game.getWindow().getCurrentScene().camera.position.y -= dy;

        }

        prevMx = Game.mouseListener.x + Game.mouseListener.dx;
        prevMy = Game.mouseListener.y + Game.mouseListener.dy;
    }

}
