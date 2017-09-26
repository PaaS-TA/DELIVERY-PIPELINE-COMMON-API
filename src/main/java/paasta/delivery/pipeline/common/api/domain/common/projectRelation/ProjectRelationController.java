package paasta.delivery.pipeline.common.api.domain.common.projectRelation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import paasta.delivery.pipeline.common.api.domain.common.project.ProjectService;

/**
 * Created by hrjin on 2017-07-06.
 */
@RestController
@RequestMapping("/projectRelation")
public class ProjectRelationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRelationController.class);

    private final ProjectService projectService;
    private final ProjectRelationService projectRelationService;


    @Autowired
    public ProjectRelationController(ProjectService projectService, ProjectRelationService projectRelationService) {
        this.projectService = projectService;
        this.projectRelationService = projectRelationService;
    }


    /**
     * ProjectRelation qualityGateId update.
     *
     * @param projectId
     * @param qualityGateId
     * @return
     */
    @RequestMapping(value = "/{projectId}/qualityGate/{qualityGateId}", method = RequestMethod.PUT)
    public ProjectRelation updateProjectQG(@PathVariable Long projectId, @PathVariable Long qualityGateId) {

        ProjectRelation connectQualityGateProject = projectRelationService.getProjectRelation(projectId);
        connectQualityGateProject.setQualityGateId(qualityGateId);

        return projectRelationService.updateProjectRelation(connectQualityGateProject);
    }


    @RequestMapping(value = "/{projectId}/qualityProfile/{qualityProfile}", method = RequestMethod.PUT)
    public ProjectRelation updateProjectQP(@PathVariable Long projectId, @PathVariable Long qualityProfile) {

        ProjectRelation connectQualityProfileProject = projectRelationService.getProjectRelation(projectId);
        connectQualityProfileProject.setQualityProfileId(qualityProfile);

        return projectRelationService.updateProjectRelation(connectQualityProfileProject);
    }
}