import java.util.ArrayList;

public interface Fighter {
    String getName();
    int getHealth();
    int getSpeed();
    int getAttack();
    int getDefense();
    void takeDamage(int damage);
    int getCritChance();
    int getCritMultiplier();
    void heal(int amount);
    void breakDefense(int amount);
    void raiseAttack(int amount);
    ArrayList<Abilities.AbilityTypes> getAbilities();
}