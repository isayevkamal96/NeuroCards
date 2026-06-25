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


    public DeckController(DeckService deckService, PdfTextExtractor pdfTextExtractor) {
        this.deckService = deckService;
        this.pdfTextExtractor = pdfTextExtractor;

    }

    @PostMapping("/upload")
    public String uploadPdf(@RequestParam("file") MultipartFile file) throws IOException {
        return pdfTextExtractor.extractText(file);
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
