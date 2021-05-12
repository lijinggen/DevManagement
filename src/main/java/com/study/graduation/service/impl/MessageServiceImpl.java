package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.dto.ListProjectReq;
import com.study.graduation.dto.MessageDto;
import com.study.graduation.entity.Message;
import com.study.graduation.dao.MessageDao;
import com.study.graduation.entity.Project;
import com.study.graduation.service.MessageService;
import com.study.graduation.service.ProjectService;
import com.study.graduation.service.ProjectUserRelationService;
import com.study.graduation.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2021-04-26 20:09:35
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    @Resource
    private ProjectUserRelationService projectUserRelationService;

    @Resource
    private ProjectService projectService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Message queryById(String id) {
        return this.messageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Message> queryAllByLimit(int offset, int limit) {
        return this.messageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message insert(Message message) {
        this.messageDao.insert(message);
        return message;
    }

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message update(Message message) {
        this.messageDao.update(message);
        return this.queryById(message.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.messageDao.deleteById(id) > 0;
    }

    @Override
    public List<MessageDto> queryAllByUserId(String userId) throws ParseException {
        QueryWrapper<Message>messageQueryWrapper=new QueryWrapper<>();
        messageQueryWrapper.lambda().eq(Message::getToUserId,userId);
        List<Message> messages = messageDao.selectList(messageQueryWrapper);
        List<MessageDto> messageDtos=new ArrayList<>();
        for (Message message : messages) {
            MessageDto messageDto=new MessageDto();
            messageDto.setId(message.getId());
            messageDto.setFromUser(message.getFromUser());
            messageDto.setTitle(message.getTitle());
            messageDto.setToUser(message.getToUser());
            messageDto.setToUserId(message.getToUserId());
            messageDto.setProject(message.getProject());
            messageDto.setIsRead(message.getIsRead());
            messageDto.setCreateTime(DateUtil.format(message.getCreateTime()));
            messageDtos.add(messageDto);
        }
        Collections.sort(messageDtos, new Comparator<MessageDto>() {
            @Override
            public int compare(MessageDto o1, MessageDto o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        return messageDtos;
    }

    @Override
    public List<Message> queryAllInMyProject(String userId) {
        ListProjectReq listProjectReq=new ListProjectReq();
        listProjectReq.setUserId(userId);
        List<Project> list = projectService.list(listProjectReq);
        List<Message> result = new ArrayList<>();
        for (Project project : list) {
            QueryWrapper<Message> messageQueryWrapper=new QueryWrapper<>();
            messageQueryWrapper.lambda().eq(Message::getProjectId,project.getId());
            List<Message> messages = messageDao.selectList(messageQueryWrapper);
            for (Message message : messages) {
                result.add(message);
                if(result.size()>50){
                    break;
                }
            }
            if(result.size()>50){
                break;
            }
        }
        Collections.sort(result, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        return result;
    }

    @Override
    public void read(String id) {
        Message message=messageDao.queryById(id);
        message.setIsRead(1);
        messageDao.update(message);
    }
}
