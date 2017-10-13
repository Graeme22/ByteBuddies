package etbcor.bluetoothgame.ai;

public enum ETower {
	
	HATCHERY("Hatchery", 10000, 0, 0, 0),
	ARROWTOWER("Arrow Tower", 12000, 100, 2, 100);
	
	private ETower(String sName, int iHealth, int iDamage, int iRange, int iCost) {
		this.health = iHealth;
		this.damage = iDamage;
		this.cost = iCost;
		this.range = iRange;
		this.name = sName;
	};
	
	private final int health, damage, range, cost;
	private final String name;
	
	public String getName() {
		return this.name;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getRange() {
		return this.range;
	}
	
	public int getCost() {
		return this.cost;
	}
	
}