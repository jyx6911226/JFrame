package com.jyx.service;

import com.jyx.util.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.jyx.pojo.UserInfo;

import java.util.List;


@Service
public interface UserInfoService {
    UserInfo findByUsername(String username) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    void delete(List<UserInfo> userInfos) throws Exception;

    Page<UserInfo> findUserInfoList(UserInfo searchObj, Pageable page, List<SearchFilter> filters) throws Exception;
}
