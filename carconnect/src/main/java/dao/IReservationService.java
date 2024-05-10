package dao;



import entity.Reservation;
import java.util.List;

public interface IReservationService {
    Reservation getReservationById(int reservationId);
    void getReservationsByCustomerId(int customerId);
    void createReservation(Reservation reservationData);
    void updateReservation(Reservation reservationData);
    void cancelReservation(int reservationId);
}
