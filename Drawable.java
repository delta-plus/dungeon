package dungeon;

import java.util.Scanner;

public interface Drawable
{
	public int getHeight();
	public String getType();
	public Scanner getSprite();
	public void refreshSprite() throws Exception;
}
