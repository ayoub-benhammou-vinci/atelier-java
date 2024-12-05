import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<Task> tasks = new ArrayList<>();

    public boolean addTask(Task task) {
        if(task == null){
            return false;
        }
        if(task.getTitre().isBlank()){
            return false;
        }
        if(this.containsTask(task)){
            return false;
        }
        return tasks.add(task);
    }

    public boolean containsTask(Task task) {
        return tasks.contains(task);
    }

    public boolean removeTask(Task task){
        return this.tasks.remove(task);
    }

    public Task findTask(Task task){
        if(!this.containsTask(task)){
            return null;
        }
        return task;
    }

    public boolean updateTodoListTask(Task task, Task taskModified){
        if(task == null || taskModified == null){
            return false;
        }
        if(!tasks.contains(task)){
            return false;
        }
        if(task.isCompleted()){
            return false;
        }

        task.updateTitle(taskModified.getTitre());
        task.updateDescription(taskModified.getDescription());
        return true;

    }

}
