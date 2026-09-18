package com.depi.items;

/**
 * Simple POJO representing an item.
 * Used across every collection example so the SAME data can be
 * stored, accessed, searched, modified and displayed in different
 * ways, as required by Task 3.
 */
public class Item {

    private final int id;
    private String name;
    private double price;

    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{id=" + id + ", name='" + name + "', price=" + price + "}";
    }

    /**
     * Equality/hashCode are based on id only. This matters for Set/HashSet:
     * two Item objects with the same id are considered duplicates, which
     * is what a HashSet uses to reject repeats.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
