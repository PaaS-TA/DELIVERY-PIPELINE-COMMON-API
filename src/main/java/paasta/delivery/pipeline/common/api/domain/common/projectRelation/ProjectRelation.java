package paasta.delivery.pipeline.common.api.domain.common.projectRelation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dojun on 2017-06-28.
 */
@Entity
@Table(name = "project_relation")
public class ProjectRelation {
    @Id
    @Column(name = "id")
    private Long id; // projectId 와 동일.

    @Column(name = "quality_profile_id")
    private Long qualityProfileId;

    @Column(name = "quality_gate_id")
    private Long qualityGateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQualityProfileId() {
        return qualityProfileId;
    }

    public void setQualityProfileId(Long qualityProfileId) {
        this.qualityProfileId = qualityProfileId;
    }

    public Long getQualityGateId() {
        return qualityGateId;
    }

    public void setQualityGateId(Long qualityGateId) {
        this.qualityGateId = qualityGateId;
    }

    @Override
    public String toString() {
        return "ProjectRelation{" +
                "id=" + id +
                ", qualityProfileId=" + qualityProfileId +
                ", qualityGateId=" + qualityGateId +
                '}';
    }


}
