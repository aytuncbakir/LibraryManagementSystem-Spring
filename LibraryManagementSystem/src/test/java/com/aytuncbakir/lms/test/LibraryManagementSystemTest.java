package com.aytuncbakir.lms.test;



import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aytuncbakir.lms.config.SpringRootConfig;
import com.aytuncbakir.lms.dao.BookDAO;
import com.aytuncbakir.lms.dao.UserDAO;
import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.domain.Member;
import com.aytuncbakir.lms.domain.Novel;
import com.aytuncbakir.lms.domain.User;
import com.aytuncbakir.lms.service.UserService;

public class LibraryManagementSystemTest {
	
	@Test
	public void testInsertToBook() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		
		DataSource datasource =  context.getBean( DataSource.class);
		
		JdbcTemplate jt = new JdbcTemplate(datasource);
		
		String sql = "INSERT INTO book(name, author, isbn, type, total, availableCount) VALUES(?,?,?,?,?,?)";
		
		Object[] params = new Object[] {"Kasagi","Omer Seyfettin","2345","Novel","2","2"};
		
		jt.update(sql,params );
		System.out.println("Book inserted");
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testInsertToUser() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		DataSource dataSource = context.getBean( DataSource.class);
		
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		
		String sql = "INSERT INTO user(name,surname,username,password,phone,address,email) VALUES(?,?,?,?,?,?,?)";
		
		Object[] args = new Object[] {"Adem","Badem","abadem","12345","565656565","Lahti","abadem@gmail.com"};
				
		jt.update(sql, args);
				
		System.out.println("User inserted");		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testSaveUser() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		UserDAO userDAO  = context.getBean(UserDAO.class);
		User user = new Member() ;
		user.setName("aaa");
		user.setSurname("asas");
		user.setEmail("aaaaaa");
		user.setPassword("1234");
		user.setPhone("4545454");
		user.setAddress("asdasdsad");
		user.setType(2);
		user.setUsername("ahan");
		user.setStatus(1);
		userDAO.save(user);
		
						
		System.out.println("User saved");		
		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testUpdateUser() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		UserDAO userDAO  = context.getBean(UserDAO.class);
		User user = new Member() ;
		user.setName("Hasan");
		user.setSurname("Kara");
		user.setEmail("hasan@gmail");
		user.setPassword("12345678");
		user.setPhone("45454547878");
		user.setAddress("Konya");
		user.setType(2);
		user.setUsername("hasan");
		user.setUserId(2);
		user.setStatus(1);
		userDAO.update(user);
		
						
		System.out.println("User updated");		
		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testDeleteUser() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		UserDAO userDAO  = context.getBean(UserDAO.class);
		User user = new Member() ;
		user.setUserId(3);
		userDAO.delete(user);
		
						
		System.out.println("User deleted");		
		
		((ConfigurableApplicationContext)context).close();
	}

	@Test
	public void testFindByIdUser() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		UserDAO userDAO  = context.getBean(UserDAO.class);
		
		User user = userDAO.findById(2);
		
						
		System.out.println("User selected");	
		
		System.err.println(user);
		
		((ConfigurableApplicationContext)context).close();
	}
		

	@Test
	public void testFindAllUser() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		UserDAO userDAO  = context.getBean(UserDAO.class);
		
		List<User> users = userDAO.findAll();
		
		
						
		System.out.println("User selected");	
		for(User user: users)
			System.err.println(user);
		
		((ConfigurableApplicationContext)context).close();
	}
		

	@Test
	public void testFindByPropUser() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		UserDAO userDAO  = context.getBean(UserDAO.class);
		
		List<User> users = userDAO.findByProperty("name","Aytunc");
		
		
						
		System.out.println("User selected");	
		for(User user: users)
			System.err.println(user);
		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testSaveBook() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		BookDAO bookDao = context.getBean(BookDAO.class);
		Book book = new Novel();
		
		book.setAuthor("Necip Fazil Kisakurek");
		book.setAvailableCount(3);
		book.setIsbn("323212121");
		book.setName("Reis Bey");
		book.setTotal(3);
		book.setType("novel");
		
		bookDao.save(book);
		
						
		System.out.println("Book saved");		
		
		((ConfigurableApplicationContext)context).close();
	}
		
	@Test
	public void testUpdateBook() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		BookDAO bookDao = context.getBean(BookDAO.class);
		Book book = new Novel();
		
		book.setAuthor("N.F.K");
		book.setAvailableCount(2);
		book.setIsbn("323212121");
		book.setName("Reis Bey");
		book.setTotal(2);
		book.setType("novel");
		book.setBookId(4);
		
		bookDao.update(book);
		
						
		System.out.println("Book updated");		
		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testDeleteBook() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		BookDAO bookDao = context.getBean(BookDAO.class);
		Book book = new Novel();
		book.setBookId(4);
		bookDao.delete(book);
		
						
		System.out.println("Book deleted");		
		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testFindByIdBook() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		BookDAO bookDao = context.getBean(BookDAO.class);
		Book book = bookDao.findById(3);
		
		
		System.out.println(book.getName());
						
		System.out.println("Book found");		
		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testFindAllBook() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		BookDAO bookDao = context.getBean(BookDAO.class);
		List<Book> books = bookDao.findAll();
		
		for (Book book : books) {
			System.out.println(book.getName());
		}
		
						
		System.out.println("All Books found");		
		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testFindByPropertBook() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		BookDAO bookDao = context.getBean(BookDAO.class);
		List<Book> books  = bookDao.findByProperty("total", 2);
		
		
		
		for (Book book : books) {
			System.out.println(book.getName());
		}
						
		System.out.println("Book found by property");		
		
		((ConfigurableApplicationContext)context).close();
	}
	
	@Test
	public void testUserServiceRegister() {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		
		UserService userService  = context.getBean(UserService.class);
		User user = new Member() ;
		user.setName("Meke");
		user.setSurname("Teke");
		user.setEmail("meketeke@gmail.com");
		user.setPassword("1234");
		user.setPhone("4545454");
		user.setAddress("Cukur");
		user.setType(2);
		user.setUsername("mteke");
		user.setStatus(1);
		
		userService.register(user);
		
		System.out.println("User registered");		
		
		((ConfigurableApplicationContext)context).close();
	}

}
