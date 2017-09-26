package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hrjin on 2017-05-29.
 */
@Repository
public interface ServiceInstancesRepository extends JpaRepository<ServiceInstances, String> {
}
