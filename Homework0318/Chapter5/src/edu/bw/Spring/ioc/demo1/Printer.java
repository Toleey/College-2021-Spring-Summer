package edu.bw.Spring.ioc.demo1;

public class Printer {

    private String printColor;
    private String paperType;

    public String getPrintColor() {
        return printColor;
    }

    public void setPrintColor(String printColor) {
        this.printColor = printColor;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public void print(){
        System.out.println("打印机使用了"+printColor+"墨盒，打印"+paperType+"类型的文件");
    }
}
