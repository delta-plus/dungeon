package dungeon;

import java.util.concurrent.TimeUnit;

public class CrushingBlow implements Attack
{ 
    public void attack(DungeonCharacter opponent, Hero hero) throws Exception 
	{
		if (Math.random() <= .4)
		{
			int blowPoints = ((int) (Math.random() * 76) + 100) * -1;
			hero.getController().updateView(opponent, "WHAM!");
			TimeUnit.SECONDS.sleep(1);
			opponent.modifyHitPoints(blowPoints);
		}
		else
		{
			opponent.getController().updateView(opponent, "Miss!");
			TimeUnit.SECONDS.sleep(1);
		}
	}
}