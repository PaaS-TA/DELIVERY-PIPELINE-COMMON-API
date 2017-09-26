package paasta.delivery.pipeline.common.api.domain.common.qualityProfile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationRepository;
import paasta.delivery.pipeline.common.api.domain.common.qualityGate.QualityGate;

import java.util.List;

/**
 * Created by hrjin on 2017-06-26.
 */

@RestController
@RequestMapping("/qualityProfile")
public class QualityProfileController {
    private final QualityProfileService qualityProfileService;
    private final ProjectRelationRepository projectRelationRepository;

    @Autowired
    public QualityProfileController(QualityProfileService qualityProfileService, ProjectRelationRepository projectRelationRepository) {
        this.qualityProfileService = qualityProfileService;
        this.projectRelationRepository = projectRelationRepository;
    }





    /**
     * QualityProfile 상세 조회
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public QualityProfile getQualityProfile(@PathVariable Long id) {
        QualityProfile getQualityProfile = qualityProfileService.getQualityProfile(id);
        List<Long> projectIdList = projectRelationRepository.findIdByQualityProfileId(id);
        getQualityProfile.setProjectIdList(projectIdList);
        return getQualityProfile;
    }


    /**
     * QualityProfile 수정
     *
     * @param id
     * @param qualityProfile
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public QualityProfile updateQualityProfile(@PathVariable Long id, @RequestBody QualityProfile qualityProfile) {
        qualityProfile.setId(id);
        QualityProfile updateQualityProfile = qualityProfileService.updateQualityProfile(qualityProfile);
        return updateQualityProfile;
    }


    /**
     * QualityProfile 삭제
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteQualityProfile(@PathVariable Long id) {
        return qualityProfileService.deleteQualityProfile(id);
    }


    //////////////////////////////////////////////////////////

    /**
     * QualityProfile 목록 조회
     *
     * @return
     */
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<QualityProfile> getQualityProfileList(@RequestParam String serviceInstancesId) {
//        List<QualityProfile> getQualityProfileList = qualityProfileService.getQualityProfileList(serviceInstancesId);
//        return getQualityProfileList;
//    }
    //시연후 수정
    @RequestMapping(value = "/qualityProfileList", method = RequestMethod.GET)
    public List getQualityProfileList() {
        return qualityProfileService.getQualityProfileList();
    }


    /**
     * QualityProfile 생성
     *
     * @param qualityProfile
     * @return
     */
    @RequestMapping(value = "/qualityProfilCreate", method = RequestMethod.PUT)
    public QualityProfile createQualityProfile(@RequestBody QualityProfile qualityProfile) {
        QualityProfile newQualityProfile = qualityProfileService.createQualityProfile(qualityProfile);
        return newQualityProfile;
    }

    /**
     * QualityProfile 복제
     *
     * @return
     */
    @RequestMapping(value = "/qualityProfileCopy", method = RequestMethod.PUT)
    public QualityProfile qualityProfileCopy(@RequestBody QualityProfile qualityProfile) {
        return qualityProfileService.qualityProfileCopy(qualityProfile);
    }

    /**
     * QualityProfile 삭제
     *
     * @return
     */
    @RequestMapping(value = "/qualityProfileDelete", method = RequestMethod.POST)
    public String deleteQualityProfile(@RequestBody QualityProfile qualityProfile) {
        return qualityProfileService.deleteQualityProfile(qualityProfile);
    }

    /**
     * QualityProfile 수정
     *
     * @return
     */
    @RequestMapping(value = "/qualityProfileUpdate", method = RequestMethod.POST)
    public QualityProfile updateQualityProfile(@RequestBody QualityProfile qualityProfile) {
        return qualityProfileService.updateQualityProfile(qualityProfile);
    }

    /**
     * QualityProfile default Setting
     *
     * @return
     */
    @RequestMapping(value = "/qualityProfilDefaultSetting",method = RequestMethod.PUT)
    public String qualityProfileDefaultSetting(@RequestBody QualityProfile qualityProfile){
        return qualityProfileService.qualityProfileDefaultSetting(qualityProfile);
    }

}
