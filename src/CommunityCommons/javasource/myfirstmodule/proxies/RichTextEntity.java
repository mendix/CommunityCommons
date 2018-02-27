// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package myfirstmodule.proxies;

public class RichTextEntity
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject richTextEntityMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "MyFirstModule.RichTextEntity";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Name("Name"),
		Text("Text"),
		Policy("Policy"),
		SanitizedText("SanitizedText"),
		RichTextEntity_PdfOverlay("MyFirstModule.RichTextEntity_PdfOverlay");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public RichTextEntity(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "MyFirstModule.RichTextEntity"));
	}

	protected RichTextEntity(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject richTextEntityMendixObject)
	{
		if (richTextEntityMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("MyFirstModule.RichTextEntity", richTextEntityMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a MyFirstModule.RichTextEntity");

		this.richTextEntityMendixObject = richTextEntityMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'RichTextEntity.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static myfirstmodule.proxies.RichTextEntity initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return myfirstmodule.proxies.RichTextEntity.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static myfirstmodule.proxies.RichTextEntity initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new myfirstmodule.proxies.RichTextEntity(context, mendixObject);
	}

	public static myfirstmodule.proxies.RichTextEntity load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return myfirstmodule.proxies.RichTextEntity.initialize(context, mendixObject);
	}

	public static java.util.List<myfirstmodule.proxies.RichTextEntity> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<myfirstmodule.proxies.RichTextEntity> result = new java.util.ArrayList<myfirstmodule.proxies.RichTextEntity>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//MyFirstModule.RichTextEntity" + xpathConstraint))
			result.add(myfirstmodule.proxies.RichTextEntity.initialize(context, obj));
		return result;
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of Name
	 */
	public final java.lang.String getName()
	{
		return getName(getContext());
	}

	/**
	 * @param context
	 * @return value of Name
	 */
	public final java.lang.String getName(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Name.toString());
	}

	/**
	 * Set value of Name
	 * @param name
	 */
	public final void setName(java.lang.String name)
	{
		setName(getContext(), name);
	}

	/**
	 * Set value of Name
	 * @param context
	 * @param name
	 */
	public final void setName(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String name)
	{
		getMendixObject().setValue(context, MemberNames.Name.toString(), name);
	}

	/**
	 * @return value of Text
	 */
	public final java.lang.String getText()
	{
		return getText(getContext());
	}

	/**
	 * @param context
	 * @return value of Text
	 */
	public final java.lang.String getText(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Text.toString());
	}

	/**
	 * Set value of Text
	 * @param text
	 */
	public final void setText(java.lang.String text)
	{
		setText(getContext(), text);
	}

	/**
	 * Set value of Text
	 * @param context
	 * @param text
	 */
	public final void setText(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String text)
	{
		getMendixObject().setValue(context, MemberNames.Text.toString(), text);
	}

	/**
	 * Set value of Policy
	 * @param policy
	 */
	public final communitycommons.proxies.XSSPolicy getPolicy()
	{
		return getPolicy(getContext());
	}

	/**
	 * @param context
	 * @return value of Policy
	 */
	public final communitycommons.proxies.XSSPolicy getPolicy(com.mendix.systemwideinterfaces.core.IContext context)
	{
		Object obj = getMendixObject().getValue(context, MemberNames.Policy.toString());
		if (obj == null)
			return null;

		return communitycommons.proxies.XSSPolicy.valueOf((java.lang.String) obj);
	}

	/**
	 * Set value of Policy
	 * @param policy
	 */
	public final void setPolicy(communitycommons.proxies.XSSPolicy policy)
	{
		setPolicy(getContext(), policy);
	}

	/**
	 * Set value of Policy
	 * @param context
	 * @param policy
	 */
	public final void setPolicy(com.mendix.systemwideinterfaces.core.IContext context, communitycommons.proxies.XSSPolicy policy)
	{
		if (policy != null)
			getMendixObject().setValue(context, MemberNames.Policy.toString(), policy.toString());
		else
			getMendixObject().setValue(context, MemberNames.Policy.toString(), null);
	}

	/**
	 * @return value of SanitizedText
	 */
	public final java.lang.String getSanitizedText()
	{
		return getSanitizedText(getContext());
	}

	/**
	 * @param context
	 * @return value of SanitizedText
	 */
	public final java.lang.String getSanitizedText(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.SanitizedText.toString());
	}

	/**
	 * Set value of SanitizedText
	 * @param sanitizedtext
	 */
	public final void setSanitizedText(java.lang.String sanitizedtext)
	{
		setSanitizedText(getContext(), sanitizedtext);
	}

	/**
	 * Set value of SanitizedText
	 * @param context
	 * @param sanitizedtext
	 */
	public final void setSanitizedText(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String sanitizedtext)
	{
		getMendixObject().setValue(context, MemberNames.SanitizedText.toString(), sanitizedtext);
	}

	/**
	 * @return value of RichTextEntity_PdfOverlay
	 */
	public final myfirstmodule.proxies.PdfOverlay getRichTextEntity_PdfOverlay() throws com.mendix.core.CoreException
	{
		return getRichTextEntity_PdfOverlay(getContext());
	}

	/**
	 * @param context
	 * @return value of RichTextEntity_PdfOverlay
	 */
	public final myfirstmodule.proxies.PdfOverlay getRichTextEntity_PdfOverlay(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		myfirstmodule.proxies.PdfOverlay result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.RichTextEntity_PdfOverlay.toString());
		if (identifier != null)
			result = myfirstmodule.proxies.PdfOverlay.load(context, identifier);
		return result;
	}

	/**
	 * Set value of RichTextEntity_PdfOverlay
	 * @param richtextentity_pdfoverlay
	 */
	public final void setRichTextEntity_PdfOverlay(myfirstmodule.proxies.PdfOverlay richtextentity_pdfoverlay)
	{
		setRichTextEntity_PdfOverlay(getContext(), richtextentity_pdfoverlay);
	}

	/**
	 * Set value of RichTextEntity_PdfOverlay
	 * @param context
	 * @param richtextentity_pdfoverlay
	 */
	public final void setRichTextEntity_PdfOverlay(com.mendix.systemwideinterfaces.core.IContext context, myfirstmodule.proxies.PdfOverlay richtextentity_pdfoverlay)
	{
		if (richtextentity_pdfoverlay == null)
			getMendixObject().setValue(context, MemberNames.RichTextEntity_PdfOverlay.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.RichTextEntity_PdfOverlay.toString(), richtextentity_pdfoverlay.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return richTextEntityMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final myfirstmodule.proxies.RichTextEntity that = (myfirstmodule.proxies.RichTextEntity) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "MyFirstModule.RichTextEntity";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
