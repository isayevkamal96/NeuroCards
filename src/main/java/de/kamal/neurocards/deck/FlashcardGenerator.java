package de.kamal.neurocards.deck;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardGenerator {

    private final ChatClient chatClient;

    public FlashcardGenerator(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public List<FlashcardData> generate(String text) {
        String prompt = """
                Erstelle Lernkarten aus dem folgenden Text.
                Jede Karte besteht aus einer prägnanten Frage und einer klaren Antwort.
                Erstelle maximal 10 Karten. Antworte auf Deutsch.

                Text:
                %s
                """.formatted(text);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<FlashcardData>>() {});

    }

}
