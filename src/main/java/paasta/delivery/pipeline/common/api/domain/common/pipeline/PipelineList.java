package paasta.delivery.pipeline.common.api.domain.common.pipeline;

import java.util.List;

/**
 * Created by hrjin on 2017-05-25.
 */
public class PipelineList {
    int total;
    int start;
    int display;
    List<Pipeline> pipelines;

    int page;
    int size;
    int totalPages;
    long totalElements;
    boolean isLast;

    public PipelineList() {
        // DO NOTHING
    }

    public PipelineList(List<Pipeline> pipelines) {
        this.pipelines = pipelines;
    }

    public PipelineList(int total, int start, int display, List<Pipeline> pipelines) {
        this.total = total;
        this.start = start;
        this.display = display;
        this.pipelines = pipelines;
    }

    public List<Pipeline> getPipelines() {
        return pipelines;
    }

    public void setPipelines(List<Pipeline> pipelines) {
        this.pipelines = pipelines;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
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
