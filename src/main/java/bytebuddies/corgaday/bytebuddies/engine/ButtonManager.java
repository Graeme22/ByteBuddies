package bytebuddies.corgaday.bytebuddies.engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import java.util.ArrayList;

public class ButtonManager {
    private ArrayList<Button> buttons;

    public ButtonManager() {
        buttons = new ArrayList<>();
    }

    public void addButton(String id, Rect rectangle, Bitmap bitmap, boolean iregular, @NonNull Dimension dimension) {
        buttons.add(new Button(id, rectangle, bitmap, iregular, dimension));
    }

    public String receiveTouch(MotionEvent event) {
        for (Button button : buttons) {
            if (button.receiveTouch(event) != null)
                return button.receiveTouch(event);
        }
        return null;
    }

    public void update() {
        for (Button button : buttons) {
            button.update();
        }
    }

    public void draw(Canvas canvas) {
        for (Button button : buttons) {
            button.draw(canvas);
        }
    }
}
