package com.jinhong.th58;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer error;
    private String error_mess;
    private Object data;
}
