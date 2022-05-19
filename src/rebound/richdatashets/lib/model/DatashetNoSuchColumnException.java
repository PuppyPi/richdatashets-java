package rebound.richdatashets.lib.model;

public class DatashetNoSuchColumnException
extends RuntimeException
{
	private static final long serialVersionUID = 1l;
	
	public DatashetNoSuchColumnException()
	{
		super();
	}
	
	public DatashetNoSuchColumnException(String message)
	{
		super(message);
	}
	
	public DatashetNoSuchColumnException(Throwable cause)
	{
		super(cause);
	}
	
	public DatashetNoSuchColumnException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
