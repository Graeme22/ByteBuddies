package bytebuddies.corgaday.bytebuddies.player.heroes;

import bytebuddies.corgaday.bytebuddies.player.BaseHero;
import bytebuddies.corgaday.bytebuddies.resources.EHero;

public class HeroVormoud extends BaseHero {

    public HeroVormoud(int iHealth, EHero eH) {
        super(iHealth, eH);
    }

    @Override
    public void ability1() {
        //temporary shield that absorbs damage
    }

    @Override
    public void ability2() {
        //additional speed for 2 seconds, next strike deals bonus damage, on hit prevents enemy abilities for 1 second
    }

    @Override
    public void ability3() {
        //spins for aoe damage
    }

    @Override
    public void onDeath() {
        //comes back with 10% hp, recharge ~3 minutes. short delay.
    }

}