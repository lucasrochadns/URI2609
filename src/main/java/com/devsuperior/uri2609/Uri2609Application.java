package com.devsuperior.uri2609;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.repositories.CategoryRepository;

import java.util.List;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public void run(String... args) throws Exception {
      categoryRepository.searchAllBySumCategories().stream().map(x -> String.format("%s %d", x.getName(), x.getSum()))
			  .forEach(System.out::println);

		List<CategorySumDTO> names = categoryRepository.searchAllBySumCategoriesTotal();
		List<CategorySumProjection> name = categoryRepository.searchAllBySumCategories();
		System.out.println("\n\n\n");
		System.out.println("JPQL");
		names.forEach(x -> System.out.println(x.getName()+" " + x.getSum()));
		System.out.println("\n\n\n");
		System.out.println("NATIVE QUERY");
		name.forEach(x -> System.out.println(x.getName()+" " + x.getSum()));
	}
}
