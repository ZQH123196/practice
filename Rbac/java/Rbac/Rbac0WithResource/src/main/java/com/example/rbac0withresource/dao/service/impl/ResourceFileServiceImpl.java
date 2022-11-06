package com.example.rbac0withresource.dao.service.impl;

import com.example.rbac0withresource.dao.entity.ResourceFile;
import com.example.rbac0withresource.dao.mapper.ResourceFileMapper;
import com.example.rbac0withresource.dao.service.IResourceFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件资源表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@Service
public class ResourceFileServiceImpl extends ServiceImpl<ResourceFileMapper, ResourceFile> implements IResourceFileService {

    /**
     * 获取全部可用的 resourceHandler
     * @return
     */
    public String getResourceHandler() {
        return "";
    }
    
}
