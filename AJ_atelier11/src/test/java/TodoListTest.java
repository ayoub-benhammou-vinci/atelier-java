import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    private TodoList todoList;
    Task task;
    Task task2;
    Task taskModified;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
        task = new TaskImpl("Java","Atelier Java");
        task2 = new TaskImpl("JavaScript","Web");
        taskModified = new TaskImpl("Modified","Hello");
    }
    @Test
    void addTask() {
        assertAll(
                () -> assertTrue(todoList.addTask(task)),
                () -> assertTrue(todoList.containsTask(task))
        );
    }

    @Test
    void addNullTask() {
        assertFalse(todoList.addTask(null));
    }

    @Test
    void addEmptyTask() {
        assertAll(
                () -> assertFalse(todoList.addTask(null)),
                () -> assertFalse(todoList.containsTask(null))
        );
    }

    @Test
    void addExistingTask() {
        todoList.addTask(task);
        assertFalse(todoList.addTask(task));
    }

    @Test
    void removeTask() {
        todoList.addTask(task);
        todoList.addTask(task2);
        assertTrue(todoList.removeTask(task2));
    }

    @Test
    void removeUnexistingTask() {
        assertFalse(todoList.removeTask(task));
    }

    @Test
    void removeClonedTask() {
        todoList.addTask(task);
        Task taskClone = new TaskImpl("Java","Atelier Java");
        todoList.addTask(taskClone);
        assertTrue(todoList.removeTask(taskClone));
    }

    @Test
    void findTask() {
        this.todoList.addTask(task);
        assertEquals(task,this.todoList.findTask(task));
    }

    @Test
    void findUnexistingTask() {
        //La tâche n'a pas été ajouté => Elle n'existe pas
        assertNull(this.todoList.findTask(task));
    }

    @Test
    void updateTodoListTask() {
        this.todoList.addTask(task);

        assertAll(
                () -> assertTrue(this.todoList.updateTodoListTask(task,taskModified)),
                () -> assertEquals(taskModified,task)
        );
    }

    @Test
    void updateTodoListUnexistingTask() {
        //Si la tâche n'est ajouté, elle n'existe pas dans la todoList
        assertAll(
                () -> assertFalse(this.todoList.updateTodoListTask(task, taskModified)),
                () -> assertNotEquals(taskModified,task)
        );
    }

    @Test
    void updateTodoListwithNullTask() {

        Task taskNull = null;
        Task taskModifiedNull = null;

        this.todoList.addTask(taskNull);
        assertAll(
                () -> assertFalse(this.todoList.updateTodoListTask(taskNull,taskModified)),
                () -> assertNotEquals(taskModified,task),
                () -> assertFalse(this.todoList.updateTodoListTask(task, taskModifiedNull)),
                () -> assertNotEquals(taskModifiedNull,task)
        );
    }

    @Test
    void updateTodoListWhenCompletedTask() {
        this.todoList.addTask(task);
        task.completeTask();

        assertAll(
                () -> assertFalse(this.todoList.updateTodoListTask(task,taskModified)),
                () -> assertNotEquals(taskModified,task)
        );
    }
}
