package com.jyx.controller;

import com.jyx.pojo.SysDict;
import com.jyx.service.SysDictService;
import com.jyx.util.jpa.SearchFilter;

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
@RequestMapping("/dict")
public class SysDictController {
    @Resource
    private SysDictService sysDictService;
    
    @RequestMapping("/initList")
    public String initList() {
        return "dict/list";
    }
    
    @RequestMapping("/getList")
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

    @RequestMapping("/initAdd")
    public String initAdd() {
        return "dict/add";
    }

    @RequestMapping("/initView/{id}")
    public String initView(@PathVariable(value = "id") SysDict obj, Model model) {
        model.addAttribute("obj", obj);
        return "dict/view";
    }

    @RequestMapping("/initEdit/{id}")
    public String initEdit(@PathVariable(value = "id") SysDict obj, Model model) {
        model.addAttribute("obj", obj);
        return "dict/add";
    }

    @RequestMapping("/initEditDetail/{id}")
    public String initEditRole(@PathVariable(value = "id") SysDict obj, Model model) {
        model.addAttribute("obj", obj);
        return "dict/editDetail";
    }

    @RequestMapping(method = RequestMethod.POST)
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
    
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody List<SysDict> objs) {
        Map<String, Object> resdata = new HashMap<>();
        try {
            sysDictService.delete(objs);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据删除失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequestMapping({"/validUnique"})
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
