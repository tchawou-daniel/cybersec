package com.cybersecu.secu.repositories;

import com.cybersecu.secu.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
   /* @Query(value = "SELECT id,name,description, date_created ,finished FROM t_tasks WHERE id=?1")
    Task findOneInsecure(@Param("id") int id);*/
}

