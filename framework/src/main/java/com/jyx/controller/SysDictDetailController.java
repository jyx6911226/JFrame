package com.jyx.controller;

import com.jyx.pojo.SysDict;
import com.jyx.pojo.SysDictDetail;
import com.jyx.service.SysDictDetailService;
import com.jyx.util.jpa.SearchFilter;

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
public class SysDictDetailController {
    @Resource
    private SysDictDetailService sysDictDetailService;
    
    @RequiresPermissions(value={"SysDictDetail-ListByDict"})
    @RequestMapping(value = "/dictDetail/initList/{id}",method = RequestMethod.GET)
    public String initList(@PathVariable(value = "id") SysDict obj, Model model) {
    	model.addAttribute("obj", obj);
        return "dict/detail/list";
    }
    
    @RequiresPermissions(value={"SysDictDetail-Search-Interf"})
    @RequestMapping(value = "/dictDetails" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getList(SysDictDetail searchObj,
                                              String draw,
                                              Integer pageNo,
                                              Integer pageSize,
                                              @RequestParam(defaultValue = "createdTime") String orderColumn,
                                              @RequestParam(defaultValue = "desc") String orderDir) {

        Map<String, Object> resdata = new HashMap<>();
        Sort sort = new Sort("Desc".equalsIgnoreCase(orderDir) ? Direction.DESC : Direction.ASC, orderColumn);
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        List<SearchFilter> filters = new ArrayList<>();
        
        if (searchObj.getLabel() != null && !searchObj.getLabel().trim().equals("")) {
            SearchFilter labelFilter = new SearchFilter("label", SearchFilter.Operator.LIKE, searchObj.getLabel(), SearchFilter.Connector.AND);
            filters.add(labelFilter);
        }
        
        if (searchObj.getValue() != null && !searchObj.getValue().trim().equals("")) {
            SearchFilter valueFilter = new SearchFilter("value", SearchFilter.Operator.LIKE, searchObj.getValue(), SearchFilter.Connector.AND);
            filters.add(valueFilter);
        }
        
        if (searchObj.getParentId() != null && !searchObj.getParentId().trim().equals("")) {
            SearchFilter parentIdFilter = new SearchFilter("parentId", SearchFilter.Operator.EQ, searchObj.getParentId(), SearchFilter.Connector.AND);
            filters.add(parentIdFilter);
        }
        try {
            Page<SysDictDetail> list = sysDictDetailService.findEntityPage(searchObj, pageable, filters);
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
    
    @RequiresPermissions(value={"SysDictDetail-GetListByDict-Interf"})
    @RequestMapping(value = "/dictDetails/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getListByDict(@PathVariable(value = "id") SysDict obj) {
    	SysDictDetail searchObj = new SysDictDetail();
    	searchObj.setSysDict(obj);
    	List<SearchFilter> filters = new ArrayList<>();
        SearchFilter sysDictFilter = new SearchFilter("sysDict.id", SearchFilter.Operator.EQ, searchObj.getSysDict().getId(), SearchFilter.Connector.AND);
        filters.add(sysDictFilter);
        
        Map<String, Object> resdata = new HashMap<>();
        try {
            Page<SysDictDetail> list = sysDictDetailService.findEntityPage(searchObj, null, filters);
            resdata.put("list", list);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据查询失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysDictDetail-Add-Interf"})
    @RequestMapping( value = "/dictDetail/{dictId}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@PathVariable(value = "dictId") SysDict sysDict, SysDictDetail obj, Model model) {
        Map<String, Object> resdata = new HashMap<>();
        try {
            obj.setSysDict(sysDict);
        	sysDictDetailService.save(obj);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据保存失败");
            e.printStackTrace();
        }
        return resdata;
    }
    
    @RequiresPermissions(value={"SysDictDetail-Del-Interf"})
    @RequestMapping(value = "/dictDetail/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable(value = "id") SysDictDetail obj) {
        Map<String, Object> resdata = new HashMap<>();
        List<SearchFilter> filters = new ArrayList<>();
        try {
            SearchFilter delFilter = new SearchFilter("parentIds", SearchFilter.Operator.LIKE, "," + obj.getId() + ",", SearchFilter.Connector.AND);
            filters.add(delFilter);
            Page<SysDictDetail> objs = sysDictDetailService.findEntityPage(obj, null, filters);
            List<SysDictDetail> delList = new ArrayList<>(objs.getContent());
            delList.add(obj);
            sysDictDetailService.delete(delList);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据删除失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysDictDetail-ListByDict"})
    @RequestMapping(value = "/dictDetail/validUnique", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> validUnique(SysDictDetail searchObj, String dictId) {
        boolean valid = false;
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        List<SearchFilter> filters = new ArrayList<>();
        if (searchObj.getId() != null) {
            SearchFilter idFilter = new SearchFilter("id", SearchFilter.Operator.NEQ, searchObj.getId(), SearchFilter.Connector.AND);
            filters.add(idFilter);
        }
        if (searchObj.getLabel() != null) {
            SearchFilter labelFilter = new SearchFilter("label", SearchFilter.Operator.EQ, searchObj.getLabel(), SearchFilter.Connector.AND);
            filters.add(labelFilter);
        }
        if (searchObj.getValue() != null) {
            SearchFilter valueFilter = new SearchFilter("value", SearchFilter.Operator.EQ, searchObj.getValue(), SearchFilter.Connector.AND);
            filters.add(valueFilter);
        }
        if (dictId != null) {
            SearchFilter valueFilter = new SearchFilter("sysDict.id", SearchFilter.Operator.EQ, dictId, SearchFilter.Connector.AND);
            filters.add(valueFilter);
        }
        if (searchObj.getParentId() != null) {
            SearchFilter parentIdFilter = new SearchFilter("parentId", SearchFilter.Operator.EQ, searchObj.getParentId(), SearchFilter.Connector.AND);
            filters.add(parentIdFilter);
        }
        
        try {
            Page<SysDictDetail> list = sysDictDetailService.findEntityPage(searchObj, null, filters);
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
