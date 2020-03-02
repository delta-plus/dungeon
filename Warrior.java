package dungeon;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.io.FileNotFoundException;

public class Warrior extends Hero
{
	public Warrior(ViewController controller) throws FileNotFoundException 
	{
		super("Warrior", 125, 4, .8, 35, 60, .2, 4, 
		      new File("sprites/WarriorSprite.txt"), 
		      controller, 
		      new String[] {"1. Attack", "2. Crushing Blow"}
		);
	}


	public void crushingBlow(DungeonCharacter opponent) throws Exception 
	{
		if (Math.random() <= .4)
		{
			int blowPoints = ((int) (Math.random() * 76) + 100) * -1;
			controller.updateView(opponent, "WHAM!");
			TimeUnit.SECONDS.sleep(1);
			opponent.modifyHitPoints(blowPoints);
		}
		else
		{
			controller.updateView(opponent, "Miss!");
		}
	}
}
