package life.ledon.community.service;

import life.ledon.community.dto.NotificationDTO;
import life.ledon.community.dto.PaginationDTO;
import life.ledon.community.enums.NotificationStatusEnum;
import life.ledon.community.enums.NotificationTypeEnum;
import life.ledon.community.exception.CustomizeErrorCode;
import life.ledon.community.exception.CustomizeException;
import life.ledon.community.mapper.NotificationMapper;
import life.ledon.community.mapper.UserMapper;
import life.ledon.community.model.Notification;
import life.ledon.community.model.NotificationExample;
import life.ledon.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);

        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

        //计算总页数
        totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);

        //页码校验
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        //获取页码列表
        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = page < 0 ? 0 : size * (page - 1);

        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        RowBounds rowBounds = new RowBounds(offset, size);
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, rowBounds);

        if (notifications.size()==0){
            return paginationDTO;
        }
        List<NotificationDTO> questionDTOLS = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            questionDTOLS.add(notificationDTO);
        }

        paginationDTO.setData(questionDTOLS);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification==null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(),user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));

        return notificationDTO;
    }
}
