package paasta.delivery.pipeline.common.api.domain.common.job;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job
 *
 * @author REX
 * @version 1.0
 * @since 5 /17/2017
 */
@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Long> {


    /**
     * Find all by pipeline id order by created desc list.
     *
     * @param pageable   the pageable
     * @param pipelineId the pipeline id
     * @return the list
     */
    List<Job> findAllByPipelineId(Pageable pageable, int pipelineId);


    /**
     * Find all by service instances id and pipeline id order by group order asc job order asc list.
     *
     * @param pipelineId the pipeline id
     * @return the list
     */
    List<Job> findAllByPipelineIdOrderByGroupOrderAscJobOrderAsc(int pipelineId);


    /**
     * Find all by service instances id and pipeline id and job type list.
     *
     * @param pageable   the pageable
     * @param pipelineId the pipeline id
     * @param jobType    the job type
     * @return the list
     */
    List<Job> findAllByPipelineIdAndJobTypeOrderByJobOrderAsc(Pageable pageable, int pipelineId, String jobType);


    /**
     * Find all by job type order by job order asc list.
     *
     * @param jobType the job type
     * @return the list
     */
    List<Job> findAllByJobTypeOrderByJobOrderAsc(String jobType);


    /**
     * Count by pipeline id and job name int.
     *
     * @param pipelineId the pipeline id
     * @param jobName    the job name
     * @return the int
     */
    int countByPipelineIdAndJobName(int pipelineId, String jobName);


    /**
     * Find distinct top by pipeline id job.
     *
     * @param pipelineId the pipeline id
     * @return the job
     */
    Job findDistinctTopByPipelineId(int pipelineId);

}
