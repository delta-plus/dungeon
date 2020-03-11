package dungeon;

import java.io.File;
import java.util.Scanner;

public class EmptySpace implements Drawable
{
	private int height;
	private File spriteFile;
	private Scanner sprite;
	private String type;

	public EmptySpace() throws Exception
	{
		height = 0;
		spriteFile = new File("sprites/EmptySpaceSprite.txt");
		sprite = new Scanner(spriteFile);
		type = "Empty Space";
	}

	public int getHeight()
	{
		return height;
	}

	public Scanner getSprite()
	{
		return sprite;
	}

	public void refreshSprite() throws Exception
	{
		sprite = new Scanner(spriteFile);
	}

	public String getType()
	{
		return type;
	}
}
