package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import java.util.List;

/**
 * Created by hrjin on 2017-07-19.
 */
public class InstanceUseList {

    List<InstanceUse> instanceUses;

    int page;
    int size;
    int totalPages;
    long totalElements;
    boolean isLast;

    public InstanceUseList() {
    }

    public InstanceUseList(List<InstanceUse> instanceUses) {
        this.instanceUses = instanceUses;
    }

    public List<InstanceUse> getInstanceUses() {
        return instanceUses;
    }

    public void setInstanceUses(List<InstanceUse> instanceUses) {
        this.instanceUses = instanceUses;
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
