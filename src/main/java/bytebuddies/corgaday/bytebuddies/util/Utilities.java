package bytebuddies.corgaday.bytebuddies.util;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import bytebuddies.corgaday.bytebuddies.engine.Dimension;

public final class Utilities {

    public static float distance(float aX, float aY, float bX, float bY) {
        return (float)Math.sqrt(Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2));
    }

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;


        if (original_width < bound_width) {
            new_width = bound_width;
            new_height = (new_width * original_height) / original_width;
        }

        if (new_height < bound_height && ((float)(original_width) / (float)(original_height)) > 2) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    public static boolean compareMacs (String string1, String string2) {

	    for (int i = 0; i < string1.length(); i++) {
		    if (string1.charAt(i) > string2.charAt(i))
		    	return true;
		    else if (string1.charAt(i) < string2.charAt(i)) return false;
	    }
	    return true;
    }

	public static String getMacAddress() {
		try {
			Context cntxt = Constants.APPLICATION_CONTEXT;
			WifiManager wifi = (WifiManager) cntxt.getSystemService(Constants.CURRENT_CONTEXT.WIFI_SERVICE);
			if(wifi == null) return "Failed: WiFiManager is null";

			WifiInfo info = wifi.getConnectionInfo();
			if(info == null) return "Failed: WifiInfo is null";

			return info.getMacAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nothing";
	}

    public static void print(String text) {
        System.out.println(text);
    }

    public static float map (float value, float from, float to, float newFrom, float newTo) {
        return (value - from) / (to - from) * (newTo - newFrom) + newFrom;
    }

    public static float smoothStep (float a) {
        return a * a * (3 - 2 * a);
    }

    public static void drawCenterText(Canvas canvas, Paint paint, String text) {
        Rect r = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    public static void drawCenterText (Canvas canvas, Paint paint, String text, Rect r) {
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, r.centerX(), r.centerY() - (paint.ascent() + paint.descent()) / 2, paint);
    }

    public static void drawMiddleText(Canvas canvas, float y, Paint paint, String text) {
        Rect r = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        canvas.drawText(text, x, y, paint);
    }

    public static void drawMiddleText(Canvas canvas, float y, Paint paint, String text, Rect r) {
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, r.centerX(), r.centerY() - (paint.ascent() + paint.descent()) / 2, paint);
    }
}
