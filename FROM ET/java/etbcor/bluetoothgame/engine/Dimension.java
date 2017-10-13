package etbcor.bluetoothgame.engine;

public class Dimension {
    public int width;
    public int height;
    public int left;
    public int top;
    public int right;
    public int bottom;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.width = this.right - this.left;
	    this.height = this.bottom - this.top;
    }
}
