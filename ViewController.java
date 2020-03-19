package dungeon;

import java.util.Scanner;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ViewController 
{
	private Hero theHero;
	private Room theRoom;

	public ViewController() {}

	public void setHero(Hero theHero)
	{
		this.theHero = theHero;
	}

	public void setRoom(Room theRoom)
	{
		this.theRoom = theRoom;
	}

	public void drawLowerScene()
	{
		boolean hasPit = false;
		Scanner pitSprite = null;
		boolean hasMonster = false;
		Monster theMonster = null;

		if (theRoom.getGraphic("Pit") != null)
		{
			hasPit = true;
			pitSprite = theRoom.getGraphic("Pit").getSprite();
		}

		if (theRoom.getMonster() != null)
		{
			hasMonster = true;
			theMonster = theRoom.getMonster();
		}

		if (hasMonster && hasPit)
		{
			System.out.println("-    ------------------------------------------");
			System.out.println(pitSprite.nextLine() + " " + 
					   theHero.getName() + 
					   "                  " + 
					   theMonster.getName());
			System.out.println(pitSprite.nextLine() + " " + 
					   "HP: " + theHero.getHitPoints() + 
					   "                  " + 
					   "HP: " + theMonster.getHitPoints());
			System.out.println(pitSprite.nextLine() + " Turns: " + theHero.getTurns());
			System.out.println(pitSprite.nextLine());
			System.out.println(pitSprite.nextLine());
			System.out.println();

			for (String choice : theHero.getActionList()) 
			{
				System.out.println(choice);
			}

			System.out.println(Integer.toString(theHero.getActionList().length + 1) + ". Quit");
			System.out.println();
		}
		else if (hasMonster && !hasPit)
		{
 			System.out.println("-----------------------------------------------");
			System.out.println("       " + 
					   theHero.getName() + 
					   "                  " + 
					   theMonster.getName());
			System.out.println("       " + 
					   "HP: " + Integer.toString(theHero.getHitPoints()) + 
					   "                  " + 
					   "HP: " + Integer.toString(theMonster.getHitPoints()));
			System.out.println("       Turns: " + theHero.getTurns());
			System.out.println();

			for (String choice : theHero.getActionList()) 
			{
				System.out.println(choice);
			}

			System.out.println(Integer.toString(theHero.getActionList().length + 1) + ". Quit");
			System.out.println();
		}
		else if (!hasMonster && hasPit)
		{
			System.out.println("-    ------------------------------------------");
			System.out.println(pitSprite.nextLine() + " " + 
					   theHero.getName());
			System.out.println(pitSprite.nextLine()+ " " + 
					   "HP: " + Integer.toString(theHero.getHitPoints()));
			System.out.println(pitSprite.nextLine());
			System.out.println(pitSprite.nextLine());
			System.out.println(pitSprite.nextLine());
			System.out.println();
		}
		else
		{
 			System.out.println("-----------------------------------------------");
			System.out.println("       " + 
					   theHero.getName());
			System.out.println("       " + 
					   "HP: " + Integer.toString(theHero.getHitPoints()));
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	public void drawUpperScene() throws Exception
	{
		String line;

		for (int linesDrawn = 0; linesDrawn < 10; linesDrawn++)
		{
			line = "";

			for (Drawable graphic : theRoom.getGraphics())
			{
				if (graphic.getHeight() < 10 - linesDrawn)
				{
					line += "      ";
				} 
				else 
				{
					line += graphic.getSprite().nextLine();
				}
			}

			System.out.println(line);
		}
	}

	public void drawScene() throws Exception
	{
		drawUpperScene();
		drawLowerScene();

		for (Drawable graphic : theRoom.getGraphics())
		{
			graphic.refreshSprite();
		}
	}

	public void createView() throws Exception
	{
		clearScreen();
		System.out.println();
		System.out.println();

		drawScene();
	}

	public void updateView(DungeonCharacter updatedCharacter, String action) throws Exception
	{
		if (updatedCharacter == theHero) 
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
		if (updatedCharacter == theHero) 
		{
			drawHero(hitPointChange);
		} 
		else 
		{
			drawMonster(hitPointChange);
		}
	}

	private void drawHero(String action) throws Exception
	{
		clearScreen();
		System.out.println();
		System.out.println("        " + action);

		drawScene();
	}

	private void drawHero(int hitPointChange) throws Exception
	{
		clearScreen();
		System.out.println();
		System.out.println("        " + Integer.toString(hitPointChange));

		drawScene();
	}

	private void drawMonster(String action) throws Exception
	{
		String buffer = "";

		for (int i = 36 - (action.length() + 1); i > 0; i--) 
		{
			buffer += " ";
		}

		clearScreen();
		System.out.println();
		System.out.println(buffer + action + " ");

		drawScene();
	}

	private void drawMonster(int hitPointChange) throws Exception 
	{
		String buffer = "";

		for (int i = 36 - (Integer.toString(hitPointChange).length() + 1); i > 0; i--) 
		{
			buffer += " ";
		}

		clearScreen();
		System.out.println();
		System.out.println(buffer + Integer.toString(hitPointChange) + " ");

		drawScene();
	}

	public void eraseMonster() throws Exception
	{
		Drawable tempMonster = theRoom.getGraphic("Monster");
		theRoom.removeMonster();
		theRoom.replaceGraphic(5, new EmptySpace());
		createView();
		TimeUnit.MILLISECONDS.sleep(250);
		tempMonster.refreshSprite();
		theRoom.replaceGraphic(5, tempMonster);
		createView();
		TimeUnit.MILLISECONDS.sleep(250);
		theRoom.replaceGraphic(5, new EmptySpace());
		createView();
		TimeUnit.MILLISECONDS.sleep(250);
		tempMonster.refreshSprite();
		theRoom.replaceGraphic(5, tempMonster);
		createView();
		TimeUnit.MILLISECONDS.sleep(250);
		theRoom.replaceGraphic(5, new EmptySpace());
		createView();
	}

	public void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
