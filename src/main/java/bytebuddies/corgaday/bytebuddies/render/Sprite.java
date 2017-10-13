package bytebuddies.corgaday.bytebuddies.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {

    private Bitmap[] bmp;
    private float x;
    private float y;
    private int frameCount;
    private int frame;
    private long timer;
    private int height;
    private int width;

    public Sprite(Bitmap[] bmp) {
        timer = 0;
        frame = 0;
        x = -100;
        y = -100;
    }

    public void draw(Canvas c) {
        c.drawBitmap(bmp[frame], x, y, null);
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void nextFrame() {
        if(!(frame == bmp.length - 1)) frame++;
        else frame = 0;
    }

    public void incrementPos(float fX, float fY) {
        this.x += fX;
        this.y += fY;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }

}