package paasta.delivery.pipeline.common.api.domain.common.qualityGate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationRepository;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationService;

import java.util.List;

/**
 * Created by hrjin on 2017-06-22.
 */
@Service
public class QualityGateService {

    private final QualityGateRepository qualityGateRepository;
    private final ProjectRelationRepository projectRelationRepository;
    private final ProjectRelationService projectRelationService;

    @Autowired
    public QualityGateService(QualityGateRepository qualityGateRepository, ProjectRelationRepository projectRelationRepository, ProjectRelationService projectRelationService) {
        this.qualityGateRepository = qualityGateRepository;
        this.projectRelationRepository = projectRelationRepository;
        this.projectRelationService = projectRelationService;
    }


    public QualityGate createQualityGate(QualityGate reqQualityGate) {
        return qualityGateRepository.save(reqQualityGate);
    }


    public QualityGate getQualityGate(Long id) {
        return qualityGateRepository.findOne(id);
    }


    //////////////////////////////////////////////////
//    public List<QualityGate> getQualityGateList(String serviceInstancesId) {
//        return qualityGateRepository.findAllByserviceInstancesId(serviceInstancesId);
//    }

    public List<QualityGate> getQualityGateList(String serviceInstancesId) {
//        return qualityGateRepository.findAllByserviceInstancesId(serviceInstancesId);
        return qualityGateRepository.findAll();
    }

    public QualityGate copyQualityGate(QualityGate qualityGate) {
        return qualityGateRepository.save(qualityGate);
    }

    public QualityGate updateQualityGate(QualityGate qualityGate) {
        return qualityGateRepository.save(qualityGate);
    }


//    public String deleteQualityGate(Long id) {
//        qualityGateRepository.delete(id);
//
//        // QualityGateId 가 삭제될 시 관련돼 있는 ProjectRelation id 리스트를 가져옴.
//        List<Long> projectRelations = projectRelationRepository.findIdByQualityGateId(id);
//
//        int i;
//        for(i = 0; i < projectRelations.size(); i++){
//            ProjectRelation projectRelation = projectRelationService.getProjectRelation(projectRelations.get(i));
//            projectRelation.setQualityGateId((long) 0);
//            projectRelationService.updateProjectRelation(projectRelation);
//        }
//
//        return Constants.RESULT_STATUS_SUCCESS;
//    }

    public String deleteQualityGate(QualityGate qualityGate) {
        qualityGateRepository.delete(qualityGate.getId());

        return Constants.RESULT_STATUS_SUCCESS;
    }

    public String qualityGateDefaultSetting(QualityGate qualityGate){

        QualityGate result = new QualityGate();
        result.setQualityGateDefault(1);
        result.setServiceInstancesId(qualityGate.getServiceInstancesId());
        result = qualityGateRepository.findByServiceInstancesIdAndQualityGateDefault(result.getServiceInstancesId(),result.getQualityGateDefault());

        if(result != null){
            result.setQualityGateDefault(0);
            qualityGateRepository.save(result);
        }


        result = qualityGateRepository.findOne(qualityGate.getId());
        result.setQualityGateDefault(1);
        qualityGateRepository.save(result);

        return Constants.RESULT_STATUS_SUCCESS;
    }

}
