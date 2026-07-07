package de.kamal.neurocards.deck;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import de.kamal.neurocards.pdf.PdfTextExtractor;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckService deckService;
    private final PdfTextExtractor pdfTextExtractor;
    private final FlashcardGenerator flashcardGenerator;



    public DeckController(DeckService deckService, PdfTextExtractor pdfTextExtractor, FlashcardGenerator flashcardGenerator) {
        this.deckService = deckService;
        this.pdfTextExtractor = pdfTextExtractor;
        this.flashcardGenerator = flashcardGenerator;

    }

    @PostMapping("/upload")
    public Deck uploadPdf(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) throws IOException {
        String text = pdfTextExtractor.extractText(file);
        List<FlashcardData> generatedCards = flashcardGenerator.generate(text);
        return deckService.createDeckFromCards(title, generatedCards);
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
