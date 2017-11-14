package paasta.delivery.pipeline.common.api.domain.common.qualityProfile;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; // pid

    @Column(name = "service_instances_id", nullable = false)
    private String serviceInstancesId;

    @Column(name = "quality_profile_id", nullable = false)
    private int qualityProfileId; // id -> qualityProfileId

    @Column(name = "quality_profile_name", nullable = false)
    private String qualityProfileName; // name -> qualityProfileName

    //    @JsonProperty("key")
    @Column(name = "quality_profile_key", nullable = false)
    private String qualityProfileKey; // sonarKey -> qualityProfileKey

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "language_name", nullable = false)
    private String languageName;

    @Column(name = "profile_default_yn", nullable = false)
    private String profileDefaultYn;

    @Column(name = "active_rule_count", nullable = false)
    private int activeRuleCount;

    @Column(name = "active_deprecated_rule_count", nullable = false)
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

    @Transient
    private String resultStatus;

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

    public int getQualityProfileId() {
        return qualityProfileId;
    }

    public void setQualityProfileId(int qualityProfileId) {
        this.qualityProfileId = qualityProfileId;
    }

    public String getQualityProfileName() {
        return qualityProfileName;
    }

    public void setQualityProfileName(String qualityProfileName) {
        this.qualityProfileName = qualityProfileName;
    }

    public String getQualityProfileKey() {
        return qualityProfileKey;
    }

    public void setQualityProfileKey(String qualityProfileKey) {
        this.qualityProfileKey = qualityProfileKey;
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

    public String getProfileDefaultYn() {
        return profileDefaultYn;
    }

    public void setProfileDefaultYn(String profileDefaultYn) {
        this.profileDefaultYn = profileDefaultYn;
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

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public List<Long> getProjectIdList() {
        return projectIdList;
    }

    public void setProjectIdList(List<Long> projectIdList) {
        this.projectIdList = projectIdList;
    }

}
