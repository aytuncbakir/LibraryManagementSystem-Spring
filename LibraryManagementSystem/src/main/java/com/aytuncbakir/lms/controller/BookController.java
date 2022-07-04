package com.aytuncbakir.lms.controller;

import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.domain.Novel;
import com.aytuncbakir.lms.dto.BarrowingDTO;
import com.aytuncbakir.lms.service.BookService;
import com.aytuncbakir.lms.util.CalculateUtil;
import com.aytuncbakir.lms.util.StringUtil;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value="/librarian/book_form")
	public String contactForm (Model model) {
		
		Book book = new Book();
		model.addAttribute("command", book);
		
		return "book_form";  // JSP form view
		
	}
	
	@RequestMapping(value="/librarian/save_book")
	public String saveOrUpdateBook (@ModelAttribute("command") Book book, Model model, HttpSession session) {
		
		Integer bookId = (Integer) session.getAttribute("abookId");
		if(bookId == null) {
			// save
			try {
			Integer userId = (Integer) session.getAttribute("userId");
			bookService.save(book);	
			return "redirect:blist?act=sv";  // redirect user to book list url
			}catch(Exception e){
				model.addAttribute("err","Failed to save book");
				e.printStackTrace();
				return "book_form"; 
				
			}
		}else {
			// update
			try {
			book.setBookId(bookId);
			bookService.update(book);	
			session.setAttribute("abookId", null);
			return "redirect:blist?act=ed";  // redirect user to book list url
			}catch(Exception e){
				model.addAttribute("err","Failed to edit book");
				e.printStackTrace();
				return "book_form"; 
				
			}
		}
		
	}
	
	@RequestMapping(value="/librarian/edit_book")
	public String prepareEditBook (Model model, @RequestParam("book_id") Integer bookId, HttpSession session) {
		// session kim edit etti kullanilbilir session.setAttribete("abookId", bookId);
		session.setAttribute("abookId", bookId);
		Book book =	bookService.findById(bookId);
		model.addAttribute("command", book);
		return "book_form";  // JSP page
		
	}
	
	
	@RequestMapping(value="/user/ublist")
	public String memberBookList (Model model) {
		model.addAttribute("bookList",bookService.findAll());
		return "ublist";  // JSP page
		
	}
	

	@RequestMapping(value="/user/book_search")  
	public String memberBookSearch (Model model, HttpSession session, @RequestParam("freeText") String freeText) {
		model.addAttribute("bookList",bookService.findUserBook( freeText));
		return "ublist";  // JSP page
		
	}
	
	@RequestMapping(value="/librarian/blist")
	public String librarianBookList (Model model) {
		model.addAttribute("bookList",bookService.findAll());
		return "blist";  // JSP page
		
	}
	
	@RequestMapping(value="/librarian/check_restitution")
	public String librarianCheckRestitution (Model model) {
		
		model.addAttribute("barrowList",checkDate(bookService.findAllBarrowedBooks()));
		return "barrow_list";  // JSP page
		
	}
	
	@RequestMapping(value="/librarian/check_given_books")
	public String librarianCheckGivenBooks (Model model) {
		
		model.addAttribute("barrowList",bookService.findAllBarrowedBooks());
		return "barrow_list";  // JSP page
		
	}

	@RequestMapping(value="/librarian/book_search")  
	public String librarianBookSearch (Model model, HttpSession session, @RequestParam("freeText") String freeText) {
		model.addAttribute("bookList",bookService.findUserBook( freeText));
		return "blist";  // JSP page
		
	}
	
	@RequestMapping(value="/librarian/del_book")
	public String deleteBook (@RequestParam("book_id") Integer bookId) {
		bookService.delete(bookId);
		return "redirect:blist?act=del";  // JSP page
		
	}

	@RequestMapping(value="/user/barrow_book")
	public String barrowBook (@RequestParam("book_id") Integer bookId, HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		if (bookService.barrow(bookId, userId))
			return "redirect:ublist?act=barrowed";  // JSP page
		
		return "redirect:ublist?act=barrowFailed";
	}
	
	@RequestMapping(value="/user/give_book")
	public String giveBook (@RequestParam("book_id") Integer bookId, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		Date date = bookService.getTakenDay(bookId, userId);
		String start_date = date.toString();
		if (bookService.give(bookId, userId)) {
			
			
			long  days = StringUtil.findDifference(start_date);
			System.out.println(days);
			if(days > 30) {
				long sum = CalculateUtil.punishmentCalculateDays(days);
				return "redirect:ublist?act=given&act2=punishment&act3="+sum;  // JSP page
			}
			
			return "redirect:ublist?act=given";  // JSP page
			
		}
		
		return "redirect:ublist?act=givenFailed";
	}
	
	
	@RequestMapping(value="/librarian/bulk_book_delete")
	public String deleteBulkBook (@RequestParam("book_id") Integer[] books) {
		bookService.delete(books);
		
		return "redirect:blist?act=del";  // JSP page
		
	}
	
	private List<BarrowingDTO> checkDate( List<BarrowingDTO> barrowList){
		
		for(int i = 0 ; i<barrowList.size(); i++ ) {
			long  days = StringUtil.findDifference(barrowList.get(i).getBarrowDate().toString());
			if(days < 30) {
				barrowList.remove(i);
				i--;
			}else {
				barrowList.get(i).setDays(CalculateUtil.punishmentCalculateDays(days));
			}
				
		}
		
		return barrowList;
		
	}
	
	
	
}
