package com.jyx.controller;

import com.jyx.pojo.SysPermission;
import com.jyx.pojo.SysRole;
import com.jyx.service.SysPermissionService;
import com.jyx.service.SysRoleService;
import com.jyx.util.jpa.SearchFilter;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysPermission")
public class SysPermissionController {
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysPermissionService sysPermissionService;
    
    @RequiresPermissions(value={"SysPermission-List"})
    @RequestMapping("/initList")
    public String initAccountList() {
        return "syspermission/list";
    }

    @RequiresPermissions(value={"SysPermission-SearchAll-Interf"})
    @RequestMapping("/getAllList")
    @ResponseBody
    public Map<String, Object> getAllList() {
        Map<String, Object> resdata = new HashMap<>();
        try {
            Page<SysPermission> list = sysPermissionService.findEntityPage(null, null, null);
            resdata.put("list", list);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据查询失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysPermission-GetPermissList-Interf"})
    @RequestMapping("/getPermissList/{id}")
    @ResponseBody
    public Map<String, Object> getPermissList(@PathVariable(value = "id") SysRole obj, Model model) {
        model.addAttribute("obj", obj);
        Map<String, Object> resdata = new HashMap<>();
        try {
            List<SysPermission> list = obj.getPermissions();
            resdata.put("list", list);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据查询失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysPermission-Add-Interf"})
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(SysPermission obj) {
        System.out.println("add:" + obj.toString());
        Map<String, Object> resdata = new HashMap<>();
        try {
            sysPermissionService.save(obj);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据保存失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysPermission-SavePermissionList-Interf"})
    @RequestMapping(value = "/savePermissionList/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> savePermissionList(@PathVariable(value = "id") SysRole obj,@RequestBody List<Long> pids, Model model) {
        Map<String, Object> resdata = new HashMap<>();
        List<SearchFilter> filters = new ArrayList<>();
        if (pids != null && pids.size()>0) {
            String ids = "";
            for (Long pid : pids){
                ids += pid + ",";
            }
            if(ids.endsWith(",")){
                ids = ids.substring(0,ids.length()-1);
            }
            SearchFilter idFilter = new SearchFilter("id", SearchFilter.Operator.IN, ids, SearchFilter.Connector.AND);
            filters.add(idFilter);
        }
        try {
            Page<SysPermission> permissions = sysPermissionService.findEntityPage(null,null,filters);
            obj.getPermissions().clear();
            obj.getPermissions().addAll(permissions.getContent());
            sysRoleService.save(obj);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据保存失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysPermission-Del-Interf"})
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable(value = "id") SysPermission obj) {
        Map<String, Object> resdata = new HashMap<>();
        List<SearchFilter> filters = new ArrayList<>();
        try {
            SearchFilter delFilter = new SearchFilter("parentIds", SearchFilter.Operator.LIKE, "," + obj.getId() + ",", SearchFilter.Connector.AND);
            filters.add(delFilter);
            Page<SysPermission> objs = sysPermissionService.findEntityPage(obj, null, filters);
            List<SysPermission> delList = new ArrayList<>(objs.getContent());
            delList.add(obj);
            sysPermissionService.delete(delList);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据删除失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysPermission-List"})
    @RequestMapping({"/validUnique"})
    @ResponseBody
    public Map<String, Boolean> validUnique(SysPermission searchObj) {
        boolean valid = false;
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        List<SearchFilter> filters = new ArrayList<>();
        if (searchObj.getId() != null) {
            SearchFilter idFilter = new SearchFilter("id", SearchFilter.Operator.NEQ, searchObj.getId(), SearchFilter.Connector.AND);
            filters.add(idFilter);
        }
        if (searchObj.getName() != null && !searchObj.getName().trim().equals("")) {
            SearchFilter usernameFilter = new SearchFilter("name", SearchFilter.Operator.EQ, searchObj.getName(), SearchFilter.Connector.AND);
            filters.add(usernameFilter);
        }
        if (searchObj.getPermission() != null && !searchObj.getPermission().trim().equals("")) {
            SearchFilter permissionFilter = new SearchFilter("permission", SearchFilter.Operator.EQ, searchObj.getPermission(), SearchFilter.Connector.AND);
            filters.add(permissionFilter);
        }
        if (searchObj.getResourceUrl() != null && !searchObj.getResourceUrl().trim().equals("")) {
            SearchFilter urlFilter = new SearchFilter("resourceUrl", SearchFilter.Operator.EQ, searchObj.getResourceUrl(), SearchFilter.Connector.AND);
            filters.add(urlFilter);
        }
        try {
            Page<SysPermission> list = sysPermissionService.findEntityPage(searchObj, null, filters);
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
