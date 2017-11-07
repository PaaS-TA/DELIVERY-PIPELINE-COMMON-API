package paasta.delivery.pipeline.common.api.domain.common.cf.url;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import paasta.delivery.pipeline.common.api.common.Constants;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.cf.url
 *
 * @author REX
 * @version 1.0
 * @since 11/6/2017
 */
@Entity
@Table(name = "cf_url")
public class CfUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "service_instances_id", nullable = false)
    private String serviceInstancesId;

    @Column(name = "cf_api_name", nullable = false)
    private String cfApiName;

    @Column(name = "cf_api_url")
    private String cfApiUrl;

    @Column(name = "user_id", nullable = false)
    private String userId;

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

    public String getCfApiName() {
        return cfApiName;
    }

    public void setCfApiName(String cfApiName) {
        this.cfApiName = cfApiName;
    }

    public String getCfApiUrl() {
        return cfApiUrl;
    }

    public void setCfApiUrl(String cfApiUrl) {
        this.cfApiUrl = cfApiUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

}
