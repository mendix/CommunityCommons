#### Notes
In version 7.2.0 of this module, we introduced a new way of dependency management using a Gradle build file. Unfortunately, this does not mean that obsolete JARs are automatically deleted from your apps' userlib folders when you import this module into your app model.

The Community Commons container project contains a variety of predesigned unit tests. To use these tests, download the Unit Testing module from the Mendix Marketplace and run `gradle copyAllToUserlib`. This module has a dependency on the Object Handling module, so that module should also be imported to your app if you want to run the tests.

Please see [Community Commons Function Library](https://docs.mendix.com/appstore/modules/community-commons-function-library) in the Mendix documentation for details.

#### To publish the module to Appstore

###### Step 1: Update the required properties
1. `marketplace/release-notes`: (mandatory) Add your release notes txt with the file name as the version number you mentioned in the `gradle.properties` (Example: 2.1.2.txt)
2. `marketplace/decription.html`: (optional) Update the description of the module
3. `marketplace/documentation.html`: (optional) Update the documentation of the module

###### Step 2: Publish the module
Release to the Marketplace with the following command:
```
gradle release -PmarketplaceUsername=myemail@mendix.com -PmarketplacePassword=MyMarketPlacePassword
```

#### References:
1. [Gradle publish marketplace module plugin](https://gitlab.rnd.mendix.com/runtime/gradle-mx-publish-module-plugin) 

