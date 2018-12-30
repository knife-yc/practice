package com.yc.practice.test.zip;

/**
 * 自定义异常类
 */
public class FileUtilException extends Exception {

	private static final long serialVersionUID = -2493099997631642753L;

	/**
     * 构造一个基本异常
     * @param message 信息描述
     */
    public FileUtilException(String message) {
        super(message);
    }

    /**
     * 构造一个基本异常
     * @param message 信息描述
     * @param cause 根异常类（可以存入任何异常）
     */
    public FileUtilException(String message, Throwable cause) {
        super(message, cause);
    }
}
