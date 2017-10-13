package etbcor.bluetoothgame.ai;

import etbcor.bluetoothgame.util.Point;

public abstract class BaseTower {

	private ETower tower;
	private Point gLocation;
	private int currentHealth;
	
	public BaseTower(Point gP, int iHealth, ETower eT) {
		this.setLocation(gP);
		this.setCurrentHealth(eT.getHealth());
		this.tower = eT;
	}

	public void attack(){}
	public void ability1(){}
	public void ability2(){}
	public void ability3(){}
	public void ability4(){}
	public void onDeath(){}

	public Point getLocation() {
		return this.gLocation;
	}

	public void setLocation(Point gP) {
		this.gLocation = gP;
	}

	public int getCurrentHealth() {
		return this.currentHealth;
	}

	public void setCurrentHealth(int hp) {
		this.currentHealth = hp;
	}

	public ETower getTower() {
		return this.tower;
	}
	
}