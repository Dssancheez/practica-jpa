package es.fpsumma.dam2.videoclub.persistence.jpa.repository;

import es.fpsumma.dam2.videoclub.persistence.jpa.entity.ActorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deberiaListarActoresOrdenadosPorNombre() {
        ActorEntity a1 = new ActorEntity(); a1.setNombre("Zoe Saldana");
        ActorEntity a2 = new ActorEntity(); a2.setNombre("Al Pacino");
        entityManager.persist(a1);
        entityManager.persist(a2);

        List<ActorEntity> actores = actorRepository.findAllByOrderByNombreAsc();

        assertThat(actores).hasSize(2);
        assertThat(actores.get(0).getNombre()).isEqualTo("Al Pacino");
    }

    @Test
    void deberiaBuscarPorContenidoIgnorandoMayusculas() {
        ActorEntity actor = new ActorEntity();
        actor.setNombre("Robert De Niro");
        entityManager.persist(actor);

        List<ActorEntity> resultado = actorRepository.findByNombreContainingIgnoreCase("niro");

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Robert De Niro");
    }
}
