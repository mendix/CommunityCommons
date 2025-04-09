// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import java.util.ArrayList;
import java.util.List;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import communitycommons.proxies.SplitItem;
import com.mendix.systemwideinterfaces.core.UserAction;

public class StringSplit extends UserAction<java.util.List<IMendixObject>>
{
	private final java.lang.String inputString;
	private final java.lang.String splitParameter;

	public StringSplit(
		IContext context,
		java.lang.String _inputString,
		java.lang.String _splitParameter
	)
	{
		super(context);
		this.inputString = _inputString;
		this.splitParameter = _splitParameter;
	}

	@java.lang.Override
	public java.util.List<IMendixObject> executeAction() throws Exception
	{
		// BEGIN USER CODE
		List<IMendixObject> returnList = new ArrayList<IMendixObject>();
		String[] parts = this.inputString.split(this.splitParameter);
		Integer index = 0;
		for (String part : parts) {
			IMendixObject splitPart = Core.instantiate(getContext(), SplitItem.getType());
			splitPart.setValue(getContext(), SplitItem.MemberNames.Value.toString(), part);
			splitPart.setValue(getContext(), SplitItem.MemberNames.Index.toString(), index);
			returnList.add(splitPart);
			index = index + 1;
		}
		return returnList;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "StringSplit";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
