package entity;

public class Student {
    private  Integer stuId;
    private  String stuName;
    private  int stuAge;

    public Student() {
    }

    public Student(Integer stuId, String stuName, int stuAge) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }

    public void printStu(){
        System.out.println(this);
    }
}
