package com.rrrent.theia.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 
 * 
 * @Description: 接口返回报文数据封装bean
 *
 * @author: jack
 */
@Getter
@Setter
@NoArgsConstructor
@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class ResponseInfo<T> implements Serializable {

	private static final long serialVersionUID = -2210269174549937804L;

	// 成功状态码
	public static final Integer SUCCESS_CODE = Integer.valueOf(200);

	// 失败状态码
	public static final Integer FAIL_CODE = Integer.valueOf(0);

	// 接口状态码
	private Integer code;

	// 提示消息
	private String message;

	// 接口业务数据
	private T result;


	public ResponseInfo(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ResponseInfo(Integer code, T result) {
		super();
		this.code = code;
		this.result = result;
	}

	public ResponseInfo(Integer code) {
		super();
		this.code = code;
	}

	public static <T> ResponseInfo<T> success(T result){
		return new ResponseInfo<>(ResponseInfo.SUCCESS_CODE, result);
	}

	public static <T> ResponseInfo<T> delSuccess(T result){
		return new ResponseInfo<>(ResponseInfo.SUCCESS_CODE, result);
	}

	public static <T> ResponseInfo<T> success() {
		return new ResponseInfo<>(ResponseInfo.SUCCESS_CODE);
	}

	public static <T> ResponseInfo<T> success(String msg){
		return new ResponseInfo<>(ResponseInfo.SUCCESS_CODE,msg);
	}

	public static <T> ResponseInfo<T> fail(String errorMsg) {
		return new ResponseInfo<>(ResponseInfo.FAIL_CODE, errorMsg);
	}

	public static <T> ResponseInfo<T> fail(Integer errCode) {
		if (errCode.equals(SUCCESS_CODE)) {
			return new ResponseInfo<>(FAIL_CODE);
		}
		return new ResponseInfo<>(errCode);
	}

	public static <T> ResponseInfo<T> fail(Integer errCode, String errMessage) {
		return new ResponseInfo<>(errCode, errMessage);
	}
}