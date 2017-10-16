package paasta.delivery.pipeline.common.api.domain.common.job;

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

import java.util.ArrayList;
import java.util.Date;
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

    private static final String SERVICE_INSTANCES_ID = "test-service-instances-id";
    private static final int PIPELINE_ID = 1;
    private static final long JOB_ID = 1L;
    private static final String JOB_TYPE = String.valueOf(Constants.JobType.BUILD);
    private static final String JOB_NAME = "test-job-name";
    private static final String JOB_GUID = "test-job-" + UUID.randomUUID().toString();
    private static final int GROUP_ORDER = 1;
    private static final int JOB_ORDER = 1;
    private static final String BUILDER_TYPE = "test-builder-type";
    private static final int BUILD_JOB_ID = 1;
    private static final String JOB_TRIGGER = "test-";
    private static final String POST_ACTION_YN = "test-post-action-yn";
    private static final String REPOSITORY_TYPE = "test-repository-type";
    private static final String REPOSITORY_URL = "test-repository-url";
    private static final String REPOSITORY_ID = "test-repository-id";
    private static final String REPOSITORY_ACCOUNT_ID = "test-repository-account-id";
    private static final String REPOSITORY_ACCOUNT_PASSWORD = "test-repository-account-password";
    private static final String REPOSITORY_BRANCH = "test-repository_branch";
    private static final String REPOSITORY_COMMIT_REVISION = "test-repository-commit-revision";
    private static final int CF_INFO_ID = 1;
    private static final String CF_API_URL = "test-cf_api_url";
    private static final String APP_NAME = "test-app-name";
    private static final String APP_URL = "test-app-url";
    private static final String DEPLOY_TYPE = "test-deploy-type";
    private static final String BLUE_GREEN_DEPLOY_STATUS = "test-blue-green-deploy-status";
    private static final String DEPLOY_TARGET_ORG = "test-deploy-target-org";
    private static final String DEPLOY_TARGET_SPACE = "test-deploy-target-space";
    private static final String MANIFEST_USE_YN = "test-ynmanifest-use-yn";
    private static final String MANIFEST_SCRIPT = "test-manifest-script";
    private static final String INSPECTION_PROJECT_ID = "test-inspection-project-id";
    private static final String INSPECTION_PROJECT_NAME = "test-inspection-project-name";
    private static final String INSPECTION_PROJECT_KEY = "test-inspection-project-key";
    private static final String INSPECTION_PROFILE_ID = "test-inspection-profile-id";
    private static final String INSPECTION_GATE_ID = "test-inspection-gate-id";
    private static final String USER_ID = "test-user-id";
    private static final Date TEST_CREATED = new Date();
    private static final String TEST_CREATED_STRING = "test-created-string";
    private static final Date TEST_LAST_MODIFIED = new Date();
    private static final String TEST_LAST_MODIFIED_STRING = "test-last-modified-string";
    private static final String BUILD_JOB_NAME = "test-build-job-name";
    private static final String LAST_JOB_STATUS = "test-last-job-status";
    private static final String LAST_JOB_MODIFIED = "test-last-job-modified";
    private static final String LAST_SUCCESS_JOB_NUMBER = "test-last-success-job-number";
    private static final String LAST_GROUP_ORDER = "1";
    private static final String LAST_JOB_ORDER = "1";
    private static final String LAST_JOB_NUMBER = "1";
    private static final String PIPELINE_NAME = "test-pipeline-name";
    private static final String PREVIOUS_JOB_NUMBER_COUNT = "test-previous-job-number";
    private static final String ENCODED_STRING = "test-job-repository-password-encoded";
    private static final String DECODED_STRING = "test-cf-password-decoded";

    private static Job gTestJobModel = null;
    private static Job gTestResultJobModel = null;
    private static List<Job> gTestResultJobList = null;


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
        gTestJobModel = new Job();
        gTestResultJobModel = new Job();
        gTestResultJobList = new ArrayList<>();

        gTestJobModel.setJobGuid(JOB_GUID);
        gTestJobModel.setJobName(JOB_NAME);
        gTestJobModel.setJobType(String.valueOf(Constants.JobType.BUILD));
        gTestJobModel.setLastGroupOrder("1");
        gTestJobModel.setRepositoryAccountPassword("test-job-repository-password");
        gTestJobModel.setCreated(TEST_CREATED);
        gTestJobModel.setLastModified(TEST_LAST_MODIFIED);

        gTestResultJobModel.setServiceInstancesId(SERVICE_INSTANCES_ID);
        gTestResultJobModel.setPipelineId(PIPELINE_ID);
        gTestResultJobModel.setId(JOB_ID);
        gTestResultJobModel.setJobType(JOB_TYPE);
        gTestResultJobModel.setJobName(JOB_NAME);
        gTestResultJobModel.setJobGuid(JOB_GUID);
        gTestResultJobModel.setGroupOrder(GROUP_ORDER);
        gTestResultJobModel.setJobOrder(JOB_ORDER);
        gTestResultJobModel.setBuilderType(BUILDER_TYPE);
        gTestResultJobModel.setBuildJobId(BUILD_JOB_ID);
        gTestResultJobModel.setJobTrigger(JOB_TRIGGER);
        gTestResultJobModel.setPostActionYn(POST_ACTION_YN);
        gTestResultJobModel.setRepositoryType(REPOSITORY_TYPE);
        gTestResultJobModel.setRepositoryUrl(REPOSITORY_URL);
        gTestResultJobModel.setRepositoryId(REPOSITORY_ID);
        gTestResultJobModel.setRepositoryAccountId(REPOSITORY_ACCOUNT_ID);
        gTestResultJobModel.setRepositoryAccountPassword(REPOSITORY_ACCOUNT_PASSWORD);
        gTestResultJobModel.setRepositoryBranch(REPOSITORY_BRANCH);
        gTestResultJobModel.setRepositoryCommitRevision(REPOSITORY_COMMIT_REVISION);
        gTestResultJobModel.setCfInfoId(CF_INFO_ID);
        gTestResultJobModel.setCfApiUrl(CF_API_URL);
        gTestResultJobModel.setAppName(APP_NAME);
        gTestResultJobModel.setAppUrl(APP_URL);
        gTestResultJobModel.setDeployType(DEPLOY_TYPE);
        gTestResultJobModel.setBlueGreenDeployStatus(BLUE_GREEN_DEPLOY_STATUS);
        gTestResultJobModel.setDeployTargetOrg(DEPLOY_TARGET_ORG);
        gTestResultJobModel.setDeployTargetSpace(DEPLOY_TARGET_SPACE);
        gTestResultJobModel.setManifestUseYn(MANIFEST_USE_YN);
        gTestResultJobModel.setManifestScript(MANIFEST_SCRIPT);
        gTestResultJobModel.setInspectionProjectId(INSPECTION_PROJECT_ID);
        gTestResultJobModel.setInspectionProjectName(INSPECTION_PROJECT_NAME);
        gTestResultJobModel.setInspectionProjectKey(INSPECTION_PROJECT_KEY);
        gTestResultJobModel.setInspectionProfileId(INSPECTION_PROFILE_ID);
        gTestResultJobModel.setInspectionGateId(INSPECTION_GATE_ID);
        gTestResultJobModel.setUserId(USER_ID);
        gTestResultJobModel.setCreated(gTestJobModel.getCreated());
        gTestResultJobModel.setCreatedString(gTestJobModel.getCreatedString());
        gTestResultJobModel.setLastModified(gTestJobModel.getLastModified());
        gTestResultJobModel.setLastModifiedString(gTestJobModel.getLastModifiedString());
        gTestResultJobModel.setBuildJobName(BUILD_JOB_NAME);
        gTestResultJobModel.setLastJobStatus(LAST_JOB_STATUS);
        gTestResultJobModel.setLastJobModified(LAST_JOB_MODIFIED);
        gTestResultJobModel.setLastSuccessJobNumber(LAST_SUCCESS_JOB_NUMBER);
        gTestResultJobModel.setLastGroupOrder(LAST_GROUP_ORDER);
        gTestResultJobModel.setLastJobOrder(LAST_JOB_ORDER);
        gTestResultJobModel.setLastJobNumber(LAST_JOB_NUMBER);
        gTestResultJobModel.setPipelineName(PIPELINE_NAME);
        gTestResultJobModel.setPreviousJobNumberCount(PREVIOUS_JOB_NUMBER_COUNT);

        gTestResultJobList.add(gTestJobModel);

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
     * Gets job list pageable valid phase 1 return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobListPageable_Valid_phase_1_ReturnList() throws Exception {
        when(jobRepository.findAllByPipelineIdAndJobTypeOrderByJobOrderAsc(pageable, PIPELINE_ID, String.valueOf(Constants.JobType.BUILD))).thenReturn(gTestResultJobList);


        // TEST
        List<Job> resultList = jobService.getJobListPageable(pageable, PIPELINE_ID, String.valueOf(Constants.JobType.BUILD));

        assertThat(resultList).isNotNull();
        assertEquals(gTestResultJobList, resultList);
        assertEquals(JOB_GUID, resultList.get(0).getJobGuid());
    }


    /**
     * Gets job list pageable valid phase 2 return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobListPageable_Valid_phase_2_ReturnList() throws Exception {
        when(jobRepository.findAllByJobTypeOrderByJobOrderAsc(String.valueOf(Constants.JobType.BUILD))).thenReturn(gTestResultJobList);


        // TEST
        List<Job> resultList = jobService.getJobListPageable(pageable, 0, String.valueOf(Constants.JobType.BUILD));

        assertThat(resultList).isNotNull();
        assertEquals(gTestResultJobList, resultList);
        assertEquals(JOB_GUID, resultList.get(0).getJobGuid());
    }


    /**
     * Gets job list by pipeline id order by group order asc job order asc valid return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobListByPipelineIdOrderByGroupOrderAscJobOrderAsc_Valid_ReturnList() throws Exception {
        when(jobRepository.findAllByPipelineIdOrderByGroupOrderAscJobOrderAsc(PIPELINE_ID)).thenReturn(gTestResultJobList);


        // TEST
        List<Job> resultList = jobService.getJobListByPipelineIdOrderByGroupOrderAscJobOrderAsc(PIPELINE_ID);

        assertThat(resultList).isNotNull();
        assertEquals(gTestResultJobList, resultList);
        assertEquals(JOB_GUID, resultList.get(0).getJobGuid());
    }


    /**
     * Gets job by id valid phase 1 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobById_Valid_phase_1_ReturnModel() throws Exception {
        when(jobRepository.findOne(JOB_ID)).thenReturn(gTestResultJobModel);


        // TEST
        Job resultModel = jobService.getJobById((int) JOB_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(SERVICE_INSTANCES_ID, resultModel.getServiceInstancesId());
        assertEquals(PIPELINE_ID, resultModel.getPipelineId());
        assertEquals(JOB_ID, resultModel.getId());
        assertEquals(JOB_TYPE, resultModel.getJobType());
        assertEquals(JOB_NAME, resultModel.getJobName());
        assertEquals(JOB_GUID, resultModel.getJobGuid());
        assertEquals(GROUP_ORDER, resultModel.getGroupOrder());
        assertEquals(JOB_ORDER, resultModel.getJobOrder());
        assertEquals(BUILDER_TYPE, resultModel.getBuilderType());
        assertEquals(BUILD_JOB_ID, resultModel.getBuildJobId());
        assertEquals(JOB_TRIGGER, resultModel.getJobTrigger());
        assertEquals(POST_ACTION_YN, resultModel.getPostActionYn());
        assertEquals(REPOSITORY_TYPE, resultModel.getRepositoryType());
        assertEquals(REPOSITORY_URL, resultModel.getRepositoryUrl());
        assertEquals(REPOSITORY_ID, resultModel.getRepositoryId());
        assertEquals(REPOSITORY_ACCOUNT_ID, resultModel.getRepositoryAccountId());
        assertEquals(REPOSITORY_BRANCH, resultModel.getRepositoryBranch());
        assertEquals(REPOSITORY_COMMIT_REVISION, resultModel.getRepositoryCommitRevision());
        assertEquals(CF_INFO_ID, resultModel.getCfInfoId());
        assertEquals(CF_API_URL, resultModel.getCfApiUrl());
        assertEquals(APP_NAME, resultModel.getAppName());
        assertEquals(APP_URL, resultModel.getAppUrl());
        assertEquals(DEPLOY_TYPE, resultModel.getDeployType());
        assertEquals(BLUE_GREEN_DEPLOY_STATUS, resultModel.getBlueGreenDeployStatus());
        assertEquals(DEPLOY_TARGET_ORG, resultModel.getDeployTargetOrg());
        assertEquals(DEPLOY_TARGET_SPACE, resultModel.getDeployTargetSpace());
        assertEquals(MANIFEST_USE_YN, resultModel.getManifestUseYn());
        assertEquals(MANIFEST_SCRIPT, resultModel.getManifestScript());
        assertEquals(INSPECTION_PROJECT_ID, resultModel.getInspectionProjectId());
        assertEquals(INSPECTION_PROJECT_NAME, resultModel.getInspectionProjectName());
        assertEquals(INSPECTION_PROJECT_KEY, resultModel.getInspectionProjectKey());
        assertEquals(INSPECTION_PROFILE_ID, resultModel.getInspectionProfileId());
        assertEquals(INSPECTION_GATE_ID, resultModel.getInspectionGateId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(TEST_CREATED, resultModel.getCreated());
        assertEquals(TEST_LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(BUILD_JOB_NAME, resultModel.getBuildJobName());
        assertEquals(LAST_JOB_STATUS, resultModel.getLastJobStatus());
        assertEquals(LAST_JOB_MODIFIED, resultModel.getLastJobModified());
        assertEquals(LAST_SUCCESS_JOB_NUMBER, resultModel.getLastSuccessJobNumber());
        assertEquals(LAST_GROUP_ORDER, resultModel.getLastGroupOrder());
        assertEquals(LAST_JOB_ORDER, resultModel.getLastJobOrder());
        assertEquals(LAST_JOB_NUMBER, resultModel.getLastJobNumber());
        assertEquals(PIPELINE_NAME, resultModel.getPipelineName());
        assertEquals(PREVIOUS_JOB_NUMBER_COUNT, resultModel.getPreviousJobNumberCount());
    }


    /**
     * Gets job by id valid phase 2 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobById_Valid_phase_2_ReturnModel() throws Exception {
        gTestResultJobModel.setJobType(String.valueOf(Constants.JobType.DEPLOY));
        gTestResultJobModel.setLastSuccessJobNumber(null);
        gTestResultJobModel.setLastJobNumber(null);

        when(jobRepository.findOne(JOB_ID)).thenReturn(gTestResultJobModel);
        when(commonService.setPasswordByAES256(Constants.AES256Type.DECODE, gTestResultJobModel.getRepositoryAccountPassword())).thenReturn(DECODED_STRING);


        // TEST
        Job resultModel = jobService.getJobById((int) JOB_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_GUID, resultModel.getJobGuid());
        assertEquals("0", resultModel.getLastSuccessJobNumber());
        assertEquals("0", resultModel.getLastJobNumber());
    }


    /**
     * Gets job by id valid phase 3 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobById_Valid_phase_3_ReturnModel() throws Exception {
        when(jobRepository.findOne(JOB_ID)).thenReturn(null);


        // TEST
        Job resultModel = jobService.getJobById((int) JOB_ID);

        assertThat(resultModel).isNull();
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
     * Gets job max group order by pipeline id valid model phase 1 return integer.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobMaxGroupOrderByPipelineId_ValidModel_phase_1_ReturnInteger() throws Exception {
        when(jobRepository.findDistinctTopByPipelineId(PIPELINE_ID)).thenReturn(null);


        // TEST
        int resultCount = jobService.getJobMaxGroupOrderByPipelineId(PIPELINE_ID);

        assertThat(resultCount).isNotNull();
        assertThat(resultCount).isEqualTo(0);
    }


    /**
     * Gets job max group order by pipeline id valid model phase 2 return integer.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobMaxGroupOrderByPipelineId_ValidModel_phase_2_ReturnInteger() throws Exception {
        when(jobRepository.findDistinctTopByPipelineId(PIPELINE_ID)).thenReturn(gTestResultJobModel);


        // TEST
        int resultCount = jobService.getJobMaxGroupOrderByPipelineId(PIPELINE_ID);

        assertThat(resultCount).isNotNull();
        assertThat(resultCount).isEqualTo(1);
    }


    /**
     * Create job valid model phase 1 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createJob_ValidModel_phase_1_ReturnModel() throws Exception {
        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestJobModel.getRepositoryAccountPassword())).thenReturn(ENCODED_STRING);
        when(jobRepository.save(gTestJobModel)).thenReturn(gTestResultJobModel);


        // TEST
        Job resultModel = jobService.createJob(gTestJobModel);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_GUID, resultModel.getJobGuid());
        assertEquals(JOB_NAME, resultModel.getJobName());
    }


    /**
     * Create job valid model phase 2 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createJob_ValidModel_phase_2_ReturnModel() throws Exception {
        gTestJobModel.setJobType(String.valueOf(Constants.JobType.TEST));


        when(jobRepository.save(gTestJobModel)).thenReturn(gTestResultJobModel);


        // TEST
        Job resultModel = jobService.createJob(gTestJobModel);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_GUID, resultModel.getJobGuid());
        assertEquals(JOB_NAME, resultModel.getJobName());
    }


    /**
     * Update job valid model phase 1 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateJob_ValidModel_phase_1_ReturnModel() throws Exception {
        gTestJobModel.setId(JOB_ID);

        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestJobModel.getRepositoryAccountPassword())).thenReturn(ENCODED_STRING);
        when(jobRepository.save(gTestJobModel)).thenReturn(gTestResultJobModel);


        // TEST
        Job resultModel = jobService.updateJob(gTestJobModel);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_ID, resultModel.getId());
        assertEquals(JOB_GUID, resultModel.getJobGuid());
        assertEquals(JOB_NAME, resultModel.getJobName());
    }


    /**
     * Update job valid model phase 2 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateJob_ValidModel_phase_2_ReturnModel() throws Exception {
        gTestJobModel.setId(JOB_ID);
        gTestJobModel.setJobType(String.valueOf(Constants.JobType.TEST));


        when(jobRepository.save(gTestJobModel)).thenReturn(gTestResultJobModel);


        // TEST
        Job resultModel = jobService.updateJob(gTestJobModel);

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
