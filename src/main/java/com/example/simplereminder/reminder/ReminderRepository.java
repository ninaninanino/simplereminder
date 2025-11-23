// src/main/java/com/example/simplereminder/reminder/ReminderRepository.java
package com.example.simplereminder.reminder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
