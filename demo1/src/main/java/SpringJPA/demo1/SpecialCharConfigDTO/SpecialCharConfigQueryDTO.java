package SpringJPA.demo1.SpecialCharConfigDTO;

import lombok.Data;


@Data
public class SpecialCharConfigQueryDTO {
    private String clientName;
    private String specialChar;
    private String msgType;
    private Integer paraStatus;
    private Integer configActiveStatus;
    private String fieldId;
    private String clientCode;
    private Integer pageNumber =1; // Giá trị mặc định
    private Integer pageSize =5;
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSpecialChar() {
        return specialChar;
    }

    public void setSpecialChar(String specialChar) {
        this.specialChar = specialChar;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public Integer getParaStatus() {
        return paraStatus;
    }

    public void setParaStatus(Integer paraStatus) {
        this.paraStatus = paraStatus;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getActiveStatus() {
        return configActiveStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.configActiveStatus = activeStatus;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }





    public SpecialCharConfigQueryDTO() {
    }

    public SpecialCharConfigQueryDTO(String clientName, String specialChar, String msgType, Integer paraStatus, Integer activeStatus, String fieldId, String clientCode, Integer pageNumber, Integer pageSize) {
        this.clientName = clientName;
        this.specialChar = specialChar;
        this.msgType = msgType;
        this.paraStatus = paraStatus;
        this.configActiveStatus = activeStatus;
        this.fieldId = fieldId;
        this.clientCode = clientCode;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
