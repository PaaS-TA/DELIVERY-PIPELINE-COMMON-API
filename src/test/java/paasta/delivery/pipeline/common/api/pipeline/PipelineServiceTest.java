package paasta.delivery.pipeline.common.api.pipeline;

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
import paasta.delivery.pipeline.common.api.common.ListRequest;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.Pipeline;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.PipelineList;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.PipelineRepository;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.PipelineService;
import paasta.delivery.pipeline.common.api.domain.common.serviceInstance.ServiceInstances;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by hrjin on 2017-06-15.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PipelineServiceTest {

    private final Logger logger = LoggerFactory.getLogger(PipelineServiceTest.class);

    @Mock
    private PipelineRepository pipelineRepository;

    @InjectMocks
    private PipelineService pipelineService;

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception{
    }

    Long pipelineId = (long) (Math.random()*100);


    @Test
    public void getPipelineList_Valid_ReturnList() throws Exception{
        String name = "test01";
        ServiceInstances serviceInstances = new ServiceInstances();
        serviceInstances.setId("serviceInstance01");

        Pipeline pipeline = new Pipeline();
        pipeline.setName(name);
        pipeline.setId(pipelineId);

        ListRequest listRequest = new ListRequest();

        List<Pipeline> pipelines = new ArrayList<>();
        pipelines.add(pipeline);

        PipelineList testModel = new PipelineList();
        testModel.setDisplay(0);
        testModel.setStart(0);
        testModel.setTotal(0);
        testModel.setPipelines(pipelines);


        when(pipelineRepository.findAll()).thenReturn(pipelines);

        /*PipelineList resultList = pipelineService.getPipelineList(listRequest);

        System.out.println("pipelines" + resultList.getPipelines());

        assertThat(resultList).isNotNull();
        assertEquals(pipelineId, resultList.getPipelines().get(0).getId());*/
    }


    @Test
    public void getPipeline_Valid_ReturnModel() throws Exception{
        Pipeline testModel = new Pipeline();
        testModel.setId(pipelineId);

        when(pipelineRepository.findOne(testModel.getId())).thenReturn(testModel);

        Pipeline resultModel = pipelineService.getPipeline(testModel.getId());

        assertThat(resultModel).isNotNull();
        assertEquals(pipelineId, resultModel.getId());
    }

    @Test
    public void createPipeline_Valid_ReturnModel() throws Exception{
        String name = "파이프라인 테스트";
        ServiceInstances serviceInstances = new ServiceInstances();
        serviceInstances.setId("serviceInstance01");

        Pipeline testModel = new Pipeline();

        testModel.setId(pipelineId);
        testModel.setName(name);
        testModel.setServiceInstances(serviceInstances);

        when(pipelineRepository.save(testModel)).thenReturn(testModel);

        Pipeline resultModel = pipelineService.createPipeline(testModel);

        assertThat(resultModel).isNotNull();
        assertEquals(pipelineId, resultModel.getId());

        logger.info("pipelineId ::: " + testModel.getId() + "=" + resultModel.getId());

    }

    /*@Test
    public void updatePipeline_Valid_ReturnModel() throws Exception{

        // 기존 데이터 값
        String name = "파이프라인 테스트";

        // 수정할 데이터 값
        String nameModify = "파이프라인 수정";

        ServiceInstances serviceInstances = new ServiceInstances();
        serviceInstances.setId("serviceInstance01");

        Pipeline testModel = new Pipeline();
        Pipeline resultModel = new Pipeline();

        testModel.setId(pipelineId);
        *//*testModel.setName(name);
        testModel.setServiceInstances(serviceInstances);*//*

        resultModel.setId(testModel.getId());
        resultModel.setName(nameModify);
        resultModel.setServiceInstances(testModel.getServiceInstances());

        when(pipelineRepository.save(testModel)).thenReturn(resultModel);

        logger.info("test model ::: " + testModel);

        logger.info("result model ::: " + resultModel);

        Pipeline finalModel = pipelineService.updatePipeline(testModel.getId(), resultModel);
        logger.info("final model ::: " + finalModel);

        assertThat(finalModel).isNotNull();
        assertEquals(pipelineId, finalModel.getId());
    }*/
}
