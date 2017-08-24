package bytebuddies.corgaday.bytebuddies;

import bytebuddies.corgaday.bytebuddies.resources.ETower;

public abstract class BaseTower {

	private ETower tower;
	private Point location;
	private int currentHealth;
	
	public BaseTower(Point p, int iHealth, ETower eT) {
		this.setLocation(p);
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

	public ETower getTower() {
		return this.tower;
	}
	
}