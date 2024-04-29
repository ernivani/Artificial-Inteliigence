package fr.ernicani.Components;

import fr.ernicani.Graphics.Component;
import fr.ernicani.Graphics.GameObject;
import fr.ernicani.Graphics.LevelEditorScene;
import fr.ernicani.Util.Constants;

import java.awt.*;

public class Ground extends Component {

    @Override
    public void update(double dt) {
        GameObject player = LevelEditorScene.getScene().player;
        if (player.transform.position.y + player.getComponent(BoxBounds.class).height >
                gameObject.transform.position.y) {
            player.transform.position.y = Constants.GROUND_Y - player.getComponent(BoxBounds.class).height;
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawRect((int) gameObject.transform.position.x-10, (int) gameObject.transform.position.y, Constants.SCREEN_WIDTH+20, Constants.SCREEN_HEIGHT);
    }
}
