package tech.chillo.sa.dto;

import tech.chillo.sa.entites.Sentiment;

public record SentimentDTO(
        int id,
        String texte,
        tech.chillo.sa.enums.TypeSentiment type,
        ClientDTO client
) {
    public static SentimentDTO fromSentiment(Sentiment sentiment) {
        return new SentimentDTO(
                sentiment.getId(),
                sentiment.getTexte(),
                sentiment.getType(),
                new ClientDTO(
                        sentiment.getClient().getId(),
                        sentiment.getClient().getEmail(),
                        sentiment.getClient().getTelephone()
                )
        );
    }
}
