# CommunityCommons

This module adds a number reusable Java methods to your project, which can be called from Microflows or custom Java actions. The content of this module is the result of many questions and answers posted at mxforum.mendix.com, and investigating several project. The module addes functionality for working with Dates, Batches, Strings, Internet, Files, Object Locking, Configuration etc. See the documentation or screenshot for a complete list of functions.

## Description

This module adds many reusable java methods to your project, which can be called from microflows or custom java actions. The content of this module is the result of many questions and answers posted at mxforum.mendix.com, and investigating several project.

The module adds functionality for working with Dates, Batches, Strings, Internet, Files, Configuration, locking etc. See the documentation or screenshot for a complete list of functions.

## _Important when updating_ :warning:

### Deleting obsolete dependencies first
It is *highly* recommended that you remove all jars that have an accompanying `.CommunityCommons.RequiredLib` file from the `userlib` folder by hand before importing the CommunityCommons 7.2.0 module in the Modeler. Otherwise you may encounter strange compilation or runtime errors.

### Java 8
This release utilizes some Java 8 native APIs that replace functionality that was previously imported from external libraries.
This means that for upgrading, [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) is a minimum requirement! You can change your JDK directory in the Desktop Modeler under Edit > Preferences.

### Breaking change to XSSSanitize

In order to mitigate some security vulnerabilities in dependent libraries, the XSSSanitize action has been re-implemented using the [OWASP Java HTML Sanitizer](https://github.com/OWASP/java-html-sanitizer) library.
This means that any usage of this action in your app needs to be reconfigured. It now takes six policy parameters, of which at least one must be non-empty. Make sure that the non applicable policy parameters are explicitly filled in with the value `empty`.
Possible policy values are defined in the `SanitizerPolicy` enumeration. The meaning of the policies are defined in the [javadocs](https://static.javadoc.io/com.googlecode.owasp-java-html-sanitizer/owasp-java-html-sanitizer/20180219.1/org/owasp/html/Sanitizers.html).

## Usage

All functions in this package can be invoked using a microflow Java action call in a Microflow, our from your own java code by calling

`communitycommons.<Action Folder>.<Action name>;`

for example : `commonitycommons.StringUtils.hash("Mendix", 20);`

The module contains one constant: `CommunityCommons.MergeMultiplePdfs_MaxAtOnce`. It is used in the _MergeMultiplePdfs_ Java action to restrict the number of PDFs processed at the same time.
Restricted to 10 files at once for Mendix Cloud v4 compatibility. If you need to merge more than 10 files increase the number here. Setting the value to <= 0 means unlimited.

## Testing

The Community Commons container project contains a variety of predesigned unit tests. To be able to use these tests the UnitTesting module should be downloaded from the AppStore. Please refer to the UnitTesting documentation for further instruction regarding the implementation. The UnitTesting has a dependency to the ObjectHandling module, so that module should also be imported to this project if you want to run the tests. 

## Contributing

For more information on contributing to this repository visit [Contributing to a GitHub repository](https://docs.mendix.com/howto/collaboration-project-management/contribute-to-a-github-repository)!

### Gradle
In version 7.2.0, we introduce a new way of dependency management using a [Gradle](https://gradle.org/install/) build file.
Unfortunately, this doesn't mean that obsoleted jars are automatically deleted from your projects' `userlib` folder when you import the Community Commons module into your app model.

To download the dependencies and copy them to the `userlib/` folder of the Community Commons container project, execute:
```
gradle prepareDeps
``` 
from the command line. Afterwards, you will be able to export a CommunityCommons.mpk module from the Community Commons main project. Select only the dependencies listed below as dependencies in userlib for the exported module.

## Dependencies
 -  commons.io-2.6.jar
 -  commons.lang3-3.7.jar
 -  pdfbox-2.0.11.jar
 -  fontbox-2.0.11.jar
 -  guava-19.0.jar
 -  owasp-java-html-sanitizer-20180219.1.jar

## Function list

### Batches

*AddToBatch* - Add an object to a batch.

*CommitBatch* - Commit a batch.

*DeleteAll* - Removes ALL instances of a certain domain object type using batches.


### DateTime

*DateTimeToLong* - Converts a DateTime to a Unix timestamps (Milliseconds since 1-1-1970).

*LongToDateTime* - Converts a Unix timestamp(ms) to a dateTime object.

*YearsBetween* - Calculates the number of years between two dates.

*GetIntFromDateTime* - Extracts a part of a date (year, month or day) to an integer (new in 2.2).

### Files

*Base64DecodeToFile* - Stores an base 64 encoded string plain in the provided target file document.

*Base64EncodeFile* - Converts an unencode file to a base 64 encoded string.

*StringToFile* - Stores a string into the provides filedocument.

*StringFromFile* -  Reads the contents form the provided file document and return it as string.

*StoreURLToFileDocument* - Retrieve a document from an URL using a HTTP GET request.

*DuplicateFileDocument* - Clones the contents of one file document into another.

*GetFileSize* - Returns the filesize of a file document in bytes.

*OverlayPdfDocument* - Overlay a generated PDF document with another PDF (containing the company stationary for example)

### Logging

*CreateLogNode* - Initialize a log node without having a log line.

*Log* - Prints a message to the Mendix log.

*SimpleLog* - Logs a message to 'Community Commons' with loglevel 'Info'.

*TimeMeasureStart* - Start timing something, and print the result to the log.

*TimeMeasureEnd* - End timing something, and print the result to the log.


### Misc

*AssertTrue* - Shorthand for checking something, and throwing an error if that something is not true. Saves creating three microflow items for things that MUST be true.

*CreateUserIfNotExists* - (Microflow) Create a user with predefined password an role. Useful during startup for integration purposes. Changed in 2.4: The user now always gets updated, if even the user already exists.

*Delay* - Causes this request to sleep for a while. Useful to prevent brute force attacks or to simulate latency delays.

*GetApplicationUrl* - Returns the runtime URL of this application.

*GetRuntimeVersion* - Returns the runtime version of this application.

*RetrieveURL* - Retrieves data (such as an HTML page) from an URL using the HTTP protocol, and returns it as string.

*ThrowException* - This action always throws an exception (of type communityutils.UserThrownError), which is, in combination with custom error handling, quite useful to end a microflow prematurely or to bail out to the calling action/ microflow.

*ThrowWebserviceException* - Throws an exception. This is very useful if the microflow is called by a webservice. If you throw this kind of exceptions, a fault message will be generated in the output, instead of an '501 Internal server' error.  (Fixed/ updated in 2.2)

*GetDefaultLanguage* - Gets the Language object for the default language as defined in the model.

### Execute Microflow

*executeMicroflowAsUser* -  Executes the given microflow as if the $currentuser is the provided user (delegation).

*RunMicroflowAsyncInQueue* - Runs a microflow asynchronous, that is, this action immediately returns but schedules the microflow to be run in the near future. The queue guarantees a first come first serve order of the microflows, and only one action is served at a time. The microflow is run with system rights in its own transaction, and is very useful to run heavy microflows on the background.

*executeMicroflowInBackground* - Similar to RunMicroflowAsyncInQueue, but accepts one argument as parameter. (new in 2.2)

*executeMicroflowInBatches (Recommended!)* - Performs a batch operation on a large dataset, by invoking the microflow on small subsets of the data, each with its own database transaction.  (new in 2.2)
*recommitInBatches* - Recommits (with events) all items returned by the xpath query. Useful in migration scenerios (new in 2.4)

### ORM

*CommitWithoutEvents* - Commits an object, but without events.

*Clone* - Clones objects

*DeepClone* - Clones objects, their associations and even referred objects.

*GetGUID* - returns the Global Unique Identifier (GUID, or id) of an object.

*GetOriginalValueAsString* - Returns the original value of an object member, that is, the last committed value.

*GetTypeAsString* - Returns the actual type of an Entity. Useful as alternative way to split upon inheritance, or as input of other functions in this module.

*MemberHasChanged* - Checks whether a member has changed since the last commit. Useful in combination with getOriginalValueAsString.

*RefreshClass* - Refreshes a certain domain object type in the client. Useful to enforce a datagrid to refresh for example.

*refreshClassByObject* - Refreshes a certain domain object type in the client. Useful to enforce a datagrid to refresh for example.

*getLastChangedByUser* -  Returns the user that last changed an object as System.User (new in 2.2).
*getCreatedByUser* - Returns the user that created an object (new in 2.2).

*encryptMemberIfChanged* - Use this function to automatically encrypt attributes of an object during (for example) before commit. (new in 2.4)

*EndTransaction* - Commit the transaction, this will end this transaction or remove a save point from the queue if the transaction is nested.

*StartTransaction* - Start a transaction, if a transaction is already started for this context, a savepoint will be added.

### Regexes

*EmailAddressRegex* - A, not too restrictive, email address regular expression.

*GUIDOrEmpty* - Same as GUIDRegex, but accepts empty string as well.

*GUIDRegex* - Supports alphanumeric characters, dash and underscore.

*Identifier* - Identifier.


### StringUtils

*Hash* - Hashes a value using the SHA-256 hash algorithm.

*HTMLEncode* - Encodes a string to HTML Entities, so that they can be displayed in the browser without breaking any layout.

*HTMLToPlainText* - Use this function to convert HTML text to plain text. It will preserve linebreaks but strip all other markup. including html entity decoding.

*RandomString* - Generates a random alphanumeric string of the desired length.

*RandomHash* - Generates a random hash, perfectly to use as random, unique identifier.

*RegexReplaceAll* - Performs a regular expression. Identical to the microflow expression funciton 'replaceAll'.

*RegexTest* - Returns true if a value matches a regular expression.

*StringLeftPad* - Pads a string on the left to a certain length.

*StringLength* - Returns -1 if the value is empty, the length otherwise.

*StringRightPad* - Pads a string on the right to a certain length.

*StringTrim* - Left and right trims a string (that is; removes all surrounding whitespace characters such as tabs, spaces and returns).

*SubstituteTemplate* - Given an object and a template, substitutes all fields in the template. Supports attributes, references, referencesets and constants. (updated in 2.2: Enums are now displayed using their caption instead of key)

*Base64Encode* - Converts a plain string to a base64 encoded string.

*Base64Decode* - Converts a base64 encoded string to the plain, original string.

*XSSSanitize* - Removes all potential dangerous HTML from a string so that it can be safely displayed in a browser. This function should be applied to all HTML which is displayed in the browser and can be entered by (untrusted) users.

*RandomStrongPassword* - Returns a random strong password containing at least one number, lowercase character,uppercase character and strange character.

*EncryptString* - Decrypts an AES encrypted string. The keylength should exactly be 16 characters (128 bit). (New in 2.4)

*DecryptString* - Applies AES encryption to the value string using a symmetric key. The keylength should exactly be 16 characters (128 bit). (New in 2.4)
GenerateHMAC_SHA256_hash - Generates and asymmetric hash using the HMAC_SHA256 hash algorithm (New in 2.4)

*SubstringAfter* - Returns the substring of a string after the first occurence of a given separator.

*SubstringAfterLast* - Returns the substring of a string after the last occurence of a given separator.

*SubstringBefore* - Returns the substring of a string before the first occurence of a given separator.

*SubstringBeforeLast* - Returns the substring of a string before the last occurence of a given separator.


## Thank you, community.

The contents of this package has been based upon, among others, those forum threads:

https://mxforum.mendix.com/questions/1108/Java-XPath-Query-How-do-I-retrieve-all-orderlines-related-to-an-order
https://mxforum.mendix.com/questions/1097/Regex-help
https://mxforum.mendix.com/questions/1096/Years-between-function
https://mxforum.mendix.com/questions/330/Best-way-to-deal-with-a-huge-list-in-microflows
https://mxforum.mendix.com/questions/330/Best-way-to-deal-with-a-huge-list-in-microflows
https://mxforum.mendix.com/questions/743/Removing-nonnumeric-characters-
https://mxforum.mendix.com/questions/733/How-to-retrieve-the-GUID-id-of-an-object-in-a-Microflow
https://mxforum.mendix.com/questions/713/Parsing-input-format-zipcode
https://mxforum.mendix.com/questions/703/IMendixObjectMembergetOriginalValue
https://mxforum.mendix.com/questions/657/Get-Mendix-Version-at-runtime
https://mxforum.mendix.com/questions/387/How-to-set-an-enum-value-based-on-a-string-value
https://mxforum.mendix.com/questions/383/Can-I-check-the-length-of-a-string-in-Microflow
https://mxforum.mendix.com/questions/231/Refresh-class-in-microflow
https://mxforum.mendix.com/questions/230/Refresh-object-in-microflow
https://mxforum.mendix.com/questions/228/Navigating-associations-in-Java
https://mxforum.mendix.com/questions/197/Logging-to-the-console-in-Java
