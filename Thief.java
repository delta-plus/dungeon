package dungeon;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Thief extends Hero
{
	public Thief(ViewController controller, AttackFactory attacks) throws Exception
	{
		super("Thief", 75, 6, .8, 20, 40, .5, 4, 
		      new File("sprites/ThiefSprite.txt"), 
		      controller, 
			  new String[] {"1. Attack", "2. Surprise Attack"},
			  attacks
		);
	}

	public void surpriseAttack(DungeonCharacter opponent) throws Exception 
	{
		getAttacks().getAttack("surpriseattack").attack(opponent, this);
	}
}
