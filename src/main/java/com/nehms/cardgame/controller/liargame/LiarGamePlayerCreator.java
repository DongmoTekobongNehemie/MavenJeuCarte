package com.nehms.cardgame.controller.liargame;

import com.nehms.cardgame.controller.PlayerCreator;
import com.nehms.cardgame.model.Player;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class LiarGamePlayerCreator implements PlayerCreator {

    private static final Logger log = Logger.getLogger(LiarGamePlayerCreator.class.getName());

    @Override
    public void create(List<Player> players) {
        Scanner clavier = new Scanner(System.in);

        log.info("Entre le nombre de joueurs => ");

        int nbreJoueur = clavier.nextInt();

        for (int i = 0; i < nbreJoueur; i++) {
            players.add(new Player("Joueur_" + (i + 1)));
        }

        clavier.close();
    }
}
