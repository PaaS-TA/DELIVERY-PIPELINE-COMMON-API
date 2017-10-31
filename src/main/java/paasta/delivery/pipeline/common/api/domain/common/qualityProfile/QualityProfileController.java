package paasta.delivery.pipeline.common.api.domain.common.qualityProfile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paasta.delivery.pipeline.common.api.domain.common.qualityGate.QualityGate;

import java.util.List;

/**
 * Created by hrjin on 2017-06-26.
 */

@RestController
@RequestMapping("/qualityProfile")
public class QualityProfileController {
    private final QualityProfileService qualityProfileService;


    @Autowired
    public QualityProfileController(QualityProfileService qualityProfileService) {
        this.qualityProfileService = qualityProfileService;

    }



    /**
     * QualityProfile 목록 조회
     *
     * @return
     */
    @RequestMapping(value = "/qualityProfileList", method = RequestMethod.GET)
    public List getQualityProfileList(String serviceInstancesId) {
        return qualityProfileService.getQualityProfileList(serviceInstancesId);
    }


    /**
     * QualityProfile 생성
     *
     * @param qualityProfile
     * @return
     */
    @RequestMapping(value = "/qualityProfilCreate", method = RequestMethod.POST)
    public QualityProfile createQualityProfile(@RequestBody QualityProfile qualityProfile) {
        QualityProfile newQualityProfile = qualityProfileService.createQualityProfile(qualityProfile);
        return newQualityProfile;
    }

    /**
     * QualityProfile 복제
     *
     * @return
     */
    @RequestMapping(value = "/qualityProfileCopy", method = RequestMethod.POST)
    public QualityProfile qualityProfileCopy(@RequestBody QualityProfile qualityProfile) {
        return qualityProfileService.qualityProfileCopy(qualityProfile);
    }

    /**
     * QualityProfile 삭제
     *
     * @return
     */
    @RequestMapping(value = "/qualityProfileDelete", method = RequestMethod.DELETE)
    public String deleteQualityProfile(@RequestBody QualityProfile qualityProfile) {
        return qualityProfileService.deleteQualityProfile(qualityProfile);
    }

    /**
     * QualityProfile 수정
     *
     * @return
     */
    @RequestMapping(value = "/qualityProfileUpdate", method = RequestMethod.PUT)
    public QualityProfile updateQualityProfile(@RequestBody QualityProfile qualityProfile) {
        return qualityProfileService.updateQualityProfile(qualityProfile);
    }

    /**
     * QualityProfile default Setting
     *
     * @return
     */
/*
    @RequestMapping(value = "/qualityProfilDefaultSetting",method = RequestMethod.PUT)
    public String qualityProfileDefaultSetting(@RequestBody QualityProfile qualityProfile){
        return qualityProfileService.qualityProfileDefaultSetting(qualityProfile);
    }
*/


    @RequestMapping(value = "/getQualityProfile" ,method = RequestMethod.GET)
    public QualityProfile getQualityProfile(long id){
        return qualityProfileService.getQualityProfile(id);
    }


}
