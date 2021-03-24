package edu.bw.Spring.ioc.printer.paper;

public class B5Paper implements IPaper{
    //纸张类型
    private String paperType;

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    @Override
    public String paperInfo() {
        return paperType+"提供服务";
    }
}
