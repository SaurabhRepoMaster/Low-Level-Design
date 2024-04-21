package org.bookMyShow.api;

import lombok.NonNull;
import org.bookMyShow.model.Booking;
import org.bookMyShow.model.BookingStatus;
import org.bookMyShow.service.BookingService;
import org.bookMyShow.service.PaymentsService;

public class PaymentsController {

    private final BookingService bookingService;
    private final PaymentsService paymentsService;

    public PaymentsController(BookingService bookingService, PaymentsService paymentsService) {
        this.bookingService = bookingService;
        this.paymentsService = paymentsService;
    }

    public void paymentSuccess(@NonNull final  String bookingId, @NonNull final String user) {
        final Booking booking = bookingService.getBooking(bookingId);
        bookingService.confirmBooking(booking,user);
    }

    public void paymentFailed(@NonNull final String bookingId, @NonNull final String user) {
        paymentsService.processPaymentFailed(bookingService.getBooking(bookingId), user);
    }

}
