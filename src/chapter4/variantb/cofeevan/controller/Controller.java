package chapter4.variantb.cofeevan.controller;

import chapter4.variantb.cofeevan.IItem.Find;
import chapter4.variantb.cofeevan.IItem.IItem;
import chapter4.variantb.cofeevan.IItem.Sort;
import chapter4.variantb.cofeevan.IItem.coffee.Arabica;
import chapter4.variantb.cofeevan.IItem.coffee.Coffee;
import chapter4.variantb.cofeevan.IItem.coffee.CoffeeFabric;
import chapter4.variantb.cofeevan.IItem.coffee.CoffeePrice;
import chapter4.variantb.cofeevan.IItem.coffee.Liberica;
import chapter4.variantb.cofeevan.IItem.coffee.Robusta;
import chapter4.variantb.cofeevan.exceptions.CloneItemException;
import chapter4.variantb.cofeevan.exceptions.InputException;
import chapter4.variantb.cofeevan.storage.IStorage;
import chapter4.variantb.cofeevan.storage.Van;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Общий контроллер программы.
 *
 */
public class Controller {
    private List<Class<? extends Coffee>> listOfCoffee =
            Arrays.asList(Arabica.class, Robusta.class, Liberica.class);
    private double totalMoney;
    private double usedMoney;
    private IStorage storage;
    private CoffeeFabric coffeeFabric;
    private List<IItem> find = new ArrayList<>();

    /**
     * Указываем сумму и обьем
     * для создания фрона определенного
     * обьема и наполнения его на определенную
     * сумму
     *
     * @param money сумма
     * @param volume обьем
     * @throws InputException если значения
     * отрицательные или нулевые
     */
    public Controller(double money, int volume)
            throws InputException {
        if (money <= 0 || volume <= 0) {
            throw new InputException
                    ("money or volume cant be zero or negative");
        } else {
            this.totalMoney = money;
            this.storage = new Van(volume);
            new CoffeePrice(listOfCoffee);
            this.coffeeFabric = new CoffeeFabric(listOfCoffee);
        }
    }

    /**
     * Проверка достаточно ли денег
     * для добавления нового товара
     * определенной цены.
     *
     * @param cost цена добовляемого товара
     * @return true если цена товара и всех уже
     * погруженных товаров меньше максимального обьема
     */
    private boolean hasFreeMoney(double cost) {
        return this.usedMoney + cost <= this.totalMoney;
    }

    private String getState() {
        return "Стоимость товара: " + this.usedMoney
                + " от общей суммы: " + this.totalMoney;
    }

    /**
     * Возвращает отформатированную
     * определенным образом информацию
     * о найденных в хранилище предметах.
     *
     * @return строка с данными о
     * предметах
     */
    private String findToString(List<IItem> items, Find findBy,
                                int startValue, int endValue) {
        StringBuilder result = new StringBuilder("Результат поиска ");
        result.append(findBy).append(" [").append(startValue).append(" - ")
                .append(endValue).append("]\n");
        double sum = 0;
        int volume = 0;
        for (IItem item : items) {
            result.append(item.toString()).append("\n");
            sum += item.getCost();
            volume += item.getVolume();
        }
        result.append("\nОбъем товара: ").append(volume)
                .append("\nСтоимость товара: ").append(sum).append("\n");
        return result.toString();
    }

    /**
     * Вызов поиска в хранилище
     * по заданным парраметрам и
     * получение клонов предметов
     * в отдельную коллекцию
     *
     * @param findBy свойство предмета
     *               по которому ведется
     *               поиск
     * @param startValue начальное значение
     *                   свойства
     * @param endValue конечное значение свойства
     * @throws CloneItemException исключение при клонировании
     * предмета
     */
    public void find(Find findBy, int startValue, int endValue)
            throws CloneItemException {
        find.clear();
        find.addAll(storage.find(findBy, startValue, endValue));
        if (!find.isEmpty()) {
            System.out.println(findToString(find, findBy,
                    startValue, endValue));
        }
    }

    /**
     * Заполнение хранилища.
     *
     */
    public void fillStorage() {
        while(true) {
            IItem item = coffeeFabric.getRandomCoffee();
            if (!hasFreeMoney(item.getCost()) || !storage.add(item)) {
                break;
            }
            usedMoney += item.getCost();
        }
    }

    /**
     * Вывод информации
     * о фургоне
     * 
     *
     */
    public void outputStorage() {
        System.out.println(storage);
       System.out.println();
    }
    
    /**
     * Вывод информации
     * о использованных и общих
     * обьеме и количестве денег.
     *
     */
    public void outputVolumeCost() {
        
        System.out.println(this.getState());
        System.out.println();
       
    }
 
    /**
     * Увеличение размера хранилища на
     * заданный обьем
     *
     * @param volume обьем увеличения
     */
    public void upVolumeOfStorage(int volume) {
        if (volume > 0){
            storage.upVolume(volume);
        } else { 
            System.out.println("Volume cant be zero or negative");
        }
    }

    /**
     * Увеличение срока годности
     * на минимально дней.
     *
     * @param days кол-во дней
     */
    public void increaseDateOfExpiry(int days) {
        if (days > 0) {
            storage.increaseDateOfExpiry(days);
        } else {
            System.out.println("days cant be zero or negative");
        }
    }

    /**
     * Вызов сортировки в хранилище
     * по заданному свойству предмета
     *
     * @param sortBy свойство предмета
     *               по которому ведется
     *               сортировка
     */
    public void sort(Sort sortBy) {
        storage.sort(sortBy);
    }
}
