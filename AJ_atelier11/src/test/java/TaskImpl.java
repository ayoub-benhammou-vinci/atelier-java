import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TaskImpl implements Task {
    private String titre;
    private String description;
    private boolean isCompleted;

    public TaskImpl(String titre, String description) {
        if(titre == null || description == null){
            throw new IllegalArgumentException("Titre ou description NULL");
        }
        if(titre.isBlank()){
            throw new IllegalArgumentException("Titre est constitué uniquement de caractère blanc");
        }
        this.titre = titre;
        this.description = description;
        //isCompleted est par défaut à false
    }

    public String getTitre() {
        return titre;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskImpl task = (TaskImpl) o;
        return Objects.equals(titre, task.titre) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre, description);
    }

    public boolean completeTask() {
        if(this.isCompleted()){
            return false;
        }
        this.isCompleted = true;
        return true;
    }

    public boolean updateTitle(String title){
        if(this.isCompleted){
            return false;
        }

        if(title == null || title.isBlank()){
            return false;
        }

        this.titre = title;
        return true;
    }

    public boolean updateDescription(String description) {
        if(this.getDescription() == null){
            return false;
        }
        if(this.isCompleted()){
            return false;
        }
        this.description = description;
        return true;
    }

}
