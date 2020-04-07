package com.sac.redis02springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ShiAC
 * @date 2020/4/7
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
//在企业中，我们的pojo类都会序列化
public class User implements Serializable {
    private String name;
    private int age;
}
