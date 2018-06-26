import java.util.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;

//----------------------------------------------------
class Item {
    ConsoleGame use = new ConsoleGame();
    String name;
    String lore;
    int durability;
    String type_;

    Item(String nameL, String loreL, int durabilityL, String type) {
        name = nameL;
        lore = loreL;
        durability = durabilityL;
        type_ = type;
    }

    String getAttr(String Type) {
        if(Type.equals("lore")) return lore;
        if(Type.equals("name")) return name;
        return "Blank";
    }
}
//----------------------------------------------------
//----------------------------------------------------
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
            use.TypeLine("\u001B[37m" + "\nHi There!\n" + "\u001B[0m");
        } else {
            use.TypeLine("\u001B[37m" + "\nGrrrrr...\n" + "\u001B[0m");
        }
    }

}
//-------------------------------------------------------
//-------------------------------------------------------
class Player {
    String username;
    ConsoleGame use = new ConsoleGame();
    int money = 50;
    String pclass;
    int HP = 50;
    int weaponDmg = 2;
    int wpnblock;
    int currentIndex = 4;
    String[] temp = new String[] {"water-bottle x2", "torch x10", "rope(10ft)", "scabbard", "rations x3"};
    String[] inventory = Arrays.copyOf(temp, 20);

    Player(String name, String classL) {
        username = name;
        pclass = classL;
    }

    void talk(String to_whom) {
    ;
    }

