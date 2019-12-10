package com.lsp.project.system.controller;

import java.util.List;
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
import com.lsp.project.system.domain.SysUserName;
import com.lsp.project.system.service.ISysUserNameService;
import com.lsp.framework.web.controller.BaseController;
import com.lsp.framework.web.domain.AjaxResult;
import com.lsp.common.utils.poi.ExcelUtil;
import com.lsp.framework.web.page.TableDataInfo;

/**
 * 实名信息Controller
 *
 * @author lsp
 * @date 2019-12-10
 */
@RestController
@RequestMapping("/system/name")
public class SysUserNameController extends BaseController
{
    @Autowired
    private ISysUserNameService sysUserNameService;

    /**
     * 查询实名信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:name:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserName sysUserName)
    {
        startPage();
        List<SysUserName> list = sysUserNameService.selectSysUserNameList(sysUserName);
        return getDataTable(list);
    }

    /**
     * 导出实名信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:name:export')")
    @Log(title = "实名信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserName sysUserName)
    {
        List<SysUserName> list = sysUserNameService.selectSysUserNameList(sysUserName);
        ExcelUtil<SysUserName> util = new ExcelUtil<SysUserName>(SysUserName.class);
        return util.exportExcel(list, "name");
    }

    /**
     * 获取实名信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:name:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysUserNameService.selectSysUserNameById(id));
    }

    /**
     * 新增实名信息
     */
    @PreAuthorize("@ss.hasPermi('system:name:add')")
    @Log(title = "实名信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserName sysUserName)
    {
        return toAjax(sysUserNameService.insertSysUserName(sysUserName));
    }

    /**
     * 修改实名信息
     */
    @PreAuthorize("@ss.hasPermi('system:name:edit')")
    @Log(title = "实名信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserName sysUserName)
    {
        return toAjax(sysUserNameService.updateSysUserName(sysUserName));
    }

    /**
     * 删除实名信息
     */
    @PreAuthorize("@ss.hasPermi('system:name:remove')")
    @Log(title = "实名信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysUserNameService.deleteSysUserNameByIds(ids));
    }
}
