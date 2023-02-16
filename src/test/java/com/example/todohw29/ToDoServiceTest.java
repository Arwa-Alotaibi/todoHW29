package com.example.todohw29;

import com.example.todohw29.Model.Todo;
import com.example.todohw29.Model.User;
import com.example.todohw29.Repository.TodoRepository;
import com.example.todohw29.Repository.UserRepository;
import com.example.todohw29.Service.ToDoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @InjectMocks
    ToDoService toDoService;

    @Mock
    TodoRepository todoRepository;

    @Mock
    UserRepository userRepository;

    Todo todo1 , todo2, todo3;

    User user1 ,user2, user3;

    List<Todo> todoList;



    @BeforeEach
    void setUp() {
        user1 = new User(null,"arwa","12345","ADMIN",null);
        todo1 = new Todo(null,"study java","aaa",user1);
        todo2 = new Todo(null,"study spring","aaa",user1);
        todo3 = new Todo(null,"title","body",user1);

        todoList= new ArrayList<>();
        todoList.add(todo1);
        todoList.add(todo2);
        todoList.add(todo3);
    }

    @Test
    public void AddToDoTest(){
        when(userRepository.findUserById(user1.getId())).thenReturn(user1);
        toDoService.AddTodo(user1.getId(),todo1);
        verify(userRepository,times(1)).findUserById(user1.getId());
        verify(todoRepository,times(1)).save(todo1);
    }

    @Test
    public void UpdateToDoTest(){
        when(userRepository.findUserById(user1.getId())).thenReturn(user1);
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);
        toDoService.UpdateToDo(todo1.getId(),todo2,user1.getId());
        verify(userRepository,times(1)).findUserById(user1.getId());
        verify(todoRepository,times(1)).findTodoById(todo1.getId());
        verify(todoRepository,times(1)).save(todo1);
    }

    @Test
    public void delete_todo(){
        when(userRepository.findUserById(user1.getId())).thenReturn(user1);
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);
        toDoService.Delete_Todo(todo1.getId(),user1.getId());
        verify(userRepository,times(1)).findUserById(user1.getId());
        verify(todoRepository,times(1)).findTodoById(todo1.getId());
        verify(todoRepository,times(1)).delete(todo1);
    }

    @Test
    public void getAlltodos(){
       when(todoRepository.findAll()).thenReturn(todoList);
       List<Todo> todoList1 =toDoService.getall();
        Assertions.assertEquals(3,todoList1.size());
        verify(todoRepository,times(1)).findAll();
    }

    @Test
    public void GetAllMyTodo(){
        when(userRepository.findUserById(user1.getId())).thenReturn(user1);
        when(todoRepository.findAllByUser(user1)).thenReturn(todoList);

        List<Todo> todos= toDoService.GetAllMyTodo(user1.getId());
        Assertions.assertEquals(todos,todoList);
        verify(userRepository,times(1)).findUserById(user1.getId());
        verify(todoRepository,times(1)).findAllByUser(user1);
    }
}
