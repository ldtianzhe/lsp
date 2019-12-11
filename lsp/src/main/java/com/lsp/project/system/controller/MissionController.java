package com.lsp.project.system.controller;
import	java.security.acl.Group;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lsp.framework.aspectj.lang.annotation.Log;
import com.lsp.framework.aspectj.lang.enums.BusinessType;
import com.lsp.project.system.domain.Mission;
import com.lsp.project.system.service.IMissionService;
import com.lsp.framework.web.controller.BaseController;
import com.lsp.framework.web.domain.AjaxResult;
import com.lsp.common.utils.poi.ExcelUtil;
import com.lsp.framework.web.page.TableDataInfo;

/**
 * 任务Controller
 *
 * @author lsp
 * @date 2019-12-11
 */
@Api("任务信息管理")
@RestController
@RequestMapping("/system/mission")
public class MissionController extends BaseController
{
    @Autowired
    private IMissionService missionService;

    /**
     * 查询任务列表
     */
    @ApiOperation("获取任务列表")
    @PreAuthorize("@ss.hasPermi('system:mission:list')")
    @GetMapping("/list")
    public TableDataInfo list(Mission mission)
    {
        startPage();
        List<Mission> list = missionService.selectMissionList(mission);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:mission:export')")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Mission mission)
    {
        List<Mission> list = missionService.selectMissionList(mission);
        ExcelUtil<Mission> util = new ExcelUtil<Mission>(Mission.class);
        return util.exportExcel(list, "mission");
    }

    /**
     * 获取任务详细信息
     */
    @ApiOperation("根据任务id获取任务详细信息")
    @ApiImplicitParam(name = "id",value = "任务id",required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('system:mission:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(missionService.selectMissionById(id));
    }

    @ApiOperation("根据用户id获取任务详细信息")
    @GetMapping("/myMission/{userId}")
    public AjaxResult getInfoByUserId(@PathVariable("userId") Long userId){
        return AjaxResult.success(missionService.selectMissionByUserId(userId));
    }

    /**
     * 新增任务
     */
    @ApiOperation("任务发布" )
    @PreAuthorize("@ss.hasPermi('system:mission:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody Mission mission)
    {
        return toAjax(missionService.insertMission(mission));
    }



    /**
     * 修改任务
     */
    @ApiOperation("任务修改" )
    @PreAuthorize("@ss.hasPermi('system:mission:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Mission mission)
    {
        return toAjax(missionService.updateMission(mission));
    }

    /**
     * 删除任务
     */
    @ApiOperation("删除任务" )
    @PreAuthorize("@ss.hasPermi('system:mission:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(missionService.deleteMissionByIds(ids));
    }
}
