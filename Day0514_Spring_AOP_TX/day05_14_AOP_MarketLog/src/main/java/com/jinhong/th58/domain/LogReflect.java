package com.jinhong.th58.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogReflect {
    private Integer id;
    private String operation_method;//type
    private String action;//action
    private LocalDateTime add_time;
    private boolean success;
    private String error_msg;
}
