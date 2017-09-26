package paasta.delivery.pipeline.common.api.cfInfo;

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
import paasta.delivery.pipeline.common.api.domain.common.cfInfo.CfInfo;
import paasta.delivery.pipeline.common.api.domain.common.cfInfo.CfInfoList;
import paasta.delivery.pipeline.common.api.domain.common.cfInfo.CfInfoRepository;
import paasta.delivery.pipeline.common.api.domain.common.cfInfo.CfInfoService;

import java.util.ArrayList;
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
    private static final String SERVICE_INSTANCES_ID = "test-service-instances-id";
    private static final String CF_INFO_ID = "test-cf-info-id";
    private static final String CF_INFO_NAME = "test-cf-info-name";
    private static final String CF_INFO_PASSWORD = "test-cf-info-password";
    private static final String ENCODED_STRING = "test-cf-password-encoded";

    private static CfInfo gTestModel = null;
    private static CfInfo gTestResultModel = null;
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
        gTestModel = new CfInfo();
        gTestResultModel = new CfInfo();

        gTestModel.setCfId(CF_INFO_ID);
        gTestModel.setCfPassword(CF_INFO_PASSWORD);

        gTestResultModel.setId(CF_ID);
        gTestResultModel.setCfId(CF_INFO_ID);
        gTestResultModel.setCfPassword(CF_INFO_PASSWORD);

        gTestResultList = new ArrayList<>();
        gTestResultList.add(gTestModel);

        cfInfoPage = new PageImpl<>(gTestResultList);
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
     * Gets cf info list pageable valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoListPageable_Valid_ReturnModel() throws Exception {
        CfInfoList cfInfoList = new CfInfoList();
        cfInfoList.setPage(PAGE_COUNT);
        cfInfoList.setCfInfos(gTestResultList);

        Pageable pageable = new Pageable() {
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

        when(cfInfoRepository.findAll(pageable)).thenReturn(cfInfoPage);
        when(commonService.setPageInfo(cfInfoPage, CfInfoList.class)).thenReturn(cfInfoList);

        CfInfoList resultList = cfInfoService.getCfInfoListPageable(pageable, null, String.valueOf(CF_ID));

        assertThat(resultList).isNotNull();
        assertEquals(cfInfoList, resultList);
        assertEquals(PAGE_COUNT, resultList.getPage());
        assertEquals(CF_INFO_ID, resultList.getCfInfos().get(0).getCfId());
        assertEquals(CF_INFO_PASSWORD, resultList.getCfInfos().get(0).getCfPassword());
    }


    /**
     * Gets cf info by id valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoById_Valid_ReturnModel() throws Exception {
        when(cfInfoRepository.findOne(CF_ID)).thenReturn(gTestResultModel);
        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestResultModel.getCfPassword())).thenReturn(ENCODED_STRING);

        CfInfo resultModel = cfInfoService.getCfInfoById((int) CF_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(CF_ID, resultModel.getId());
        assertEquals(CF_INFO_ID, resultModel.getCfId());
    }


    /**
     * Gets cf info count by service instances id and cf name valid return integer.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCfInfoCountByServiceInstancesIdAndCfName_Valid_ReturnInteger() throws Exception {
        when(cfInfoRepository.countByServiceInstancesIdAndCfName(SERVICE_INSTANCES_ID, CF_INFO_NAME)).thenReturn(1);

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
        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestResultModel.getCfPassword())).thenReturn(ENCODED_STRING);
        when(cfInfoRepository.save(gTestModel)).thenReturn(gTestResultModel);

        CfInfo resultModel = cfInfoService.createCfInfo(gTestModel);

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
        gTestModel.setId(CF_ID);

        when(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, gTestResultModel.getCfPassword())).thenReturn(ENCODED_STRING);
        when(cfInfoRepository.save(gTestModel)).thenReturn(gTestResultModel);

        CfInfo resultModel = cfInfoService.updateCfInfo(gTestModel);

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

        String resultString = cfInfoService.deleteCfInoById((int) CF_ID);

        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }

}