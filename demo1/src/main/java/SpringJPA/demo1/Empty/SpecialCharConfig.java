package SpringJPA.demo1.Empty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "PARA_SPECIAL_CHAR", schema = "DEMO")
@NamedStoredProcedureQuery(
        name = "getSpecialCharConfigByClientWithCount",
        procedureName = "get_special_char_config_by_client",
        resultClasses = SpecialCharConfig.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_client_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_special_char", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_msg_type", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_para_status", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_active_status", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_field_id", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_client_code", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_page_number", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_page_size", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_total", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
        }
)

public class SpecialCharConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "para_special_char_seq")
    @SequenceGenerator(name = "para_special_char_seq", sequenceName = "para_special_char_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SPECIAL_CHAR", nullable = false)
    @NotNull(message = "SPECIAL_CHAR là bắt buộc")
    private String specialChar;

    @Column(name = "REPLACE_CHAR")
    private String replaceChar;

    @Column(name = "DATA_LOCATION")
    private String dataLocation;

    @Column(name = "WEB_VIEW")
    private Integer webView;

    @Column(name = "PARA_STATUS")
    private Integer paraStatus;

    @Column(name = "MSG_TYPE", nullable = false)
    @NotNull(message = "MSG_TYPE là bắt buộc")
    private String msgType;

    @Column(name = "FIELD_ID", nullable = false)
    @NotNull(message = "FIELD_ID là bắt buộc")
    private String fieldId;

    @Column(name = "FIELD_CODE")
    private String fieldCode;

    @Column(name = "MSG_DIRECTION")
    private String msgDirection;

    @Column(name = "XML_TAG")
    private String xmlTag;

    @Column(name = "ACTIVE_STATUS")
    private Integer activeStatus;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Column(name = "CLIENT_CODE")
    private String clientCode;

    @Column(name = "CHANNEL_PAYMENT")
    private String channelPayment;

    @Column(name = "ROW_NUMBER")
    private Integer rowNumber;

    @Column(name = "JSON_DATA")
    private String jsonData;

    // Getters và setters giữ nguyên
    public Long getId() {
        return id;
    }

    public String getSpecialChar() {
        return specialChar;
    }

    public String getReplaceChar() {
        return replaceChar;
    }

    public String getDataLocation() {
        return dataLocation;
    }

    public Integer getWebView() {
        return webView;
    }

    public String getMsgType() {
        return msgType;
    }

    public Integer getParaStatus() {
        return paraStatus;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getMsgDirection() {
        return msgDirection;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public String getXmlTag() {
        return xmlTag;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientCode() {
        return clientCode;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public String getChannelPayment() {
        return channelPayment;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpecialChar(String specialChar) {
        this.specialChar = specialChar;
    }

    public void setReplaceChar(String replaceChar) {
        this.replaceChar = replaceChar;
    }

    public void setDataLocation(String dataLocation) {
        this.dataLocation = dataLocation;
    }

    public void setWebView(Integer webView) {
        this.webView = webView;
    }

    public void setParaStatus(Integer paraStatus) {
        this.paraStatus = paraStatus;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public void setMsgDirection(String msgDirection) {
        this.msgDirection = msgDirection;
    }

    public void setXmlTag(String xmlTag) {
        this.xmlTag = xmlTag;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public void setChannelPayment(String channelPayment) {
        this.channelPayment = channelPayment;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public SpecialCharConfig() {
    }
}