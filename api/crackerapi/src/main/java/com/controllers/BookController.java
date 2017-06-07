package com.controllers;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.HelperUtility;
import com.Response;
import com.google.gson.Gson;
import com.models.Book;
import com.services.BookService;

/**
 * @author RITESH SINGH
 *
 */
@Controller("bookController")
@ComponentScan("com.services")
@RequestMapping(value = "book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	@Autowired
	@Qualifier("bookService")
	private BookService bookService;
	
	/**
	 * 
	 * @param book
	 * @param accessToken
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> saveBook(
			@RequestBody Book book,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {

		return new ResponseEntity<Response>(
				new Response(200, "Book saved successfully.",bookService.save(book)),
				HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> updateBook(
			@RequestBody Book book,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {

		return new ResponseEntity<Response>(
				new Response(200, "Book updates successfully.",bookService.update(book)),
				HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param bookId
	 * @param accessToken
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBookById(
			@PathVariable("bookId") String bookId,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		return new ResponseEntity<Response>(
				new Response(200, "Fetched Book successfully.",bookService.getBookById(bookId)),
				HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param query
	 * @param accessToken
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getAllBooks(
			@RequestParam(value = "q", required = false) String query,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		List<Book> books = bookService.getBooks();
		Map<String,Object> additionalData = new HashMap<String, Object>();
		HelperUtility.setTotalElements(books, additionalData);
		
		return new ResponseEntity<Response>(
				new Response(200, "Fetched all books successfully.",books,additionalData),
				HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param query
	 * @param page
	 * @param size
	 * @param sort
	 * @param accessToken
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	// http://localhost:8989/crackerapi/book/?page=1&size=4&sort=bookName,DESC
	@CrossOrigin
	@RequestMapping(value = "/all", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBooks(
			@RequestParam(value = "q", required = false) String query,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "size", required = true) int size,
			@RequestParam(value = "sort", required = true) String sort,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		Page<Book> books = bookService.getBooks(HelperUtility.getPageable(page, size, sort));
		
		return new ResponseEntity<Response>(
				new Response(200, "Fetched books successfully of given page.",books.getContent(),HelperUtility.getPageableResponse(books)),
				HttpStatus.OK);
	}
}