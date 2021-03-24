package entity;

public class Student {
    private  Integer sutId;
    private  String stuName;
    private  Integer stuAge;

    public Student() {
    }

    public Student(Integer sutId, String stuName, Integer stuAge) {
        this.sutId = sutId;
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public Integer getSutId() {
        return sutId;
    }

    public void setSutId(Integer sutId) {
        this.sutId = sutId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sutId=" + sutId +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }

    public void printStu(){
        System.out.println(this);
    }
}
