package dungeon;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.*;
import java.util.concurrent.TimeUnit;

public class Dungeon
{
	private static Hero theHero = null;
	private static Monster theMonster = null;
	private static Room[][] dungeon = new Room[5][5];
	private static Room theRoom = null;
	private static int[] pillarRoomRows = new int[4];
	private static int[] pillarRoomColumns = new int[4];
	private static boolean hasPillar = false;
	private static boolean playerWon = false;
	private static boolean playerLost = false;
	private static boolean playerQuit = false;
	private static Scanner keyboard = new Scanner(System.in);
	private static ViewController controller = new ViewController();
	private static AttackFactory attacks = new AttackFactory();

	public Dungeon() {}

	public Room getRoom(int row, int column)
	{
		return dungeon[row][column];
	}

	public void play() throws Exception
	{
		do
		{
			theHero = chooseHero();
			controller.setHero(theHero);

			// Generate a random entrance and exit location
			int entranceRow = (int) (Math.random() * 5);
			int entranceColumn = (int) (Math.random() * 5);

			int exitRow = (int) (Math.random() * 5);
			int exitColumn = (int) (Math.random() * 5);

			while (exitRow == entranceRow && exitColumn == entranceColumn)
			{
				exitRow = (int) (Math.random() * 5);
				exitColumn = (int) (Math.random() * 5);
			}

			// Generate 4 unique Pillar locations
			for (int i = 0; i < 4; i++)
			{
				pillarRoomRows[i] = (int) (Math.random() * 5);
				pillarRoomColumns[i] = (int) (Math.random() * 5);

				while (pillarRoomRows[i] == entranceRow  && pillarRoomColumns[i] == entranceColumn || 
				       pillarRoomRows[i] == exitRow && pillarRoomColumns[i] == exitColumn) 
				{
					pillarRoomRows[i] = (int) (Math.random() * 5);
					pillarRoomColumns[i] = (int) (Math.random() * 5);
				}

				if (i > 0)
				{
					while (pillarRoomRows[i] == pillarRoomRows[i - 1] && 
					       pillarRoomColumns[i] == pillarRoomColumns[i - 1] || 
					       pillarRoomRows[i] == entranceRow && 
					       pillarRoomColumns[i] == entranceColumn ||
					       pillarRoomRows[i] == exitRow && 
					       pillarRoomColumns[i] == exitColumn
					      )
					{
						pillarRoomRows[i] = (int) Math.random() * 5;
						pillarRoomColumns[i] = (int) Math.random() * 5;
					}
				}
			}

			// Randomly generate all the rooms
			for (int i = 0; i < 5; i++)
			{
				for (int j = 0; j < 5; j++)
				{
					if (i == entranceRow && j == entranceColumn)
					{
						dungeon[i][j] = new Room(theHero, 
									 i, 
									 j, 
									 controller, 
									 false, 
									 true, 
									 false, 
									 attacks
									);
					}
					else if (i == exitRow && j == exitColumn)
					{
						dungeon[i][j] = new Room(theHero, 
									 i, 
									 j, 
									 controller, 
									 false, 
									 false, 
									 true, 
									 attacks
									);
					} else {
						for (int k = 0; k < 4; k++)
						{
							if (i == pillarRoomRows[k] && j == pillarRoomColumns[k])
							{
								hasPillar = true;
							}
						}

						dungeon[i][j] = new Room(theHero, 
									 i, 
									 j, 
									 controller, 
									 hasPillar, 
									 false,
									 false,
									 attacks
									);
						hasPillar = false;
					}
				}
			}

			theRoom = dungeon[entranceRow][entranceColumn];

			while (!playerWon && !playerLost && !playerQuit)
			{
				enterRoom();
			}

			controller.clearScreen();

			if (playerWon)
			{
				playerWon = false;
				System.out.println(theHero.getName() + " was victorious!");
				TimeUnit.SECONDS.sleep(2);
			}
			else if (playerLost)
			{
				playerLost = false;
				System.out.println(theHero.getName() + " was defeated!");
				TimeUnit.SECONDS.sleep(2);
			}
			else
			{
				playerQuit = false;
			}
		} while (playAgain());

	}

	public static Hero chooseHero() throws Exception  
	{
		int choice;

		System.out.println("Choose a hero:\n" +
				   "1. Warrior\n" +
				   "2. Sorceress\n" +
				   "3. Thief");
		choice = keyboard.nextInt();
		keyboard.nextLine();
		return new HeroFactory().createHero(choice, controller, attacks);
	}

	public static Monster generateMonster() throws Exception  
	{
		int choice;

		choice = (int) (Math.random() * 3) + 1;

		switch(choice)
		{

			case 1: return new Ogre(controller, attacks);

			case 2: return new Gremlin(controller, attacks);

			case 3: return new Skeleton(controller, attacks);

			default: return new Skeleton(controller, attacks);
		}
	}

	public static boolean playAgain()
	{
		controller.clearScreen();

		System.out.println("Play again (y/n)?");

		String answer = keyboard.next();
		keyboard.nextLine();
		char ch[] = answer.toCharArray();
		char again = ch[0];

		return (again == 'Y' || again == 'y');
	}

	private static void doAction(int choice) throws Exception  
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

