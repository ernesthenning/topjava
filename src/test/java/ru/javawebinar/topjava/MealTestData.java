package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL0_ID = START_SEQ + 3;
    public static final int MEAL1_ID = START_SEQ + 4;
    public static final int MEAL2_ID = START_SEQ + 5;
    public static final int MEAL3_ID = START_SEQ + 6;
    public static final int MEAL4_ID = START_SEQ + 7;
    public static final int MEAL5_ID = START_SEQ + 8;
    public static final int MEAL6_ID = START_SEQ + 9;
    public static final int MEAL7_ID = START_SEQ + 10;
    public static final int MEAL8_ID = START_SEQ + 11;
    public static final int MEAL9_ID = START_SEQ + 12;
    public static final int NOT_FOUND = 5;

    public static final Meal meal0 = new Meal(MEAL0_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal meal1 = new Meal(MEAL1_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal meal2 = new Meal(MEAL2_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal meal3 = new Meal(MEAL3_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal meal4 = new Meal(MEAL4_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 500);
    public static final Meal meal5 = new Meal(MEAL5_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1000);
    public static final Meal meal6 = new Meal(MEAL6_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 500);
    public static final Meal meal7 = new Meal(MEAL7_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак админа", 500);
    public static final Meal meal8 = new Meal(MEAL8_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед админа", 1000);
    public static final Meal meal9 = new Meal(MEAL9_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин админа", 1000);
    public static final List<Meal> userMeals = Arrays.asList(meal6, meal5, meal4, meal3, meal2, meal1, meal0);

    public static Meal getUpdated() {
        Meal updated = new Meal(meal0);
        updated.setCalories(500);
        updated.setDescription("Обед чемпиона");
        updated.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 1));
        return updated;
    }

    public static Meal getNew() {
        return new Meal(LocalDateTime.now(), "Перекус", 300);
    }
    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("dateTime").isEqualTo(expected);
    }

}
