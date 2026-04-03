import java.util.ArrayList;

public class Abilities {
    public static int critCalc(int dmg, Fighter user)
    {
        int critChance = user.getCritChance();
        double critMulti = (user.getCritMultiplier()/100.0) + 1;
        int random = (int)(Math.random() * 100) + 1;
        if (random>critChance)
        {
            return dmg;
        }
        else
        {
            System.out.println(user.getName() + " lands a critical hit!");
            return (int)(dmg*critMulti);
        }
    }

    public enum AbilityTypes {
        //tier 1
        SLASH {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = user.getAttack() + (int)(Math.random() * 4 -2);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses slash against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        SMACK {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = user.getAttack() + (int)(Math.random() * 10 -4);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses smack against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        SWEEP {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                System.out.println(user.getName() + " uses sweep!");
                for(int i = 0; i<targets.size(); i++)
                {
                    int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 4 -3)) * 0.5);
                    int calcDMG = baseDMG - targets.get(i).getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    targets.get(i).takeDamage(calcDMG);
                    System.out.println("Deals " + calcDMG + " damage! " + targets.get(i).getName() + " now has " + targets.get(i).getHealth() + "hp left!");
                }
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        HEAVY_SLASH {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = user.getAttack() + (int)(Math.random() * 5 -1);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses heavy slash against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        DOUBLE_SWING {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                System.out.println(user.getName() + " uses double swing against " + target.getName() + "!");
                for (int i = 0; i<2; i++)
                {
                    int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 4 -3)) * 0.7);
                    int calcDMG = baseDMG - target.getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    target.takeDamage(calcDMG);
                    System.out.println("Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                }
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        //2
        SPEED_BLITZ {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 4)) * 0.7);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses speed blitz against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                int speedCycles = (user.getSpeed() - target.getSpeed())/10;
                for (int i = 0; i<speedCycles; i++)
                {
                    baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 4 -8)) * 0.4);
                    calcDMG = baseDMG - target.getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    target.takeDamage(calcDMG);
                    System.out.println(user.getName() + " does an additional hit against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                }

                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        BOUNCE_SHOT {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                System.out.println(user.getName() + " uses bounce shot!");
                for(int i = 0; i<5; i++)
                {
                    int randomTarget = (int)(Math.random() * targets.size());
                    int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 4 -8)) * 0.4);
                    int calcDMG = baseDMG - targets.get(randomTarget).getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    targets.get(randomTarget).takeDamage(calcDMG);
                    System.out.println("Deals " + calcDMG + " damage! " + targets.get(randomTarget).getName() + " now has " + targets.get(randomTarget).getHealth() + "hp left!");
                }
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        SIPHON {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 10 -5)) * 1.3);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                user.heal(calcDMG/2);
                System.out.println(user.getName() + " uses siphon against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println(user.getName() + " heals " + (calcDMG/2) + "! " + user.getName() + " now has " + user.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        DEFENSE_CRACK {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 10 -5)) * 1.3);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                target.breakDefense(7);
                System.out.println(user.getName() + " uses defense crack against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println(target.getName() + " loses 7 defense!  " + target.getName() + " now has " + target.getDefense() + "defense left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        //3
        STORM {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                System.out.println(user.getName() + " uses storm!");
                for(int i = 0; i<targets.size(); i++)
                {
                    int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 12 -3)) * 1);
                    int calcDMG = baseDMG - targets.get(i).getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    targets.get(i).takeDamage(calcDMG);
                    System.out.println("Deals " + calcDMG + " damage! " + targets.get(i).getName() + " now has " + targets.get(i).getHealth() + "hp left!");
                }
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        FURY_STRIKE {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = user.getAttack() + (int)((double)(user.getAttack() + (int)(Math.random() * 25 -8)) * 1.6);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                user.raiseAttack(7);
                System.out.println(user.getName() + " uses fury strike against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println(user.getName() + " attack rises by 7! " + user.getName() + " now has " + user.getAttack() + "attack!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        GAMBLE_BARRAGE {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int random = (int)(Math.random() * 5) + 1;
                System.out.println(user.getName() + " uses gamble barrage against " + target.getName() + "!");
                for (int i = 0; i<random; i++)
                {
                    int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 50 -25)) * 0.5);
                    int calcDMG = baseDMG - target.getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    target.takeDamage(calcDMG);
                    System.out.println("Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                }
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        //Special
        CROSS_SWIPE {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                System.out.println(user.getName() + " uses cross swipe!");

                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 4 -2)) * 0.8);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println("Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");

                baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 4 -8)) * 0.7);
                int randomTarget = (int)(Math.random() * targets.size());
                calcDMG = baseDMG - targets.get(randomTarget).getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                targets.get(randomTarget).takeDamage(calcDMG);
                System.out.println("Deals " + calcDMG + " damage! " + targets.get(randomTarget).getName() + " now has " + targets.get(randomTarget).getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        //Tier 1 Enemies
        BITE {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = user.getAttack() + (int)(Math.random() * 5 -1);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses bite against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        SHOOT {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = user.getAttack() + (int)(Math.random() * 2);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses shoot against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        AXE_CHOP {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 8 -5)) * 1.1);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses axe chop against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        REGENERATE {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                user.heal(12);
                System.out.println(user.getName() + " uses regenerate to gain 12 health!" + user.getName() + " now has " + user.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
            }
        },
        CRITICAL_STAB {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 3 -1)) * 1.4);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses critical stab against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        CLEAVE {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                System.out.println(user.getName() + " uses cleave against " + target.getName() + "!");
                for (int i = 0; i<2; i++)
                {
                    int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 6 -5)) * 0.6);
                    int calcDMG = baseDMG - target.getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    target.takeDamage(calcDMG);
                    System.out.println("Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                }
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        RAGE {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                user.raiseAttack(6);
                System.out.println(user.getName() + " uses rage to raise its attack by 6!" + user.getName() + " now has " + user.getAttack() + "attack!");
                System.out.println("-----------------------------------------------");
            }
        },
        SOUL_CONTRACT {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 8 -2)) * 0.8);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                user.heal(calcDMG/2);
                System.out.println(user.getName() + " uses soul contract against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println(user.getName() + " heals " + (calcDMG/2) + "! " + user.getName() + " now has " + user.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        VOID_SCRATCH {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 10 -5)) * 1.5);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                System.out.println(user.getName() + " uses void scratch against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        FIRE_BREATH {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                System.out.println(user.getName() + " uses fire breath against " + target.getName() + "!");
                for (int i = 0; i<10; i++)
                {
                    int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 3 -7)) * 0.2);
                    int calcDMG = baseDMG - target.getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    target.takeDamage(calcDMG);
                    System.out.println("Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                }
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        ROAR {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 10 -5)) * 1.2);
                int calcDMG = baseDMG - target.getDefense();
                calcDMG = critCalc(calcDMG, user);
                if (calcDMG < 1)
                {
                    calcDMG = 1;
                }
                target.takeDamage(calcDMG);
                target.raiseAttack(-4);
                System.out.println(user.getName() + " uses roar against " + target.getName() + "! Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                System.out.println(target.getName() + " loses 4 attack!  " + target.getName() + " now has " + target.getAttack() + "attack left!");
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        GRAB {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                int random = (int)(Math.random() * 4) + 1;
                System.out.println(user.getName() + " uses grab against " + target.getName() + "!");
                for (int i = 0; i<random; i++)
                {
                    int baseDMG = (int)((double)(user.getAttack() + (int)(Math.random() * 5 -3)) * 0.6);
                    int calcDMG = baseDMG - target.getDefense();
                    calcDMG = critCalc(calcDMG, user);
                    if (calcDMG < 1)
                    {
                        calcDMG = 1;
                    }
                    target.takeDamage(calcDMG);
                    System.out.println("Deals " + calcDMG + " damage! " + target.getName() + " now has " + target.getHealth() + "hp left!");
                }
                System.out.println("-----------------------------------------------");
                if(target instanceof Player)
                {
                    ((Player)target).checkPlayerDeath();
                }
            }
        },
        MORAL_SCREAM {
            @Override
            public void use(Fighter user, ArrayList<Enemy> targets, Fighter target)
            {
                user.heal(10);
                user.breakDefense(-3);
                user.raiseAttack(4);
                System.out.println(user.getName() + " uses moral scream!");
                System.out.println(user.getName() + " now has " + user.getHealth() + "hp left!");
                System.out.println(user.getName() + " now has " + user.getDefense() + "defense!");
                System.out.println(user.getName() + " now has " + user.getAttack() + "attack!");
                System.out.println("-----------------------------------------------");
            }
        };

        public abstract void use(Fighter user, ArrayList<Enemy> targets, Fighter target);
    }
}
