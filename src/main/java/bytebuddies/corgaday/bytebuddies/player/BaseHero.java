package bytebuddies.corgaday.bytebuddies.player;

import android.graphics.Bitmap;

import bytebuddies.corgaday.bytebuddies.resources.EEffect;
import bytebuddies.corgaday.bytebuddies.resources.EHero;

public abstract class BaseHero {

	//corgaday loading screen!

    private Bitmap walking;
    private Bitmap dead;
    private Bitmap attack;
	private int x = 0;
    private int y = 0;
	private EHero hero;
	private int currentHealth;
	
	public BaseHero(int iHealth, EHero eH/*, Bitmap bWalking, Bitmap bDead, Bitmap bAttack*/) {
		this.setCurrentHealth(eH.getHealth());
		this.hero = eH;
        /*this.walking = bWalking;
        this.dead = bDead;
        this.attack = bAttack;*/
	}

	public abstract void ability1();
	public abstract void ability2();
	public abstract void ability3();
	public void onDeath() {}

	public void applyEffect(EEffect eE) {
		//clock needs to know duration of effect
		switch(eE) {
			case ATTACKFAST:
				break;
			case ATTACKSLOW:
				break;
			case MOVEFAST:
				break;
			case MOVESLOW:
				break;
			case FAIL:
				break;
			case STUN:
				break;
			case SILENCE:
				break;
			case SHIELD:
				break;
		}
	}

	public void takeDamage(int amount) {

	}

	public void dealDamage() {

	}

	public int getX() {
		return this.x;
	}

	public int getY() {
        return this.y;
	}

	public void setX(int i) {
		this.x = i;
	}

	public void setY(int i) {
        this.y = i;
    }

	public int getCurrentHealth() {
		return this.currentHealth;
	}

	public void setCurrentHealth(int hp) {
		this.currentHealth = hp;
	}

	public EHero getHero() {
		return this.hero;
	}
	
}