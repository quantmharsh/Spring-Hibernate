package com.harsh;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//to treat this class as a table
//to rename or having diffrent name in db we can use name="newname". use same name is hql
@Entity(name = "alien_database")
//change table name only
//@Table(name="alien_db")
public class Alien {
//    make aid as a primary key .  important to have primary key
    @Id
    //rename or have diffrent name of column in database
    @Column(name = "a_id")
    private int aid;
    private String aname;
    private String tech;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", tech='" + tech + '\'' +
                '}';
    }
}
