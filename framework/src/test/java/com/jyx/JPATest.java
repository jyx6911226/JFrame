package com.jyx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jyx.pojo.SysScheduler;
import com.jyx.quartz.SchedulerManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.*;

//// SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {
//	@Resource
//    private JavaMailSender mailSender;
    @Resource
    SchedulerManager schedulerManager;
	@Test
	public void test(){
//	    String jobParams = "[]";
//        List<Map> list = JSON.parseArray(jobParams, Map.class);
    }
//	@Test
//	public void testOneToOne_save(){
//		//Assert.assertEquals("hello",testService.getName());
//		OneObj1 oneObj1 = new OneObj1();
//		oneObj1.setName("testOneObj1");
//		OneObj2 oneObj2 = new OneObj2();
//		oneObj2.setName("testOneObj2");
//		oneObj1.setOneObj2(oneObj2);
//		oneObjService.save(oneObj1);
//	}
//	@Test
//	public void testOneToOne_remove(){
//		//Assert.assertEquals("hello",testService.getName());
//		OneObj1 oneObj1 = oneObjService.getById("402880a8581e74c101581e74db470000");		
//		oneObjService.remove(oneObj1);
//	}
//	@Test
//	public void testJPA_KeyWord(){
//		Pageable pageable = new PageRequest(1, 5);
//		Page<OneObj1> page = oneObjService.findByNameContaining("O", pageable);
//		System.out.println("结果数:"+page.getTotalElements());
//	}
//	@Test
//	public void testOneToMany_save(){
//		//Assert.assertEquals("hello",testService.getName());
//		OneObj1 oneObj1 = new OneObj1();
//		oneObj1.setName("testOneToManyObj1");
//		List<ManyObj1> manyObj1s = new ArrayList<ManyObj1>();
//		oneObj1.setManyObj1s(manyObj1s);
//		ManyObj1 arg0 = new ManyObj1();
//		ManyObj1 arg1 = new ManyObj1();
//		ManyObj1 arg2 = new ManyObj1();
//		arg0.setName("000");
//		arg1.setName("001");
//		arg2.setName("002");
//		manyObj1s.add(arg0);
//		manyObj1s.add(arg1);
//		manyObj1s.add(arg2);
//		arg0.setOneObj1(oneObj1);//此步骤不能少，如果不设置arg0对应的OneObj1，数据库中的关系也没法维护
//		arg1.setOneObj1(oneObj1);
//		arg2.setOneObj1(oneObj1);
//		OneObj2 oneObj2 = new OneObj2();
//		oneObj2.setName("abc");
//		oneObj1.setOneObj2(oneObj2);
//		oneObjService.save(oneObj1);
//		System.out.println("执行完成========================>");
//	}
	/**
	 * 注意：在级联删除时如果只知道id，这样就没有加载多方的list，jpa只会删除本类对应的数据，关联的表不会删除，如果有外键的话会报错
	 * 如果先查询出这个对象（级联查询），再删除，则所有相关数据都会删除。
	 * LAZY加载的时候，是会删除关联的对象的。
	 * 
	 * 一般来说，正式项目中应尽量使用逻辑删除（更改状态位），物理删除时一定要谨慎！！！
	 * 
	 * */
//	@Test
//	public void testOneToMany_del(){
////		OneObj1 oneObj1 = new OneObj1();
////		oneObj1.setId("402882e558243ead0158243eca190000");
//		OneObj1 oneObj1 = oneObjService.getById("402882e558245b310158245b49350000");		
//		oneObjService.delete(oneObj1);
////		List<ManyObj1> list = oneObj1.getManyObj1s();
////		for(ManyObj1 m:list){
////			System.out.println("m================>"+m.getName());
////		}
//		System.out.println("执行完成========================>");
//	}
	//注意：如果配置了双向一对多关联，删除一个manyObj时，对应的OneObj1也会被删除（所有关联的记录都会被删除！！！）
//	@Test
//	public void testOneToMany_del2(){
//		ManyObj1 mo = manyObjService.getById("402882e558244ff60158245010a00003");
//		System.out.println("mo.getName():"+mo.getName());
//		manyObjService.delete(mo);
//		System.out.println("执行完成========================>");
//	}
	//级联更新时，关联的类如果更新也会被更新
//	@Test
//	public void testOneToMany_update(){
//		
//		OneObj1 oneObj1 = oneObjService.getById("402882e65828227b0158282296430000");		
//		
//		List<ManyObj1> list = oneObj1.getManyObj1s();
//		System.out.println("list:"+list.size());
//		System.out.println("OneObj2:"+oneObj1.getOneObj2().getName());
//		System.out.println("testOneToMany_update=======================>");
//	}
	/**
	 * 
	 * ManyObj1,ManyObj2及关联关系都会被保存
	 * */
//	@Test
//	public void testManyToMany_save(){
//		ManyObj1 m1 = new ManyObj1();
//		m1.setName("m1a");
//		List<ManyObj2> list = new ArrayList<ManyObj2>();
//		ManyObj2 m20 = new ManyObj2();
//		m20.setName("m20a");
//		ManyObj2 m21 = new ManyObj2();
//		m21.setName("m21a");
//		ManyObj2 m22 = new ManyObj2();
//		m22.setName("m22a");
//		list.add(m20);
//		list.add(m21);
//		list.add(m22);
//		m1.setManyObj2s(list);
//		manyObjService.save2(m20);
//		manyObjService.save2(m21);
//		manyObjService.save2(m22);
//		manyObjService.save(m1);
//		System.out.println("success=================>");
//	}
	/**
	 * 多对多级联删除时只会删除此类及其在中间表中的关联关系，并不会删除另一个多方的数据
	 * */
//	@Test
//	public void testManyToMany_del(){
//		ManyObj1 m1 = manyObjService.getById("402882e65828e577015828e5916d0004");
//		manyObjService.delete(m1);
//		System.out.println("success=================>");
//	}
//	@Test
//	public void testManyToMany_del(){
//		ManyObj1 m1 = manyObjService.getById("402882e65828e577015828e5916d0004");
//		manyObjService.delete(m1);
//		System.out.println("success=================>");
//	}
	/**
	 * jpa事务测试
	 * */
//	@Test
//	@Transactional
//	public void testTransactional(){
//		ManyObj1 m1 = new ManyObj1();
//		m1.setName("testTransactional");
//		ManyObj2 m2 = new ManyObj2();
//		m2.setName("testTransactional2");
//		manyObjService.save(m1);
////		String a =null;
////		a.length();
//		manyObjService.save2(m2);
//		System.out.println("success=================>");
//	}
//	@Test
//	public void test(){
//		
//	}
//	@Test
//    public void sendSimpleEmail(){
//       SimpleMailMessage message = new SimpleMailMessage();
//       message.setFrom("15165194064@163.com");//发送者.
//       message.setTo("369906430@qq.com");//接收者.
//       message.setSubject("测试邮件（邮件主题）");//邮件主题.
//       message.setText("这是邮件内容");//邮件内容.
//       mailSender.send(message);//发送邮件
//    }
//	public static void main(String[] args) {
//		List names = new ArrayList();
//
//		names.add("Google");
//		names.add("Runoob");
//		names.add("Taobao");
//		names.add("Baidu");
//		names.add("Sina");
//
//		names.forEach(System.out::println);
//	}
}
