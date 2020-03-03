package dungeon;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Thief extends Hero
{
	public Thief(ViewController controller) 
	{
		super("Thief", 75, 6, .8, 20, 40, .5, 4, 
		      new File("sprites/ThiefSprite.txt"), 
		      controller, 
		      new String[] {"1. Attack", "2. Surprise Attack"}
		);
	}

	public void surpriseAttack(DungeonCharacter opponent) throws Exception 
	{
		double surprise = Math.random();
		if (surprise <= .4)
		{
			turns++;
			controller.updateView(this, "Surprise!");
			TimeUnit.SECONDS.sleep(1);
			attack(opponent);
		}
		else if (surprise >= .9)
		{
			controller.updateView(opponent, "Block!");
		}
		else
		{
			attack(opponent);
		}
	}
}
