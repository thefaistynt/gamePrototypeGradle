package org.example;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @Type(value = Weapon.class, name = "Weapon"),
        @Type(value = Backpack.class, name = "Backpack")
})
public class Item {
    @JsonIgnore
    private static int next_id = 1;
    @JsonIgnore
    public static final Item[] items = new Item[] {new Item("Band-Aids"), new Item("Thermometer"), new Item("Antiseptic wipes"), new Item("Tweezers"), new Item("Pain relievers (e.g., Ibuprofen)"), new Item("Adhesive tape"), new Item("Elastic bandages"), new Item("Gauze pads"), new Item("Alcohol swabs"), new Item("Digital blood pressure monitor"), new Item("First aid kit"), new Item("Ace bandage"), new Item("Scissors"), new Item("Disposable gloves"), new Item("Instant cold packs"), new Item("Prescription medications (as needed)"), new Item("CPR face shield"), new Item("Hydrogen peroxide"), new Item("Prescription eyeglasses or contacts"), new Item("EpiPen (if prescribed)"), new Item("Toilet paper"), new Item("Paper towels"), new Item("Dish soap"), new Item("Trash bags"), new Item("Broom and dustpan"), new Item("Cleaning supplies"), new Item("Laundry detergent"), new Item("Kitchen utensils"), new Item("Can opener"), new Item("Plates and silverware"), new Item("Pots and pans"), new Item("Bed sheets"), new Item("Towels"), new Item("Pillow and pillowcases"), new Item("Vacuum cleaner"), new Item("Garbage cans"), new Item("Fire extinguisher"), new Item("Smoke detectors"), new Item("Carbon monoxide detector"), new Item("Dish rack"), new Item("Extension cords"), new Item("Surge protectors"), new Item("Batteries (various sizes)"), new Item("Flashlights"), new Item("Light bulbs"), new Item("Power strips"), new Item("Cordless phone and charger"), new Item("Portable phone charger"), new Item("Battery-operated alarm clock"), new Item("Multimeter"), new Item("Electric drill"), new Item("Screwdriver set"), new Item("Wire nuts and electrical tape"), new Item("Circuit breaker box"), new Item("Extension ladder"), new Item("Voltage tester"), new Item("Outdoor extension cords"), new Item("Generator (if needed)"), new Item("Welding machine (if needed)"), new Item("Soldering iron"), new Item("Nails and screws"), new Item("Hammer"), new Item("Screwdriver set"), new Item("Tape measure"), new Item("Level"), new Item("Wood glue"), new Item("Sandpaper"), new Item("Paintbrushes and rollers"), new Item("Paint and primer"), new Item("Caulk and caulking gun"), new Item("Plywood sheets"), new Item("Drywall"), new Item("Insulation materials"), new Item("Roofing materials"), new Item("Concrete mix"), new Item("Plumbing materials (pipes, fittings)"), new Item("Electric wiring and conduit"), new Item("Hinges and locks"), new Item("Shovels and rakes"), new Item("Wheelbarrow")};
    @JsonIgnore
    public static final Weapon[] weapons = new Weapon[]{new Weapon("Sword", 57), new Weapon("Dagger", 89), new Weapon("Bow", 37), new Weapon("Crossbow", 139), new Weapon("Spear", 110), new Weapon("Axe", 73), new Weapon("Mace", 63), new Weapon("Warhammer", 115), new Weapon("Katana", 69), new Weapon("Rapier", 142), new Weapon("Flail", 33), new Weapon("Morningstar", 12), new Weapon("Halberd", 131), new Weapon("Scimitar", 49), new Weapon("Longbow", 64), new Weapon("Shortbow", 147), new Weapon("Club", 145), new Weapon("Boomerang", 14), new Weapon("Slingshot", 96), new Weapon("Javelin", 29), new Weapon("Shiv", 79), new Weapon("Brass knuckles", 80), new Weapon("Kunai", 31), new Weapon("Nunchaku", 76), new Weapon("Sai", 102), new Weapon("War fan", 27), new Weapon("Throwing stars", 79), new Weapon("Blowgun", 31), new Weapon("Whip", 44), new Weapon("War scythe", 115), new Weapon("Lance", 64), new Weapon("Cutlass", 41), new Weapon("War club", 102), new Weapon("Polearm", 119), new Weapon("Battle-axe", 85), new Weapon("War pick", 117), new Weapon("War fork", 124), new Weapon("Sling", 87), new Weapon("Bolas", 115), new Weapon("War cudgel", 65), new Weapon("War flail", 97), new Weapon("War maul", 55), new Weapon("War trident", 55), new Weapon("War sickle", 96), new Weapon("War machete", 91), new Weapon("War katana", 146), new Weapon("War rapier", 137), new Weapon("War scimitar", 66), new Weapon("War halberd", 98), new Weapon("War staff", 17), new Weapon("War glaive", 135), new Weapon("War dagger", 122), new Weapon("War shortsword", 82), new Weapon("War longbow", 83), new Weapon("War shortbow", 69), new Weapon("War crossbow", 130), new Weapon("War slingshot", 100), new Weapon("War blowgun", 130), new Weapon("War whip", 58), new Weapon("War lance", 74), new Weapon("War cutlass", 87), new Weapon("War polearm", 73), new Weapon("War battle-axe", 70), new Weapon("War scythe", 105), new Weapon("War pickaxe", 120), new Weapon("War hammer", 36), new Weapon("War spear", 91), new Weapon("War nunchaku", 7), new Weapon("War sai", 9), new Weapon("War war fan", 96), new Weapon("War throwing stars", 85), new Weapon("War brass knuckles", 86), new Weapon("War kunai", 43), new Weapon("War bolas", 121), new Weapon("War cudgel", 4), new Weapon("War mace", 41), new Weapon("War katana", 33), new Weapon("War rapier", 115), new Weapon("War trident", 139), new Weapon("War sickle", 110), new Weapon("War machete", 99), new Weapon("War staff", 36), new Weapon("War glaive", 43), new Weapon("War dagger", 51), new Weapon("War shortsword", 60), new Weapon("War longbow", 23), new Weapon("War shortbow", 19), new Weapon("War crossbow", 97), new Weapon("War slingshot", 101), new Weapon("War blowgun", 54), new Weapon("War whip", 110), new Weapon("War lance", 92), new Weapon("War cutlass", 82), new Weapon("War polearm", 85), new Weapon("War battle-axe", 143), new Weapon("War scythe", 86), new Weapon("War pickaxe", 140), new Weapon("War war hammer", 6), new Weapon("War spear", 45), new Weapon("War nunchaku", 26)};
    @JsonIgnore
    public static final Backpack[] backpacks = new Backpack[]{new Backpack("5.11 Tactical Rush 72 Backpack", 5, 1), new Backpack("CamelBak H.A.W.G. Hydration Pack", 9, 2), new Backpack("Mystery Ranch 3 Day Assault Pack", 4, 7), new Backpack("Maxpedition Falcon-II Backpack", 7, 2), new Backpack("Kelty Eagle 7850 Backpack", 2, 7), new Backpack("Condor 3 Day Assault Pack", 4, 2), new Backpack("Eberlestock G4 Operator Pack", 6, 6), new Backpack("Arc'teryx LEAF Assault Pack 30", 8, 1), new Backpack("Blackhawk S.T.R.I.K.E. Cyclone Hydration Pack", 3, 1), new Backpack("Direct Action Ghost Tactical Backpack", 2, 6)};

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Integer getInventory_id() {
        return inventory_id;
    }

    private Integer inventory_id;

    private Integer player_id;

    private Integer backpack_id;

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public void setBackpack_id(Integer backpack_id) {
        this.backpack_id = backpack_id;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public String name;

    public Item(String name) {
        this.id = next_id++;
        this.name = name;
    }

    public Integer getBackpack_id() {
        return backpack_id;
    }
    @JsonIgnore
    public int getType(Item item) {
        if (item.getId() <= 80) {
            return 0;
        } if (item.getId() <= 180) {
            return 1;
        } else {
            return 2;
        }
    }

    public Item(Item item) {
        this.id = item.id;
        this.name = item.name;
    }

    protected Item() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{"+this.id +", "+this.name + "}";
    }

}
