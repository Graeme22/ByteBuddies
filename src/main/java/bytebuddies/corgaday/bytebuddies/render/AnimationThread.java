package bytebuddies.corgaday.bytebuddies.render;

import android.graphics.Canvas;
import android.provider.Settings;
import android.view.SurfaceHolder;

public class AnimationThread extends Thread {

    private SurfaceHolder sH;
    private boolean running = false;
    private IDraw ID;
    private long timer;

    public AnimationThread(SurfaceHolder _sH, IDraw _ID) {
        this.sH = _sH;
        this.ID = _ID;
        this.ID.onInitialize();
    }

    public void setRunning(boolean b) {
        this.running = b;
    }

    @Override
    public void run() {
        Canvas c;
        while(running) {
            c = null;
            timer = System.currentTimeMillis();
            ID.onUpdate(timer);

            try {
                c = sH.lockCanvas(null);
                synchronized (sH) {
                    ID.onDraw(c);
                }
            } finally {
                if(c != null) {
                    sH.unlockCanvasAndPost(c);
                }
            }
        }
    }

}