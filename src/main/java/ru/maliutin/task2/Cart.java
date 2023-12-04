package ru.maliutin.task2;

import ru.maliutin.task2.product.eatable.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        Map<String, Boolean> PFC = new HashMap<>();
        PFC.put("proteins", false);
        PFC.put("fats", false);
        PFC.put("carbohydrates", false);

        foodstuffs.forEach(food -> {
            if (food.getProteins())
                PFC.put("proteins", true);
            if (food.getFats())
                PFC.put("fats", true);
            if(food.getCarbohydrates())
                PFC.put("carbohydrates", true);
        });

        if (!PFC.containsValue(false))
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        market.getThings(clazz).forEach(thing -> {
            if (!PFC.get("proteins") && thing.getProteins())
            {
                PFC.put("proteins", true);
                foodstuffs.add(thing);
            }
            else if (!PFC.get("fats") && thing.getFats())
            {
                PFC.put("fats", true);
                foodstuffs.add(thing);
            }
            else if (!PFC.get("carbohydrates") && thing.getCarbohydrates())
            {
                PFC.put("carbohydrates", true);
                foodstuffs.add(thing);
            }
        });

        if (!PFC.containsValue(false))
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
    }


    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs()
    {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }

}
