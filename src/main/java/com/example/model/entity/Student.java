package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生年龄
     */
    private Integer age;

    /**
     * 
     */
    private String sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 专业
     */
    private String speciality;

    /**
     * 班级
     */
    private String myClass;

    /**
     * 奖惩
     */
    private String reawrdPunish;

    /**
     * (0:未入学,1:在读,2:已毕业)
     */
    private Integer checkIn;

    /**
     * 生源地
     */
    private String from;

    /**
     * (0:不留校,1:留校)
     */
    private Integer inSchool;

    /**
     * 创建日期
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新日期
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Student other = (Student) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getSpeciality() == null ? other.getSpeciality() == null : this.getSpeciality().equals(other.getSpeciality()))
            && (this.getMyClass() == null ? other.getMyClass() == null : this.getMyClass().equals(other.getMyClass()))
            && (this.getReawrdPunish() == null ? other.getReawrdPunish() == null : this.getReawrdPunish().equals(other.getReawrdPunish()))
            && (this.getCheckIn() == null ? other.getCheckIn() == null : this.getCheckIn().equals(other.getCheckIn()))
            && (this.getFrom() == null ? other.getFrom() == null : this.getFrom().equals(other.getFrom()))
            && (this.getInSchool() == null ? other.getInSchool() == null : this.getInSchool().equals(other.getInSchool()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getSpeciality() == null) ? 0 : getSpeciality().hashCode());
        result = prime * result + ((getMyClass() == null) ? 0 : getMyClass().hashCode());
        result = prime * result + ((getReawrdPunish() == null) ? 0 : getReawrdPunish().hashCode());
        result = prime * result + ((getCheckIn() == null) ? 0 : getCheckIn().hashCode());
        result = prime * result + ((getFrom() == null) ? 0 : getFrom().hashCode());
        result = prime * result + ((getInSchool() == null) ? 0 : getInSchool().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pwd=").append(pwd);
        sb.append(", email=").append(email);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", balance=").append(balance);
        sb.append(", speciality=").append(speciality);
        sb.append(", myClass=").append(myClass);
        sb.append(", reawrdPunish=").append(reawrdPunish);
        sb.append(", checkIn=").append(checkIn);
        sb.append(", from=").append(from);
        sb.append(", inSchool=").append(inSchool);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}