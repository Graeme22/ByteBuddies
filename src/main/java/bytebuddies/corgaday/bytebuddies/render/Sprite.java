package bytebuddies.corgaday.bytebuddies.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private Bitmap animation;
    private int x;
    private int y;
    private Rect r;
    private int fps;
    private int frameCount;
    private int frame;
    private long timer;
    private int height;
    private int width;

    public Sprite(Bitmap bmp) {
        r = new Rect(0, 0, 0, 0);
        timer = 0;
        frame = 0;
        x = -100;
        y = -100;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int i) {
        this.y = i;
    }

}