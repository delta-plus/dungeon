package dungeon;

import java.io.File;

public class Skeleton extends Monster
{
	public Skeleton(ViewController controller, AttackFactory attacks) throws Exception
	{
		super("Sargath the Skeleton", 100, 3, .8, .3, 30, 50, 30, 50, 5, 
		      new File("sprites/SkeletonSprite.txt"), 
			  controller,
			  attacks
		);
	}
}
