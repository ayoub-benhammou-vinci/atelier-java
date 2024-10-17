package main;

import domaine.Employe;

import java.util.function.Consumer;

public class ForEachInterface implements Consumer<Employe>{
    @Override
    public void accept(Employe employe) {
        System.out.println(employe);
    }

}