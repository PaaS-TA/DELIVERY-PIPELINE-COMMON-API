package paasta.delivery.pipeline.common.api.domain.common.pipeline;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017-05-16.
 */
@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long>, JpaSpecificationExecutor<Pipeline> {
    Page<Pipeline> findByServiceInstancesId(String suid, Pageable pageable);

    Page<Pipeline> findByServiceInstancesIdAndNameContaining(String suid, String reqName, Pageable pageable);
}
