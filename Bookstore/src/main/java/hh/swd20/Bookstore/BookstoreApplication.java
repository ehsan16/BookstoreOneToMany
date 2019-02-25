package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Department;
import hh.swd20.Bookstore.domain.DepartmentRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, DepartmentRepository drepository) {
		return (args) -> {
			log.info("save a couple of books");
			drepository.save(new Department("IT"));
			drepository.save(new Department("Business"));
			drepository.save(new Department("Law"));
			drepository.save(new Department("Lifestory"));
			
			brepository.save(new Book("Ehsan Haidari", "Ehsanin kirja", "123456-789", "1979", drepository.findByName("Lifestory").get(0)));
			brepository.save(new Book("Matti Meikäläinen", "Matin kirja", "123456-789", "1961", drepository.findByName("Law").get(0)));	
			
			log.info("fetch all students");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
