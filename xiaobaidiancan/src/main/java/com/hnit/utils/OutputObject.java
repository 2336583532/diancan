package com.hnit.utils;

import lombok.Data;
/**
 * 
 * @author 23365
 *返回数据的包装类
 */
@Data
public class OutputObject {
	private int code;
	private String message;
	private Object data;
}
