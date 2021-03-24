package test;

import entity.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		// 加载Student类，产生类对象，并且根据类对象，生成Student普通对象
		//Student student = new Student();
		//获得(Student)类对象
		//Class cls = student.getClass();

		Class cls = Student.class;
		//获得类对象描述的当前类所在包
		Package package1 = cls.getPackage();
		//System.out.println("student类型所在包"+package1);
		//获得类对象描述的类的访问控制
		cls.getModifiers();
		//System.out.println(Modifier.toString(cls.getModifiers()));

		String packageName = cls.getPackage().getName();
		String className =  cls.getName();
		String classDadName = cls.getSuperclass().getName();
		Type[] interfaces = cls.getGenericInterfaces();
		Constructor[] consAll = cls.getDeclaredConstructors();
		Constructor[] consPublic = cls.getConstructors();
		Method[] methodAll = cls.getDeclaredMethods();
		Method[] methodPublic = cls.getMethods();
		Field[] fields = cls.getDeclaredFields();
		Annotation[] ans = cls.getAnnotations();

		System.out.println("获取Student类的，包名："+packageName
				+"\n类名："+className
				+"\n父类名："+classDadName
				+"\n继承的接口："+ Arrays.toString(interfaces)
				+"\n全部构造器："+Arrays.toString(consAll)
				+"\npubilc构造器："+Arrays.toString(consPublic)
				+"\n全部普通方法："+Arrays.toString(methodAll)
				+"\npublic方法："+Arrays.toString(methodPublic)
				+"\n所有属性："+Arrays.toString(fields)
				+"\n注解："+Arrays.toString(ans));

	}

}
