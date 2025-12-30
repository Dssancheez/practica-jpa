package es.fpsumma.dam2.videoclub.persistence.jpa.repository;

import es.fpsumma.dam2.videoclub.persistence.jpa.entity.DirectorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class DirectorRepositoryTest {

    @Autowired
    private DirectorRepository directorRepository;

    @Test
    void deberiaEncontrarDirectorPorNombre() {
        Optional<DirectorEntity> resultado = directorRepository.findByNombre("Christopher Nolan");
        assertThat(resultado).isPresent();
        DirectorEntity director = resultado.get();
        assertThat(director.getNombre()).isEqualTo("Christopher Nolan");
    }


    @Test
    void deberiaEncontrarDirectorPorNombreContainingIgnoreCase() {

        List<DirectorEntity> directores = directorRepository.findByNombreContainingIgnoreCase("Ridley");


        assertThat(directores).isNotEmpty();
        assertThat(directores.stream().anyMatch(d -> d.getNombre().contains("Ridley"))).isTrue();
    }

    @Test
    void deberiaEncontrarDirectorPorNombre_conQuery() {
        var resultado = directorRepository.buscarPorNombre("Christopher Nolan");
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Christopher Nolan");
    }

    @Test
    void noDeberiaEncontrarDirectorInexistente() {
        Optional<DirectorEntity> resultado = directorRepository.findByNombre("Director Inventado");
        assertThat(resultado).isEmpty();
    }
}