	public static void battle() throws Exception 
	{
                int quitChoice = theHero.getActionList().length + 1;

		theHero.setTurns(theMonster);
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
	                        	doAction(choice);
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

		if (!theMonster.isAlive())
		{
			controller.eraseMonster();
		} 
		else if (!theHero.isAlive())
		{
			playerLost = true;
		}
		else
		{
			playerQuit = true;
		}
	}

	public void loot() throws Exception
	{
		ArrayList<Item> items = theRoom.getItems();
		this.theHero.addItems(items);
		for (int i = 0; i < items.size(); i++)
		{
			controller.updateView(theHero, "Found a " + items.get(i).getName() + "!");
			TimeUnit.SECONDS.sleep(1);
                        // This is when the item would go in the hero's inventory
		}

		for (int i = 0; i < items.size(); i++)
		{
			theRoom.removeItem(i);
		}

		if (theRoom.getGraphic("Pillar") != null && theRoom.pillarDiscovered() == false)
		{
			String pillarNumber = null;

			if (theHero.getPillarCount() == 0)
			{
				pillarNumber = "first";
			}
			else if (theHero.getPillarCount() == 1)
			{
				pillarNumber = "second";
			}
			else if (theHero.getPillarCount() == 2)
			{
				pillarNumber = "third";
			}
			else
			{
				pillarNumber = "fourth and final";
			}

			theHero.addPillar();
			theRoom.discoverPillar();
			controller.updateView(theHero, "Found the " + pillarNumber + " pillar of OO!");
			TimeUnit.SECONDS.sleep(1);
		}

		controller.createView();
	}

	public void navigate() throws Exception
	{
		Room currentRoom = theRoom;

		while (theRoom == currentRoom)
		{
			char direction = (char) System.in.read();

			if (direction == 'w' || direction == 'W')
			{
				if (theRoom.getRow() > 0)
				{
					theRoom = getRoom(theRoom.getRow() - 1, theRoom.getColumn());
				}
			}
			else if (direction == 'a' || direction == 'A')
			{
				if (theRoom.getColumn() > 0)
				{
					theRoom = getRoom(theRoom.getRow(), theRoom.getColumn() - 1);
				}
			}
			else if (direction == 's' || direction == 'S')
			{
				if (theRoom.getRow() < 4)
				{
					theRoom = getRoom(theRoom.getRow() + 1, theRoom.getColumn());
				}
			}
			else if (direction == 'd' || direction == 'D')
			{
				if (theRoom.getColumn() < 4)
				{
					theRoom = getRoom(theRoom.getRow(), theRoom.getColumn() + 1);
				}
			}
			else if(direction == 'q'||direction == 'Q')
			{
				pauseMenu();
			}
		}
	}
	public void pauseMenu() throws Exception
	{
		boolean paused = true;
		controller.clearScreen();
		while(paused==true)
		{
			System.out.println(" Hero health is "+this.theHero.getHitPoints());
			System.out.println(" You have "+theHero.getHealthPotions()+" Health Potions");
			System.out.println(" You have "+theHero.getVisionPotions()+" Vision Potions");
			System.out.println(" Type 1 to use a vision Potion");
			System.out.println(" Type 2 to use a health Potion");
			System.out.println("Type q to leave this menu");
			char action = (char) System.in.read();
			if(action=='1')this.theHero.useHealthPotion();
			else if(action=='2')this.theHero.useVisionPotion();
			else if(action=='Q'||action=='q')
			{
				controller.createView();
				return;
			}
		}
		
	}

	public void enterRoom() throws Exception
	{
		controller.setRoom(theRoom);
		controller.createView();

		if (theRoom.isExit())
		{
			controller.updateView(theHero, "This must be the exit!");
			TimeUnit.SECONDS.sleep(2);
			controller.createView();
			TimeUnit.MILLISECONDS.sleep(500);

			if (theHero.getPillarCount() == 4)
			{
				controller.updateView(theHero, "At last, my quest is complete!");
				TimeUnit.SECONDS.sleep(2);
				controller.createView();
				TimeUnit.MILLISECONDS.sleep(500);

				playerWon = true;
				return;
			}
			else
			{
				controller.updateView(theHero, "Alas, the door is locked!");
				TimeUnit.SECONDS.sleep(2);
				controller.createView();
				controller.updateView(theHero, 
						      "Its mechanism must be controlled by the four lost pillars."
						     );
				TimeUnit.SECONDS.sleep(2);
				controller.createView();
			}
		}

		if (theRoom.getGraphic("Pit") != null)
		{
			controller.updateView(theHero, "These pits are the pits!");
			TimeUnit.SECONDS.sleep(1);
			theHero.modifyHitPoints(((int) (Math.random() * 20) + 1) * -1);
			TimeUnit.SECONDS.sleep(1);
			controller.createView();

			if (!theHero.isAlive())
			{
				playerLost = true;
				return;
			}
		}

		if (theRoom.getMonster() != null)
		{
			theMonster = theRoom.getMonster();
			battle();
		}

		if (!playerLost && !playerQuit)
		{
			loot();
			navigate();
		}
	}

	public static void main(String[] args) throws Exception
	{
		Dungeon game = new Dungeon();
		game.play();
	}
}
