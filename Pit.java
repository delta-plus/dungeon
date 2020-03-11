package dungeon;

import java.io.File;
import java.util.Scanner;

public class Pit implements Drawable
{
	private int height;
	private File spriteFile;
	private Scanner sprite;
	private String type;

	public Pit() throws Exception
	{
		height = -4;
		spriteFile = new File("sprites/PitSprite.txt");
		sprite = new Scanner(spriteFile);
		type = "Pit";
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
