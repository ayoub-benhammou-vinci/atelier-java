package main;

import domaine.Employe;
import domaine.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionInterface implements Function<Employe, String> {

    public static List<Employe> employes;

    public static void main(String[] args) {
        employes = new ArrayList<>();

        employes.add(new Employe(Genre.HOMME, 185, "Bob"));
        employes.add(new Employe(Genre.FEMME, 225, "Alice"));
        employes.add(new Employe(Genre.HOMME, 155, "John"));
        employes.add(new Employe(Genre.FEMME, 165, "Carole"));
        employes.add(new Employe(Genre.HOMME, 185, "Alex"));
        employes.add(new Employe(Genre.HOMME, 185, "Bart"));
    }

    public FunctionInterface() {
    }

    @Override
    public String apply(Employe employe) {
        if(employe == null) return null;
        return employe.getNom();
    }


}
