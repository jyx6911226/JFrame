package com.jyx;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 多数据源切换测试
 * 
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleDateSourceTest {
	//@Resource
	//private TestService testService;
	
//	@Resource
//    private DataSource dataSource;
//	@Resource
//    @Qualifier("ds1")
//    private DataSource dataSource1;
//    @Autowired
//    @Qualifier("ds2")
//    private DataSource dataSource2;
//    @Test
//    public void dataSource(){
//        System.out.println("dataSource:"+dataSource);
//        System.out.println("dataSource1:"+dataSource1);
//        System.out.println("dataSource2:"+dataSource2);   
//    }
    @Test
    @Transactional
    public void dynamicDataSource(){
//    	System.out.println("start======================>");
//    	List<String> t1 = testService.getListTest1();
//    	System.out.println("t1:"+t1.size());
//    	testService.addTest1("testadd001");
//    	System.out.println("addend========================>");
//    	String a = null;
//    	a.length();
//    	List<String> t2 = testService.getListTest2();
//    	System.out.println("t2:"+t2.size());
//    	System.out.println("end======================>");
    }
}
