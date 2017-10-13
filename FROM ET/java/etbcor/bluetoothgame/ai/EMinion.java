package etbcor.bluetoothgame.ai;

public enum EMinion {
	
	MELEEMINION(400, 5, 1),
	RANGEDMINION(400, 3, 2),
	SUPERMINION(1000, 25, 1);
	
	private EMinion(int iHealth, int iDamage, int iRange) {
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