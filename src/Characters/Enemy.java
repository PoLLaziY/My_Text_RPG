package Characters;

public abstract class Enemy extends RPGCharacter {
    public Enemy(String name, double hp, int gold, int dexterity, int strange, int luck, double experience, int level, double needExperienceToLevel) {
        super(name, hp, gold, dexterity, strange, luck, experience, level, needExperienceToLevel);
    }
}
