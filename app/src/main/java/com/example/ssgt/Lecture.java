package com.example.ssgt;

/**
 * Created by seyeon on 2017-10-19.
 */

public class Lecture {
    private String lecNum;
    private String lecTitle;
    private String lecDate;
    private String lecCity;//자세한 위치는 강의 세부정보안에 넣고 시도/구정도의 위치정보만 강의 목록에서 보여질 수 있게

    public Lecture(String num, String title, String date, String city){
        this.lecNum = num;
        this.lecTitle = title;
        this.lecDate = date;
        this.lecCity = city;
    }

    public void setLecNum(String num){this.lecNum = num;}
    public void setLecTitle(String title){this.lecTitle = title;}
    public void setLecDate(String date){this.lecDate = date;}
    public void setLecCity(String city){this.lecCity = city;}

    public String getLecNum(){return lecNum;}
    public String getLecTitle(){return lecTitle;}
    public String getLecDate(){return lecDate;}
    public String getLecCity(){return lecCity;}
}
