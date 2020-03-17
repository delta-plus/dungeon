package dungeon;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Warrior extends Hero
{
	public Warrior(ViewController controller, AttackFactory attacks) throws Exception
	{
		super("Warrior", 125, 4, .8, 35, 60, .2, 4, 
		      new File("sprites/WarriorSprite.txt"), 
		      controller, 
			  new String[] {"1. Attack", "2. Crushing Blow"},
			  attacks
		);
	}


	public void crushingBlow(DungeonCharacter opponent) throws Exception 
	{
		getAttacks().getAttack("crushingblow").attack(opponent, this);
	}
}
