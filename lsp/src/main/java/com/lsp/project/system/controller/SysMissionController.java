package com.lsp.project.system.controller;
import	java.awt.Desktop.Action;

import java.util.List;

import io.swagger.annotations.Api;
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
import com.lsp.project.system.domain.SysMission;
import com.lsp.project.system.service.ISysMissionService;
import com.lsp.framework.web.controller.BaseController;
import com.lsp.framework.web.domain.AjaxResult;
import com.lsp.common.utils.poi.ExcelUtil;
import com.lsp.framework.web.page.TableDataInfo;

/**
 * 任务信息Controller
 *
 * @author lsp
 * @date 2019-12-13
 */
@Api("任务信息")
@RestController
@RequestMapping("/system/mission")
public class SysMissionController extends BaseController
{
    @Autowired
    private ISysMissionService sysMissionService;

    /**
     * 查询任务信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:mission:list')")
    @ApiOperation("查询任务列表")
    @GetMapping("/list")
    public TableDataInfo list(SysMission sysMission)
    {
        startPage();
        List<SysMission> list = sysMissionService.selectSysMissionList(sysMission);
        return getDataTable(list);
    }


    @GetMapping("/getList")
    public TableDataInfo mission(SysMission sysMission){
        startPage();
        List<SysMission> list = sysMissionService.selectSysMissionList(sysMission);
        return getDataTable(list);
    }

    /**
     * 导出任务信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:mission:export')")
    @Log(title = "任务信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysMission sysMission)
    {
        List<SysMission> list = sysMissionService.selectSysMissionList(sysMission);
        ExcelUtil<SysMission> util = new ExcelUtil<SysMission>(SysMission.class);
        return util.exportExcel(list, "mission");
    }

    @PreAuthorize("@ss.hasPermi('system:mission:query')")
    @GetMapping( value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysMissionService.selectSysMissionById(id));
    }

    /**
     * 获取任务信息详细信息
     */
    @ApiOperation("根据任务id查询任务详情")
    @GetMapping("/getInfoById/{id}")
    public AjaxResult getInfoById(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysMissionService.selectSysMissionById(id));
    }

    @ApiOperation("根据用户id查询自己的任务")
    @GetMapping("/getInfoByUserId/{userId}" )
    public AjaxResult getInfoByUserId(@PathVariable("userId") Long userId){
        return AjaxResult.success(sysMissionService.selectSysMissionByUserId(userId));
    }

    /**
     * 新增任务信息
     */
    @PreAuthorize("@ss.hasPermi('system:mission:add')")
    @Log(title = "任务信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMission sysMission)
    {
        return toAjax(sysMissionService.insertSysMission(sysMission));
    }

    /**
     * 修改任务信息
     */
    @ApiOperation("修改任务信息" )
    @PreAuthorize("@ss.hasPermi('system:mission:edit')")
    @Log(title = "任务信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMission sysMission)
    {
        return toAjax(sysMissionService.updateSysMission(sysMission));
    }

    /**
     * 删除任务信息
     */
    @ApiOperation("删除任务信息")
    @PreAuthorize("@ss.hasPermi('system:mission:remove')")
    @Log(title = "任务信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysMissionService.deleteSysMissionByIds(ids));
    }
}
