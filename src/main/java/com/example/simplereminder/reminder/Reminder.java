// src/main/java/com/example/simplereminder/reminder/Reminder.java
package com.example.simplereminder.reminder;

import jakarta.persistence.*;

@Entity
@Table(name = "REMINDER")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean done = false;

    // === getter / setter ===
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
