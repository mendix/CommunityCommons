// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import communitycommons.Logging;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * End timing something, and print the result to the log. 
 * - TimerName. Should correspond to the TimerName used with TimeMeasureStart.
 * - LogLevel. The loglevel used to print the result.
 * - The message to be printed in the log.
 */
public class TimeMeasureEnd extends UserAction<java.lang.Long>
{
	private final java.lang.String TimerName;
	private final communitycommons.proxies.LogLevel Loglevel;
	private final java.lang.String message;

	public TimeMeasureEnd(
		IContext context,
		java.lang.String _timerName,
		java.lang.String _loglevel,
		java.lang.String _message
	)
	{
		super(context);
		this.TimerName = _timerName;
		this.Loglevel = _loglevel == null ? null : communitycommons.proxies.LogLevel.valueOf(_loglevel);
		this.message = _message;
	}

	@java.lang.Override
	public java.lang.Long executeAction() throws Exception
	{
		// BEGIN USER CODE
		return Logging.measureEnd(TimerName, Loglevel, message);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "TimeMeasureEnd";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
