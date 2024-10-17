package domaine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat implements Comparator<Ingredient>{

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    private ArrayList<Instruction> recette = new ArrayList<Instruction>();
    private HashSet<IngredientQuantifie> ingredients = new HashSet<IngredientQuantifie>();

    public Plat(String nom, int nbPersonnes, Difficulte niveauDifficulte, Cout cout) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ofMinutes(0);
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void insererInstruction(int position, Instruction instruction){
        if(position <= 0){
            throw new IllegalArgumentException("Position passé en paramètre est inférieur ou égal à 0");
        }

        if(this.recette.size() < position){
            throw new IllegalArgumentException("Possition passé en paramètre supérieur au nombre d'instructions");
        }


        this.recette.add(position-1,instruction);
        this.dureeEnMinutes = this.dureeEnMinutes.plus(instruction.getDureeEnMinutes());

    }

    public void ajouterInstruction(Instruction instruction){

        this.recette.add(instruction);
        this.dureeEnMinutes = this.dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    public Instruction remplacerInstruction(int position, Instruction instruction){
        if(position <= 0){
            throw new IllegalArgumentException("Position passé en paramètre est inférieur ou égal à 0");
        }

        if(this.recette.size() < position){
            throw new IllegalArgumentException("Possition passé en paramètre supérieur au nombre d'instructions");
        }
        Instruction instructionOld = this.recette.get(position-1);
        this.dureeEnMinutes = this.dureeEnMinutes.minus(instructionOld.getDureeEnMinutes());

        this.recette.set(position-1, instruction);
        this.dureeEnMinutes = this.dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        return instructionOld;

    }


    public Instruction supprimerInstruction(int position){
        if(position <= 0){
            throw new IllegalArgumentException("Position passé en paramètre est inférieur ou égal à 0");
        }

        if(this.recette.size() < position){
            throw new IllegalArgumentException("Possition passé en paramètre supérieur au nombre d'instructions");
        }

        Instruction instructionDefault = this.recette.remove(position-1);
        this.dureeEnMinutes = this.dureeEnMinutes.minus(instructionDefault.getDureeEnMinutes());
        return instructionDefault;
    }

    public List<Instruction> instructions() {
        return Collections.unmodifiableList(recette);
    }

    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }


    public enum Difficulte {
        X,XX,XXX,XXXX,XXXXX;

        public String toString(){
            return super.toString().replace("X","*");
        }
    }

    public enum Cout {
        $, $$, $$$, $$$$, $$$$$;

        public String toString() {
            return super.toString().replace("$","€");
        }

    }


}
