package paasta.delivery.pipeline.common.api.domain.common.pipeline;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.CommonService;
import paasta.delivery.pipeline.common.api.common.Constants;
import paasta.delivery.pipeline.common.api.domain.common.authority.Authority;
import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthority;
import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthorityRepository;
import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthorityService;
import paasta.delivery.pipeline.common.api.domain.common.serviceInstance.ServiceInstances;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by hrjin on 2017-06-15.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PipelineServiceTest {

    private final Logger logger = LoggerFactory.getLogger(PipelineServiceTest.class);

    private static final Long id = Long.valueOf(1);
    private static final String name = "pipeline";
    private static final String description = "this is pipeline";
    private static final Date created = new Date();
    private static final Date lastModified = new Date();
    private static final String createdString = "bbb";
    private static final String lastModifiedString = "ccc";

    private static final int PAGE_COUNT = 9999;
    private static final int PAGE_SIZE = 1;
    private static final int TOTAL_PAGES = 1;
    private static final long TOTAL_ELEMENTS = 1;
    private static final int DISPLAY = 1;
    private static final int TOTAL = 1;
    private static final int START = 1;

    private static Pipeline gTestPipelineModel = null;
    private static Pipeline gTestResultPipelineModel = null;
    private static Pageable gTestPageable = null;
    private static List<Pipeline> gTestPipelineList = null;
    private static PipelineList gTestResultPipelineList = null;
    private static GrantedAuthority gTestGrantedAuthorityModel = null;
    private static List<GrantedAuthority> gTestGrantedAuthorityList = null;
    private static Authority gTestAuthority = null;

    @Mock
    private PipelineRepository pipelineRepository;

    @Mock
    private GrantedAuthorityRepository grantedAuthorityRepository;

    @Mock
    private CommonService commonService;

    @Mock
    private Page<Pipeline> pipelinePage;

    @InjectMocks
    private PipelineService pipelineService;

    @Mock
    private GrantedAuthorityService grantedAuthorityService;

    @Before
    public void setUp() throws Exception{
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

        gTestPipelineModel = new Pipeline();
        gTestResultPipelineModel = new Pipeline();
        gTestPipelineList = new ArrayList<>();
        gTestResultPipelineList = new PipelineList();

        gTestPipelineModel.setId(id);
        gTestPipelineModel.setName(name);
        gTestPipelineModel.setDescription(description);
        gTestPipelineModel.setCreated(created);
        gTestPipelineModel.setLastModified(lastModified);
        gTestPipelineModel.setCreatedString(createdString);
        gTestPipelineModel.setLastModifiedString(lastModifiedString);

        gTestResultPipelineModel.setId(id);
        gTestResultPipelineModel.setName(name);
        gTestResultPipelineModel.setDescription(description);
        gTestResultPipelineModel.setCreated(created);
        gTestResultPipelineModel.setLastModified(lastModified);
        gTestResultPipelineModel.setCreatedString(createdString);
        gTestResultPipelineModel.setLastModifiedString(lastModifiedString);

        gTestPipelineList.add(gTestPipelineModel);

        pipelinePage = new PageImpl<>(gTestPipelineList);

        gTestResultPipelineList.setPipelines(gTestPipelineList);
        gTestResultPipelineList.setPage(PAGE_COUNT);
        gTestResultPipelineList.setSize(PAGE_SIZE);
        gTestResultPipelineList.setTotalPages(TOTAL_PAGES);
        gTestResultPipelineList.setTotalElements(TOTAL_ELEMENTS);
        gTestResultPipelineList.setLast(true);
        gTestResultPipelineList.setDisplay(DISPLAY);
        gTestResultPipelineList.setTotal(TOTAL);
        gTestResultPipelineList.setStart(START);

    }

    @After
    public void tearDown() throws Exception{
    }



    @Test
    public void getPipelineList_Valid_ReqNULL() throws Exception{
        ServiceInstances serviceInstances = new ServiceInstances();
        serviceInstances.setId("serviceInstance01");

        when(pipelineRepository.findByServiceInstancesId(anyString(),any(Pageable.class))).thenReturn(pipelinePage);
        when(pipelineRepository.findByServiceInstancesIdAndNameContaining(anyString(),anyString(),any(Pageable.class))).thenReturn(pipelinePage);
        when(commonService.setPageInfo(any(Page.class), any(Object.class))).thenReturn(gTestResultPipelineList);
        PipelineList resultList = pipelineService.getPipelineList(serviceInstances.getId(), "reqName", gTestPageable);

        assertThat(resultList).isNotNull();
        assertEquals(gTestResultPipelineList, resultList);
        assertEquals(gTestPipelineList, resultList.getPipelines());
        assertEquals(PAGE_COUNT, resultList.getPage());
        assertEquals(PAGE_SIZE, resultList.getSize());
        assertEquals(TOTAL_PAGES, resultList.getTotalPages());
        assertEquals(TOTAL_ELEMENTS, resultList.getTotalElements());
        assertEquals(true, resultList.isLast());
        assertEquals(DISPLAY, resultList.getDisplay());
        assertEquals(TOTAL, resultList.getTotal());
        assertEquals(START, resultList.getStart());
        assertEquals(id, resultList.getPipelines().get(0).getId());
        assertEquals(name, resultList.getPipelines().get(0).getName());
    }


    @Test
    public void getPipelineList_Valid_Req() throws Exception{
        ServiceInstances serviceInstances = new ServiceInstances();
        serviceInstances.setId("serviceInstance01");

        when(pipelineRepository.findByServiceInstancesId(anyString(),any(Pageable.class))).thenReturn(pipelinePage);
        when(pipelineRepository.findByServiceInstancesIdAndNameContaining(anyString(),anyString(),any(Pageable.class))).thenReturn(pipelinePage);
        when(commonService.setPageInfo(any(Page.class), any(Object.class))).thenReturn(gTestResultPipelineList);
        PipelineList resultList = pipelineService.getPipelineList(serviceInstances.getId(), null, gTestPageable);

        assertThat(resultList).isNotNull();
        assertEquals(gTestResultPipelineList, resultList);
        assertEquals(gTestPipelineList, resultList.getPipelines());
        assertEquals(PAGE_COUNT, resultList.getPage());
        assertEquals(PAGE_SIZE, resultList.getSize());
        assertEquals(TOTAL_PAGES, resultList.getTotalPages());
        assertEquals(TOTAL_ELEMENTS, resultList.getTotalElements());
        assertEquals(true, resultList.isLast());
        assertEquals(id, resultList.getPipelines().get(0).getId());
        assertEquals(name, resultList.getPipelines().get(0).getName());
    }

    @Test
    public void getPipeline_Valid_ReturnModel(){
        when(pipelineRepository.findOne(id)).thenReturn(gTestResultPipelineModel);

        Pipeline resultModel = pipelineService.getPipeline(id);

        assertThat(resultModel).isNotNull();
        assertEquals(id, resultModel.getId());
    }

    @Test
    public void createPipeline_Valid_ReturnModel(){
        when(pipelineRepository.save(gTestPipelineModel)).thenReturn(gTestResultPipelineModel);

        Pipeline resultModel = pipelineService.createPipeline(gTestPipelineModel);

        assertThat(resultModel).isNotNull();
        assertEquals(id, resultModel.getId());
        assertEquals(name, resultModel.getName());
    }

    @Test
    public void updatePipeline_Valid_ReturnModel(){
        when(pipelineRepository.findOne(id)).thenReturn(gTestPipelineModel);
        when(pipelineRepository.save(gTestPipelineModel)).thenReturn(gTestResultPipelineModel);

        Pipeline resultModel = pipelineService.updatePipeline(id, gTestPipelineModel);

        assertThat(resultModel).isNotNull();
        assertEquals(id, resultModel.getId());
        assertEquals(name, resultModel.getName());
        assertEquals(description, resultModel.getDescription());
    }


    @Test
    public void deletePipeline_Valid_ReturnString(){
        when(grantedAuthorityRepository.findByAuthCode(id)).thenReturn(gTestGrantedAuthorityList);
        when(grantedAuthorityService.findByAuthCode(id)).thenReturn(gTestGrantedAuthorityList);
        doNothing().when(grantedAuthorityService).deleteGrantedAuthorityRows(gTestGrantedAuthorityList);
        doNothing().when(pipelineRepository).delete(id);

        String resultString = pipelineService.deletePipeline(id);
        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }
}
