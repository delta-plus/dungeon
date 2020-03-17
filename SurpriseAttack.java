package dungeon;

import java.util.concurrent.TimeUnit;

public class SurpriseAttack implements Attack 
{	
    public void attack(DungeonCharacter opponent, Hero hero) throws Exception 
	{
		double surprise = Math.random();
		if (surprise <= .4)
		{
			int Turns = hero.getTurns()+1;
			hero.setTurns(Turns);
			hero.getController().updateView(hero, "Surprise!");
			TimeUnit.SECONDS.sleep(1);
			hero.attack(opponent);
		}
		else if (surprise >= .9)
		{
			opponent.getController().updateView(opponent, "Block!");
		}
		else
		{
			hero.attack(opponent);
		}
	}
}