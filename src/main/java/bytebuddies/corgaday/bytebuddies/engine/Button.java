package bytebuddies.corgaday.bytebuddies.engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import bytebuddies.corgaday.bytebuddies.R;

import bytebuddies.corgaday.bytebuddies.util.Constants;


public class Button {
	private NinePatchDrawable bg;

    private String id;
    private Rect rectangle;
	private boolean iregular;
    private Paint paint;
    private Bitmap bitmap;
    private Dimension dimension;


    public Button (String id, Rect rectangle, boolean iregular, Paint paint) {
        this.id = id;
        this.rectangle = rectangle;
	    this.iregular = iregular;
        this.paint = paint;
    }

    public Button (String id, Rect rectangle, Bitmap bitmap, boolean iregular, @NonNull Dimension dimension) {
        this.id = id;
        this.rectangle = rectangle;
	    this.iregular = iregular;
        this.bitmap = bitmap;
        this.dimension = dimension;


	    bg = (NinePatchDrawable)Constants.RESOURCES.getDrawable(R.drawable.button_stretch);
	    bg.setFilterBitmap(false);
	    bg.setBounds(rectangle);
    }

    String receiveTouch (MotionEvent event) {
        if (rectangle.contains((int)event.getX(), (int)event.getY()))
            return id;
        return null;
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        if (bitmap != null && dimension != null) {
	        if (!iregular) {
		        bg.draw(canvas);

		        canvas.drawBitmap(bitmap, new Rect(1, 1, dimension.width, dimension.height), rectangle, paint);
	        } else {
	        }
        } else {
            canvas.drawRect(rectangle, paint);
        }
    }
}
