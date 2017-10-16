package paasta.delivery.pipeline.common.api.domain.common.job.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job.history
 *
 * @author REX
 * @version 1.0
 * @since 6 /29/2017
 */
@Service
public class JobHistoryService {

    private final JobHistoryRepository jobHistoryRepository;


    /**
     * Instantiates a new Job history service.
     *
     * @param jobHistoryRepository the job history repository
     */
    @Autowired
    public JobHistoryService(JobHistoryRepository jobHistoryRepository) {this.jobHistoryRepository = jobHistoryRepository;}


    /**
     * Gets job history list by job id.
     *
     * @param pageable the pageable
     * @param jobId    the job id
     * @return the job history list by job id
     */
    List<JobHistory> getJobHistoryListByJobId(Pageable pageable, int jobId) {
        return jobHistoryRepository.findByJobId(pageable, jobId).getContent();
    }


    /**
     * Gets first job history by job id and status.
     *
     * @param jobId  the job id
     * @param status the status
     * @return the first job history by job id and status
     */
    JobHistory getFirstJobHistoryByJobIdAndStatus(int jobId, String status) {
        JobHistory jobHistory;
        JobHistory resultModel = new JobHistory();

        if (Constants.EMPTY_VALUE.equals(status)) {
            jobHistory = jobHistoryRepository.findFirstByJobIdOrderByCreatedDesc(jobId);
        } else {
            jobHistory = jobHistoryRepository.findFirstByJobIdAndStatusOrderByCreatedDesc(jobId, status);
        }

        if (jobHistory != null) {
            resultModel = jobHistory;
        }

        return resultModel;
    }


    /**
     * Gets job history.
     *
     * @param id the id
     * @return the job history
     */
    JobHistory getJobHistoryById(int id) {
        return jobHistoryRepository.findOne(Long.valueOf(id));
    }


    /**
     * Create job history custom job history.
     *
     * @param jobHistory the custom job history
     * @return the custom job history
     */
    JobHistory createJobHistory(JobHistory jobHistory) {
        return jobHistoryRepository.save(jobHistory);
    }


    /**
     * Update job history custom job history.
     *
     * @param jobHistory the custom job history
     * @return the custom job history
     */
    JobHistory updateJobHistory(JobHistory jobHistory) {
        return jobHistoryRepository.save(jobHistory);
    }


    /**
     * Delete job history by job id string.
     *
     * @param jobId the job id
     * @return the string
     */
    String deleteJobHistoryByJobId(int jobId) {
        jobHistoryRepository.deleteByJobId(jobId);
        return Constants.RESULT_STATUS_SUCCESS;
    }

}
