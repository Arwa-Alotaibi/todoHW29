package com.example.todohw29.Controller;


import com.example.todohw29.Model.Todo;
import com.example.todohw29.Model.User;
import com.example.todohw29.Service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/todos/")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping("/add/todo")
    public ResponseEntity AddToDo(@Valid @RequestBody Todo todo, @AuthenticationPrincipal User user_id){
        toDoService.AddTodo(user_id.getId(),todo);
        return ResponseEntity.status(200).body("todo added !!");
    }
    @PutMapping("/update/todo/{todo_id}")
    public ResponseEntity UpdateToDo(@PathVariable Integer todo_id , @Valid @RequestBody Todo todo , @AuthenticationPrincipal User user_id){
        toDoService.UpdateToDo(todo_id,todo, user_id.getId());
        return ResponseEntity.status(200).body("todo updated !!");
    }
    @DeleteMapping("/delete/todo/{todo_id}")
    public ResponseEntity DeleteToDo(@PathVariable Integer todo_id, @AuthenticationPrincipal User user){
        toDoService.Delete_Todo(todo_id, user.getId());
        return ResponseEntity.status(200).body("todo deleted !!");
    }

    @GetMapping("todo/{todo_id}")
    public ResponseEntity GetToDobyid(@PathVariable Integer todo_id, @AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(toDoService.GetToDoById(todo_id, user.getId()));
    }

    @GetMapping("/all")
    public ResponseEntity AllMYtodos(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(toDoService.GetAllMyTodo(user.getId()));
    }

}
