package com.mkl.hengyimes.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Authorities对象", description="权限")
public class Authorities implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "授权标识")
    @TableId(value = "authority", type = IdType.AUTO)
    private String authority;

    @ApiModelProperty(value = "名称")
    @TableField("authority_name")
    private String authorityName;

    @ApiModelProperty(value = "模块")
    @TableField("parent_name")
    private String parentName;

    @ApiModelProperty(value = "排序号")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;


}
