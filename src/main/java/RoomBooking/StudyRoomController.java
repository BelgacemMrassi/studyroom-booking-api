package RoomBooking;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class StudyRoomController {

    private final StudyRoomRepository repository;

    public StudyRoomController(StudyRoomRepository repository) {
        this.repository = repository;
    }

    // GET /api/rooms – alle Räume
    @GetMapping
    public List<StudyRoom> getAllRooms() {
        return repository.findAll();
    }

    // GET /api/rooms/available – nur freie Räume
    @GetMapping("/available")
    public List<StudyRoom> getAvailableRooms() {
        return repository.findByIsBookedFalse();
    }

    // POST /api/rooms – neuen Raum anlegen (immer ungebucht)
    @PostMapping
    public ResponseEntity<StudyRoom> createRoom(@RequestBody StudyRoom room) {
        room.setId(null);       // ID wird von der DB vergeben
        room.setBooked(false);  // Lombok erzeugt für "isBooked" den Setter setBooked()
        StudyRoom saved = repository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/rooms/{id}/book – Raum buchen
    @PutMapping("/{id}/book")
    public ResponseEntity<StudyRoom> bookRoom(@PathVariable Long id) {
        return repository.findById(id)
                .map(room -> {
                    room.setBooked(true);
                    return ResponseEntity.ok(repository.save(room));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
