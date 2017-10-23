package bytebuddies.corgaday.bytebuddies.player.heroes;

import bytebuddies.corgaday.bytebuddies.resources.EEffect;
import bytebuddies.corgaday.bytebuddies.resources.EHero;

public class HeroIlyana extends BaseHero {

    public HeroIlyana(int iHealth, EHero eH) {
        super(iHealth, eH);
    }

    @Override
    public void onAbility1() {
        //gains attack speed and move speed for 4 seconds
        this.applyEffect(EEffect.CHANGEATTACKSPEED, 4000l, 2.0f);
        this.applyEffect(EEffect.CHANGEMOVESPEED, 4000l, 1.5f);
    }

    @Override
    public void onAbility2() {
        //throws daggers in a line, doing damage and slowing enemies hit
    }

}