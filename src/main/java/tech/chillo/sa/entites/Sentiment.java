package tech.chillo.sa.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tech.chillo.sa.enums.TypeSentiment;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
@Getter
@Setter
@Entity
@Table(name = "SENTIMENT")
public class Sentiment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String texte;
   private TypeSentiment type;

   @ManyToOne(cascade = {PERSIST, MERGE})
   @JoinColumn(name = "CLIENT_ID")
   private Client client;

   public Sentiment() {

   }

   public Sentiment(int id, String texte, TypeSentiment type, Client client) {
      this.id = id;
      this.texte = texte;
      this.type = type;
      this.client = client;
   }

}
