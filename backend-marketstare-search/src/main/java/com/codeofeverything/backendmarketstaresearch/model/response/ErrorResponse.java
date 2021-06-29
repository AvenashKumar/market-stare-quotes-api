package com.codeofeverything.backendmarketstaresearch.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
	private String errorCode;
	private String error;
	private String message;
}
