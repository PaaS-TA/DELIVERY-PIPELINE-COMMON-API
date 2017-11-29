package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrjin on 2017-05-29.
 */
@Repository
public interface CiInfoRepository extends JpaRepository<CiInfo, Long> {

    List<CiInfo> findByStatusAndTypeOrderByUsedcount(String status, String type);

    List<CiInfo> findByTypeOrderByUsedcount(String type);

    CiInfo findByServerUrl(String serverUrl);

    List<CiInfo> findByServerUrlNotIn(List<String> serverUrls);
}
