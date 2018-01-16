package paasta.delivery.pipeline.common.api.domain.common.cf.info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.cfInfo
 *
 * @author REX
 * @version 1.0
 * @since 7 /25/2017
 */
@Repository
@Transactional
public interface CfInfoRepository extends JpaRepository<CfInfo, Long> {

    /**
     * Find all by service instances id page.
     *
     * @param pageable           the pageable
     * @param serviceInstancesId the service instances id
     * @return the page
     */
    Page<CfInfo> findAllByServiceInstancesId(Pageable pageable, String serviceInstancesId);


    /**
     * Find all by service instances id and cf name containing page.
     *
     * @param pageable           the pageable
     * @param serviceInstancesId the service instances id
     * @param cfName             the cf name
     * @return the page
     */
    Page<CfInfo> findAllByServiceInstancesIdAndCfNameContaining(Pageable pageable, String serviceInstancesId, String cfName);


    /**
     * Count by service instances id and cf name int.
     *
     * @param serviceInstancesId the service instances id
     * @param cfName             the cf name
     * @return the int
     */
    int countByServiceInstancesIdAndCfName(String serviceInstancesId, String cfName);

    List<CfInfo> findByServiceInstancesId(String id);

    void deleteCfInoById(long id);
}
