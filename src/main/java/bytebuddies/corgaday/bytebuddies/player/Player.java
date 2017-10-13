package bytebuddies.corgaday.bytebuddies.player;

import android.graphics.Bitmap;

public class Player {

    public void rotateChampion(float pitch, float roll, float sensitivity) {
        if(Math.abs(pitch) + Math.abs(roll) > sensitivity) {
            float theta = (float) Math.atan2(-pitch, -roll);
            //bmp.setRotation(270 - (int) Math.toDegrees(theta));
        }
    }

    public void moveChampion(/*Player player, */float pitch, float roll, float speed, float sensitivity) {
        if(Math.abs(pitch) + Math.abs(roll) > sensitivity) {
            //bmp.setTranslationX(bmp.getTranslationX() + speed * (pitch > 0 ? -1 : 1) * (Math.abs(pitch) > sensitivity ? 1 : (Math.abs(pitch) / sensitivity)));
            //bmp.setTranslationY(bmp.getTranslationY() + speed * (roll > 0 ? 1 : -1) * (Math.abs(roll) > sensitivity ? 1 : (Math.abs(roll) / sensitivity)));
        }
    }

}