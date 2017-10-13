package etbcor.bluetoothgame.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import etbcor.bluetoothgame.util.Constants;
import etbcor.bluetoothgame.scenes.SceneManager;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private SceneManager sceneManager;

    public GamePanel (Context context, MainActivity ma) {
        super(context);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;
        Constants.RESOURCES = getResources();

        thread = new MainThread(getHolder(), this);
        sceneManager = new SceneManager(ma);

        setFocusable(true);
    }

    public MainThread getMainThread () {
        return thread;
    }

    @Override
    public void surfaceCreated (SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged (SurfaceHolder holder, int format, int width, int height) {
        Constants.INIT_TIME = System.currentTimeMillis();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        sceneManager.receiveTouch(event);
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        sceneManager.draw(canvas);
    }

    public void update () {
        sceneManager.update();
    }
}
