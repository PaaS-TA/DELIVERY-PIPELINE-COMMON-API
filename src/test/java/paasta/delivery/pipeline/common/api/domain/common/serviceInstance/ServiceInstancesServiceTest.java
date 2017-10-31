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
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class ServiceInstancesServiceTest {

    private static final String SERVICE_INSTANCES_ID = "test-service-Instances-id";
    private static final String SERVICE_INSTANCES_OWNER = "test-service-Instances-owner";
    private static final String SERVICE_INSTANCES_CI_SERVER_URL = "test-service-Instances-ci-server-url";

    private static ServiceInstances gTestServiceInstances = null;
    private static ServiceInstances gTestResultServiceInstances = null;
    private static List<ServiceInstances> gTestServiceInstancesList = null;

    @Mock
    private ServiceInstancesRepository serviceInstancesRepository;

    @InjectMocks
    private ServiceInstancesService serviceInstancesService;


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

        gTestResultServiceInstances.setId(SERVICE_INSTANCES_ID);
        gTestResultServiceInstances.setPipelineList(new ArrayList<>());
        gTestResultServiceInstances.setInstanceUseList(new ArrayList<>());
        gTestResultServiceInstances.setOwner(SERVICE_INSTANCES_OWNER);
        gTestResultServiceInstances.setCiServerUrl(SERVICE_INSTANCES_CI_SERVER_URL);

        gTestServiceInstancesList.add(gTestResultServiceInstances);
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
        when(serviceInstancesRepository.save(gTestServiceInstances)).thenReturn(gTestResultServiceInstances);


        // TEST
        ServiceInstances resultModel = serviceInstancesService.createInstances(gTestServiceInstances);

        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultServiceInstances.getId(), resultModel.getId());
        assertEquals(gTestResultServiceInstances.getOwner(), resultModel.getOwner());
        assertEquals(gTestResultServiceInstances.getCiServerUrl(), resultModel.getCiServerUrl());
        assertFalse(0 < resultModel.getPipelineList().size());
        assertFalse(0 < resultModel.getInstanceUseList().size());
    }


    /**
     * Delete instance valid model return string.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteInstance_ValidModel_ReturnString() throws Exception {
        doNothing().when(serviceInstancesRepository).delete(SERVICE_INSTANCES_ID);


        // TEST
        String result = serviceInstancesService.deleteInstance(SERVICE_INSTANCES_ID);

        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result);
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
