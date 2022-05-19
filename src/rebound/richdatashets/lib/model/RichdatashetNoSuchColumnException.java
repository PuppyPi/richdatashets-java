package rebound.richdatashets.lib.model;

public class RichdatashetNoSuchColumnException
extends RuntimeException
{
	private static final long serialVersionUID = 1l;
	
	public RichdatashetNoSuchColumnException()
	{
		super();
	}
	
	public RichdatashetNoSuchColumnException(String message)
	{
		super(message);
	}
	
	public RichdatashetNoSuchColumnException(Throwable cause)
	{
		super(cause);
	}
	
	public RichdatashetNoSuchColumnException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
