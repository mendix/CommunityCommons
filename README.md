Please see [Community Commons Function Library](https://docs.mendix.com/appstore/modules/community-commons-function-library) in the Mendix documentation for details.

In version 7.2.0 of this module, we introduced a new way of dependency management using a Gradle build file. Unfortunately, this does not mean that obsolete JARs are automatically deleted from your apps' userlib folders when you import this module into your app model.

To download the dependencies and copy them to the `userlib` folder of the Community Commons container project, execute `gradle prepareDeps` from the command line. Afterwards, you will be able to export a CommunityCommons.mpk module from the Community Commons main project. Select only the dependencies listed below in the Dependencies section as dependencies in userlib for the exported module.

The Community Commons container project contains a variety of predesigned unit tests. To use these tests, download the Unit Testing module from the Mendix Marketplace. This module has a dependency on the Object Handling module, so that module should also be imported to your app if you want to run the tests.
