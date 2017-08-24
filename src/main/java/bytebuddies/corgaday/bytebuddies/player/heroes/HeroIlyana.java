package bytebuddies.corgaday.bytebuddies.player.heroes;

import android.graphics.Bitmap;

import bytebuddies.corgaday.bytebuddies.player.BaseHero;
import bytebuddies.corgaday.bytebuddies.resources.EHero;

public class HeroIlyana extends BaseHero {

    public HeroIlyana(int iHealth, EHero eH) {
        super(iHealth, eH);
    }

    @Override
    public void ability1() {
        //barrage at a location, aoe
    }

    @Override
    public void ability2() {
        //throws daggers in a line, doing damage and slowing enemies hit
    }

    @Override
    public void ability3() {
        //gains attack speed and damage for 4 seconds
    }

}