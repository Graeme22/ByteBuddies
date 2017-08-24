package bytebuddies.corgaday.bytebuddies.resources;

public enum EHero {
	
	VORMOUD("Vormoud", 4000, 50, 1, 0),
	ILYANA("Ilyana", 2500, 35, 3, 0);
	
	EHero(String sName, int iHealth, int iDamage, int iRange, int iCost) {
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