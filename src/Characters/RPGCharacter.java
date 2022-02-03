package Characters;

abstract public class RPGCharacter {
    String name;
    double maxHp;
    double hp;
    int gold;
    int flask;
    boolean isAlive;

    int dexterity;
    int strange;
    int luck;

    double experience;
    int level;
    double needExperienceToLevel;

    public RPGCharacter(String name, double hp, int gold, int dexterity, int strange, int luck, double experience, int level, double needExperienceToLevel) {
        this.name = name;
        this.hp = hp;
        this.gold = gold;
        this.dexterity = dexterity;
        this.strange = strange;
        this.luck = luck;
        this.experience = experience;
        this.level = level;
        this.needExperienceToLevel = needExperienceToLevel;
        this.isAlive = true;
        this.flask = 0;
        this.maxHp = hp;
    }

    public double attack(RPGCharacter object) {
        int rate = (int) Math.round(Math.random()*100);
        double damage;
        if (luck > rate) {
            damage = criticalAttack();
            object.takeDamage(damage);
            return damage;
        } else if (dexterity*3 > rate) {
            damage = normalAttack();
            object.takeDamage(damage);
            return damage;
        } else {
            object.takeDamage(miss());
            return 0;
        }
    }

    private double miss() {
        return 0;
    }

    private double normalAttack() {
        return strange;
    }

    public void takeDamage(double damage) {
        hp -= damage;
        if (hp <= 0) isAlive = false;
    }

    private double criticalAttack() {
        return strange*(1+luck/10);
    };

    public boolean isAlive() {
        return isAlive;
    }

    public int takeGold() {return gold;}

    public double takeExperience() {return experience;}

    public void getGold(int gold) {
        this.gold += gold;
    }

    public void getExperience(double experience) {
        this.experience += experience;
        while (this.experience >= needExperienceToLevel) {
            this.experience -= needExperienceToLevel;
            System.out.println(this.getName() + " достиг " + ++level + " уровня");
            double levelUp = Math.random()*4+1;
            if (levelUp > 2) {
                if (levelUp > 3) {
                    if (levelUp > 4) {
                        hp += 10;
                    } else strange++;
                } else luck++;
            } else dexterity++;
            needExperienceToLevel = level*100;
            System.out.println("Здоровье/Сила/Ловкость/Удача = " + hp + '/' + strange + '/' + dexterity + '/' + luck);
        }
    }

    public String getName() {
        return name;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getStrange() {
        return strange;
    }

    public int getLuck() {
        return luck;
    }

    public double getHp() {
        if (hp>0) return hp;
        else return 0;
    }

    public void addFlask(int number) {
        flask+=number;
    }

    public void useFlask() {
        if (flask>=1) {
            hp+=50;
            if (hp > maxHp) hp = maxHp;
            flask--;
        }
    }

    public int getFlask() {
        return flask;
    }

    public void chill(int hp) {
        this.hp+=hp;
        if (this.hp > maxHp) this.hp = maxHp;
        System.out.println("Здоровье "+ name + ' ' + getHp());
    }
}
