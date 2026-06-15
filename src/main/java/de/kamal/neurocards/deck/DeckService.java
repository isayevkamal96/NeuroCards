package de.kamal.neurocards.deck;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeckService {

    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public Deck createDeck(String title) {
        Deck deck = new Deck();
        deck.setTitle(title);
        return deckRepository.save(deck);
    }

    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    public Deck getDeckById(Long id) {
        return deckRepository.findById(id).orElseThrow(() -> new RuntimeException("Deck not found: " + id));
    }

    public void deleteDeck(Long id) {
        deckRepository.deleteById(id);
    }
}
