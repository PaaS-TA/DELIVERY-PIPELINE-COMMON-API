package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrjin on 2017-05-29.
 */
@Repository
public interface InstanceUseRepository extends JpaRepository<InstanceUse, Long> {
    InstanceUse findByServiceInstancesIdAndUserId(String serviceInstancesId, String userId);

    List<InstanceUse> findByServiceInstancesId(String serviceInstanceId);

    Page<InstanceUse> findByServiceInstancesIdAndUserNameContaining(String serviceInstanceId, String userName, Pageable pageable);

    Page<InstanceUse> findByServiceInstancesId(String serviceInstanceId, Pageable pageable);

    Page<InstanceUse> findByServiceInstancesIdAndUserNameContainingAndGrantedAuthorities_Authority_Id(String serviceInstanceId, String userName, String id, Pageable pageable);

    Page<InstanceUse> findByServiceInstancesIdAndGrantedAuthorities_Authority_Id(String serviceInstanceId, String authName, Pageable pageable);

    Page<InstanceUse> findAllByServiceInstancesIdAndGrantedAuthoritiesAuthCode(String suid, Long pipelineId, Pageable pageable);

    Page<InstanceUse> findAllByServiceInstancesIdAndUserNameContainingAndGrantedAuthoritiesAuthCode(String suid, String reqName, Long pipelineId, Pageable pageable);

    //List<InstanceUse> findAllByServiceInstancesIdAndGrantedAuthorities_Authority_AuthTypeNotAndGrantedAuthoritiesAuthCode(String suid, String authType, Long pipelineId);

    //List<InstanceUse> findAllByServiceInstancesIdAndGrantedAuthoritiesAuthCode(String suid, Long pipelineId);
}
