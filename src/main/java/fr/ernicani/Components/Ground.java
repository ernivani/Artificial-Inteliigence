package fr.ernicani.Components;

import fr.ernicani.Graphics.*;
import fr.ernicani.Graphics.Component;
import fr.ernicani.Util.Constants;

import java.awt.*;

public class Ground extends Component {

    @Override
    public void update(double dt) {
        if (!Game.getWindow().isInEditorMode) {
            LevelScene scene = (LevelScene)Game.getWindow().getCurrentScene();
            GameObject player = scene.player;
            if (player.transform.position.y + player.getComponent(BoxBounds.class).height >
                    gameObject.transform.position.y) {
                player.transform.position.y = Constants.GROUND_Y -
                        player.getComponent(BoxBounds.class).height;
            }
            gameObject.transform.position.x = scene.camera.position.x - Constants.CAMERA_OFFSET_X;
        } else {
            gameObject.transform.position.x = Game.getWindow().getCurrentScene().camera.position.x - Constants.CAMERA_OFFSET_X;
        }

    }
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawRect((int) gameObject.transform.position.x-10, (int) gameObject.transform.position.y, Constants.SCREEN_WIDTH+20, Constants.SCREEN_HEIGHT);
    }
}
