package com.jyx.controller;

import com.jyx.pojo.SysRole;
import com.jyx.pojo.UserInfo;
import com.jyx.service.SysRoleService;
import com.jyx.service.UserInfoService;
import com.jyx.util.jpa.SearchFilter;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SysRoleController {
	
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private UserInfoService userInfoService;
    
    @RequiresPermissions(value={"SysRole-List"})
    @RequestMapping(value = "/sysRole/initList", method = RequestMethod.GET)
    public String initList() {
        return "sysrole/list";
    }
    
    @RequiresPermissions(value={"SysRole-Search-Interf"})
    @RequestMapping(value = "/sysRoles", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getList(SysRole searchObj,
                                       String draw,
                                       Integer pageNo,
                                       Integer pageSize,
                                       @RequestParam(defaultValue = "id") String orderColumn,
                                       @RequestParam(defaultValue = "asc") String orderDir) {
        Map<String, Object> resdata = new HashMap<>();
        Sort sort = new Sort("Desc".equalsIgnoreCase(orderDir) ? Sort.Direction.DESC : Sort.Direction.ASC, orderColumn);
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        List<SearchFilter> filters = new ArrayList<>();
        if (searchObj.getName() != null && !searchObj.getName().trim().equals("")) {
            SearchFilter nameFilter = new SearchFilter("name", SearchFilter.Operator.LIKE, searchObj.getName(), SearchFilter.Connector.AND);
            filters.add(nameFilter);
        }
        if (searchObj.getRole() != null && !searchObj.getRole().trim().equals("")) {
            SearchFilter roleFilter = new SearchFilter("role", SearchFilter.Operator.LIKE, searchObj.getRole(), SearchFilter.Connector.AND);
            filters.add(roleFilter);
        }
        if (searchObj.getAvailable() != null) {
            SearchFilter availableFilter = new SearchFilter("available", SearchFilter.Operator.EQ, searchObj.getAvailable(), SearchFilter.Connector.AND);
            filters.add(availableFilter);
        }
        try {
            Page<SysRole> list = sysRoleService.findEntityPage(searchObj, pageable, filters);
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
    
    @RequiresPermissions(value={"SysRole-SearchAll-Interf"})
    @RequestMapping(value = "/sysRoles/getAllList",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllList() {
        Map<String, Object> resdata = new HashMap<>();
        try {
            Page<SysRole> list = sysRoleService.findEntityPage(null, null, null);
            resdata.put("list", list);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据查询失败");
            e.printStackTrace();
        }
        return resdata;
    }
    
    @RequiresPermissions(value={"SysRole-SearchByUserInfo-Interf"})
    @RequestMapping(value = "/sysRoles/getListByUserInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getListByUserInfo(@PathVariable(value = "id") UserInfo obj, Model model) {
        model.addAttribute("obj", obj);
        Map<String, Object> resdata = new HashMap<>();
        try {
            List<SysRole> list = obj.getRoleList();
            resdata.put("list", list);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据查询失败");
            e.printStackTrace();
        }
        return resdata;
    }
    
    @RequiresPermissions(value={"SysRole-Add-Btn"})
    @RequestMapping(value = "/sysRole/initAdd", method = RequestMethod.GET)
    public String initAdd() {
        return "sysrole/add";
    }
    
    @RequiresPermissions(value={"SysRole-Add-Interf"})
    @RequestMapping(value = "/sysRole",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(SysRole obj) {
        Map<String, Object> resdata = new HashMap<>();
        try {
            sysRoleService.save(obj);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据保存失败");
            e.printStackTrace();
        }
        return resdata;
    }
    
    @RequiresPermissions(value={"SysRole-View"})
    @RequestMapping(value = "/sysRole/initView/{id}", method = RequestMethod.GET)
    public String initView(@PathVariable(value = "id") SysRole obj, Model model) {
        model.addAttribute("obj", obj);
        return "sysrole/view";
    }
    
    @RequiresPermissions(value={"SysRole-Update-Btn"})
    @RequestMapping(value = "/sysRole/initEdit/{id}", method = RequestMethod.GET)
    public String initEdit(@PathVariable(value = "id") SysRole obj, Model model) {
        model.addAttribute("obj", obj);
        return "sysrole/add";
    }

    @RequiresPermissions(value={"SysRole-UpdatePermission-Btn"})
    @RequestMapping(value = "/sysRole/initEditPermission/{id}", method = RequestMethod.GET)
    public String initEditPermission(@PathVariable(value = "id") SysRole obj, Model model) {
        model.addAttribute("obj", obj);
        return "sysrole/editPermission";
    }
    
    @RequiresPermissions(value={"SysRole-SaveRoleList-Interf"})
    @RequestMapping(value = "/sysRoles/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveRoleList(@PathVariable(value = "id") UserInfo obj,@RequestBody List<Long> rids, Model model) {
        Map<String, Object> resdata = new HashMap<>();
        List<SearchFilter> filters = new ArrayList<>();
        if (rids != null && rids.size()>0) {
            String ids = "";
            for (Long rid : rids){
                ids += rid + ",";
            }
            if(ids.endsWith(",")){
                ids = ids.substring(0,ids.length()-1);
            }
            SearchFilter idFilter = new SearchFilter("id", SearchFilter.Operator.IN, ids, SearchFilter.Connector.AND);
            filters.add(idFilter);
        }
        try {
            Page<SysRole> roles = sysRoleService.findEntityPage(null,null,filters);
            obj.getRoleList().clear();
            obj.getRoleList().addAll(roles.getContent());
            userInfoService.save(obj);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据保存失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysRole-Del-Interf"})
    @RequestMapping(value = "/sysRoles", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody List<SysRole> objs) {
        Map<String, Object> resdata = new HashMap<>();
        try {
            sysRoleService.delete(objs);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据删除失败");
            e.printStackTrace();
        }
        return resdata;
    }
    
    @RequiresPermissions(value={"SysRole-List"})
    @RequestMapping(value = "/sysRoles/validUnique", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> validUnique(SysRole searchObj) {
        boolean valid = false;
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        List<SearchFilter> filters = new ArrayList<>();
        if (searchObj.getId() != null) {
            SearchFilter idFilter = new SearchFilter("id", SearchFilter.Operator.NEQ, searchObj.getId(), SearchFilter.Connector.AND);
            filters.add(idFilter);
        }
        if (searchObj.getName() != null) {
            SearchFilter nameFilter = new SearchFilter("name", SearchFilter.Operator.EQ, searchObj.getName(), SearchFilter.Connector.AND);
            filters.add(nameFilter);
        }
        if (searchObj.getRole() != null) {
            SearchFilter roleFilter = new SearchFilter("role", SearchFilter.Operator.EQ, searchObj.getRole(), SearchFilter.Connector.AND);
            filters.add(roleFilter);
        }
        try {
            Page<SysRole> list = sysRoleService.findEntityPage(searchObj, null, filters);
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
