package paasta.delivery.pipeline.common.api.domain.common.qualityProfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import paasta.delivery.pipeline.common.api.common.Constants;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by hrjin on 2017-06-26.
 */
@Entity
@Table(name = "quality_profile")
public class QualityProfile {


    @Transient
    private String resultStatus;
    // QualityProfile id는 자동증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "sonar_key", nullable = false)
    @JsonProperty("key")
    private String sonarKey;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "language_name", nullable = false)
    private String languageName;

    @Column(name = "default_yn")
    private String defaultYn;

    @Column(name = "service_instances_id", nullable = false)
    private String serviceInstancesId;

    @Transient
    private List<Long> projectIdList;

    @Column(name = "active_rule_count")
    private int activeRuleCount;

    @Column(name = "active_deprecated_rule_count")
    private int activeDeprecatedRuleCount;

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

    public String getSonarKey() {
        return sonarKey;
    }

    public void setSonarKey(String sonarKey) {
        this.sonarKey = sonarKey;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public List<Long> getProjectIdList() {
        return projectIdList;
    }

    public void setProjectIdList(List<Long> projectIdList) {
        this.projectIdList = projectIdList;
    }

    public int getActiveRuleCount() {
        return activeRuleCount;
    }

    public void setActiveRuleCount(int activeRuleCount) {
        this.activeRuleCount = activeRuleCount;
    }

    public int getActiveDeprecatedRuleCount() {
        return activeDeprecatedRuleCount;
    }

    public void setActiveDeprecatedRuleCount(int activeDeprecatedRuleCount) {
        this.activeDeprecatedRuleCount = activeDeprecatedRuleCount;
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
        return new SimpleDateFormat(Constants.STRING_DATE_TYPE, Locale.KOREA).format(created);
    }

    public void setCreatedString(String createdString) {
        this.createdString = createdString;
    }

    public String getLastModifiedString() {
        return new SimpleDateFormat(Constants.STRING_DATE_TYPE, Locale.KOREA).format(lastModified);
    }

    public void setLastModifiedString(String lastModifiedString) {
        this.lastModifiedString = lastModifiedString;
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

    public String getDefaultYn() {
        return defaultYn;
    }

    public void setDefaultYn(String defaultYn) {
        this.defaultYn = defaultYn;
    }

    @Override
    public String toString() {
        return "QualityProfile{" +
                "resultStatus='" + resultStatus + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sonarKey='" + sonarKey + '\'' +
                ", language='" + language + '\'' +
                ", languageName='" + languageName + '\'' +
                ", defaultYn='" + defaultYn + '\'' +
                ", serviceInstancesId='" + serviceInstancesId + '\'' +
                ", projectIdList=" + projectIdList +
                ", activeRuleCount=" + activeRuleCount +
                ", activeDeprecatedRuleCount=" + activeDeprecatedRuleCount +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", createdString='" + createdString + '\'' +
                ", lastModifiedString='" + lastModifiedString + '\'' +
                '}';
    }

}
