package com.study.graduation.service;

import com.study.graduation.dto.AddDocumentDto;
import com.study.graduation.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * (Document)表服务接口
 *
 * @author makejava
 * @since 2021-03-29 13:08:54
 */
public interface DocumentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Document queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Document> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param document 实例对象
     * @return 实例对象
     */
    Document insert(Document document);

    /**
     * 修改数据
     *
     * @param document 实例对象
     * @return 实例对象
     */
    Document update(Document document);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    void addFile(AddDocumentDto addDocumentDto, MultipartFile file) throws IOException;
}
