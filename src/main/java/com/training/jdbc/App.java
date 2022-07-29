package com.training.jdbc;

import java.util.List;

public class App {

	public static void main(String[] args) {
		//Added an extra line of code/comment - best breakfast is parathas
		JdbcProductDAO dao = new JdbcProductDAO();
		
		Product testData = new Product("stuffed paratha", 1234f, 100);
		
		Product saved = dao.save(testData);
		
		System.out.println("Saved a product with id: "+saved.getId());
		
		System.out.println("_______________________________________");
		System.out.println("List of all products before deletion: ");
		List<Product> all = dao.findAll();
		all.forEach(System.out::println);

		dao.deleteById(saved.getId());
		
		System.out.println("_______________________________________");
		System.out.println("List of all products after deletion: ");
		all = dao.findAll();
		all.forEach(System.out::println);
		//added a comment at bottom by someone else
	}

}
