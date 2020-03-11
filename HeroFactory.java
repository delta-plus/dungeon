package dungeon;

public class HeroFactory 
{
    public Hero createHero(int choice, ViewController controller) throws Exception
    {
        if (choice == 1) 
        {
            return new Warrior(controller);
        } 
        else if (choice == 2) 
        {
            return new Sorceress(controller);
        } 
        else if (choice == 3) 
        {
            return new Thief(controller);
        } 
        else 
        {
            System.out.println("invalid choice, returning Thief");
            return new Thief(controller);
        }
    }
}
