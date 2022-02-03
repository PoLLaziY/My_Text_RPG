package Characters;

public class Person extends RPGCharacter {
    public Person(String name, int strange, int dexterity, int luck, int gold) {
        super(name, 100, gold, dexterity, strange, luck, 0, 1, 100);
    }
}
