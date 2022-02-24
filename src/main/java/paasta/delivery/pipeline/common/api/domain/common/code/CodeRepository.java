package paasta.delivery.pipeline.common.api.domain.common.code;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.code
 *
 * @author REX
 * @version 1.0
 * @since 11 /6/2017
 */
@Repository
@Transactional
public interface CodeRepository extends JpaRepository<Code, Long> {

    /**
     * Find all by service instances id order by created desc list.
     *
     * @param codeGroup the codeGroup
     * @return the list
     */
    List<Code> findAllByCodeGroupOrderByCodeOrderAsc(String codeGroup);

}
