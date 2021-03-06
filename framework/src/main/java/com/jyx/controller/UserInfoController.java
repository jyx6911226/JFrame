package com.jyx.controller;

import com.jyx.pojo.UserInfo;
import com.jyx.service.UserInfoService;
import com.jyx.util.jpa.SearchFilter;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    private Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @RequiresPermissions(value={"UserInfo-List"})
    @RequestMapping(value = "/userInfo/initList", method = RequestMethod.GET)
    public String initAccountList() {
        return "account/list";
    }
    @RequiresPermissions(value={"UserInfo-Search-Interf"})
    @RequestMapping(value = "/userInfos", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAccountList(UserInfo searchObj,
                                              String draw,
                                              Integer pageNo,
                                              Integer pageSize,
                                              @RequestParam(defaultValue = "id") String orderColumn,
                                              @RequestParam(defaultValue = "asc") String orderDir) {

        Map<String, Object> resdata = new HashMap<>();
        Sort sort = new Sort("Desc".equalsIgnoreCase(orderDir) ? Direction.DESC : Direction.ASC, orderColumn);
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        List<SearchFilter> filters = new ArrayList<>();
        if (searchObj.getUsername() != null && !searchObj.getUsername().trim().equals("")) {
            SearchFilter usernameFilter = new SearchFilter("username", SearchFilter.Operator.LIKE, searchObj.getUsername(), SearchFilter.Connector.AND);
            filters.add(usernameFilter);
        }
        if (searchObj.getName() != null && !searchObj.getName().trim().equals("")) {
            SearchFilter nameFilter = new SearchFilter("name", SearchFilter.Operator.LIKE, searchObj.getName(), SearchFilter.Connector.AND);
            filters.add(nameFilter);
        }
        if (searchObj.getEmail() != null && !searchObj.getEmail().trim().equals("")) {
            SearchFilter emailFilter = new SearchFilter("email", SearchFilter.Operator.LIKE, searchObj.getEmail(), SearchFilter.Connector.AND);
            filters.add(emailFilter);
        }
        if (searchObj.getTelephone() != null && !searchObj.getTelephone().trim().equals("")) {
            SearchFilter telephoneFilter = new SearchFilter("telephone", SearchFilter.Operator.LIKE, searchObj.getTelephone(), SearchFilter.Connector.AND);
            filters.add(telephoneFilter);
        }
        try {
            Page<UserInfo> list = userInfoService.findEntityPage(searchObj, pageable, filters);
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

    @RequiresPermissions(value={"UserInfo-Add-Btn"})
    @RequestMapping(value = "/userInfo/initAdd", method = RequestMethod.GET)
    public String initAdd() {
        return "account/add";
    }

    @RequiresPermissions(value={"UserInfo-View"})
    @RequestMapping(value = "/userInfo/initView/{id}", method = RequestMethod.GET)
    public String initView(@PathVariable(value = "id") UserInfo obj, Model model) {
        model.addAttribute("obj", obj);
        return "account/view";
    }

    @RequiresPermissions(value={"UserInfo-Update-Btn"})
    @RequestMapping(value = "/userInfo/initEdit/{id}", method = RequestMethod.GET)
    public String initEdit(@PathVariable(value = "id") UserInfo obj, Model model) {
        model.addAttribute("obj", obj);
        return "account/add";
    }

    @RequiresPermissions(value={"UserInfo-UpdateRole-Btn"})
    @RequestMapping(value = "/userInfo/initEditRole/{id}", method = RequestMethod.GET)
    public String initEditRole(@PathVariable(value = "id") UserInfo obj, Model model) {
        model.addAttribute("obj", obj);
        return "account/editRole";
    }

    //管理员添加用户
    @RequiresPermissions(value={"UserInfo-Add-Interf"})
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(UserInfo obj, Model model) {
        Map<String, Object> resdata = new HashMap<>();
        try {
            //新添加的账号默认密码为123456
            if (obj.getId() == null) {
                obj.setState((byte) 1);
                //密码加密
                String password_cipherText = new Md5Hash("123456", obj.getCredentialsSalt(), 2).toHex();
                obj.setPassword(password_cipherText);
            }
            userInfoService.save(obj);
            resdata.put("success", true);
            log.info("管理员添加用户成功",obj);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据保存失败");
            log.error("管理员添加用户失败",obj);
            e.printStackTrace();
        }
        return resdata;
    }

    @RequiresPermissions(value={"UserInfo-List"})
    @RequestMapping(value = "/userInfo/validUnique",  method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> validUnique(UserInfo searchObj) {
        boolean valid = false;
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        List<SearchFilter> filters = new ArrayList<>();
        if (searchObj.getId() != null) {
            SearchFilter idFilter = new SearchFilter("id", SearchFilter.Operator.NEQ, searchObj.getId(), SearchFilter.Connector.AND);
            filters.add(idFilter);
        }
        if (searchObj.getUsername() != null) {
            SearchFilter nameFilter = new SearchFilter("username", SearchFilter.Operator.EQ, searchObj.getUsername(), SearchFilter.Connector.AND);
            filters.add(nameFilter);
        }
        try {
            Page<UserInfo> list = userInfoService.findEntityPage(searchObj, null, filters);
            if (list == null || list.getTotalElements() == 0) {
                valid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("valid", valid);
        return map;
    }
    
    @RequiresPermissions(value={"UserInfo-Del-Interf"})  
    @RequestMapping(value = "/userInfo", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody List<UserInfo> objs) {
        Map<String, Object> resdata = new HashMap<>();
        try {
            userInfoService.delete(objs);
            resdata.put("success", true);
        } catch (Exception e) {
            resdata.put("success", false);
            resdata.put("error", "数据删除失败");
            e.printStackTrace();
        }
        return resdata;
    }
}
