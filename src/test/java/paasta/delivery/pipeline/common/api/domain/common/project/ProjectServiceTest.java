package paasta.delivery.pipeline.common.api.domain.common.project;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by kim on 2017-10-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectServiceTest {

    private static final long ID = 1L;
    private static final Boolean LINKED = true;
    private static final String GATE_DEFAULT_YN = "N";
    private static final String PROFILE_DEFAULT_YN = "N";
    private static final String PROJECT_NAME = "test-project-name";
    private static final String QUALITY_PROFILE_KEY = "test-quality-profile-key";
    private static final int QUALITY_GATE_ID = 1;
    private static final String SERVICE_INSTANCES_ID = "test-service-instances-id";
    private static final int PIPELINE_ID = 1;
    private static final long JOB_ID = 1L;
    private static final Date TEST_CREATED = new Date();
    private static final Date TEST_LAST_MODIFIED = new Date();
    private static final String RESULT_STATUS = "SUCCESS";

    private static Project testModel = null;
    private static Project resultModel = null;
    private static List<Project> testResultList = null;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        testModel = new Project();
        resultModel = new Project();
        testResultList = new ArrayList<>();

        testModel.setId(ID);
        testModel.setProjectName(PROJECT_NAME);
        testModel.setJobId(JOB_ID);
        testModel.setQualityProfileKey(QUALITY_PROFILE_KEY);
        testModel.setQualityGateId(QUALITY_GATE_ID);
        testModel.setResultStatus(RESULT_STATUS);
        testModel.setGateDefaultYn(GATE_DEFAULT_YN);
        testModel.setLinked(LINKED);
        testModel.setServiceInstancesId(SERVICE_INSTANCES_ID);
        testModel.setPipelineId(PIPELINE_ID);
        testModel.setProfileDefaultYn(PROFILE_DEFAULT_YN);
        testModel.setCreated(TEST_CREATED);
        testModel.setLastModified(TEST_LAST_MODIFIED);

        resultModel.setId(ID);
        resultModel.setProjectName(PROJECT_NAME);
        resultModel.setJobId(JOB_ID);
        resultModel.setQualityProfileKey(QUALITY_PROFILE_KEY);
        resultModel.setQualityGateId(QUALITY_GATE_ID);
        resultModel.setResultStatus(RESULT_STATUS);
        resultModel.setGateDefaultYn(GATE_DEFAULT_YN);
        resultModel.setLinked(LINKED);
        resultModel.setServiceInstancesId(SERVICE_INSTANCES_ID);
        resultModel.setPipelineId(PIPELINE_ID);
        resultModel.setProfileDefaultYn(PROFILE_DEFAULT_YN);
        resultModel.setCreated(testModel.getCreated());
        resultModel.setLastModified(testModel.getLastModified());

        testResultList.add(resultModel);

    }

    /**
     * ProjectsList model valid return List.
     *
     * @throws Exception the exception
     */
    @Test
    public void getProjectsList_Valid_ReturnList() throws Exception {
        when(projectRepository.findByserviceInstancesId(testModel.getServiceInstancesId())).thenReturn(testResultList);

        List<Project> resultList = projectService.getProjectsList(testModel);
        assertThat(resultList).isNotNull();
        assertEquals(testResultList, resultList);
        assertEquals(SERVICE_INSTANCES_ID, resultList.get(0).getServiceInstancesId());
    }


    /**
     * Project model valid return List.
     *
     * @throws Exception the exception
     */
    @Test
    public void getProject_Valid_ReturnList() throws Exception {
        when(projectRepository.findByserviceInstancesIdAndPipelineId(testModel.getServiceInstancesId(), testModel.getPipelineId())).thenReturn(testResultList);

        List<Project> resultList = projectService.getProject(testModel);

        assertThat(resultList).isNotNull();
        assertEquals(testResultList, resultList);
        assertEquals(SERVICE_INSTANCES_ID, resultList.get(0).getServiceInstancesId());
        assertEquals(PIPELINE_ID, resultList.get(0).getPipelineId());
    }


    /**
     * ProjectKey model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getProjectKey_Valid_Return() throws Exception {
        when(projectRepository.findOne(testModel.getId())).thenReturn(resultModel);

        Project result = projectService.getProjectKey(testModel);
        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());

    }


    /**
     * Create Project model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createProjects_Valid_Return() throws Exception {
        when(projectRepository.save(testModel)).thenReturn(testModel);

        Project result = projectService.createProjects(testModel);

        assertThat(result).isNotNull();
        assertEquals(SERVICE_INSTANCES_ID, result.getServiceInstancesId());
        assertEquals(PROJECT_NAME, result.getProjectName());
    }


    /**
     * Update Project model valid return model.
     *
     * @throws Exception the exception
     */
