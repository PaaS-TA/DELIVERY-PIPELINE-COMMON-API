package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthority;
import paasta.delivery.pipeline.common.api.domain.common.user.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hrjin on 2017-05-29.
 */
@Entity
@Table(name = "instance_use")
public class InstanceUse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Transient
    private String userDescription;
    @Transient
    private String authListStr;

    @Transient
    private Long pipelineId;

    @ManyToOne
    @JoinColumn(name = "service_instances_id", nullable = false)
    private ServiceInstances serviceInstances;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(targetEntity = GrantedAuthority.class)
    @JoinColumn(name = "instance_use_id")
    private List<GrantedAuthority> grantedAuthorities;

    public InstanceUse() {
    }

    public InstanceUse(ServiceInstances serviceInstances, User user) {
        this.serviceInstances = serviceInstances;
        this.user = user;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public ServiceInstances getServiceInstances() {
        return serviceInstances;
    }

    public void setServiceInstances(ServiceInstances serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getAuthListStr() {
        return authListStr;
    }

    public void setAuthListStr(String authListStr) {
        this.authListStr = authListStr;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
    }
}
