package bytebuddies.corgaday.bytebuddies.engine;

import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.view.SurfaceHolder;

import bytebuddies.corgaday.bytebuddies.player.heroes.BaseHero;
import bytebuddies.corgaday.bytebuddies.resources.EEffect;

class GameThread extends Thread {
    private static final int MAX_FPS = 30;

    private final SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    public static Canvas canvas;

    private boolean running;
//    private double averageFPS;

    GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setEffectDelay(long msTime, BaseHero hero, EEffect eE, float strength) {

    }

    public void setRunning(boolean b) {
        this.running = b;
    }

    @Override
    public void run () {
        long startTime;
        long timeMillis = 1000 / MAX_FPS;
        long waitTime;
        int frameCount = 0;
//        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();

                DrawFilter filter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, 0);
                canvas.setDrawFilter(filter);

                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                if (waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
//                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
//                totalTime = 0;
            }
        }
    }
}