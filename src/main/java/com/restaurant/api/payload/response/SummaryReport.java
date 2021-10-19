package com.restaurant.api.payload.response;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Getter
@Setter
@NoArgsConstructor
public class SummaryReport {
    private Long current;
    private Long last;
    private String type;
    private double percentage;
    private String status;

    public SummaryReport(Long current, Long last,String type){
        this.current = current;
        this.last = last;
        this.type = type;
        this.percentage = ((current - last) / (double)Math.abs(last)) * 100;
        this.status = (this.current-this.last)>0?"positive":"negative";
    }
}
