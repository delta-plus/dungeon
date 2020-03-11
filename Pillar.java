package dungeon;

import java.io.File;
import java.util.Scanner;

public class Pillar implements Drawable
{
	private int height;
	private File spriteFile;
	private Scanner sprite;
	private String type;

	public Pillar() throws Exception
	{
		height = 9;
		spriteFile = new File("sprites/PillarSprite.txt");
		sprite = new Scanner(spriteFile);
		type = "Pillar";
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
