package com.lsp.project.system.mapper;

import com.lsp.project.system.domain.SysMission;
import java.util.List;

/**
 * 任务信息Mapper接口
 *
 * @author lsp
 * @date 2019-12-13
 */
public interface SysMissionMapper
{
    /**
     * 查询任务信息
     *
     * @param id 任务信息ID
     * @return 任务信息
     */
    public SysMission selectSysMissionById(Long id);

    /**
     * 查询任务信息列表
     *
     * @param sysMission 任务信息
     * @return 任务信息集合
     */
    public List<SysMission> selectSysMissionList(SysMission sysMission);

    /**
     * 新增任务信息
     *
     * @param sysMission 任务信息
     * @return 结果
     */
    public int insertSysMission(SysMission sysMission);

    /**
     * 修改任务信息
     *
     * @param sysMission 任务信息
     * @return 结果
     */
    public int updateSysMission(SysMission sysMission);

    /**
     * 删除任务信息
     *
     * @param id 任务信息ID
     * @return 结果
     */
    public int deleteSysMissionById(Long id);

    /**
     * 批量删除任务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysMissionByIds(Long[] ids);

    public SysMission selectSysMissionByUserId(Long userId);
}
