package com.lsp.project.system.service;

import com.lsp.project.system.domain.SysUserName;
import java.util.List;

/**
 * 实名信息Service接口
 *
 * @author lsp
 * @date 2019-12-10
 */
public interface ISysUserNameService
{
    /**
     * 查询实名信息
     *
     * @param id 实名信息ID
     * @return 实名信息
     */
    public SysUserName selectSysUserNameById(String id);

    /**
     * 根据用户id查询实名信息
     *
     * @param userid 用户id
     * @return
     */
    public SysUserName selectSysUserNameByUserId(Long userid);

    /**
     * 查询实名信息列表
     *
     * @param sysUserName 实名信息
     * @return 实名信息集合
     */
    public List<SysUserName> selectSysUserNameList(SysUserName sysUserName);

    /**
     * 新增实名信息
     *
     * @param sysUserName 实名信息
     * @return 结果
     */
    public int insertSysUserName(SysUserName sysUserName);

    /**
     * 修改实名信息
     *
     * @param sysUserName 实名信息
     * @return 结果
     */
    public int updateSysUserName(SysUserName sysUserName);

    /**
     * 批量删除实名信息
     *
     * @param ids 需要删除的实名信息ID
     * @return 结果
     */
    public int deleteSysUserNameByIds(String[] ids);

    /**
     * 删除实名信息信息
     *
     * @param id 实名信息ID
     * @return 结果
     */
    public int deleteSysUserNameById(String id);

}
