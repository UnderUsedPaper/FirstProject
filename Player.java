import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player implements Fighter {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private int level;
    private int statPoints;
    private int xp;
    private int critChance;
    private int critMultiplier;
    private int speed;
    private ArrayList<Abilities.AbilityTypes> abilities;
    private Scanner input;

    public Player(String name, int level, Scanner input) {
        this.name = name;
        this.level = level;
        this.health = 100;
        this.attack = 10;
        this.defense = 0;
        this.statPoints = 0;
        this.xp = 0;
        this.critChance = 5;
        this.critMultiplier = 100;
        this.speed = 100;
        this.abilities = new ArrayList<>(List.of(Abilities.AbilityTypes.SLASH, Abilities.AbilityTypes.SMACK));
        this.input = input;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void checkPlayerDeath()
    {
        if(this.health < 1)
        {
            GameSystem.gameOver();
        }
    }

    public String getName()
    {
        return this.name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense()
    {
        return defense;
    }
    
    public int getSpeed()
    {
        return speed;
    }

    public int getCritChance()
    {
        return critChance;
    }

    public int getCritMultiplier()
    {
        return critMultiplier;
    }

    public int getStatPoints()
    {
        return statPoints;
    }

    public void giveStatPoints(int statPoints)
    {
        this.statPoints += statPoints;
    }

    public ArrayList<Abilities.AbilityTypes> getAbilities()
    {
        return abilities;
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

    public void raiseSpeed(int amount)
    {
        this.speed += amount;
    }

    public void raiseCritChance(int amount)
    {
        this.critChance += amount;
    }

    public void setAttack(int amount)
    {
        this.attack = amount;
    }

    public void setHealth(int amount)
    {
        this.health = amount;
    }

    public void setSpeed(int amount)
    {
        this.speed = amount;
    }

    public void setCritMultiplier(int amount)
    {
        this.critMultiplier = amount;
    }

    public void setCritChance(int amount)
    {
        this.critChance = amount;
    }

    public void gainAbility(Abilities.AbilityTypes ability)
    {
        this.abilities.add(ability);
    }

    public void gainXP(int xp) {
        this.xp += xp;
        if (this.xp >= level * 5 + 20) {
            xp-=level * 5 + 20;
            levelUp();
        }
    }

    public void levelUp() {
        this.level++;
        this.statPoints += 5;
        this.xp = 0;
        System.out.println("You have leveled up to level " + this.level + ".");
    }

    public void useStatPoint() {
        System.out.println("What category do you want to invest in? (Health) (Attack) (Crits) (Speed)");
        String answer = input.nextLine().toUpperCase();
        while (answer.equals("HEALTH") != true && answer.equals("ATTACK") != true && answer.equals("CRITS") != true && answer.equals("SPEED")!=true)
        {
            System.out.println("Invalid. Please try again. (Health) (Attack) (Crits) (Speed)");
            answer = input.nextLine().toUpperCase();
        }

        System.out.println("How many stat points do you want to invest? Current stat points: " + this.statPoints);
        int answer2 = input.nextInt();
        input.nextLine();
        if (answer2 > this.statPoints)
        {
            System.out.println("Insufficient Stat Points");
            return;
        }
        this.statPoints-=answer2;
        if (answer.equals("HEALTH"))
        {
            this.health += answer2 * 4;
            System.out.println(this.name + "'s health is now " + this.health + "!");
        }
        else if (answer.equals("ATTACK"))
        {
            this.attack += answer2;
            System.out.println(this.name + "'s attack is now " + this.attack + "!");
        }
        else if (answer.equals("CRITS"))
        {
            this.critChance += answer2;
            this.critMultiplier += 3 * answer2;
            System.out.println(this.name + "'s critical chance is now " + this.critChance + "%!");
            System.out.println(this.name + "'s critical multiplier is now " + this.critMultiplier + "%!");
        }
        else
        {
            this.speed += 2 * answer2;
            System.out.println(this.name + "'s speed is now " + this.speed + "!");
        }
    }

    public void statCheck()
    {
        System.out.println(this.name + "'s level is " + this.level + " with " + this.xp + "/" + (this.level*5+20) + " xp needed for the next level.");
        System.out.println(this.name + "'s health is " + this.health + ".");
        System.out.println(this.name + "'s attack is " + this.attack + ".");
        System.out.println(this.name + "'s critical chance is " + this.critChance * 100 + "%.");
        System.out.println(this.name + "'s critical multiplier is " + this.critMultiplier + "%.");
        System.out.println(this.name + "'s speed is " + this.speed + ".");
    }

    public String toString()
    {
        return name + " is level " + level + "!\n" + "Health: " + health + " | Attack: " + attack + " | Defense: " + defense + " | Speed: " + speed + " | Crit Damage: " + critMultiplier 
        + " | Crit Chance: " + critChance + "\nAbilities: " + abilities;
    }
}
