package paasta.delivery.pipeline.common.api.domain.common.code;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import paasta.delivery.pipeline.common.api.common.Constants;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.code
 *
 * @author REX
 * @version 1.0
 * @since 11/6/2017
 */
@Entity
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code_type")
    private String codeType;

    @Column(name = "code_group")
    private String codeGroup;

    @Column(name = "code_name", nullable = false)
    private String codeName;

    @Column(name = "code_value", nullable = false)
    private String codeValue;

    @Column(name = "code_order")
    private int codeOrder;

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

    public String getCodeType() { return codeType; }

    public void setCodeType(String codeType) { this.codeType = codeType; }

    public String getCodeGroup() { return codeGroup; }

    public void setCodeGroup(String codeGroup) { this.codeGroup = codeGroup; }

    public String getCodeName() { return codeName; }

    public void setCodeName(String codeName) { this.codeName = codeName; }

    public String getCodeValue() { return codeValue; }

    public void setCodeValue(String codeValue) { this.codeValue = codeValue; }

    public int getCodeOrder() { return codeOrder; }

    public void setCodeOrder(int codeOrder) { this.codeOrder = codeOrder; }

    public Date getCreated() { return created; }

    public void setCreated(Date created) { this.created = created; }

    public Date getLastModified() { return lastModified; }

    public void setLastModified(Date lastModified) { this.lastModified = lastModified; }

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

