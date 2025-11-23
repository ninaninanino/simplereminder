// 우리가 실제로 써야 하는 리마인더 컨트롤러 (다시 한 번 정리)
// src/main/java/com/example/simplereminder/reminder/ReminderController.java
package com.example.simplereminder.reminder;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@CrossOrigin(origins = "http://localhost:5173")
public class ReminderController {

    private final ReminderRepository repo;

    public ReminderController(ReminderRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Reminder> list() {
        return repo.findAll();
    }

    @PostMapping
    public Reminder create(@RequestBody CreateReminderRequest req) {
        Reminder r = new Reminder();
        r.setText(req.getText());
        r.setDone(false);
        return repo.save(r);
    }

    @PatchMapping("/{id}")
    public Reminder updateDone(@PathVariable Long id, @RequestBody UpdateDoneRequest req) {
        Reminder r = repo.findById(id).orElseThrow();
        r.setDone(req.isDone());
        return repo.save(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    public static class CreateReminderRequest {
        private String text;
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }

    public static class UpdateDoneRequest {
        private boolean done;
        public boolean isDone() {return done; }
        public void setDone(boolean done) { this.done = done; }
    }
}
