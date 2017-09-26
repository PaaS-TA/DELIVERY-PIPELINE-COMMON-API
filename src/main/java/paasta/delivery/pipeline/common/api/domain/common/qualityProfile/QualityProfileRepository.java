package paasta.delivery.pipeline.common.api.domain.common.qualityProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by hrjin on 2017-06-26.
 */

@Repository
public interface QualityProfileRepository extends JpaRepository<QualityProfile, Long> {

    /**
     * Find all by service instances id  order by  list.
     *
     * @param serviceInstancesId
     * @return the list
     */
    List<QualityProfile> findAllByserviceInstancesId(String serviceInstancesId);


    /**
     * Find all by service instances id and quality profile default order by  list.
     *
     * @param serviceInstancesId
     * @return the list
     */
    QualityProfile findByServiceInstancesIdAndQualityProfileDefault(String serviceInstancesId, int qualityProfileDefault);

}