    void buy(String[] place, String ite, int moneh, String names) throws java.lang.InterruptedException {
        int tempor;
        tempor = money - moneh;
        int prevdmg = weaponDmg;
        String[] tempor2 = ite.split("");
        for(String element : place) {
                String type;
if(element.equals(ite)) {
                if(tempor < 0) {
                    use.TypeLine("\u001B[31m" + "ERROR: Not enough money." + "\u001B[0m");
                    break;
                } else {
                    money -= moneh;
                }
                currentIndex++;
                if(names.equals("b")) {
                    inventory[currentIndex] = ite;
                    use.created_items.put(ite, new Item(ite, "An item bought from the marketplace.", 50, names));
                } else {
                    inventory[currentIndex] = ite.substring(3);
                    use.created_items.put(ite, new Item(ite.substring(3), "An item bought from the marketplace.", 50, names));
                }

                if(ite.contains("|dmg=")) {
                    for(String g : tempor2) {
                        try
                        {
                            Integer.parseInt(g);
                            weaponDmg = Integer.parseInt(g);
                            use.TypeLine("\u001B[34m" + "Current weapon damage increased from " + prevdmg + " to " + weaponDmg + "." + "\u001B[0m");
                        }
                        catch(NumberFormatException ex) { }
                    }
                }
                if(ite.contains("|block=")) {
                    for(String j : tempor2) {
                        try
                        {
                            Integer.parseInt(j);
                            wpnblock = Integer.parseInt(j);
                            use.TypeLine("\u001B[34m" + "Current shield block increased to " + wpnblock + "." + "\u001B[0m");
                        }
                        catch(NumberFormatException ex) { }
                    }
                }
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

    void seeInventory() throws java.lang.InterruptedException {
        use.TypeLine("\n");
        for(String part : inventory) {
            if (part == null) {
                use.TypeLine(" ");
            } else {
                use.TypeLine("\u001B[34m" + "\t" + part + "\n" + "\u001B[0m");
            }
        }
    }

    void attack(Entity mob) {
    ;
    }
}
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
class Professions {
    String[] names = {"Joe", "Bob", "Bobby", "Joey", "John", "Ralph", "Frederic", "Leo", "David", "Emma", "Janet", "Isabel", "Elizabeth", "Mary"};
    ConsoleGame use = new ConsoleGame();
    Random randomizer = new Random();
    Scanner input_= new Scanner(System.in);
    String ph, ph2;

    String[] blacksmith = {"Blunt Sword |dmg=4", "Scythe |dmg=8", "Cleaver |dmg=12", "Longsword |dmg=16", "BattleAxe |dmg=16", "Sharpened Sword |dmg=14", "Shapeshifting Knife |dmg=15","Basic Shield |block=2", "Large Shield |block=8"};
    String[] blacksmithPrices = {"10", "100", "200", "300", "250", "220", "350", "50", "200"};
    String[] weaponDamage = {"4", "8", "12", "16", "16", "14", "15", "B2", "B8"};

    String[] clothes = {};
    String[] clothesPrices = {};

    String[] provisions = {};
    String[] provisionsPrices = {};

    String[] books = {};
    String[] booksPrices = {};

    String[] food = {"1. Beer", "2. Haggis", "3. Stuffulworm-Pie", "4. Tacos", "5. Fish-Eyeball-Soup", "6. Chicken-Toes", "7. Refried-Beans", "8. Corn-On-Da-Cob", "9. Constipation-Reliever"};
    String[] foodPrices = {"5", "10", "30", "20", "12", "3", "20", "10", "50"};

    Professions() {};

    String[] blacksmith(Player user, String... args) throws java.lang.InterruptedException {
        Entity smith = new Entity(names[randomizer.nextInt(14)],"The blacksmith of " + use.current_location,20);
        smith.SetAttr(false, false, 5);
        if(args[0].equals("return")) {
            if(args[1].equals("list")) return blacksmith;
            if(args[1].equals("prices")) return blacksmithPrices;
            if(args[1].equals("damage")) return weaponDamage;
        } else if(args[0].equals("begin")) {
            smith.talk();
            use.TypeLine("\u001B[37m" + "Here are the items I sell: \n\n" + "\u001B[0m");
            for(int part = 0; part < blacksmith.length; part++) {
                use.TypeLine("\u001B[37m" + "\t" + (part+1) + ". " + blacksmith[part] + " --costs ");
                use.TypeLine("$" + blacksmithPrices[part] + "--\n" + "\u001B[0m");
            }

            if(args.length > 1 && args[1].equals("true")) {
                System.out.print(" ");
            } else {
                this.buy(user, "blacksmith");
            }
        }
        return new String[0];
    }

    String[] food(Player user, String... args) throws java.lang.InterruptedException {
        Entity fooder = new Entity(names[randomizer.nextInt(14)],"The food vendor of " + use.current_location,20);
        fooder.SetAttr(false, false, 5);
        if(args[0].equals("return")) {
            if(args[1].equals("list")) return food;
            if(args[1].equals("prices")) return foodPrices;
        } else if(args[0].equals("begin")) {
            fooder.talk();
            use.TypeLine("\u001B[37m" + "Here are the items I sell: \n\n" + "\u001B[0m");
            for(int part = 0; part < food.length; part++) {
                use.TypeLine("\u001B[37m" + "\t" + food[part] + " --costs " + "\u001B[0m");
                use.TypeLine("\u001B[37m" + "$" + foodPrices[part] + "--\n" + "\u001B[0m");
            }
            this.buy(user, "food");
        }
        return new String[0];
    }

    String[] clothes(Player user, String... args) throws java.lang.InterruptedException {
        Entity cloth = new Entity(names[randomizer.nextInt(14)],"The clothes seller of " + use.current_location,20);
        cloth.SetAttr(false, false, 5);
        if(args[0].equals("return")) {
            if(args[1].equals("list")) return clothes;
            if(args[1].equals("prices")) return clothesPrices;
        } else if(args[0].equals("begin")) {
            cloth.talk();
            use.TypeLine("\u001B[37m" + "Here are the items I sell: \n\n" + "\u001B[0m");
            for(int part = 0; part < clothes.length; part++) {
                use.TypeLine("\u001B[37m" + "\t" + clothes[part] + " --costs " + "\u001B[0m");
                use.TypeLine("\u001B[37m" + "$" + clothesPrices[part] + "--\n" + "\u001B[0m");
            }
            this.buy(user, "clothes");
        }
        return new String[0];
    }

    String[] provisions(Player user, String... args) throws java.lang.InterruptedException {
        Entity provider = new Entity(names[randomizer.nextInt(14)],"The item supplier of " + use.current_location,20);
        provider.SetAttr(false, false, 5);
        if(args[0].equals("return")) {
            if(args[1].equals("list")) return provisions;
            if(args[1].equals("prices")) return provisionsPrices;
        } else if(args[0].equals("begin")) {
            provider.talk();
            use.TypeLine("\u001B[37m" + "Here are the items I sell: \n\n" + "\u001B[0m");
            for(int part = 0; part < provisions.length; part++) {
                use.TypeLine("\u001B[37m" + "\t" + provisions[part] + " --costs " + "\u001B[0m");
                use.TypeLine("\u001B[37m" + "$" + provisionsPrices[part] + "--\n" + "\u001B[0m");
            }
            this.buy(user, "provisions");
        }
        return new String[0];
    }

    String[] books(Player user, String... args) throws java.lang.InterruptedException {
        Entity bookie = new Entity(names[randomizer.nextInt(14)],"The librarian of " + use.current_location,20);
        bookie.SetAttr(false, false, 5);
        if(args[0].equals("return")) {
            if(args[1].equals("list")) return books;
            if(args[1].equals("prices")) return booksPrices;
        } else if(args[0].equals("begin")) {
            bookie.talk();
            use.TypeLine("\u001B[37m" + "Here are the items I sell: \n\n" + "\u001B[0m");
            for(int part = 0; part < books.length; part++) {
                use.TypeLine("\u001B[37m" + "\t" + books[part] + " --costs " + "\u001B[0m");
                use.TypeLine("\u001B[37m" + "$" + booksPrices[part] + "--\n" + "\u001B[0m");
            }
            this.buy(user, "books");
        }
        return new String[0];
    }

    void buy(Player user, String type_) throws java.lang.InterruptedException {
        use.TypeLine("\u001B[34m" + "\nType the number of the item you want to buy: \n>> " + "\u001B[0m");
        ph = input_.nextLine();
        use.TypeLine("\u001B[34m" + "Confirm purchase by typing the cost of the item: \n>> " + "\u001B[0m");
        ph2 = input_.nextLine();
        if(type_.equals("blacksmith")) user.buy(blacksmith(user, "return", "list"), blacksmith(user, "return", "list")[Integer.parseInt(ph) - 1], Integer.parseInt(ph2), "b");
        if(type_.equals("food")) user.buy(food(user, "return", "list"), food(user, "return", "list")[Integer.parseInt(ph) - 1], Integer.parseInt(ph2), "f");
        if(type_.equals("clothes")) user.buy(clothes(user, "return", "list"), clothes(user, "return", "list")[Integer.parseInt(ph) - 1], Integer.parseInt(ph2), "c");
        if(type_.equals("provisions")) user.buy(provisions(user, "return", "list"), provisions(user, "return", "list")[Integer.parseInt(ph) - 1], Integer.parseInt(ph2), "p");
        if(type_.equals("books")) user.buy(books(user, "return", "list"), books(user, "return", "list")[Integer.parseInt(ph) - 1], Integer.parseInt(ph2), "o");

    }

}
//--------------------------------------------------------



public class ConsoleGame {

    public static String current_location = "Zedmore";
    public static boolean in_village = true;

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final Professions prof = new Professions();


    public static final HashMap<Integer, String> classes = new HashMap<Integer, String>() {{
        put(1, "Adventurer");
        put(2, "Treasure Hunter");
        put(3, "Merchant");
        put(4, "Rogue");
    }};

    public static final String[] one_word_commands = {"NORTH", "SOUTH", "EAST", "WEST", "BALANCE", "blacksmith", "food", "clothes", "provisions", "books", "exit", "HP"};

    public static final Map<String, Item> created_items = new HashMap<>();

    static void TypeLine(String line) throws java.lang.InterruptedException {
        for (int i = 0; i < line.length(); i++) {
            System.out.print(line.charAt(i));
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }

    static void direction(String cardinal, String... extraargs) throws java.lang.InterruptedException {
        if(extraargs.length > 0) {
            if(extraargs[0].equals("Blacksmith")) {
                if(!cardinal.equals("SOUTH")) {
                    System.out.print("You have to travel south for now.\n");
                }
                TypeLine(ANSI_BLUE + "You travel south through a bustling marketplace full of exotic items, and come to the blacksmith's shop." + ANSI_RESET);
            }
        } else {
            TypeLine(ANSI_BLUE + "Blank - " + cardinal + ANSI_RESET);
        }
    }

    static String generateSituation() {
        return "Blank";
    }

    static String toTitleCase(String givenString) {
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    static void keywords(Player user, String word, Scanner input, String... extras) throws java.lang.InterruptedException {
        if(extras.length < 1) {
            for(int w = 0; w < one_word_commands.length; w++) {
                if(one_word_commands[w].equals(word)) {
                    if(w < 4) {
                        direction(word);
                    } else if(w == 4) {
                        TypeLine(ANSI_YELLOW + "You have a balance of $" + user.money + "." + ANSI_RESET);
                    } else if(w > 4 && w < 10){
                        switch(word) {
                            case "blacksmith":
                                prof.blacksmith(user, "begin");
                                break;
                            case "food":
                                prof.food(user, "begin");
                                break;
                            case "clothes":
                                prof.clothes(user, "begin");
                                break;
                            case "provisions":
                                prof.provisions(user, "begin");
                                break;
                            case "books":
                                prof.books(user, "begin");
                                break;
                            default:
                                TypeLine(ANSI_RED + "ERROR: Unknown market." + ANSI_RESET);
                        }
                    } else if(w == 10) {
                        TypeLine(ANSI_WHITE + "Are you sure? " + ANSI_BLACK + "[" + ANSI_GREEN + "y" + ANSI_BLACK + "/" + ANSI_RED + "n" + ANSI_BLACK + "]\n>> " + ANSI_RESET);
                        if(input.nextLine().equals("y")) {
                            System.exit(0);
                        } else {

                        }
                    } else {
                        TypeLine(ANSI_YELLOW + "You are currently at " + user.HP + " health points." + ANSI_RESET);
                    }
                }
            }
        } else {
            if(word.equals("OPEN")) {
                if(extras[0].equals("backpack")) {
                    user.seeInventory();
                }
            } else if(word.equals("EAT")) {
                String temp = toTitleCase(extras[0]);
                if(Arrays.asList(user.inventory).contains(temp)) {
                    if(user.HP < 50) {
                        user.HP = 50;
                        int x = Arrays.asList(user.inventory).indexOf(temp);
                        user.inventory[x] = null;
                        TypeLine(ANSI_YELLOW + "You ate (or drank) " + temp + '.' + ANSI_RESET);
                    } else {
                        TypeLine(ANSI_RED + "You are at full health, and therefore cannot eat right now." + ANSI_RESET);
                    }
                } else {
                    TypeLine(ANSI_RED + "You do not have any " + temp + "." + ANSI_RESET);
                }
            } else if(word.equals("DESCRIPTION")) {
                // TODO
            } else {
                TypeLine(ANSI_RED + "ERROR: Option not recognized." + ANSI_RESET);
            }
        }
    }

    static void gameLoop(String placeholder, String first_name, String last_name, String classname, Scanner input, Player user) throws java.lang.InterruptedException {

        //INITIALIZATIONS
        created_items.put("backpack", new Item("Basic Backpack", "An essential item used to carry objects.", 200, "p"));

        String placeholder2;
        String[] keys;
        TypeLine(ANSI_BLUE + "You do not have any weapon, so you should visit the blacksmith, who is in the market (SOUTH). Type SOUTH (all caps).\n>> " + ANSI_RESET);
        placeholder = input.nextLine();
        direction(placeholder, "Blacksmith");
        prof.blacksmith(user, "begin", "true");
        TypeLine(ANSI_BLUE + "\nYou have $" + user.money + ". Buy the Blunt Sword, for now. Type '1' [the number of the weapon]\n>> ");
        placeholder = input.nextLine();
        TypeLine(ANSI_BLUE + "Confirm Purchase by typing the amount of money for the item (10).\n>> " + ANSI_RESET);
        placeholder2 = input.nextLine();
        user.buy(prof.blacksmith(user, "return", "list"), prof.blacksmith(user, "return", "list")[Integer.parseInt(placeholder) - 1], Integer.parseInt(placeholder2), "b");
        TypeLine(ANSI_YELLOW + "\nPress Enter to continue..." + ANSI_RESET);
        input.nextLine();
        TypeLine(ANSI_RED + "\rTo travel and interact, use the following keywords, followed by options: " + ANSI_PURPLE + "\nOPEN (what)\nLOOK (in a cardinal direction)\nPICK UP (what)\nDROP (what)\nTALK (to whom)\nATTACK (what, with what weapon)\n(cardinal direction)\nBALANCE - (check balance)\nDESCRIPTION (item/person)\nEAT (food/beverage)\nHP - (check health points)\nexit - (to quit)" + ANSI_BLUE + "\nFor example, saying " +
                "'OPEN backpack' shows the inventory.\nYou can explore the marketplace if you want to. (to travel to a shop, type one of the names below)" + ANSI_PURPLE + "\nblacksmith\nfood\nclothes\nprovisions\nbooks\n" + ANSI_BLUE + "When you are ready, type 'SOUTH' to exit the village.\nGood Luck!" + ANSI_RESET);
        while(true) {
            TypeLine(ANSI_BLUE + "\n>> " + ANSI_RESET);
            placeholder = input.nextLine();
            keys = placeholder.split(" ");
            if(keys[0].equals("SOUTH") || keys[0].equals("NORTH") || keys[0].equals("WEST") || keys[0].equals("EAST") || keys[0].equals("BALANCE") || keys[0].equals("blacksmith") || keys[0].equals("food") || keys[0].equals("clothes") || keys[0].equals("provisions") || keys[0].equals("books") || keys[0].equals("exit") || keys[0].equals("HP")) {
                keywords(user, keys[0], input);
            } else if(keys.length < 2) {
                TypeLine(ANSI_RED + "ERROR: You need to specify options." + ANSI_RESET);
            } else {
                keywords(user, keys[0], input, keys[1]);
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
        if(placeholder.equals("0")) {
            Player user = new Player("joe bob", classes.get(1));
            gameLoop(placeholder, "joe", "bob", "Adventurer", input, user);
        }
        placeholder2 = Integer.parseInt(placeholder);
        TypeLine(ANSI_RED + "What is the name of your character? (first and last names) >> " + ANSI_RESET);
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
        } else {
            TypeLine(ANSI_RED + "Not a valid argument. Anyways, continuing story...\n" + ANSI_RESET);
            gameLoop(placeholder, first_name, last_name, classname, input, user);
        }
    }
}
