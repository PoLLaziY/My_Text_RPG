package Characters;

public class Goblin extends Enemy{
    public Goblin(String name, double hp, int gold, int dexterity, int strange, int luck, double experience, int level, double needExperienceToLevel) {
        super(name, hp, gold, dexterity, strange, luck, experience, level, needExperienceToLevel);
    }

    public static Enemy getBalancedEnemy(RPGCharacter person) {
        double hp = Math.random()*50+70;
        int gold = (int) Math.round(hp*Math.random()*0.5 + 0.1*person.getLuck()/10);
        int dexterity = (int) Math.round(Math.random()*(person.getDexterity()-3) + 4);
        int strange = (int) Math.round(Math.random()*person.getStrange() + 1);
        int luck = (int) Math.round(Math.random()*person.luck + 1);
        int level = dexterity+strange+luck+(int) (hp-70)/10;
        Goblin goblin = new Goblin("Goblin " + level + " lvl", hp, gold, dexterity, strange, luck, hp, level, 10000);
        return goblin;
    }
}
