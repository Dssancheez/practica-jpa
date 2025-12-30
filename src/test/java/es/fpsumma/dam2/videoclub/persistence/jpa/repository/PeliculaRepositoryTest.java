package es.fpsumma.dam2.videoclub.persistence.jpa.repository;

import es.fpsumma.dam2.videoclub.persistence.jpa.entity.DirectorEntity;
import es.fpsumma.dam2.videoclub.persistence.jpa.entity.PeliculaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PeliculaRepositoryTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deberiaEncontrarPeliculaPorTitulo() {
        DirectorEntity director = new DirectorEntity();
        director.setNombre("Christopher Nolan");
        entityManager.persist(director);

        PeliculaEntity pelicula = new PeliculaEntity("Inception", "Sci-Fi", 2010, 8.8, director);
        entityManager.persist(pelicula);

        Optional<PeliculaEntity> encontrada = peliculaRepository.findByTitulo("Inception");

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getTitulo()).isEqualTo("Inception");
    }

    @Test
    void deberiaFiltrarPorPuntuacion() {
        DirectorEntity director = new DirectorEntity();
        director.setNombre("Test Director");
        entityManager.persist(director);

        entityManager.persist(new PeliculaEntity("Mala", "Accion", 2020, 4.0, director));
        entityManager.persist(new PeliculaEntity("Buena", "Drama", 2021, 9.0, director));

        List<PeliculaEntity> top = peliculaRepository.findByPuntuacionGreaterThanEqual(8.0);

        assertThat(top).hasSize(1);
        assertThat(top.get(0).getTitulo()).isEqualTo("Buena");
    }
}