package bytebuddies.corgaday.bytebuddies.environment;

import java.io.Serializable;

import bytebuddies.corgaday.bytebuddies.resources.ETower;

public class Tower implements Serializable {

	private transient ETower tower;
	private int currentHealth;
	//tower position coordinates are for a GRID, so they aren't floats
	private int x, y;
	
	public Tower(int posX, int posY, int iHealth, ETower eT) {
		this.x = posX;
		this.y = posY;
		this.setCurrentHealth(eT.getHealth());
		this.tower = eT;
	}

	public void attack() {

	}

	public void upgrade(ETower eT) {
		//subtract gold
		this.tower = eT;
	}

	public int getX() { return this.x; }

	public int getY() { return this.y; }

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