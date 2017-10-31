package paasta.delivery.pipeline.common.api.domain.common.qualityProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrjin on 2017-06-26.
 */
@Service
public class QualityProfileService {
    private final QualityProfileRepository qualityProfileRepository;


    @Autowired
    public QualityProfileService(QualityProfileRepository qualityProfileRepository) {
        this.qualityProfileRepository = qualityProfileRepository;
    }

    public QualityProfile createQualityProfile(QualityProfile qualityProfile) {
        return qualityProfileRepository.save(qualityProfile);
    }



    public List<QualityProfile> getQualityProfileList(String serviceInstancesId) {
        QualityProfile qualityProfile = new QualityProfile();
        qualityProfile.setProfileDefaultYn("Y");
        qualityProfile.setServiceInstancesId(serviceInstancesId);
        return qualityProfileRepository.findAllByserviceInstancesIdOrProfileDefaultYn(qualityProfile.getServiceInstancesId(), qualityProfile.getProfileDefaultYn());
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
   /*     QualityProfile result = new QualityProfile();
        result = qualityProfileRepository.findOne(qualityProfile.getId());
        result.setName(qualityProfile.getName());*/
        return qualityProfileRepository.save(qualityProfile);
    }

    /**
     * QualityProfile default Setting
     *
     * @return
     */
/*    public String qualityProfileDefaultSetting(QualityProfile qualityProfile){
        QualityProfile result = new QualityProfile();

        result.setProfileDefaultYn("Y");
        result.setServiceInstancesId(qualityProfile.getServiceInstancesId());


        result = qualityProfileRepository.findOne(qualityProfile.getId());
        result.setProfileDefaultYn("N");
        qualityProfileRepository.save(result);
        return Constants.RESULT_STATUS_SUCCESS;
    }*/


    public QualityProfile getQualityProfile(long id){
        return qualityProfileRepository.findOne(id);
    }
}
