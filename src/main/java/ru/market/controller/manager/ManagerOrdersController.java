/*
 * Copyright
 */

package ru.market.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.market.model.order.Order;
import ru.market.model.order.OrderStatus;
import ru.market.model.user.User;
import ru.market.model.user.UserRole;
import ru.market.service.interfaces.OrderService;
import ru.market.service.interfaces.UserService;

import static ru.market.util.validator.ObjectValidator.*;

import java.util.Date;

@Controller
@RequestMapping(value = "/managers/order")
@ComponentScan(basePackages = "ru.market.service")
public final class ManagerOrdersController {

    private final UserService userService;

    private final OrderService orderService;

    /**
     * Конструктор для инициализации основных переменных контроллера страниц для менеджеров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать
     * объекты.
     */
    @Autowired
    public ManagerOrdersController(
            final UserService userService,
            final OrderService orderService
    ) {
        super();
        this.userService = userService;
        this.orderService = orderService;
    }

    /**
     * Возвращает все заказы, сделаные клиентами, на страницу "manager/order/all".
     */
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView viewAllOrders() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", this.orderService.getAll());
        modelAndView.addObject("status_new", OrderStatus.NEW);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("order/manager/all");
        return modelAndView;
    }

    /**
     * Возвращает заказ с уникальным кодом id на страницу "manager/order/one".
     */
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewOrder(@PathVariable(value = "id") final long id) {
        final Order order = this.orderService.get(id);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("status_new", OrderStatus.NEW);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.setViewName("order/manager/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/order/edit" для редактирование заказа с уникальным
     * кодом,который совпадает с параметром id, или перенаправляет по запросу
     * "/managers/orders", если этот заказ уже обработал другой менеджер.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") final long id) {
        final Order order = this.orderService.get(id);
        final ModelAndView modelAndView = new ModelAndView();
        if (isNotNull(order.getManager()) ||
                (order.getManager().equals(this.userService.getAuthenticatedUser()))) {
            modelAndView.addObject("order", order);
            modelAndView.addObject("sale_positions", order.getSalePositions());
            modelAndView.addObject("order_price", order.getPrice());
            modelAndView.addObject("statuses", OrderStatus.values());
            modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
            modelAndView.setViewName("order/manager/edit");
        } else {
            modelAndView.setViewName("redirect:/managers/order/all");
        }
        return modelAndView;
    }

    /**
     * Обновляет заказ по входящим параметрам и перенаправляет по запросу "/admin/order/view/{id}".
     * URL запроса "/admin/order/update", метод POST.
     *
     * @param id           Код заказа для обновления.
     * @param managerId    Код менеджера или администратора, который обработал заказ в последний раз.
     * @param number       Номер заказа.
     * @param statusName     Код статуса выполнения заказа.
     * @param name         Имя клиента, оформивший заказ.
     * @param email        Электронная почта клиента.
     * @param phone        Номер телефона клиента.
     * @param address      Адрес доставки заказа.
     * @param details      Детали доставки заказа.
     * @param description  Описание заказа.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateOrder(
            @RequestParam final long id,
            @RequestParam(value = "auth_user") final long managerId,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "status") final String statusName,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "shipping-address") final String address,
            @RequestParam(value = "shipping-details") final String details,
            @RequestParam final String description
    ) {
        final Order order = this.orderService.get(id);
        if (isNotNull(order.getManager()) || (order.getManager() == this.userService.getAuthenticatedUser())) {
            final User client = order.getClient();
            client.setName(name);
            client.setEmail(email);
            client.setPhone(phone);
            final OrderStatus status = OrderStatus.valueOf(statusName);
            User manager = null;
            if (!status.equals(OrderStatus.NEW)) {
                manager = this.userService.get(managerId);
            }
            order.setNumber(number);
            order.setDate(new Date());
            order.setShippingAddress(address);
            order.setShippingDetails(details);
            order.setDescription(description);
            order.setStatus(status);
            order.setClient(client);
            order.setManager(manager);
            this.orderService.update(order);
        }
        return "redirect:/manager/order/view/" + id;
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/admin/order/update" методом GET.
     */
    @RequestMapping(
            value = "/admin/order/update",
            method = RequestMethod.GET
    )
    public void updateOrder() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/order/update\" is not supported!"
        );
    }
}