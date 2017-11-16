package paasta.delivery.pipeline.common.api.domain.common.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import paasta.delivery.pipeline.common.api.common.Constants;
import paasta.delivery.pipeline.common.api.domain.common.serviceInstance.InstanceUse;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.user
 *
 * @author REX
 * @version 1.0
 * @since 5/11/2017
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private String id; // UAA 와 연동 시 ID 자동생성이 아닌 UUID 가 등록되야하기 때문에 GeneratedType.AUTO 제거.

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InstanceUse> instanceUseList = new ArrayList<>();

    @Column(name = "tell_phone")
    private String tellPhone;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "description")
    private String description;

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


    public User() {
        // DO NOTHING
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTellPhone() {
        return tellPhone;
    }

    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<InstanceUse> getInstanceUseList() {
        return instanceUseList;
    }

    public void setInstanceUseList(List<InstanceUse> instanceUseList) {
        this.instanceUseList = instanceUseList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", instanceUseList=" + instanceUseList +
                ", tellPhone='" + tellPhone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", createdString='" + createdString + '\'' +
                ", lastModifiedString='" + lastModifiedString + '\'' +
                '}';
    }
}
