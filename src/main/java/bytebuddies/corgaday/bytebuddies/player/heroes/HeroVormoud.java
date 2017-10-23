package bytebuddies.corgaday.bytebuddies.player.heroes;

import bytebuddies.corgaday.bytebuddies.resources.EHero;

public class HeroVormoud extends BaseHero {

    public HeroVormoud(int iHealth, EHero eH) {
        super(iHealth, eH);
    }

    @Override
    public void onAbility1() {
        //spins for aoe damage
    }

    @Override
    public void onAbility2() {
        //temporary shield
    }

    @Override
    public void onDeath() {

        super.onDeath();

        //comes back with 10% hp, recharge ~5 minutes. short delay.

        //if (passive hasn't reloaded yet)
        this.setCurrentHealth(this.getHero().getHealth() / 10);
        //turn off passive

    }

}