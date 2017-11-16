package paasta.delivery.pipeline.common.api.domain.common.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hrjin on 2017-06-23.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;


    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @RequestMapping(value = "/projectsList", method = RequestMethod.POST)
    public List getProjectsList(@RequestBody Project project) {
        return projectService.getProjectsList(project);
    }


    @RequestMapping(value = "/getProject", method = RequestMethod.POST)
    public List getProject(@RequestBody Project project) {
        return projectService.getProject(project);
    }


    /**
     * project create
     *
     * @param project
     * @return project
     */
    @RequestMapping(value = "/projectsCreate", method = RequestMethod.POST)
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
        // TODO
//        return projectService.updateProject(project);
        return projectService.setUpdateProject(project);
    }

    /**
     * QualityGate project 연결
     *
     * @param project
     * @return
     */
    @RequestMapping(value = "/qualityGateProjectLiked", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/qualityGateDelete", method = RequestMethod.PUT)
    public String qualityGateDelete(@RequestBody Project project) {
        return projectService.qualityGateDelete(project);
    }

    /**
     * QualityProfile 연결된 project 수정
     *
     * @param project
     * @return
     */
    @RequestMapping(value = "/qualityProfileDelete", method = RequestMethod.PUT)
    public String qualityProfileDelete(@RequestBody Project project) {
        return projectService.qualityProfileDelete(project);
    }


    /**
     * getProjectKey
     *
     * @param project
     * @return Project
     */
    @RequestMapping(value = "/projectKey", method = RequestMethod.POST)
    public Project getProjectKey(@RequestBody Project project) {
        return projectService.getProjectKey(project);
    }


    /**
     * Gets project by id.
     *
     * @param id the id
     * @return the project by id
     */
    @GetMapping(value = "/{id:.+}")
    public Project getProjectById(@PathVariable("id") int id) {
        return projectService.getProjectById(id);
    }

}
