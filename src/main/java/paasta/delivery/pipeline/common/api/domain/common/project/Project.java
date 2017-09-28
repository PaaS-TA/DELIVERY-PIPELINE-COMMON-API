package paasta.delivery.pipeline.common.api.domain.common.project;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

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


    // sonarqube에서 자동증가 되는 값을 리턴해주는데 이 값을 id 에 넣어줌.
    @Id
    @Column(name = "id")
    private Long id;

    @JsonProperty("sonarKey")
    @Column(name = "sonar_key", nullable = false)
    private String sonarKey; // sonarqube의 key 값

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "qualifier")
    private String qualifier;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_guid")
    private String orgGuid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_guid")
    private String userGuid;

    @Column(name = "quality_profile_id")
    private int qualityProfileId;

    @Column(name = "quality_gate_id")
    private int qualityGateId;

    @Column(name = "service_instances_id")
    private String serviceInstancesId;

    /*@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectQualityGate> projectQualityGateList;*/

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quality_gate_id", updatable = true , nullable = true)
    private QualityGate qualityGate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quality_profile_id", updatable = true, nullable = true)
    private QualityProfile qualityProfile;*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
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

    @Override
    public String toString() {
        return "Project{" +
                "key='" + key + '\'' +
                ", resultStatus='" + resultStatus + '\'' +
                ", linked=" + linked +
                ", id=" + id +
                ", sonarKey='" + sonarKey + '\'' +
                ", name='" + name + '\'' +
                ", qualifier='" + qualifier + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgGuid='" + orgGuid + '\'' +
                ", userName='" + userName + '\'' +
                ", userGuid='" + userGuid + '\'' +
                ", qualityProfileId=" + qualityProfileId +
                ", qualityGateId=" + qualityGateId +
                ", serviceInstancesId='" + serviceInstancesId + '\'' +
                '}';
    }

}
