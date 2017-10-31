package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

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
import paasta.delivery.pipeline.common.api.domain.common.authority.Authority;
import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthority;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.Pipeline;
import paasta.delivery.pipeline.common.api.domain.common.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.serviceInstance
 *
 * @author REX
 * @version 1.0
 * @since 10 /31/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InstanceUseServiceTest {

    private static final String SERVICE_INSTANCES_ID = "test-service-Instances-id";
    private static final String SERVICE_INSTANCES_OWNER = "test-service-Instances-owner";
    private static final String SERVICE_INSTANCES_CI_SERVER_URL = "test-service-Instances-ci-server-url";
    private static final String USER_ID = "test-user-id";
    private static final String USER_NAME = "test-user-name";
    private static final String REQUEST_NAME = "test-name";
    private static final String AUTH_NAME = "test-auth-name";
    private static final String USER_TELL_PHONE = "test-user-tell-phone";
    private static final String USER_CELL_PHONE = "test-user-cell-phone";
    private static final String USER_EMAIL = "test-user-email";
    private static final String USER_COMPANY = "test-user-company";
    private static final String USER_DESCRIPTION = "test-user-description";
    private static final String GRANTED_AUTHORITY_ID = "test-granted-authority-id";
    private static final String AUTHORITY_ID = "test-authority-id";
    private static final String AUTHORITY_LIST_STRING = "test-authority-list-string";
    private static final Long PIPELINE_ID = 1L;
    private static final Long INSTANCE_USE_ID = 1L;

    private static InstanceUse gTestInstanceUse = null;
    private static InstanceUse gTestResultInstanceUse = null;
    private static List<InstanceUse> gTestInstanceUseArrayList = null;
    private static ServiceInstances gTestServiceInstances = null;
    private static InstanceUseList gTestInstanceUseList = null;
    private static User gTestUser = null;
    private static List<GrantedAuthority> gTestGrantedAuthorityList = null;
    private static Page<InstanceUse> gTestPage = null;

    @Mock
    private Pageable gTestPageable;

    @Mock
    private CommonService commonService;

    @Mock
    private InstanceUseRepository instanceUseRepository;

    @InjectMocks
    private InstanceUseService instanceUseService;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        gTestInstanceUse = new InstanceUse();
        gTestResultInstanceUse = new InstanceUse();

        List<Pipeline> gTestPipelineList = new ArrayList<>();
        Pipeline pipeline = new Pipeline();
        pipeline.setId(PIPELINE_ID);
        gTestPipelineList.add(pipeline);

        gTestServiceInstances = new ServiceInstances();
        gTestServiceInstances.setId(SERVICE_INSTANCES_ID);
        gTestServiceInstances.setPipelineList(gTestPipelineList);
        gTestServiceInstances.setInstanceUseList(new ArrayList<>());
        gTestServiceInstances.setOwner(SERVICE_INSTANCES_OWNER);
        gTestServiceInstances.setCiServerUrl(SERVICE_INSTANCES_CI_SERVER_URL);

        gTestInstanceUseArrayList = new ArrayList<>();
        gTestInstanceUseList = new InstanceUseList();
        gTestUser = new User();
        gTestUser.setId(USER_ID);
        gTestUser.setName(USER_NAME);
        gTestUser.setInstanceUseList(gTestInstanceUseArrayList);
        gTestUser.setTellPhone(USER_TELL_PHONE);
        gTestUser.setCellPhone(USER_CELL_PHONE);
        gTestUser.setEmail(USER_EMAIL);
        gTestUser.setCompany(USER_COMPANY);
        gTestUser.setDescription(USER_DESCRIPTION);
        gTestUser.setCreated(new Date());
        gTestUser.setLastModified(new Date());

        gTestGrantedAuthorityList = new ArrayList<>();
        GrantedAuthority grantedAuthority = new GrantedAuthority();
        grantedAuthority.setId(GRANTED_AUTHORITY_ID);
        grantedAuthority.setInstanceUseId(1L);
        grantedAuthority.setAuthCode(1L);
        grantedAuthority.setAuthority(new Authority());
        grantedAuthority.setAuthorityId(AUTHORITY_ID);
        grantedAuthority.setCreated(new Date());
        gTestGrantedAuthorityList.add(grantedAuthority);

        gTestInstanceUse.setUserDescription(USER_DESCRIPTION);
        gTestInstanceUse.setAuthListStr(AUTHORITY_LIST_STRING);
        gTestInstanceUse.setPipelineId(PIPELINE_ID);
        gTestInstanceUse.setServiceInstances(gTestServiceInstances);
        gTestInstanceUse.setUser(gTestUser);
        gTestInstanceUse.setGrantedAuthorities(gTestGrantedAuthorityList);

        gTestResultInstanceUse.setId(INSTANCE_USE_ID);
        gTestResultInstanceUse.setUserDescription(USER_DESCRIPTION);
        gTestResultInstanceUse.setAuthListStr(AUTHORITY_LIST_STRING);
        gTestResultInstanceUse.setPipelineId(PIPELINE_ID);
        gTestResultInstanceUse.setServiceInstances(gTestServiceInstances);
        gTestResultInstanceUse.setUser(gTestUser);
        gTestResultInstanceUse.setGrantedAuthorities(gTestGrantedAuthorityList);

        gTestPageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 10;
            }

            @Override
            public int getPageSize() {
                return 10;
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

        gTestPage = new PageImpl<>(gTestInstanceUseArrayList);
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
     * Create instance use valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createInstanceUse_ValidModel_ReturnModel() throws Exception {
        when(instanceUseRepository.save(gTestInstanceUse)).thenReturn(gTestResultInstanceUse);


        // TEST
        InstanceUse resultModel = instanceUseService.createInstanceUse(gTestInstanceUse);

        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultInstanceUse.getId(), resultModel.getId());
        assertEquals(USER_DESCRIPTION, resultModel.getUserDescription());
        assertEquals(AUTHORITY_LIST_STRING, resultModel.getAuthListStr());
        assertEquals(PIPELINE_ID, resultModel.getPipelineId());
        assertEquals(gTestServiceInstances, resultModel.getServiceInstances());
        assertEquals(gTestUser, resultModel.getUser());
        assertEquals(gTestGrantedAuthorityList, resultModel.getGrantedAuthorities());
    }


    /**
     * Instance service instance use list valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void instanceServiceInstanceUseList_ValidModel_ReturnList() throws Exception {
        when(instanceUseRepository.findAll()).thenReturn(gTestInstanceUseArrayList);


        // TEST
        List<InstanceUse> resultList = instanceUseService.instanceServiceInstanceUseList();

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets instance use list phase 01 valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstanceUseList_phase_01_ValidModel_ReturnList() throws Exception {
        when(instanceUseRepository.findByServiceInstancesId(SERVICE_INSTANCES_ID, gTestPageable)).thenReturn(gTestPage);
        when(commonService.setPageInfo(gTestPage, InstanceUseList.class)).thenReturn(gTestInstanceUseList);


        // TEST
        InstanceUseList resultList = instanceUseService.getInstanceUseList(SERVICE_INSTANCES_ID, null, null, gTestPageable);

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets instance use list phase 02 valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstanceUseList_phase_02_ValidModel_ReturnList() throws Exception {
        when(instanceUseRepository.findByServiceInstancesIdAndUserNameContaining(SERVICE_INSTANCES_ID, USER_NAME, gTestPageable)).thenReturn(gTestPage);
        when(commonService.setPageInfo(gTestPage, InstanceUseList.class)).thenReturn(gTestInstanceUseList);


        // TEST
        InstanceUseList resultList = instanceUseService.getInstanceUseList(SERVICE_INSTANCES_ID, USER_NAME, null, gTestPageable);

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets instance use list phase 03 valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstanceUseList_phase_03_ValidModel_ReturnList() throws Exception {
        when(instanceUseRepository.findByServiceInstancesIdAndGrantedAuthorities_Authority_Id(SERVICE_INSTANCES_ID, AUTH_NAME, gTestPageable)).thenReturn(gTestPage);
        when(commonService.setPageInfo(gTestPage, InstanceUseList.class)).thenReturn(gTestInstanceUseList);


        // TEST
        InstanceUseList resultList = instanceUseService.getInstanceUseList(SERVICE_INSTANCES_ID, null, AUTH_NAME, gTestPageable);

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets instance use list phase 04 valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstanceUseList_phase_04_ValidModel_ReturnList() throws Exception {
        when(instanceUseRepository.findByServiceInstancesIdAndUserNameContainingAndGrantedAuthorities_Authority_Id(SERVICE_INSTANCES_ID, USER_NAME, AUTH_NAME, gTestPageable)).thenReturn(gTestPage);
        when(commonService.setPageInfo(gTestPage, InstanceUseList.class)).thenReturn(gTestInstanceUseList);


        // TEST
        InstanceUseList resultList = instanceUseService.getInstanceUseList(SERVICE_INSTANCES_ID, USER_NAME, AUTH_NAME, gTestPageable);

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets instance use list by pipeline contributor phase 01 valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstanceUseListByPipelineContributor_phase_01_ValidModel_ReturnList() throws Exception {
        when(instanceUseRepository.findAllByServiceInstancesIdAndGrantedAuthoritiesAuthCode(SERVICE_INSTANCES_ID, PIPELINE_ID, gTestPageable)).thenReturn(gTestPage);
        when(commonService.setPageInfo(gTestPage, InstanceUseList.class)).thenReturn(gTestInstanceUseList);


        // TEST
        InstanceUseList resultList = instanceUseService.getInstanceUseListByPipelineContributor(SERVICE_INSTANCES_ID, PIPELINE_ID, null, gTestPageable);

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets instance use list by pipeline contributor phase 02 valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstanceUseListByPipelineContributor_phase_02_ValidModel_ReturnList() throws Exception {
        when(instanceUseRepository.findAllByServiceInstancesIdAndGrantedAuthoritiesAuthCode(SERVICE_INSTANCES_ID, PIPELINE_ID, gTestPageable)).thenReturn(gTestPage);
        when(commonService.setPageInfo(gTestPage, InstanceUseList.class)).thenReturn(gTestInstanceUseList);


        // TEST
        InstanceUseList resultList = instanceUseService.getInstanceUseListByPipelineContributor(SERVICE_INSTANCES_ID, PIPELINE_ID, "", gTestPageable);

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets instance use list by pipeline contributor phase 02 valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstanceUseListByPipelineContributor_phase_03_ValidModel_ReturnList() throws Exception {
        when(instanceUseRepository.findAllByServiceInstancesIdAndUserNameContainingAndGrantedAuthoritiesAuthCode(SERVICE_INSTANCES_ID, REQUEST_NAME, PIPELINE_ID, gTestPageable)).thenReturn(gTestPage);
        when(commonService.setPageInfo(gTestPage, InstanceUseList.class)).thenReturn(gTestInstanceUseList);


        // TEST
        InstanceUseList resultList = instanceUseService.getInstanceUseListByPipelineContributor(SERVICE_INSTANCES_ID, PIPELINE_ID, REQUEST_NAME, gTestPageable);

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets instance use valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstanceUse_ValidModel_ReturnModel() throws Exception {
        when(instanceUseRepository.findByServiceInstancesIdAndUserId(SERVICE_INSTANCES_ID, USER_ID)).thenReturn(gTestResultInstanceUse);


        // TEST
        InstanceUse resultModel = instanceUseService.getInstanceUse(SERVICE_INSTANCES_ID, USER_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultInstanceUse.getId(), resultModel.getId());
    }


    /**
     * Delete instance use valid model return string.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteInstanceUse_ValidModel_ReturnString() throws Exception {
        doNothing().when(instanceUseRepository).delete(INSTANCE_USE_ID);


        // TEST
        String result = instanceUseService.deleteInstanceUse(INSTANCE_USE_ID);

        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result);
    }


    /**
     * Update instance use valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateInstanceUse_ValidModel_ReturnModel() throws Exception {
        gTestInstanceUse.setId(INSTANCE_USE_ID);

        when(instanceUseRepository.save(gTestInstanceUse)).thenReturn(gTestResultInstanceUse);


        // TEST
        InstanceUse resultModel = instanceUseService.updateInstanceUse(gTestInstanceUse);

        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultInstanceUse.getId(), resultModel.getId());
    }

}
