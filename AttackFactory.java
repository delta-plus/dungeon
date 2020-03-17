package dungeon;

import java.util.HashMap;

public class AttackFactory {
    private HashMap<String, Attack> attacks = new HashMap<String, Attack>();
    private BasicAttack attack = new BasicAttack();
    
    public BasicAttack getBasicAttack() {
        return attack;
    }

    public Attack getAttack(String attackName) {
        Attack attack = attacks.get(attackName);
        if (attack == null) {
            if (attackName == "surpriseattack") {
                attacks.put(attackName, new SurpriseAttack());
            } else if (attackName == "crushingblow") {
                attacks.put(attackName, new CrushingBlow());
            }
            attack = attacks.get(attackName);
        }
        return attack;
    }
}