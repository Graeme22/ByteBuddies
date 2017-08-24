package bytebuddies.corgaday.bytebuddies.render;

import android.graphics.Canvas;

public interface IDraw {

    void onInitialize();
    void onDraw(Canvas c);
    void onUpdate(long time);

}