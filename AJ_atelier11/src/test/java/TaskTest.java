import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {


    Task task;
    Task task2;

    @BeforeEach
    void setUp() {
        task = new TaskImpl("Java","Atelier Java");
        task2 = new TaskImpl("JavaScript","Web");
    }

    @Test
    void createTask() {
        Task newTask = new TaskImpl("BD","SQL");
        assertAll(
                () -> assertEquals("BD", newTask.getTitre()),
                () -> assertEquals("SQL", newTask.getDescription())
        );
    }

    @Test
    void createNullFieldsTask() {
        Task errorTask = new TaskImpl(null,null);
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, errorTask::getTitre),
                () -> assertThrows(IllegalArgumentException.class, errorTask::getDescription)
        );
    }

    @Test
    void createEmptyTask() {
        Task errorTask = new TaskImpl("","Sans titre");
        assertThrows(IllegalArgumentException.class, errorTask::getTitre);
    }

    @Test
    void completeTask() {
        assertTrue(this.task.completeTask());
    }

    @Test
    void completeAlreadyCompletedTask() {
        this.task.completeTask();
        assertFalse(this.task.completeTask());
    }

    @Test
    void updateDescription() {
        assertAll(
                () -> assertTrue(this.task.updateDescription("Hello")),
                () -> assertEquals("Hello",this.task.getDescription())
        );
    }

    @Test
    void updateDescriptionWhenCompletedTask() {
        this.task.completeTask();
        assertAll(
                () -> assertFalse(this.task.updateDescription("Hello")),
                () -> assertEquals("Atelier Java", this.task.getDescription())
        );
    }

    @Test
    void updateDescriptionToNull() {
        Task taskError = new TaskImpl("hello","Did you really think ?");
        assertAll(
                () -> assertFalse(taskError.updateDescription(null)),
                () -> assertEquals("Did you really think ?", taskError.getDescription())
        );
    }

    @Test
    void updateTitle() {
        this.task.updateTitle("Yeeha");
        assertEquals("Yeeha",task.getTitre());
    }

    @Test
    void updateTitleWhenCompletedTask() {
        this.task.completeTask();
        assertAll(
                () -> assertFalse(this.task.updateTitle("Mima")),
                () -> assertEquals("Java", this.task.getTitre())
        );
    }

    @Test
    void updateTitleToEmptyOrNullString() {
        assertAll(
                () -> assertFalse(this.task.updateTitle(null)),
                () -> assertEquals("Java",this.task.getTitre()),
                () -> assertFalse(this.task.updateTitle("")),
                () -> assertEquals("Java", this.task.getTitre())
        );
    }
}
