package com.lsp.project.system.domain;

import com.lsp.framework.aspectj.lang.annotation.Excel;
import com.lsp.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 审核信息对象 sys_verify
 *
 * @author lsp
 * @date 2019-12-09
 */
public class SysVerify extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 认证信息id
     */
    private String verifyId;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private Long userId;

    /**
     * 认证类型0为实名认证1为企业认证2为用电信息认证3为职业认证
     */
    @Excel(name = "认证类型", readConverterExp = "0=实名认证,1=企业认证,2=用电信息认证，3=职业认证")
    private String verifyType;

    /**
     * 审核状态0未审核1已审核
     */
    @Excel(name = "审核状态0未审核1已审核")
    private String status;

    public void setVerifyId(String verifyId) {
        this.verifyId = verifyId;
    }

    public String getVerifyId() {
        return verifyId;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("verifyId", getVerifyId())
                .append("userId", getUserId())
                .append("verifyType", getVerifyType())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
