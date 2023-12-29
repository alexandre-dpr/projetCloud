package com.projetcloud.modele;

import com.projetcloud.exceptions.CoupNonAutoriseException;
import com.projetcloud.exceptions.MauvaisTourException;
import com.projetcloud.exceptions.PartieTermineException;
import com.projetcloud.util.User;
import org.junit.Assert;
import org.junit.Test;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puissance4Test {
    private final List<User.Roles> role = new ArrayList<>(Arrays.asList(User.Roles.ROLE_USER));
    private final User joueur1 = new User("damien","password", role);
    private final User joueur2 = new User("alexandre","password", role);

    private final ArrayList<User> joueurs = new ArrayList<>(Arrays.asList(joueur1,joueur2));

    @Test
    public void jouerTour_WIN_COL_OK() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        System.out.println(joueurs);
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 1);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 2);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 3);
        partie.jouerTour(joueur1, 0);

        Assert.assertTrue(partie.isPartieTerminee());
        Assert.assertEquals(partie.getWinner(), joueur1);
    }

    @Test
    public void jouerTour_WIN_LIGNE_OK() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 1);
        partie.jouerTour(joueur1, 1);
        partie.jouerTour(joueur2, 2);
        partie.jouerTour(joueur1, 2);
        partie.jouerTour(joueur2, 3);
        partie.jouerTour(joueur1, 3);
        partie.jouerTour(joueur2, 4);

        Assert.assertTrue(partie.isPartieTerminee());
        Assert.assertEquals(partie.getWinner(), joueur2);
    }

    @Test
    public void jouerTour_WIN_DIAGONALE_DROITE_OK() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur1, 0); // 1
        partie.jouerTour(joueur2, 1); // 1 1
        partie.jouerTour(joueur1, 1); // 1 2
        partie.jouerTour(joueur2, 2); // 1 2 1
        partie.jouerTour(joueur1, 3); // 1 2 1 1
        partie.jouerTour(joueur2, 2); // 1 2 2 1
        partie.jouerTour(joueur1, 2); // 1 2 3 1
        partie.jouerTour(joueur2, 3); // 1 2 3 2
        partie.jouerTour(joueur1, 4); // 1 2 3 2 1
        partie.jouerTour(joueur2, 3); // 1 2 3 3 1
        partie.jouerTour(joueur1, 3); // 1 2 3 4 1 → Gagné par diagonale

        Assert.assertTrue(partie.isPartieTerminee());
        Assert.assertEquals(partie.getWinner(), joueur1);
    }

    @Test
    public void jouerTour_WIN_DIAGONALE_GAUCHE_OK() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur1, 5);
        partie.jouerTour(joueur2, 4);
        partie.jouerTour(joueur1, 4);
        partie.jouerTour(joueur2, 3);
        partie.jouerTour(joueur1, 2);
        partie.jouerTour(joueur2, 3);
        partie.jouerTour(joueur1, 3);
        partie.jouerTour(joueur2, 2);
        partie.jouerTour(joueur1, 1);
        partie.jouerTour(joueur2, 2);
        partie.jouerTour(joueur1, 2);

        Assert.assertTrue(partie.isPartieTerminee());
        Assert.assertEquals(partie.getWinner(), joueur1);
    }

    @Test
    public void jouerTour_PARTIE_NON_TERMINEE_OK() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 1);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 5);
        partie.jouerTour(joueur1, 4);
        partie.jouerTour(joueur2, 2);
        partie.jouerTour(joueur1, 3);
        partie.jouerTour(joueur2, 5);
        partie.jouerTour(joueur1, 4);
        partie.jouerTour(joueur2, 2);
        partie.jouerTour(joueur1, 1);

        Assert.assertFalse(partie.isPartieTerminee());
        Assert.assertNull(partie.getWinner());
    }

    @Test(expected = PartieTermineException.class)
    public void jouerTour_PartieTermineException_KO() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 1);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 2);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 3);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 0);
    }

    @Test(expected = MauvaisTourException.class)
    public void jouerTour_MauvaisTourException_KO() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur2, 0);
    }

    @Test(expected = CoupNonAutoriseException.class)
    public void jouerTour_CoupNonAutoriseException_MAX_COL_KO() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 0);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 0);
        partie.jouerTour(joueur1, 0);
        partie.jouerTour(joueur2, 0);
        partie.jouerTour(joueur1, 0);
    }

    @Test(expected = CoupNonAutoriseException.class)
    public void jouerTour_CoupNonAutoriseException_MAX_ROW_KO() throws CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        Puissance4 partie = new Puissance4(joueurs);
        partie.jouerTour(joueur1, 99);
    }
}