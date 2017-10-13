package bytebuddies.corgaday.bytebuddies.player.heroes;

import bytebuddies.corgaday.bytebuddies.player.BaseHero;
import bytebuddies.corgaday.bytebuddies.render.Sprite;
import bytebuddies.corgaday.bytebuddies.resources.EHero;

public class HeroVormoud extends BaseHero {

    private Sprite passive;

    public HeroVormoud(int iHealth, EHero eH) {
        super(iHealth, eH);
    }

    @Override
    public void onAbility1() {
        //temporary shield that absorbs damage
    }

    @Override
    public void onAbility2() {
        //additional speed for 2 seconds, next strike deals bonus damage, on hit prevents enemy abilities for 1 second
    }

    @Override
    public void onAbility3() {
        //spins for aoe damage
    }

    @Override
    public void onDeath() {
        //comes back with 10% hp, recharge ~5 minutes. short delay.

        //if (passive hasn't reloaded yet)
        this.setCurrentHealth(this.getHero().getHealth() / 10);
        //turn off passive

    }

}