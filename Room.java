package dungeon;

import java.util.ArrayList;

public class Room
{
	private ArrayList<Drawable> graphics;
	private ArrayList<Item> items;
	private Monster theMonster;
	private int row;
	private int column;
	private boolean pillarDiscovered;
	private boolean isExit;
	private ViewController controller;
	private AttackFactory attacks;

	public Room(Hero theHero, 
		    int row, 
		    int column, 
		    ViewController controller, 
		    boolean hasPillar,
		    boolean isEntrance,
		    boolean isExit, 
		    AttackFactory attacks
		   ) throws Exception
	{
		graphics = new ArrayList<Drawable>();
		items = new ArrayList<Item>();
		double monsterChance = Math.random();
		double healingPotionChance = Math.random();
		double visionPotionChance = Math.random();
		double pitChance = Math.random();
		this.pillarDiscovered = false;

		this.row = row;
		this.column = column;
		this.controller = controller;
		this.isExit = isExit;
		this.attacks = attacks;

                if (isEntrance || isExit)
		{
			graphics.add(new EmptySpace());
			graphics.add(theHero);
			graphics.add(new EmptySpace());
			graphics.add(new EmptySpace());
			graphics.add(new EmptySpace());
			graphics.add(new EmptySpace());
			graphics.add(new EmptySpace());
			graphics.add(new EmptySpace());
		}
		else
		{
			if (pitChance > .9)
			{
				graphics.add(new Pit());
			}
			else
			{
				graphics.add(new EmptySpace());
			}

			graphics.add(theHero);
			graphics.add(new EmptySpace());
			graphics.add(new EmptySpace());
			graphics.add(new EmptySpace());

			if (monsterChance > .5)
			{
				theMonster = MonsterFactory.createRandomMonster(controller, attacks);
				graphics.add(theMonster);
			}
			else
			{
				graphics.add(new EmptySpace());
			}

			if (hasPillar)
			{
				graphics.add(new Pillar());
			}
			else
			{
				graphics.add(new EmptySpace());
			}

			graphics.add(new EmptySpace());

			if (healingPotionChance > .9)
			{
				items.add(new HealingPotion());
			}

			if (visionPotionChance > .9)
			{
				items.add(new VisionPotion());
			}
		}
	}

	public ArrayList<Drawable> getGraphics()
	{
		return graphics;
	}

	public Drawable getGraphic(String type)
	{
		for (Drawable graphic : graphics)
		{
			if (graphic.getType() == type)
			{
				return graphic;
			}
		}

		return null;
	}

        public void removeMonster()
	{
		theMonster = null;
	}

	public void replaceGraphic(int oldGraphicIndex, Drawable newGraphic)
	{
		graphics.set(oldGraphicIndex, newGraphic);
	}

	public void removeItem(int target)
	{
		items.remove(target);
	}

	public ArrayList<Item> getItems()
	{
		return items;
	}

	public Monster getMonster()
	{
		return theMonster;
	}

	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	public boolean pillarDiscovered()
	{
		return pillarDiscovered;
	}

	public void discoverPillar()
	{
		pillarDiscovered = true;
	}

	public boolean isExit()
	{
		return isExit;
	}
}
