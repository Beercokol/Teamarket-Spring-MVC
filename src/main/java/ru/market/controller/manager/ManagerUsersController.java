package ru.market.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.market.model.user.UserRole;
import ru.market.service.interfaces.UserService;

@Controller
@RequestMapping(value = "/managers/user")
@ComponentScan(basePackages = "ru.market.service")
public final class ManagerUsersController {

    private final UserService userService;

    /**
     * Конструктор для инициализации основных переменных контроллера страниц для менеджеров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать
     * объекты.
     */
    @Autowired
    public ManagerUsersController(final UserService userService) {
        super();
        this.userService = userService;
    }

    /**
     * Возвращает всех пользователей на страницу "manager/user/all".
     * URL запроса {"/managers/user", "/managers/user/", "/managers/user/all"},
     * метод GET.
     */
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView viewAllPersonnel() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", this.userService.getPersonnel());
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/manager/all");
        return modelAndView;
    }
    /**
     * Возвращает пользователя с уникальным кодом id на страницу "manager/user/one".
     * URL запроса "/managers/user/view/{id}", метод GET.
     */
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewUser(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.userService.get(id));
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/manager/one");
        return modelAndView;
    }
}