package org.example;

import org.example.DBSObjects.DBCItem;

import java.sql.SQLException;
import java.util.Scanner;

public class Console {
    public static Scanner scanner = new Scanner(System.in);
    public static CRUD crud;

    public static void updateAddScreen() {
        System.out.println("Add: ");
        System.out.println("\t · 1. Player ");
        System.out.println("\t · 2. Item ");
        System.out.println("\t · 3. Backpack ");
        System.out.println("\t · 4. Money ");
        System.out.println("\t · 5. Inventory ");
        System.out.println("\t · 6. Weapon \n");
        System.out.println("\t · 7. Previous screen \n");
        System.out.print("Your choose: ");
    }

    public static void addScreen() throws Exception {
        boolean flag = true;
        while (flag) {
            updateAddScreen();
            int ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Nickname: ");
                    String nickname = scanner.nextLine();
                    System.out.println();
                    Player player = new Player(nickname, null, null, null, null, null);
                    crud.addPlayer(player);
                    break;

                case 2:
                    System.out.print("Item: ");
                    String item_name = scanner.nextLine();
                    System.out.println();
                    Item item = new Item(item_name);
                    crud.addItem(item);
                    break;

                case 3:
                    System.out.print("Backpack id: ");
                    int backpack_id = scanner.nextInt();
                    System.out.println();
                    System.out.print("Player id: ");
                    int player_id = scanner.nextInt();
                    System.out.println();
                    player = crud.findPlayer(player_id);
                    Backpack backpack = new Backpack(Item.backpacks[backpack_id - Item.weapons.length - Item.items.length]);
                    crud.addBackpack(backpack);
                    crud.updatePlayer(player, backpack);
                    break;

                case 4:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();

                    System.out.print("Dollars: ");
                    int dollars = scanner.nextInt();
                    System.out.println();

                    System.out.print("Euro: ");
                    int euro = scanner.nextInt();
                    System.out.println();

                    System.out.print("Rubles: ");
                    int rubles = scanner.nextInt();
                    System.out.println();

                    Money money = new Money(player_id, dollars, euro, rubles);
                    crud.addMoney(money);
                    crud.updatePlayer(crud.findPlayer(player_id), money);
                    break;

                case 5:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();

                    Inventory inventory = new Inventory(player_id, new Item[10][40]);
                    crud.addInventory(inventory);
                    crud.updatePlayer(crud.findPlayer(player_id), inventory);
                    break;

                case 6:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();

                    System.out.print("Weapon id: ");
                    int weapon_id = scanner.nextInt();
                    System.out.println();

                    System.out.print("Slot (0/1): ");
                    int slot = scanner.nextInt();
                    System.out.println();
                    if (slot != 0 && slot != 1) {
                        throw new Exception();
                    }

                    Weapon weapon = new Weapon(Item.weapons[weapon_id - Item.items.length]);
                    crud.updatePlayer(crud.findPlayer(player_id), weapon, slot);
                    break;

                case 7:
                    flag = false;
                    break;
                default:
                    System.err.println("You enter the wrong number");
                    break;
            }
        }
    }

    public static void updateFindScreen() {
        System.out.println("Find: ");
        System.out.println("\t · 1. Player by id ");
        System.out.println("\t · 2. Player by name ");
        System.out.println("\t · 3. Weapons by player id ");
        System.out.println("\t · 4. Inventory by player id ");
        System.out.println("\t · 5. Previous screen ");
        System.out.print("Your choose: ");
    }

    public static void findScreen() throws SQLException {
        boolean flag = true;
        while (flag) {
            updateFindScreen();
            int ch = scanner.nextInt();
            System.out.println();
            switch (ch) {
                case 1:
                    System.out.print("Player id: ");
                    int player_id = scanner.nextInt();
                    System.out.println();
                    Player player = crud.findPlayer(player_id);
                    System.out.println(player.toString());
                    break;

                case 2:
                    System.out.print("Player nickname: ");
                    String player_nickname = scanner.nextLine();
                    System.out.println();
                    player = crud.findPlayer(player_nickname);
                    System.out.println(player.toString());
                    break;

                case 3:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();
                    Weapon[] weapons = crud.findWeapons(player_id);
                    System.out.println("weapon 1: " + weapons[0].toString());
                    System.out.println("weapon 2: " + weapons[1].toString());
                    break;

                case 4:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();
                    Inventory inventory = crud.findInventory(player_id);
                    System.out.println(inventory.toString());
                    break;

                case 5:
                    flag = false;
                    break;

                default:
                    System.err.println("You enter the wrong number");
                    break;
            }
        }
    }

    public static void updateUpdateScreen() {
        System.out.println("Update: ");
        System.out.println("\t · 1. Update player inventory ");
        System.out.println("\t · 2. Update player weapon ");
        System.out.println("\t · 3. Update player money ");
        System.out.println("\t · 4. Update item ");
        System.out.println("\t · 5. Previous screen ");
        System.out.print("Your choose: ");
    }

    public static void updateScreen() throws Exception {
        boolean flag = true;
        while (flag) {
            updateFindScreen();
            int ch = scanner.nextInt();
            System.out.println();
            switch (ch) {
                case 1:
                    System.out.print("Player id: ");
                    int player_id = scanner.nextInt();
                    System.out.println();
                    Player player = crud.findPlayer(player_id);

                    System.out.print("Inventory id: ");
                    int inventory_id = scanner.nextInt();
                    System.out.println();
                    Inventory inventory = crud.findInventory(inventory_id);
                    crud.updatePlayer(player, inventory);
                    break;

                case 2:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();
                    player = crud.findPlayer(player_id);

                    System.out.print("Weapons id: ");
                    int weapons_id = scanner.nextInt();
                    System.out.println();
                    Weapon[] weapons = crud.findWeapons(weapons_id);

                    System.out.print("Weapons slot: ");
                    int slot = scanner.nextInt();
                    System.out.println();

                    if (slot != 0 && slot != 1) {
                        throw new Exception();
                    }

                    crud.updatePlayer(player, weapons[slot], slot);
                    break;

                case 3:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();
                    player = crud.findPlayer(player_id);

                    System.out.print("Money id: ");
                    int money_id = scanner.nextInt();
                    System.out.println();
                    Money money = crud.findMoney(money_id);

                    crud.updatePlayer(player, money);
                    break;

                case 4:
                    System.out.print("Item id: ");
                    int item_id = scanner.nextInt();
                    System.out.println();

                    System.out.print("New name: ");
                    String new_name = scanner.nextLine();
                    System.out.println();
                    Item item;
                    if (item_id <= 80) {
                        item = Item.items[item_id];
                    } else if (item_id <= 180) {
                        item = Item.weapons[item_id - Item.items.length];
                    } else {
                        item = Item.backpacks[item_id - Item.weapons.length - Item.items.length];
                    }

                    crud.updateItem(item, new_name);
                    break;

                case 5:
                    flag = false;
                    break;

                default:
                    System.err.println("You enter the wrong number");
                    break;
            }
        }
    }

    public static void updateDeleteScreen() {
        System.out.println("Delete: ");
        System.out.println("\t · 1. Delete player by id ");
        System.out.println("\t · 2. Delete inventory by player id ");
        System.out.println("\t · 3. Delete weapon by id ");
        System.out.println("\t · 4. Delete item by id ");
        System.out.println("\t · 5. Previous screen ");
        System.out.print("Your choose: ");
    }

    public static void deleteScreen() throws Exception {
        boolean flag = true;
        while (flag) {
            updateFindScreen();
            int ch = scanner.nextInt();
            System.out.println();
            switch (ch) {
                case 1:
                    System.out.print("Player id: ");
                    int player_id = scanner.nextInt();
                    System.out.println();
                    crud.deletePlayer(player_id);
                    break;

                case 2:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();

                    crud.deleteInventoryById(player_id);
                    break;

                case 3:
                    System.out.print("Player id: ");
                    player_id = scanner.nextInt();
                    System.out.println();
                    System.out.print("Slot: ");
                    int slot = scanner.nextInt();
                    System.out.println();

                    crud.deleteWeapon(player_id, slot);
                    break;

                case 4:
                    System.out.print("Item id: ");
                    int item_id = scanner.nextInt();
                    System.out.println();
                    crud.deleteItem(item_id);
                    break;

                case 5:
                    flag = false;
                    break;

                default:
                    System.err.println("You enter the wrong number");
                    break;
            }
        }
    }

    public static void updateMainScreen() {
        System.out.println("Program: ");
        System.out.println("\t · 1. Add: ");
        System.out.println("\t\t · Player ");
        System.out.println("\t\t · Item ");
        System.out.println("\t\t · Backpack ");
        System.out.println("\t\t · Money ");
        System.out.println("\t\t · Inventory ");
        System.out.println("\t\t · Weapon ");
        System.out.println("\t · 2. Find: ");
        System.out.println("\t\t · Player by id ");
        System.out.println("\t\t · Player by name ");
        System.out.println("\t\t · Weapons by player id ");
        System.out.println("\t\t · Inventory by player id ");
        System.out.println("\t · 3. Update: ");
        System.out.println("\t\t · Update player inventory ");
        System.out.println("\t\t · Update player weapon ");
        System.out.println("\t\t · Update player money ");
        System.out.println("\t\t · Update item ");
        System.out.println("\t · 4. Delete: ");
        System.out.println("\t\t · Delete player by id ");
        System.out.println("\t\t · Delete inventory by player id ");
        System.out.println("\t\t · Delete weapon by id ");
        System.out.println("\t\t · Delete item by id ");
        System.out.println("\t · 5. Exit program ");
        System.out.print("Your choose: ");
    }

    public static int GUI() throws Exception {
        boolean flag = true;
        while (flag) {
            updateMainScreen();
            int ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    addScreen();
                    break;

                case 2:
                    findScreen();
                    break;

                case 3:
                    updateScreen();
                    break;

                case 4:
                    deleteScreen();
                    break;

                case 5:
                    flag = false;
                    break;
                default:
                    System.err.println("You enter the wrong number");
                    break;
            }
        }
        return 1;
    }

    public static void start() throws Exception {
        crud = new CRUD(Connect.get_connection());
        int result = GUI();
        switch (result) {
            case 1:
                System.out.println("Successful end of program");
                break;

            default:
                System.err.println("Error code = " + result);
                break;
        }
    }
}
