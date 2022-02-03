package Characters;

public class Skeleton extends Enemy{
    public Skeleton(String name, double hp, int gold, int dexterity, int strange, int luck, double experience, int level, double needExperienceToLevel) {
        super(name, hp, gold, dexterity, strange, luck, experience, level, needExperienceToLevel);
    }

    public static Enemy getBalancedEnemy(RPGCharacter person) {
        double hp = Math.random()*50+50;
        int gold = (int) Math.round(hp*Math.random()*0.5 + 0.1*person.getLuck()/10);
        int dexterity = (int) Math.round(Math.random()*(person.getDexterity()-1) + 2);
        int strange = (int) Math.round((Math.random()*person.getStrange()-2) + 3);
        int luck = (int) Math.round(Math.random()*person.luck + 1);
        int level = dexterity+strange+luck+(int) (hp-50)/10;
        Skeleton skeleton = new Skeleton("Skeleton " + level + " lvl", hp, gold, dexterity, strange, luck, hp, level, 10000);
        return skeleton;
    }
}
