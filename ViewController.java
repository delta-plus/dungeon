package dungeon;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ViewController 
{
	private Hero hero;
	private Monster monster;
	private Scanner heroSprite;
	private Scanner monsterSprite;
	private int heightDiff;
	private boolean heroTaller;

	public ViewController() {}

	public void setHero(Hero theHero) 
	{
		hero = theHero;
	}

	public void setMonster(Monster theMonster) 
	{
		monster = theMonster;
	}

	// Initializes (or resets) sprite Scanners
	public void setSprites() throws FileNotFoundException 
	{
		try {
			heroSprite = new Scanner(hero.getSprite());
			monsterSprite = new Scanner(monster.getSprite());

		        if (hero.getHeight() >= monster.getHeight()) {
		    		heightDiff = hero.getHeight() - monster.getHeight();
		      		heroTaller = true;
		    	} else {
		      		heightDiff = monster.getHeight() - hero.getHeight();
		     		heroTaller = false;
		    	}
		} catch (FileNotFoundException e) {
			System.out.println("Error loading sprite: " + e);
		}
	}

	public void createView() throws Exception 
	{
		try {
			setSprites();
		} catch (FileNotFoundException e) {
			System.out.println("Error loading sprite: " + e);
		}

		clearScreen();
		System.out.println();
		System.out.println();
		System.out.println();

		int heightDifference = heightDiff;

		while (heroSprite.hasNextLine() && monsterSprite.hasNextLine()) 
		{
			if (heightDifference > 0) 
			{
				if (heroTaller) 
				{
					System.out.println(heroSprite.nextLine());
				} 
				else 
				{
					System.out.println("            " + monsterSprite.nextLine());
				}

				heightDifference--;
			} 
			else 
			{
				String line = heroSprite.nextLine() + "        " + monsterSprite.nextLine();
				System.out.println(line);
			}
		}

		System.out.println("----------------");
		System.out.println(hero.getName() + "      " + monster.getName());
		System.out.println("HP: " + hero.getHitPoints() + "      " + "HP: " + monster.getHitPoints());
		System.out.println("Turns: " + hero.getTurns());
		System.out.println();

		for (String choice : hero.getActionList()) 
		{
			System.out.println(choice);
		}

		System.out.println(Integer.toString(hero.getActionList().length + 1) + ". Quit");
		System.out.println();
	}

	public void updateView(DungeonCharacter updatedCharacter, String action) 
	{
		if (updatedCharacter == hero) 
		{
			drawHero(action);
		} 
		else 
		{
			drawMonster(action);
		}
	}

	public void updateView(DungeonCharacter updatedCharacter, int hitPointChange) throws Exception 
	{
		if (updatedCharacter == hero) 
		{
			drawHero(hitPointChange);
		} 
		else 
		{
			drawMonster(hitPointChange);
		}
	}

	private void drawHero(String action) 
	{
		try {
			setSprites();
		} catch (FileNotFoundException e) {
			System.out.println("Error loading sprite: " + e);
		}

		clearScreen();
		System.out.println();
		System.out.println(" " + action);
		System.out.println();

		int heightDifference = heightDiff;

		while (heroSprite.hasNextLine() && monsterSprite.hasNextLine()) 
		{
			if (heightDifference > 0) 
			{
				if (heroTaller) 
				{
					System.out.println(heroSprite.nextLine());
				} else {
					System.out.println("            " + monsterSprite.nextLine());
				}

				heightDifference--;
			} 
			else 
			{
				String line = heroSprite.nextLine() + "        " + monsterSprite.nextLine();
				System.out.println(line);
			}
		}

		System.out.println("----------------");
		System.out.println(hero.getName() + "      " + monster.getName());
		System.out.println("HP: " + hero.getHitPoints() + "      " + "HP: " + monster.getHitPoints());
		System.out.println("Turns: " + hero.getTurns());
		System.out.println();

		for (String choice : hero.getActionList()) 
		{
			System.out.println(choice);
		}

		System.out.println(Integer.toString(hero.getActionList().length + 1) + ". Quit");
		System.out.println();
	}

	private void drawHero(int hitPointChange) 
	{
		try {
			setSprites();
		} catch (FileNotFoundException e) {
			System.out.println("Error loading sprite: " + e);
		}

		clearScreen();
		System.out.println();
		System.out.println(" " + Integer.toString(hitPointChange));
		System.out.println();

		int heightDifference = heightDiff;

		while (heroSprite.hasNextLine() && monsterSprite.hasNextLine()) 
		{
			if (heightDifference > 0) 
			{
				if (heroTaller) 
				{
					System.out.println(heroSprite.nextLine());
				} 
				else 
				{
					System.out.println("            " + monsterSprite.nextLine());
				}

				heightDifference--;
			} 
			else 
			{
				String line = heroSprite.nextLine() + "        " + monsterSprite.nextLine();
				System.out.println(line);
			}
		}

		System.out.println("----------------");
		System.out.println(hero.getName() + "      " + monster.getName());
		System.out.println("HP: " + hero.getHitPoints() + "      " + "HP: " + monster.getHitPoints());
		System.out.println("Turns: " + hero.getTurns());
		System.out.println();

		for (String choice : hero.getActionList()) 
		{
			System.out.println(choice);
		}

		System.out.println(Integer.toString(hero.getActionList().length + 1) + ". Quit");
		System.out.println();
	}

	private void drawMonster(String action) 
	{
		String buffer = "";

		for (int i = 16 - (action.length() + 1); i > 0; i--) 
		{
			buffer += " ";
		}

		try {
			setSprites();
		} catch (FileNotFoundException e) {
			System.out.println("Error loading sprite: " + e);
		}

		clearScreen();
		System.out.println();
		System.out.println(buffer + action + " ");
		System.out.println();

		int heightDifference = heightDiff;

		while (heroSprite.hasNextLine() && monsterSprite.hasNextLine()) 
		{
			if (heightDifference > 0) 
			{
				if (heroTaller) 
				{
					System.out.println(heroSprite.nextLine());
				} 
				else 
				{
					System.out.println("            " + monsterSprite.nextLine());
				}

				heightDifference--;
			} 
			else 
			{
				String line = heroSprite.nextLine() + "        " + monsterSprite.nextLine();
				System.out.println(line);
			}
		}

		System.out.println("----------------");
		System.out.println(hero.getName() + "      " + monster.getName());
		System.out.println("HP: " + hero.getHitPoints() + "      " + "HP: " + monster.getHitPoints());
		System.out.println("Turns: " + hero.getTurns());
		System.out.println();

		for (String choice : hero.getActionList()) 
		{
			System.out.println(choice);
		}

		System.out.println(Integer.toString(hero.getActionList().length + 1) + ". Quit");
		System.out.println();
	}

	private void drawMonster(int hitPointChange) throws Exception 
	{
		String buffer = "";

		for (int i = 16 - (Integer.toString(hitPointChange).length() + 1); i > 0; i--) 
		{
			buffer += " ";
		}

		try {
			setSprites();
		} catch (FileNotFoundException e) {
			System.out.println("Error loading sprite: " + e);
		}

		clearScreen();
		System.out.println();
		System.out.println(buffer + Integer.toString(hitPointChange) + " ");
		System.out.println();

		int heightDifference = heightDiff;

		while (heroSprite.hasNextLine() && monsterSprite.hasNextLine()) 
		{
			if (heightDifference > 0) 
			{
				if (heroTaller) 
				{
					System.out.println(heroSprite.nextLine());
				} 
				else 
				{
					System.out.println("            " + monsterSprite.nextLine());
				}

				heightDifference--;
			} 
			else 
			{
				String line = heroSprite.nextLine() + "        " + monsterSprite.nextLine();
				System.out.println(line);
			}
		}

		System.out.println("----------------");
		System.out.println(hero.getName() + "      " + monster.getName());
		System.out.println("HP: " + hero.getHitPoints() + "      " + "HP: " + monster.getHitPoints());
		System.out.println("Turns: " + hero.getTurns());
		System.out.println();

		for (String choice : hero.getActionList()) 
		{
			System.out.println(choice);
		}

		System.out.println(Integer.toString(hero.getActionList().length + 1) + ". Quit");
		System.out.println();
	}

	public void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
