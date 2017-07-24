package chapter4.variantb.cofeevan.IItem.coffee;

import chapter4.variantb.cofeevan.IItem.IItem;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import chapter4.variantb.cofeevan.exceptions.CloneItemException;

/**
 * Абстрактный класс Кофе,
 * предок разных сортов.
 *
 */
public abstract class Coffee implements IItem, Cloneable  {
    protected Calendar dateOfExpiry;
    private double cost;
    private int volume;
    private int volumeOfCoffee;
    private CoffeeCondition coffeeCondition;
    protected CoffeeWrap coffeeWrap;
    

    public Coffee(CoffeeCondition coffeeCondition, CoffeeWrap coffeeWrap) {
        this.coffeeCondition = coffeeCondition;
        this.coffeeWrap = coffeeWrap;
        this.cost = CoffeePrice.getPrice(this.getClass());
        this.volume = coffeeWrap.getVolume() + coffeeWrap.getVolumeOfWrap();
        this.volumeOfCoffee = coffeeWrap.getVolume();
        this.dateOfExpiry = new GregorianCalendar();
        this.dateOfExpiry.add(Calendar.DAY_OF_YEAR, 30);
    }


    @Override
    public double getCost() {
        return this.cost;
    }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder str = new StringBuilder("(");
        str.append("сорт ").append(this.getClass().getSimpleName()).append(", ");
        str.append("состояние кофе: ").append(this.coffeeCondition).append(", ");
        str.append("упаковка ").append(this.coffeeWrap).append(", ");
        str.append("объем кофе ").append(this.volumeOfCoffee).append(", ");
        str.append("объем общий ").append(this.volume).append(", ");
        str.append("стоимость ").append(this.cost).append(", ");
       str.append("срок годности ").append(dateFormat.format(dateOfExpiry.getTime())).append(", ");
       return  str.toString();
       
    }

@Override
    public IItem getClone() throws CloneItemException {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneItemException("Cant clone goods");
        }
    }

    @Override
    public IItem clone() throws CloneNotSupportedException {
        return (IItem) super.clone();
    }

}
