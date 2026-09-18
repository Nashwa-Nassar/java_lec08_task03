package com.depi.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * DEPI - Software Testing Track - Session 08 - Task 3
 * Question 1: Java Collections - Managing a List of Items
 *
 * Demonstrates how the SAME item data can be stored, accessed, searched,
 * modified and displayed using different Java data structures / collections:
 *   1) One-dimensional array
 *   2) Two-dimensional array
 *   3) List (interface) / ArrayList
 *   4) LinkedList
 *   5) Set / HashSet
 *   6) HashMap
 */
public class Main {

    public static void main(String[] args) {
        oneDimensionalArrayDemo();
        twoDimensionalArrayDemo();
        listArrayListDemo();
        linkedListDemo();
        setHashSetDemo();
        hashMapDemo();
    }

    // ------------------------------------------------------------------
    // 1) One-dimensional array
    // Why: Best when the number of items is fixed and known in advance,
    // and you just need fast index-based access with no resizing.
    // ------------------------------------------------------------------
    private static void oneDimensionalArrayDemo() {
        System.out.println("=== 1) One-Dimensional Array ===");

        // Store: a fixed-size array of item names
        String[] itemNames = { "Keyboard", "Mouse", "Monitor", "Headset" };

        // Access: by index
        System.out.println("Access index 2: " + itemNames[2]);

        // Search: linear search for "Monitor"
        int foundIndex = -1;
        for (int i = 0; i < itemNames.length; i++) {
            if (itemNames[i].equals("Monitor")) {
                foundIndex = i;
                break;
            }
        }
        System.out.println("Search 'Monitor' found at index: " + foundIndex);

        // Modify: replace an element (arrays are fixed-size but elements are mutable)
        itemNames[1] = "Wireless Mouse";

        // Display
        System.out.println("Display all: " + Arrays.toString(itemNames));
        System.out.println();
    }

    // ------------------------------------------------------------------
    // 2) Two-dimensional array
    // Why: Good for tabular, fixed-shape numeric data such as an
    // ID/Price table, mirroring the example given in the task sheet.
    // ------------------------------------------------------------------
    private static void twoDimensionalArrayDemo() {
        System.out.println("=== 2) Two-Dimensional Array ===");

        // Store: ID / Price table, as given in the task example
        double[][] itemData = {
                {101, 500},
                {102, 700},
                {103, 25000},
                {104, 7000}
        };

        // Access: row 2 ({103, 25000})
        System.out.println("Access row 2 -> ID=" + (int) itemData[2][0]
                + ", Price=" + itemData[2][1]);

        // Search: find the row where price is the highest
        int maxPriceRow = 0;
        for (int i = 1; i < itemData.length; i++) {
            if (itemData[i][1] > itemData[maxPriceRow][1]) {
                maxPriceRow = i;
            }
        }
        System.out.println("Most expensive item -> ID=" + (int) itemData[maxPriceRow][0]
                + ", Price=" + itemData[maxPriceRow][1]);

        // Modify: apply a 10% discount to item 102 (row index 1)
        itemData[1][1] = itemData[1][1] * 0.9;

        // Display
        System.out.println("Display all rows:");
        for (double[] row : itemData) {
            System.out.println("  ID=" + (int) row[0] + ", Price=" + row[1]);
        }
        System.out.println();
    }

    // ------------------------------------------------------------------
    // 3) List / ArrayList
    // Why: Use List (the interface) as the declared type and ArrayList
    // as the implementation when you need a resizable, ordered
    // collection with fast random access (get/set by index) - ideal
    // for the "main catalog" of items that grows/shrinks over time.
    // ------------------------------------------------------------------
    private static void listArrayListDemo() {
        System.out.println("=== 3) List / ArrayList ===");

        // Store
        List<Item> items = new ArrayList<>();
        items.add(new Item(101, "Keyboard", 500));
        items.add(new Item(102, "Mouse", 700));
        items.add(new Item(103, "Monitor", 25000));
        items.add(new Item(104, "Headset", 7000));

        // Access: by index (ArrayList is efficient at this)
        System.out.println("Access index 0: " + items.get(0));

        // Search: find item by id
        Item found = null;
        for (Item item : items) {
            if (item.getId() == 103) {
                found = item;
                break;
            }
        }
        System.out.println("Search id=103: " + found);

        // Modify: change the price of the Mouse
        for (Item item : items) {
            if (item.getId() == 102) {
                item.setPrice(650);
            }
        }

        // Display
        System.out.println("Display all:");
        items.forEach(item -> System.out.println("  " + item));
        System.out.println();
    }

