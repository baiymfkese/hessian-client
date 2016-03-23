package com.hessian.client;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.caucho.hessian.client.HessianProxyFactory;
import com.dawning.gridview.app.gridview.webapp.jedis.hessian.ExampleService;
import com.dawning.gridview.app.gridview.webapp.jedis.hessian.HelloService;
import com.dawning.gridview.app.gridview.webapp.jedis.po.Student;

/**
 * hessian客户端调用
 * @author ming
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://localhost:8083/jedis/hessian/helloservice";
		HessianProxyFactory proxyFactory=new HessianProxyFactory();
		HelloService helloService=null;
		try {
			helloService=(HelloService)proxyFactory.create(HelloService.class,url);
			System.out.println(helloService.helloWorld("ming"));
			List<Student> list=helloService.getAll();
			for(Student student:list){
				System.out.println("name="+student.getName());
			}
			Student student=new Student();
			student.setName("旧的");
			student=helloService.getStudentByStudent(student);
			System.out.println("new:"+student.getName());
			Map<String,Object> map=helloService.getMap();
			String total=map.get("total").toString();
			System.out.println("total="+total);
			List<Student> studentlist=(List<Student>)map.get("list");
			for(Student stud:studentlist){
				System.out.println("name-----"+stud.getName());
			}
			url="http://localhost:8083/jedis/hessian/exampleservice";
			ExampleService exampleService=(ExampleService)proxyFactory.create(ExampleService.class,url);
			System.out.println("------------"+exampleService.greeting());
//			Set<Entry<String,Object>> setEntry=map.entrySet();
//			for(Entry<String,Object> entry:setEntry){
//				System.out.println("key="+entry.getKey());
//				System.out.println("value="+entry.getValue());
//			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
