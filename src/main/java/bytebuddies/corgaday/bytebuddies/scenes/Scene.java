package etbcor.bluetoothgame.scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Ethan on 2/27/17.
 */

public interface Scene {
    void draw(Canvas canvas);
    void update();

    void receiveTouch(MotionEvent event);

    void activate();
    void deactivate();
}
