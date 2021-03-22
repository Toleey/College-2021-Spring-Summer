package org.bw.aopextend.demo1.entity;

public class PeopleSay {
    private String Name;
    private String Words;
    private String Name2;
    private String Words2;

    public PeopleSay() {
    }

    public PeopleSay(String name, String words, String name2, String words2) {
        Name = name;
        Words = words;
        Name2 = name2;
        Words2 = words2;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWords() {
        return Words;
    }

    public void setWords(String words) {
        Words = words;
    }

    public String getName2() {
        return Name2;
    }

    public void setName2(String name2) {
        Name2 = name2;
    }

    public String getWords2() {
        return Words2;
    }

    public void setWords2(String words2) {
        Words2 = words2;
    }

    @Override
    public String toString() {
        return  Name + "说：" + Words +"\n"+ Name2 + "说：" + Words2 ;
    }
}
