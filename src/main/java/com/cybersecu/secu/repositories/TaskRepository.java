package com.cybersecu.secu.repositories;

import com.cybersecu.secu.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Map;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value="SELECT * FROM WHERE id=?1", nativeQuery = true)
    Task findOneInsecure(@Param("id") int id);


}
