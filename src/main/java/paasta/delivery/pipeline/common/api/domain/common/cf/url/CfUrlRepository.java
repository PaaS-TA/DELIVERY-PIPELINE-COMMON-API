package paasta.delivery.pipeline.common.api.domain.common.cf.url;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.cf.url
 *
 * @author REX
 * @version 1.0
 * @since 11/6/2017
 */
@Repository
@Transactional
public interface CfUrlRepository extends JpaRepository<CfUrl, Long> {

    Page<CfUrl> findAllByServiceInstancesId(Pageable pageable, String serviceInstancesId);
}
