import Characters.*;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        startMenu();
    }

    public static void startMenu() {
        while (true) {
            System.out.println("Text RPG");
            System.out.println("Выберите действие:\n" +
                    "1. Создать нового персонажа\n" +
                    "2. Выйти");
            Scanner s = new Scanner(System.in);
            switch (s.nextInt()) {
                case 1: {
                    startGame(createPerson());
                    break;
                }
                case 2: {
                    return;
                }
            }
        }
    }

    public static void startGame(Person player) {
        while (true) {
            System.out.println("Персонаж " + player.getName() +
                    " с характеристиками: Сила " + player.getStrange()
                    + " | Ловкость " + player.getDexterity() +
                    " | Удача " + player.getLuck() + " готов");
            System.out.println(new StringBuilder().append("Куда отправимся?\n").append("1. В тёмный лес\n").append("2. К торговцу\n").append("3. Отдохнуть (+20хп)\n").append("4. На выход").toString());
            Scanner s = new Scanner(System.in);
            switch (s.nextInt()) {
                case 1:
                    System.out.println("Вы вошли в Тёмный лес");
                    Enemy enemy = balancedEnemy(player);
                    System.out.println("На вашем пути находится " + enemy.getName() + "\n" +
                            "Действия:\n" + "1. Сразиться\n" + "2. Вернуться");
                    switch (s.nextInt()) {
                        case 1: {
                            Battle battle = new Battle(player, enemy);
                            Thread t = new Thread() {
                                @Override
                                public void run() {
                                    battle.startBattle();
                                }
                            };
                            t.start();
                            try {
                                t.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case 2: break;
                    }
                    break;
                case 2:
                    System.out.println("Торговец:\n" + "1. Купить зелье (50 золота).\n" + "2. Вернуться" );
                    switch (s.nextInt()) {
                        case 1: if (player.takeGold() >= 50) {
                            player.addFlask(1);
                        } else {
                            System.out.println("У вас недостаточно золота: " + player.takeGold());
                        } break;
                        case 2: break;
                    }
                case 3: {
                    player.chill(20);
                    break;
                }
                case 4: return;
            }
        }
    }

    public static Person createPerson() {
        System.out.println("Создание персонажа:");
        System.out.println("Введите имя персонажа");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        System.out.println("Введите праметры персонажа Сила Ловкость Удача:");
        int strange = s.nextInt();
        int dexterity = s.nextInt();
        int luck = s.nextInt();
        return new Person(name, strange, dexterity, luck, 0);
    }

    public static Enemy balancedEnemy(RPGCharacter person) {
        return (Math.random() > 0.5) ? Goblin.getBalancedEnemy(person) : Skeleton.getBalancedEnemy(person);
    }
}
