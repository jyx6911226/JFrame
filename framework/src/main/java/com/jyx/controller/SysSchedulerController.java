package com.jyx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jyx.pojo.SysScheduler;
import com.jyx.quartz.SchedulerUtil;
import com.jyx.service.SysSchedulerService;
import com.jyx.service.SysSchedulerService;
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
@RequestMapping("/scheduler")
public class SysSchedulerController {
    @Resource
    private SysSchedulerService sysSchedulerService;
    @Resource
    private SchedulerUtil schedulerUtil;
    @RequiresPermissions(value={"SysScheduler-List"})
    @RequestMapping("/initList")
    public String initList() {
        return "scheduler/list";
    }
    
    @RequiresPermissions(value={"SysScheduler-Search-Interf"})
    @RequestMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(SysScheduler searchObj,
                                              String draw,
                                              Integer pageNo,
                                              Integer pageSize,
                                              @RequestParam(defaultValue = "createdTime") String orderColumn,
                                              @RequestParam(defaultValue = "desc") String orderDir) {

        Map<String, Object> resdata = new HashMap<>();
        Sort sort = new Sort("Desc".equalsIgnoreCase(orderDir) ? Direction.DESC : Direction.ASC, orderColumn);
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        List<SearchFilter> filters = new ArrayList<>();

        if (searchObj.getStartFlag() != null) {
            SearchFilter startFlagFilter = new SearchFilter("startFlag", SearchFilter.Operator.EQ, searchObj.getStartFlag(), SearchFilter.Connector.AND);
            filters.add(startFlagFilter);
        }

        if (searchObj.getName() != null && !searchObj.getName().trim().equals("")) {
            SearchFilter nameFilter = new SearchFilter("name", SearchFilter.Operator.LIKE, searchObj.getName(), SearchFilter.Connector.AND);
            filters.add(nameFilter);
        }

        if (searchObj.getJobClass() != null && !searchObj.getJobClass().trim().equals("")) {
            SearchFilter jobFilter = new SearchFilter("jobClass", SearchFilter.Operator.LIKE, searchObj.getJobClass(), SearchFilter.Connector.AND);
            filters.add(jobFilter);
        }

        try {
            Page<SysScheduler> list = sysSchedulerService.findEntityPage(searchObj, pageable, filters);
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

    @RequiresPermissions(value={"SysScheduler-Add-Btn"})
    @RequestMapping("/initAdd")
    public String initAdd() {
        return "scheduler/add";
    }

    @RequiresPermissions(value={"SysScheduler-View"})
    @RequestMapping("/initView/{id}")
    public String initView(@PathVariable(value = "id") SysScheduler obj, Model model) {
        model.addAttribute("obj", obj);
        return "scheduler/view";
    }

    @RequiresPermissions(value={"SysScheduler-Update-Btn"})
    @RequestMapping("/initEdit/{id}")
    public String initEdit(@PathVariable(value = "id") SysScheduler obj, Model model) {
        model.addAttribute("obj", obj);
        return "scheduler/add";
    }
    
    @RequiresPermissions(value={"SysScheduler-Add-Interf"})
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(SysScheduler obj, Model model) {
        Map<String, Object> resdata = new HashMap<>();
        try {
        	sysSchedulerService.saveAndDeploySysScheduler(obj);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据保存失败");
            e.printStackTrace();
        }
        return resdata;
    }
    
    @RequiresPermissions(value={"SysScheduler-Del-Interf"})
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody List<SysScheduler> objs) {
        Map<String, Object> resdata = new HashMap<>();
        try {
            sysSchedulerService.deleteSysSchedulerAndRemove(objs);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据删除失败");
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"SysScheduler-List"})
    @RequestMapping({"/validUnique"})
    @ResponseBody
    public Map<String, Boolean> validUnique(SysScheduler searchObj) {
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

        if (searchObj.getJobClass() != null) {
            SearchFilter jobClassFilter = new SearchFilter("jobClass", SearchFilter.Operator.EQ, searchObj.getJobClass(), SearchFilter.Connector.AND);
            filters.add(jobClassFilter);
        }

        try {
            Page<SysScheduler> list = sysSchedulerService.findEntityPage(searchObj, null, filters);
            if (list == null || list.getTotalElements() == 0) {
                valid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("valid", valid);
        return map;
    }

    @RequiresPermissions(value={"SysScheduler-List"})
    @RequestMapping({"/validJobParams"})
    @ResponseBody
    public Map<String, Boolean> validJobParams(String jobParams) {
        boolean valid = false;
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            schedulerUtil.getJobDataMap(jobParams);
            valid = true;
        }catch (JSONException e) {
            valid = false;
        }
        map.put("valid", valid);
        return map;
    }
}
