//package com.sprint;
//
//
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//import org.junit.Test;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//
//import static org.mockito.Mockito.*;
//
//import com.sprint.controllers.CustomerController;
//
//import com.sprint.exceptions.BookingNotFoundException;
//
//import com.sprint.models.Admin;
//import com.sprint.models.Booking;
//import com.sprint.models.Customer;
//import com.sprint.repository.AdminRepository;
//import com.sprint.repository.BookingRepository;
//import com.sprint.repository.CustomerRepository;
//import com.sprint.service.BookingImpl;
//import com.sprint.service.BookingService;
//import com.sprint.service.CustomerImpl;
//
//
//
//public class BookingImplTests {
//	@InjectMocks
//CustomerImpl customerService;
//	@Mock
//	CustomerRepository customerRepository;
//	@Mock
//	BookingImpl bookingService;
//	@Mock
//    private AdminRepository adminRepository;
//
//    
//
//	@Mock
//	private BookingRepository bookingRepository;
//	@Mock
//	BookingService bookService;
//	private Long id;
//	private Booking booking;
//	@Mock
//	private Customer Customer;
//	@Mock
//    private Admin admin;
//    
//    @Mock
//    private Customer customer;
//    
//    @Mock
//    private BookingService bookingService1;
//    
//    @InjectMocks
//    private CustomerController customerController;
//	
//    @Test 
//	public void testUpdateBooking() throws BookingNotFoundException {
//		// Create a mock booking object
//		Booking booking = Mockito.mock(Booking.class);
//		
//		// Set up the booking repository mock
//		BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
//		Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
//		
//		// Create a new date
//		LocalDate newDate = LocalDate.of(2023, 3, 1);
//		
//		// Call the updateBooking method
//		BookingImpl bookingService = new BookingImpl(bookingRepository);
//		bookingService.updateBooking(1L, newDate ,newTime);
//		
//		// Verify that the booking date was updated and saved
//		Mockito.verify(booking).setDate(newDate);
//		Mockito.verify(bookingRepository).save(booking);
//		}
//	@Test
//	  public void cancelBookingTest() throws BookingNotFoundException {
//	    // Create a mock object of the BookingRepository
//	    BookingRepository repository = mock(BookingRepository.class);
//
//	    // Define the behavior of the mock repository when findById method is called
//	    Booking booking = new Booking();
//	    when(repository.findById(anyLong())).thenReturn(Optional.of(booking));
//
//	    // Call the cancelBooking method of the BookingService class and pass the mock repository object
//	    BookingImpl service = new BookingImpl(repository);
//	    service.cancelBooking(1L);
//
//	    // Verify if the delete method was called on the mock repository
//	    verify(repository, times(1)).delete(booking);
//	  }
//
//	  @Test(expected = BookingNotFoundException.class)
//	  public void cancelBookingNotFoundTest() throws BookingNotFoundException {
//	    // Create a mock object of the BookingRepository
//	    BookingRepository repository = mock(BookingRepository.class);
//
//	    // Define the behavior of the mock repository when findById method is called
//	    when(repository.findById(anyLong())).thenReturn(Optional.empty());
//
//	    // Call the cancelBooking method of the BookingService class and pass the mock repository object
//	    BookingImpl service = new BookingImpl(repository);
//	    service.cancelBooking(1L);
//	  }
//	  @Test
//		public void findBookingByDateTest() throws BookingNotFoundException {
//			Admin admin = new Admin();
//			admin.setAdminId(1);
//			Customer customer = new Customer();
//			customer.setCustomerId(1L);
//			
//			Booking booking = new Booking();
//			booking.setId(1L);
//			booking.setDate(LocalDate.now());
//			//booking.setTime(LocalTime.now());
//			booking.setTableNumber(1);
//			booking.setAdmin(admin);
//			booking.setCustomer(customer);
//			booking.setNumberOfGuests(4);
//			
//			List<Booking> existingBooking = new ArrayList<>();
//			existingBooking.add(booking);
//			
//			when(bookingRepository.findByDate(any(LocalDate.class))).thenReturn(existingBooking);
//			
//			List<Booking> found = bookingService.findBookingByDate(LocalDate.now());
//			
//			assertEquals(1, found.size());
//			assertEquals(1L, found.get(0).getId());
//			assertEquals(LocalDate.now(), found.get(0).getDate());
//			//assertEquals(LocalTime.now(), found.get(0).getTime());
//			assertEquals(1, found.get(0).getTableNumber());
//			assertEquals(4, found.get(0).getNumberOfGuests());
//		}
//	  
//
//
//
//
//
//	}
//	
	   
