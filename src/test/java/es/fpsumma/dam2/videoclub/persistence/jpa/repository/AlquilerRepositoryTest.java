package es.fpsumma.dam2.videoclub.persistence.jpa.repository;

import es.fpsumma.dam2.videoclub.persistence.jpa.entity.AlquilerEntity;
import es.fpsumma.dam2.videoclub.persistence.jpa.entity.ClienteEntity;
import es.fpsumma.dam2.videoclub.persistence.jpa.entity.DirectorEntity;
import es.fpsumma.dam2.videoclub.persistence.jpa.entity.PeliculaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlquilerRepositoryTest {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deberiaEncontrarAlquileresNoDevueltos() {
        DirectorEntity d = new DirectorEntity(); d.setNombre("Nolan");
        entityManager.persist(d);

        PeliculaEntity p = new PeliculaEntity("Tenet", "Sci-Fi", 2020, 7.5, d);
        entityManager.persist(p);

        ClienteEntity c = new ClienteEntity();
        c.setNombre("Ana");
        c.setEmail("ana@mail.com");
        entityManager.persist(c);

        AlquilerEntity alquiler = new AlquilerEntity();
        alquiler.setCliente(c);
        alquiler.setPelicula(p);
        alquiler.setFechaAlquiler(LocalDate.now());
        alquiler.setFechaDevolucion(null);
        entityManager.persist(alquiler);

        List<AlquilerEntity> pendientes = alquilerRepository.findByFechaDevolucionIsNull();

        assertThat(pendientes).hasSize(1);
    }
}