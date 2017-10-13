package bytebuddies.corgaday.bytebuddies.engine;

import bytebuddies.corgaday.bytebuddies.player.BaseHero;
import bytebuddies.corgaday.bytebuddies.render.Sprite;
import bytebuddies.corgaday.bytebuddies.resources.EEffect;

public class GameThread extends Thread {

    Sprite[] sprites;

    public GameThread(boolean isServer) {

    }

    @Override
    public void run() {
        //new Thread(this).start();
    }
    
    public void setEffectDelay(long msTime, BaseHero hero, EEffect eE, float strength) {

    }

    private void update() {

    }

    private void draw() {
        for(Sprite s : sprites) {
            //s.draw(canvas);
        }
    }

}