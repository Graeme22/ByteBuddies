package bytebuddies.corgaday.bytebuddies.resources;

public enum ETower {

	MAGE1("Arcane Spire", 1000, 200, 3, 150, true, 1f),
	MAGE2A("Battle Mage", 1200, 250, 3, 175, true, 1.25f),
	MAGE2B("Channeler Adept", 1500, 5, 3, 200, false, 60f),
	MAGE3AA("Magma Warlock", 1400, 300, 3, 250, true, 1.25f),
	MAGE3AB("Stormlord", 1400, 500, 4, 250, false, 0.5f),
	MAGE3BA("Archmage", 1600, 7, 3, 300, false, 60f),
	MAGE3BB("Necromancer", 1600, 2, 2, 300, true, 60f),//tower attacks all targets around it, not just 1

	ARROW1("Arrow Tower", 1200, 100, 3, 100, false, 2f),
	ARROW2A("Watchtower", 1400, 120, 3, 150, false, 2f),
	ARROW2B("Guardtower", 1500, 220, 3, 150, false, 1.25f),
	ARROW3AA("Marksman Tower", 1600, 130, 4, 180, false, 2.25f),
	ARROW3AB("Ranger Tower", 1600, 50, 3, 200, false, 6.5f),
	ARROW3BA("Crossbow Tower", 1700, 300, 3, 200, false, 1.75f),
	ARROW3BB("Cannon Tower", 2000, 250, 2, 250, true, 1f),

	MAINTOWERA("King's Castle", 10000, 200, 2, 0, false, 1.5f);
	
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