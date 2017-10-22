package bytebuddies.corgaday.bytebuddies.scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene {
    void draw(Canvas canvas);
    void update();

    void receiveTouch(MotionEvent event);

    void activate();
    void deactivate();
}
