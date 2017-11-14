package paasta.delivery.pipeline.common.api.domain.common.project;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import paasta.delivery.pipeline.common.api.common.Constants;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hrjin on 2017-06-23.
 */
@Entity
@Table(name = "sonar_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; // pid

    @Column(name = "service_instances_id", nullable = false)
    private String serviceInstancesId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    // sonarqube에서 자동증가 되는 값을 리턴해주는데 이 값을 id 에 넣어줌.
    @Column(name = "sonar_id", nullable = false)
    private long sonarId; // id -> sonarId

    @Column(name = "sonar_name", nullable = false)
    private String sonarName;

    //    @JsonProperty("sonarKey")
    @Column(name = "sonar_key", nullable = false)
    private String sonarKey; // sonarqube의 key 값

    @Column(name = "quality_profile_id", nullable = false)
    private int qualityProfileId;

    @Column(name = "quality_gate_id", nullable = false)
    private int qualityGateId;

    @Column(name = "pipeline_id", nullable = false)
    private int pipelineId;

    @Column(name = "job_id", nullable = false)
    private long jobId;

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
    private String qualityProfileKey; // key -> qualityProfileKey

    @Transient
    private String resultStatus;

    //프로젝트 연결상태
    @Transient
    private Boolean linked;

    @Transient
    private String gateDefaultYn;

    @Transient
    private String profileDefaultYn;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getSonarId() {
        return sonarId;
    }

    public void setSonarId(long sonarId) {
        this.sonarId = sonarId;
    }

    public String getSonarName() {
        return sonarName;
    }

    public void setSonarName(String sonarName) {
        this.sonarName = sonarName;
    }

    public String getSonarKey() {
        return sonarKey;
    }

    public void setSonarKey(String sonarKey) {
        this.sonarKey = sonarKey;
    }

    public int getQualityProfileId() {
        return qualityProfileId;
    }

    public void setQualityProfileId(int qualityProfileId) {
        this.qualityProfileId = qualityProfileId;
    }

    public int getQualityGateId() {
        return qualityGateId;
    }

    public void setQualityGateId(int qualityGateId) {
        this.qualityGateId = qualityGateId;
    }

    public int getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(int pipelineId) {
        this.pipelineId = pipelineId;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
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

    public String getQualityProfileKey() {
        return qualityProfileKey;
    }

    public void setQualityProfileKey(String qualityProfileKey) {
        this.qualityProfileKey = qualityProfileKey;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Boolean getLinked() {
        return linked;
    }

    public void setLinked(Boolean linked) {
        this.linked = linked;
    }

    public String getGateDefaultYn() {
        return gateDefaultYn;
    }

    public void setGateDefaultYn(String gateDefaultYn) {
        this.gateDefaultYn = gateDefaultYn;
    }

    public String getProfileDefaultYn() {
        return profileDefaultYn;
    }

    public void setProfileDefaultYn(String profileDefaultYn) {
        this.profileDefaultYn = profileDefaultYn;
    }
}
