package com.cybersecu.secu.controllers;

import com.cybersecu.secu.models.Task;
import com.cybersecu.secu.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class MainController {

    @GetMapping("/home")
    //@ResponseBody
    public String home(@RequestParam(required = false, defaultValue = "D") String mode, ModelMap modelMap) {
        modelMap.put("mode", mode);
        //System.out.println("\n\n\n" + name + "\n\n\n");
        //System.out.println("\n\n\n" + request.getParameter("name") +"\n\n\n");
        return "index";
    }

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

    /*@PostMapping("/save-task/{id}")
    public String saveTask(@PathVariable("id") int id, @RequestBody Task task, BindingResult result, ModelMap modelMap){
        /*if (result.hasErrors()) {
            task.setId(id);
            return "index";
        }*/
     /*   task.setDateCreated(new Date());
        System.out.println(task);
        this.taskRepository.saveAndFlush(task);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "index";
    }
    */

    @RequestMapping(value ="/create-task/", method = RequestMethod.POST)
    public String create(Task task, ModelMap modelMap) {
        task.setDateCreated(new Date());
        task.setDescription(task.getDescription().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b|(\\b(from)\\b)|(\\b(FROM)\\b)|\\*|\\'|(\\b(and)\\b)| (\\b(AND)\\b)|\\=|(\\b(where)\\b)|(\\b(WHERE)\\b|(\\b(drop)\\b))|(\\b(DROP)\\b)|(\\b(1=1)\\b)|\\=|\\;)", ""));
        task.setName(task.getName().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b|(\\b(from)\\b)|(\\b(FROM)\\b)|\\*|\\'|(\\b(and)\\b)| (\\b(AND)\\b)|\\=|(\\b(where)\\b)|(\\b(WHERE)\\b|(\\b(drop)\\b))|(\\b(DROP)\\b)|(\\b(1=1)\\b)|\\=|\\;)", ""));

        this.taskRepository.saveAndFlush(task);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "redirect:/all-tasks";
    }

    @RequestMapping(value = "/save-task/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, Task task, ModelMap modelMap){
        Task existingTask = taskRepository.getOne(id);
        task.setDateCreated(new Date());
        task.setDescription(task.getDescription().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b|(\\b(from)\\b)|(\\b(FROM)\\b)|\\*|\\'|(\\b(and)\\b)| (\\b(AND)\\b)|\\=|(\\b(where)\\b)|(\\b(WHERE)\\b|(\\b(drop)\\b))|(\\b(DROP)\\b)|(\\b(1=1)\\b)|\\=|\\;)", ""));
        task.setName(task.getName().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b|(\\b(from)\\b)|(\\b(FROM)\\b)|\\*|\\'|(\\b(and)\\b)| (\\b(AND)\\b)|\\=|(\\b(where)\\b)|(\\b(WHERE)\\b|(\\b(drop)\\b))|(\\b(DROP)\\b)|(\\b(1=1)\\b)|\\=|\\;)", ""));
        BeanUtils.copyProperties(task, existingTask, "id");
        this.taskRepository.saveAndFlush(existingTask);
        modelMap.put("tasks", taskRepository.findAll());
        modelMap.put("mode", "MODE_TASKS");
        return "index";
    }

	@GetMapping("/edit-task/{id}")
	public String editTask(@PathVariable("id") int id, ModelMap modelMap){
        modelMap.put("task", taskRepository.getOne(id));
        //modelMap.put("task", taskRepository.findOneInsecure(id));

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
