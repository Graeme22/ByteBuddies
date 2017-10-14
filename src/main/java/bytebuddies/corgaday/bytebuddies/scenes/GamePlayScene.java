package bytebuddies.corgaday.bytebuddies.scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import bytebuddies.corgaday.bytebuddies.util.Constants;
import bytebuddies.corgaday.bytebuddies.R;
import bytebuddies.corgaday.bytebuddies.util.Utilities;
import bytebuddies.corgaday.bytebuddies.engine.ButtonManager;
import bytebuddies.corgaday.bytebuddies.engine.Dimension;

import static bytebuddies.corgaday.bytebuddies.util.Utilities.map;
import static bytebuddies.corgaday.bytebuddies.util.Constants.FRAME;

class GamePlayScene implements Scene {
    private ButtonManager buttonManager;

    private Bitmap wood;
    private Bitmap bg;
    private Bitmap buttonBg;
    private Bitmap buttonBg2;

    GamePlayScene () {
        buttonManager = new ButtonManager();

        wood = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.wood), 16, 16, false);

        bg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.bg), 512, 256, false);

        buttonBg = buttonBg2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.button_bg), 32, 32, false);

        //LTRB
        Bitmap buttonActionA;
        Bitmap buttonActionB;
        Bitmap buttonActionAttack;

        buttonActionA = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.action_button_a), 32, 32, false);
        buttonActionB = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.action_button_b), 32, 32, false);
        buttonActionAttack = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.action_button_attack), 32, 32, false);

        buttonManager.addButton("actionA", new Rect(FRAME.left, FRAME.top, FRAME.left + 2 * FRAME.width / 16, FRAME.top + 2 * FRAME.height / 8), buttonActionA, true, new Dimension(32, 32));
        buttonManager.addButton("actionB", new Rect(FRAME.left, 2 * FRAME.height / 8, FRAME.left + 2 * FRAME.width / 16, FRAME.top + 4 * FRAME.height / 8), buttonActionB, true, new Dimension(32, 32));
        buttonManager.addButton("actionAttack", new Rect(FRAME.left, 6 * FRAME.height / 8, FRAME.left + 2 * FRAME.width / 16, FRAME.top + 8 * FRAME.height / 8), buttonActionAttack, true, new Dimension(32, 32));

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 1; i <= 16; i++) {
            canvas.save();
            canvas.rotate(90, ((int)(map(i, 1, 16, 0, FRAME.right - FRAME.width / 16) + (int)(map(i, 1, 16, FRAME.width / 16, FRAME.right))) / 2), (FRAME.top + FRAME.height + FRAME.bottom + FRAME.width / 16) / 2);
            canvas.drawBitmap(wood, new Rect(0, 0, 16, 16), new Rect(
                    (int)(map(i, 1, 16, 0, FRAME.right - FRAME.width / 16)),
                    FRAME.top + FRAME.height,
                    (int)(map(i, 1, 16, FRAME.width / 16, FRAME.right)),
                    FRAME.bottom + FRAME.width / 16
            ), null);
            canvas.restore();
        }

        canvas.drawBitmap(bg, new Rect(0, 0, 512, 256), new Rect(FRAME.left, FRAME.top, FRAME.right, FRAME.bottom), null);

        canvas.drawBitmap(buttonBg, new Rect(0, 0, 32, 32), new Rect(FRAME.left, FRAME.top, FRAME.left + FRAME.width / 8, FRAME.bottom), null);
        canvas.drawBitmap(buttonBg2, new Rect(0, 0, 32, 32), new Rect(FRAME.right - FRAME.width / 8, FRAME.top, FRAME.right, FRAME.bottom), null);

        buttonManager.draw(canvas);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        String button = buttonManager.receiveTouch(event);
        if (button != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    switch (button) {
                        case "actionA":
                            Utilities.print("actionA");
                            break;
                        case "actionB":
                            Utilities.print("actionB");
                            break;
                        case "actionTarget":
                            Utilities.print("actionTarget");
                            break;
                        case "actionAttack":
                            Utilities.print("actionAttack");
                            break;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }
}
