import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Client implements Iterable<Commande> {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom;
    private String prenom;
    private String telephone;
    private Commande commandeEnCours;
    private ArrayList<Commande> commandesPassees = new ArrayList<Commande>();

    public Client(String nom, String prenom, String telephone) {
        Util.checkString(nom);
        Util.checkString(prenom);
        Util.checkString(telephone);
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.numero = numeroSuivant;
        numeroSuivant++;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return numero == client.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    public void setCommandeEnCours(Commande commandeEnCours) {
        Util.checkObject(commandeEnCours);
        this.commandeEnCours = commandeEnCours;
    }

    public boolean enregistrer(Commande commande){
        Util.checkObject(commande);
        if(this.commandeEnCours != null){
            return false;
        }

        if(commande.getClient() != this){
            return false;
        }

        this.commandeEnCours = commande;
        return true;
    }

    public boolean cloturerCommandeEnCours(){
        if(this.commandeEnCours == null){
            return false;
        }

        this.commandesPassees.add(this.commandeEnCours);
        this.commandeEnCours = null;
        return true;
    }

    @Override
    public Iterator<Commande> iterator() {
        return this.commandesPassees.iterator();
    }

    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }
}
