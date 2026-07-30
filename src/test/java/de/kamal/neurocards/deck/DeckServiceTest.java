package de.kamal.neurocards.deck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeckServiceTest {

    @Mock
    private DeckRepository deckRepository;

    @InjectMocks
    private DeckService deckService;

    @Test
    void createDeckFromCards_shouldBuildDeckWithCorrectCards() {
        List<FlashcardData> input = List.of(
                new FlashcardData("Was ist Java?", "Eine Programmiersprache."),
                new FlashcardData("Was ist Spring?", "Ein Framework für Java.")
        );

        when(deckRepository.save(any(Deck.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Deck result = deckService.createDeckFromCards("Java Basics", input);

        assertThat(result.getTitle()).isEqualTo("Java Basics");
        assertThat(result.getFlashcards()).hasSize(2);
        assertThat(result.getFlashcards().get(0).getQuestion()).isEqualTo("Was ist Java?");
        assertThat(result.getFlashcards().get(0).getAnswer()).isEqualTo("Eine Programmiersprache.");
        assertThat(result.getFlashcards().get(0).getDeck()).isSameAs(result);
    }
}