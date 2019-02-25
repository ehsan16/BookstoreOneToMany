package hh.swd20.Bookstore.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.DepartmentRepository;



@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private DepartmentRepository drepository; 
	
	@RequestMapping(value="/booklist")
	public String newBook(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("departments", drepository.findAll());
        return "addbook";
    } 
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:booklist";
    } 
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.deleteById(bookId);
        return "redirect:../booklist";
    } 
    
    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, @PathVariable("id") Long departmentId, Model model) {
    	model.addAttribute("book", brepository.findById(bookId));
    	model.addAttribute("departments", drepository.findAll());
        return "editbook";
	
}
}

