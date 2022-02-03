import Characters.RPGCharacter;

import java.util.ArrayList;
import java.util.Scanner;

public class Battle {
    ArrayList<RPGCharacter> characters = new ArrayList<>();

    public Battle(RPGCharacter ch1, RPGCharacter ch2) {
        characters.add(ch1);
        characters.add(ch2);
    }

    public void endBattle(RPGCharacter person, RPGCharacter enemy) {
        if (person.isAlive()) {
            person.getExperience(enemy.takeExperience());
            person.getGold(enemy.takeGold());
            System.out.println("Вы победили! Ваша награда: " + enemy.takeGold() + " золота и " + (int) enemy.takeExperience() + " опыта");
        } else {
            enemy.getExperience(person.takeExperience());
            enemy.getGold(person.takeGold());
            System.out.println("Вы проиграли бой");
        }
    }

    public void characterAttack(RPGCharacter person, RPGCharacter enemy) {
        if (person.isAlive()
                && enemy.isAlive()) {
            double damage = person.attack(enemy);
            if (damage > 0) {
                System.out.println(person.getName() + " нанёс " + damage + " урона " + enemy.getName());
                person.attack(enemy);
            } else System.out.println(person.getName() + " промахнулся");
        } else return;
    }

    public void startBattle() {
        RPGCharacter first = characters.get(0);
        RPGCharacter second = characters.get(1);
        int round = 1;
        while (first.isAlive() && second.isAlive()) {
            System.out.println("Раунд " + round++);
            System.out.println(getStatistic(first, second));
            Scanner s = new Scanner(System.in);
            System.out.println("Бой\n" + "1. Атаковать\n" + "2. Выпить зелье (" + first.getFlask() + ')' + '\n'
                    + "3. Пропустить ход\n" + "4. Aвтобой");
            switch (s.nextInt()) {
                case 1: {
                    characterAttack(first, second);
                    characterAttack(second, first);
                }
                break;
                case 2: {
                    first.useFlask();
                    second.attack(first);
                }
                break;
                case 3: {
                    second.attack(first);
                    break;
                }
                case 4: {
                    characterAttack(first, second);
                    characterAttack(second, first);
                    while (first.isAlive() && second.isAlive()) {
                        System.out.println("Раунд " + round++);
                        characterAttack(first, second);
                        characterAttack(second, first);
                        System.out.println(getStatistic(first, second));
                    }
                    endBattle(first, second);
                    return;
                }
            }
        }
        endBattle(first, second);

        while (first.isAlive() && second.isAlive()) {
            System.out.println("Раунд " + round++);
            characterAttack(first, second);
            characterAttack(second, first);
            System.out.println(getStatistic(first, second));
        }
        endBattle(first, second);
    }

    private String getStatistic(RPGCharacter first, RPGCharacter second) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("|%20s|", first.getName())).append(String.format("|%-20s|", second.getName()));
        result.append('\n').append(String.format("|%20s|", "hp " + (int) first.getHp())).append(String.format("|%-20s|", "hp " + (int) second.getHp()));
        return result.toString();
    }
}
