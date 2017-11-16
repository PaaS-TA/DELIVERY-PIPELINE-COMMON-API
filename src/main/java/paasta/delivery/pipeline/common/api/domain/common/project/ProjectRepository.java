package paasta.delivery.pipeline.common.api.domain.common.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrjin on 2017-06-23.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByserviceInstancesId(String serviceInstancesId);


    /**
     * Find all by service instances id and quality profile id  order by  list.
     *
     * @param serviceInstancesId , qualityProfileId
     * @return the list
     */
    List<Project> findByServiceInstancesIdAndQualityProfileKey(String serviceInstancesId, String qualityProfileKey);


    /**
     * Find all by service instances id and quality gate id  order by  list.
     *
     * @param serviceInstancesId , qualityGateId
     * @return the list
     */
    List<Project> findByServiceInstancesIdAndQualityGateId(String serviceInstancesId, int qualityGateId);


    /**
     * Find all by service instances id and pipeline id order by  project.
     *
     * @param serviceInstancesId , pipelineId, jobId
     * @return the Project
     */
    List<Project> findByserviceInstancesIdAndPipelineId(String serviceInstancesId, int pipelineId);


}
