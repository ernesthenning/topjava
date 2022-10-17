package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import static ru.javawebinar.topjava.util.MealsUtil.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
@Service
public class MealService {

    private final MealRepository repository;
    public MealService (MealRepository repository) {
        this.repository = repository;
    }

    public Meal create (Meal meal, int authUserId) {
        return repository.save(meal, authUserId);
    }
    public void delete (int id, int authUserId) {
        checkNotFoundWithId(repository.delete(id, authUserId), id);
    }
    public Meal get(int id, int authUserId) {
        return checkNotFoundWithId(repository.get(id, authUserId), id);
    }
    public List<MealTo> getAll(int authUserId, int caloriesPerDay) {
        return getTos(repository.getAll(authUserId), caloriesPerDay);
    }
    public List<MealTo> getFiltered (LocalDateTime startDatetime, LocalDateTime endDateTime, int authUserId, int caloriesPerDay) {
        return getFilteredTos(repository.getFilteredByDate(startDatetime.toLocalDate(), endDateTime.toLocalDate(), authUserId),
                caloriesPerDay, startDatetime.toLocalTime(), endDateTime.toLocalTime());
    }
    public Meal update(Meal meal, int authUserId) {
        return checkNotFoundWithId(repository.save(meal, authUserId), meal.getId());
    }

}