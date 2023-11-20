// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import communitycommons.StringUtils;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;

/**
 * Performs a regular expression. Similar to the replaceAll microflow function, but supports more advanced usages such as capture variables.
 * 
 * For the regexp specification see:
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 * 
 * A decent regexp tester can be found at:
 * http://www.fileformat.info/tool/regex.htm
 */
public class RegexReplaceAll extends CustomJavaAction<java.lang.String>
{
	private java.lang.String haystack;
	private java.lang.String needleRegex;
	private java.lang.String replacement;

	public RegexReplaceAll(IContext context, java.lang.String haystack, java.lang.String needleRegex, java.lang.String replacement)
	{
		super(context);
		this.haystack = haystack;
		this.needleRegex = needleRegex;
		this.replacement = replacement;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		return StringUtils.regexReplaceAll(haystack, needleRegex, replacement);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "RegexReplaceAll";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
