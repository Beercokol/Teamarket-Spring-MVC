/*
 * Copyright
 */

package ru.market.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.market.model.category.Category;
import ru.market.model.photo.Photo;
import ru.market.model.photo.PhotoBuilder;
import ru.market.model.product.Product;
import ru.market.service.interfaces.CategoryService;
import ru.market.service.interfaces.PhotoService;
import ru.market.service.interfaces.ProductService;
import ru.market.service.interfaces.UserService;

import static ru.market.util.validator.ObjectValidator.*;

/**
 * Класс-контроллер страниц управления товарами. К даному контроллеру и соответствующим
 * страницам могут обращатсья пользователи, имеющие роль-админстратор.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/admin/product") сообщаем, что данный контроллер
 * будет обрабатывать запросы, URI которых начинается с "/admin/product".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 */
@Controller
@RequestMapping(value = "/admin/product")
@ComponentScan(basePackages = "ru.market.service")
public final class AdminProductsController {
    /**
     * Объект сервиса для работы с товаров.
     */
    private final ProductService productService;

    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private final CategoryService categoryService;

    /**
     * Объект сервиса для работы с изображенями товаров.
     */
    private final PhotoService photoService;

    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Конструктор для инициализации основных переменных контроллера товаров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически
     * инициализировать объекты.
     */
    @Autowired
    public AdminProductsController(
            final ProductService productService,
            final CategoryService categoryService,
            final PhotoService photoService,
            final UserService userService
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.photoService = photoService;
        this.userService = userService;
    }

    /**
     * Возвращает все товары на страницу "admin/product/all".
     * URL запроса {"/admin/product", "/admin/product/", "/admin/product/all"},
     * метод GET.
     */
    @RequestMapping(
            value = { "", "/", "/all" },
            method = RequestMethod.GET
    )
    public ModelAndView viewAllProducts() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", this.productService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("product/admin/all");
        return modelAndView;
    }

    /**
     * Возвращает товар с уникальным кодом id на страницу "admin/product/one".
     * URL запроса "/admin/view/{id}", метод GET.
     */
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewProduct(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", this.productService.get(id));
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("product/admin/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/product/add" для добавления нового товара.
     * URL запроса "/admin/product/add", метод GET.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public ModelAndView getAddProductPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.addObject("photos", this.photoService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("product/admin/add");
        return modelAndView;
    }

    /**
     * Сохраняет новый товар по входящим параметрам и перенаправляет
     * по запросу "/admin/product/all".
     * URL запроса "/admin/product/save", метод POST.
     */
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    public String saveProduct(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "parameters") final String parameters,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "category") final long categoryId,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "small_photo") final MultipartFile smallPhotoFile,
            @RequestParam(value = "big_photo") final MultipartFile bigPhotoFile,
            @RequestParam(value = "price") final double price
    ) {
        final Product product = new Product();
        product.setTitle(title);
        product.setUrl(url);
        product.setParameters(parameters);
        product.setDescription(description);
        product.setPrice(price);

        final Category category = this.categoryService.get(categoryId);
        product.setCategory(category);

        final PhotoBuilder photoBuilder = Photo.getBuilder();
        photoBuilder.addTitle(photoTitle);
        if (isNotNull(smallPhotoFile)) {
            final String smallUrl = smallPhotoFile.getOriginalFilename();
            photoBuilder.addSmallUrl(smallUrl);
        }
        if (isNotNull(bigPhotoFile)) {
            final String longUrl = bigPhotoFile.getOriginalFilename();
            photoBuilder.addLongUrl(longUrl);
        }
        final Photo photo = photoBuilder.build();
        product.setPhoto(photo);

        this.productService.add(product);
        this.photoService.saveFile(smallPhotoFile);
        this.photoService.saveFile(bigPhotoFile);
        return "redirect:/admin/product/all";
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/admin/product/save" методом GET.
     */
    @RequestMapping(
            value = "/save",
            method = RequestMethod.GET
    )
    public void saveProduct() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/product/save\" is not supported!"
        );
    }

    /**
     * Возвращает страницу "admin/product/edit" для редактирование товара с уникальным
     * кодом, который совпадает с параметром id.
     * URL запроса "/admin/product/edit/{id}", метод GET.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditProductPage(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", this.productService.get(id));
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.addObject("photos", this.photoService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("product/admin/edit");
        return modelAndView;
    }

    /**
     * Обновляет товар по входящим параметрам и перенаправляет
     * по запросу "/admin/product/view/{id}".
     * URL запроса "/admin/product/update", метод POST.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateProduct(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "parameters") final String parameters,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "category") final long categoryId,
            @RequestParam(value = "photo_id") final long photoId,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "small_photo") final MultipartFile smallPhotoFile,
            @RequestParam(value = "big_photo") final MultipartFile bigPhotoFile,
            @RequestParam(value = "price") final double price
    ) {
        final Product product = this.productService.get(id);
        product.setTitle(title);
        product.setUrl(url);
        product.setParameters(parameters);
        product.setDescription(description);
        product.setPrice(price);

        final Category category = this.categoryService.get(categoryId);
        product.setCategory(category);

        final Photo photo = this.photoService.get(photoId);
        if (isNotNull(smallPhotoFile) && isNotEmpty(smallPhotoFile.getOriginalFilename())) {
            final String smallUrl = smallPhotoFile.getOriginalFilename();
            photo.setSmallUrl(smallUrl);
        }
        if (isNotNull(bigPhotoFile) && isNotEmpty(bigPhotoFile.getOriginalFilename())) {
            final String longUrl = bigPhotoFile.getOriginalFilename();
            photo.setLongUrl(longUrl);
        }
        product.setPhoto(photo);

        this.productService.update(product);
        this.photoService.saveFile(smallPhotoFile);
        this.photoService.saveFile(bigPhotoFile);
        return "redirect:/admin/product/view/" + id;
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/admin/product/update" методом GET.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateProduct() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/product/update\" is not supported!"
        );
    }

    /**
     * Удаляет товар с уникальным кодом, который совпадает с входящим параметром id,
     * и перенаправляет по запросу "/admin/products".
     * URL запроса "/admin/product/delete/{id}", метод GET.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteProduct(@PathVariable(value = "id") final long id) {
        this.productService.remove(id);
        return "redirect:/admin/product/all";
    }

    /**
     * Удаляет все товары и перенаправляет по запросу "/admin/product/all".
     * URL запроса "/admin/product/delete_all", метод GET.
     */
    @RequestMapping(
            value = "/delete_all",
            method = RequestMethod.GET
    )
    public String deleteAllProducts() {
        this.productService.removeAll();
        return "redirect:/admin/product/all";
    }
}