    // ------------------------------------------------------------------
    // 4) LinkedList
    // Why: Best when items are frequently added/removed from the
    // front or back of the collection (e.g. a queue of items waiting
    // to be processed/tested), since LinkedList gives O(1) insertion
    // and removal at both ends, unlike ArrayList.
    // ------------------------------------------------------------------
    private static void linkedListDemo() {
        System.out.println("=== 4) LinkedList ===");

        // Store: a processing queue of items
        LinkedList<Item> processingQueue = new LinkedList<>();
        processingQueue.addLast(new Item(201, "Webcam", 1200));
        processingQueue.addLast(new Item(202, "Microphone", 900));
        processingQueue.addFirst(new Item(200, "USB Hub", 300)); // priority item added to front

        // Access: first and last elements
        System.out.println("First in queue: " + processingQueue.peekFirst());
        System.out.println("Last in queue: " + processingQueue.peekLast());

        // Search: does the queue contain an item named "Microphone"?
        boolean hasMic = processingQueue.stream()
                .anyMatch(item -> item.getName().equals("Microphone"));
        System.out.println("Contains 'Microphone': " + hasMic);

        // Modify: process (remove) the first item in the queue
        Item processed = processingQueue.removeFirst();
        System.out.println("Processed and removed: " + processed);

        // Display
        System.out.println("Remaining queue:");
        processingQueue.forEach(item -> System.out.println("  " + item));
        System.out.println();
    }

    // ------------------------------------------------------------------
    // 5) Set / HashSet
    // Why: Use a Set when duplicate items must be rejected automatically
    // - e.g. tracking the set of distinct item IDs that have been
    // scanned/tested, where order doesn't matter but uniqueness does.
    // ------------------------------------------------------------------
    private static void setHashSetDemo() {
        System.out.println("=== 5) Set / HashSet ===");

        // Store: HashSet relies on Item.equals()/hashCode() (based on id)
        Set<Item> scannedItems = new HashSet<>();
        scannedItems.add(new Item(101, "Keyboard", 500));
        scannedItems.add(new Item(102, "Mouse", 700));
        scannedItems.add(new Item(101, "Keyboard", 500)); // duplicate id -> ignored

        System.out.println("Total distinct items scanned: " + scannedItems.size());

        // Access/Search: check membership
        boolean containsId101 = scannedItems.contains(new Item(101, "Keyboard", 500));
        System.out.println("Was item 101 scanned? " + containsId101);

        // Modify: remove an item, then add a new one
        scannedItems.remove(new Item(102, "Mouse", 700));
        scannedItems.add(new Item(103, "Monitor", 25000));

        // Display (order is not guaranteed in a HashSet)
        System.out.println("Display all scanned items:");
        scannedItems.forEach(item -> System.out.println("  " + item));
        System.out.println();
    }

    // ------------------------------------------------------------------
    // 6) HashMap
    // Why: Best when you need fast lookup of an item by a unique key
    // (its id), avoiding a linear search through a List - ideal for an
    // item "catalog" indexed by ID.
    // ------------------------------------------------------------------
    private static void hashMapDemo() {
        System.out.println("=== 6) HashMap ===");

        // Store: id -> Item
        Map<Integer, Item> catalog = new HashMap<>();
        catalog.put(101, new Item(101, "Keyboard", 500));
        catalog.put(102, new Item(102, "Mouse", 700));
        catalog.put(103, new Item(103, "Monitor", 25000));
        catalog.put(104, new Item(104, "Headset", 7000));

        // Access/Search: direct O(1) lookup by id (no loop needed)
        System.out.println("Lookup id=103: " + catalog.get(103));
        System.out.println("Contains id=999? " + catalog.containsKey(999));

        // Modify: update price for id=104
        catalog.get(104).setPrice(6500);

        // Add a new item, remove another
        catalog.put(105, new Item(105, "Webcam", 1200));
        catalog.remove(101);

        // Display
        System.out.println("Display all catalog entries:");
        for (Map.Entry<Integer, Item> entry : catalog.entrySet()) {
            System.out.println("  key=" + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
