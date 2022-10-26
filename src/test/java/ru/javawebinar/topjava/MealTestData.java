package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_MEAL0_ID = START_SEQ + 3;
    public static final int USER_MEAL1_ID = START_SEQ + 4;
    public static final int USER_MEAL2_ID = START_SEQ + 5;
    public static final int USER_MEAL3_ID = START_SEQ + 6;
    public static final int USER_MEAL4_ID = START_SEQ + 7;
    public static final int USER_MEAL5_ID = START_SEQ + 8;
    public static final int USER_MEAL6_ID = START_SEQ + 9;
    public static final int ADMIN_MEAL7_ID = START_SEQ + 10;
    public static final int ADMIN_MEAL8_ID = START_SEQ + 11;
    public static final int ADMIN_MEAL9_ID = START_SEQ + 12;
    public static final int NOT_FOUND = 5;

    public static final Meal userMeal0 = new Meal(USER_MEAL0_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal userMeal1 = new Meal(USER_MEAL1_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal userMeal2 = new Meal(USER_MEAL2_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal userMeal3 = new Meal(USER_MEAL3_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal userMeal4 = new Meal(USER_MEAL4_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 500);
    public static final Meal userMeal5 = new Meal(USER_MEAL5_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1000);
    public static final Meal userMeal6 = new Meal(USER_MEAL6_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 500);
    public static final Meal adminMeal7 = new Meal(ADMIN_MEAL7_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак админа", 500);
    public static final Meal adminMeal8 = new Meal(ADMIN_MEAL8_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед админа", 1000);
    public static final Meal adminMeal9 = new Meal(ADMIN_MEAL9_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин админа", 1000);
    public static final List<Meal> userMeals = Arrays.asList(userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1, userMeal0);
    public static final  List<Meal> filteredUserMeals = Arrays.asList(userMeal2, userMeal1, userMeal0);

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal0);
        updated.setCalories(500);
        updated.setDescription("Обед чемпиона");
        updated.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 1));
        return updated;
    }

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 1), "Перекус", 300);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

}
