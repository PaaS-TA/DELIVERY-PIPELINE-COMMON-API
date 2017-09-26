package paasta.delivery.pipeline.common.api.domain.common.pipeline;

import org.springframework.data.jpa.domain.Specification;

/**
 * Created by hrjin on 2017-05-24.
 */
public class PipelineSpecifications {
    private PipelineSpecifications() {}

    static Specification<Pipeline> hasName(final String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

}
