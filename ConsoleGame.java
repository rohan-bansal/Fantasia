import java.util.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;


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
    String pclass;
    int HP = 50;
    String[] temp = new String[] {"water-bottle x2", "torch x10", "rope(10ft)", "scabbard", "rations x3"};
    String[] inventory = Arrays.copyOf(temp, 20);

    Player(String name, String classL) {
        username = name;
        pclass = classL;
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

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final HashMap<Integer, String> classes = new HashMap<Integer, String>() {{
        put(1, "Adventurer");
        put(2, "Treasure Hunter");
        put(3, "Merchant");
        put(4, "Rogue");
    }};

    static void TypeLine(String line) throws java.lang.InterruptedException {
        for (int i = 0; i < line.length(); i++) {
            System.out.print(line.charAt(i));
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }

    public static void main(String[] args) throws java.lang.InterruptedException{

        String placeholder;
        String first_name;
        String last_name;
        String classname;
        int placeholder2;
        Scanner input = new Scanner(System.in);

        TypeLine(ANSI_BLUE + "---------------Welcome to Fantasy RPG---------------\n\n" + ANSI_GREEN + "Choose a player class: \n\t1. Adventurer\n\t2. Treasure Hunter\n\t3. Merchant\n\t4. Rogue Thief\n\n" + ANSI_RED + "Input a number >> " + ANSI_RESET);
        placeholder = input.nextLine();
        placeholder2 = Integer.parseInt(placeholder);
        TypeLine(ANSI_RED + "What is the name of you character? (first and last names) >> " + ANSI_RESET);
        placeholder = input.nextLine();
        String[] namesplit = placeholder.split(" ");
        first_name = namesplit[0];
        last_name = namesplit[1];
        Player user = new Player(placeholder, classes.get(placeholder2));
        classname = classes.get(placeholder2);

        // BEGIN
        TypeLine(ANSI_BLUE + "\n----Information----\n\nYou are " + first_name + " " + last_name + ", a " + classname + " in the world of Lotria. Magic exists, and is known as a fact. You can think of it like the LOTR realm. " +
                "Wizards exist, and can harness magic to do good and evil things. Clusters of villages and the occasional city are spread across the land. And because magic exists, " +
                "magical creatures exist as well. (Giant spiders, ogres, occasional giant, unicorns, etc.) You live in the small town of Zedmore.\n\n\tTo start the adventure, type 'go'.\n\tInformation on Zedmore, type 'info'.\n>>" + ANSI_RESET);
        placeholder = input.nextLine();
    }
}
