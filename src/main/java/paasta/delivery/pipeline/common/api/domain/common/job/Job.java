package paasta.delivery.pipeline.common.api.domain.common.job;

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
 * paasta.delivery.pipeline.common.api.job
 *
 * @author REX
 * @version 1.0
 * @since 5/17/2017
 */
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "service_instances_id", nullable = false)
    private String serviceInstancesId;

    @Column(name = "pipeline_id", nullable = false)
    private int pipelineId;

    @Column(name = "job_type", nullable = false)
    private String jobType;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @Column(name = "job_guid", nullable = false)
    private String jobGuid;

    @Column(name = "group_order", nullable = false)
    private int groupOrder;

    @Column(name = "job_order", nullable = false)
    private int jobOrder;

    @Column(name = "builder_type")
    private String builderType;

    @Column(name = "build_job_id")
    private int buildJobId;

    @Column(name = "job_trigger")
    private String jobTrigger;

    @Column(name = "post_action_yn")
    private String postActionYn;

    @Column(name = "repository_type")
    private String repositoryType;

    @Column(name = "repository_url")
    private String repositoryUrl;

    @Column(name = "repository_id")
    private String repositoryId;

    @Column(name = "repository_account_id")
    private String repositoryAccountId;

    @Column(name = "repository_account_password")
    private String repositoryAccountPassword;

    @Column(name = "repository_branch")
    private String repositoryBranch;

    @Column(name = "repository_commit_revision")
    private String repositoryCommitRevision;

    @Column(name = "cf_info_id")
    private int cfInfoId;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_url")
    private String appUrl;

    @Column(name = "deploy_type")
    private String deployType;

    @Column(name = "blue_green_deploy_status")
    private String blueGreenDeployStatus;

    @Column(name = "deploy_target_org")
    private String deployTargetOrg;

    @Column(name = "deploy_target_space")
    private String deployTargetSpace;

    @Column(name = "manifest_use_yn")
    private String manifestUseYn;

    @Column(name = "manifest_script")
    private String manifestScript;

    @Column(name = "inspection_project_id")
    private String inspectionProjectId;

    @Column(name = "inspection_project_name")
    private String inspectionProjectName;

    @Column(name = "inspection_project_key")
    private String inspectionProjectKey;

    @Column(name = "inspection_profile_id")
    private String inspectionProfileId;

    @Column(name = "inspection_gate_id")
    private String inspectionGateId;

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

    @Formula("(SELECT j1.job_name FROM job j1 WHERE j1.id = build_job_id)")
    private String buildJobName;

    @Formula("(SELECT jh.status FROM job_history jh WHERE jh.job_id = id ORDER BY jh.last_modified DESC LIMIT 1)")
    private String lastJobStatus;

    @Formula("(SELECT jh.last_modified FROM job_history jh WHERE jh.job_id = id ORDER BY jh.last_modified DESC LIMIT 1)")
    private String lastJobModified;

    @Formula("(SELECT jh.job_number FROM job_history jh WHERE jh.job_id = id AND (jh.status = 'SUCCESS' OR jh.status = 'ABORTED' OR jh.status = 'FAILURE') ORDER BY jh.last_modified DESC LIMIT 1)")
    private String lastSuccessJobNumber;

    @Formula("(SELECT IFNULL(MAX(j1.group_order), 0) FROM job j1 WHERE j1.pipeline_id = pipeline_id)")
    private String lastGroupOrder;

    @Formula("(SELECT MAX(j1.job_order) FROM job j1 WHERE j1.pipeline_id = pipeline_id AND j1.group_order = group_order)")
    private String lastJobOrder;

    @Formula("(SELECT jh.job_number FROM job_history jh WHERE jh.job_id = id ORDER BY jh.last_modified DESC LIMIT 1)")
    private String lastJobNumber;

    @Formula("(SELECT p.name FROM pipeline p WHERE p.id = pipeline_id)")
    private String pipelineName;

    @Formula("(SELECT COUNT(1) FROM job_history jh WHERE jh.job_id = id" +
            " AND jh.id >= (SELECT jh2.id FROM job_history jh2 WHERE jh2.job_id = id ORDER BY jh2.last_modified ASC LIMIT 1) " +
            " AND jh.id <= (SELECT jh3.id FROM job_history jh3 WHERE jh3.job_id = id AND jh3.status = 'SUCCESS' ORDER BY jh3.last_modified DESC LIMIT 1))")
    private String previousJobNumberCount;

    @Formula("(SELECT COUNT(1) FROM job_history jh WHERE jh.job_id = id AND jh.status = 'BLUE_DEPLOY_SUCCESS')")
    private String blueDeployedCount;

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

    public int getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(int pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGuid() {
        return jobGuid;
    }

    public void setJobGuid(String jobGuid) {
        this.jobGuid = jobGuid;
    }

    public int getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(int groupOrder) {
        this.groupOrder = groupOrder;
    }

    public int getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(int jobOrder) {
        this.jobOrder = jobOrder;
    }

    public String getBuilderType() {
        return builderType;
    }

    public void setBuilderType(String builderType) {
        this.builderType = builderType;
    }

    public int getBuildJobId() {
        return buildJobId;
    }

    public void setBuildJobId(int buildJobId) {
        this.buildJobId = buildJobId;
    }

    public String getJobTrigger() {
        return jobTrigger;
    }

    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    public String getPostActionYn() {
        return postActionYn;
    }

    public void setPostActionYn(String postActionYn) {
        this.postActionYn = postActionYn;
    }

    public String getRepositoryType() {
        return repositoryType;
    }

    public void setRepositoryType(String repositoryType) {
        this.repositoryType = repositoryType;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getRepositoryAccountId() {
        return repositoryAccountId;
    }

    public void setRepositoryAccountId(String repositoryAccountId) {
        this.repositoryAccountId = repositoryAccountId;
    }

    public String getRepositoryAccountPassword() {
        return repositoryAccountPassword;
    }

    public void setRepositoryAccountPassword(String repositoryAccountPassword) {
        this.repositoryAccountPassword = repositoryAccountPassword;
    }

    public String getRepositoryBranch() {
        return repositoryBranch;
    }

    public void setRepositoryBranch(String repositoryBranch) {
        this.repositoryBranch = repositoryBranch;
    }

    public String getRepositoryCommitRevision() {
        return repositoryCommitRevision;
    }

    public void setRepositoryCommitRevision(String repositoryCommitRevision) {
        this.repositoryCommitRevision = repositoryCommitRevision;
    }

    public int getCfInfoId() {
        return cfInfoId;
    }

    public void setCfInfoId(int cfInfoId) {
        this.cfInfoId = cfInfoId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getDeployType() {
        return deployType;
    }

    public void setDeployType(String deployType) {
        this.deployType = deployType;
    }

    public String getBlueGreenDeployStatus() {
        return blueGreenDeployStatus;
    }

    public void setBlueGreenDeployStatus(String blueGreenDeployStatus) {
        this.blueGreenDeployStatus = blueGreenDeployStatus;
    }

    public String getDeployTargetOrg() {
        return deployTargetOrg;
    }

    public void setDeployTargetOrg(String deployTargetOrg) {
        this.deployTargetOrg = deployTargetOrg;
    }

    public String getDeployTargetSpace() {
        return deployTargetSpace;
    }

    public void setDeployTargetSpace(String deployTargetSpace) {
        this.deployTargetSpace = deployTargetSpace;
    }

    public String getManifestUseYn() {
        return manifestUseYn;
    }

    public void setManifestUseYn(String manifestUseYn) {
        this.manifestUseYn = manifestUseYn;
    }

    public String getManifestScript() {
        return manifestScript;
    }

    public void setManifestScript(String manifestScript) {
        this.manifestScript = manifestScript;
    }

    public String getInspectionProjectId() {
        return inspectionProjectId;
    }

    public void setInspectionProjectId(String inspectionProjectId) {
        this.inspectionProjectId = inspectionProjectId;
    }

    public String getInspectionProjectName() {
        return inspectionProjectName;
    }

    public void setInspectionProjectName(String inspectionProjectName) {
        this.inspectionProjectName = inspectionProjectName;
    }

    public String getInspectionProjectKey() {
        return inspectionProjectKey;
    }

    public void setInspectionProjectKey(String inspectionProjectKey) {
        this.inspectionProjectKey = inspectionProjectKey;
    }

    public String getInspectionProfileId() {
        return inspectionProfileId;
    }

    public void setInspectionProfileId(String inspectionProfileId) {
        this.inspectionProfileId = inspectionProfileId;
    }

    public String getInspectionGateId() {
        return inspectionGateId;
    }

    public void setInspectionGateId(String inspectionGateId) {
        this.inspectionGateId = inspectionGateId;
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

    public String getBuildJobName() {
        return buildJobName;
    }

    public void setBuildJobName(String buildJobName) {
        this.buildJobName = buildJobName;
    }

    public String getLastJobStatus() {
        return lastJobStatus;
    }

    public void setLastJobStatus(String lastJobStatus) {
        this.lastJobStatus = lastJobStatus;
    }

    public String getLastJobModified() {
        return lastJobModified;
    }

    public void setLastJobModified(String lastJobModified) {
        this.lastJobModified = lastJobModified;
    }

    public String getLastSuccessJobNumber() {
        return (lastSuccessJobNumber != null) ? lastSuccessJobNumber : "0";
    }

    public void setLastSuccessJobNumber(String lastSuccessJobNumber) {
        this.lastSuccessJobNumber = lastSuccessJobNumber;
    }

    public String getLastGroupOrder() {
        return lastGroupOrder;
    }

    public void setLastGroupOrder(String lastGroupOrder) {
        this.lastGroupOrder = lastGroupOrder;
    }

    public String getLastJobOrder() {
        return lastJobOrder;
    }

    public void setLastJobOrder(String lastJobOrder) {
        this.lastJobOrder = lastJobOrder;
    }

    public String getLastJobNumber() {
        return (lastJobNumber != null) ? lastJobNumber : "0";
    }

    public void setLastJobNumber(String lastJobNumber) {
        this.lastJobNumber = lastJobNumber;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public String getPreviousJobNumberCount() {
        return previousJobNumberCount;
    }

    public void setPreviousJobNumberCount(String previousJobNumberCount) {
        this.previousJobNumberCount = previousJobNumberCount;
    }

    public String getBlueDeployedCount() {
        return blueDeployedCount;
    }

    public void setBlueDeployedCount(String blueDeployedCount) {
        this.blueDeployedCount = blueDeployedCount;
    }
}
