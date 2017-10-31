package paasta.delivery.pipeline.common.api.domain.common.qualityGate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;

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
/*        QualityGate result = new QualityGate();
        result = qualityGateRepository.findOne(qualityGate.getId());
        result.setName(qualityGate.getName());*/
        return qualityGateRepository.save(qualityGate);
    }


    public String deleteQualityGate(QualityGate qualityGate) {
        qualityGateRepository.delete(qualityGate.getId());

        return Constants.RESULT_STATUS_SUCCESS;
    }

    //기본 셋팅은 쓰지 않는걸로 결정
/*    public String qualityGateDefaultSetting(QualityGate qualityGate){
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
    }*/



}
