package de.kamal.neurocards.deck;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FlashcardGeneratorTest {

    @Test
    void generate_shouldReturnCardsFromChatClient() {
        List<FlashcardData> expected = List.of(
                new FlashcardData("Frage 1?", "Antwort 1."),
                new FlashcardData("Frage 2?", "Antwort 2.")
        );

        ChatClient chatClient = mock(ChatClient.class);
        ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        ChatClient.CallResponseSpec callSpec = mock(ChatClient.CallResponseSpec.class);
        ChatClient.Builder builder = mock(ChatClient.Builder.class);

        when(builder.build()).thenReturn(chatClient);
        when(chatClient.prompt()).thenReturn(requestSpec);
        when(requestSpec.user(ArgumentMatchers.<String>any())).thenReturn(requestSpec);
        when(requestSpec.call()).thenReturn(callSpec);
        when(callSpec.entity(any(ParameterizedTypeReference.class))).thenReturn(expected);

        FlashcardGenerator generator = new FlashcardGenerator(builder);

        List<FlashcardData> result = generator.generate("Beliebiger Text");

        assertThat(result).hasSize(2);
        assertThat(result.get(0).question()).isEqualTo("Frage 1?");
        assertThat(result.get(1).answer()).isEqualTo("Antwort 2.");
    }
}