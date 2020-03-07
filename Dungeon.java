package dungeon;

import java.util.Scanner;
import java.lang.reflect.*;
import java.util.concurrent.TimeUnit;

public class Dungeon
{
	public static void main(String[] args) 
	throws Exception 
	{
		Hero theHero;
		Monster theMonster;
		ViewController controller = new ViewController();

		do
		{
			theHero = chooseHero(controller);
			theMonster = generateMonster(controller);
			battle(controller, theHero, theMonster);
		} while (playAgain(controller));

	}

	public static Hero chooseHero(ViewController controller) 
	throws Exception  
	{
		int choice;

		System.out.println("Choose a hero:\n" +
				   "1. Warrior\n" +
				   "2. Sorceress\n" +
				   "3. Thief");
		choice = new Scanner(System.in).nextInt();
		return new HeroFactory().createHero(choice, controller);
	}

	public static Monster generateMonster(ViewController controller) 
	throws Exception  
	{
		int choice;

		choice = (int)(Math.random() * 3) + 1;

		switch(choice)
		{

			case 1: return new Ogre(controller);

			case 2: return new Gremlin(controller);

			case 3: return new Skeleton(controller);

			default: return new Skeleton(controller);
		}
	}

	public static boolean playAgain(ViewController controller)
	{
		controller.clearScreen();

		System.out.println("Play again (y/n)?");

		String answer = new Scanner(System.in).next();
		char ch[] = answer.toCharArray();
		char again = ch[0];

		return (again == 'Y' || again == 'y');
	}

	private static void doAction(Hero theHero, Monster theMonster, int choice) 
	throws Exception  
	{
		choice--;

		String chosenAction = theHero.getActionList()[choice];
		String nextActionName;

		if (chosenAction.contains(" "))
		{
			nextActionName = chosenAction.substring(3, chosenAction.length());
			nextActionName = nextActionName.substring(0, 1).toLowerCase() + 
					 nextActionName.substring(1, nextActionName.length());
			nextActionName = nextActionName.replaceAll("\\s+", "");
		}
		else 
		{
			nextActionName = chosenAction.substring(3, chosenAction.length()).toLowerCase();
		}

		Method nextAction = null;

		for (Method method : theHero.getClass().getMethods()) {
			if (method.getName().compareTo(nextActionName) == 0) {
				nextAction = method;
				break;
			}
		}

		Parameter[] params = nextAction.getParameters();

		if (params.length == 0) 
		{
			nextAction.invoke(theHero);
		} 
		else if (params.length == 1) 
		{
			if (params[0].getType() == DungeonCharacter.class) 
			{
				nextAction.invoke(theHero, theMonster);
			}
		}
	}

	public static void battle(ViewController controller, Hero theHero, Monster theMonster)
	throws Exception 
	{
                int quitChoice = theHero.getActionList().length + 1;

		theHero.setTurns(theMonster);
		controller.setHero(theHero);
		controller.setMonster(theMonster);
		controller.setSprites();
		controller.createView();

                battle:
		while (theHero.isAlive() && theMonster.isAlive())
		{
			while (theHero.getTurns() != 0 && theHero.isAlive() && theMonster.isAlive()) 
			{
				int choice = theHero.chooseAction(theMonster);

				if (choice == quitChoice) 
				{
					controller.updateView(theHero, "Egads! Time for a hasty retreat!");
					TimeUnit.SECONDS.sleep(1);
					TimeUnit.SECONDS.sleep(1);
					break battle;
                        	} 
				else 
				{
	                        	doAction(theHero, theMonster, choice);
					controller.createView();
				}
			}

			theHero.setTurns(theMonster);
			TimeUnit.SECONDS.sleep(1);

			if (theMonster.isAlive())
			{
				theMonster.attack(theHero);
				TimeUnit.SECONDS.sleep(1);
				controller.createView();
			}
		}

		controller.clearScreen();

		if (!theMonster.isAlive())
			System.out.println(theHero.getName() + " was victorious!");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated!");
		else //both are alive so user quit the game
			System.out.println("Quitters never win.");

		TimeUnit.SECONDS.sleep(1);
		TimeUnit.SECONDS.sleep(1);
	}
}
