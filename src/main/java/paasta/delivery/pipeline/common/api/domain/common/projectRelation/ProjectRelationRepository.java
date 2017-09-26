package paasta.delivery.pipeline.common.api.domain.common.projectRelation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dojun on 2017-06-28.
 */
@Repository
public interface ProjectRelationRepository extends JpaRepository<ProjectRelation, Long> {

    @Query("SELECT id from ProjectRelation WHERE qualityGateId = :id")
    List<Long> findIdByQualityGateId(@Param("id") Long id);

    @Query("SELECT id from ProjectRelation WHERE qualityProfileId = :id")
    List<Long> findIdByQualityProfileId(@Param("id") Long id);
}
