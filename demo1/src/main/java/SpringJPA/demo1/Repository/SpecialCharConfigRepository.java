package SpringJPA.demo1.Repository;

import SpringJPA.demo1.Empty.SpecialCharConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpecialCharConfigRepository extends JpaRepository<SpecialCharConfig, Long>, JpaSpecificationExecutor<SpecialCharConfig> {

    @Query("SELECT s FROM SpecialCharConfig s WHERE s.clientName = :name")
    List<SpecialCharConfig> findClient(@Param("name") String name);

}
