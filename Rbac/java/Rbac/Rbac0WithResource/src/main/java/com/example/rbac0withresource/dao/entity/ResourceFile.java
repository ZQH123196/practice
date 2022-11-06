package com.example.rbac0withresource.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文件资源表
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@TableName("resource_file")
@Data
public class ResourceFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件的根路径
     * 只能由数据库访问者修改，完全信任此路径不会有任何校验，不应当被暴露到前端，暴露可能造成安全隐患。
     */
    String dirPath;

    /**
     * 文件名，会进行文件名的安全校验。
     */
    String fileName;

    /**
     * 资源描述
     */
    @TableField("description")
    private String description;


}
