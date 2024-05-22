package com.temelio.technical.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Organization {
    private Long id;
    private String createdAt;
    private String name;
    private String email;
    private String address;
    private boolean emailSent;
}
