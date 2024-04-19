package francescoandrisani.progettoU5W3.controllers;

import francescoandrisani.progettoU5W3.entities.Reservation;
import francescoandrisani.progettoU5W3.entities.User;
import francescoandrisani.progettoU5W3.exceptions.BadRequest;
import francescoandrisani.progettoU5W3.payloads.ReservationRequestDTO;
import francescoandrisani.progettoU5W3.payloads.ReservationResponseDTO;
import francescoandrisani.progettoU5W3.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public Page<Reservation> getAllReservations(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.reservationService.getReservations(page, size, sortBy);
    }

    @PostMapping
    public ReservationResponseDTO createReservation(@RequestBody ReservationRequestDTO body) {
        Reservation reservation = this.reservationService.save(body);
        return new ReservationResponseDTO(reservation.getId());
    }

    @GetMapping("/{reservationId}")
    public Reservation findReservationById(@PathVariable Long reservationId) {
        return this.reservationService.findById(reservationId);
    }

    @DeleteMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findReservationByIdAndDelete(@PathVariable Long reservationId) {
        this.reservationService.findByIdAndDelete(reservationId);
    }

}
