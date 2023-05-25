package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLinkStuEmpDTO {
    /**
    *   学生id
     *   sId+afterEid:为学生修改对应的职工
     *   Eid+afterSid:为职工修改对应的学生
    * */
    private Integer sId;
    /**
     * 教师id
    * */
    private Integer eId;
    /**
     *修改后的学生id
     * 1:afterSid或afterEid,只改一个
     * 2:afterSid和afterEid,两个都改
    **/
    private Integer afterSid;

    /**
     * 修改后的教师id
    * */
    private Integer afterEid;

}
