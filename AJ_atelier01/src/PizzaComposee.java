import java.util.ArrayList;

public class PizzaComposee extends Pizza {
    public final static int REMISE = 15;

    //Fait appel au constructeur de la classe parent, pas besoin de vérifier
    public PizzaComposee(String titre, String description, ArrayList<Ingredient> ingredients) {
        super(titre, description, ingredients);
    }

    public boolean ajouter(Ingredient ingredient){
        throw new UnsupportedOperationException( "Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    public boolean supprimer(Ingredient ingredient){
        throw new UnsupportedOperationException( "Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    public double calculerPrix(){
        double somme = super.calculerPrix();
        double reduction = (100.0 - REMISE)/100;
        somme = somme * reduction;
        return Math.ceil(somme);
    }
}
