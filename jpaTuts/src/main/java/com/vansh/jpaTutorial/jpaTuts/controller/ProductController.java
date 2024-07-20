package com.vansh.jpaTutorial.jpaTuts.controller;


import com.vansh.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.vansh.jpaTutorial.jpaTuts.respositories.ProductRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {


    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository=productRepository;
    }



    @GetMapping({"","/"})
    public List<ProductEntity>getProducts()
    {
        return  productRepository.findByTitleOrderByPrice("Portable Charger");
    }

    @GetMapping(path = "/all")
    public List<ProductEntity> getAllProducts()
    {
        //my default if we write it as findByOrderBy() it will order it by id
        return  productRepository.findByOrderByPrice();
    }


    @GetMapping(path="/sort")
    public List<ProductEntity> getSorted1()
    {
        //we will pass instance of Sort class
        /*
          one more way to do it
          Sort sort=Sort.by(Sort.Direction.ASC,"");
         */

        Sort sort=Sort.by(Sort.Order.asc("quantity"),Sort.Order.desc("price"));
        return  productRepository.findBy(sort);
    }

    @GetMapping(path="/sortBy")
    public List<ProductEntity>getSorted2(@RequestParam(defaultValue = "id")String sortOnBasis)
    {

        //http://localhost:8080/products/sortBy?sortOnBasis=quantity example link
        Sort sort=Sort.by(Sort.Order.asc(sortOnBasis),Sort.Order.asc("price"));
        return  productRepository.findBy(sort);
    }



    @GetMapping(path = "/page")
    //http://localhost:8080/products/page?sortBy=id&pageNumber=0&pageSize=5
    //example Url
    public List<ProductEntity>getPages(@RequestParam(defaultValue = "id")String sortBy ,
                                       @RequestParam(defaultValue = "0") Integer pageNumber,
                                       @RequestParam(defaultValue = "5")Integer pageSize)
    {
        Sort sort=Sort.by(Sort.Order.asc(sortBy));
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        return productRepository.findAll(pageable).getContent();
    }


    @GetMapping(path = "/pageByTitle")
    //http://localhost:8080/products/pageByTitle?findBy=Portable&pageNumber=0&pageSize=5
    //example link
    public  List<ProductEntity>getPagesBytitle(@RequestParam(defaultValue = "")String findBy ,
                                               @RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "5")Integer pageSize)
    {

        Sort sort=Sort.by(Sort.Order.asc("id"));
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        return  productRepository.findByTitleContaining(findBy,pageable);
    }






}
