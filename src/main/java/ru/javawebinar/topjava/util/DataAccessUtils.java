package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class DataAccessUtils implements DataAccess {

    public static final List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, 0),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, 1),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, 2),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, 3),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, 4),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, 5),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, 6)
    );;
    public static final Map<Integer, Meal> mealStorage = meals.stream()
            .collect(Collectors.toMap(Meal::getId, meal -> meal));;



    public static void saveMeal(Meal meal) {
        int lastId = Collections.max(mealStorage.keySet());
        meal.setId(lastId + 1);
        mealStorage.put(lastId + 1, meal);
    }


    public static Meal findMeal(int id) {
        return mealStorage.getOrDefault(id, null);
    }


    public static void deleteMeal(int id) {
        mealStorage.remove(id);
    }


    public static void updateMeal(int id, Meal meal) {
        mealStorage.replace(id, meal);
    }


    public static List<Meal> findAllMeals() {
        return new ArrayList<>(mealStorage.values());
    }
}
