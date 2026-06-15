package de.kamal.neurocards.deck;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping
    public Deck createDeck(@RequestBody CreateDeckRequest request) {
        return deckService.createDeck(request.title());
    }

    @GetMapping
    public List<Deck> getAllDecks() {
        return deckService.getAllDecks();
    }

    @GetMapping("/{id}")
    public Deck getDeckById(@PathVariable Long id) {
        return deckService.getDeckById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDeck(@PathVariable Long id) {
        deckService.deleteDeck(id);
    }

}
