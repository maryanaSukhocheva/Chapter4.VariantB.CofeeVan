package chapter4.variantb.cofeevan.storage;

import chapter4.variantb.cofeevan.IItem.Find;
import chapter4.variantb.cofeevan.IItem.IItem;
import chapter4.variantb.cofeevan.IItem.Sort;
import java.util.List;
import chapter4.variantb.cofeevan.exceptions.CloneItemException;

/**
 * Типизирующий интерфейс
 * для хранилищ.
 *
 */
public interface IStorage {
    /**
     * Добавить предмет в хранилище.
     *
     * @param item предмет
     * @return возвращает true
     * если добавление упешно, false
     * если для товара нет места
     */
    boolean add(IItem item);

    /**
     * Увеличиваем обьем хранилища
     * на входящую величину.
     *
     * @param totalVolume входящая величина
     */
    void upVolume(int totalVolume);

    /**
     * Получить состояние хранилища
     * в виде строки.
     *
     * @return возвращает строку
     * которая содержит информацию
     * о состоянии хранилища
     */
    String getState();

    /**
     * Сортировать предметы по
     * определенному свойству.
     *
     * @param sortBy свойство по которому
     *               бдет произведена сортировка
     */
    void sort(Sort sortBy);

    /**
     * Возвращает коллекцию клонированных предметов
     * по заданным параметрам
     *
     * @param findBy свойство предмета
     *               по которому производится
     *               поиск
     * @param startValue начальное значение
     *                   свойства
     * @param endValue конечное значение свойства
     * @return возвращает коллекцию клонированных предметов.
     * @throws CloneItemException Исключение при клонировании
     */
    List<IItem> find(Find findBy, int startValue, int endValue) throws CloneItemException;

    void increaseDateOfExpiry(int days);
}
