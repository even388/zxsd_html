package  com.zxsd.common;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ServiceException(String str){
    	super(generateExceptionInfo(str));
    }
    public ServiceException(Throwable throwable)
    {
        super(throwable);
    }
 
    public ServiceException(Throwable throwable, String str)
    {
        super(generateExceptionInfo(str),throwable);
        throwable.printStackTrace();
    }
    private static String generateExceptionInfo(String exceptionInfo){
    	String prefixStr = "";
    	String suffixStr = "";
    	 
    	StringBuffer msg = new StringBuffer("");
    	 
    	msg.append(prefixStr);
    	msg.append(exceptionInfo);
    	msg.append(suffixStr);
    	 
    	return msg.toString();
    }
}
