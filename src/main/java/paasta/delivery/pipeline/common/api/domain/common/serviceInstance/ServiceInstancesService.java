package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

/**
 * Created by hrjin on 2017-05-29.
 */
@Service
public class ServiceInstancesService {

    private final ServiceInstancesRepository serviceInstancesRepository;

    @Autowired
    public ServiceInstancesService(ServiceInstancesRepository serviceInstancesRepository) {
        this.serviceInstancesRepository = serviceInstancesRepository;
    }

    public ServiceInstances createInstances(@RequestBody ServiceInstances serviceInstances) {
        ServiceInstances newInstances = serviceInstancesRepository.save(serviceInstances);
        return newInstances;
    }

    public String deleteInstance(String id) {
        serviceInstancesRepository.delete(id);
        return Constants.RESULT_STATUS_SUCCESS;
    }

    public List<ServiceInstances> getServiceInstances() {
        List<ServiceInstances> serviceInstances = serviceInstancesRepository.findAll();
        return serviceInstances;
    }

    public ServiceInstances getServiceInstance(String id) {
        ServiceInstances serviceInstance = serviceInstancesRepository.findOne(id);
        return serviceInstance;
    }
}
