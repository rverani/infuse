package br.com.infuse.api.repository;


import br.com.infuse.api.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface RequestRepository extends JpaRepository<RequestModel, UUID> {

    @Query(value = "SELECT * FROM request_tb WHERE id_client = :id", nativeQuery = true)
    Optional<List<RequestModel>> findByIdCliente(@Param("id") String id);

    @Query(value = "SELECT COUNT(id_control) FROM request_tb WHERE id_control = :id", nativeQuery = true)
    int findByIdControl(@Param("id") int id);

    @Query(value = "SELECT * FROM request_tb WHERE id_control = :id", nativeQuery = true)
    Optional<RequestModel> findByIdPedido(@Param("id") int id);

    @Query(value = "SELECT * FROM request_tb WHERE DATE(dt_cadastro) = :dt", nativeQuery = true)
    Optional<List<RequestModel>> findByDateCadastro(@Param("dt") LocalDate dt);

    @Query(value = "SELECT * FROM request_tb WHERE DATE(dt_cadastro) = :dt AND id_control = :id", nativeQuery = true)
    Optional<RequestModel> findByOneDateCadastro(@Param("dt") String dt, @Param("id") int id );

}

