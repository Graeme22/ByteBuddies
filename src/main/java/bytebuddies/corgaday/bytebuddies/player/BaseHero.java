package bytebuddies.corgaday.bytebuddies.player;

import bytebuddies.corgaday.bytebuddies.render.Sprite;
import bytebuddies.corgaday.bytebuddies.resources.EDamage;
import bytebuddies.corgaday.bytebuddies.resources.EEffect;
import bytebuddies.corgaday.bytebuddies.resources.EHero;

public abstract class BaseHero {

	//corgaday loading screen!
    private Sprite walk, die, attack, ability1, ability2, ability3;
	private EHero hero;
	private int currentHealth, currentShield, currentMR, currentArmor;
	private float currentAttackSpeed, currentMoveSpeed, x, y;
	private boolean isSilenced;
	private Sprite sprite;
	
	public BaseHero(int iHealth, EHero eH) {
		this.currentHealth = eH.getHealth();
		this.currentShield = 0;
		this.currentMoveSpeed = eH.getMoveSpeed();
		this.currentAttackSpeed = eH.getAttackSpeed();
		this.currentArmor = eH.getArmor();
		this.currentMR = eH.getMR();
		this.x = -100f;
		this.y = -100f;
		this.isSilenced = false;
		this.hero = eH;
	}

	public abstract void onAbility1();
	public abstract void onAbility2();
	public abstract void onAbility3();
	public void onDeath() {}

	public void update() {}

	public void applyEffect(EEffect eE, long msTime, float strength) {
		switch(eE) {
			/*
			thread.setEffectDelay(msTime, this, eE, strength);
			do this for each case
			 */
			case CHANGEATTACKSPEED:
				this.currentAttackSpeed *= strength;
				break;
			case CHANGEMOVESPEED:
				this.currentMoveSpeed *= strength;
				break;
			case SHIELD:
				this.currentShield += strength;
				break;
			case CHANGEMR:
				this.currentMR *= strength;
				break;
			case CHANGEARMOR:
				this.currentArmor *= strength;
				break;
			default:
				break;
		}
	}

	public void applyEffect(EEffect eE, long msTime) {
		switch(eE) {
			/*
			thread.setEffectDelay(msTime, this, eE);
			do this for each case
			 */
			case STUN:
				this.currentMoveSpeed = 0;
				break;
			case SILENCE:
				//make sure fail fails silencing abilities
				//can't use abilities
				break;
			default:
				break;
		}
	}

	public void takeDamage(int amount, EDamage type) {
		//apply shield first, which isn't affected by armor or MR
		if(this.currentShield > 0) {
			if(this.currentShield >= amount) {
				this.currentShield -= amount;
				return;
			} else {
				amount -= this.currentShield;
				this.currentShield = 0;
			}
		}

		//apply armor and magic resistance. notice this also takes into account true damage by virtue of doing nothing.
		if(type == EDamage.MAGIC) amount *= (100 - this.currentMR) / 100;
		else if(type == EDamage.PHYSICAL) amount *= (100 - this.currentArmor) / 100;

		//deal damage
		this.currentHealth -= amount;
		if(this.currentHealth < 0) this.onDeath();
	}

	protected void setCurrentHealth(int health) {
		this.currentHealth = health;
	}

	public float getX() { return this.x; }

	public float getY() { return this.y; }

	public void setPos(float posX, float posY) {
		this.x = posX;
		this.y = posY;
	}

	public void addToPos(float posX, float posY) {
		this.x += posX;
		this.y += posY;
	}

	public int getCurrentHealth() {
		return this.currentHealth;
	}

	public EHero getHero() {
		return this.hero;
	}

	public Sprite getSprite() { return this.sprite; }
	
}