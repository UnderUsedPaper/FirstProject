import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public enum Events
{
    //Good Events
    TREASURE_CHEST(eventType.GOOD, 0){
        @Override
        public void execute(Player player, GameSystem gameSystem, Scanner input)
        {
            int tier = gameSystem.getTier();
            System.out.println("You find a treasure chest!");
            System.out.println("What do you want to do? (1) Open chest (2) Leave chest");
            int answer = answerChoices(new ArrayList<>(List.of(1, 2)), input);
            if(answer==1)
            {
                int roll = perfectRandom(1, 2);
                switch(roll)
                {
                    case 1:
                        System.out.println("You found " + 5 * tier + " stat points!");
                        player.giveStatPoints(5 * tier);
                        break;
                    case 2:
                        randomAbility(player,tier);
                        break;
                }
            }
            else
            {
                System.out.println("You left...");
            }
        }
    },
    SWORDSMAN(eventType.GOOD, 0){
        @Override
        public void execute(Player player, GameSystem gameSystem, Scanner input)
        {
            System.out.println("You find a swordsman training in the woods!");
            System.out.println("What do you want to do? (1) Talk to him (2) Leave");
            int answer = answerChoices(new ArrayList<>(List.of(1, 2)), input);
            if(answer==1)
            {
                System.out.println("He wants you to train with him. (1) Accept (2) Deny");
                answer = answerChoices(new ArrayList<>(List.of(1, 2)), input);
                if(answer==1)
                {
                    if(player.getAbilities().contains(Abilities.AbilityTypes.CROSS_SWIPE))
                    {
                        System.out.println("He makes you go through conditioning!");
                        System.out.println("You gain 7 stat points.");
                        player.giveStatPoints(7);
                    }
                    else
                    {
                        System.out.println("He teaches you a new skill!");
                        System.out.println("You learned cross swipe!");
                        player.gainAbility(Abilities.AbilityTypes.CROSS_SWIPE);
                    }
                }
                else
                {
                    System.out.println("You lets you leave without a problem...");
                }
            }
            else
            {
                System.out.println("You left...");
            }
        }
    },
    //NEUTRAL
    MYSTERY_POTION(eventType.NEUTRAL, 0){
        @Override
        public void execute(Player player, GameSystem gameSystem, Scanner input)
        {
            System.out.println("You find a mysterious potion on the floor!");
            System.out.println("What do you want to do? (1) Drink it (2) Break it (3) Leave");
            int answer = answerChoices(new ArrayList<>(List.of(1, 2, 3)), input);
            if(answer==1)
            {
                int roll = perfectRandom(1, 6);
                switch(roll)
                {
                    case 1:
                        System.out.println("You gain 25 health!");
                        player.heal(25);
                        break;
                    case 2:
                        System.out.println("You gain 10 attack!");
                        player.raiseAttack(10);
                        break;
                    case 3:
                        System.out.println("You gain 12 speed!");
                        player.raiseSpeed(12);
                        break;
                    case 4:
                        System.out.println("You lose 15 health!");
                        player.heal(-15);
                        player.checkPlayerDeath();
                        break;
                    case 5:
                        System.out.println("You lose 5 attack!");
                        player.raiseAttack(-5);
                        break;
                    case 6:
                        System.out.println("You lose 10 speed!");
                        player.raiseSpeed(-10);
                        break;
                }
            }
            else if (answer == 2)
            {
                System.out.println("The broken glass cuts you but you feel good knowing that you possibly saved someone else.");
                System.out.println("You lose 5 health.");
                player.heal(-5);
                System.out.println("You gain 6 critical chance.");
                player.raiseCritChance(6);
            }
            else
            {
                System.out.println("You left...");
            }
        }
    },
    WIZARD(eventType.NEUTRAL, 0){
        @Override
        public void execute(Player player, GameSystem gameSystem, Scanner input)
        {
            System.out.println("There is a strange wizard in front of you.");
            System.out.println("What do you want to do? (1) Talk to him (2) Avoid him");
            int answer = answerChoices(new ArrayList<>(List.of(1, 2)), input);
            if(answer==1)
            {
                System.out.println("He offers you an experimental potion that supposedly gives you more health. However, there may be errors in the recipe...");
                System.out.println("Do you still drink it? (1) Yes (2) No");
                answer = answerChoices(new ArrayList<>(List.of(1, 2)), input);
                if(answer==1)
                {
                    int healthGained = perfectRandom(-30, 30);
                    System.out.println("Your health changed by " + healthGained + "!");
                    player.heal(healthGained);
                    player.checkPlayerDeath();
                }
                else
                {
                    System.out.println("You left...");
                }
            }
            else
            {
                System.out.println("You left...");
            }
        }
    },
    //BAD
    DARK_FOREST(eventType.BAD, 0){
        @Override
        public void execute(Player player, GameSystem gameSystem, Scanner input)
        {
            System.out.println("You encounter a strange forest. There is no option but to go in. However, there are 2 paths.");
            System.out.println("What do you want to do? (1) Walk on the dark path (2) Walk on the REALLY dark and evil path");
            int answer = answerChoices(new ArrayList<>(List.of(1, 2)), input);
            if(answer==1)
            {
                System.out.println("You encounter a small band of enemies!");
                int enemyAmount = perfectRandom(2, 3);
                startRound(player, input, enemyAmount, gameSystem.getTier());
            }
            else
            {
                System.out.println("You encounter a huge horde of enemies!");
                int enemyAmount = perfectRandom(4, 5);
                startRound(player, input, enemyAmount, gameSystem.getTier());
            }
        }
    },
    EVIL_MAGE(eventType.BAD, 0){
        @Override
        public void execute(Player player, GameSystem gameSystem, Scanner input)
        {
            System.out.println("You encounter a man dressed in dark attire. He wants to take part of your strength. If you refuse, he threatens that he will summon evil monsters to attack you.");
            System.out.println("What do you want to do? (1) Sacrifice part of your power (2) Fight the evil monsters");
            int answer = answerChoices(new ArrayList<>(List.of(1, 2)), input);
            if(answer==1)
            {
                System.out.println("You lose 20 health!");
                player.heal(-20);
                player.checkPlayerDeath();
            }
            else
            {
                System.out.println("The man says that you will regret this.");
                int enemyAmount = perfectRandom(1, 4);
                startRound(player, input, enemyAmount, gameSystem.getTier());
            }
        }
    };

    public enum eventType
    {
        GOOD, NEUTRAL, BAD
    }

    private final eventType type;
    private final int tier;

    //abilities
    private static final ArrayList<Abilities.AbilityTypes> tierOneAbilities = 
        new ArrayList<Abilities.AbilityTypes>(List.of(
            Abilities.AbilityTypes.SWEEP,
            Abilities.AbilityTypes.HEAVY_SLASH,
            Abilities.AbilityTypes.DOUBLE_SWING
        ));

    private static final ArrayList<Abilities.AbilityTypes> tierTwoAbilities = 
        new ArrayList<Abilities.AbilityTypes>(List.of(
            Abilities.AbilityTypes.SPEED_BLITZ,
            Abilities.AbilityTypes.BOUNCE_SHOT,
            Abilities.AbilityTypes.SIPHON,
            Abilities.AbilityTypes.DEFENSE_CRACK
        ));

    private static final ArrayList<Abilities.AbilityTypes> tierThreeAbilities = 
        new ArrayList<Abilities.AbilityTypes>(List.of(
            Abilities.AbilityTypes.STORM,
            Abilities.AbilityTypes.FURY_STRIKE,
            Abilities.AbilityTypes.GAMBLE_BARRAGE
        ));

    //enemies
    private static final ArrayList<Enemy.EnemyType> tierOneEnemy = 
        new ArrayList<Enemy.EnemyType>(List.of(
            Enemy.EnemyType.Skeleton,
            Enemy.EnemyType.Zombie,
            Enemy.EnemyType.Spider
        ));

    private static final ArrayList<Enemy.EnemyType> tierTwoEnemy = 
        new ArrayList<Enemy.EnemyType>(List.of(
            Enemy.EnemyType.Orc,
            Enemy.EnemyType.Thief,
            Enemy.EnemyType.Minotaur
        ));

    private static final ArrayList<Enemy.EnemyType> tierThreeEnemy = 
        new ArrayList<Enemy.EnemyType>(List.of(
            Enemy.EnemyType.Demon,
            Enemy.EnemyType.Dragon,
            Enemy.EnemyType.Griffon
        ));

    Events(eventType type, int tier)
    {
        this.type = type;
        this.tier = tier;
    }

    public eventType getType()
    {
        return type;
    }

    public void startRound(Player player, Scanner input, int enemyAmount, int tier)
    {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for(int i = 0; i<enemyAmount; i++)
        {
            int randomIndex;
            Enemy enemy;
            switch(tier)
            {
                case 1:
                    randomIndex = (int)(Math.random() * tierOneEnemy.size());
                    enemy = new Enemy(tierOneEnemy.get(randomIndex));
                    enemies.add(enemy);
                    break;
                case 2:
                    randomIndex = (int)(Math.random() * tierTwoEnemy.size());
                    enemy = new Enemy(tierTwoEnemy.get(randomIndex));
                    enemies.add(enemy);
                    break;
                case 3:
                    randomIndex = (int)(Math.random() * tierThreeEnemy.size());
                    enemy = new Enemy(tierThreeEnemy.get(randomIndex));
                    enemies.add(enemy);
                    break;
            }
        }
        TurnBasedBattle battle = new TurnBasedBattle(player, enemies, input);
        battle.runBattle();
        randomAbility(player, tier);
    }

    public int answerChoices(ArrayList<Integer> choices, Scanner input)
    {
        int answer = -1;
        while (!choices.contains(answer))
        {
            try
            {
                answer = input.nextInt();
                input.nextLine();
            } 
            catch (InputMismatchException e)
            {
                input.nextLine();
            }
            if(!choices.contains(answer))
            {
                System.out.println("Please input a valid number!");
            }
        }
        return answer;
    }

    public int perfectRandom(int start, int end)
    {
        int range = end - start + 1;
        return (int)(Math.random() * range) + start;
    }

    public void randomAbility(Player player, int tier)
    {
        int randomAbility = 0;
        Abilities.AbilityTypes ability;
        switch (tier) {
            case 1:
                randomAbility = (int)(Math.random() * tierOneAbilities.size());
                ability = tierOneAbilities.get(randomAbility);
                if (player.getAbilities().contains(ability))
                {
                    System.out.println("Duplicate ability! +5 stat points instead.");
                    player.giveStatPoints(5);
                }
                else
                {
                    player.gainAbility(ability);
                    System.out.println("You gained the ability " + ability + "!");
                }
                break;
            case 2:
                randomAbility = (int)(Math.random() * tierTwoAbilities.size());
                ability = tierTwoAbilities.get(randomAbility);
                if (player.getAbilities().contains(ability))
                {
                    System.out.println("Duplicate ability! +5 stat points instead.");
                    player.giveStatPoints(5);
                }
                else
                {
                    player.gainAbility(ability);
                    System.out.println("You gained the ability " + ability + "!");
                }
                break;
            case 3:
                randomAbility = (int)(Math.random() * tierThreeAbilities.size());
                ability = tierThreeAbilities.get(randomAbility);
                if (player.getAbilities().contains(ability))
                {
                    System.out.println("Duplicate ability! +5 stat points instead.");
                    player.giveStatPoints(5);
                }
                else
                {
                    player.gainAbility(ability);
                    System.out.println("You gained the ability " + ability + "!");
                }
                break;
        }
    }

    public abstract void execute(Player player, GameSystem gameSystem, Scanner input);
}