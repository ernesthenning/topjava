package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class JspMealController {
    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);

    @Autowired
    private MealService mealService;

    @Autowired
    private UserService userService;

    @GetMapping("/meals")
    public String getAll(Model model) {
        int userId = SecurityUtil.authUserId();
        List<Meal> meals = mealService.getAll(userId);
        List<MealTo> mealTos = MealsUtil.getTos(meals, userService.get(userId).getCaloriesPerDay());
        model.addAttribute("meals", mealTos);
        log.info("get all meals for user {}", userId);
        return "meals";
    }

    @GetMapping("/filter")
    public String filter(Model model, HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meals", MealsUtil.getFilteredTos(mealService.getBetweenInclusive(startDate, endDate, userId),
                userService.get(userId).getCaloriesPerDay(), startTime, endTime));
        log.info("get between dates {} - {} and times {} - {} for user {}", startDate, endDate, startTime, endTime, userId);
        return "meals";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        int mealId = Integer.parseInt(id);
        int userId = SecurityUtil.authUserId();
        mealService.delete(mealId, userId);
        log.info("delete meal {} for user {}", id, userId);
        return "redirect:meals";
    }

    @GetMapping("/update")
    public String updateGet(Model model, @RequestParam String id) {
        int mealId = Integer.parseInt(id);
        model.addAttribute("meal", mealService.get(mealId, SecurityUtil.authUserId()));
        return "mealForm";
    }

    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @PostMapping("/save")
    public String save(HttpServletRequest request) {
        int userId = SecurityUtil.authUserId();
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (!StringUtils.hasLength(request.getParameter("id"))) {
            checkNew(meal);
            mealService.update(meal, userId);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            assureIdConsistent(meal, id);
            mealService.create(meal, userId);
        }
        return "redirect:meals";
    }

}
