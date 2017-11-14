package paasta.delivery.pipeline.common.api.domain.common.qualityGate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import paasta.delivery.pipeline.common.api.common.Constants;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by hrjin on 2017-06-22.
 */
@Entity
@Table(name = "quality_gate")
public class QualityGate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; // pid

    @Column(name = "service_instances_id", nullable = false)
    private String serviceInstancesId;

    // sonarqube에서 자동증가 되는 값을 리턴해주는데 이 값을 id 에 넣어줌.
    @Column(name = "quality_gate_id", nullable = false)
    private int qualityGateId; // id -> qualityGateId

    @Column(name = "quality_gate_name", nullable = false)
    private String qualityGateName; // name -> qualityGateName

    @Column(name = "gate_default_yn", nullable = false)
    private String gateDefaultYn;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "last_modified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Transient
    private String createdString;

    @Transient
    private String lastModifiedString;

    @Transient
    private List<Long> projectIdList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceInstancesId() {
        return serviceInstancesId;
    }

    public void setServiceInstancesId(String serviceInstancesId) {
        this.serviceInstancesId = serviceInstancesId;
    }

    public int getQualityGateId() {
        return qualityGateId;
    }

    public void setQualityGateId(int qualityGateId) {
        this.qualityGateId = qualityGateId;
    }

    public String getQualityGateName() {
        return qualityGateName;
    }

    public void setQualityGateName(String qualityGateName) {
        this.qualityGateName = qualityGateName;
    }

    public String getGateDefaultYn() {
        return gateDefaultYn;
    }

    public void setGateDefaultYn(String gateDefaultYn) {
        this.gateDefaultYn = gateDefaultYn;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getCreatedString() {
        return (created != null) ? new SimpleDateFormat(Constants.STRING_DATE_TYPE, Locale.KOREA).format(created) : null;
    }

    public void setCreatedString(String createdString) {
        this.createdString = createdString;
    }

    public String getLastModifiedString() {
        return (lastModified != null) ? new SimpleDateFormat(Constants.STRING_DATE_TYPE, Locale.KOREA).format(lastModified) : null;
    }

    public void setLastModifiedString(String lastModifiedString) {
        this.lastModifiedString = lastModifiedString;
    }

    public List<Long> getProjectIdList() {
        return projectIdList;
    }

    public void setProjectIdList(List<Long> projectIdList) {
        this.projectIdList = projectIdList;
    }

}
