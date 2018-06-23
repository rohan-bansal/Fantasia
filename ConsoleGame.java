import java.util.*;

class Item {
    String name;
    String lore;

    Item(String nameL, String loreL) {
        name = nameL;
        lore = loreL;
    }

    String getAttr(String Type) {
        if(Type.equals("lore")) return lore;
        if(Type.equals("name")) return name;
        return "Blank";
    }
}

class Entity {
    String name;
    String description;
    int to_kill;
    int HP;
    boolean living;
    boolean passive;

    Entity(String nameL, String descriptionL, int HPl) {
        name = nameL;
        description = descriptionL;
        HP = HPl;

    }

    void SetAttr(boolean dead, boolean aggressive, int level_to_kill) {
        to_kill = level_to_kill;
        if(!dead) {
            living = true;
        } else {
            living = false;
        }
        if(!aggressive) {
            passive = true;
        } else {
            passive = false;
        }

    }

    String getAttr(String Type) {
        if(Type.equals("name")) return name;
        if(Type.equals("description")) return description;
        if(Type.equals("to_kill")) return Integer.toString(to_kill);
        if(Type.equals("living")) return Boolean.toString(living);
        if(Type.equals("passive")) return Boolean.toString(passive);
        return "Blank";
    }

}

class Player {
    String username;
    int HP = 50;
    String[] temp = new String[] {"water-bottle x2", "torch x10", "rope(10ft)", "scabbard", "rations x3"};
    String[] inventory = Arrays.copyOf(temp, 20);

    Player(String name) {
        username = name;
        HashMap<String, Integer> damage = new HashMap<>();
        ArrayList<String> weapons = new ArrayList<>();
        damage.put("Fists", 2);
        weapons.add("Fists");

    }

    void talk(String to_whom) {
    ;
    }

    void move(String direction) {
    ;
    }

    void buy(boolean in_village, String which_village_shop) {
    ;
    }

    void attack(Entity mob) {
    ;
    }
}


public class ConsoleGame {
    public static void main(String[] args) {
        
    }
}
