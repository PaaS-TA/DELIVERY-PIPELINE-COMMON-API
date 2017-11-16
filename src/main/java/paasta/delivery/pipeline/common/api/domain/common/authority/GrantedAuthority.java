package paasta.delivery.pipeline.common.api.domain.common.authority;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Mingu on 2017-05-31.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "granted_authority")
public class GrantedAuthority {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "instance_use_id")
    @JsonProperty("instance_use_id")
    private Long instanceUseId;

    @Column(name = "auth_code")
    private Long authCode;

    @ManyToOne(targetEntity = Authority.class, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id", nullable = false, referencedColumnName = "id")
    private Authority authority;

    @Transient
    @JsonProperty("authority_id")
    private String authorityId;

    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public GrantedAuthority() {
        // DO NOTHING
    }

    public GrantedAuthority(String id, Long instanceUseId, String authorityId) {
        this.id = id;
        this.instanceUseId = instanceUseId;
        this.authority = new Authority(authorityId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getInstanceUseId() {
        return instanceUseId;
    }

    public void setInstanceUseId(Long instanceUseId) {
        this.instanceUseId = instanceUseId;
    }

    public Long getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Long authCode) {
        this.authCode = authCode;
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


}
