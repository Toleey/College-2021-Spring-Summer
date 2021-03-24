package test;

import entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class UserMain {
    public static void main(String[] args) throws Exception {

        Class userClass = Class.forName("entity.User");

        String packageName = userClass.getPackageName();
        Field []fieldAll = userClass.getDeclaredFields();
        Method []methodAll = userClass.getDeclaredMethods();
        Constructor []constructorAll = userClass.getDeclaredConstructors();

        System.out.println("User类的："
                +"\n包："+packageName
                +"\n属性："+Arrays.toString(fieldAll)
                +"\n方法："+Arrays.toString(methodAll)
                +"\n构造器："+Arrays.toString(constructorAll));


        Class []initParams = {Integer.class,String.class,Integer.class};
        Constructor userCons = userClass.getConstructor(initParams);
        Object []userInit = {1,"哈哈",20};
        User user = (User) userCons.newInstance(userInit);


        Method setUnameMethod = userClass.getMethod("setuName", String.class);
        setUnameMethod.invoke(user,"呵呵");

        Method getUnameMethod = userClass.getMethod("getuName");
        String getUname = (String) getUnameMethod.invoke(user);
        System.out.println(getUname);

        System.out.println(user);




    }
}
