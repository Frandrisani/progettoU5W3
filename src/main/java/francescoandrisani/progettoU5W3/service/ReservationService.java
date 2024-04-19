package francescoandrisani.progettoU5W3.service;

import francescoandrisani.progettoU5W3.entities.Event;
import francescoandrisani.progettoU5W3.entities.Reservation;
import francescoandrisani.progettoU5W3.entities.USerRole;
import francescoandrisani.progettoU5W3.entities.User;
import francescoandrisani.progettoU5W3.exceptions.FullException;
import francescoandrisani.progettoU5W3.exceptions.NotFound;
import francescoandrisani.progettoU5W3.exceptions.ReservationNotFound;
import francescoandrisani.progettoU5W3.exceptions.Unauthorized;
import francescoandrisani.progettoU5W3.payloads.ReservationRequestDTO;
import francescoandrisani.progettoU5W3.repository.EventDAO;
import francescoandrisani.progettoU5W3.repository.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private EventDAO eventDAO;

    public Page<Reservation> getReservations(int page, int size, String sortBy){
        if(size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.reservationDAO.findAll(pageable);
    }

    public Reservation save(ReservationRequestDTO body) {
        Event event = this.eventDAO.findById(body.eventId()).orElseThrow(() -> new NotFound(body.eventId()));
        if (event.getAvailableSeats() <= 0) {
            throw new FullException(body.eventId());
        }
        Reservation newReservation = new Reservation(body);
        newReservation = reservationDAO.save(newReservation);

        event.setAvailableSeats(event.getAvailableSeats() - 1);
        this.eventDAO.save(event);

        return newReservation;
    }

    public Reservation findById(Long reservationId){
        return this.reservationDAO.findById(reservationId).orElseThrow(() -> new ReservationNotFound(reservationId));
    }

    public Reservation findByIdAndUpdate(Long reservationId, User modifiedReservation){
        Reservation found = this.findById(reservationId);

        return this.reservationDAO.save(found);
    }

    public void findByIdAndDelete(Long reservationId){
        Reservation found = this.findById(reservationId);
        this.reservationDAO.delete(found);
    }
}
