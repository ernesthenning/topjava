package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController extends AbstractMealController {

    public JspMealController(MealService mealService) {
        super(mealService);
    }

    @GetMapping("/meals")
    public String getAll(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/filter")
    public String filter(HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        getBetween(startDate, startTime, endDate, endTime);
        return "meals";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        int mealId = Integer.parseInt(id);
        delete(mealId);
        return "redirect:meals";
    }

    @GetMapping("/save")
    public String save(Model model, HttpServletRequest request) {
        if (request.getParameter("action").equals("create")) {
            model.addAttribute("meal",
                    new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        } else {
            String id = request.getParameter("id");
            int mealId = Integer.parseInt(id);
            model.addAttribute("meal", mealService.get(mealId, SecurityUtil.authUserId()));
        }
        return "mealForm";
    }

    @PostMapping("/save")
    public String save(HttpServletRequest request) {
        String id = request.getParameter("id");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (!id.isEmpty()) {
            update(meal, Integer.parseInt(id));
        } else {
            create(meal);
        }
        return "redirect:meals";
    }

}
