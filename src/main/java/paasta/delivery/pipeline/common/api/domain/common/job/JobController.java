package paasta.delivery.pipeline.common.api.domain.common.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job
 *
 * @author REX
 * @version 1.0
 * @since 5 /17/2017
 */
@RestController
public class JobController {

    private static final String REQ_URL = "/jobs";
    private static final int PAGE_SIZE = 1000;
    private final JobService jobService;


    /**
     * Instantiates a new Job controller.
     *
     * @param jobService the job service
     */
    @Autowired
    public JobController(JobService jobService) {this.jobService = jobService;}


    /**
     * Gets job list.
     *
     * @param pipelineId the pipeline id
     * @return the job list
     */
    @RequestMapping(value = "/pipelines/{pipelineId:.+}" + REQ_URL, method = RequestMethod.GET)
    public List<Job> getJobList(@PathVariable("pipelineId") int pipelineId) {
        return jobService.getJobListByPipelineIdOrderByGroupOrderAscJobOrderAsc(pipelineId);
    }


    /**
     * Gets job list pageable.
     *
     * @param pageable   the pageable
     * @param pipelineId the pipeline id
     * @param jobType    the job type
     * @return the job list pageable
     */
    @RequestMapping(value = "/pipelines/{pipelineId:.+}" + REQ_URL + "/job-types/{jobType:.+}", method = RequestMethod.GET)
    public List<Job> getJobListPageable(@PageableDefault(sort = "created", direction = Sort.Direction.DESC, size = PAGE_SIZE) Pageable pageable,
                                        @PathVariable("pipelineId") int pipelineId,
                                        @PathVariable("jobType") String jobType) {
        return jobService.getJobListPageable(pageable, pipelineId, jobType);
    }


    /**
     * Gets job.
     *
     * @param id the id
     * @return the job
     */
    @RequestMapping(value = REQ_URL + "/{id:.+}", method = RequestMethod.GET)
    public Job getJob(@PathVariable("id") int id) {
        return jobService.getJobById(id);
    }


    /**
     * Gets job count by job name.
     *
     * @param pipelineId the pipeline id
     * @param jobName    the job name
     * @return the job count by job name
     */
    @RequestMapping(value = "/pipelines/{pipelineId:.+}/job-names/{jobName:.+}", method = RequestMethod.GET)
    public int getJobCountByJobName(@PathVariable("pipelineId") int pipelineId, @PathVariable("jobName") String jobName) {
        return jobService.getJobCountByJobName(pipelineId, jobName);
    }


    /**
     * Gets job max group order by pipeline id.
     *
     * @param pipelineId the pipeline id
     * @return the job max group order by pipeline id
     */
    @RequestMapping(value = "/pipelines/{pipelineId:.+}/max-job-group-order", method = RequestMethod.GET)
    public int getJobMaxGroupOrderByPipelineId(@PathVariable("pipelineId") int pipelineId) {
        return jobService.getJobMaxGroupOrderByPipelineId(pipelineId);
    }


    /**
     * Create job job.
     *
     * @param job the job
     * @return the job
     */
    @PostMapping(value = REQ_URL)
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }


    /**
     * Update job job.
     *
     * @param job the job
     * @return the job
     */
    @PutMapping(value = REQ_URL)
    public Job updateJob(@RequestBody Job job) {
        return jobService.updateJob(job);
    }


    /**
     * Delete job string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping(value = REQ_URL + "/{id:.+}")
    public String deleteJob(@PathVariable("id") int id) {
        return jobService.deleteJob(id);
    }

}
