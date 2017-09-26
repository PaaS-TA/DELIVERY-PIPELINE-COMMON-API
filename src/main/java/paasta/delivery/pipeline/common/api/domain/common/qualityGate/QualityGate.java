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
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /*@Column(nullable = false)
    private Long sonarQgId;*/
    @Column(name = "quality_gate_default")
    private int qualityGateDefault;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_guid")
    private String orgGuid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_guid")
    private String userGuid;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
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

    public int getQualityGateDefault() {
        return qualityGateDefault;
    }

    public void setQualityGateDefault(int qualityGateDefault) {
        this.qualityGateDefault = qualityGateDefault;
    }

    @Override
    public String toString() {
        return "QualityGate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qualityGateDefault=" + qualityGateDefault +
                ", orgName='" + orgName + '\'' +
                ", orgGuid='" + orgGuid + '\'' +
                ", userName='" + userName + '\'' +
                ", userGuid='" + userGuid + '\'' +
                ", projectIdList=" + projectIdList +
                ", serviceInstancesId='" + serviceInstancesId + '\'' +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", createdString='" + createdString + '\'' +
                ", lastModifiedString='" + lastModifiedString + '\'' +
                '}';
    }

}
