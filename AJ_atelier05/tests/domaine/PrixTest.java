package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {
    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);

        prixPub = new Prix(TypePromo.PUB,10);
        prixPub.definirPrix(3,15);
        prixSolde = new Prix(TypePromo.SOLDE, 5);
    }

    @DisplayName("Test du constructeur pour savoir si TypeProduit est null")
    @Test
    void testPrixConstructor1() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(null,2), "TypePromo est null");
    }
    @DisplayName("Test du constructeur pour savoir si le prix <= 0")
    @Test
    void testPrixConstructor2() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.SOLDE,-1), "Valeur de la promo est négatif ( <= 0)");
    }

    @DisplayName("Test pour vérifier si la valeur de la promo est == 0")
    @Test
    void testGetValeurPromo1() {
        assertEquals(0, prixAucune.getValeurPromo());
    }

    @DisplayName("Test pour vérifier que la valeur de la promo correspond à la valeur passé en paramètre")
    @Test
    void testGetValeurPromo2() {
        assertEquals(prixPub.getValeurPromo(),10);
    }

    @DisplayName("Test pour vérifier que le type de promo est null lors de l'appel du constructeur sans paramètre")
    @Test
    void verifyTypePromoIsNull() {
        assertNull(prixAucune.getTypePromo());
    }

    @DisplayName("Vérification du TypePromo == typePromo passé en paramètre")
    @Test
    void verifyTypePromo() {
        assertEquals(prixPub.getTypePromo(), TypePromo.PUB);
    }

    @DisplayName("Verification si la quantité <= 0 via la méthode définir prix")
    @Test
    void definirPrixTest1() {
        assertThrows(IllegalArgumentException.class, () -> prixPub.definirPrix(-2,10));
    }


    @DisplayName("Vérification si le prix unitaire <= 0 via la méthode définir prix")
    @Test
    void definirPrixTest2() {
        assertThrows(IllegalArgumentException.class, () ->prixPub.definirPrix(2,0));
    }

    @DisplayName("Vérifier si l'ancien prix a bien été remplacé")
    @Test
    void definirPrixTest3() {
        prixAucune.definirPrix(10,6);
        assertEquals(prixAucune.getPrix(10),6);
    }

    @DisplayName("Verifier si IllegalArgumentExpecton (Quantité null ou négatif)")
    @Test
    void getPrixTest1() {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.getPrix(0));
    }

    @DisplayName("Tester les prix de prixAucune (Partie 1)")
    @ParameterizedTest
    @ValueSource(ints = {1,5,9})
    void getPrixTest2Part1(int quantite) {
        assertEquals(20,prixAucune.getPrix(quantite));
    }

    @DisplayName("Testers les prix de prixAucune (Partie 2)")
    @ParameterizedTest
    @ValueSource(ints = {10,15,20,25})
    void getPrixTest2Part2(int quantite) {
        assertEquals(10, prixAucune.getPrix(quantite));
    }

    @DisplayName("Testez qu’une QuantiteNonAutoriseeException si demande de prix de 2 unité pour l'attribut prixPub")
    @Test
    void testGetPrix3() {
        assertThrows(QuantiteNonAutoriseeException.class,() -> prixPub.getPrix(2));
    }

    @DisplayName("Testez qu’une QuantiteNonAutoriseeException si demande de prix de 2 unité pour l'attribut")
    @Test
    void testGetPrix4() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix(1));
    }
}