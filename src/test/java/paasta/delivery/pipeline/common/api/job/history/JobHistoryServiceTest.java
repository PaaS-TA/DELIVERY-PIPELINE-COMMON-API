package paasta.delivery.pipeline.common.api.job.history;

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
import paasta.delivery.pipeline.common.api.domain.common.job.history.JobHistory;
import paasta.delivery.pipeline.common.api.domain.common.job.history.JobHistoryRepository;
import paasta.delivery.pipeline.common.api.domain.common.job.history.JobHistoryService;

import java.util.ArrayList;
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

    private static JobHistory gTestModel = null;
    private static JobHistory gTestResultModel = null;


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
        gTestModel = new JobHistory();
        gTestResultModel = new JobHistory();

        gTestModel.setJobId(JOB_ID);

        gTestResultModel.setId(JOB_HISTORY_ID);
        gTestResultModel.setJobId(JOB_ID);
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
        testList.add(gTestModel);

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
     * Gets first job history by job id and status valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getFirstJobHistoryByJobIdAndStatus_Valid_ReturnModel() throws Exception {
        when(jobHistoryRepository.findFirstByJobIdOrderByCreatedDesc(JOB_HISTORY_ID)).thenReturn(gTestResultModel);


        // TEST
        JobHistory resultModel = jobHistoryService.getFirstJobHistoryByJobIdAndStatus(JOB_HISTORY_ID, Constants.EMPTY_VALUE);

        assertThat(resultModel).isNotNull();
        assertEquals(JOB_HISTORY_ID, resultModel.getId());
        assertEquals(JOB_ID, resultModel.getJobId());
    }


    /**
     * Gets job history by id valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getJobHistoryById_Valid_ReturnModel() throws Exception {
        when(jobHistoryRepository.findOne(Long.valueOf(JOB_HISTORY_ID))).thenReturn(gTestResultModel);


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
        when(jobHistoryRepository.save(gTestModel)).thenReturn(gTestResultModel);


        // TEST
        JobHistory resultModel = jobHistoryService.createJobHistory(gTestModel);

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
        gTestModel.setId(JOB_HISTORY_ID);

        when(jobHistoryRepository.save(gTestModel)).thenReturn(gTestResultModel);


        // TEST
        JobHistory resultModel = jobHistoryService.createJobHistory(gTestModel);

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