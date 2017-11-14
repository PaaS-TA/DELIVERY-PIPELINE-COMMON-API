package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import javafx.beans.binding.When;
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
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
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
public class CiInfoServiceTest {

    private static final String SERVICE_INSTANCES_ID = "test-service-Instances-id";
    private static final String SERVICE_INSTANCES_OWNER = "test-service-Instances-owner";
    private static final String SERVICE_INSTANCES_CI_SERVER_URL = "test-service-Instances-ci-server-url";

    private static ServiceInstances gTestServiceInstances = null;
    private static ServiceInstances gTestResultServiceInstances = null;
    private static CiInfo CI_INFO;
    private static List<CiInfo> CI_INFOS;

    @Mock
    private ServiceInstancesRepository serviceInstancesRepository;

    @Mock
    private CiInfoRepository ciInfoRepository;

    @InjectMocks
    private CiInfoService ciInfoService;





    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        gTestServiceInstances = new ServiceInstances();
        gTestResultServiceInstances = new ServiceInstances();


        gTestServiceInstances.setPipelineList(new ArrayList<>());
        gTestServiceInstances.setInstanceUseList(new ArrayList<>());
        gTestServiceInstances.setOwner(SERVICE_INSTANCES_OWNER);
        gTestServiceInstances.setCiServerUrl(SERVICE_INSTANCES_CI_SERVER_URL);
        gTestServiceInstances.setService_type("Shared");
        gTestResultServiceInstances.setService_type("Shared");
        gTestResultServiceInstances.setId(SERVICE_INSTANCES_ID);
        gTestResultServiceInstances.setPipelineList(new ArrayList<>());
        gTestResultServiceInstances.setInstanceUseList(new ArrayList<>());
        gTestResultServiceInstances.setOwner(SERVICE_INSTANCES_OWNER);
        gTestResultServiceInstances.setCiServerUrl(SERVICE_INSTANCES_CI_SERVER_URL);



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
     * Gets service instances valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void test_getCiServer_1() throws Exception {
        when(ciInfoRepository.findByTypeOrderByUsedcount(anyString())).thenReturn(CI_INFOS);
        List<CiInfo> resultList = ciInfoRepository.findByTypeOrderByUsedcount("Shared");
        assertThat(resultList).isNotNull();
    }


    /**
     * Gets service instances valid model return list.
     *
     * @throws Exception the exception
     */
    @Test
    public void test_getCiServer_2() throws Exception {
        when(ciInfoRepository.findByStatusAndTypeOrderByUsedcount(anyString(),anyString())).thenReturn(CI_INFOS);
        List<CiInfo> resultList = ciInfoRepository.findByStatusAndTypeOrderByUsedcount("N","Shared");
        assertThat(resultList).isNotNull();
    }


    @Test
    public void test_getCiServer_3() throws Exception {
        when(ciInfoRepository.findByStatusAndTypeOrderByUsedcount(anyString(),anyString())).thenReturn(CI_INFOS);
        when(ciInfoRepository.findByTypeOrderByUsedcount(anyString())).thenReturn(CI_INFOS);
        CiInfo resultList = ciInfoService.getNotUsedCfinfo(anyString());
        assertEquals(CI_INFOS.get(0).getServerUrl(),resultList.getServerUrl());
        assertEquals(CI_INFOS.get(0).getUsedcount(),resultList.getUsedcount());
        assertEquals(CI_INFOS.get(0).getId(),resultList.getId());
        assertEquals(CI_INFOS.get(0).getStatus(),resultList.getStatus());
        assertEquals(CI_INFOS.get(0).getType(),resultList.getType());

    }

    @Test
    public void test_update() throws Exception {
        when(ciInfoRepository.save(any(CiInfo.class))).thenReturn(CI_INFO);
        when(ciInfoService.update(any(CiInfo.class))).thenReturn(true);
        boolean result = ciInfoService.update(CI_INFO);
        assertEquals(result,true);
    }




    @Test
    public void test_recovery() throws Exception {
        when(ciInfoRepository.findByServerUrl(anyString())).thenReturn(CI_INFO);
        when(ciInfoRepository.save(any(CiInfo.class))).thenReturn(CI_INFO);
        when(ciInfoService.recovery(anyString())).thenReturn(true);
        boolean result = ciInfoService.recovery(CI_INFO.getServerUrl());
        assertEquals(result,true);
    }
}
