package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(m -> save(m, m.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (repository.containsKey(userId)) {
                repository.get(userId).put(meal.getId(), new Meal(meal.getId(), meal.getDateTime(),
                        meal.getDescription(), meal.getCalories(), userId));
            } else {
                repository.put(userId, new ConcurrentHashMap<>());
                repository.get(userId).put(meal.getId(), new Meal(meal.getId(), meal.getDateTime(),
                        meal.getDescription(), meal.getCalories(), userId));
            }
            return meal;
        }
        // handle case: update, but not present in storage
        Map<Integer, Meal> userIdMeals = repository.get(userId);
        Meal presentMeal = userIdMeals.get(meal.getId());
        if (presentMeal != null && presentMeal.getUserId() == userId) {
            return userIdMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> new Meal(meal.getId(), meal.getDateTime(),
                    meal.getDescription(), meal.getCalories(), userId));
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (get(id, userId) != null) {
            return repository.get(userId).remove(id) != null;
        } else {
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userIdMeals = repository.get(userId);
        Meal meal = userIdMeals.get(id);
        if (meal != null && userId == meal.getUserId()) {
            return meal;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return filterByPredicate(m -> true, userId);
    }

    public List<Meal> getFilteredByDate(LocalDate startDate, LocalDate endDate, int userId) {
        return filterByPredicate(m -> DateTimeUtil.isBetweenDates(m.getDate(), startDate, endDate), userId);
    }

    private List<Meal> filterByPredicate(Predicate<Meal> filter, int userId) {
        return repository.get(userId).values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

