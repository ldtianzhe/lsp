package com.lsp.project.system.domain;

import com.lsp.framework.aspectj.lang.annotation.Excel;
import com.lsp.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务信息对象 sys_mission
 *
 * @author lsp
 * @date 2019-12-13
 */
public class SysMission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 地区 */
    @Excel(name = "地区")
    private String area;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 分类id */
    @Excel(name = "分类id")
    private Long sort;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String cPhone;

    /** 接单人id */
    @Excel(name = "接单人id")
    private Long orderId;

    /** 评价 */
    @Excel(name = "评价")
    private String evaluate;

    /** 状态 */
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
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLongitude()
    {
        return longitude;
    }
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLatitude()
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
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
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
            .append("description", getDescription())
            .append("img", getImg())
            .append("contact", getContact())
            .append("cPhone", getcPhone())
            .append("orderId", getOrderId())
            .append("evaluate", getEvaluate())
            .append("state", getState())
            .toString();
    }
}
