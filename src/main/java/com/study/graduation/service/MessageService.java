package com.study.graduation.service;

import com.study.graduation.dto.MessageDto;
import com.study.graduation.entity.Message;

import java.text.ParseException;
import java.util.List;

/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2021-04-26 20:09:35
 */
public interface MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Message> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    public List<MessageDto> queryAllByUserId(String userId) throws ParseException;

    public List<Message> queryAllInMyProject(String userId);

    public void read(String id);
}
