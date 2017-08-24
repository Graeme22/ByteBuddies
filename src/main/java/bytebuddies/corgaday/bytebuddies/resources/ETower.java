package bytebuddies.corgaday.bytebuddies.resources;

public enum ETower {

	MAGE1("Arcane Spire", 15000, 200, 2, 250, true, 1f),

	ARROW1("Peasant Archers", 12000, 100, 3, 100, false, 2f),
	ARROW2A("King's Huntsmen", 14000, 120, 3, 150, false, 2.1f),

	BARRACKS1("Barracks", 12000, 0, 0, 150, false, 0.2f),
	BARRACKS2A("Battle School", 16000, 0, 0, 200, false, 0.25f),
	BARRACKS2B("Soldiers' Quarters", 15000, 0, 0, 175, false, 0.25f),
	BARRACKS3AA("Elite Academy", 18000, 0, 0, 250, false, 0.25f),
	BARRACKS3AB("Swordmasters", 18000, 0, 0, 200, false, 0.2f),
	BARRACKS3BA("Fortified Estate", 17000, 0, 0, 200, false, 0.25f),
	BARRACKS3BB("Veteran Knights", 17000, 0, 0, 250, false, 0.15f),

	MAINTOWERA("King's Castle", 100000, 0, 0, 0, false, 0.1f),
	MAINTOWERB("", 1, 250, 3, 0, false, 1.5f);
	
	ETower(String sName, int iHealth, int iDamage, int iRange, int iCost, boolean bSplash, float iRate) {
		this.health = iHealth;
		this.damage = iDamage;
		this.cost = iCost;
		this.range = iRange;
		this.name = sName;
		this.splash = bSplash;
		this.rate = iRate;
	}
	
	private final int health, damage, range, cost;
	private final float rate;
	private final String name;
	private final boolean splash;

	public float getRate() { return this.rate; }
	
	public String getName() {
		return this.name;
	}

	public boolean isSplash() { return this.splash; }
	
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