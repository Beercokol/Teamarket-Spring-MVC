/*
 * Copyright
 */

package ru.market.controller.client;

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
import ru.market.model.order.OrderBuilder;
import ru.market.model.order.OrderStatus;
import ru.market.model.position.SalePosition;
import ru.market.model.product.Product;
import ru.market.model.user.User;
import ru.market.model.user.UserBuilder;
import ru.market.model.user.UserRole;
import ru.market.service.interfaces.CategoryService;
import ru.market.service.interfaces.OrderService;
import ru.market.service.interfaces.ProductService;
import ru.market.service.interfaces.ShoppingCartService;

@Controller
@ComponentScan(basePackages = "ru.market.service")
public final class HomeController {
    /**
     * Объект сервиса для работы с товарами.
     */
    private final ProductService productService;

    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private final CategoryService categoryService;

    /**
     * Объект сервиса для работы с торговой корзиной.
     */
    private final ShoppingCartService shoppingCartService;

    /**
     * Объект сервиса для работы с заказами.
     */
    private final OrderService orderService;


    /**
     * Конструктор для инициализации основных переменных контроллера главных страниц сайта.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать
     * объекты.
     */
    @Autowired
    public HomeController(
            final ProductService productService,
            final CategoryService categoryService,
            final ShoppingCartService shoppingCartService,
            final OrderService orderService

    ) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    /**
     * Возвращает на главную стараницу сайта "client/home".
     * Для формирования страницы с базы подгружаются категории
     * товаров, 12 рандомных товаров и количество товаров в корзине.
     * URL запроса {"", "/", "/index", "/home"}, метод GET.
     */
    @RequestMapping(
            value = { "", "/", "/index", "/home" },
            method = RequestMethod.GET
    )
    public ModelAndView home() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", this.categoryService.getAll());
        final int productNumber = 12;
        modelAndView.addObject("products", this.productService.getRandom(productNumber));
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("home/home");
        return modelAndView;
    }

    /**
     * Возвращает страницу "client/category" с товарами, которые пренадлежат
     * категории с url.
     * URL запроса "/category/{url}", метод GET.
     */
    @RequestMapping(
            value = "/category/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView viewProductsInCategory(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", this.categoryService.get(url));
        modelAndView.addObject("products", this.productService.getByCategoryUrl(url));
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("category/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "client/products" с всема товарами.
     * URL запроса "/product/all", метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/product/all",
            method = RequestMethod.GET
    )
    public ModelAndView viewAllProducts() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", this.productService.getAll());
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("product/all");
        return modelAndView;
    }

    /**
     * Возвращает страницу "client/product" с 1-м товаром с уникальним URL,
     * который совпадает с входящим параметром url.
     * URL запроса "/product/{url}", метод GET.
     * В запросе в параметре url можно передавать как URL так и артикль товара.
     */
    @RequestMapping(
            value = "/product/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView viewProduct(@PathVariable("url") final String url) {
        Product product;
        try {
            final int article = Integer.parseInt(url);
            product = this.productService.getByArticle(article);
        } catch (NumberFormatException ex) {
            product = this.productService.getByUrl(url);
        }
        final long categoryId = product.getCategory().getId();
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", product);
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        final int categoryNumber = 4;
        modelAndView.addObject(
                "featured_products",
                this.productService.getRandomByCategoryId(
                        categoryNumber, categoryId, product.getId()
                )
        );
        modelAndView.setViewName("product/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "client/cart" - страница корзины с торговыми
     * позициями, которие сделал клиент.
     * URL запроса "/cart", метод GET.
     */
    @RequestMapping(
            value = "/cart",
            method = RequestMethod.GET
    )
    public ModelAndView viewCart() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sale_positions", this.shoppingCartService.getSalePositions());
        modelAndView.addObject("price_of_cart", this.shoppingCartService.getPrice());
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("cart/cart");
        return modelAndView;
    }

    /**
     * Добавляет товар с уникальным кодом id в корзину и перенаправляет по запросу "/cart".
     * URL запроса "/cart/add", метод POST.
     */
    @RequestMapping(
            value = "/cart/add",
            method = RequestMethod.POST
    )
    public String addProductToCart(@RequestParam(value = "id") final long id) {
        final int number = 1;
        final SalePosition position = new SalePosition();
        position.setNumber(number);
        final Product product = this.productService.get(id);
        position.setProduct(product);
        this.shoppingCartService.add(position);
        return "redirect:/cart";
    }

    /**
     * Возвращает исключение IllegalMappingException,если обратится
     * по запросу "/cart/add" методом GET.
     *
     * @throws IllegalMappingException Бросает исключение,если обратится к
     *                                 этому методу GET.
     */
    @RequestMapping(
            value = "/cart/add",
            method = RequestMethod.GET
    )
    public void addProductToCart() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/add\" is not supported!"
        );
    }

    /**
     * Быстрое добавления товара с уникальным номером id в корзину и перенаправление
     * по запросу входящего параметра url.
     * URL запроса "/cart/add_quickly", метод POST.
     */
    @RequestMapping(
            value = "/cart/add_quickly",
            method = RequestMethod.POST
    )
    public String addProductToCartQuickly(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "url") final String url
    ) {
        final int number = 1;
        final SalePosition position = new SalePosition();
        position.setNumber(number);
        final Product product = this.productService.get(id);
        position.setProduct(product);
        this.shoppingCartService.add(position);
        return "redirect:" + url;
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/cart/add_quickly" методом GET.
     */
    @RequestMapping(
            value = "/cart/add_quickly",
            method = RequestMethod.GET
    )
    public void addProductToCartQuickly() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/add_quickly\" is not supported!"
        );
    }

    /**
     * Очищает корзину от торгвых позиции и перенаправление по запросу "/cart".
     * URL запроса "/cart/clear", метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/cart/clear",
            method = RequestMethod.GET
    )
    public String clearCart() {
        this.shoppingCartService.clear();
        return "redirect:/cart";
    }

    /**
     * Оформляет и сохраняет заказ клиента, возвращает страницу "client/checkout".
     * Если корзина пуста, по перенаправляет на главную страницу.
     * URL запроса "/checkout", метод POST.
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ModelAndView viewCheckout(
            @RequestParam(value = "user_name") final String name,
            @RequestParam(value = "user_email") final String email,
            @RequestParam(value = "user_phone") final String phone
    ) {
        final ModelAndView modelAndView = new ModelAndView();
        if (this.shoppingCartService.getSize() > 0) {
            final UserBuilder userBuilder = User.getBuilder();
            userBuilder.addRole(UserRole.CLIENT)
                    .addName(name)
                    .addEmail(email)
                    .addPhone(phone);
            final User client = userBuilder.build();
            final OrderBuilder orderBuilder = Order.getBuilder();
            orderBuilder.addStatus(OrderStatus.NEW)
                    .addClient(client)
                    .addSalePositions(this.shoppingCartService.getSalePositions());
            final Order order = orderBuilder.build();
            this.orderService.add(order);
            modelAndView.addObject("order", order);
            modelAndView.addObject("sale_positions", order.getSalePositions());
            modelAndView.addObject("price_of_cart", this.shoppingCartService.getPrice());
            this.shoppingCartService.clear();
            modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
            modelAndView.setViewName("cart/checkout");
        } else {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    /**
     * Перенаправляет по запросу "/cart", если обратится
     * по запросу "/checkout" методом GET.
     */
    @RequestMapping(
            value = "/checkout",
            method = RequestMethod.GET
    )
    public String viewCheckout() {
        return "redirect:/cart";
    }

    /**
     * Возвращает исключение IllegalMappingException, если пользователь обращается
     * к запросам, к которым он не имеет права доступа (роли).
     */
    @RequestMapping(
            value = "/forbidden_exception",
            method = RequestMethod.GET
    )
    public void getIllegalMappingException() throws IllegalMappingException {
        throw new IllegalMappingException(
                "You do not have sufficient permissions to access this page."
        );
    }

    /**
     * Перенаправляет на страницу с заказами для администратора.
     */
    @RequestMapping(
            value = { "/admin", "/admin/" },
            method = RequestMethod.GET
    )
    public String redirectToAdminPage() {
        return "redirect:/admin/order/all";
    }

}
