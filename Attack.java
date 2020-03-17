package dungeon;

public interface Attack
{
	public void attack(DungeonCharacter opponent, Hero hero) throws Exception;
}