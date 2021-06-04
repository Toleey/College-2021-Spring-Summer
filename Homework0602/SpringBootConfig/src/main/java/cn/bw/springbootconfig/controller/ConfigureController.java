package cn.bw.springbootconfig.controller;

import cn.bw.springbootconfig.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConfigureController {

    //1.实验用@Value读取application.yml，自定义的属性值
    @Value("${name}")
    private String name;
    @Value("${person.name}")
    private String pname;
    @Value("${person.age}")
    private int age;
//    @Value("${person.address}")
//    private List<String> address;//value不能往数组集合里诸如数据
    @Value("${msg1}")
    private String msg1;
    @Value("${msg2}")
    private String msg2;

    @RequestMapping("/configure")
    public String Configure(){
        System.out.println("msg1单引号表达纯量："+msg1);//单引号表达不出来
        System.out.println("msg2双引号表示纯量："+msg2);//双引号表达出来
        return "configure @Value read: "
                +"<br />name = "+name
                +"<br />person.name = "+pname
                +"<br />person.age = "+age
//                +"<br />person.address = "+address
                ;
    }
    //2.实验用Environment对象读取配置文件
    @Autowired//自动注入值
    private Environment env;
    @RequestMapping("/configure2")
    public String Configure2(){
        System.out.println("msg1单引号表达纯量："+env.getProperty("msg1"));//单引号表达不出来
        System.out.println("msg2双引号表示纯量："+env.getProperty("msg2"));//双引号表达出来
        return       "configure Environment Object:"
                     +"name = "+env.getProperty("name")  //读取配置信息中的name属性值
                     +"<br /> person.name = "+env.getProperty("person.name")
                     +"<br /> person.age = "+env.getProperty("person.age");
    }
    //3.实验用@ConfigurationProperties注解读取配置文件信息注入到相应的对象中
    @Autowired
    private User user;
    @RequestMapping("/configure3")
    public String Configure3(){
        return   "configure @ConfigurationProperties read: "+user;
    }
}
