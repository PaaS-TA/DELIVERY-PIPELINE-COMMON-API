package paasta.delivery.pipeline.common.api.domain.common.user;

import org.springframework.data.jpa.domain.Specification;

/**
 * Created by hrjin on 2017-07-10.
 */
public class UserSpecifications {
    private UserSpecifications() {}

    static Specification<User> hasName(final String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }
}
