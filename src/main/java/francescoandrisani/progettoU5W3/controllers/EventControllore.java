package francescoandrisani.progettoU5W3.controllers;

import francescoandrisani.progettoU5W3.entities.Event;
import francescoandrisani.progettoU5W3.payloads.EventRequestDTO;
import francescoandrisani.progettoU5W3.payloads.EventResponseDTO;
import francescoandrisani.progettoU5W3.payloads.ReservationResponseDTO;
import francescoandrisani.progettoU5W3.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/events")
public class EventControllore {

    @Autowired
    private EventService eventService;

    @PostMapping
    @PreAuthorize("hasRole('ORGANIZER')")
    public EventResponseDTO createEvent(@RequestBody EventRequestDTO body) {
        Event createdEvent = this.eventService.save(body);
        return new EventResponseDTO(createdEvent.getId(), createdEvent.getTitle(), createdEvent.getDescription(), createdEvent.getDateTime(), createdEvent.getLocation(), createdEvent.getAvailableSeats());
    }

    @GetMapping
    public Page<EventResponseDTO> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sortBy) {
        Page<Event> events = this.eventService.getEvents(page, size, sortBy);
        return events.map(event -> new EventResponseDTO(event.getId(), event.getTitle(), event.getDescription(), event.getDateTime(), event.getLocation(), event.getAvailableSeats()));
    }

    @GetMapping("/{eventId}")
    public EventResponseDTO findEventById(@PathVariable Long eventId) {
        Event event = this.eventService.findById(eventId);
        return new EventResponseDTO(event.getId(), event.getTitle(), event.getDescription(), event.getDateTime(), event.getLocation(), event.getAvailableSeats());
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('ORGANIZER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long eventId) {
        this.eventService.findByIdAndDelete(eventId);
    }

}
