package paasta.delivery.pipeline.common.api.domain.common.projectRelation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.domain.common.project.Project;

import java.util.List;

/**
 * Created by Dojun on 2017-06-28.
 */
@Service
public class ProjectRelationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRelationService.class);
    private final ProjectRelationRepository projectRelationRepository;

    @Autowired
    public ProjectRelationService(ProjectRelationRepository projectRelationRepository) {
        this.projectRelationRepository = projectRelationRepository;
    }


    /**
     * ProjectRelation 수정
     *
     * @param connectQualityGateProject
     * @return
     */
    public ProjectRelation updateProjectRelation(ProjectRelation connectQualityGateProject) {
        LOGGER.info("connectQualityGateProject :::::: " + connectQualityGateProject);
        return projectRelationRepository.save(connectQualityGateProject);
    }


    /**
     * ProjectRelation 생성
     *
     * @param project
     * @return
     */
    public ProjectRelation createProjectRelation(Project project) {
        ProjectRelation newProjectRelation = new ProjectRelation();
        newProjectRelation.setId(project.getId());
        newProjectRelation.setQualityGateId((long) 0);
        newProjectRelation.setQualityProfileId((long) 0);

        return projectRelationRepository.save(newProjectRelation);
    }


    /**
     * ProjectRelation 삭제
     *
     * @param id
     * @return
     */
    public String deleteProjectRelation(Long id) {
        projectRelationRepository.delete(id);
        return "deleted success";
    }


    /**
     * ProjectRelation 상세 조회
     *
     * @param id
     * @return
     */
    public ProjectRelation getProjectRelation(Long id) {
        ProjectRelation getProjectRelation = projectRelationRepository.findOne(id);
        return getProjectRelation;
    }

    /**
     * ProjectRelation 전체 목록 조회
     *
     * @return
     */
    public List<ProjectRelation> getProjectRelationList() {
        List<ProjectRelation> getProjectRelationList = projectRelationRepository.findAll();
        return getProjectRelationList;
    }


    // QualityGate id 를 가지고 그 id와 연관된 project 리스트 조회
    public List<Long> getProjectIdList(Long id) {
        List<Long> relatedProjectIdList = projectRelationRepository.findIdByQualityGateId(id);
        return relatedProjectIdList;
    }



    /*public List<Long> getProjectRelationQGList() {
        List<Long> getProjectRelationQGList = projectRelationRepository.findIdByQualityGateId()
    }*/



    /*public List<Project> findByQualityGateId(Long id) {
        List<Project> projects = projectRelationRepository.findIdByQualityGateId(id);
        return projects;
    }*/
}
