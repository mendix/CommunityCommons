// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import com.mendix.systemwideinterfaces.core.IMendixObject;
import communitycommons.StringUtils;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * Stores a string into the provided FileDocument, using the specified encoding.
 * Note that destination will be committed.
 */
public class StringToFile extends UserAction<java.lang.Boolean>
{
	private final java.lang.String value;
	/** @deprecated use destination.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __destination;
	private final system.proxies.FileDocument destination;
	private final communitycommons.proxies.StandardEncodings encoding;

	public StringToFile(
		IContext context,
		java.lang.String _value,
		IMendixObject _destination,
		java.lang.String _encoding
	)
	{
		super(context);
		this.value = _value;
		this.__destination = _destination;
		this.destination = _destination == null ? null : system.proxies.FileDocument.initialize(getContext(), _destination);
		this.encoding = _encoding == null ? null : communitycommons.proxies.StandardEncodings.valueOf(_encoding);
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		Charset charset = StandardCharsets.UTF_8;
		if (this.encoding != null)
			charset = Charset.forName(this.encoding.name().replace('_', '-'));
		StringUtils.stringToFile(getContext(), value, destination, charset);
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "StringToFile";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
