package com.sprint.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.exceptions.TransactionRecordNotFoundException;
import com.sprint.dto.BookingDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.repository.BookingRepository;

@Service
public class BookingImpl implements BookingService
{
	private BookingRepository bookingRepository;
	@Autowired
	public BookingImpl(BookingRepository bookingRepository){
		this.bookingRepository = bookingRepository;
	}
	
	private Admin admin=new Admin();
	private Customer customer=new Customer();
	
	@Override
	public BookingDTO bookTable(long custId,LocalTime time,BookingDTO booking)throws BookingAlreadyExistsException{
	      List<Booking> existingBooking = bookingRepository.findByDateTimeAndNumberOfGuestsAndTableNumber(booking.getDate(),time, booking.getNumberOfGuests(), booking.getTableNumber());
	      Booking book;
	      if (!existingBooking.isEmpty()) {
	         throw new  BookingAlreadyExistsException("This table is already booked for the selected date and time");
	      }
	 else
	  		{
	  		admin.setAdminId(1);
	  		customer.setCustomerId(custId);
	  		book = new Booking();
	  		book.setId(booking.getId());
	  		book.setDate(booking.getDate());
	  		book.setTime(time);
	  		book.setTableNumber(booking.getTableNumber());
	  		//book.setTime(LocalTime.now());
	  		book.setAdmin(admin);
	  		book.setCustomer(customer);
	  		book.setNumberOfGuests(booking.getNumberOfGuests());
	  		
	  		}
	  		Booking b=bookingRepository.save(book);
	  		booking.setId(book.getId());
	  		return booking;
	  	}

	
	public List<Booking> findBookingByDate(LocalDate date)throws TransactionRecordNotFoundException{
		List<Booking>  existBooking = bookingRepository.findByDate(date);
		if(existBooking.isEmpty()) {
			throw new TransactionRecordNotFoundException("Booking records are not found for given date ");
		}
		else {
			return bookingRepository.findByDate(date);
		}
}
	
	@Override
	public Booking updateBooking(long bookingId, LocalDate newDate, LocalTime newTime) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
        booking.setDate(newDate);
        booking.setTime(newTime);
        return bookingRepository.save(booking);
    }


	@Override
	public void cancelBooking(Long bookingId) throws BookingNotFoundException {
		Booking booking = bookingRepository.findById(bookingId)
			      .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
			    bookingRepository.delete(booking);
		
	}

	 public List<String> displayTableBookings(int tableNumber, LocalDate date) {
	        List<Booking> bookings = bookingRepository.findByTableNumberAndDate(tableNumber, date);
	        List<String> bookingDetails = new ArrayList<>();
	        for (Booking booking : bookings) {
	            String bookingDetail = "Customer ID: " + booking.getCustomer().getCustomerId()
	                                 + " Date: " + booking.getDate()
	                                 + " Time: " + booking.getTime()
	                                 + " Number of guests: " + booking.getNumberOfGuests()
	                                 + " Table number: " + booking.getTableNumber();
	            bookingDetails.add(bookingDetail);
	        }
	        return bookingDetails;
	}
	
	}