////	      @Test
////	      public void testFindByDateTimeAndNumberOfGuestsAndTableNumber() {
////	          LocalDate date = LocalDate.now();
////	          String time = "18:00:00";
////	          int numberOfGuests = 4;
////	          int tableNumber = 2;
////
////	          Booking booking = new Booking();
////	          booking.setDate(date);
////	          booking.setTime(time);
////	          booking.setNumberOfGuests(numberOfGuests);
////	          booking.setTableNumber(tableNumber);
////
////	          List<Booking> bookings = new ArrayList<>();
////	          bookings.add(booking);
////
////	          when(bookingRepository.findByDateTimeAndNumberOfGuestsAndTableNumber(date, time, numberOfGuests, tableNumber))
////	              .thenReturn(bookings);
////
////	          List<Booking> result = bookingRepository.findByDateTimeAndNumberOfGuestsAndTableNumber(date, time, numberOfGuests, tableNumber);
////
////	          assertEquals(1, result.size());
////	          assertEquals(booking, result.get(0));
////	      }
////	     
//	  
//
//
////	  @Test
////	    public void bookTable_shouldBookTable_whenTableIsNotAlreadyBooked() throws BookingAlreadyExistsException {
////	        // Arrange
////	        long customerId = 1L;
////	        BookingDTO bookingDTO = new BookingDTO();
////	        bookingDTO.setId(1L);
////	        bookingDTO.setDate(LocalDate.now());
////	        bookingDTO.setTableNumber(1);
////	        bookingDTO.setNumberOfGuests(4);
////
////	        List<Booking> existingBookings = Collections.emptyList();
////
////	        Admin admin = new Admin();
////	        admin.setAdminId(1L);
////
////	        Customer customer = new Customer();
////	        customer.setCustomerId(customerId);
////
////	        Booking expectedBooking = new Booking();
////	        expectedBooking.setId(1L);
////	        expectedBooking.setDate(LocalDate.now());
////	        expectedBooking.setTableNumber(1);
////	        expectedBooking.setTime(LocalTime.now());
////	        expectedBooking.setAdmin(admin);
////	        expectedBooking.setCustomer(customer);
////	        expectedBooking.setNumberOfGuests(4);
////
////	        // Mock dependencies
////	        when(bookingRepository.findByDateTimeAndNumberOfGuestsAndTableNumber(
////	                bookingDTO.getDate(),
////	                bookingDTO.getNumberOfGuests(),
////	                bookingDTO.getTableNumber()
////	        )).thenReturn(existingBookings);
////
////	        when(adminRepository.getOne(1L)).thenReturn(admin);
////	        when(customerRepository.getOne(customerId)).thenReturn(customer);
////
////	        when(bookingRepository.save(any(Booking.class))).thenReturn(expectedBooking);
////
////	        // Act
////	        BookingDTO actualBookingDTO = bookingService.bookTable(customerId, bookingDTO);
////
////	        // Assert
////	        assertEquals(expectedBooking.getId(), actualBookingDTO.getId());
////	        assertEquals(expectedBooking.getDate(), actualBookingDTO.getDate());
////	        assertEquals(expectedBooking.getTableNumber(), actualBookingDTO.getTableNumber());
////	        assertEquals(expectedBooking.getNumberOfGuests(), actualBookingDTO.getNumberOfGuests());
////	    }


    

