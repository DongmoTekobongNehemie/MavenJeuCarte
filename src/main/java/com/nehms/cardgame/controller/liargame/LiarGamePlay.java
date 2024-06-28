package com.nehms.cardgame.controller.liargame;

import com.nehms.cardgame.controller.Configurer;
import com.nehms.cardgame.controller.Playable;
import com.nehms.cardgame.controller.PlayerCreator;
import com.nehms.cardgame.model.Card;
import com.nehms.cardgame.model.Pattern;
import com.nehms.cardgame.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class LiarGamePlay implements Playable {

    private static final Logger log = Logger.getLogger(LiarGamePlay.class.getName());

    Random rand = new Random();

    @Override
    public void play() {
    	
        Card currentCard = new Card(null, null);
        List<Card> cardsPlayed = new ArrayList<>();

        Configurer configurer = new LiarGameConfigurer();

        List<Card> cards = new ArrayList<>();
        
     

        configurer.createCards(cards);
        configurer.mixCards(cards);

        List<Player> players = new ArrayList<>();
        PlayerCreator playerCreator = new LiarGamePlayerCreator();
        playerCreator.create(players);

        configurer.distribute(cards, players);

        boolean isStillPlaying = true;

        int i = 0;

        while (isStillPlaying) {

            log.log(Level.FINE, "Tour {0}", i);

            for (Player joueur : players) {
            	
                Pattern pattern = Pattern.values()[rand.nextInt(Pattern.values().length)];


                playOneCard(joueur, cardsPlayed, currentCard,  pattern);
                
               Pattern currentPattern = pattern;


                contradict(joueur, players, currentCard, currentPattern, cardsPlayed);

                if (joueur.getHand().isEmpty()) {
                    log.log(INFO, "Le gagnant est {0} au tour {1}!", new Object[]{joueur.getName(), i});
                    isStillPlaying = false;
                    break;
                }
            }

            i++;
        }
    }

    @Override
    public void playOneCard(Player joueur, List<Card> cardsPlayed, Card currentCard, Pattern avis) {
        int randomNumber = rand.nextInt(joueur.getHand().size());

        Pattern[] values = Pattern.values();
        int index = rand.nextInt(values.length);
        avis = values[index];

        cardsPlayed.add(joueur.getHand().get(randomNumber));
        currentCard.setPattern(joueur.getHand().get(randomNumber).getPattern());
        currentCard.setNumber(joueur.getHand().get(randomNumber).getNumber());
        joueur.getHand().remove(randomNumber);

        log.log(Level.FINE, "=> Le {0} dit {1} \n", new Object[]{joueur.getName(), avis});
        log.log(Level.FINE, "La carte qui a ete jouer est la carte => {0} \n", currentCard);

        int i = 1;

        log.log(Level.FINE, "La main du {0} est désormais \n", joueur.getName());

        for (Card carte : joueur.getHand()) {
            log.log(Level.FINE, "{0} - {1}", new Object[]{i, carte});
            i++;
        }
    }

    
    public void contradict(Player player, List<Player> players, Card currentCard, Pattern pattern,
                           List<Card> cardsPlayed) {
        // Créer une liste de joueurs excluant le joueur actuel
        List<Player> listeAutresJoueurs = new ArrayList<>(players);
        listeAutresJoueurs.remove(player);

        // Vérifier qu'il reste bien des joueurs après la suppression
        if (listeAutresJoueurs.isEmpty()) {
            log.severe("Il n'y a pas d'autres joueurs disponibles pour refuter.");
            return;
        }

        // Choisir un joueur au hasard parmi les autres joueurs
        int randomNumber = rand.nextInt(listeAutresJoueurs.size());

        if (currentCard.getPattern().equals(pattern)) {
            listeAutresJoueurs.get(randomNumber).getHand().addAll(cardsPlayed);
            cardsPlayed.clear();
        } else {
            player.getHand().addAll(cardsPlayed); 
            cardsPlayed.clear();

        }

    }

}
