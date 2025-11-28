package org.simplecash.projet_fall_ndeyefatou.repository;

import org.junit.jupiter.api.Test;
import org.simplecash.projet_fall_ndeyefatou.entity.ClientEntity;
import org.simplecash.projet_fall_ndeyefatou.entity.ConseillerEntity;
import org.simplecash.projet_fall_ndeyefatou.entity.CompteCourantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RelationJpaTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ConseillerRepository conseillerRepository;

    @Autowired
    private CompteCourantRepository compteCourantRepository;


    @Test
    void testConseillerClientRelation() {
        // créer conseiller
        var conseiller = new ConseillerEntity("Alice", "alice@mail.com", "0102030405");
        conseiller = conseillerRepository.save(conseiller);

        // créer client
        var client = new ClientEntity("Bob", "Doe", "Rue X", "0600000000", "75000", "Paris");
        client.setConseiller(conseiller);
        client = clientRepository.save(client);

        // vérifier côté client
        assertThat(client.getConseiller()).isNotNull();
        assertThat(client.getConseiller().getId()).isEqualTo(conseiller.getId());
    }


    @Test
    void testClientCompteCourantRelation() {
        // créer client
        var client = new ClientEntity("Bob", "Doe", "Rue X", "0600000000", "75000", "Paris");
        client = clientRepository.save(client);

        // créer compte courant
        var compte = new CompteCourantEntity(client);
        compte = compteCourantRepository.save(compte);

        // vérifier relation
        var found = compteCourantRepository.findByClientId(client.getId())
                .orElseThrow();

        assertThat(found.getClient().getId()).isEqualTo(client.getId());
        assertThat(found.getSolde()).isEqualTo(0.0);
    }
}
