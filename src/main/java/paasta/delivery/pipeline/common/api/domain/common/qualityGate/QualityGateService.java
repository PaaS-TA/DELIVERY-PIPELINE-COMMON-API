package paasta.delivery.pipeline.common.api.domain.common.qualityGate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationRepository;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationService;
import paasta.delivery.pipeline.common.api.domain.common.qualityProfile.QualityProfile;

import java.util.List;

/**
 * Created by hrjin on 2017-06-22.
 */
@Service
public class QualityGateService {

    private final QualityGateRepository qualityGateRepository;

    @Autowired
    public QualityGateService(QualityGateRepository qualityGateRepository) {
        this.qualityGateRepository = qualityGateRepository;

    }


    public QualityGate createQualityGate(QualityGate reqQualityGate) {
        return qualityGateRepository.save(reqQualityGate);
    }


    public QualityGate getQualityGate(long id) {
        return qualityGateRepository.findOne(id);
    }


    //////////////////////////////////////////////////
//    public List<QualityGate> getQualityGateList(String serviceInstancesId) {
//        return qualityGateRepository.findAllByserviceInstancesId(serviceInstancesId);
//    }

    public List<QualityGate> getQualityGateList(String serviceInstancesId) {
        QualityGate param = new QualityGate();
        param.setGateDefaultYn("Y");
        return qualityGateRepository.findByserviceInstancesIdOrGateDefaultYn(serviceInstancesId, param.getGateDefaultYn());
    }

    public QualityGate copyQualityGate(QualityGate qualityGate) {
        return qualityGateRepository.save(qualityGate);
    }

    public QualityGate updateQualityGate(QualityGate qualityGate) {
        QualityGate result = new QualityGate();
        result = qualityGateRepository.findOne(qualityGate.getId());
        result.setName(qualityGate.getName());
        return qualityGateRepository.save(result);
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
        //삭제예정
        QualityGate result = new QualityGate();
        result.setGateDefaultYn("Y");
        result.setServiceInstancesId(qualityGate.getServiceInstancesId());
        result = qualityGateRepository.findByServiceInstancesIdAndGateDefaultYn(result.getServiceInstancesId(),result.getGateDefaultYn());

        if(result != null){
            result.setGateDefaultYn("N");
            qualityGateRepository.save(result);
        }


        result = qualityGateRepository.findOne(qualityGate.getId());
        result.setGateDefaultYn("Y");
        qualityGateRepository.save(result);

        return Constants.RESULT_STATUS_SUCCESS;
    }



}
