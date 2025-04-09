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
import communitycommons.ORM;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * Checks whether a member has changed since the last commit. Useful in combination with getOriginalValueAsString.
 * 
 * - item : the object to inspect
 * - member: the name of the member to inspect. Note that for references, the module name needs to be included.
 * 
 * Returns true if changed.
 */
public class memberHasChanged extends UserAction<java.lang.Boolean>
{
	private final IMendixObject item;
	private final java.lang.String member;

	public memberHasChanged(
		IContext context,
		IMendixObject _item,
		java.lang.String _member
	)
	{
		super(context);
		this.item = _item;
		this.member = _member;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		return ORM.memberHasChanged(this.getContext(), item, member);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "memberHasChanged";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
