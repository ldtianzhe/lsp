package com.lsp.project.system.domain;

import com.lsp.framework.aspectj.lang.annotation.Excel;
import com.lsp.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 实名信息对象 sys_user_name
 *
 * @author lsp
 * @date 2019-12-10
 */
public class SysUserName extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 实名认证id */
    private String id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNumber;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
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
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("idNumber", getIdNumber())
            .append("name", getName())
            .toString();
    }
}
