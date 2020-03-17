package dungeon;

import java.util.Random;

public class MonsterFactory 
{
	public static Monster createMonster(ViewController controller, int choice, AttackFactory attacks) throws Exception  
	{
		switch(choice)
		{
			case 1: return new Ogre(controller, attacks);
			case 2: return new Gremlin(controller, attacks);
			case 3: return new Skeleton(controller, attacks);
			default: return new Skeleton(controller, attacks);
		}
	}
	
	
	public static Monster createRandomMonster(ViewController controller, AttackFactory attacks) throws Exception
	{
		Random random = new Random();
		int ran = random.nextInt(3);
		Monster newMonster = createMonster(controller, ran, attacks);
		return newMonster;
	}
}
