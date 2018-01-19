package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.RestTemplateService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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
public class ServiceInstancesServiceTest {

    private static final String SERVICE_INSTANCES_ID = "test-service-Instances-id";
    private static final String SERVICE_INSTANCES_OWNER = "test-service-Instances-owner";
    private static final String SERVICE_INSTANCES_CI_SERVER_URL = "test-service-Instances-ci-server-url";

    private static ServiceInstances gTestServiceInstances = null;
    private static ServiceInstances gTestResultServiceInstances = null;
    private static CiInfo CI_INFO;
    private static List<CiInfo> CI_INFOS;
    private static List<ServiceInstances> gTestServiceInstancesList = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceInstancesServiceTest.class);
    @Mock
    private ServiceInstancesRepository serviceInstancesRepository;

    @Mock
    private CiInfoRepository ciInfoRepository;

    @Mock
    private CiInfoService ciInfoService;


    @InjectMocks
    private ServiceInstancesService serviceInstancesService;

    @InjectMocks
    private RestTemplateService restTemplateService;
    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        gTestServiceInstances = new ServiceInstances();
        gTestResultServiceInstances = new ServiceInstances();
        gTestServiceInstancesList = new ArrayList<>();

        gTestServiceInstances.setPipelineList(new ArrayList<>());
        gTestServiceInstances.setInstanceUseList(new ArrayList<>());
        gTestServiceInstances.setOwner(SERVICE_INSTANCES_OWNER);
        gTestServiceInstances.setCiServerUrl(SERVICE_INSTANCES_CI_SERVER_URL);
        gTestServiceInstances.setServiceType("Shared");
        gTestResultServiceInstances.setServiceType("Shared");
        gTestResultServiceInstances.setId(SERVICE_INSTANCES_ID);
        gTestResultServiceInstances.setPipelineList(new ArrayList<>());
        gTestResultServiceInstances.setInstanceUseList(new ArrayList<>());
        gTestResultServiceInstances.setOwner(SERVICE_INSTANCES_OWNER);
        gTestResultServiceInstances.setCiServerUrl(SERVICE_INSTANCES_CI_SERVER_URL);

        gTestServiceInstancesList.add(gTestResultServiceInstances);

        CI_INFO = new CiInfo();
        CI_INFO.setUsedcount(0);
        CI_INFO.setStatus("Y");
        CI_INFO.setServerUrl("http://localhost:8080");
        CI_INFO.setType("Y");
        CI_INFOS = setCiInfos();





    }

    private List<CiInfo> setCiInfos() {
        List<CiInfo> ciInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CiInfo ciInfo = new CiInfo();
            if (i % 2 == 0) {
                ciInfo.setType("Dedicated");
            } else {
                ciInfo.setType("Shared");
            }
            ciInfo.setServerUrl("http://localhost:8080/" + i);

            if (i % 3 == 0) {
                ciInfo.setStatus("Y");
            } else {
                ciInfo.setStatus("N");
            }
            Long id = Long.parseLong("" + i);
            ciInfo.setId(id);
            ciInfo.setUsedcount(i);
            ciInfos.add(ciInfo);
        }
        return ciInfos;
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
     * Create instances valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createInstances_ValidModel_ReturnModel() throws Exception {
//        when(ciInfoRepository.findByStatusAndTypeOrderByUsedcount("N", eq("Dedicated"))).thenReturn(CI_INFOS);
//        when(ciInfoRepository.findByTypeOrderByUsedcount(eq("Shared"))).thenReturn(CI_INFOS);
//        when(ciInfoService.getNotUsedCfinfo(eq("Shared"))).thenReturn(CI_INFOS.get(0));
//
//
//        when(serviceInstancesRepository.save(gTestServiceInstances)).thenReturn(gTestResultServiceInstances);
//        when(ciInfoService.update(any(CiInfo.class))).thenReturn(true);
//
//        // TEST
//        ServiceInstances resultModel = serviceInstancesService.createInstances(gTestServiceInstances);
//
//
//        assertThat(resultModel).isNotNull();
//        assertEquals(gTestResultServiceInstances.getId(), resultModel.getId());
//        assertEquals(gTestResultServiceInstances.getOwner(), resultModel.getOwner());
//        assertEquals(gTestResultServiceInstances.getCiServerUrl(), resultModel.getCiServerUrl());
//        assertFalse(0 < resultModel.getPipelineList().size());
//        assertFalse(0 < resultModel.getInstanceUseList().size());
    }


    /**
     * Delete instance valid model return string.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteInstance_ValidModel_ReturnString() throws Exception {
        /*LOGGER.info("###############################");
        LOGGER.info("###############################");
        LOGGER.info("###############################" + SERVICE_INSTANCES_ID);
        LOGGER.info("###############################");
        LOGGER.info("###############################");

        restTemplateService.send();
        doNothing().when(serviceInstancesRepository).delete(SERVICE_INSTANCES_ID);


        // TEST
        String result = serviceInstancesService.deleteInstance(SERVICE_INSTANCES_ID);

        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result);*/
    }


    /**
     * Gets service instances valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void getServiceInstances_ValidModel_ReturnList() throws Exception {
        when(serviceInstancesRepository.findAll()).thenReturn(gTestServiceInstancesList);


        // TEST
        List<ServiceInstances> resultList = serviceInstancesService.getServiceInstances();

        assertThat(resultList).isNotNull();
    }


    /**
     * Gets service instance valid model return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getServiceInstance_ValidModel_ReturnModel() throws Exception {
        when(serviceInstancesRepository.findOne(SERVICE_INSTANCES_ID)).thenReturn(gTestResultServiceInstances);


        // TEST
        ServiceInstances resultModel = serviceInstancesService.getServiceInstance(SERVICE_INSTANCES_ID);

        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultServiceInstances.getId(), resultModel.getId());
    }

}
