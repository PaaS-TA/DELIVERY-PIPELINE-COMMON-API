package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hrjin on 2017-05-29.
 */
@RestController
@RequestMapping("/serviceInstance")
public class ServiceInstancesController {

    private final ServiceInstancesService serviceInstancesService;


    @Autowired
    public ServiceInstancesController(ServiceInstancesService serviceInstancesService) {
        this.serviceInstancesService = serviceInstancesService;
    }

    /*
    * 나중에 broker 에서 서비스 인스턴스 생성하도록 연결
    *
    * */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ServiceInstances createInstances(@RequestBody ServiceInstances serviceInstances) {
        ServiceInstances newInstances = serviceInstancesService.createInstances(serviceInstances);
        return newInstances;
    }


    /*
    * 인스턴스 삭제
    *
    * */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteInstances(@PathVariable String id) {
        return serviceInstancesService.deleteInstance(id);
    }

    /*
    *  인스턴스 목록 조회
    *
    * */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ServiceInstances> getServiceInstancesList() {
        List<ServiceInstances> serviceInstancesList = serviceInstancesService.getServiceInstances();
        return serviceInstancesList;
    }

    /*
    *  인스턴스 상세 조회
    *
    * */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ServiceInstances getServiceInstance(@PathVariable String id) {
        ServiceInstances serviceInstance = serviceInstancesService.getServiceInstance(id);
        return serviceInstance;
    }
}
