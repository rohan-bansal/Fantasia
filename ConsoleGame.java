import java.util.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;


class Item {
    String name;
    String lore;
    int durability;

    Item(String nameL, String loreL, int durabilityL) {
        name = nameL;
        lore = loreL;
        durability = durabilityL;
    }

    String getAttr(String Type) {
        if(Type.equals("lore")) return lore;
        if(Type.equals("name")) return name;
        return "Blank";
    }
}

class Entity {
    ConsoleGame use = new ConsoleGame();
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

    void talk() throws java.lang.InterruptedException {
        if(passive) {
            use.TypeLine("\u001B[37m" + "\n\nHi There!\n" + "\u001B[0m");
        } else {
            use.TypeLine("\u001B[37m" + "\n\nGrrrrr...\n" + "\u001B[0m");
        }
    }

}

class Player {
    String username;
    ConsoleGame use = new ConsoleGame();
    int money = 50;
    String pclass;
    int HP = 50;
    int currentIndex = 4;
    String[] temp = new String[] {"water-bottle x2", "torch x10", "rope(10ft)", "scabbard", "rations x3"};
    String[] inventory = Arrays.copyOf(temp, 20);

    Player(String name, String classL) {
        username = name;
        pclass = classL;
        HashMap<String, Integer> damage = new HashMap<>();
        ArrayList<String> weapons = new ArrayList<>();
        damage.put("Fists", 2);
    }

    void talk(String to_whom) {
    ;
    }

    void buy(String[] place, String item, int moneh) throws java.lang.InterruptedException{
        for(String element : place) {
            if(element.equals(item)) {
                money -= moneh;
                currentIndex++;
                inventory[currentIndex] = item.substring(3);
                use.TypeLine("\u001B[34m" + "\nBought item. You have $" + money + " left. Current inventory consists of: \n\n" + "\u001B[0m");
                for(String part : inventory) {
                    if(part == null) {
                        use.TypeLine(" ");
                    } else {
                        use.TypeLine("\u001B[34m" + "\t" + part + "\n" + "\u001B[0m");
                    }

                }
            }
        }
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

    static String direction(String cardinal, String... extraargs) {
        if(extraargs.length > 0) {
            if(extraargs[0].equals("Blacksmith")) {
                if(!cardinal.equals("SOUTH")) {
                    System.out.print("SOUTH it is :P");
                }
                return ANSI_BLUE + "You travel south through a bustling marketplace full of exotic items, and come to the blacksmith's shop." + ANSI_RESET;
            }
        }
        return "Blank";
    }

    static String generateSituation() {
        return "Blank";
    }

    static void keywords(String word, String... extras) {
        ;
    }

    static String[] Shop(String type) {
        String[] blacksmith = {"1. Blunt Sword - $10 |dmg=4", "2. Scythe - $100 |dmg=8", "3. Cleaver - $200 |dmg=12", "4. Longsword - $300 |dmg=16", "5. BattleAxe - $250 |dmg=16", "6. Sharpened Sword - $220 |dmg=14", "7. Shapeshifting Knife - $350 |dmg=15","8. Basic Shield - $50 |block=2", "9. Large Shield - $200 |block=8"};
        String[] blacksmithPrices = {"10", "100", "200", "300", "250", "220", "350", "50", "200"};
        if(type.equals("blacksmith")) return blacksmith;
        if(type.equals("blacksmithPrices")) return blacksmithPrices;

        String[] clothes = {};
        String[] clothesPrices = {};
        if(type.equals("clothes")) return clothes;
        if(type.equals("clothesPrices")) return clothesPrices;

        String[] provisions = {};
        String[] provisionsPrices = {};
        if(type.equals("provisions")) return provisions;
        if(type.equals("provisionsPrices")) return provisionsPrices;

        String[] magic = {};
        String[] magicPrices = {};
        if(type.equals("magic")) return magic;
        if(type.equals("magicPrices")) return magicPrices;

        String[] books = {};
        String[] booksPrices = {};
        if(type.equals("books")) return books;
        if(type.equals("booksPrices")) return booksPrices;

        String[] food = {};
        String[] foodPrices = {};
        if(type.equals("food")) return food;
        if(type.equals("foodPrices")) return foodPrices;

        return new String[0];
    }


    static void gameLoop(String placeholder, String first_name, String last_name, String classname, Scanner input, Player user) throws java.lang.InterruptedException {
        String placeholder2;
        String[] keys;
        TypeLine(ANSI_BLUE + "You do not have any weapon, so you should visit the blacksmith, who is in the market (SOUTH). Type SOUTH (all caps).\n>> " + ANSI_RESET);
        placeholder = input.nextLine();
        TypeLine(direction(placeholder, "Blacksmith"));
        Entity blacksmith = new Entity("Bobby", "The blacksmith of Zedmore.", 20);
        blacksmith.SetAttr(false, false, 5);
        blacksmith.talk();
        TypeLine(ANSI_WHITE + "Here are the items I sell: \n\n" + ANSI_RESET);
        for(String item : Shop("blacksmith")) {
            TypeLine(ANSI_WHITE + "\t" + item + "\n" + ANSI_RESET);
        }
        TypeLine(ANSI_BLUE + "\nYou have $" + user.money + ". Buy the Blunt Sword, for now. Type '1' [the number of the weapon]\n>> ");
        placeholder = input.nextLine();
        TypeLine(ANSI_BLUE + "Confirm Purchase by typing the amount of money for the item (10).\n>> " + ANSI_RESET);
        placeholder2 = input.nextLine();
        user.buy(Shop("blacksmith"), Shop("blacksmith")[Integer.parseInt(placeholder) - 1], Integer.parseInt(placeholder2));
        TypeLine(ANSI_RED + "\nTo travel and interact, use the following keywords, followed by options: " + ANSI_PURPLE + "\nOPEN (what)\nLOOK (in a cardinal direction)\nPICK UP (what)\nDROP (what)\nTALK (to whom)\nATTACK (what, with what weapon)\nMONEY BALANCE" + ANSI_BLUE + "\n\nFor example, saying " +
                "'OPEN backpack' shows the inventory.\nGood Luck!" + ANSI_RESET);
        while(true) {
            TypeLine(ANSI_BLUE + "\n>> " + ANSI_RESET);
            placeholder = input.nextLine();
            keys = placeholder.split(" ");
            if(keys.length < 2) {
                TypeLine(ANSI_RED + "You need to specify options." + ANSI_RESET);
            } else {
                keywords(keys[0], keys[1]);
            }
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
                "magical creatures exist as well. (Giant spiders, ogres, occasional giant, unicorns, etc.) You live in the small town of Zedmore.\n\n\tTo start the adventure, type 'go'.\n\tInformation on Zedmore, type 'info'.\n>> " + ANSI_RESET);
        placeholder = input.nextLine();
        if(placeholder.equals("go")) {
            gameLoop(placeholder, first_name, last_name, classname, input, user);
        } else if(placeholder.equals("info")) {
            TypeLine(ANSI_BLUE + "\nZedmore is a small town on the west coast of Lotria's main continent. It is not close to water, but it is possible to travel to the beach in a few hours on horseback. The town is mainly used by " +
                    "travelers as a rest stop. Zedmore consists of about 10 houses, a market (blacksmith, clothing, food, etc.), and a town hall. \n" + ANSI_RESET);
            gameLoop(placeholder, first_name, last_name, classname, input, user);
        }
    }
}
