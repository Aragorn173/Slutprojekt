package CardChat.Cards;

public class Card {
    String name;
    int health;
    int attack;


    public Card(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }



    public void deathCheck(Card enemy) {
        if (this.health <= 0) {
            System.out.println(this.name + " HAS DIED");
            System.out.println(enemy.getName() + " IS VICTORIOUS");
        }
    }

    public void battle(Card enemy) {
        while (this.health > 0 && enemy.getHealth() > 0) {
            System.out.println(this.name + " attacks " + enemy.getName() + " for: " + this.attack +"dmg");
            enemy.setHealth(enemy.getHealth() - this.attack);

            if (enemy.getHealth() <= 0) {
                System.out.println(enemy.getName() + " HAS DIED");
                System.out.println(this.name + " IS VICTORIOUS");
                break;
            }

            System.out.println(enemy.getName() + " attacks " + this.name + " for: " + enemy.getAttack() +"dmg");
            this.setHealth(this.getHealth() - enemy.getAttack());

            if (this.health <= 0) {
                System.out.println(this.name + " HAS DIED");
                System.out.println(enemy.getName() + " IS VICTORIOUS");
                break;
            }
        }
    }


    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                '}';
    }
}
