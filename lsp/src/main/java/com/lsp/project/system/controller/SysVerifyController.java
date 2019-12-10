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
import com.lsp.project.system.domain.SysVerify;
import com.lsp.project.system.service.ISysVerifyService;
import com.lsp.framework.web.controller.BaseController;
import com.lsp.framework.web.domain.AjaxResult;
import com.lsp.common.utils.poi.ExcelUtil;
import com.lsp.framework.web.page.TableDataInfo;

/**
 * 审核信息Controller
 *
 * @author lsp
 * @date 2019-12-09
 */
@RestController
@RequestMapping("/system/verify")
public class SysVerifyController extends BaseController
{
    @Autowired
    private ISysVerifyService sysVerifyService;

    /**
     * 查询审核信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:verify:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysVerify sysVerify)
    {
        startPage();
        List<SysVerify> list = sysVerifyService.selectSysVerifyList(sysVerify);
        return getDataTable(list);
    }

    /**
     * 导出审核信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:verify:export')")
    @Log(title = "审核信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysVerify sysVerify)
    {
        List<SysVerify> list = sysVerifyService.selectSysVerifyList(sysVerify);
        ExcelUtil<SysVerify> util = new ExcelUtil<SysVerify>(SysVerify.class);
        return util.exportExcel(list, "verify");
    }

    /**
     * 获取审核信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:verify:query')")
    @GetMapping(value = "/{verifyId}")
    public AjaxResult getInfo(@PathVariable("verifyId") String verifyId)
    {
        return AjaxResult.success(sysVerifyService.selectSysVerifyById(verifyId));
    }

    /**
     * 新增审核信息
     */
    @PreAuthorize("@ss.hasPermi('system:verify:add')")
    @Log(title = "审核信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysVerify sysVerify)
    {
        return toAjax(sysVerifyService.insertSysVerify(sysVerify));
    }

    /**
     * 修改审核信息
     */
    @PreAuthorize("@ss.hasPermi('system:verify:edit')")
    @Log(title = "审核信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysVerify sysVerify)
    {
        return toAjax(sysVerifyService.updateSysVerify(sysVerify));
    }

    /**
     * 删除审核信息
     */
    @PreAuthorize("@ss.hasPermi('system:verify:remove')")
    @Log(title = "审核信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{verifyIds}")
    public AjaxResult remove(@PathVariable String[] verifyIds)
    {
        return toAjax(sysVerifyService.deleteSysVerifyByIds(verifyIds));
    }
}
