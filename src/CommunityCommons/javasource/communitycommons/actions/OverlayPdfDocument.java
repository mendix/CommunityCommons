// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import communitycommons.Misc;
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * Overlay a generated PDF document with another PDF (containing the company stationary for example)
 */
public class OverlayPdfDocument extends UserAction<java.lang.Boolean>
{
	/** @deprecated use generatedDocument.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __generatedDocument;
	private final system.proxies.FileDocument generatedDocument;
	/** @deprecated use overlay.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __overlay;
	private final system.proxies.FileDocument overlay;
	private final java.lang.Boolean onTopOfContent;

	public OverlayPdfDocument(
		IContext context,
		IMendixObject _generatedDocument,
		IMendixObject _overlay,
		java.lang.Boolean _onTopOfContent
	)
	{
		super(context);
		this.__generatedDocument = _generatedDocument;
		this.generatedDocument = _generatedDocument == null ? null : system.proxies.FileDocument.initialize(getContext(), _generatedDocument);
		this.__overlay = _overlay;
		this.overlay = _overlay == null ? null : system.proxies.FileDocument.initialize(getContext(), _overlay);
		this.onTopOfContent = _onTopOfContent;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		return Misc.overlayPdf(getContext(), __generatedDocument, __overlay, onTopOfContent);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "OverlayPdfDocument";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
