package etbcor.bluetoothgame.ai;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Ethan on 3/2/17.
 */

public class TowerManager {
    private ArrayList<Tower> towers;

    public TowerManager () {
        towers = new ArrayList<>();
    }

    public void addTower (Rect rectangle, int type) {
        towers.add(new Tower(rectangle, type));
    }

    public void update () {
        for (Tower tower: towers) {
            tower.update();
        }
    }

    public void draw (Canvas canvas) {
        for (Tower tower: towers) {
            tower.draw(canvas);
        }
    }
}
