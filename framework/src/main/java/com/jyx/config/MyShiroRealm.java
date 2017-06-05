package com.jyx.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import com.jyx.pojo.SysPermission;
import com.jyx.pojo.SysRole;
import com.jyx.pojo.UserInfo;
import com.jyx.service.UserInfoService;

/**
 * 身份校验核心类;
 */
public class MyShiroRealm extends AuthorizingRealm {
	@Resource
	private UserInfoService userInfoService;

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 *
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		UserInfo userInfo = null;
		try {
			userInfo = userInfoService.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userInfo == null) {
			return null;
		}
		/*
		 * 获取权限信息:这里没有进行实现， 请自行根据UserInfo,Role,Permission进行实现；
		 * 获取之后可以在前端for循环显示所有链接;
		 */
		// userInfo.setPermissions(userService.findPermissions(user));
		// 账号判断;
		// 加密方式;
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, // 用户名
				userInfo.getPassword(), // 密码
				ByteSource.Util.bytes(userInfo.getCredentialsSalt()), getName() // realm
																				// name
		);
		return authenticationInfo;
	}

	/**
	 * 此方法调用 hasRole,hasPermission的时候才会进行回调.
	 * <p>
	 * 权限信息.(授权): 1、如果用户正常退出，缓存自动清空； 2、如果用户非正常退出，缓存自动清空；
	 * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。 （需要手动编程进行实现；放在service进行调用）
	 * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例， 调用clearCached方法；
	 * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链 接，某个资源文件等。
	 *
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*
		 * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行， 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
		 * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了， 缓存过期之后会再次执行。
		 */
		// System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		// UserInfo userInfo = userInfoService.findByUsername(username)
		// 权限单个添加;
		// 或者按下面这样添加
		// 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
		// authorizationInfo.addRole("admin");
		// 添加权限
		// authorizationInfo.addStringPermission("userInfo:query");
		/// 在认证成功之后返回.
		// 设置角色信息.
		// 支持 Set集合,
		// 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
		// List<Role> roleList=user.getRoleList();
		// for (Role role : roleList) {
		// info.addStringPermissions(role.getPermissionsName());
		// }
		for (SysRole role : userInfo.getRoleList()) {
			authorizationInfo.addRole(role.getRole());
			authorizationInfo.addStringPermissions(getStringPermissions(role.getPermissions()));
			// for (SysPermission p : role.getPermissions()) {
			// authorizationInfo.addStringPermission(p.getPermission());
			// }
		}
		// 设置权限信息.
		// authorizationInfo.setStringPermissions(getStringPermissions(userInfo.getRoleList()));
		return authorizationInfo;
	}

	/**
	 * 将权限对象中的权限code取出.
	 *
	 * @param permissions
	 * @return
	 */
	public Set<String> getStringPermissions(List<SysPermission> permissions) {
		Set<String> stringPermissions = new HashSet<String>();
		if (permissions != null) {
			for (SysPermission p : permissions) {
				stringPermissions.add(p.getPermission());
			}
		}
		return stringPermissions;
	}
}