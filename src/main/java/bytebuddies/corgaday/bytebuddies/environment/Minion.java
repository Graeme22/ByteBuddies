package bytebuddies.corgaday.bytebuddies.environment;

import bytebuddies.corgaday.bytebuddies.resources.EMinion;

public abstract class Minion {

	private float x;
	private float y;
	private int currentHealth;
	private EMinion minion;
	
	public Minion(int iX, int iY, EMinion eM) {
		this.setX(iX);
		this.setY(iY);
		this.setCurrentHealth(eM.getHealth());
		this.minion = eM;
	}

	public abstract void attack();
	public abstract void defend();

	public float getX() { return x;	}

	public float getY() { return y;	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
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