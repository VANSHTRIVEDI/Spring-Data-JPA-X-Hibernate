package com.vansh.jpaTutorial.jpaTuts;

import com.vansh.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.vansh.jpaTutorial.jpaTuts.respositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository()
	{
		ProductEntity entity=ProductEntity.builder().sku("nestle234").title("Nestle Chocolate").price(BigDecimal.valueOf(123.48)).quantity(12).build();
		productRepository.save(entity);
	}

	@Test
	void  testRepository2()
	{
		List<ProductEntity> entity=productRepository.findAll();
		System.out.println(entity);
	}

	@Test
	void  testRepository3()
	{
		List<ProductEntity> entityByTitle=productRepository.findByTitle("Sample Product A");
		System.out.println(entityByTitle);
	}

  @Test
	void  testRepository4()
	{
		List<ProductEntity> entityByTitle=productRepository.findByCreatedAtAfter(LocalDateTime.of(2025,1,1,0,0,0));
	}

	@Test
	void  testRepository5()
	{
		List<ProductEntity> entityByTitle=productRepository.findByQuantityAndPrice(4,BigDecimal.valueOf(23.45));
	}

	@Test
	void  testRepository6()
	{
		List<ProductEntity> entityByTitle=productRepository.findByQuantityGreaterThanAndPriceLessThan(4,BigDecimal.valueOf(23.45));
	}

	@Test
	void  testRepository7()
	{
		List<ProductEntity> entityByTitle=productRepository.findByQuantityGreaterThanOrPriceLessThan(4,BigDecimal.valueOf(23.45));
	}

	@Test
	void  testRepository8()
	{
		//using Like without wildcard will check exact string
		List<ProductEntity> entityByTitle=productRepository.findByTitleLike("nestle");
	}


	@Test
	void  testRepository9()
	{
		//using Like with wildcard will check for  every character of string for example if title is vansl , here l macthes it
		List<ProductEntity> entityByTitle=productRepository.findByTitleLike("%nestle%");
	}
	@Test
	void  testRepository10()
	{
		//Containing is sam as like with wildcards (hubernate puts wildcard for us)
		List<ProductEntity> entityByTitle=productRepository.findByTitleContaining("nestle");
	}
	@Test
	void  testRepository11()
	{
		//Containing is sam as like with wildcards (hubernate puts wildcard for us)
		List<ProductEntity> entityByTitle=productRepository.findByTitleContaining("nestle");
	}
	@Test
	void  testRepository12()
	{
		//Containing is sam as like with wildcards (hubernate puts wildcard for us)
		List<ProductEntity> entityByTitle=productRepository.findByTitleIgnoreCase("nestle");
	}

	@Test
	void  testRepository13()
	{
		Optional<ProductEntity> entityByTitleAndName=productRepository.findByTitleAndPrice("nestle",BigDecimal.valueOf(29.9));
	}

	@Test
	void  testRepository14()
	{

		//This will be null because every test runs individually so it happen that this testRepository14 will run before testRepository(where nestle chocolate is created)
		Optional<ProductEntity> entityByTitleAndName=productRepository.findByTitleAndPriceAndQuantity("Wireless Mouse",BigDecimal.valueOf(19.99),100);
		System.out.println(entityByTitleAndName);
	}








}
