package paasta.delivery.pipeline.common.api.job;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.CommonService;
import paasta.delivery.pipeline.common.api.common.Constants;
import paasta.delivery.pipeline.common.api.domain.common.job.Job;
import paasta.delivery.pipeline.common.api.domain.common.job.JobRepository;
import paasta.delivery.pipeline.common.api.domain.common.job.JobService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doNothing;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job
 *
 * @author REX
 * @version 1.0
 * @since 5 /18/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobServiceTest {

    private static final int PIPELINE_ID = 1;
    private static final long JOB_ID = 1L;
    private static final String JOB_GUID = "test-job-" + UUID.randomUUID().toString();
    private static final String JOB_NAME = "test-job-name";
    private static final String ENCODED_STRING = "test-job-repository-password-encoded";

    private static Job gTestModel = null;
    private static Job gTestResultModel = null;
    private static List<Job> gTestResultList = null;


    @Mock
    private Pageable pageable;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private CommonService commonService;

    @InjectMocks
    private JobService jobService;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        gTestModel = new Job();
        gTestResultModel = new Job();
        gTestResultList = new ArrayList<>();

        gTestModel.setJobGuid(JOB_GUID);
        gTestModel.setJobName(JOB_NAME);
        gTestModel.setJobType(String.valueOf(Constants.JobType.BUILD));
        gTestModel.setLastGroupOrder("1");
        gTestModel.setRepositoryAccountPassword("test-job-repository-password");

        gTestResultModel.setId(JOB_ID);
        gTestResultModel.setJobGuid(JOB_GUID);
        gTestResultModel.setJobName(JOB_NAME);
        gTestResultModel.setJobType(String.valueOf(Constants.JobType.BUILD));
        gTestResultModel.setLastGroupOrder("1");
        gTestResultModel.setRepositoryAccountPassword("test-job-repository-password");

        gTestResultList.add(gTestModel);

        pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public int getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }


    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// MethodName_StateUnderTest_ExpectedBehavior
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Gets job list pageable valid return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobListPageable_Valid_ReturnList() throws Exception {
        when(jobRepository.findAllByPipelineIdAndJobTypeOrderByJobOrderAsc(pageable, PIPELINE_ID, String.valueOf(Constants.JobType.BUILD))).thenReturn(gTestResultList);


        // TEST
        List<Job> resultList = jobService.getJobListPageable(pageable, PIPELINE_ID, String.valueOf(Constants.JobType.BUILD));

        assertThat(resultList).isNotNull();
        assertEquals(gTestResultList, resultList);
        assertEquals(JOB_GUID, resultList.get(0).getJobGuid());
    }


    /**
     * Gets job list by pipeline id order by group order asc job order asc valid return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobListByPipelineIdOrderByGroupOrderAscJobOrderAsc_Valid_ReturnList() throws Exception {
        when(jobRepository.findAllByPipelineIdOrderByGroupOrderAscJobOrderAsc(PIPELINE_ID)).thenReturn(gTestResultList);


        // TEST
        List<Job> resultList = jobService.getJobListByPipelineIdOrderByGroupOrderAscJobOrderAsc(PIPELINE_ID);

        assertThat(resultList).isNotNull();
        assertEquals(gTestResultList, resultList);
        assertEquals(JOB_GUID, resultList.get(0).getJobGuid());
    }


    /**
     * Gets job by id valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobById_Valid_ReturnModel() throws Exception {
        when(jobRepository.findOne(JOB_ID)).thenReturn(gTestModel);


        // TEST
        Job resultModel = jobService.getJobById((int) JOB_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_GUID, resultModel.getJobGuid());
    }


    /**
     * Gets job count by job name valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobCountByJobName_ValidModel_ReturnInteger() throws Exception {
        when(jobRepository.countByPipelineIdAndJobName(PIPELINE_ID, JOB_NAME)).thenReturn(1);


        // TEST
        int resultCount = jobService.getJobCountByJobName(PIPELINE_ID, JOB_NAME);

        assertThat(resultCount).isNotNull();
        assertThat(resultCount).isGreaterThan(0);
    }


    /**
     * Gets job max group order by pipeline id valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobMaxGroupOrderByPipelineId_ValidModel_ReturnInteger() throws Exception {
        when(jobRepository.findDistinctTopByPipelineId(PIPELINE_ID)).thenReturn(null);


        // TEST
        int resultCount = jobService.getJobMaxGroupOrderByPipelineId(PIPELINE_ID);

        assertThat(resultCount).isNotNull();
        assertThat(resultCount).isEqualTo(0);
    }


    /**
     * Create job valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createJob_ValidModel_ReturnModel() throws Exception {
        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestModel.getRepositoryAccountPassword())).thenReturn(ENCODED_STRING);
        when(jobRepository.save(gTestModel)).thenReturn(gTestResultModel);


        // TEST
        Job resultModel = jobService.createJob(gTestModel);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_GUID, resultModel.getJobGuid());
        assertEquals(JOB_NAME, resultModel.getJobName());
    }


    /**
     * Update job valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateJob_ValidModel_ReturnModel() throws Exception {
        gTestModel.setId(JOB_ID);

        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestModel.getRepositoryAccountPassword())).thenReturn(ENCODED_STRING);
        when(jobRepository.save(gTestModel)).thenReturn(gTestResultModel);


        // TEST
        Job resultModel = jobService.updateJob(gTestModel);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_ID, resultModel.getId());
        assertEquals(JOB_GUID, resultModel.getJobGuid());
        assertEquals(JOB_NAME, resultModel.getJobName());
    }


    /**
     * Delete job valid return string.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteJob_Valid_ReturnString() throws Exception {
        doNothing().when(jobRepository).delete(JOB_ID);


        // TEST
        String resultString = jobService.deleteJob((int) JOB_ID);

        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }

}
