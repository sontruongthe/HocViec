package SpringJPA.demo1.SpecialCharConfigDTO;

import java.util.List;

public class SpecicalCharConfigDTOV2 {
    private List<SpecialCharConfigProjection> content;
    private int currentPage;
    private int totalPages;
    private int totalItems; // Thêm totalItems

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SpecialCharConfigProjection> getContent() {
        return content;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setContent(List<SpecialCharConfigProjection> content) {
        this.content = content;
    }
}
