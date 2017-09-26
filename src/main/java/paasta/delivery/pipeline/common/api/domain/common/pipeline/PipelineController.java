package paasta.delivery.pipeline.common.api.domain.common.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import paasta.delivery.pipeline.common.api.domain.common.serviceInstance.ServiceInstances;
import paasta.delivery.pipeline.common.api.domain.common.serviceInstance.ServiceInstancesRepository;

/**
 * Created by user on 2017-05-16.
 */
@RestController
@RequestMapping("/pipeline")
public class PipelineController {

    private static final int PAGE_SIZE = 5;
    private final PipelineService pipelineService;
    private final ServiceInstancesRepository serviceInstancesRepository;


    @Autowired
    public PipelineController(PipelineService pipelineService, ServiceInstancesRepository serviceInstancesRepository) {
        this.pipelineService = pipelineService;
        this.serviceInstancesRepository = serviceInstancesRepository;
    }


    /**
     * 파이프라인 전체 목록 및 검색 목록 조회
     *
     * @param suid
     * @param name
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/{suid}/list", method = RequestMethod.GET)
    public PipelineList getPipelineList(@PathVariable String suid,
                                        @RequestParam(value = "name", required = false) String name,
                                        @PageableDefault(sort = "created", direction = Sort.Direction.DESC, size = PAGE_SIZE) Pageable pageable) {

        return pipelineService.getPipelineList(suid, name, pageable);
    }



    /**
     * 파이프라인 정보 조회
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pipeline getPipeline(@PathVariable Long id) {
        Pipeline pipeline = pipelineService.getPipeline(id);
        return pipeline;
    }



    /**
     * 파이프라인 생성
     *
     * @param serviceInstancesId
     * @param pipeline
     * @return
     */
    @RequestMapping(value = "/{serviceInstancesId}", method = RequestMethod.POST)
    public Pipeline createPipeline(@PathVariable String serviceInstancesId, @RequestBody Pipeline pipeline) {

        ServiceInstances serviceInstances = serviceInstancesRepository.findOne(serviceInstancesId);

        if (serviceInstances != null) {
            String name = pipeline.getName();
            String description = pipeline.getDescription();
            Pipeline newPipeline = pipelineService.createPipeline(new Pipeline(name, description, serviceInstances));

            return newPipeline;
        }
        return pipelineService.getPipeline(pipeline.getId());
    }



    /**
     * 파이프라인 수정
     *
     * @param id
     * @param pipeline
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Pipeline updatePipeline(@PathVariable Long id, @RequestBody Pipeline pipeline) {
        Pipeline modifyPipeline = pipelineService.updatePipeline(id, pipeline);
        return modifyPipeline;
    }



    /**
     * 파이프라인 삭제
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deletePipeline(@PathVariable Long id) {
        return pipelineService.deletePipeline(id);
    }

}
