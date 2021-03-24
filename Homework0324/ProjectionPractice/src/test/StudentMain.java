package test;

import entity.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

public class StudentMain {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

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

		//动态创建Student对象,先获得Student类对象，然后通过类对象创建Student普通对象
		//1.调用默认构造方法(构建器)
		//Student stu = (Student) Class.forName("entity.Student").newInstance();
		//stu.printStu();
		Class stuClass1 =  Class.forName("entity.Student");
		Student stu = (Student) stuClass1.newInstance();
		stu.printStu();

		//2.调用Student的三个参的构造方法，创建Student普通对象
		//(1)获得Student对象
		Class stuClass = Class.forName("entity.Student");
		//(2)获得Student三个参的构建器
		//三个参数的构造器的参数类型数组    int不是类
		Class paramsType[] = {Integer.class,String.class,Integer.TYPE};
		//获得三个参数的构造器对象
		Constructor stuCons = stuClass.getConstructor(paramsType);
		//(3)通过已获得的三个参数的构建器对象，创建Student对象(实例)
			//建立一个三个值数组，数组值类型顺序跟三个参数的构造方法一致
			Object []initParams = {2,"李四",18};
		Student stu2 = (Student) stuCons.newInstance(initParams);
		stu2.printStu();

		//3.通过动态调用Student的系列set...方法，给Student普通对象设置值
		//(1)设置stu所表示对象的编号值
			//获得Student的setStuId的方法 getDeclaredMethod和getMethods
		Method setIdMethod = stuClass1.getDeclaredMethod("setStuId", Integer.class);
			//给Stu所表示对象的编号设置值
			setIdMethod.invoke(stu,1);
		Method setNameMethod = stuClass1.getDeclaredMethod("setStuName", String.class);
			setNameMethod.invoke(stu,"哈哈");
//		Method setStuAgeMethod = stuClass1.getDeclaredMethod("setStuAge", int.class);
//			setStuAgeMethod.invoke(stu,20);
		stuClass1.getDeclaredMethod("setStuAge", int.class).invoke(stu,20);

		stu.printStu();

		//4.动态修改某个属性值，在这个例子中，修改stu所表示对象的stuName属性值
		//(1)获得stuName属性对象，
		Field nameField = stuClass1.getDeclaredField("stuName");
		//(2)修改nameField对象所代表的stu对象的stuName属性值 这样直接修改，不安全，还是要调用set方法
		nameField.setAccessible(true);
		nameField.set(stu,"呵呵");

		Field idField = stuClass1.getDeclaredField("stuId");
		idField.setAccessible(true);
		idField.set(stu,2);

		Field ageField = stuClass1.getDeclaredField("stuAge");
		ageField.setAccessible(true);
		ageField.set(stu,29);

		stu.printStu();






	}

}
