package paasta.delivery.pipeline.common.api.domain.common.cfInfo;

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
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.cfInfo
 *
 * @author REX
 * @version 1.0
 * @since 8 /2/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CfInfoServiceTest {

    private static final long CF_ID = 1L;
    private static final int PAGE_COUNT = 9999;
    private static final int PAGE_SIZE = 1;
    private static final int TOTAL_PAGES = 1;
    private static final long TOTAL_ELEMENTS = 1;
    private static final String SERVICE_INSTANCES_ID = "test-service-instances-id";
    private static final String CF_INFO_ID = "test-cf-info-id";
    private static final String CF_INFO_NAME = "test-cf-info-name";
    private static final String CF_INFO_PASSWORD = "test-cf-info-password";
    private static final String CF_API_URL = "test-cf-api-url";
    private static final String TEST_DESCRIPTION = "test-description";
    private static final String USER_ID = "test-user-id";
    private static final Date TEST_CREATED = new Date();
    private static final String TEST_CREATED_STRING = "test-created-string";
    private static final Date TEST_LAST_MODIFIED = new Date();
    private static final String TEST_LAST_MODIFIED_STRING = "test-last-modified-string";
    private static final String ENCODED_STRING = "test-cf-password-encoded";
    private static final String DECODED_STRING = "test-cf-password-decoded";

    private static CfInfo gTestCfInfoModel = null;
    private static CfInfo gTestResultCfInfoModel = null;
    private static Pageable gTestPageable = null;
    private static CfInfoList gTestCfInfoList = null;
    private static List<CfInfo> gTestResultList = null;


    @Mock
    private CfInfoRepository cfInfoRepository;

    @Mock
    private CommonService commonService;

    @Mock
    private Page<CfInfo> cfInfoPage;

    @InjectMocks
    private CfInfoService cfInfoService;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        gTestPageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 1;
            }

            @Override
            public int getPageSize() {
                return 1;
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

        gTestCfInfoModel = new CfInfo();
        gTestResultCfInfoModel = new CfInfo();
        gTestCfInfoList = new CfInfoList();
        gTestResultList = new ArrayList<>();

        gTestCfInfoModel.setCfId(CF_INFO_ID);
        gTestCfInfoModel.setCfPassword(CF_INFO_PASSWORD);
        gTestCfInfoModel.setCreated(TEST_CREATED);
        gTestCfInfoModel.setLastModified(TEST_LAST_MODIFIED);
        gTestCfInfoModel.setCreatedString(TEST_CREATED_STRING);
        gTestCfInfoModel.setLastModifiedString(TEST_LAST_MODIFIED_STRING);

        gTestResultCfInfoModel.setId(CF_ID);
        gTestResultCfInfoModel.setServiceInstancesId(SERVICE_INSTANCES_ID);
        gTestResultCfInfoModel.setCfId(CF_INFO_ID);
        gTestResultCfInfoModel.setCfName(CF_INFO_NAME);
        gTestResultCfInfoModel.setCfPassword(CF_INFO_PASSWORD);
        gTestResultCfInfoModel.setCfApiUrl(CF_API_URL);
        gTestResultCfInfoModel.setDescription(TEST_DESCRIPTION);
        gTestResultCfInfoModel.setUserId(USER_ID);
        gTestResultCfInfoModel.setCreated(null);
        gTestResultCfInfoModel.setLastModified(null);
        gTestResultCfInfoModel.setCreatedString(TEST_CREATED_STRING);
        gTestResultCfInfoModel.setLastModifiedString(TEST_LAST_MODIFIED_STRING);

        gTestResultList.add(gTestCfInfoModel);

        cfInfoPage = new PageImpl<>(gTestResultList);

        gTestCfInfoList.setCfInfos(gTestResultList);
        gTestCfInfoList.setPage(PAGE_COUNT);
        gTestCfInfoList.setSize(PAGE_SIZE);
        gTestCfInfoList.setTotalPages(TOTAL_PAGES);
        gTestCfInfoList.setTotalElements(TOTAL_ELEMENTS);
        gTestCfInfoList.setLast(true);
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
     * Gets cf info list pageable valid phase 1 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoListPageable_Valid_phase_1_ReturnModel() throws Exception {
        when(cfInfoRepository.findAll(gTestPageable)).thenReturn(cfInfoPage);
        when(commonService.setPageInfo(cfInfoPage, CfInfoList.class)).thenReturn(gTestCfInfoList);


        // TEST
        CfInfoList resultList = cfInfoService.getCfInfoListPageable(gTestPageable, null, String.valueOf(CF_ID));

        assertThat(resultList).isNotNull();
        assertEquals(gTestCfInfoList, resultList);
        assertEquals(gTestResultList, resultList.getCfInfos());
        assertEquals(PAGE_COUNT, resultList.getPage());
        assertEquals(PAGE_SIZE, resultList.getSize());
        assertEquals(TOTAL_PAGES, resultList.getTotalPages());
        assertEquals(TOTAL_ELEMENTS, resultList.getTotalElements());
        assertEquals(true, resultList.isLast());
        assertEquals(CF_INFO_ID, resultList.getCfInfos().get(0).getCfId());
        assertEquals(CF_INFO_PASSWORD, resultList.getCfInfos().get(0).getCfPassword());
    }


    /**
     * Gets cf info list pageable valid phase 2 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoListPageable_Valid_phase_2_ReturnModel() throws Exception {
        when(cfInfoRepository.findAll(gTestPageable)).thenReturn(cfInfoPage);
        when(commonService.setPageInfo(cfInfoPage, CfInfoList.class)).thenReturn(gTestCfInfoList);


        // TEST
        CfInfoList resultList = cfInfoService.getCfInfoListPageable(gTestPageable, "", String.valueOf(CF_ID));

        assertThat(resultList).isNotNull();
        assertEquals(gTestCfInfoList, resultList);
        assertEquals(PAGE_COUNT, resultList.getPage());
        assertEquals(CF_INFO_ID, resultList.getCfInfos().get(0).getCfId());
        assertEquals(CF_INFO_PASSWORD, resultList.getCfInfos().get(0).getCfPassword());
    }


    /**
     * Gets cf info list pageable valid phase 3 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoListPageable_Valid_phase_3_ReturnModel() throws Exception {
        when(cfInfoRepository.findAllByServiceInstancesId(gTestPageable, SERVICE_INSTANCES_ID)).thenReturn(cfInfoPage);
        when(commonService.setPageInfo(cfInfoPage, CfInfoList.class)).thenReturn(gTestCfInfoList);


        // TEST
        CfInfoList resultList = cfInfoService.getCfInfoListPageable(gTestPageable, SERVICE_INSTANCES_ID, null);

        assertThat(resultList).isNotNull();
        assertEquals(gTestCfInfoList, resultList);
        assertEquals(PAGE_COUNT, resultList.getPage());
        assertEquals(CF_INFO_ID, resultList.getCfInfos().get(0).getCfId());
        assertEquals(CF_INFO_PASSWORD, resultList.getCfInfos().get(0).getCfPassword());
    }


    /**
     * Gets cf info list pageable valid phase 4 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoListPageable_Valid_phase_4_ReturnModel() throws Exception {
        when(cfInfoRepository.findAllByServiceInstancesId(gTestPageable, SERVICE_INSTANCES_ID)).thenReturn(cfInfoPage);
        when(commonService.setPageInfo(cfInfoPage, CfInfoList.class)).thenReturn(gTestCfInfoList);


        // TEST
        CfInfoList resultList = cfInfoService.getCfInfoListPageable(gTestPageable, SERVICE_INSTANCES_ID, "");

        assertThat(resultList).isNotNull();
        assertEquals(gTestCfInfoList, resultList);
        assertEquals(PAGE_COUNT, resultList.getPage());
        assertEquals(CF_INFO_ID, resultList.getCfInfos().get(0).getCfId());
        assertEquals(CF_INFO_PASSWORD, resultList.getCfInfos().get(0).getCfPassword());
    }


    /**
     * Gets cf info list pageable valid phase 5 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoListPageable_Valid_phase_5_ReturnModel() throws Exception {
        when(cfInfoRepository.findAllByServiceInstancesIdAndCfNameContaining(gTestPageable, SERVICE_INSTANCES_ID, CF_INFO_NAME)).thenReturn(cfInfoPage);
        when(commonService.setPageInfo(cfInfoPage, CfInfoList.class)).thenReturn(gTestCfInfoList);


        // TEST
        CfInfoList resultList = cfInfoService.getCfInfoListPageable(gTestPageable, SERVICE_INSTANCES_ID, CF_INFO_NAME);

        assertThat(resultList).isNotNull();
        assertEquals(gTestCfInfoList, resultList);
        assertEquals(PAGE_COUNT, resultList.getPage());
        assertEquals(CF_INFO_ID, resultList.getCfInfos().get(0).getCfId());
        assertEquals(CF_INFO_PASSWORD, resultList.getCfInfos().get(0).getCfPassword());
    }


    /**
     * Gets cf info by id valid phase 1 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoById_Valid_phase_1_ReturnModel() throws Exception {
        when(cfInfoRepository.findOne(CF_ID)).thenReturn(gTestResultCfInfoModel);
        when(commonService.setPasswordByAES256(Constants.AES256Type.DECODE, gTestResultCfInfoModel.getCfPassword())).thenReturn(DECODED_STRING);


        // TEST
        CfInfo resultModel = cfInfoService.getCfInfoById((int) CF_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(CF_ID, resultModel.getId());
        assertEquals(SERVICE_INSTANCES_ID, resultModel.getServiceInstancesId());
        assertEquals(CF_INFO_ID, resultModel.getCfId());
        assertEquals(CF_INFO_NAME, resultModel.getCfName());
        assertEquals(DECODED_STRING, resultModel.getCfPassword());
        assertEquals(CF_API_URL, resultModel.getCfApiUrl());
        assertEquals(TEST_DESCRIPTION, resultModel.getDescription());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(null, resultModel.getCreated());
        assertEquals(null, resultModel.getLastModified());
        assertEquals(null, resultModel.getCreatedString());
        assertEquals(null, resultModel.getLastModifiedString());
    }


    /**
     * Gets cf info by id valid phase 2 return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoById_Valid_phase_2_ReturnModel() throws Exception {
        gTestResultCfInfoModel.setCreated(gTestCfInfoModel.getCreated());
        gTestResultCfInfoModel.setLastModified(gTestCfInfoModel.getLastModified());
        gTestResultCfInfoModel.setCreatedString(gTestCfInfoModel.getCreatedString());
        gTestResultCfInfoModel.setLastModifiedString(gTestCfInfoModel.getLastModifiedString());

        when(cfInfoRepository.findOne(CF_ID)).thenReturn(gTestResultCfInfoModel);
        when(commonService.setPasswordByAES256(Constants.AES256Type.DECODE, gTestResultCfInfoModel.getCfPassword())).thenReturn(DECODED_STRING);


        // TEST
        CfInfo resultModel = cfInfoService.getCfInfoById((int) CF_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(CF_ID, resultModel.getId());
        assertEquals(SERVICE_INSTANCES_ID, resultModel.getServiceInstancesId());
        assertEquals(CF_INFO_ID, resultModel.getCfId());
        assertEquals(CF_INFO_NAME, resultModel.getCfName());
        assertEquals(DECODED_STRING, resultModel.getCfPassword());
        assertEquals(CF_API_URL, resultModel.getCfApiUrl());
        assertEquals(TEST_DESCRIPTION, resultModel.getDescription());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(TEST_CREATED, resultModel.getCreated());
        assertEquals(TEST_LAST_MODIFIED, resultModel.getLastModified());
    }


    /**
     * Gets cf info count by service instances id and cf name valid return integer.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoCountByServiceInstancesIdAndCfName_Valid_ReturnInteger() throws Exception {
        when(cfInfoRepository.countByServiceInstancesIdAndCfName(SERVICE_INSTANCES_ID, CF_INFO_NAME)).thenReturn(1);


        // TEST
        int resultCount = cfInfoService.getCfInfoCountByServiceInstancesIdAndCfName(SERVICE_INSTANCES_ID, CF_INFO_NAME);

        assertThat(resultCount).isNotNull();
        assertThat(resultCount).isGreaterThan(0);
    }


    /**
     * Create cf info valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createCfInfo_Valid_ReturnModel() throws Exception {
        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestResultCfInfoModel.getCfPassword())).thenReturn(ENCODED_STRING);
        when(cfInfoRepository.save(gTestCfInfoModel)).thenReturn(gTestResultCfInfoModel);


        // TEST
        CfInfo resultModel = cfInfoService.createCfInfo(gTestCfInfoModel);

        assertThat(resultModel).isNotNull();
        assertEquals(CF_ID, resultModel.getId());
        assertEquals(CF_INFO_ID, resultModel.getCfId());
    }


    /**
     * Update cf info valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateCfInfo_Valid_ReturnModel() throws Exception {
        gTestCfInfoModel.setId(CF_ID);

        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestResultCfInfoModel.getCfPassword())).thenReturn(ENCODED_STRING);
        when(cfInfoRepository.save(gTestCfInfoModel)).thenReturn(gTestResultCfInfoModel);


        // TEST
        CfInfo resultModel = cfInfoService.updateCfInfo(gTestCfInfoModel);

        assertThat(resultModel).isNotNull();
        assertEquals(CF_ID, resultModel.getId());
        assertEquals(CF_INFO_ID, resultModel.getCfId());
    }


    /**
     * Delete cf ino by id valid return string.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteCfInoById_Valid_ReturnString() throws Exception {
        doNothing().when(cfInfoRepository).delete(CF_ID);


        // TEST
        String resultString = cfInfoService.deleteCfInoById((int) CF_ID);

        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }

}