package bytebuddies.corgaday.bytebuddies.scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import bytebuddies.corgaday.bytebuddies.engine.MainActivity;


public class SceneManager {
    private static ArrayList<Scene> scenes = new ArrayList<>();
    private static int ACTIVE_SCENE;

    public static final int MAIN_MENU_SCENE = 0;
    public static final int GAME_PLAY_SCENE = 1;


    public SceneManager (MainActivity ma) {
        scenes.add(new MainMenuScene(ma));
        scenes.add(new GamePlayScene());
        ACTIVE_SCENE = 0;
        scenes.get(ACTIVE_SCENE).activate();
    }

    public static void setScene (int scene) {
        scenes.get(scene).deactivate();
        ACTIVE_SCENE = scene;
        scenes.get(scene).activate();
    }

    public void receiveTouch (MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void draw (Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public void update () {
        scenes.get(ACTIVE_SCENE).update();
    }
}
