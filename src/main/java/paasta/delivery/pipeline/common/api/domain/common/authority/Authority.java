package paasta.delivery.pipeline.common.api.domain.common.authority;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mingu on 2017-05-31.
 */

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "authority")
public class Authority {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "description")
    private String description;

    @Column(name = "auth_type")
    private String authType;

    @Column(name = "code")
    private String code;

    @Column(name = "auth_code")
    private String authCode;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "authority_id")
    @Transient
    private List<GrantedAuthority> grantedAuthorities;

    public Authority() {}

    public Authority(String id) {
        this.id = id;
    }

    public Authority(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", authType='" + authType + '\'' +
                ", code='" + code + '\'' +
                ", authCode='" + authCode + '\'' +
                ", grantedAuthorities=" + grantedAuthorities +
                '}';
    }
}
