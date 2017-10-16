package paasta.delivery.pipeline.common.api.domain.common.cfInfo;

import java.util.List;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.cfInfo
 *
 * @author REX
 * @version 1.0
 * @since 7/25/2017
 */
public class CfInfoList {

    private List<CfInfo> cfInfos;

    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean isLast;

    public List<CfInfo> getCfInfos() {
        return cfInfos;
    }

    public void setCfInfos(List<CfInfo> cfInfos) {
        this.cfInfos = cfInfos;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

}
