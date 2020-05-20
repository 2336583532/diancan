package com.hnit.biz.Exception;
/**
 * 自定义的异常类
 * @author 23365
 *
 */
public class MyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyException(){
		 super();
	}
	public MyException(String msg){
		super(msg);
	}
}
