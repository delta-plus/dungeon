package dungeon;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public abstract class Hero extends DungeonCharacter
{
	private double chanceToBlock;
	private String[] actionList;
	private int pillarCount;
	private int turns;
	
	private int healthPotions;
	private int visionPotions;
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
			String[] actionList,
			AttackFactory attacks
                   ) throws Exception
	{
		super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, height, "Hero", sprite, controller, attacks);
		this.chanceToBlock = chanceToBlock;
		this.actionList = actionList;
		pillarCount = 0;
		readName();
	}

	public void readName()
	{
		System.out.print("Enter character name: ");
		
		
		setName(new Scanner(System.in).next());
		
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
				getController().updateView(this, "Block!");
			} else 
			{
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
		int numTurns = getAttackSpeed()/opponent.getAttackSpeed();

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

	public int getPillarCount()
	{
		return pillarCount;
	}

	public void addPillar()
	{
		pillarCount++;
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
	public void useHealthPotion()throws Exception
	{
		if(this.healthPotions>0)
		{	
			int healthChange = 35;
			this.healthPotions = this.healthPotions - 1;
			modifyHitPoints(healthChange);
		}
		else
		{
			System.out.println("Error, player does not have a Health Potion");
		}
	}
	public void useVisionPotion(Room room1,Room room2, Room room3, Room room4, Room room5, Room room6, Room room7, Room room8) throws Exception
	{
		ArrayList<Drawable> graphic1 = room1.getGraphics();
		ArrayList<Drawable> graphic2 = room2.getGraphics();
		ArrayList<Drawable> graphic3 = room3.getGraphics();
		ArrayList<Drawable> graphic4 = room4.getGraphics();
		ArrayList<Drawable> graphic5 = room5.getGraphics();
		ArrayList<Drawable> graphic6 = room6.getGraphics();
		ArrayList<Drawable> graphic7 = room7.getGraphics();
		ArrayList<Drawable> graphic8 = room8.getGraphics();
		ViewController controller = super.getController();
		controller.clearScreen();
		controller.createView();
		System.out.println("Behind you is");
		for(Drawable graphic: graphic1)
		{	
			if(graphic.getType()!="Empty Space"&&graphic.getType()!="Hero")
			{
				System.out.println(graphic.getType());
			}
		}
			System.out.println("To the bottom left corner is");
		for(Drawable graphic: graphic2)
		{	
			if(graphic.getType()!="Empty Space"&&graphic.getType()!="Hero")
			{
				System.out.println(graphic.getType());
			}
		}
			System.out.println("To the left is");
		for(Drawable graphic: graphic3)
		{	
			if(graphic.getType()!="Empty Space"&&graphic.getType()!="Hero")
			{
				System.out.println(graphic.getType());
			}
		}
			System.out.println("In the top left corner is");
		for(Drawable graphic: graphic4)
		{	
			if(graphic.getType()!="Empty Space"&&graphic.getType()!="Hero")
			{
				System.out.println(graphic.getType());
			}
		}
			System.out.println("In front of you is");
		for(Drawable graphic: graphic5)
		{	
			if(graphic.getType()!="Empty Space"&&graphic.getType()!="Hero")
			{
				System.out.println(graphic.getType());
			}
		}
			System.out.println("In the top right corner is");
		for(Drawable graphic: graphic6)
		{	
			if(graphic.getType()!="Empty Space"&&graphic.getType()!="Hero")
			{
				System.out.println(graphic.getType());
			}
		}
			System.out.println("To the right is");
		for(Drawable graphic: graphic7)
		{	
			if(graphic.getType()!="Empty Space"&&graphic.getType()!="Hero")
			{
				System.out.println(graphic.getType());
			}
		}
			System.out.println("In the bottom right corner is");
		for(Drawable graphic: graphic8)
		{	if(graphic.getType()!="Empty Space"&&graphic.getType()!="Hero")
			{
				System.out.println(graphic.getType());
			}
			
		}
		this.visionPotions--;
		//todo add vision potion
	}
	public void addItems(ArrayList<Item> items)
	{
		
		for(int i = 0; i<items.size(); i++)
		{
			if(items.get(i).getName().equals("Vision Potion"))this.visionPotions++;
			else if(items.get(i).getName().equals("Healing Potion"))this.healthPotions++;
		}
	}
	public void addItem(Item item)
	{
		
		if(item.getName().equals("Vision Potion"))this.visionPotions++;
		else if(item.getName().equals("Healing Potion"))this.healthPotions++;
	}
	public int getHealthPotions()
	{
		return this.healthPotions;
	}
	public int getVisionPotions()
	{
		return this.visionPotions;
	}
}
