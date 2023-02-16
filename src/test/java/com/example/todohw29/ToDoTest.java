package com.example.todohw29;
import com.example.todohw29.Model.Todo;
import com.example.todohw29.Model.User;
import com.example.todohw29.Repository.TodoRepository;
import com.example.todohw29.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ToDoTest {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    Todo todo1 , todo2, todo3;

    User user1 ,user2, user3;


    @BeforeEach
    void setUp() {
        user1 = new User(null,"arwa","12345","ADMIN",null);
        todo1 = new Todo(null,"study java","aaa",user1);
        todo2 = new Todo(null,"study spring","aaa",user1);
        todo3 = new Todo(null,"title","body",user1);
    }

    @Test
    public void findAllByMyUserTesting(){
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);
        List<Todo> All_todo=todoRepository.findAllByUser(user1);
        Assertions.assertThat(All_todo.get(0).getUser().getId()).isEqualTo(user1.getId());
    }

    @Test
    public void findtodobyid(){
        todoRepository.save(todo2);
        Todo todo = todoRepository.findTodoById(todo2.getId());
        Assertions.assertThat(todo).isEqualTo(todo2);

    }


}
