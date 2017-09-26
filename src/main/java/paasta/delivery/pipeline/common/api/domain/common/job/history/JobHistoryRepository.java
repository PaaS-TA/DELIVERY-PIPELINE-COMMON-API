package paasta.delivery.pipeline.common.api.domain.common.job.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job.history
 *
 * @author REX
 * @version 1.0
 * @since 6 /29/2017
 */
@Repository
@Transactional
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {

    /**
     * Find by job id list.
     *
     * @param pageable the pageable
     * @param jobId    the job id
     * @return the list
     */
    Page<JobHistory> findByJobId(Pageable pageable, int jobId);


    /**
     * Find first by job id order by created desc job history.
     *
     * @param jobId the job id
     * @return the job history
     */
    JobHistory findFirstByJobIdOrderByCreatedDesc(int jobId);


    /**
     * Find first by job id and status order by created desc job history.
     *
     * @param jobId  the job id
     * @param status the status
     * @return the job history
     */
    JobHistory findFirstByJobIdAndStatusOrderByCreatedDesc(int jobId, String status);


    /**
     * Delete by job id.
     *
     * @param jobId the job id
     */
    void deleteByJobId(int jobId);
}
