package dungeon;

import java.io.File;

public class Sorceress extends Hero
{
	private final int MIN_ADD = 25;
	private final int MAX_ADD = 50;

	public Sorceress(ViewController controller, AttackFactory attacks) throws Exception
	{
		super("Sorceress", 75, 5, .7, 25, 50, .3, 5, 
		      new File("sprites/SorceressSprite.txt"), 
		      controller, 
			  new String[] {"1. Attack", "2. Heal"},
			  attacks
		);
	}

	public void heal() throws Exception 
	{
		int hitPointChange = (int) (Math.random() * (MAX_ADD - MIN_ADD + 1)) + MIN_ADD;
		super.modifyHitPoints(hitPointChange);
	}
}
