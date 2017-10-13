package etbcor.bluetoothgame.ai;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;


public class Tower {
    private Rect rectangle;
    private int type;
    private Drawable image;

    private long initTime;

    public static final int TYPE_ARROW = 1;
    public static final int TYPE_BARRACKS = 1;

    public Tower (Rect rectangle, int type) {
        this.rectangle = rectangle;
        this.type = type;
        initTime = System.currentTimeMillis();
        if (this.type == TYPE_ARROW) {
//            image = ContextCompat.getDrawable(Constants.CURRENT_CONTEXT, R.drawable.tower_arrow);
        } else if (this.type == TYPE_BARRACKS) {

        }
        image.setBounds(rectangle);
    }

    public void update () {

    }

    public void draw (Canvas canvas) {
        image.draw(canvas);
    }
}
