package dungeon;

import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class Monster extends DungeonCharacter
{
	private double chanceToHeal;
	private int minHeal, maxHeal;

	public Monster(String name, 
		       int hitPoints, 
		       int attackSpeed,
		       double chanceToHit, 
		       double chanceToHeal,
		       int damageMin, 
		       int damageMax,
		       int minHeal, 
		       int maxHeal,
                       int height,
                       File sprite,
                       ViewController controller
		      )
	{
		super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, height, sprite, controller);
		this.chanceToHeal = chanceToHeal;
		this.maxHeal = maxHeal;
		this.minHeal = minHeal;
	}

	public void heal() throws Exception 
	{
		boolean canHeal;
		int hitPointChange;

		canHeal = (Math.random() <= chanceToHeal) && (getHitPoints() > 0);

		if (canHeal)
		{
			hitPointChange = (int)(Math.random() * (maxHeal - minHeal + 1)) + minHeal;
			super.modifyHitPoints(hitPointChange);
		}


	}

	public void modifyHitPoints(int hitPointChange) throws Exception 
	{
		super.modifyHitPoints(hitPointChange);
		TimeUnit.SECONDS.sleep(1);
		heal();
	}
}
