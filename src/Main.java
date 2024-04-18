import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {290, 270, 250, 300, 500, 200, 150, 130};
    public static int[] heroesDamage = {10, 15, 20, 0, 5, 10, 0, 25};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Witcher", "Thor"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        printStatistics();
        Medic();
        Golem();
        Lucky();
        Witcher();
        Thor();
    }

    public static void Medic() {
        Random random = new Random();
        int randomIndex = random.nextInt(10, 40);
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesAttackType[i].equals("Medic")) {
                continue;
            }
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] += randomIndex;
            }
            break;

        }
    }

    public static void Thor() {
        Random random = new Random();
        boolean stun = random.nextBoolean();
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[7] > 0 && stun == true) {
                bossDamage = 0;
                System.out.printf("босс оглушен");
                break;
            } else if (stun == false) {
                bossDamage = 50;
            }
        }
    }

    public static void Golem() {
        int golem = bossDamage / 5;
        if (heroesHealth[4] > 0 && bossHealth > 0) {
            heroesHealth[4] -= golem;
            bossDamage -= golem;
        } else {
            bossDamage = 50;
        }
    }

    public static void Lucky() {
        Random random = new Random();
        boolean lucky = random.nextBoolean();
        if (heroesHealth[5] > 0) {
            if (!(lucky)) {
                heroesHealth[5] = heroesHealth[5] + bossDamage;
                if (heroesHealth[5] > 130) {
                    heroesHealth[5] = heroesHealth[5] - bossDamage;
                } else if (lucky) {
                    heroesHealth[5] = heroesHealth[5];
                }
            }
        }
    }

    public static void Witcher() {
        if (heroesHealth[6] > 0)
            for (int i = 0; i < heroesAttackType.length; i++) {
                if (heroesHealth[i] == 0) ;
                heroesHealth[i] += heroesHealth[6];
                heroesHealth[6] = 0;
                break;


            }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomInd = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomInd];
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + " damage: " + heroesDamage[i]);
        }
    }
}