package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {
    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;

    Produit produit1;
    Produit produit2;

    @BeforeEach
    void setUp() {
        //Initialisation des Prix
        prixAucune = new Prix();
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);

        prixPub = new Prix(TypePromo.PUB,10);
        prixPub.definirPrix(3,15);

        prixSolde = new Prix(TypePromo.SOLDE, 5);

        produit1 = new Produit("Savon","Dove","Hygiène");
        produit2 = new Produit("Chips","Doritos","Nourriture");

        produit1.ajouterPrix(LocalDate.of(2005,06,21), prixPub);
        produit2.ajouterPrix(LocalDate.of(2024,10,24),prixAucune);


    }
    @DisplayName("Test du constructeur")
    @Test
    void testConstructor1() {
        assertThrows(IllegalArgumentException.class, () -> new Produit("","", null), "Paramètre vide ou null");
    }

    @DisplayName("Test des getters")
    @Test
    void testGetter() {
        assertEquals("Savon", produit1.getNom());
        assertEquals("Dove", produit1.getMarque());
        assertEquals("Hygiène", produit1.getRayon());
    }

    @DisplayName("Test des prix (ajouterPrix)")
    @Test
    void testAjouterPrix1() {
        assertThrows(IllegalArgumentException.class,() -> produit1.ajouterPrix(null,null));
    }

    @DisplayName("Test de la méthode ajouter Prix -> DDateDejaPresenteException")
    @Test
    void testAjouterPrix2() {
        assertThrows(DateDejaPresenteException.class, () -> produit1.ajouterPrix(LocalDate.of(2005,06,21),prixAucune));
    }

    @DisplayName("test de la méthode ajouuter prix")
    @Test
    void testAjouterPrix3() {
        produit1.ajouterPrix(LocalDate.of(2024,6,20),prixPub);
        assertEquals(prixPub,produit1.getPrix(LocalDate.of(2024,6,20)));
    }

    @DisplayName("test de la méthode get")
    @Test
    void testGetPrix1() {
        assertThrows(PrixNonDisponibleException.class, () -> produit1.getPrix(LocalDate.of(2004,1,1)));
    }

    @DisplayName("Test de la méthode GET 2")
    @Test
    void testGetPrix2() {
        LocalDate date = LocalDate.of(2020,12,1);
        assertThrows(PrixNonDisponibleException.class, () -> new Produit("Dragibus","Haribo","Nourriture").getPrix(date));
    }

    @DisplayName("Test de la méthode GET 3")
    @Test
    void testGetPrix3() {
        Prix prixChanged = produit1.getPrix(LocalDate.of(2023,1,1));
        LocalDate date = LocalDate.of(2005,6,21);
        assertEquals(produit1.getPrix(date), prixChanged);


    }

    @DisplayName("Test de la méthode equals 1")
    @Test
    void testEquals1() {
        Produit produitSame = new Produit("Savon","Dove","Hygiène");
        assertEquals(produit1,produitSame);
    }

    @DisplayName("Test de la méthode equals 2")
    @Test
    void testEquals2() {
        Produit produitSame = new Produit("Savoaa","Dove","Hygiène");
        assertFalse(produit1.equals(produitSame));
    }

    @DisplayName("Test de la méthode equals 3")
    @Test
    void testEquals3() {
        Produit produitSame = new Produit("Savon","Dovea","Hygiène");
        assertFalse(produit1.equals(produitSame));
    }

    @DisplayName("Test de la méthode equals 4")
    @Test
    void testEquals4() {
        Produit produitSame = new Produit("Savon","Dove","Hygiène1");
        assertFalse(produit1.equals(produitSame));
    }

    @DisplayName("Test de la méthode Hashcode")
    @Test
    void testHashCode1() {
        Produit produitSame = new Produit("Savon","Dove","Hygiène");
        assertEquals(produit1.hashCode(), produitSame.hashCode());
    }




}
