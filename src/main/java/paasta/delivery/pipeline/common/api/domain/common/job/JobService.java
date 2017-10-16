package paasta.delivery.pipeline.common.api.domain.common.job;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.CommonService;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job
 *
 * @author REX
 * @version 1.0
 * @since 5 /17/2017
 */
@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CommonService commonService;


    /**
     * Instantiates a new Job service.
     *
     * @param jobRepository the job repository
     * @param commonService the common service
     */
    public JobService(JobRepository jobRepository, CommonService commonService) {
        this.jobRepository = jobRepository;
        this.commonService = commonService;
    }


    /**
     * Gets job list by pipeline id order by group order asc job order asc.
     *
     * @param pipelineId the pipeline id
     * @return the job list by pipeline id order by group order asc job order asc
     */
    List<Job> getJobListByPipelineIdOrderByGroupOrderAscJobOrderAsc(int pipelineId) {
        return jobRepository.findAllByPipelineIdOrderByGroupOrderAscJobOrderAsc(pipelineId);
    }


    /**
     * Gets job list pageable.
     *
     * @param pageable   the pageable
     * @param pipelineId the pipeline id
     * @param jobType    the job type
     * @return the job list pageable
     */
    List<Job> getJobListPageable(Pageable pageable, int pipelineId, String jobType) {
        List<Job> resultList;

        if (pipelineId > 0) {
            resultList = jobRepository.findAllByPipelineIdAndJobTypeOrderByJobOrderAsc(pageable, pipelineId, jobType);
        } else {
            resultList = jobRepository.findAllByJobTypeOrderByJobOrderAsc(jobType);
        }

        return resultList;
    }


    /**
     * Gets job by id.
     *
     * @param id the id
     * @return the job by id
     */
    Job getJobById(int id) {
        Job resultModel = jobRepository.findOne((long) id);
        String jobType;

        if (null != resultModel) {
            jobType = resultModel.getJobType();

            if (!String.valueOf(Constants.JobType.DEPLOY).equals(jobType)) {
                resultModel.setRepositoryAccountPassword(commonService.setPasswordByAES256(Constants.AES256Type.DECODE, resultModel.getRepositoryAccountPassword()));
            }
        }

        return resultModel;
    }


    /**
     * Gets job count by job name.
     *
     * @param pipelineId the pipeline id
     * @param jobName    the job name
     * @return the job count by job name
     */
    int getJobCountByJobName(int pipelineId, String jobName) {
        return jobRepository.countByPipelineIdAndJobName(pipelineId, jobName);
    }


    /**
     * Gets job max group order by pipeline id.
     *
     * @param pipelineId the pipeline id
     * @return the job max group order by pipeline id
     */
    int getJobMaxGroupOrderByPipelineId(int pipelineId) {
        Job tempModel = jobRepository.findDistinctTopByPipelineId(pipelineId);
        int resultCount = 0;

        if (null != tempModel) {
            resultCount = Integer.parseInt(tempModel.getLastGroupOrder());
        }

        return resultCount;
    }


    /**
     * Create job custom job.
     *
     * @param job the custom job
     * @return the custom job
     */
    Job createJob(Job job) {
        if (String.valueOf(Constants.JobType.BUILD).equals(job.getJobType())) {
            job.setRepositoryAccountPassword(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, job.getRepositoryAccountPassword()));
        }

        return jobRepository.save(job);
    }


    /**
     * Update job job.
     *
     * @param job the job
     * @return the job
     */
    Job updateJob(Job job) {
        if (String.valueOf(Constants.JobType.BUILD).equals(job.getJobType())) {
            job.setRepositoryAccountPassword(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, job.getRepositoryAccountPassword()));
        }

        return jobRepository.save(job);
    }


    /**
     * Delete job string.
     *
     * @param id the id
     * @return the string
     */
    String deleteJob(int id) {
        jobRepository.delete((long) id);
        return Constants.RESULT_STATUS_SUCCESS;
    }

}
