package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.Pipeline;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrjin on 2017-05-29.
 */
@Entity
@Table(name = "service_instances")
public class ServiceInstances {

    @Id
    @Column(name = "id")
    private String id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_instances_id", referencedColumnName = "id")
    @JsonIgnore
    private List<Pipeline> pipelineList;

    @OneToMany(mappedBy = "serviceInstances", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InstanceUse> instanceUseList = new ArrayList<>();

    @Column(name = "owner")
    private String owner;

    @Column(name = "ci_server_url")
    private String ciServerUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<InstanceUse> getInstanceUseList() {
        return instanceUseList;
    }

    public void setInstanceUseList(List<InstanceUse> instanceUseList) {
        this.instanceUseList = instanceUseList;
    }

    public List<Pipeline> getPipelineList() {
        return pipelineList;
    }

    public void setPipelineList(List<Pipeline> pipelineList) {
        this.pipelineList = pipelineList;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCiServerUrl() {
        return ciServerUrl;
    }

    public void setCiServerUrl(String ciServerUrl) {
        this.ciServerUrl = ciServerUrl;
    }

}
