package com.decodedbytes.beans;

import lombok.Data;

@Data
public class InboundNameAddress {
    private Long id;
    private String name;
    private String houseNumber;
    private String city;
    private String province;
    private String postalCode;
}