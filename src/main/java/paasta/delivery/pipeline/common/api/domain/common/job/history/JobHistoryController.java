package paasta.delivery.pipeline.common.api.domain.common.job.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job.history
 *
 * @author REX
 * @version 1.0
 * @since 6 /29/2017
 */
@RestController
public class JobHistoryController {

    private static final int PAGE_SIZE = 1000;
    private final JobHistoryService jobHistoryService;


    /**
     * Instantiates a new Job history controller.
     *
     * @param jobHistoryService the job history service
     */
    @Autowired
    public JobHistoryController(JobHistoryService jobHistoryService) {this.jobHistoryService = jobHistoryService;}


    /**
     * Gets job history list by job id.
     *
     * @param pageable the pageable
     * @param jobId    the job id
     * @return the job history list by job id
     */
    @GetMapping(value = "/jobs/{jobId:.+}/histories")
    public List<JobHistory> getJobHistoryListByJobId(@PageableDefault(sort = "created", direction = Sort.Direction.DESC, size = PAGE_SIZE) Pageable pageable,
                                                     @PathVariable("jobId") int jobId) {
        return jobHistoryService.getJobHistoryListByJobId(pageable, jobId);
    }


    /**
     * Gets job history by id and status.
     *
     * @param jobId  the job id
     * @param status the status
     * @return the job history by id and status
     */
    @GetMapping(value = "/jobs/{jobId:.+}/histories/status/{status:.+}/first")
    public JobHistory getJobHistoryByIdAndStatus(@PathVariable("jobId") int jobId, @PathVariable("status") String status) {
        return jobHistoryService.getFirstJobHistoryByJobIdAndStatus(jobId, status);
    }


    /**
     * Gets job history by id.
     *
     * @param id the id
     * @return the job history by id
     */
    @GetMapping(value = "/job-histories/{id:.+}")
    public JobHistory getJobHistoryById(@PathVariable("id") int id) {
        return jobHistoryService.getJobHistoryById(id);
    }


    /**
     * Create job history job history.
     *
     * @param jobHistory the job history
     * @return the job history
     */
    @PostMapping(value = "/job-histories")
    public JobHistory createJobHistory(@RequestBody JobHistory jobHistory) {
        return jobHistoryService.createJobHistory(jobHistory);
    }


    /**
     * Update job history job history.
     *
     * @param jobHistory the job history
     * @return the job history
     */
    @PutMapping(value = "/job-histories")
    public JobHistory updateJobHistory(@RequestBody JobHistory jobHistory) {
        return jobHistoryService.updateJobHistory(jobHistory);
    }


    /**
     * Delete job history by job id string.
     *
     * @param jobId the job id
     * @return the string
     */
    @DeleteMapping(value = "/jobs/{jobId:.+}/histories")
    public String deleteJobHistoryByJobId(@PathVariable("jobId") int jobId) {
        return jobHistoryService.deleteJobHistoryByJobId(jobId);
    }

}
