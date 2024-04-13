package com.ecommerce.futrako.utils;


import com.ecommerce.futrako.dto.RequestCategoryDto;
import com.ecommerce.futrako.model.Category;
import com.ecommerce.futrako.model.Product;
import com.ecommerce.futrako.service.ImageService;
import com.ecommerce.futrako.service.interfaces.ICategoryService;
import com.ecommerce.futrako.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataLoaderCategory implements CommandLineRunner {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private Mapper mapper;


    @Override
    public void run(String... args) throws Exception {


        Category category;
        Product product;

        category = Category.builder().name("Hamburguesas").description("Hamburguesas de todo tipo").build();
        category = (Category) categoryService.postCategory(mapper.getMapper().map(category, RequestCategoryDto.class)).getBody();
        product = Product.builder().category(mapper.getMapper().map(category, Category.class)).name("Gratinada").price(400.0).description("Hamburguesa comun").stock(50L).build();
        productService.postProduct(product);

        category = Category.builder().name("Papas fritas").description("Hace falta que te lo diga?").build();
        category = (Category) categoryService.postCategory(mapper.getMapper().map(category, RequestCategoryDto.class)).getBody();
        product = Product.builder().category(mapper.getMapper().map(category, Category.class)).name("Papas comunes").price(400.0).description("").stock(50L).build();
        productService.postProduct(product);

        category = Category.builder().name("Bebidas").description("").build();
        category = (Category) categoryService.postCategory(mapper.getMapper().map(category, RequestCategoryDto.class)).getBody();
        product = Product.builder().category(mapper.getMapper().map(category, Category.class)).name("Cerveza rubia").price(400.0).description("").stock(50L).build();
        productService.postProduct(product);

        category = Category.builder().name("Pizzas").description("").build();
        category = (Category) categoryService.postCategory(mapper.getMapper().map(category, RequestCategoryDto.class)).getBody();
        product = Product.builder().category(mapper.getMapper().map(category, Category.class)).name("Mozzarella").price(400.0).description("").stock(50L).build();
        productService.postProduct(product);

        category = Category.builder().name("Promociones").description("").build();
        category = (Category) categoryService.postCategory(mapper.getMapper().map(category, RequestCategoryDto.class)).getBody();
        product = Product.builder().category(mapper.getMapper().map(category, Category.class)).name("Muza y cerveza").price(400.0).description("").stock(50L).build();
        productService.postProduct(product);


    }
}