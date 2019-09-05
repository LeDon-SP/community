package life.ledon.community.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private Long notifier;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}
