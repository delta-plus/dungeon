package dungeon;

import java.io.File;

public class Ogre extends Monster
{
	public Ogre(ViewController controller, AttackFactory attacks) throws Exception
	{
		super("Oscar the Ogre", 200, 2, .6, .1, 30, 50, 30, 50, 7,
		      new File("sprites/OgreSprite.txt"), 
			  controller,
			  attacks
		);
	}
}
