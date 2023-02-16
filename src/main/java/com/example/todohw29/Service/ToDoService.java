package com.example.todohw29.Service;

import com.example.todohw29.Exception.ApiException;
import com.example.todohw29.Model.Todo;
import com.example.todohw29.Model.User;
import com.example.todohw29.Repository.TodoRepository;
import com.example.todohw29.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ToDoService {
    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public ToDoService(TodoRepository todoRepository,UserRepository userRepository){
        this.todoRepository=todoRepository;
        this.userRepository=userRepository;
    }

    public List<Todo> getall(){
        return todoRepository.findAll();
    }
    public void AddTodo(Integer user_id , Todo todo){
        User user = userRepository.findUserById(user_id);
        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void UpdateToDo(Integer todo_id ,Todo todo,Integer user_id){
        Todo update_todo = todoRepository.findTodoById(todo_id);
        User user = userRepository.findUserById(user_id);
        if(update_todo==null){
            throw new ApiException("todo id not found!!");
        } else if (update_todo.getUser().getId()!=user_id ) {
            throw new ApiException("you do not have the authority to update !");
        }

        update_todo.setTitle(todo.getTitle());
        update_todo.setBody(todo.getBody());
        todoRepository.save(update_todo);
    }

    public void Delete_Todo(Integer todo_id,Integer user_id){
        Todo delete_todo=todoRepository.findTodoById(todo_id);
        User user = userRepository.findUserById(user_id);
        if(delete_todo==null){
            throw new ApiException("todo id not found!!");
        } else if (delete_todo.getUser().getId()!=user_id) {
            throw new ApiException("you do not have the authority to delete !");
        }

        todoRepository.delete(delete_todo);
    }

    public List<Todo> GetAllMyTodo(Integer user_id){
        User user = userRepository.findUserById(user_id);
        List<Todo> All_todo = todoRepository.findAllByUser(user);
        if(All_todo.isEmpty()){
            throw new ApiException("You dont add task ");
        }
        return All_todo;
    }

    public Todo GetToDoById(Integer todo_id,Integer user_id){
        Todo todo = todoRepository.findTodoById(todo_id);
        User user = userRepository.findUserById(user_id);
        if(todo==null){
            throw new ApiException("todo id not found!!");
        } else if (todo.getUser().getId()!=user_id) {
            throw new ApiException("you do not have the authority !!");
        }

        return todo;
    }
}
