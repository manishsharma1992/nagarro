package com.nagarro.domain.payload.request;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountFilterRequest {
	
	private Long acctId;
	
	private List<Date> dateRange;
	
	private List<Double> amountRange;
}
