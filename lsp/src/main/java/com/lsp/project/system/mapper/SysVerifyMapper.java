package com.lsp.project.system.mapper;

import com.lsp.project.system.domain.SysVerify;
import java.util.List;

/**
 * 审核信息Mapper接口
 * 
 * @author lsp
 * @date 2019-12-09
 */
public interface SysVerifyMapper 
{
    /**
     * 查询审核信息
     * 
     * @param verifyId 审核信息ID
     * @return 审核信息
     */
    public SysVerify selectSysVerifyById(String verifyId);

    /**
     * 查询审核信息列表
     * 
     * @param sysVerify 审核信息
     * @return 审核信息集合
     */
    public List<SysVerify> selectSysVerifyList(SysVerify sysVerify);

    /**
     * 新增审核信息
     * 
     * @param sysVerify 审核信息
     * @return 结果
     */
    public int insertSysVerify(SysVerify sysVerify);

    /**
     * 修改审核信息
     * 
     * @param sysVerify 审核信息
     * @return 结果
     */
    public int updateSysVerify(SysVerify sysVerify);

    /**
     * 删除审核信息
     * 
     * @param verifyId 审核信息ID
     * @return 结果
     */
    public int deleteSysVerifyById(String verifyId);

    /**
     * 批量删除审核信息
     * 
     * @param verifyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysVerifyByIds(String[] verifyIds);
}
