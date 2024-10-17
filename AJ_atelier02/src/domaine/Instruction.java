package domaine;

import java.time.Duration;

public class Instruction {
    private String description;
    private Duration dureeEnMinutes;

    public Instruction(String description, int dureeMinute){
        this.description = description;
        this.dureeEnMinutes = Duration.ofMinutes(dureeMinute);
    }

    public String getDescription() {
        return description;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDureeEnMinutes(Duration dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    public String toString(){
        long hours = dureeEnMinutes.toHours();
        long minutes = dureeEnMinutes.toMinutes() % 60;
        return String.format("(%02d:%02d) %s", hours, minutes, description);
    }
}
