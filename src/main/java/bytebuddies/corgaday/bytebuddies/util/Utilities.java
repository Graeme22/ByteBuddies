package bytebuddies.corgaday.bytebuddies.util;

public class Utilities {

    public static float distance(float aX, float aY, float bX, float bY) {
        return (float)Math.sqrt(Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2));
    }

    public static int GOLD = 0;

}