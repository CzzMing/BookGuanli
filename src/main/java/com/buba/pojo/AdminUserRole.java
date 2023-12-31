package com.buba.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Relations between users and role.
 *
 * @author Evan
 * @date 2019/11
 */
@Data
@Entity
@TableName("admin_user_role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * User id.
     */
    private int uid;

    /**
     * Role id.
     */
    private int rid;
}
