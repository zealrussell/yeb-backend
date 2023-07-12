package com.zeal.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@TableName("t_salary")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 基本工资
     */
    private BigDecimal basicSalary;

    /**
     * 奖金
     */
    private BigDecimal bonus;

    /**
     * 午餐补助
     */
    private BigDecimal lunchSalary;

    /**
     * 交通补助
     */
    private BigDecimal trafficSalary;

    /**
     * 应发工资
     */
    private Long allSalary;

    /**
     * 养老金基数
     */
    private Integer pensionBase;

    /**
     * 养老金比率
     */
    private Double pensionPer;

    /**
     * 启用时间
     */
    private LocalDateTime createDate;

    /**
     * 医疗基数
     */
    private Integer medicalBase;

    /**
     * 医疗比率
     */
    private Double medicalPer;

    /**
     * 公积金基数
     */
    private Integer accumulationFundBase;

    /**
     * 公积金比率
     */
    private Double accumulationFundPer;

    /**
     * 名称
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getLunchSalary() {
        return lunchSalary;
    }

    public void setLunchSalary(BigDecimal lunchSalary) {
        this.lunchSalary = lunchSalary;
    }

    public BigDecimal getTrafficSalary() {
        return trafficSalary;
    }

    public void setTrafficSalary(BigDecimal trafficSalary) {
        this.trafficSalary = trafficSalary;
    }

    public Long getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(Long allSalary) {
        this.allSalary = allSalary;
    }

    public Integer getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(Integer pensionBase) {
        this.pensionBase = pensionBase;
    }

    public Double getPensionPer() {
        return pensionPer;
    }

    public void setPensionPer(Double pensionPer) {
        this.pensionPer = pensionPer;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getMedicalBase() {
        return medicalBase;
    }

    public void setMedicalBase(Integer medicalBase) {
        this.medicalBase = medicalBase;
    }

    public Double getMedicalPer() {
        return medicalPer;
    }

    public void setMedicalPer(Double medicalPer) {
        this.medicalPer = medicalPer;
    }

    public Integer getAccumulationFundBase() {
        return accumulationFundBase;
    }

    public void setAccumulationFundBase(Integer accumulationFundBase) {
        this.accumulationFundBase = accumulationFundBase;
    }

    public Double getAccumulationFundPer() {
        return accumulationFundPer;
    }

    public void setAccumulationFundPer(Double accumulationFundPer) {
        this.accumulationFundPer = accumulationFundPer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Salary{" +
            "id = " + id +
            ", basicSalary = " + basicSalary +
            ", bonus = " + bonus +
            ", lunchSalary = " + lunchSalary +
            ", trafficSalary = " + trafficSalary +
            ", allSalary = " + allSalary +
            ", pensionBase = " + pensionBase +
            ", pensionPer = " + pensionPer +
            ", createDate = " + createDate +
            ", medicalBase = " + medicalBase +
            ", medicalPer = " + medicalPer +
            ", accumulationFundBase = " + accumulationFundBase +
            ", accumulationFundPer = " + accumulationFundPer +
            ", name = " + name +
        "}";
    }
}
