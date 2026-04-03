import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Enemy implements Fighter{
    private EnemyType type;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int xpGain;
    private int critChance;
    private int critMultiplier;
    private ArrayList<Abilities.AbilityTypes> abilities;

    public enum EnemyType {
        Zombie, Spider, Skeleton, Orc, Thief, Minotaur, Demon, Dragon, Griffon
    }

    private static class Stats
    {
        int health, attack, defense, speed, xpGain, critChance, critMultiplier;
        ArrayList<Abilities.AbilityTypes> abilities;
        Stats(int health, int attack, int defense, int speed, int xpGain, int critChance, int critMultiplier, ArrayList<Abilities.AbilityTypes> abilities)
        {
            this.health=health;
            this.attack=attack;
            this.defense=defense;
            this.speed=speed;
            this.abilities = abilities;
            this.xpGain = xpGain;
            this.critChance = critChance;
            this.critMultiplier = critMultiplier;
        }
    }

    private static final HashMap<EnemyType, Stats> Stat_Table = new HashMap<EnemyType, Stats>();

    static {
        Stat_Table.put(EnemyType.Zombie, new Stats(30, 6, 2, 100, 12, 6, 100, new ArrayList<>(List.of(Abilities.AbilityTypes.SMACK))));
        Stat_Table.put(EnemyType.Spider, new Stats(22, 7, 0, 90, 15, 8, 100, new ArrayList<>(List.of(Abilities.AbilityTypes.BITE))));
        Stat_Table.put(EnemyType.Skeleton, new Stats(18, 8, 1, 75, 13, 5, 100, new ArrayList<>(List.of(Abilities.AbilityTypes.SHOOT))));
        Stat_Table.put(EnemyType.Orc, new Stats(44, 13, 6, 70, 18, 8, 120, new ArrayList<>(List.of(Abilities.AbilityTypes.AXE_CHOP,Abilities.AbilityTypes.REGENERATE))));
        Stat_Table.put(EnemyType.Thief, new Stats(26, 18, 0, 125, 20, 15, 125, new ArrayList<>(List.of(Abilities.AbilityTypes.CRITICAL_STAB))));
        Stat_Table.put(EnemyType.Minotaur, new Stats(33, 15, 3, 105, 17, 20, 70, new ArrayList<>(List.of(Abilities.AbilityTypes.CLEAVE,Abilities.AbilityTypes.RAGE))));
        Stat_Table.put(EnemyType.Demon, new Stats(55, 20, 3, 115, 26, 15, 100, new ArrayList<>(List.of(Abilities.AbilityTypes.SOUL_CONTRACT,Abilities.AbilityTypes.VOID_SCRATCH))));
        Stat_Table.put(EnemyType.Dragon, new Stats(60, 17, 5, 105, 28, 10, 110, new ArrayList<>(List.of(Abilities.AbilityTypes.FIRE_BREATH,Abilities.AbilityTypes.ROAR))));
        Stat_Table.put(EnemyType.Griffon, new Stats(48, 18, 3, 125, 27, 20, 90, new ArrayList<>(List.of(Abilities.AbilityTypes.GRAB,Abilities.AbilityTypes.MORAL_SCREAM))));
    }

    public Enemy(EnemyType type)
    {
        this.type = type;
        Stats s = Stat_Table.get(type);
        this.health = s.health;
        this.attack = s.attack;
        this.defense = s.defense;
        this.speed = s.speed;
        this.abilities = s.abilities;
        this.xpGain = s.xpGain;
        this.critChance = s.critChance;
        this.critMultiplier = s.critMultiplier;
    }

    public int getHealth()
    {
        return health;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getAttack()
    {
        return attack;
    }

    public int getDefense()
    {
        return defense;
    }

    public String getName()
    {
        return this.type.toString();
    }

    public int getXP()
    {
        return this.xpGain;
    }

    public int getCritChance()
    {
        return critChance;
    }

    public int getCritMultiplier()
    {
        return critMultiplier;
    }

    public ArrayList<Abilities.AbilityTypes> getAbilities()
    {
        return abilities;
    }

    public void takeDamage(int damage)
    {
        this.health -= damage;
    }

    public void heal(int amount)
    {
        this.health += amount;
    }

    public void breakDefense(int amount)
    {
        this.defense -= amount;
    }

    public void raiseAttack(int amount)
    {
        this.attack += attack;
    }

    public String toString()
    {
        return type + " | Health: " + this.health + " | Attack: " + this.attack + " | Defense: " + this.defense + " | Speed: " + this.speed;
    }
}
