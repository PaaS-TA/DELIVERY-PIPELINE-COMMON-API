package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import javax.persistence.*;

/**
 * Created by hrjin on 2017-05-29.
 */
@Entity
@Table(name = "ci_info")
public class CiInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "server_url")
    private String serverUrl;

    @Column(name = "used_count")
    private int usedcount;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    public CiInfo() {
        // DO NOTHING
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getUsedcount() {
        return usedcount;
    }

    public void setUsedcount(int usedcount) {
        this.usedcount = usedcount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
