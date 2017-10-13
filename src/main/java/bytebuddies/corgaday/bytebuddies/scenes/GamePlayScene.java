package etbcor.bluetoothgame.scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import etbcor.bluetoothgame.util.Constants;
import etbcor.bluetoothgame.R;
import etbcor.bluetoothgame.ai.TowerManager;
import etbcor.bluetoothgame.util.Utility;
import etbcor.bluetoothgame.engine.ButtonManager;
import etbcor.bluetoothgame.engine.Dimension;

import static etbcor.bluetoothgame.util.Utility.map;
import static etbcor.bluetoothgame.util.Constants.FRAME;

class GamePlayScene implements Scene {
    private ButtonManager buttonManager;
    private TowerManager towerManager;

    private Bitmap wood;
    private Bitmap bg;
    private Bitmap buttonBg;
    private Bitmap buttonBg2;
    private Bitmap mainTower;

    private Bitmap heroWhite;

    GamePlayScene () {
        buttonManager = new ButtonManager();
        towerManager = new TowerManager();

        wood = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.wood), 16, 16, false);

        bg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.bg), 512, 256, false);

        buttonBg = buttonBg2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.button_bg), 32, 32, false);

        heroWhite = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.hero_white), 32, 32, false);

        mainTower = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.main_tower), 32, 32, false);

        //LTRB
        Bitmap buttonActionA;
        Bitmap buttonActionB;
        Bitmap buttonActionTarget;
        Bitmap buttonActionAttack;

        buttonActionA = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.action_button_a), 32, 32, false);
        buttonActionB = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.action_button_b), 32, 32, false);
        buttonActionTarget = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.action_button_target), 32, 32, false);
        buttonActionAttack = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.action_button_attack), 32, 32, false);

        buttonManager.addButton("actionA", new Rect(FRAME.left, FRAME.top, FRAME.left + 2 * FRAME.width / 16, FRAME.top + 2 * FRAME.height / 8), buttonActionA, true, new Dimension(32, 32));
        buttonManager.addButton("actionB", new Rect(FRAME.left, 2 * FRAME.height / 8, FRAME.left + 2 * FRAME.width / 16, FRAME.top + 4 * FRAME.height / 8), buttonActionB, true, new Dimension(32, 32));
        buttonManager.addButton("actionTarget", new Rect(FRAME.left, 4 * FRAME.height / 8, FRAME.left + 2 * FRAME.width / 16, FRAME.top + 6 * FRAME.height / 8), buttonActionTarget, true, new Dimension(32, 32));
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

        towerManager.draw(canvas);

        canvas.drawBitmap(mainTower, new Rect(0, 0, 32, 32), new Rect(FRAME.left + 7 * FRAME.width / 16, FRAME.top + 3 * FRAME.height / 8, FRAME.left + 9 * FRAME.width / 16, FRAME.top + 5 * FRAME.height / 8), null);

        canvas.drawBitmap(heroWhite, new Rect(0, 0, 32, 32), new Rect(FRAME.left + 3 * FRAME.width / 16, FRAME.top + 3 * FRAME.height / 8, FRAME.left + 4 * FRAME.width / 16, FRAME.top + 4 * FRAME.height / 8), null);

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
                            Utility.print("actionA");
                            break;
                        case "actionB":
                            Utility.print("actionB");
                            break;
                        case "actionTarget":
                            Utility.print("actionTarget");
                            break;
                        case "actionAttack":
                            Utility.print("actionAttack");
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
