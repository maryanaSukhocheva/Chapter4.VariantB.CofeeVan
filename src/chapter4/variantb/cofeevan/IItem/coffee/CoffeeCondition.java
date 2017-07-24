package chapter4.variantb.cofeevan.IItem.coffee;

/**
 * Состояние кофе.
 *
 */
public enum CoffeeCondition {
    GRAIN,
    GROUND,
    INSTANT;

    @Override
    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }
}