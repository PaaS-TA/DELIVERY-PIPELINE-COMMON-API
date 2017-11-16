package paasta.delivery.pipeline.common.api.domain.common.qualityGate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Created by hrjin on 2017-06-22.
 */

@RestController
@RequestMapping("/qualityGate")
public class QualityGateController {
    private final QualityGateService qualityGateService;

    @Autowired
    public QualityGateController(QualityGateService qualityGateService) {
        this.qualityGateService = qualityGateService;
    }





    /**
     * QualityGate 목록 조회 -> sona에서 정보 추출로 변경
     *
     * @return QualityGate List
     */
//    @RequestMapping(value = "/qualityGateList", method = RequestMethod.GET)
//    public List getQualityGateList(@RequestParam String serviceInstancesId) {
//        return qualityGateService.getQualityGateList(serviceInstancesId);
//    }


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
    @RequestMapping(value = "/qualityGateCreate", method = RequestMethod.POST)
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

/*    @RequestMapping(value = "/qualityGateDefaultSetting", method = RequestMethod.PUT)
    public String qualityGateDefaultSetting(@RequestBody QualityGate qualityGate){
        return qualityGateService.qualityGateDefaultSetting(qualityGate);
    }*/

    @RequestMapping(value = "/getQualityGate" , method = RequestMethod.GET)
    public QualityGate getQualityGate(long id){
        return qualityGateService.getQualityGate(id);
    }

}
