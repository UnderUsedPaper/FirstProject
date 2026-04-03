import java.util.Scanner;
import java.util.ArrayList;

public class RPGRunner {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the terminal RPG! What do you want to name your character?");
        String name = input.nextLine();
        Player player = new Player(name, 0, input);
        GameSystem gameSystem = new GameSystem(player, input);
        for (int round = 0; round<3; round++)
        {
            gameSystem.increaseTier();
            System.out.println("Your world level is " + gameSystem.getTier() + "!");
            for (int cycle = 0; cycle<6; cycle++)
            {
                boolean valid = gameSystem.action();
                if (!valid)
                {
                    cycle--;
                }
            }
        }
        System.out.println("Congrats, you win!");
        input.close();
    }
}