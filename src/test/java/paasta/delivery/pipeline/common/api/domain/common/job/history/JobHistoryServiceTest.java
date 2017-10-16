package paasta.delivery.pipeline.common.api.domain.common.job.history;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job.history
 *
 * @author REX
 * @version 1.0
 * @since 6 /29/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobHistoryServiceTest {

    private static final int JOB_HISTORY_ID = 1;
    private static final int JOB_ID = 1;
    private static final int PREVIOUS_JOB_NUMBER = 1;
    private static final int JOB_NUMBER = 1;
    private static final long TEST_DURATION = 1;
    private static final String TEST_STATUS = Constants.RESULT_STATUS_SUCCESS;
    private static final long FILE_ID = 1;
    private static final String TRIGGER_TYPE = "test-trigger-type";
    private static final String USER_ID = "test-user-id";
    private static final Date TEST_CREATED = new Date();
    private static final String TEST_CREATED_STRING = "test-created-string";
    private static final Date TEST_LAST_MODIFIED = new Date();
    private static final String TEST_LAST_MODIFIED_STRING = "test-last-modified-string";
    private static final String PREVIOUS_JOB_NAME = "test-previous-job-name";

    private static JobHistory gTestJobHistoryModel = null;
    private static JobHistory gTestResultJobHistoryModel = null;


    @Mock
    private JobHistoryRepository jobHistoryRepository;

    @InjectMocks
    private JobHistoryService jobHistoryService;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        gTestJobHistoryModel = new JobHistory();
        gTestResultJobHistoryModel = new JobHistory();

        gTestJobHistoryModel.setJobId(JOB_ID);
        gTestJobHistoryModel.setCreated(TEST_CREATED);
        gTestJobHistoryModel.setLastModified(TEST_LAST_MODIFIED);
        gTestJobHistoryModel.setCreatedString(TEST_CREATED_STRING);
        gTestJobHistoryModel.setLastModifiedString(TEST_LAST_MODIFIED_STRING);

        gTestResultJobHistoryModel.setId(JOB_HISTORY_ID);
        gTestResultJobHistoryModel.setJobId(JOB_ID);
        gTestResultJobHistoryModel.setPreviousJobNumber(PREVIOUS_JOB_NUMBER);
        gTestResultJobHistoryModel.setJobNumber(JOB_NUMBER);
        gTestResultJobHistoryModel.setDuration(TEST_DURATION);
        gTestResultJobHistoryModel.setStatus(TEST_STATUS);
        gTestResultJobHistoryModel.setFileId(FILE_ID);
        gTestResultJobHistoryModel.setTriggerType(TRIGGER_TYPE);
        gTestResultJobHistoryModel.setUserId(USER_ID);
        gTestResultJobHistoryModel.setCreated(gTestJobHistoryModel.getCreated());
        gTestResultJobHistoryModel.setLastModified(gTestJobHistoryModel.getLastModified());
        gTestResultJobHistoryModel.setCreatedString(gTestJobHistoryModel.getCreatedString());
        gTestResultJobHistoryModel.setLastModifiedString(gTestJobHistoryModel.getLastModifiedString());
        gTestResultJobHistoryModel.setPreviousJobName(PREVIOUS_JOB_NAME);
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
     * Gets job history list by job id valid return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobHistoryListByJobId_Valid_ReturnList() throws Exception {
        List<JobHistory> testList = new ArrayList<>();
        testList.add(gTestJobHistoryModel);

        Page<JobHistory> testPage = new PageImpl<>(testList);

        Pageable pageable = new Pageable() {
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


        when(jobHistoryRepository.findByJobId(pageable, JOB_ID)).thenReturn(testPage);


        // TEST
        List<JobHistory> resultList = jobHistoryService.getJobHistoryListByJobId(pageable, JOB_ID);

        assertEquals(testList, resultList);
        assertEquals(JOB_ID, resultList.get(0).getJobId());
    }


    /**
     * Gets first job history by job id and status valid phase 1 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getFirstJobHistoryByJobIdAndStatus_Valid_phase_1_ReturnModel() throws Exception {
        when(jobHistoryRepository.findFirstByJobIdOrderByCreatedDesc(JOB_HISTORY_ID)).thenReturn(gTestResultJobHistoryModel);


        // TEST
        JobHistory resultModel = jobHistoryService.getFirstJobHistoryByJobIdAndStatus(JOB_HISTORY_ID, Constants.EMPTY_VALUE);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_HISTORY_ID, resultModel.getId());
        assertEquals(JOB_ID, resultModel.getJobId());
        assertEquals(PREVIOUS_JOB_NUMBER, resultModel.getPreviousJobNumber());
        assertEquals(JOB_NUMBER, resultModel.getJobNumber());
        assertEquals(TEST_DURATION, resultModel.getDuration());
        assertEquals(TEST_STATUS, resultModel.getStatus());
        assertEquals(FILE_ID, resultModel.getFileId());
        assertEquals(TRIGGER_TYPE, resultModel.getTriggerType());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(TEST_CREATED, resultModel.getCreated());
        assertEquals(TEST_LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(PREVIOUS_JOB_NAME, resultModel.getPreviousJobName());
    }


    /**
     * Gets first job history by job id and status valid phase 2 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getFirstJobHistoryByJobIdAndStatus_Valid_phase_2_ReturnModel() throws Exception {
        when(jobHistoryRepository.findFirstByJobIdAndStatusOrderByCreatedDesc(JOB_HISTORY_ID, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gTestResultJobHistoryModel);


        // TEST
        JobHistory resultModel = jobHistoryService.getFirstJobHistoryByJobIdAndStatus(JOB_HISTORY_ID, Constants.RESULT_STATUS_SUCCESS);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_HISTORY_ID, resultModel.getId());
        assertEquals(JOB_ID, resultModel.getJobId());
    }


    /**
     * Gets first job history by job id and status valid phase 3 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getFirstJobHistoryByJobIdAndStatus_Valid_phase_3_ReturnModel() throws Exception {
        when(jobHistoryRepository.findFirstByJobIdAndStatusOrderByCreatedDesc(JOB_HISTORY_ID, Constants.RESULT_STATUS_SUCCESS)).thenReturn(null);


        // TEST
        JobHistory resultModel = jobHistoryService.getFirstJobHistoryByJobIdAndStatus(JOB_HISTORY_ID, Constants.RESULT_STATUS_SUCCESS);

        assertThat(resultModel).isNotNull();
    }


    /**
     * Gets job history by id valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobHistoryById_Valid_ReturnModel() throws Exception {
        when(jobHistoryRepository.findOne(Long.valueOf(JOB_HISTORY_ID))).thenReturn(gTestResultJobHistoryModel);


        // TEST
        JobHistory resultModel = jobHistoryService.getJobHistoryById(JOB_HISTORY_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_HISTORY_ID, resultModel.getJobId());
    }


    /**
     * Create job history valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createJobHistory_ValidModel_ReturnModel() throws Exception {
        when(jobHistoryRepository.save(gTestJobHistoryModel)).thenReturn(gTestResultJobHistoryModel);


        // TEST
        JobHistory resultModel = jobHistoryService.createJobHistory(gTestJobHistoryModel);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_HISTORY_ID, resultModel.getJobId());
    }


    /**
     * Update job history valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateJobHistory_ValidModel_ReturnModel() throws Exception {
        gTestJobHistoryModel.setId(JOB_HISTORY_ID);

        when(jobHistoryRepository.save(gTestJobHistoryModel)).thenReturn(gTestResultJobHistoryModel);


        // TEST
        JobHistory resultModel = jobHistoryService.updateJobHistory(gTestJobHistoryModel);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_HISTORY_ID, resultModel.getJobId());
    }


    /**
     * Delete job history by job id valid return string.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteJobHistoryByJobId_Valid_ReturnString() throws Exception {
        doNothing().when(jobHistoryRepository).deleteByJobId(JOB_ID);


        // TEST
        String resultString = jobHistoryService.deleteJobHistoryByJobId(JOB_ID);

        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }

}