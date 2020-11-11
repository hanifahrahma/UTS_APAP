package apap.Auts011806146972.repository;

import apap.Auts011806146972.model.AnggotaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnggotaDb extends JpaRepository<AnggotaModel, Long> {
    Optional<AnggotaModel> findById(Long id);
    Optional<AnggotaModel> findByNia(String nia);
}
