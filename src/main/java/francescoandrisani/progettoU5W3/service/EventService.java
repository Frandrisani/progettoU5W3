package francescoandrisani.progettoU5W3.service;

import francescoandrisani.progettoU5W3.entities.Event;
import francescoandrisani.progettoU5W3.entities.USerRole;
import francescoandrisani.progettoU5W3.entities.User;
import francescoandrisani.progettoU5W3.exceptions.EventNotFound;
import francescoandrisani.progettoU5W3.exceptions.Unauthorized;
import francescoandrisani.progettoU5W3.payloads.EventRequestDTO;
import francescoandrisani.progettoU5W3.repository.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    public Page<Event> getEvents(int page, int size, String sortBy){
        if(size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventDAO.findAll(pageable);
    }

    public Event save(EventRequestDTO body) {
        Event newEvent = new Event(body.title(), body.description(), body.dateTime(), body.location(), body.posti());
        return eventDAO.save(newEvent);
    }

    public Event findById(Long eventId) {
        return this.eventDAO.findById(eventId).orElseThrow(() -> new EventNotFound(eventId));
    }

    public Event findByIdAndUpdate(Long eventId, Event modifiedEvent){
        Event found = this.findById(eventId);
        found.setTitle(modifiedEvent.getTitle());
        found.setDescription(modifiedEvent.getDescription());
        found.setDateTime(modifiedEvent.getDateTime());
        found.setLocation(modifiedEvent.getLocation());
        found.setAvailableSeats(modifiedEvent.getAvailableSeats());
        return this.eventDAO.save(found);
    }

    public void findByIdAndDelete(Long eventId){
        Event found = this.findById(eventId);
        this.eventDAO.delete(found);
    }


}
