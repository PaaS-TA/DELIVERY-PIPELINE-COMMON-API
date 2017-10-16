package paasta.delivery.pipeline.common.api.domain.common.job.history;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import paasta.delivery.pipeline.common.api.common.Constants;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.job.history
 *
 * @author REX
 * @version 1.0
 * @since 6/29/2017
 */
@Entity
@Table(name = "job_history")
public class JobHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "job_id", nullable = false)
    private int jobId;

    @Column(name = "previous_job_number", nullable = false)
    private int previousJobNumber;

    @Column(name = "job_number", nullable = false)
    private int jobNumber;

    @Column(name = "duration")
    private long duration;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "file_id")
    private long fileId;

    @Column(name = "trigger_type")
    private String triggerType;

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

    @Formula("(SELECT j.job_name FROM job j WHERE j.pipeline_id = " +
            "(SELECT j1.pipeline_id FROM job j1 WHERE j1.id = " +
            "(SELECT jh1.job_id FROM job_history jh1 WHERE jh1.id = id))" +
            " AND j.group_order = (SELECT j3.group_order FROM job j3 WHERE j3.id = " +
            "(SELECT jh2.job_id FROM job_history jh2 WHERE jh2.id = id))" +
            " AND j.job_order = (SELECT j4.job_order - 1 FROM job j4 WHERE j4.id = " +
            "(SELECT jh3.job_id FROM job_history jh3 WHERE jh3.id = id)))")
    private String previousJobName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getPreviousJobNumber() {
        return previousJobNumber;
    }

    public void setPreviousJobNumber(int previousJobNumber) {
        this.previousJobNumber = previousJobNumber;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
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

    public String getPreviousJobName() {
        return previousJobName;
    }

    public void setPreviousJobName(String previousJobName) {
        this.previousJobName = previousJobName;
    }

}
