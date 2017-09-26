package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.springframework.data.jpa.domain.Specification;

/**
 * Created by hrjin on 2017-07-19.
 */
public class InstanceUseSpecifications {
    private InstanceUseSpecifications() {}

    static Specification<InstanceUse> hasName(final String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    static Specification<InstanceUse> equalAuthName(final String authName) {
        return (root, query, cb) -> cb.equal(root.get("authName"), authName);
    }
}
