package me.zhengjie.service.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class BusinessTotal implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "area")
    private String area;


    @Column(name = "label")
    private String label;

    @Column(name = "house_type1_tao")
    private Integer house_type1_tao;

    @Column(name = "house_type1_area")
    private Double house_type1_area;

    @Column(name = "house_type1_money")
    private Double house_type1_money;

    @Column(name = "house_type2_tao")
    private Integer house_type2_tao;

    @Column(name = "house_type2_area")
    private Double house_type2_area;

    @Column(name = "house_type2_money")
    private Double house_type2_money;

    @Column(name = "house_type3_tao")
    private Integer house_type3_tao;

    @Column(name = "house_type3_area")
    private Double house_type3_area;

    @Column(name = "house_type3_money")
    private Double house_type3_money;

    @Column(name = "house_type4_tao")
    private Integer house_type4_tao;

    @Column(name = "house_type4_area")
    private Double house_type4_area;

    @Column(name = "house_type4_money")
    private Double house_type4_money;

    @Column(name = "house_type5_tao")
    private Integer house_type5_tao;

    @Column(name = "house_type5_area")
    private Double house_type5_area;

    @Column(name = "house_type5_money")
    private Double house_type5_money;

    @Column(name = "house_type6_tao")
    private Integer house_type6_tao;

    @Column(name = "house_type6_area")
    private Double house_type6_area;

    @Column(name = "house_type6_money")
    private Double house_type6_money;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getHouse_type1_tao() {
        return house_type1_tao;
    }

    public void setHouse_type1_tao(Integer house_type1_tao) {
        if (house_type1_tao == null) {
            this.house_type1_tao = 0;
        } else {
            this.house_type1_tao = house_type1_tao;
        }
    }

    public Double getHouse_type1_area() {
        return house_type1_area;
    }

    public void setHouse_type1_area(Double house_type1_area) {
        if (house_type1_area == null) {
            this.house_type1_area = 0d;
        } else {
            this.house_type1_area = house_type1_area;
        }
    }

    public Double getHouse_type1_money() {
        return house_type1_money;
    }

    public void setHouse_type1_money(Double house_type1_money) {
        if (house_type1_money == null) {
            this.house_type1_money = 0d;
        } else {
            this.house_type1_money = house_type1_money;
        }
    }

    public Integer getHouse_type2_tao() {
        return house_type2_tao;
    }

    public void setHouse_type2_tao(Integer house_type2_tao) {
        if (house_type2_tao == null) {
            this.house_type2_tao = 0;
        } else {
            this.house_type2_tao = house_type2_tao;
        }
    }

    public Double getHouse_type2_area() {
        return house_type2_area;
    }

    public void setHouse_type2_area(Double house_type2_area) {
        if (house_type2_area == null) {
            this.house_type2_area = 0d;
        } else {
            this.house_type2_area = house_type2_area;
        }
    }

    public Double getHouse_type2_money() {
        return house_type2_money;
    }

    public void setHouse_type2_money(Double house_type2_money) {
        if (house_type2_money == null) {
            this.house_type2_money = 0d;
        } else {
            this.house_type2_money = house_type2_money;
        }
    }

    public Integer getHouse_type3_tao() {
        return house_type3_tao;
    }

    public void setHouse_type3_tao(Integer house_type3_tao) {
        if(house_type3_tao == null){
            this.house_type3_tao = 0;

        }else {
            this.house_type3_tao = house_type3_tao;
        }
    }

    public Double getHouse_type3_area() {
        return house_type3_area;
    }

    public void setHouse_type3_area(Double house_type3_area) {
        if(house_type3_area == null){
            this.house_type3_area = 0d;

        }else {
            this.house_type3_area = house_type3_area;
        }
    }

    public Double getHouse_type3_money() {
        return house_type3_money;
    }

    public void setHouse_type3_money(Double house_type3_money) {
        if(house_type3_money == null){
            this.house_type3_money = 0d;
        }else {
            this.house_type3_money = house_type3_money;
        }
    }

    public Integer getHouse_type4_tao() {
        return house_type4_tao;
    }

    public void setHouse_type4_tao(Integer house_type4_tao) {
        if(house_type4_tao == null){
            this.house_type4_tao = 0;
        }else {
            this.house_type4_tao = house_type4_tao;
        }
    }

    public Double getHouse_type4_area() {
        return house_type4_area;
    }

    public void setHouse_type4_area(Double house_type4_area) {
        if(house_type4_area == null){
            this.house_type4_area = 0d;
        }else {
            this.house_type4_area = house_type4_area;
        }
    }

    public Double getHouse_type4_money() {
        return house_type4_money;
    }

    public void setHouse_type4_money(Double house_type4_money) {
        if(house_type4_money == null){
            this.house_type4_money = 0d;
        }else {
            this.house_type4_money = house_type4_money;
        }
    }

    public Integer getHouse_type5_tao() {
        return house_type5_tao;
    }

    public void setHouse_type5_tao(Integer house_type5_tao) {
        if(house_type5_tao == null){
            this.house_type5_tao = 0;
        }else {
            this.house_type5_tao = house_type5_tao;
        }
    }

    public Double getHouse_type5_area() {
        return house_type5_area;
    }

    public void setHouse_type5_area(Double house_type5_area) {
        if(house_type5_area == null){
            this.house_type5_area = 0d;
        }else {
            this.house_type5_area = house_type5_area;
        }
    }

    public Double getHouse_type5_money() {
        return house_type5_money;
    }

    public void setHouse_type5_money(Double house_type5_money) {
        if(house_type5_money == null){
            this.house_type5_money = 0d;
        }else {
            this.house_type5_money = house_type5_money;
        }
    }

    public Integer getHouse_type6_tao() {
        return house_type6_tao;
    }

    public void setHouse_type6_tao(Integer house_type6_tao) {
        if(house_type6_tao == null){
            this.house_type6_tao = 0;
        }else {
            this.house_type6_tao = house_type6_tao;
        }
    }

    public Double getHouse_type6_area() {
        return house_type6_area;
    }

    public void setHouse_type6_area(Double house_type6_area) {
        if(house_type6_area == null){
            this.house_type6_area = 0d;
        }else {
            this.house_type6_area = house_type6_area;
        }
    }

    public Double getHouse_type6_money() {
        return house_type6_money;
    }

    public void setHouse_type6_money(Double house_type6_money) {
        if(house_type6_money == null){
            this.house_type6_money = 0d;
        }else {
            this.house_type6_money = house_type6_money;
        }
    }



    @Override
    public String toString() {
        return "BusinessTotal{" +
                "area='" + area + '\'' +
                ", label='" + label + '\'' +
                ", house_type1_tao=" + house_type1_tao +
                ", house_type1_area=" + house_type1_area +
                ", house_type1_money=" + house_type1_money +
                ", house_type2_tao=" + house_type2_tao +
                ", house_type2_area=" + house_type2_area +
                ", house_type2_money=" + house_type2_money +
                ", house_type3_tao=" + house_type3_tao +
                ", house_type3_area=" + house_type3_area +
                ", house_type3_money=" + house_type3_money +
                ", house_type4_tao=" + house_type4_tao +
                ", house_type4_area=" + house_type4_area +
                ", house_type4_money=" + house_type4_money +
                ", house_type5_tao=" + house_type5_tao +
                ", house_type5_area=" + house_type5_area +
                ", house_type5_money=" + house_type5_money +
                ", house_type6_tao=" + house_type6_tao +
                ", house_type6_area=" + house_type6_area +
                ", house_type6_money=" + house_type6_money +
                '}';
    }
}
