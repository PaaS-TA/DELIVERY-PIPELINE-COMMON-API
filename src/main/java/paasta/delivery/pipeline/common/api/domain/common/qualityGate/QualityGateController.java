package paasta.delivery.pipeline.common.api.domain.common.qualityGate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paasta.delivery.pipeline.common.api.domain.common.projectRelation.ProjectRelationService;

import java.util.List;

/**
 * Created by hrjin on 2017-06-22.
 */

@RestController
@RequestMapping("/qualityGate")
public class QualityGateController {
    private final QualityGateService qualityGateService;
    private final ProjectRelationService projectRelationService;

    @Autowired
    public QualityGateController(QualityGateService qualityGateService, ProjectRelationService projectRelationService) {
        this.qualityGateService = qualityGateService;
        this.projectRelationService = projectRelationService;
    }


    /**
     * QualityGate 상세 조회
     *
     * @param id
     * @return QualityGate detail
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public QualityGate getQualityGate(@PathVariable Long id) {
        QualityGate qualityGate = qualityGateService.getQualityGate(id);
        List<Long> projectIdList = projectRelationService.getProjectIdList(id);
        qualityGate.setProjectIdList(projectIdList);
        return qualityGate;
    }


    ////////////////////////////////////////////////////

    /**
     * QualityGate 목록 조회
     *
     * @return QualityGate List
     */
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<QualityGate> getQualityGateList(@RequestParam String serviceInstancesId){
//        List<QualityGate> getList = qualityGateService.getQualityGateList(serviceInstancesId);
//        return getList;
//    }
    @RequestMapping(value = "/qualityGateList", method = RequestMethod.GET)
    public List getQualityGateList(@RequestParam String serviceInstancesId) {
        return qualityGateService.getQualityGateList(serviceInstancesId);
    }


    /**
     *  QualityGate 조건리스트
     *
     * @param qualityGate
     * @param qualityGate
     * @return
     */
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public QualityGate getQualityGateCondition(@RequestBody QualityGate qualityGate){
//
//    }


    /**
     * QualityGate 복제
     *
     * @param qualityGate
     * @param qualityGate
     * @return
     */
    @RequestMapping(value = "/qualityGateCopy", method = RequestMethod.POST)
    public QualityGate copyQualityGate(@RequestBody QualityGate qualityGate) {
        return qualityGateService.copyQualityGate(qualityGate);
    }


    /**
     * QualityGate 생성
     *
     * @param reqQualityGate
     * @return QualityGate
     */
    @RequestMapping(value = "/qualityGateCreate", method = RequestMethod.PUT)
    public QualityGate createQualityGate(@RequestBody QualityGate reqQualityGate) {
        QualityGate newQualityGate = qualityGateService.createQualityGate(reqQualityGate);
        return newQualityGate;
    }

    /**
     * QualityGate 수정
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/qualityGateUpdate", method = RequestMethod.PUT)
    public QualityGate updateQualityGate(@RequestBody QualityGate qualityGate) {
        QualityGate updateQualityGate = qualityGateService.updateQualityGate(qualityGate);
        return updateQualityGate;
    }


    /**
     * QualityGate 삭제
     *
     * @param
     * @return delete success message
     */
    @RequestMapping(value = "/qualityGateDelete", method = RequestMethod.DELETE)
    public String deleteQualityGate(@RequestBody QualityGate qualityGate) {
        return qualityGateService.deleteQualityGate(qualityGate);
    }

    @RequestMapping(value = "/qualityGateDefaultSetting", method = RequestMethod.PUT)
    public String qualityGateDefaultSetting(@RequestBody QualityGate qualityGate){
        return qualityGateService.qualityGateDefaultSetting(qualityGate);
    }

}
