package bytebuddies.corgaday.bytebuddies.player;

import android.graphics.Bitmap;

import java.io.Serializable;

import bytebuddies.corgaday.bytebuddies.player.heroes.BaseHero;

public class Player implements Serializable {

    private BaseHero hero;
    private int rotation = 0;
    private transient int gold = 0, cost;
    private transient Bitmap moving, attacking;

    public Player(BaseHero bh) {
        this.hero = bh;
        //TODO: set bitmaps
    }

    public void rotateChampion(float pitch, float roll, float sensitivity) {
        if(Math.abs(pitch) + Math.abs(roll) > sensitivity)
            this.rotation = 270 - (int)Math.toDegrees((float)Math.atan2(-pitch, -roll));
    }

    public void moveChampion(float pitch, float roll, float sensitivity) {
        if(Math.abs(pitch) + Math.abs(roll) > sensitivity) {
            //TODO: check if position isn't out of bounds...
            float motionX = this.hero.getCurrentMoveSpeed() * (pitch > 0 ? -1 : 1) * (Math.abs(pitch) > sensitivity ? 1 : (Math.abs(pitch) / (Math.abs(pitch) + Math.abs(roll))));
            float motionY = this.hero.getCurrentMoveSpeed() * (roll > 0 ? 1 : -1) * (Math.abs(roll) > sensitivity ? 1 : (Math.abs(roll) / (Math.abs(pitch) + Math.abs(roll))));
            this.hero.incrementPos(motionX, motionY);
        }
    }

}