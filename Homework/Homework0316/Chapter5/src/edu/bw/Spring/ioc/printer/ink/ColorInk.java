package edu.bw.Spring.ioc.printer.ink;

public class ColorInk implements IInk {

    private String inkColor;

    public String getInkColor() {
        return inkColor;
    }

    public void setInkColor(String inkColor) {
        this.inkColor = inkColor;
    }

    @Override
    public String inkInfo() {
        return inkColor+"提供颜色";
    }
}
