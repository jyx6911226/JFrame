package com.jyx.controller;

import com.jyx.pojo.SysDict;
import com.jyx.service.SysDictService;
import com.jyx.util.jpa.SearchFilter;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SysDictController {
    @Resource
    private SysDictService sysDictService;
    
    @RequiresPermissions(value={"SysDict-List"})
    @RequestMapping(value = "/dict/initList", method = RequestMethod.GET)
    public String initList() {
        return "dict/list";
    }

    @ApiOperation(value="查询字典列表", notes="根据实体查询分页排序字典列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "searchObj", value = "字典查询实体SysDict", required = false, dataType = "SysDict"),
        @ApiImplicitParam(name = "draw", value = "datatabels查询参数", required = false, dataType = "String"),
        @ApiImplicitParam(name = "pageNo", value = "分页页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "orderColumn", value = "排序列", required = false, dataType = "String",defaultValue = "createdTime"),
        @ApiImplicitParam(name = "orderDir", value = "排序方式", required = false, dataType = "String",defaultValue = "desc")
    })
    @RequiresPermissions(value={"SysDict-Search-Interf"})
    @RequestMapping(value = "/dicts", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getList(SysDict searchObj,
                                              String draw,
                                              Integer pageNo,
                                              Integer pageSize,
                                              @RequestParam(defaultValue = "createdTime") String orderColumn,
                                              @RequestParam(defaultValue = "desc") String orderDir) {

        Map<String, Object> resdata = new HashMap<>();
        Sort sort = new Sort("Desc".equalsIgnoreCase(orderDir) ? Direction.DESC : Direction.ASC, orderColumn);
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        List<SearchFilter> filters = new ArrayList<>();
        
        if (searchObj.getCode() != null && !searchObj.getCode().trim().equals("")) {
            SearchFilter codeFilter = new SearchFilter("code", SearchFilter.Operator.LIKE, searchObj.getCode(), SearchFilter.Connector.AND);
            filters.add(codeFilter);
        }
        
        if (searchObj.getName() != null && !searchObj.getName().trim().equals("")) {
            SearchFilter nameFilter = new SearchFilter("name", SearchFilter.Operator.LIKE, searchObj.getName(), SearchFilter.Connector.AND);
            filters.add(nameFilter);
        }
        try {
            Page<SysDict> list = sysDictService.findEntityPage(searchObj, pageable, filters);
            resdata.put("pageData", list);
            resdata.put("total", list.getTotalElements());
            resdata.put("draw", draw);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据查询失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysDict-Add-Btn"})
    @RequestMapping(value = "/dict/initAdd", method = RequestMethod.GET)
    public String initAdd() {
        return "dict/add";
    }

    @RequiresPermissions(value={"SysDict-View"})
    @RequestMapping(value = "/dict/initView/{id}", method = RequestMethod.GET)
    public String initView(@PathVariable(value = "id") SysDict obj, Model model) {
        model.addAttribute("obj", obj);
        return "dict/view";
    }

    @RequiresPermissions(value={"SysDict-Update-Btn"})
    @RequestMapping(value = "/dict/initEdit/{id}", method = RequestMethod.GET)
    public String initEdit(@PathVariable(value = "id") SysDict obj, Model model) {
        model.addAttribute("obj", obj);
        return "dict/add";
    }

    @RequiresPermissions(value={"SysDict-UpdateDetail-Btn"})
    @RequestMapping(value = "/dict/initEditDetail/{id}",method = RequestMethod.GET)
    public String initEditDetail(@PathVariable(value = "id") SysDict obj, Model model) {
        model.addAttribute("obj", obj);
        return "dict/editDetail";
    }
    
    @RequiresPermissions(value={"SysDict-Add-Interf"})
    @RequestMapping(value = "/dict",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(SysDict obj, Model model) {
        Map<String, Object> resdata = new HashMap<>();
        try {           
        	sysDictService.save(obj);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据保存失败");
            e.printStackTrace();
        }
        return resdata;
    }
    
    @RequiresPermissions(value={"SysDict-Del-Interf"})
    @RequestMapping(value = "/dict", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody List<String> ids) {
        Map<String, Object> resdata = new HashMap<>();
        try {
            List<SysDict> list = sysDictService.findById(ids);
            sysDictService.delete(list);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据删除失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysDict-List"})
    @RequestMapping(value = "/dict/validUnique", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> validUnique(SysDict searchObj) {
        boolean valid = false;
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        List<SearchFilter> filters = new ArrayList<>();
        if (searchObj.getId() != null) {
            SearchFilter idFilter = new SearchFilter("id", SearchFilter.Operator.NEQ, searchObj.getId(), SearchFilter.Connector.AND);
            filters.add(idFilter);
        }
        if (searchObj.getCode() != null) {
            SearchFilter nameFilter = new SearchFilter("code", SearchFilter.Operator.EQ, searchObj.getCode(), SearchFilter.Connector.AND);
            filters.add(nameFilter);
        }
        if (searchObj.getName() != null) {
            SearchFilter nameFilter = new SearchFilter("name", SearchFilter.Operator.EQ, searchObj.getName(), SearchFilter.Connector.AND);
            filters.add(nameFilter);
        }
        try {
            Page<SysDict> list = sysDictService.findEntityPage(searchObj, null, filters);
            if (list == null || list.getTotalElements() == 0) {
                valid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("valid", valid);
        return map;
    }
}
