package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DataAccess {
    static void saveMeal(Meal meal){};
    static void deleteMeal(int id){};
    static void updateMeal(int id, Meal meal){};

    static Meal findMeal(int id) {
        return null;
    }

    static List<Meal> findAllMeals(){
        return null;
    }
}
