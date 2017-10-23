package bytebuddies.corgaday.bytebuddies.resources;

import java.io.Serializable;

public enum EMinion implements Serializable {
	
	MELEEMINION(500, 5, 1),
	RANGEDMINION(450, 3, 2);
	
	EMinion(int iHealth, int iDamage, int iRange) {
		this.health = iHealth;
		this.damage = iDamage;
		this.range = iRange;
	};
	
	private final int health, damage, range;
	
	public int getHealth() {
		return this.health;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getRange() {
		return this.range;
	}
	
}