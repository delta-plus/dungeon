package dungeon;

import java.util.concurrent.TimeUnit;

public class BasicAttack
{
    public void attack(DungeonCharacter opponent, DungeonCharacter hero) throws Exception 
	{
		boolean canAttack;
		int damage;

		canAttack = Math.random() <= hero.getChanceToHit();

		if (canAttack)
		{
			damage = ((int) (Math.random() * (hero.getDamageMax() - hero.getDamageMin() + 1)) + hero.getDamageMin()) * -1;
			opponent.modifyHitPoints(damage);
		}
		else
		{
			hero.getController().updateView(opponent, "Miss!");
                        TimeUnit.SECONDS.sleep(1);
		}
	}
}