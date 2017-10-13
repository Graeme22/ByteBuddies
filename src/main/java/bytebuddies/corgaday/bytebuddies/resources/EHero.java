package bytebuddies.corgaday.bytebuddies.resources;

public enum EHero {
	
	VORMOUD("Vormoud", 800, 50, 1, 0, 1.0f, 0.9f, 5, 10, 15, EDamage.PHYSICAL),
	ILYANA("Ilyana", 650, 35, 3, 0, 1.2f, 1.0f, 3, 0, 3, EDamage.PHYSICAL);

	EHero(String sName, int iHealth, int iDamage, int iRange, int iCost, float fMoveSpeed, float fAttackSpeed, int iRegen, int iMR, int iArmor, EDamage eD) {
		this.health = iHealth;
		this.damage = iDamage;
		this.cost = iCost;
		this.range = iRange;
		this.name = sName;
		this.attackSpeed = fAttackSpeed;
		this.moveSpeed = fMoveSpeed;
		this.regen = iRegen;
		this.mr = iMR;
		this.armor = iArmor;
		this.damageType = eD;
	};
	
	private final int health, damage, range, cost, regen, mr, armor;
	private final float moveSpeed, attackSpeed;
	private final String name;
	private final EDamage damageType;
	
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

	public int getRegen() { return this.regen; }
	
	public int getCost() {
		return this.cost;
	}

	public int getMR() { return this.mr; }

	public int getArmor() { return this.armor; }

	public EDamage getDamageType() { return this.damageType; }

	public float getMoveSpeed() { return this.moveSpeed; }

	public float getAttackSpeed() { return this.attackSpeed; }
	
}