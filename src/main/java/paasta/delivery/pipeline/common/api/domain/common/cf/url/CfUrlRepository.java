package paasta.delivery.pipeline.common.api.domain.common.cf.url;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.cf.url
 *
 * @author REX
 * @version 1.0
 * @since 11 /6/2017
 */
@Repository
@Transactional
public interface CfUrlRepository extends JpaRepository<CfUrl, Long> {

    /**
     * Find all by service instances id order by created desc list.
     *
     * @param serviceInstancesId the service instances id
     * @return the list
     */
    List<CfUrl> findAllByServiceInstancesIdOrderByCreatedDesc(String serviceInstancesId);
}
