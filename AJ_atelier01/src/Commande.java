import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Commande implements Iterable<LigneDeCommande>{
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesCommande;

    public Commande(Client client) {
        Util.checkObject(client);
        if(client.getCommandeEnCours() != null){
            throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        }
        this.client = client;
        this.date = LocalDateTime.now();
        lignesCommande = new ArrayList<>();
        this.numero = numeroSuivant;
        numeroSuivant++;
        client.enregistrer(this);

    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter(Pizza pizza, int quantite){
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);
       if(client.getCommandeEnCours() != this) {
           return false;
       }

        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            if(ligneDeCommande.getPizza().equals(pizza)){
                int quantiteFinal = ligneDeCommande.getQuantite() + quantite;
                ligneDeCommande.setQuantite(quantiteFinal);
                return true;
            }
        }

        LigneDeCommande ligneDeCommande = new LigneDeCommande(pizza, quantite);
        lignesCommande.add(ligneDeCommande);
        return true;


    }


    public boolean ajouter(Pizza pizza){
        Util.checkObject(pizza);
        return this.ajouter(pizza,1);
    }

    public double calculerMontantTotal() {
        double prixTotal = 0;
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            prixTotal += ligneDeCommande.calculerPrixTotal();
        }

        return prixTotal;
    }

    public boolean retirer(Pizza pizza, int quantite){
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);
        if(this.client.getCommandeEnCours() != this) return false;

        //Dans le cas ou on trouve la pizza correspondante
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            if(ligneDeCommande.getPizza().equals(pizza)){

                if(ligneDeCommande.getQuantite() > quantite){
                    return false;
                }

                if(ligneDeCommande.getQuantite() < quantite){
                    int nouvelleQuantite = ligneDeCommande.getQuantite() - quantite;
                    ligneDeCommande.setQuantite(nouvelleQuantite);
                    return true;
                }

                if(ligneDeCommande.getQuantite() == quantite){
                    lignesCommande.remove(ligneDeCommande);
                    return true;
                }
            }
        }

        return false;

        //Sinon on return false car on a pas trouver la bonne pizza dans la liste ou
        //la quantité demandé était supérieur comparé au stock de pizza de la ligneDeCommande

    }

    public boolean retirer(Pizza pizza){
        Util.checkObject(pizza);
        return this.retirer(pizza, 1);
    }

    public boolean supprimer(Pizza pizza){
        Util.checkObject(pizza);
        if(this.client.getCommandeEnCours() != this) return false;
        for (LigneDeCommande ligneDeCommande : this.lignesCommande) {
            if(ligneDeCommande.getPizza().equals(pizza)){
                lignesCommande.remove(ligneDeCommande);
                return true;
            }
        }

        return false;
    }

    public String detailler(){
        String commandeDetail = "";
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            commandeDetail += ligneDeCommande.toString() + "\n";
        }
        return commandeDetail;
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }

    @Override
    public Iterator<LigneDeCommande> iterator() {
        return this.lignesCommande.iterator();
    }
}
