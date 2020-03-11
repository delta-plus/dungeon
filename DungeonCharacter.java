package dungeon;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public abstract class DungeonCharacter implements Drawable
{
	private String name;
	private int hitPoints;
	private int attackSpeed;
	private double chanceToHit;
	private int damageMin, damageMax;
        private int height;
	private String type;
	private File spriteFile;
        private Scanner sprite;
        private ViewController controller;

	public DungeonCharacter(String name, 
                                int hitPoints, 
                                int attackSpeed, 
                                double chanceToHit, 
                                int damageMin, 
                                int damageMax,
                                int height,
				String type,
                                File spriteFile,
                                ViewController controller
			       ) throws Exception
	{
		this.name = name;
		this.hitPoints = hitPoints;
		this.attackSpeed = attackSpeed;
		this.chanceToHit = chanceToHit;
		this.damageMin = damageMin;
		this.damageMax = damageMax;
		this.height = height;
		this.type = type;
		this.spriteFile = spriteFile;
		this.sprite = new Scanner(spriteFile);
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

	public String getType()
	{
		return type;
	}

	public Scanner getSprite() 
	{
		return sprite;
	}

	public void refreshSprite() throws Exception
	{
		sprite = new Scanner(spriteFile);
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
	public void setName(String name)
	{
		this.name = name;
	}
	public ViewController getController()
	{
		return this.controller;
		
	}
	
}
