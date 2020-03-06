import java.util.Random;
public class MonsterFactory {

	
	
	public static Monster createMonster(ViewController controller, int choice) 
			throws Exception  
			{
				

				switch(choice)
				{

					case 1: return new Ogre(controller);

					case 2: return new Gremlin(controller);

					case 3: return new Skeleton(controller);

					default: return new Skeleton(controller);
				}
			}
	
	
		public static Monster createRandomMonster(ViewController controller)
		{
			Random random = new Random();
			int ran = random.nextInt(3);
			Monster newMonster = createMonster(controller, ran);
			return newMonster;
		}
}
