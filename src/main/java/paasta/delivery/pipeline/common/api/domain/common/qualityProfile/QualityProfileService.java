package paasta.delivery.pipeline.common.api.domain.common.qualityProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelation;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationRepository;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationService;

import java.util.List;

/**
 * Created by hrjin on 2017-06-26.
 */
@Service
public class QualityProfileService {
    private final QualityProfileRepository qualityProfileRepository;
    private final ProjectRelationRepository projectRelationRepository;
    private final ProjectRelationService projectRelationService;

    @Autowired
    public QualityProfileService(QualityProfileRepository qualityProfileRepository, ProjectRelationRepository projectRelationRepository, ProjectRelationService projectRelationService) {
        this.qualityProfileRepository = qualityProfileRepository;
        this.projectRelationRepository = projectRelationRepository;
        this.projectRelationService = projectRelationService;
    }

    public QualityProfile createQualityProfile(QualityProfile qualityProfile) {
        return qualityProfileRepository.save(qualityProfile);
    }


    public QualityProfile getQualityProfile(Long id) {
        return qualityProfileRepository.findOne(id);
    }

/*    public QualityProfile updateQualityProfile(QualityProfile qualityProfile) {
        return qualityProfileRepository.save(qualityProfile);
    }*/

    public String deleteQualityProfile(Long id) {
        qualityProfileRepository.delete(id);

        // QualityProfileId 가 삭제될 시 관련돼 있는 ProjectRelation id 리스트를 가져옴.
        List<Long> projectRelations = projectRelationRepository.findIdByQualityProfileId(id);

        int i;
        for (i = 0; i < projectRelations.size(); i++) {
            ProjectRelation projectRelation = projectRelationService.getProjectRelation(projectRelations.get(i));
            projectRelation.setQualityProfileId((long) 0);
            projectRelationService.updateProjectRelation(projectRelation);
        }
        return Constants.RESULT_STATUS_SUCCESS;
    }

    //////////////////////////////////////////////


    //    public List<QualityProfile> getQualityProfileList(String serviceInstancesId) {
//        return qualityProfileRepository.findAllByserviceInstancesId(serviceInstancesId);
//    }
    //시연후 수정
    public List getQualityProfileList() {
        return qualityProfileRepository.findAll();
    }


    /**
     * QualityProfile 복제
     *
     * @return
     */
    public QualityProfile qualityProfileCopy(QualityProfile qualityProfile) {
        return qualityProfileRepository.save(qualityProfile);
    }

    /**
     * QualityProfile 삭제
     *
     * @return
     */
    public String deleteQualityProfile(QualityProfile qualityProfile) {

        qualityProfileRepository.delete(qualityProfile.getId());
        return Constants.RESULT_STATUS_SUCCESS;
    }

    /**
     * QualityProfile 수정
     *
     * @return
     */
    public QualityProfile updateQualityProfile(QualityProfile qualityProfile) {
        QualityProfile result = new QualityProfile();
        result = qualityProfileRepository.findOne(qualityProfile.getId());
        result.setName(qualityProfile.getName());
        return qualityProfileRepository.save(result);
    }

    /**
     * QualityProfile default Setting
     *
     * @return
     */
    public String qualityProfileDefaultSetting(QualityProfile qualityProfile){
        QualityProfile result = new QualityProfile();

        result.setQualityProfileDefault(1);
        result.setServiceInstancesId(qualityProfile.getServiceInstancesId());
        result = qualityProfileRepository.findByServiceInstancesIdAndQualityProfileDefault(result.getServiceInstancesId(),result.getQualityProfileDefault());

        if(result != null){
            result.setQualityProfileDefault(0);
            qualityProfileRepository.save(result);
        }

        result = qualityProfileRepository.findOne(qualityProfile.getId());
        result.setQualityProfileDefault(1);
        qualityProfileRepository.save(result);
        return Constants.RESULT_STATUS_SUCCESS;
    }
}
