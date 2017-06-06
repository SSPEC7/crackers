package com.controllers;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Response;
import com.exception.BookException;
import com.google.gson.Gson;
import com.models.Book;
import com.services.BookService;

/**
 * @author RITESH SINGH
 *
 */
@Controller("bookController")
@ComponentScan("com.services")
@RequestMapping("book")
public class BookController {

	@Autowired
	@Qualifier("bookService")
	private BookService bookService;
	
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Response> saveEmployee(ModelMap model, @RequestBody Book book,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String userAgent, HttpServletResponse response)
			throws UnknownHostException {

		bookService.save(book);
		return new ResponseEntity<Response>(
				new Response(200, "Book fetched successfully.",book),
				HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Response> getEmployeeById(ModelMap model,@PathVariable("bookId") String bookId,
			@RequestHeader(value = "token", defaultValue = "foo") String userAgent, HttpServletResponse response)
			throws UnknownHostException {
		
		return new ResponseEntity<Response>(
				new Response(200, "Book fetched successfully.",bookService.getBookById(bookId)),
				HttpStatus.OK);
	}
	
}
