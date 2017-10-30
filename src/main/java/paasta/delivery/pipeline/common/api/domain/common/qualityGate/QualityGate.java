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

    // QualityGate id는 자동증가가 아님.
    // sonarqube에서 자동증가 되는 값을 리턴해주는데 이 값을 id 에 넣어줌.
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    /*@Column(nullable = false)
    private Long sonarQgId;*/
    @Column(name = "gate_default_yn")
    private String gateDefaultYn;

    @Transient
    private List<Long> projectIdList;
    //게이트 추가

    @Column(name = "service_instances_id")
    private String serviceInstancesId;

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


    //삭제 예정
    @Column(name = "default_yn")
    private String defaultYn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getProjectIdList() {
        return projectIdList;
    }

    public void setProjectIdList(List<Long> projectIdList) {
        this.projectIdList = projectIdList;
    }

    public String getServiceInstancesId() {
        return serviceInstancesId;
    }

    public void setServiceInstancesId(String serviceInstancesId) {
        this.serviceInstancesId = serviceInstancesId;
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

    public String getGateDefaultYn() {
        return gateDefaultYn;
    }

    public void setGateDefaultYn(String gateDefaultYn) {
        this.gateDefaultYn = gateDefaultYn;
    }

    public String getDefaultYn() {
        return defaultYn;
    }

    public void setDefaultYn(String defaultYn) {
        this.defaultYn = defaultYn;
    }

    @Override
    public String toString() {
        return "QualityGate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gateDefaultYn='" + gateDefaultYn + '\'' +
                ", projectIdList=" + projectIdList +
                ", serviceInstancesId='" + serviceInstancesId + '\'' +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", createdString='" + createdString + '\'' +
                ", lastModifiedString='" + lastModifiedString + '\'' +
                ", defaultYn='" + defaultYn + '\'' +
                '}';
    }

}
