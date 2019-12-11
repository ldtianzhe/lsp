package com.lsp.project.system.domain;

import com.lsp.framework.aspectj.lang.annotation.Excel;
import com.lsp.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务对象 mission
 *
 * @author lsp
 * @date 2019-12-11
 */
@ApiModel("任务实体")
public class Mission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    @ApiModelProperty("任务id")
    private Long id;

    /** 用户id */
    @ApiModelProperty("用户id")
    @Excel(name = "用户id")
    private Long userId;

    /** 区域 */
    @ApiModelProperty("区域" )
    @Excel(name = "区域")
    private String area;

    /** 详细地址 */
    @ApiModelProperty("详细地址" )
    @Excel(name = "详细地址")
    private String address;

    /** 经度 */
    @ApiModelProperty("经度" )
    @Excel(name = "经度")
    private Double longitude;

    /** 纬度 */
    @ApiModelProperty("纬度")
    @Excel(name = "纬度")
    private Double latitude;

    /** 类别 */
    @ApiModelProperty("类别id")
    @Excel(name = "类别id")
    private Long sort;

    /** 标题 */
    @ApiModelProperty("标题" )
    @Excel(name = "标题")
    private String title;

    /** 描述 */
    @ApiModelProperty("描述" )
    @Excel(name = "描述")
    private String describe;

    /** 描述照片 */
    @ApiModelProperty("描述照片" )
    @Excel(name = "描述照片")
    private String img;

    /** 联系人 */
    @ApiModelProperty("联系人")
    @Excel(name = "联系人")
    private String contact;

    /** 联系方式 */
    @ApiModelProperty("联系方式")
    @Excel(name = "联系方式")
    private String cPhone;

    /** 接单人id */
    @ApiModelProperty("接单人id" )
    @Excel(name = "接单人id")
    private Long orderId;

    /** 评价 */
    @ApiModelProperty("评价")
    @Excel(name = "评价")
    private String evaluate;

    /** 状态默认0发布1接单2未完成3待评价4完成 */
    @ApiModelProperty("状态" )
    @Excel(name = "状态")
    private String state;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setArea(String area)
    {
        this.area = area;
    }

    public String getArea()
    {
        return area;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }
    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setDescribe(String describe)
    {
        this.describe = describe;
    }

    public String getDescribe()
    {
        return describe;
    }
    public void setImg(String img)
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return contact;
    }
    public void setcPhone(String cPhone)
    {
        this.cPhone = cPhone;
    }

    public String getcPhone()
    {
        return cPhone;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setEvaluate(String evaluate)
    {
        this.evaluate = evaluate;
    }

    public String getEvaluate()
    {
        return evaluate;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("area", getArea())
            .append("address", getAddress())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("sort", getSort())
            .append("title", getTitle())
            .append("describe", getDescribe())
            .append("img", getImg())
            .append("contact", getContact())
            .append("cPhone", getcPhone())
            .append("orderId", getOrderId())
            .append("evaluate", getEvaluate())
            .append("state", getState())
            .toString();
    }
}
