package SpringJPA.demo1.SpecialCharConfigDTO;

import SpringJPA.demo1.Empty.SpecialCharConfig;

import java.util.List;

public class SpecialCharConfigDTO {
    private List<SpecialCharConfig> content;
    private int currentPage;
    private int totalPages;
    private int totalItems; // Thêm totalItems

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SpecialCharConfig> getContent() {
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

    public void setContent(List<SpecialCharConfig> content) {
        this.content = content;
    }
}
