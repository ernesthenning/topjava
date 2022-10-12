package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class DataAccessUtils implements DataAccess {

    private final List<Meal> meals;
    private final Map<Integer, Meal> mealStorage;

    public DataAccessUtils() {
        this.meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, 0),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, 1),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, 2),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, 3),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, 4),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, 5),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, 6)
        );
        this.mealStorage = meals.stream()
                .collect(Collectors.toMap(Meal::getId, meal -> meal));
    }

    @Override
    public void saveMeal(Meal meal) {
        int lastId = Collections.max(mealStorage.keySet());
        meal.setId(lastId + 1);
        mealStorage.put(lastId + 1, meal);
    }

    @Override
    public Meal findMeal(int id) {
        return mealStorage.getOrDefault(id, null);
    }

    @Override
    public void deleteMeal(int id) {
        mealStorage.remove(id);
    }

    @Override
    public void updateMeal(int id, Meal meal) {
        mealStorage.replace(id, meal);
    }

    @Override
    public List<Meal> findAllMeals() {
        return new ArrayList<>(mealStorage.values());
    }
}
