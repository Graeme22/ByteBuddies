package etbcor.bluetoothgame.ai;

import etbcor.bluetoothgame.util.Point;

public abstract class BaseMinion {

	private Point location;
	private int currentHealth;
	private EMinion minion;
	
	public BaseMinion(Point p, int iHealth, EMinion eM) {
		this.setLocation(p);
		this.setCurrentHealth(eM.getHealth());
		this.minion = eM;
	}

	public abstract void attack();
	public abstract void defend();

	public Point getLocation() {
		return this.location;
	}

	public void setLocation(Point p) {
		this.location = p;
	}

	public int getCurrentHealth() {
		return this.currentHealth;
	}

	public void setCurrentHealth(int hp) {
		this.currentHealth = hp;
	}

	public EMinion getMinion() {
		return this.minion;
	}
	
}