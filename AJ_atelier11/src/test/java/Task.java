public interface Task {
    String getTitre();
    boolean isCompleted();
    String getDescription();
    boolean completeTask();
    boolean updateDescription(String description);
    boolean updateTitle(String title);
}
