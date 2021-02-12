package com.cybersecu.secu.controllers;

import com.cybersecu.secu.models.Task;
import com.cybersecu.secu.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String home(ModelMap modelMap){
        modelMap.put("mode", "MODE_HOME");
        return "index";
    }

    @GetMapping("/all-tasks")
    public String allTasks(ModelMap modelMap){
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "index";
    }

    @GetMapping("/new-task")
    public String newTask(ModelMap modelMap){
        modelMap.put("mode", "MODE_NEW");
        return "index";
    }

    @RequestMapping(value ="/create-task/", method = RequestMethod.POST)
    public String create(Task task, ModelMap modelMap) {
        task.setDateCreated(new Date());
        this.taskRepository.saveAndFlush(task);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "redirect:/all-tasks";
    }

    @RequestMapping(value = "/save-task/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, Task task, ModelMap modelMap){
        Task existingTask = taskRepository.getOne(id);
        task.setDateCreated(new Date());
        BeanUtils.copyProperties(task, existingTask, "id");
        this.taskRepository.saveAndFlush(existingTask);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "index";
    }

    @GetMapping("/edit-task/{id}/{description}")
    public String editTask(@PathVariable("id") int id,
                           @PathVariable("description") String description,Task task, ModelMap modelMap){
        //les trois fonctions que j'ai essayé pour trouver une faille sql : getOne, findOneInsecure, findOneIsecureAB
        //modelMap.put("task", taskRepository.getOne(id));
        //modelMap.put("task", taskRepository.findOneInsecure(id));

        modelMap.put("task",this.findOneInsecureAB(id, description));
        modelMap.put("mode", "MODE_UPDATE");
        return "index";
    }
//@GetMapping("/edit-task?{id}&{description}")
    @RequestMapping(value="/delete-task/{id}")
    public String deleteTask(@PathVariable("id") int id, ModelMap modelMap){
        taskRepository.deleteById(id);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "index";
    }



    //juste pour trouver la faille injection sql mais ça ne marche pas avec cette fonction
    //Elle renvoi toute les attaques en text
    public Task findOneInsecureAB(int id, String description){
        //String sql = "SELECT * FROM t_tasks WHERE"+"( t_tasks.description='"+ description+"')";
        ///String sql = "SELECT * FROM t_tasks WHERE id= '"+ id +"' AND "+"description='"+description+"'";
        //String sql = "SELECT * FROM t_tasks WHERE"+" ( t_tasks.id="+id +" t_tasks.description='"+ description+"')";
        String sql = "SELECT * FROM t_tasks WHERE "+"( t_tasks.id="+id +" AND t_tasks.description='"+ description+"')";


        jdbcTemplate = new JdbcTemplate(jdbcTemplate.getDataSource());
        Task task = (Task) jdbcTemplate.queryForObject(
                sql, new BeanPropertyRowMapper<Task>(Task.class));
        return task;
    }



}
