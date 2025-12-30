package es.fpsumma.dam2.videoclub.persistence.jpa.repository;

import es.fpsumma.dam2.videoclub.persistence.jpa.entity.ClienteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest

public class ClienteRepositoryTest {

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private TestEntityManager entityManager;

        @Test
        void deberiaEncontrarClientePorEmail() {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setNombre("Juan Perez");
            cliente.setEmail("juan@video.com");
            entityManager.persist(cliente);

            var encontrado = clienteRepository.findByEmail("juan@video.com");

            assertThat(encontrado).isPresent();
            assertThat(encontrado.get().getNombre()).isEqualTo("Juan Perez");
        }
    }

