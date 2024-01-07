package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static ArrayList<Player> generatePlayers(int n) {
        Item[] items1 = new Item[Weapon.weapons.length + Backpack.backpacks.length + Item.items.length];
        System.arraycopy(Weapon.weapons, 0, items1, 0, Weapon.weapons.length);
        System.arraycopy(Backpack.backpacks, 0, items1, Weapon.weapons.length, Backpack.backpacks.length);
        System.arraycopy(Item.items, 0, items1, Backpack.backpacks.length, Item.items.length);
        ArrayList<Player> list = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            Inventory inventory = new Inventory(i+1, new Item[10][40]);
            
            int t = rnd.nextInt(0, 150);
            for (int j = 0; j < 400; j++) {
                inventory.putItemInInventory(new Item(items1[rnd.nextInt(1, 100)]), j / 40, j % 40);
            }

            Weapon wp1 = new Weapon(Weapon.weapons[rnd.nextInt(1, 100)]);
            Weapon wp2 = new Weapon(Weapon.weapons[rnd.nextInt(1, 100)]);
            Backpack backpack = new Backpack(Backpack.backpacks[rnd.nextInt(1, 10)]);
            backpack.setPlayer_id(i+1);
            Money money = new Money(i+1, rnd.nextInt(1000000), rnd.nextInt(10000), rnd.nextInt(10000));
            Player player = new Player(randomString(), wp1, wp2, backpack, inventory, money);
            list.add(player);
        }
        return list;
    }
}
