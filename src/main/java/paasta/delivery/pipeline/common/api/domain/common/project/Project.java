package paasta.delivery.pipeline.common.api.domain.common.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hrjin on 2017-06-23.
 */
@Entity
@Table(name = "sonar_project")
public class Project {


    @Transient
    private String key;
    @Transient
    private String resultStatus;
    //프로젝트 연결상태
    @Transient
    private Boolean linked;




    @Transient
    private String gateDefaultYn;

    @Transient
    private String profileDefaultYn;

    // sonarqube에서 자동증가 되는 값을 리턴해주는데 이 값을 id 에 넣어줌.
    @Id
    @Column(name = "id")
    private Long id;


    @JsonProperty("sonarKey")
    @Column(name = "sonar_key", nullable = false)
    private String sonarKey; // sonarqube의 key 값

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "sonar_name")
    private String sonarName;


    @Column(name = "name")
    private String name;

    @Column(name = "quality_profile_id")
    private int qualityProfileId;

    @Column(name = "quality_gate_id")
    private int qualityGateId;

    @Column(name = "service_instances_id")
    private String serviceInstancesId;

    @Column(name = "pipeline_id")
    private int pipelineId;


    @Column(name = "job_id")
    private long jobId;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "last_modified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;


    /*@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectQualityGate> projectQualityGateList;*/

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quality_gate_id", updatable = true , nullable = true)
    private QualityGate qualityGate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quality_profile_id", updatable = true, nullable = true)
    private QualityProfile qualityProfile;*/


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSonarName() {
        return sonarName;
    }

    public void setSonarName(String sonarName) {
        this.sonarName = sonarName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getServiceInstancesId() {
        return serviceInstancesId;
    }

    public void setServiceInstancesId(String serviceInstancesId) {
        this.serviceInstancesId = serviceInstancesId;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getLinked() {
        return linked;
    }

    public void setLinked(Boolean linked) {
        this.linked = linked;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getGateDefaultYn() {
        return gateDefaultYn;
    }

    public String getProfileDefaultYn() {
        return profileDefaultYn;
    }

    public void setProfileDefaultYn(String profileDefaultYn) {
        this.profileDefaultYn = profileDefaultYn;
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


    @Override
    public String toString() {
        return "Project{" +
                "key='" + key + '\'' +
                ", resultStatus='" + resultStatus + '\'' +
                ", linked=" + linked +
                ", gateDefaultYn='" + gateDefaultYn + '\'' +
                ", profileDefaultYn='" + profileDefaultYn + '\'' +
                ", id=" + id +
                ", sonarKey='" + sonarKey + '\'' +
                ", projectName='" + projectName + '\'' +
                ", sonarName='" + sonarName + '\'' +
                ", name='" + name + '\'' +
                ", qualityProfileId=" + qualityProfileId +
                ", qualityGateId=" + qualityGateId +
                ", serviceInstancesId='" + serviceInstancesId + '\'' +
                ", pipelineId=" + pipelineId +
                ", jobId=" + jobId +
                ", created=" + created +
                ", lastModified=" + lastModified +
                '}';
    }

}
