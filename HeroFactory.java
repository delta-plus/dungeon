package dungeon;

public class HeroFactory 
{
    public Hero createHero(int choice, ViewController controller, AttackFactory attacks) throws Exception
    {
        if (choice == 1) 
        {
            return new Warrior(controller, attacks);
        } 
        else if (choice == 2) 
        {
            return new Sorceress(controller, attacks);
        } 
        else if (choice == 3) 
        {
            return new Thief(controller, attacks);
        } 
        else 
        {
            System.out.println("invalid choice, returning Thief");
            return new Thief(controller, attacks);
        }
    }
}
