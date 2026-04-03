import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TurnBasedBattle {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Fighter> turnOrder;
    private int totalXPGain;
    private Scanner input;

    public TurnBasedBattle(Player player, ArrayList<Enemy> enemies, Scanner input)
    {
        this.player = player;
        this.enemies = enemies;
        this.turnOrder = new ArrayList<Fighter>();
        this.input = input;
        for(Enemy enemy1 : enemies)
        {
            this.totalXPGain += enemy1.getXP();
        }
    }

    public void sortTurns()
    {
        this.turnOrder.add(player);
        this.turnOrder.addAll(enemies);
        turnOrder.sort((a,b) -> b.getSpeed() - a.getSpeed());
    }

    public void removeDead()
    {
        enemies.removeIf(f -> f.getHealth() <= 0);
        turnOrder.removeIf(e -> e.getHealth() <= 0);
    }

    public boolean battleEnd()
    {
        return player.getHealth() < 0 || enemies.isEmpty();
    }

    public void runBattle()
    {
        for(Enemy enemy : enemies)
        {
            System.out.println(enemy);
        }
        int savedHealth = player.getHealth();
        int savedAttack = player.getAttack();
        int savedSpeed = player.getSpeed();
        int savedCritMult = player.getCritMultiplier();
        int savedCritChance = player.getCritChance();

        while (battleEnd() != true)
        {
            sortTurns();
            while(turnOrder.size()>0)
            {
                if(turnOrder.get(0) instanceof Player)
                {
                    playerTurn();
                }
                else
                {
                    enemyTurn(turnOrder.get(0));
                }
                turnOrder.remove(0);
                removeDead();
            }
        }
        if(player.getHealth()>0)
        {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Congrats! You won!");
            System.out.println("You gained " + this.totalXPGain + " XP!");
            player.gainXP(totalXPGain);
        }

        player.setHealth(savedHealth);
        player.setAttack(savedAttack);
        player.setSpeed(savedSpeed);
        player.setCritMultiplier(savedCritMult);
        player.setCritChance(savedCritChance);
    }

    public void enemyTurn(Fighter enemy)
    {
        System.out.println(enemy.getName() + " takes action!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        ArrayList<Abilities.AbilityTypes> avaliableAbilities = enemy.getAbilities();
        int selection = (int)(Math.random() * avaliableAbilities.size());
        avaliableAbilities.get(selection).use(enemy, enemies, player);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void playerTurn()
    {
        System.out.println("What do you want to do? (Fight) (Check)");
        String answer = input.nextLine().toUpperCase();
        while (answer.equals("FIGHT") == false && answer.equals("CHECK") == false)
        {
            System.out.println("Please retype. (Fight) (Check)");
            answer = input.nextLine().toUpperCase();
        }
        if(answer.equals("FIGHT"))
        {
            System.out.println("What ability do you want to use? " + this.player.getAbilities());
            answer = input.nextLine().toUpperCase();

            Abilities.AbilityTypes abilityAnswer;
            try
            {
                abilityAnswer = Abilities.AbilityTypes.valueOf(answer);
            }
            catch(IllegalArgumentException e)
            {
                abilityAnswer = null;
            }

            while (abilityAnswer == null || !this.player.getAbilities().contains(abilityAnswer))
            {
                System.out.println("Please chose a valid ability! " + this.player.getAbilities());
                answer = input.nextLine().toUpperCase();

                try
                {
                    abilityAnswer = Abilities.AbilityTypes.valueOf(answer);
                }
                catch(IllegalArgumentException e)
                {
                    abilityAnswer = null;
                }
            }
            int answer2 = -1;
            while (answer2<1 || answer2>enemies.size())
            {
                System.out.println("Choose a target based on index: ");
                for (int i = 0; i < enemies.size(); i++)
                {
                    System.out.print("[" + (i+1) + "] " + enemies.get(i).getName() + " | ");
                }
                try
                {
                    answer2 = input.nextInt();
                    input.nextLine();
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Please print a valid number.");
                    input.nextLine();
                }
            }
            int abilityIndex = this.player.getAbilities().indexOf(abilityAnswer);
            this.player.getAbilities().get(abilityIndex).use(player, enemies, enemies.get(answer2-1));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        else if (answer.equals("CHECK"))
        {
            for (int i = 0; i < enemies.size(); i++)
            {
                System.out.println(enemies.get(i));
            }
            playerTurn();
        }
    }
}
