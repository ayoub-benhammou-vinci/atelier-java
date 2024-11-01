package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {
    private Moniteur moniteur;
    private Stage stageValide;
    private Sport sportCompetent;
    @BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("Maxime");
        sportCompetent = Mockito.mock(Sport.class);
        Mockito.when(sportCompetent.contientMoniteur(moniteur)).thenReturn(true);
        stageValide = Mockito.mock(Stage.class);
        Mockito.when(stageValide.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(stageValide.getMoniteur()).thenReturn(null);
        Mockito.when(stageValide.getSport()).thenReturn(sportCompetent);
    }

    private void amenerALEtat(int etat, Moniteur moniteur){
        for (int i = 1; i <= etat; i++) {
            Stage stage = Mockito.mock(Stage.class);
            Mockito.when(stage.getNumeroDeSemaine()).thenReturn(i);
            Mockito.when(stage.getMoniteur()).thenReturn(null);
            Mockito.when(stage.getSport()).thenReturn(sportCompetent);
            moniteur.ajouterStage(stage);
        }
    }

    @Test
    void testMoniteurTC1() {
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(1,moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC2() {
        amenerALEtat(1,moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(2,moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC3() {
        amenerALEtat(2,moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(3,moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC4() {
        amenerALEtat(3,moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4,moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC5() {
        amenerALEtat(3,moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageValide)),
                () -> assertEquals(4,moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC6() {
        amenerALEtat(4,moniteur);
        Stage stageSemainePrise = Mockito.mock(Stage.class);
        Mockito.when(stageSemainePrise.getNumeroDeSemaine()).thenReturn(1);
        Mockito.when(stageSemainePrise.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageSemainePrise.getMoniteur()).thenReturn(null);

        assertAll(() -> assertFalse(moniteur.ajouterStage(stageSemainePrise)),
                () -> assertFalse(moniteur.contientStage(stageSemainePrise)),
                () -> assertEquals(4,moniteur.nombreDeStages()),
                () -> Mockito.verify(stageSemainePrise,Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC7() {
        amenerALEtat(4,moniteur);
        Moniteur autreMoniteur = new MoniteurImpl("Alexis");
        Stage stageAutreMoniteur = Mockito.mock(Stage.class);
        Mockito.when(stageAutreMoniteur.getNumeroDeSemaine()).thenReturn(5);
        Mockito.when(stageAutreMoniteur.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageAutreMoniteur.getMoniteur()).thenReturn(autreMoniteur);

        assertAll(() -> assertFalse(moniteur.ajouterStage(stageAutreMoniteur)),
                () -> assertFalse(moniteur.contientStage(stageAutreMoniteur)),
                () -> assertEquals(4,moniteur.nombreDeStages()),
                () -> Mockito.verify(stageAutreMoniteur, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC8() {
        amenerALEtat(4,moniteur);
        Stage stageMoniteur = Mockito.mock(Stage.class);
        Mockito.when(stageMoniteur.getNumeroDeSemaine()).thenReturn(5);
        Mockito.when(stageMoniteur.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageMoniteur.getMoniteur()).thenReturn(moniteur);

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageMoniteur)),
                () -> assertTrue(moniteur.contientStage(stageMoniteur)),
                () -> assertEquals(5,moniteur.nombreDeStages()),
                () -> Mockito.verify(stageMoniteur, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC9() {
        Sport sportNonCompetent = Mockito.mock(Sport.class);
        Mockito.when(sportNonCompetent.contientMoniteur(moniteur)).thenReturn(false);

        Stage nouveauStage = Mockito.mock(Stage.class);
        Mockito.when(nouveauStage.getNumeroDeSemaine()).thenReturn(1);
        Mockito.when(nouveauStage.getSport()).thenReturn(sportNonCompetent);
        Mockito.when(nouveauStage.getMoniteur()).thenReturn(null);

        assertAll(() -> assertFalse(moniteur.ajouterStage(nouveauStage)),
                () -> assertFalse(moniteur.contientStage(nouveauStage)),
                () -> assertEquals(0,moniteur.nombreDeStages()),
                () -> Mockito.verify(nouveauStage,Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }
}