package dungeon;

import java.io.File;
import java.util.Scanner;

public abstract class Hero extends DungeonCharacter
{
	private double chanceToBlock;
	private String[] actionList;
	private int turns;

	public Hero(String name, 
                    int hitPoints, 
                    int attackSpeed, 
                    double chanceToHit, 
                    int damageMin, 
                    int damageMax,
                    double chanceToBlock,
		    int height,
		    File sprite,
                    ViewController controller,
		    String[] actionList
                   )
	{
		super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, height, sprite, controller);
		this.chanceToBlock = chanceToBlock;
		this.actionList = actionList;
		readName();
	}

	public void readName()
	{
		System.out.print("Enter character name: ");
		name = new Scanner(System.in).next();
	}

	public boolean defend()
	{
		return Math.random() <= chanceToBlock;
	}

	public void modifyHitPoints(int hitPointChange) throws Exception 
	{
		if (hitPointChange < 0) 
		{
			if (defend())
			{
				controller.updateView(this, "Block!");
			} else {
				super.modifyHitPoints(hitPointChange);
			}
		} 
		else 
		{
			super.modifyHitPoints(hitPointChange);
		}
	}

	public String[] getActionList() 
	{
		return actionList;
	}

	public void setTurns(Monster opponent)
	{
		int numTurns = attackSpeed/opponent.getAttackSpeed();

		if (numTurns == 0)
		{
			numTurns++;
		}

		turns = numTurns;
	}
	public void setTurns(int Change)
	{
		this.turns = Change;
	}

	public int getTurns()
	{
		return turns;
	}

	public int chooseAction(Monster opponent)
	{
		turns--;

		String actionChoice = Integer.toString(new Scanner(System.in).nextInt());

		for (String choice : actionList) 
		{
			if (choice.substring(0, 1).compareTo(actionChoice) == 0) 
			{
				return Integer.parseInt(actionChoice);
			}
		}

		if (Integer.parseInt(actionChoice) == actionList.length + 1)
		{
			return Integer.parseInt(actionChoice);
		}

		return 1;
	}
	public int getTurns()
	{
			return this.turns;
	}	
}
