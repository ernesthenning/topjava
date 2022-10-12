package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DataAccess {
    void saveMeal(Meal meal);
    void deleteMeal(int id);
    void updateMeal(int id, Meal meal);
    Meal findMeal(int id);
    List<Meal> findAllMeals();
}
