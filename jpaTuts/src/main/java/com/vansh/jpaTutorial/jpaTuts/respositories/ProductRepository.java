package com.vansh.jpaTutorial.jpaTuts.respositories;

import com.vansh.jpaTutorial.jpaTuts.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByTitle(String sampleProductA);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime of);

    List<ProductEntity> findByQuantityAndPrice(int i, BigDecimal bigDecimal);

    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan(int i, BigDecimal bigDecimal);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int i, BigDecimal bigDecimal);

    List<ProductEntity> findByTitleLike(String title);

    List<ProductEntity> findByTitleContaining(String title);


    //using pageable
    List<ProductEntity> findByTitleContaining(String title,Pageable pageable);

    List<ProductEntity> findByTitleIgnoreCase(String title);


    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);



    /*
       we can do this also
       1,2,3 will be parameter sequence
      @Query("select e.title from ProductEntity e where e.title=?1 and e.price=?2 and quantity=?3")
     */

    //this is also working
    @Query("select e.title from ProductEntity e where e.title=:title and e.price=:price and quantity=:quantity")
    Optional<ProductEntity> findByTitleAndPriceAndQuantity(String title,BigDecimal price,Integer quantity);




    //below both are function are for sorting but we can see that we havae to write Orderby again and again
    //intead we can do make a function which takes intance of Sort class as a parameter
    List<ProductEntity> findByTitleOrderByPrice(String s);

    List<ProductEntity> findByOrderByPrice();

    //it the help of this we can sort anything just we have send instance of Sort class
    List<ProductEntity> findBy(Sort sort);
}