//    @Test
//    public void updateProject_Valid_Return() throws Exception {
//        when(projectRepository.findOne(testModel.getId())).thenReturn(resultModel);
//        when(projectRepository.save(testModel)).thenReturn(resultModel);
//
//        Project result = projectService.updateProject(testModel);
//        assertThat(result).isNotNull();
//        assertEquals(ID,result.getId());
//    }


    /**
     * Delete project  valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteProject_valid_Return() throws Exception {
        doNothing().when(projectRepository).delete(testModel.getId());

        Project result = projectService.deleteProject(testModel);
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultStatus());
    }


    /**
     * Project Gate Liked Case1 model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void qualityGateProjectLikedCaseTrue_Valid_Return() throws Exception {
        testModel.setLinked(true);
        when(projectRepository.findOne(testModel.getId())).thenReturn(resultModel);
        when(projectRepository.save(resultModel)).thenReturn(resultModel);

        Project result = projectService.qualityGateProjectLiked(testModel);

        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());
        assertEquals(QUALITY_GATE_ID, result.getQualityGateId());

    }


    /**
     * Project Gate UnLiked Case2 model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void qualityGateProjectLikedCaseFalse_Valid_Return() throws Exception {
        testModel.setLinked(false);
        testModel.setQualityGateId(0);

        when(projectRepository.findOne(testModel.getId())).thenReturn(resultModel);
        when(projectRepository.save(resultModel)).thenReturn(resultModel);

        Project result = projectService.qualityGateProjectLiked(testModel);

        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());

    }


    /**
     * Project Profile Liked Case1 model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void qualityProfileProjectLikedCaseTrue_Valid_Return() throws Exception {
        testModel.setLinked(true);

        when(projectRepository.findOne(testModel.getId())).thenReturn(resultModel);
        when(projectRepository.save(resultModel)).thenReturn(resultModel);

        Project result = projectService.qualityProfileProjectLiked(testModel);
        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());
        assertEquals(QUALITY_PROFILE_KEY, result.getQualityProfileKey());
    }


    /**
     * Project Profile UnLiked Case2 model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void qualityProfileProjectLikedCaseFalse_Valid_Return() throws Exception {
        testModel.setLinked(false);
        testModel.setQualityProfileKey("");

        when(projectRepository.findOne(testModel.getId())).thenReturn(resultModel);
        when(projectRepository.save(resultModel)).thenReturn(resultModel);

        Project result = projectService.qualityProfileProjectLiked(testModel);
        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());

    }


    /**
     * GateDelete UnLiked  model valid return String.
     *
     * @throws Exception the exception
     */
    @Test
    public void qualityGateDelete_Valid_Return() throws Exception {
        when(projectRepository.findByServiceInstancesIdAndQualityGateId(testModel.getServiceInstancesId(), testModel.getQualityGateId())).thenReturn(testResultList);

        when(projectRepository.save(testResultList.get(0))).thenReturn(null);

        String resultString = projectService.qualityGateDelete(testModel);
        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }


    /**
     * ProfileDelete UnLiked  model valid return String.
     *
     * @throws Exception the exception
     */
    @Test
    public void qualityProfileDelete_Valid_Return() throws Exception {

        when(projectRepository.findByServiceInstancesIdAndQualityProfileKey(testModel.getServiceInstancesId(), testModel.getQualityProfileKey())).thenReturn(testResultList);

        when(projectRepository.save(testResultList.get(0))).thenReturn(null);

        String resultString = projectService.qualityProfileDelete(testModel);
        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);

    }


    /**
     * Sets update project valid return.
     *
     * @throws Exception the exception
     */
    @Test
    public void setUpdateProject_Valid_Return() throws Exception {
        when(projectRepository.findOne(testModel.getId())).thenReturn(resultModel);
        when(projectRepository.save(resultModel)).thenReturn(resultModel);

        Project result = projectService.setUpdateProject(testModel);
        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());
    }

}
