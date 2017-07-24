package chapter4.variantb.cofeevan.storage;

import chapter4.variantb.cofeevan.IItem.Find;
import chapter4.variantb.cofeevan.IItem.IItem;
import chapter4.variantb.cofeevan.IItem.Sort;
import java.util.ArrayList;
import java.util.List;
import chapter4.variantb.cofeevan.exceptions.CloneItemException;

/**
 * Описание Фургона.
 *
 */
public class Van implements IStorage {
    private int totalVolume;
    private int usedVolume;
    private List<IItem> items;

    private boolean isStorageHasFreeVolume(double volume) {
        return usedVolume + volume <= this.totalVolume;
    }

    public Van(int totalVolume) {
        this.totalVolume = totalVolume;
        items = new ArrayList<>();
    }

    @Override
    public boolean add(IItem item) {
        if (isStorageHasFreeVolume(item.getVolume())) {
            items.add(item);
            usedVolume += item.getVolume();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void upVolume(int volume) {
        this.totalVolume = this.totalVolume + volume;
    }

    @Override
    public String getState() {
        return "Вес товара: " + this.usedVolume
                + " от общего веса: " + this.totalVolume;
    }

    @Override
    public void sort(Sort sortBy) {
        sortBy.sort(this.items);
    }

    @Override
    public List<IItem> find(Find findBy, int startValue, int endValue)
            throws CloneItemException {
        return findBy.find(this.items, startValue, endValue);
    }

    @Override
    public void increaseDateOfExpiry(int days) {
        for (IItem item : items) {
            item.increaseDateOfExpiry(days);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (IItem item : this.items) {
            result.append(item.toString()).append("\n");
        }
        return result.toString();
    }

}
