package dungeon;

import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class DungeonCharacter
{
	protected String name;
	protected int hitPoints;
	protected int attackSpeed;
	protected double chanceToHit;
	protected int damageMin, damageMax;
        private int height;
        private File sprite;
        protected ViewController controller;

	public DungeonCharacter(String name, 
                                int hitPoints, 
                                int attackSpeed, 
                                double chanceToHit, 
                                int damageMin, 
                                int damageMax,
                                int height,
                                File sprite,
                                ViewController controller
			       )
	{
		this.name = name;
		this.hitPoints = hitPoints;
		this.attackSpeed = attackSpeed;
		this.chanceToHit = chanceToHit;
		this.damageMin = damageMin;
		this.damageMax = damageMax;
		this.height = height;
		this.sprite = sprite;
                this.controller = controller;
	}

	public String getName()
	{
		return name;
	}

	public int getHitPoints()
	{
		return hitPoints;
	}

	public int getAttackSpeed()
	{
		return attackSpeed;
	}

	public int getHeight() 
	{
		return height;
	}

	public File getSprite() 
	{
		return sprite;
	}

	public void modifyHitPoints(int hitPointChange) throws Exception 
	{
		if (hitPointChange <= 0)
                {
			hitPoints += hitPointChange;

			if (hitPoints < 0)
                        {
				hitPoints = 0;
			}

			controller.updateView(this, hitPointChange);
		}
		else
		{
			hitPoints += hitPointChange;
			controller.updateView(this, "Heal!");
			TimeUnit.SECONDS.sleep(1);
			controller.updateView(this, hitPointChange);
                        TimeUnit.SECONDS.sleep(1);
		}
	}

	public boolean isAlive()
	{
		return (hitPoints > 0);
	}

	public void attack(DungeonCharacter opponent) throws Exception 
	{
		boolean canAttack;
		int damage;

		canAttack = Math.random() <= chanceToHit;

		if (canAttack)
		{
			damage = ((int) (Math.random() * (damageMax - damageMin + 1)) + damageMin) * -1;
			opponent.modifyHitPoints(damage);
		}
		else
		{
			controller.updateView(opponent, "Miss!");
                        TimeUnit.SECONDS.sleep(1);
		}
	}
}
