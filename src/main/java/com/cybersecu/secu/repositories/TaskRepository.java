package com.cybersecu.secu.repositories;

import com.cybersecu.secu.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
