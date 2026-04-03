import java.util.Scanner;
import java.util.Arrays;

public class GameSystem {
    private Player player;
    private Scanner input;
    private int tier;

    public GameSystem(Player player, Scanner input)
    {
        this.player = player;
        this.input = input;
        this.tier = 0;
    }

    public boolean action()
    {
        System.out.println("What do you want to do? (Explore) (Upgrade Stats(" + player.getStatPoints() + ")) (Check Stats)");
        String answer = input.nextLine().toUpperCase();
        while(!answer.equals("EXPLORE") && !answer.equals("UPGRADE STATS") && !answer.equals("CHECK STATS"))
        {
            System.out.println("Please pick a valid option. (Explore) (Upgrade Stats(" + player.getStatPoints() + ")) (Check Stats)");
            answer = input.nextLine().toUpperCase();
        }
        switch (answer)
        {
            case "EXPLORE":
                event();
                return true;
            case "UPGRADE STATS":
                player.useStatPoint();
                return false;
            case "CHECK STATS":
                System.out.println(player);
                return false;
        }
        return false;
    }

    public void event()
    {
        int random = (int)(Math.random() * 100) + 1;
        Events event;
        if (random>80)
        {
            event = pickEvent(Events.eventType.GOOD);
        }
        else if (random>50)
        {
            event = pickEvent(Events.eventType.NEUTRAL);
        }
        else
        {
            event = pickEvent(Events.eventType.BAD);
        }
        event.execute(player, this, input);
    }

    public Events pickEvent(Events.eventType type)
    {
        Events[] filteredEvents = Arrays.stream(Events.values()).filter(e -> e.getType() == type).toArray(Events[]::new);
        Events randomEvent = filteredEvents[(int)(Math.random() * filteredEvents.length)];
        return randomEvent;
    }

    public static void gameOver()
    {
        System.out.println("Game Over!");
        System.exit(0);
    }

    public void increaseTier()
    {
        this.tier++;
    }

    public int getTier()
    {
        return this.tier;
    }
}
