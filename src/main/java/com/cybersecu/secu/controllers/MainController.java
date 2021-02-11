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
        //les regex pour garder l'intégrité et la cohérence des données
        task.setDescription(task.getDescription().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b|(\\b(from)\\b)|(\\b(FROM)\\b)|\\*|\\'|(\\b(and)\\b)| (\\b(AND)\\b)|\\=|(\\b(where)\\b)|(\\b(WHERE)\\b)|(\\b(drop)\\b)|(\\b(DROP)\\b)|(\\b(1=1)\\b)|\\=|\\;|\\`|\\-|\\1|\\=|\\--|(\\b(or)\\b))", ""));
        task.setName(task.getName().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b|(\\b(from)\\b)|(\\b(FROM)\\b)|\\*|\\'|(\\b(and)\\b)| (\\b(AND)\\b)|\\=|(\\b(where)\\b)|(\\b(WHERE)\\b|(\\b(drop)\\b))|(\\b(DROP)\\b)|(\\b(1=1)\\b)|\\=|\\;|(\\b(1=1)\\b)|\\=|\\;|\\`|\\-|\\1|\\=|\\--|(\\b(or)\\b))", ""));
        this.taskRepository.saveAndFlush(task);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "redirect:/all-tasks";
    }

    @RequestMapping(value = "/save-task/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, Task task, ModelMap modelMap){
        Task existingTask = taskRepository.getOne(id);
        task.setDateCreated(new Date());
        //les regex pour garder l'intégrité et la cohérence des données
        task.setDescription(task.getDescription().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b|(\\b(from)\\b)|(\\b(FROM)\\b)|\\*|\\'|(\\b(and)\\b)| (\\b(AND)\\b)|\\=|(\\b(where)\\b)|(\\b(WHERE)\\b)|(\\b(drop)\\b)|(\\b(DROP)\\b)|(\\b(1=1)\\b)|\\=|\\;|(\\b(1=1)\\b)|\\=|\\;|\\`|\\-|\\1|\\=|\\--|(\\b(or)\\b))", ""));
        task.setName(task.getName().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b|(\\b(from)\\b)|(\\b(FROM)\\b)|\\*|\\'|(\\b(and)\\b)| (\\b(AND)\\b)|\\=|(\\b(where)\\b)|(\\b(WHERE)\\b)|(\\b(drop)\\b)|(\\b(DROP)\\b)|(\\b(1=1)\\b)|\\=|\\;|(\\b(1=1)\\b)|\\=|\\;|\\`|\\-|\\1|\\=|\\--|(\\b(or)\\b))", ""));
        BeanUtils.copyProperties(task, existingTask, "id");
        this.taskRepository.saveAndFlush(existingTask);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "index";
    }

	@GetMapping("/edit-task/{id}")
	public String editTask(@PathVariable("id") int id, ModelMap modelMap){
        //get One fonction sérurisé renvoi tous les attaques sous forme de chaine de caractére
        modelMap.put("task", taskRepository.getOne(id));
        modelMap.put("mode", "MODE_UPDATE");
		return "index";
	}

    @RequestMapping(value="/delete-task/{id}")
    public String deleteTask(@PathVariable("id") int id, ModelMap modelMap){
        taskRepository.deleteById(id);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "index";
    }





}
