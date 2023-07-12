package com.zeal.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@TableName("t_employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String gender;

    private LocalDate birthday;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 婚姻状况
     */
    private String wedlock;

    /**
     * 民族
     */
    private Integer nationId;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 政治面貌
     */
    private Integer politicId;

    private String email;

    private String phone;

    private String address;

    /**
     * 部门ID
     */
    private Integer departmentId;

    /**
     * 职称ID
     */
    private Integer jobLevelId;

    /**
     * 职位ID
     */
    private Integer posId;

    /**
     * 聘用形式
     */
    private String engageForm;

    /**
     * 学历
     */
    private String tiptopDegree;

    /**
     * 所属专业
     */
    private String specialty;

    /**
     * 毕业学校
     */
    private String school;

    /**
     * 入职日期
     */
    private LocalDate beginDate;

    /**
     * 在职状态
     */
    private String workState;

    /**
     * 工号
     */
    private Integer workId;

    /**
     * 合同期限
     */
    private Object contractTerm;

    /**
     * 转正日期
     */
    private LocalDate conversionTime;

    /**
     * 离职日期
     */
    private LocalDate notWorkDate;

    /**
     * 合同开始日期
     */
    private LocalDate beginContract;

    /**
     * 合同终止日期
     */
    private LocalDate endContract;

    /**
     * 工龄
     */
    private String workAge;

    /**
     * 工资账套ID
     */
    private Integer salaryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock;
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Integer getPoliticId() {
        return politicId;
    }

    public void setPoliticId(Integer politicId) {
        this.politicId = politicId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getEngageForm() {
        return engageForm;
    }

    public void setEngageForm(String engageForm) {
        this.engageForm = engageForm;
    }

    public String getTiptopDegree() {
        return tiptopDegree;
    }

    public void setTiptopDegree(String tiptopDegree) {
        this.tiptopDegree = tiptopDegree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Object getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Object contractTerm) {
        this.contractTerm = contractTerm;
    }

    public LocalDate getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(LocalDate conversionTime) {
        this.conversionTime = conversionTime;
    }

    public LocalDate getNotWorkDate() {
        return notWorkDate;
    }

    public void setNotWorkDate(LocalDate notWorkDate) {
        this.notWorkDate = notWorkDate;
    }

    public LocalDate getBeginContract() {
        return beginContract;
    }

    public void setBeginContract(LocalDate beginContract) {
        this.beginContract = beginContract;
    }

    public LocalDate getEndContract() {
        return endContract;
    }

    public void setEndContract(LocalDate endContract) {
        this.endContract = endContract;
    }

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id = " + id +
            ", name = " + name +
            ", gender = " + gender +
            ", birthday = " + birthday +
            ", idCard = " + idCard +
            ", wedlock = " + wedlock +
            ", nationId = " + nationId +
            ", nativePlace = " + nativePlace +
            ", politicId = " + politicId +
            ", email = " + email +
            ", phone = " + phone +
            ", address = " + address +
            ", departmentId = " + departmentId +
            ", jobLevelId = " + jobLevelId +
            ", posId = " + posId +
            ", engageForm = " + engageForm +
            ", tiptopDegree = " + tiptopDegree +
            ", specialty = " + specialty +
            ", school = " + school +
            ", beginDate = " + beginDate +
            ", workState = " + workState +
            ", workId = " + workId +
            ", contractTerm = " + contractTerm +
            ", conversionTime = " + conversionTime +
            ", notWorkDate = " + notWorkDate +
            ", beginContract = " + beginContract +
            ", endContract = " + endContract +
            ", workAge = " + workAge +
            ", salaryId = " + salaryId +
        "}";
    }
}
