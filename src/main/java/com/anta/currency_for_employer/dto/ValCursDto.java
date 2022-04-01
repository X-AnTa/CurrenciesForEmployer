package com.anta.currency_for_employer.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ValCursDto {

    @XmlElement(name = "Valute")
    private List<ValuteDto> valuteDtoList;
}
