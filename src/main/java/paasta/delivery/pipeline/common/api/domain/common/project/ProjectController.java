package paasta.delivery.pipeline.common.api.domain.common.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationService;

import java.util.List;

/**
 * Created by hrjin on 2017-06-23.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;
    private final ProjectRelationService projectRelationService;


    @Autowired
    public ProjectController(ProjectService projectService, ProjectRelationService projectRelationService) {
        this.projectService = projectService;
        this.projectRelationService = projectRelationService;
    }


    /**
     *  Project 생성
     *
     * @param project
     * @return
     */
/*
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Project createProject(@RequestBody Project project){
        Project newProject = projectService.createProject(project);

        // ProjectRelation 생성
        ProjectRelation newProjectRelation = projectRelationService.createProjectRelation(project);
        LOGGER.info("create Project Relation : {}", newProjectRelation);
        return newProject;
    }
    */


    /**
     * Project 목록 조회
     *
     * @return
     */
/*    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Project> getProjectList(){
        List<Project> getProjectList = projectService.getProjectList();

        // ProjectRelation 전체 목록 조회
        List<ProjectRelation> getProjectRelationList = projectRelationService.getProjectRelationList();
        LOGGER.info("Project Relation List : {}", getProjectRelationList);

        int i;
        for(i = 0; i < getProjectRelationList.size(); i++) {

            // ProjectRelation 목록 조회 후 qualityGate id가 존재하면 Project 에 qualityGateId 넣어줌.
            if (getProjectRelationList.get(i).getQualityGateId() != 0) {
                getProjectList.get(i).setQualityGateId(getProjectRelationList.get(i).getQualityGateId());
            }

            // ProjectRelation 목록 조회 후 qualityProfile id가 존재하면 Project 에 qualityProfileId 넣어줌.
            if(getProjectRelationList.get(i).getQualityProfileId() != 0){
                getProjectList.get(i).setQualityProfileId(getProjectRelationList.get(i).getQualityProfileId());
            }
        }
        return getProjectList;
    }*/


    /**
     *  Project 상세 조회
     *
     * @param id
     * @return
     */
/*    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable Long id){
        Project project = projectService.getProject(id);

        // ProjectRelation 상세 조회
        ProjectRelation projectRelation = projectRelationService.getProjectRelation(id);
        LOGGER.info("GET ONE Project Relation : {}", projectRelation);

        // ProjectRelation 상세 조회 후 qualityGate id가 존재하면 Project 에 qualityGateId 넣어줌.
        if(projectRelation.getQualityGateId() != 0){
            project.setQualityGateId(projectRelation.getQualityGateId());
        }

        // ProjectRelation 상세 조회 후 qualityProfile id가 존재하면 Project 에 qualityProfileId 넣어줌.
        if(projectRelation.getQualityProfileId() != 0){
            project.setQualityProfileId(projectRelation.getQualityProfileId());
        }

        return project;
    }*/


    /**
     *  Project 삭제
     *
     * @param id
     * @return
     */
/*
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        // ProjectRelation 삭제
        projectRelationService.deleteProjectRelation(id);
        return Constants.RESULT_STATUS_SUCCESS;
    }
*/


    /**
     * Project 수정
     *
     * @param id
     * @param reqProject
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Project updateProject(@PathVariable Long id, @RequestBody Project reqProject) {
        Project project = projectService.getProject(id);
        project.setId(id);
        project.setName(reqProject.getName());
        return projectService.updateProject(project);
    }



    @RequestMapping(value = "/projectsList", method = RequestMethod.POST)
    public List getProjectsList(@RequestBody Project project) {
        return projectService.getProjectsList(project);
    }


    @RequestMapping(value = "/getProject", method = RequestMethod.POST)
    public List getProject(@RequestBody Project project){
        return projectService.getProject(project);
    }


    /**
     * project create
     *
     * @param project
     * @return project
     */
    @RequestMapping(value = "/projectsCreate", method = RequestMethod.PUT)
    public Project createProjects(@RequestBody Project project) {
        return projectService.createProjects(project);
    }


    /**
     * project delete
     *
     * @param project
     * @return project
     */
    @RequestMapping(value = "/projectsDelete", method = RequestMethod.DELETE)
    public Project deleteProjects(@RequestBody Project project) {
        return projectService.deleteProject(project);
    }

    /**
     * project update
     *
     * @param project
     * @return project
     */
    @RequestMapping(value = "/projectsUpdate", method = RequestMethod.PUT)
    public Project updateProjects(@RequestBody Project project) {
        return projectService.updateProject(project);
    }

    /**
     * QualityGate project 연결
     *
     * @param project
     * @return
     */
    @RequestMapping(value = "/qualityGateProjectLiked", method = RequestMethod.POST)
    public Project qualityGateProjectLiked(@RequestBody Project project) {
        return projectService.qualityGateProjectLiked(project);
    }


    /**
     * QualityProfile project 연결
     *
     * @param project
     * @return
     */
    @RequestMapping(value = "/qualityProfileProjectLiked", method = RequestMethod.PUT)
    public Project qualityProfileProjectLiked(@RequestBody Project project) {
        return projectService.qualityProfileProjectLiked(project);
    }

    /**
     * QualityGate 연결된 project 수정
     *
     * @param project
     * @return
     */
    @RequestMapping(value = "/qualityGateDelete",method = RequestMethod.PUT)
    public String qualityGateDelete(@RequestBody Project project){
        return projectService.qualityGateDelete(project);
    }

    /**
     * QualityProfile 연결된 project 수정
     *
     * @param project
     * @return
     */
    @RequestMapping(value = "/qualityProfileDelete", method = RequestMethod.PUT)
    public String qualityProfileDelete(@RequestBody Project project){
        return projectService.qualityProfileDelete(project);
    }


    /**
     * getProjectKey
     *
     * @param project
     * @return Project
     */
    @RequestMapping(value = "/projectKey" , method = RequestMethod.POST)
    public Project getProjectKey(@RequestBody Project project){
        return projectService.getProjectKey(project);
    }

}
