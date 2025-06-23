package SpringJPA.demo1.SpecialCharConfigDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SpecialCharConfigProjection {
    private final Long id;
    private final String specialChar;
    private final String replaceChar;
    private final String dataLocation;
    private final Integer webView;
    private final Integer paraStatus;
    private final String msgType;
    private final String fieldId;
    private final String fieldCode;
    private final String msgDirection;
    private final String xmlTag;
    private final Integer configActiveStatus;
    private final String clientName;
    private final String clientCode;
    private final String channelPayment;
    private final Integer rowNumber;
    private final String jsonData;
    private final String description;
    private final Integer activeStatus;

    public SpecialCharConfigProjection(Long id, String specialChar, String replaceChar, String dataLocation,
                                       Integer webView, Integer paraStatus, String msgType, String fieldId,
                                       String fieldCode, String msgDirection, String xmlTag, Integer configActiveStatus,
                                       String clientName, String clientCode, String channelPayment, Integer rowNumber,
                                       String jsonData, String description, Integer activeStatus) {
        this.id = id;
        this.specialChar = specialChar;
        this.replaceChar = replaceChar;
        this.dataLocation = dataLocation;
        this.webView = webView;
        this.paraStatus = paraStatus;
        this.msgType = msgType;
        this.fieldId = fieldId;
        this.fieldCode = fieldCode;
        this.msgDirection = msgDirection;
        this.xmlTag = xmlTag;
        this.configActiveStatus = configActiveStatus;
        this.clientName = clientName;
        this.clientCode = clientCode;
        this.channelPayment = channelPayment;
        this.rowNumber = rowNumber;
        this.jsonData = jsonData;
        this.description = description;
        this.activeStatus = activeStatus;
    }

    public Long getId() { return id; }
    public String getSpecialChar() { return specialChar; }
    public String getReplaceChar() { return replaceChar; }
    public String getDataLocation() { return dataLocation; }
    public Integer getWebView() { return webView; }
    public Integer getParaStatus() { return paraStatus; }
    public String getMsgType() { return msgType; }
    public String getFieldId() { return fieldId; }
    public String getFieldCode() { return fieldCode; }
    public String getMsgDirection() { return msgDirection; }
    public String getXmlTag() { return xmlTag; }
    public Integer getConfigActiveStatus() { return configActiveStatus; }
    public String getClientName() { return clientName; }
    public String getClientCode() { return clientCode; }
    public String getChannelPayment() { return channelPayment; }
    public Integer getRowNumber() { return rowNumber; }
    public String getJsonData() { return jsonData; }
    public String getDescription() { return description; }
    public Integer getActiveStatus() { return activeStatus; }
}