package dungeon;

import java.io.File;

public class Gremlin extends Monster
{
	public Gremlin(ViewController controller) throws Exception
	{
		super("Gnarltooth the Gremlin", 70, 5, .8, .4, 15, 30, 20, 40, 4,
		      new File("sprites/GremlinSprite.txt"),
		      controller
		);

	}
}
