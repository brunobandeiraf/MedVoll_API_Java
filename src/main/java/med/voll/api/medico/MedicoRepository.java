package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Tipo da Entidade e o Tipo do atributo da chave primária
}
