package SpringJPA.demo1.Empty;

import jakarta.persistence.*;

@Entity
@Table(name = "MSG_TYPE_SUMMARY")
public class MsgTypeSummary {

    @Id
    @Column(name = "MSG_TYPE", nullable = false, length = 100)
    private String msgType;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "ACTIVE_STATUS", nullable = false)
    private Integer activeStatus = 1;

    // Constructor không tham số
    public MsgTypeSummary() {
    }

    // Constructor đầy đủ
    public MsgTypeSummary(String msgType, String description, Integer activeStatus) {
        this.msgType = msgType;
        this.description = description;
        this.activeStatus = activeStatus != null ? activeStatus : 1;
    }

    // Getters and Setters
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus != null ? activeStatus : 1;
    }

    @Override
    public String toString() {
        return "MsgTypeSummary{" +
                "msgType='" + msgType + '\'' +
                ", description='" + description + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }
}