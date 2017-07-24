package chapter4.variantb.cofeevan.main;

import chapter4.variantb.cofeevan.IItem.Find;
import chapter4.variantb.cofeevan.IItem.Sort;
import chapter4.variantb.cofeevan.controller.Controller;
import chapter4.variantb.cofeevan.exceptions.ProjectMainException;

/**
 * @author Sukhocheva Maryana
 * 
 *
 */
public class Main {
    public static void main(String[] args) {
        try {
            Controller controller = new Controller(100, 100);
            controller.fillStorage();
            System.out.println("Ассортимент товара");
            controller.outputStorage();
            controller.outputVolumeCost();
            controller.sort(Sort.BY_COST);
            System.out.println("Сортировка товаров на основе соотношения цены и веса");
            controller.outputStorage();
            System.out.println("Товар, соответствующий заданному диапазону параметров качества");

            controller.find(Find.BY_COST, 2, 4);
        } catch (ProjectMainException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
