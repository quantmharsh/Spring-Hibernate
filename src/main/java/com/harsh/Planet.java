package com.harsh;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Planet {

    @Id
    private  int  pid;
    private String pname;

    @OneToMany(mappedBy = "planet") //Planet is parent. map to planet field in Aliens
    private List<Alien> aliens;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", aliens=" + aliens +
                '}';
    }
}
