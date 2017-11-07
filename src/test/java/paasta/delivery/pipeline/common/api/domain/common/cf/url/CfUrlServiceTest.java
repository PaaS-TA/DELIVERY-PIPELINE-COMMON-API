package paasta.delivery.pipeline.common.api.domain.common.cf.url;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.CommonService;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.cf.url
 *
 * @author REX
 * @version 1.0
 * @since 11 /7/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CfUrlServiceTest {

    private static final long CF_URL_ID = 1L;
    private static final String SERVICE_INSTANCES_ID = "test-service-instances-id";
    private static final String CF_API_NAME = "test-cf-api-name";
    private static final String CF_API_URL = "test-cf-api-url";
    private static final String USER_ID = "test-user-id";
    private static final Date TEST_CREATED = new Date();
    private static final String TEST_CREATED_STRING = "test-created-string";
    private static final Date TEST_LAST_MODIFIED = new Date();
    private static final String TEST_LAST_MODIFIED_STRING = "test-last-modified-string";

    private static CfUrl gTestCfUrlModel = null;
    private static CfUrl gTestResultCfUrlModel = null;
    private static List<CfUrl> gTestResultList = null;

    @Mock
    private CfUrlRepository cfUrlRepository;

    @Mock
    private CommonService commonService;

    @InjectMocks
    private CfUrlService cfUrlService;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {

        gTestCfUrlModel = new CfUrl();
        gTestResultCfUrlModel = new CfUrl();
        gTestResultList = new ArrayList<>();

        gTestCfUrlModel.setServiceInstancesId(SERVICE_INSTANCES_ID);
        gTestCfUrlModel.setCfApiName(CF_API_NAME);
        gTestCfUrlModel.setCfApiUrl(CF_API_URL);
        gTestCfUrlModel.setUserId(USER_ID);
        gTestCfUrlModel.setCreated(TEST_CREATED);
        gTestCfUrlModel.setLastModified(TEST_LAST_MODIFIED);
        gTestCfUrlModel.setCreatedString(TEST_CREATED_STRING);
        gTestCfUrlModel.setLastModifiedString(TEST_LAST_MODIFIED_STRING);

        gTestResultCfUrlModel.setId(CF_URL_ID);
        gTestResultCfUrlModel.setServiceInstancesId(SERVICE_INSTANCES_ID);
        gTestResultCfUrlModel.setCfApiName(CF_API_NAME);
        gTestResultCfUrlModel.setCfApiUrl(CF_API_URL);
        gTestResultCfUrlModel.setUserId(USER_ID);
        gTestResultCfUrlModel.setCreated(null);
        gTestResultCfUrlModel.setLastModified(null);
        gTestResultCfUrlModel.setCreatedString(TEST_CREATED_STRING);
        gTestResultCfUrlModel.setLastModifiedString(TEST_LAST_MODIFIED_STRING);

        gTestResultList.add(gTestResultCfUrlModel);

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// MethodName_StateUnderTest_ExpectedBehavior
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }


    /**
     * Gets cf url list valid return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfUrlList_Valid_ReturnList() throws Exception {
        when(cfUrlRepository.findAllByServiceInstancesIdOrderByCreatedDesc(SERVICE_INSTANCES_ID)).thenReturn(gTestResultList);


        // TEST
        List<CfUrl> resultList = cfUrlService.getCfUrlList(SERVICE_INSTANCES_ID);

        assertThat(resultList).isNotNull();
        assertEquals(gTestResultList, resultList);
        assertEquals(CF_URL_ID, resultList.get(0).getId());
        assertEquals(SERVICE_INSTANCES_ID, resultList.get(0).getServiceInstancesId());
        assertEquals(CF_API_NAME, resultList.get(0).getCfApiName());
        assertEquals(CF_API_URL, resultList.get(0).getCfApiUrl());
        assertEquals(USER_ID, resultList.get(0).getUserId());
        assertEquals(null, resultList.get(0).getCreated());
        assertEquals(null, resultList.get(0).getLastModified());
        assertEquals(null, resultList.get(0).getCreatedString());
        assertEquals(null, resultList.get(0).getLastModifiedString());
    }


    /**
     * Gets cf url by id valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfUrlById_Valid_ReturnModel() throws Exception {
        gTestResultCfUrlModel.setCreated(gTestCfUrlModel.getCreated());
        gTestResultCfUrlModel.setLastModified(gTestCfUrlModel.getLastModified());
        gTestResultCfUrlModel.setCreatedString(gTestCfUrlModel.getCreatedString());
        gTestResultCfUrlModel.setLastModifiedString(gTestCfUrlModel.getLastModifiedString());

        when(cfUrlRepository.findOne(CF_URL_ID)).thenReturn(gTestResultCfUrlModel);


        // TEST
        CfUrl resultModel = cfUrlService.getCfUrlById((int) CF_URL_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(TEST_CREATED, resultModel.getCreated());
        assertEquals(TEST_LAST_MODIFIED, resultModel.getLastModified());
    }


    /**
     * Create cf url valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createCfUrl_Valid_ReturnModel() throws Exception {
        when(cfUrlRepository.save(gTestCfUrlModel)).thenReturn(gTestResultCfUrlModel);


        // TEST
        CfUrl resultModel = cfUrlService.createCfUrl(gTestCfUrlModel);

        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultCfUrlModel, resultModel);
        assertEquals(CF_URL_ID, resultModel.getId());
    }


    /**
     * Update cf url valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateCfUrl_Valid_ReturnModel() throws Exception {
        gTestCfUrlModel.setId(CF_URL_ID);


        when(cfUrlRepository.save(gTestCfUrlModel)).thenReturn(gTestResultCfUrlModel);


        // TEST
        CfUrl resultModel = cfUrlService.updateCfUrl(gTestCfUrlModel);

        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultCfUrlModel, resultModel);
        assertEquals(CF_URL_ID, resultModel.getId());
    }


    /**
     * Delete cf ino by id valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteCfInoById_Valid_ReturnModel() throws Exception {
        doNothing().when(cfUrlRepository).delete(CF_URL_ID);


        // TEST
        String resultString = cfUrlService.deleteCfInoById((int) CF_URL_ID);

        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }

}
