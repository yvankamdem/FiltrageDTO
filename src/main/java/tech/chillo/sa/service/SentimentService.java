package tech.chillo.sa.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tech.chillo.sa.dto.SentimentDTO;
import tech.chillo.sa.entites.Client;
import tech.chillo.sa.entites.Sentiment;
import tech.chillo.sa.enums.TypeSentiment;
import tech.chillo.sa.repository.SentimentRepository;

import java.util.List;
import java.util.Optional;


@Service
public class SentimentService {

    private ClientService clientService;
    private SentimentRepository sentimentRepository;

    public SentimentService(ClientService clientService, SentimentRepository sentimentRepository) {
        this.clientService = clientService;
        this.sentimentRepository = sentimentRepository;
    }

    public void creer(Sentiment sentiment) {
        Client client = this.clientService.lireOuCreer(sentiment.getClient());
        sentiment.setClient(client);

        // Analyse
        if (sentiment.getTexte().contains("pas")) {
            sentiment.setType(TypeSentiment.NEGATIF);
        } else {
            sentiment.setType(TypeSentiment.POSITIF);
        }
        this.sentimentRepository.save(sentiment);
    }

    public List<SentimentDTO> rechercher(TypeSentiment type) {
        List<Sentiment> sentiments;
        if(type == null) {
            sentiments = this.sentimentRepository.findAll();
        } else {
            sentiments = this.sentimentRepository.findByType(type);
        }
        return sentiments.stream().map(SentimentDTO::fromSentiment).toList();
    }

    public void supprimer(int id) {
        this.sentimentRepository.deleteById(id);
    }

    public Sentiment lire(int id) {
        Optional<Sentiment> optionalSentiment = this.sentimentRepository.findById(id);
        return optionalSentiment.orElseThrow(
                () -> new EntityNotFoundException("Aucun sentiment n'existe avec cet id")
        );
    }
}
