package paasta.delivery.pipeline.common.api.domain.common.qualityGate;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doNothing;

/**
 * Created by kim on 2017-10-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QualityGateServiceTest {

    private static final long ID = 1L;
    private static final String GATE_DEFAULT_YN = "N";
    private static final String SERVICE_INSTANCES_ID = "test-serviceInstancesId";
    private static final int QUALITY_GATE_ID = 1;
    private static final String QUALITY_GATE_NAME = "test-quality-gate-name";
    private static final Date TEST_CREATED = new Date();
    private static final Date TEST_LAST_MODIFIED = new Date();
    private static final String CREATED_STRING = "test-created-string";
    private static final String LAST_MODIFIED_STRING = "last_modified_string";
    private static final List<Long> PROJECT_ID_LIST = new ArrayList<>();

    private static QualityGate testModel = null;
    private static QualityGate resultModel = null;

    private static List<QualityGate> testResultList = null;

    @Mock
    private QualityGateRepository qualityGateRepository;

    @InjectMocks
    private QualityGateService qualityGateService;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        testModel = new QualityGate();
        resultModel = new QualityGate();
        testResultList = new ArrayList<>();

        testModel.setId(ID);
        testModel.setGateDefaultYn(GATE_DEFAULT_YN);
        testModel.setServiceInstancesId(SERVICE_INSTANCES_ID);
        testModel.setQualityGateId(QUALITY_GATE_ID);
        testModel.setQualityGateName(QUALITY_GATE_NAME);
        testModel.setCreated(TEST_CREATED);
        testModel.setLastModified(TEST_LAST_MODIFIED);
        testModel.setCreatedString(CREATED_STRING);
        testModel.setLastModifiedString(LAST_MODIFIED_STRING);
        testModel.setProjectIdList(PROJECT_ID_LIST);

        resultModel.setId(testModel.getId());
        resultModel.setGateDefaultYn(testModel.getGateDefaultYn());
        resultModel.setServiceInstancesId(testModel.getServiceInstancesId());
        resultModel.setQualityGateId(testModel.getQualityGateId());
        resultModel.setQualityGateName(testModel.getQualityGateName());
        resultModel.setCreated(testModel.getCreated());
        resultModel.setCreatedString(testModel.getCreatedString());
        resultModel.setLastModified(testModel.getLastModified());
        resultModel.setLastModifiedString(testModel.getLastModifiedString());
        resultModel.setProjectIdList(testModel.getProjectIdList());

        testResultList.add(resultModel);
    }


    /**
     * Get QualityGate  Id valid return model.
     *
     * @throws Exception the exception
     */
//    @Test
//    public void getQualityGate_Valid_Return() throws Exception {
//        when(qualityGateRepository.findOne(ID)).thenReturn(resultModel);
//
//        QualityGate result = qualityGateService.getQualityGate(ID);
//        assertThat(result).isNotNull();
//        assertEquals(ID, result.getId());
//    }
//
//
//    /**
//     * Get QualityGateList  model valid return List.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void getQualityGateList_ReturnList() throws Exception {
//        testModel.setGateDefaultYn("Y");
//        when(qualityGateRepository.findByserviceInstancesIdOrGateDefaultYn(SERVICE_INSTANCES_ID, testModel.getGateDefaultYn())).thenReturn(testResultList);
//
//        List<QualityGate> resultList = qualityGateService.getQualityGateList(SERVICE_INSTANCES_ID);
//
//        assertThat(resultList).isNotNull();
//        assertEquals(testResultList, resultList);
//        assertEquals(SERVICE_INSTANCES_ID, resultList.get(0).getServiceInstancesId());
//    }
//
//
//    /**
//     * Create QualityGate  model valid return model.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void createQualityGate_Valid_Return() throws Exception {
//        when(qualityGateRepository.save(testModel)).thenReturn(resultModel);
//        QualityGate result = qualityGateService.createQualityGate(testModel);
//        assertThat(result).isNotNull();
//        assertEquals(ID, result.getId());
//    }
//
//
//    /**
//     * Copy QualityGate  model valid return model.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void copyQualityGate_Valid_Return() throws Exception {
//        when(qualityGateRepository.save(testModel)).thenReturn(resultModel);
//
//        QualityGate result = qualityGateService.copyQualityGate(testModel);
//        assertThat(result).isNotNull();
//        assertEquals(ID, result.getId());
//    }
//
//
//    /**
//     * Update QualityGate  model valid return model.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void updateQualityGate_Valid_Return() throws Exception {
//        when(qualityGateRepository.save(testModel)).thenReturn(resultModel);
//
//        QualityGate result = qualityGateService.updateQualityGate(testModel);
//        assertThat(result).isNotNull();
//        assertEquals(ID, result.getId());
//    }
//
//
//    /**
//     * Delete QualityGate  model valid return String.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void deleteQualityGate_Valid_ReturnString() throws Exception {
//        doNothing().when(qualityGateRepository).delete(testModel.getId());
//
//        String resultString = qualityGateService.deleteQualityGate(testModel);
//        assertThat(resultString).isNotNull();
//        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
//    }

}
