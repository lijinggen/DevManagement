package com.study.graduation.service.impl;

import com.study.graduation.dto.AddDocumentDto;
import com.study.graduation.entity.Document;
import com.study.graduation.dao.DocumentDao;
import com.study.graduation.service.DocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (Document)表服务实现类
 *
 * @author makejava
 * @since 2021-03-29 13:08:54
 */
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
    @Resource
    private DocumentDao documentDao;

    @Value("${customFile}")
    public String uploadDir;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Document queryById(String id) {
        return this.documentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Document> queryAllByLimit(int offset, int limit) {
        return this.documentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param document 实例对象
     * @return 实例对象
     */
    @Override
    public Document insert(Document document) {
        this.documentDao.insert(document);
        return document;
    }

    /**
     * 修改数据
     *
     * @param document 实例对象
     * @return 实例对象
     */
    @Override
    public Document update(Document document) {
        this.documentDao.update(document);
        return this.queryById(document.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.documentDao.deleteById(id) > 0;
    }

    @Override
    public void addFile(AddDocumentDto addDocumentDto, MultipartFile file) throws IOException {
        Document document = new Document();
        document.setId(UUID.randomUUID().toString());
        document.setCreateTime(new Date());
        document.setModifyTime(new Date());
        document.setDirectoryId(addDocumentDto.getDirectoryId());
        document.setName(file.getOriginalFilename());
        document.setProjectId(addDocumentDto.getProjectId());
        document.setType(addDocumentDto.getType());
        String uuid = UUID.randomUUID().toString();
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadDir + uuid + "~-" + file.getOriginalFilename());
        Files.write(path, bytes);
        document.setPath("/graduation/" + uuid + "~-" + file.getOriginalFilename());
        documentDao.insert(document);
    }
}
