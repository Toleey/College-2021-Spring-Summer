package edu.bw.Spring.ioc.printer;

import edu.bw.Spring.ioc.printer.ink.IInk;
import edu.bw.Spring.ioc.printer.paper.IPaper;

/**
 * 组装打印机
 */

public class Printer {

    private IPaper paper;
    private IInk ink;

    public IPaper getPaper() {
        return paper;
    }

    public void setPaper(IPaper paper) {
        this.paper = paper;
    }

    public IInk getInk() {
        return ink;
    }

    public void setInk(IInk ink) {
        this.ink = ink;
    }

    public void print(){
        System.out.println(paper.paperInfo());
        System.out.println(ink.inkInfo());
    }

}
