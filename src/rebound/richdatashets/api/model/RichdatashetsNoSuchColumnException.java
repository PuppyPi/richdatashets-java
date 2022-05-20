package rebound.richdatashets.api.model;

public class RichdatashetsNoSuchColumnException
extends RuntimeException
{
	private static final long serialVersionUID = 1l;
	
	public RichdatashetsNoSuchColumnException()
	{
		super();
	}
	
	public RichdatashetsNoSuchColumnException(String message)
	{
		super(message);
	}
	
	public RichdatashetsNoSuchColumnException(Throwable cause)
	{
		super(cause);
	}
	
	public RichdatashetsNoSuchColumnException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
