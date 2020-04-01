package me.zhengjie.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class BusinessTotal {

    @Column(name = "area")
    private String area;

    @Column(name = "label")
    private String label;

    @Column(name = "house_type1_tao")
    private String house_type1_tao;

    @Column(name = "house_type1_area")
    private String house_type1_area;

    @Column(name = "house_type1_money")
    private Double house_type1_money;

    @Column(name = "house_type2_tao")
    private String house_type2_tao;

    @Column(name = "house_type2_area")
    private String house_type2_area;

    @Column(name = "house_type2_money")
    private Double house_type2_money;

    @Column(name = "house_type3_tao")
    private String house_type3_tao;

    @Column(name = "house_type3_area")
    private String house_type3_area;

    @Column(name = "house_type3_money")
    private Double house_type3_money;

    @Column(name = "house_type4_tao")
    private String house_type4_tao;

    @Column(name = "house_type4_area")
    private String house_type4_area;

    @Column(name = "house_type4_money")
    private Double house_type4_money;

    @Column(name = "house_type5_tao")
    private String house_type5_tao;

    @Column(name = "house_type5_area")
    private String house_type5_area;

    @Column(name = "house_type5_money")
    private Double house_type5_money;

    @Column(name = "house_type6_tao")
    private String house_type6_tao;

    @Column(name = "house_type6_area")
    private String house_type6_area;

    @Column(name = "house_type6_money")
    private Double house_type6_money;
}
