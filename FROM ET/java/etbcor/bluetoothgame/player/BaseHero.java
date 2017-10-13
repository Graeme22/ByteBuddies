package etbcor.bluetoothgame.player;

import etbcor.bluetoothgame.util.Point;

public abstract class BaseHero {

	private Point location;
	private EHero hero;
	private int currentHealth;
	
	public BaseHero(Point p, int iHealth, EHero eH) {
		this.setLocation(p);
		this.setCurrentHealth(eH.getHealth());
		this.hero = eH;
	}

	public abstract void attack();
	public abstract void ability1();
	public abstract void ability2();
	public abstract void ability3();
	public abstract void ability4();
	public abstract void onDeath();

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

	public EHero getHero() {
		return this.hero;
	}
	
}