
# Anypoint Template: Workday and Salesforce Worker Aggregation	

<!-- Header (start) -->
This template aggregates workers from Workday and users from Salesforce into a CSV file. You can modify this basic pattern to collect from one or more different sources and to produce formats other than CSV. Trigger with an HTTP call either manually or programmatically. 

Workers or users are sorted such that the users only in Salesforce appear first, followed by workers only in Workday, and lastly by workers or users found in both systems. The custom sort or merge logic can be easily modified to present the data as needed. This template also serves as a great base for building APIs using the Anypoint Platform.

Aggregation templates can be easily extended to return a multitude of data in mobile friendly form to power your mobile initiatives by providing easily consumable data structures from otherwise complex backend systems.

![9e98b598-c4e5-40e1-89dc-f9d72d8d7fde-image.png](https://exchange2-file-upload-service-kprod.s3.us-east-1.amazonaws.com:443/9e98b598-c4e5-40e1-89dc-f9d72d8d7fde-image.png)
<!-- Header (end) -->

# License Agreement
This template is subject to the conditions of the <a href="https://s3.amazonaws.com/templates-examples/AnypointTemplateLicense.pdf">MuleSoft License Agreement</a>. Review the terms of the license before downloading and using this template. You can use this template for free with the Mule Enterprise Edition, CloudHub, or as a trial in Anypoint Studio. 
# Use Case
<!-- Use Case (start) -->
As a Salesforce admin I want to aggregate users from Workday and Salesforce and compare them to see which users can only be found in one of the two and which users are in both instances. 

For practical purposes this template generates the result in the format of a CSV report sent by email.

This template serves as a foundation for extracting data from two systems, aggregating data, comparing values of fields for the objects, and generating a report on the differences. 

As implemented, it gets workers from Workday and users from Salesforce, compares by the Email address of the workers and users, and generates a CSV file which shows workers in Workday, users in Salesforce. The final report in CSV format is sent to the emails you configured in the  `mule.*.properties` file.
<!-- Use Case (end) -->

# Considerations
<!-- Default Considerations (start) -->

<!-- Default Considerations (end) -->

<!-- Considerations (start) -->
To run this template, there are certain preconditions that must be considered. All of them deal with the preparations in both, that must be made for the template to run smoothly. Failing to do so can lead to unexpected behavior of the template.
<!-- Considerations (end) -->

## Salesforce Considerations

- Where can I check that the field configuration for my Salesforce instance is the right one? See: <a href="https://help.salesforce.com/HTViewHelpDoc?id=checking_field_accessibility_for_a_particular_field.htm&language=en_US">Salesforce: Checking Field Accessibility for a Particular Field</a>.
- How can I modify the Field Access Settings? See: [Salesforce: Modifying Field Access Settings](https://help.salesforce.com/HTViewHelpDoc?id=modifying_field_access_settings.htm&language=en_US "Salesforce: Modifying Field Access Settings")

### As a Data Destination

There are no considerations with using Salesforce as a data destination.

## Workday Considerations

### As a Data Source

The Workday connector currently does not support autopaging functionality out of the box so the number of processed objects are limited to the connector's single page size.

# Run it!
Simple steps to get this template running.
<!-- Run it (start) -->

<!-- Run it (end) -->

## Running On Premises
Complete all properties in one of the property files, for example in mule.prod.properties and run your app with the corresponding environment variable to use it. To follow the example, use `mule.env=prod`.

After this, to trigger the use case you just need to browse to the local HTTP endpoint with the port you configured in your file. If this is, for instance, `9090` then browse to: `http://localhost:9090/generatereport`. The final report in CSV format is sent to the emails you configured in the  `mule.*.properties` file.
<!-- Running on premise (start) -->

<!-- Running on premise (end) -->

### Where to Download Anypoint Studio and the Mule Runtime
If you are new to Mule, download this software:

+ [Download Anypoint Studio](https://www.mulesoft.com/platform/studio)
+ [Download Mule runtime](https://www.mulesoft.com/lp/dl/mule-esb-enterprise)

**Note:** Anypoint Studio requires JDK 8.
<!-- Where to download (start) -->

<!-- Where to download (end) -->

### Importing a Template into Studio
In Studio, click the Exchange X icon in the upper left of the taskbar, log in with your Anypoint Platform credentials, search for the template, and click Open.
<!-- Importing into Studio (start) -->

<!-- Importing into Studio (end) -->

### Running on Studio
After you import your template into Anypoint Studio, follow these steps to run it:

1. Locate the properties file `mule.dev.properties`, in src/main/resources.
2. Complete all the properties required per the examples in the "Properties to Configure" section.
3. Right click the template project folder.
4. Hover your mouse over `Run as`.
5. Click `Mule Application (configure)`.
6. Inside the dialog, select Environment and set the variable `mule.env` to the value `dev`.
7. Click `Run`.

<!-- Running on Studio (start) -->

<!-- Running on Studio (end) -->

### Running on Mule Standalone
Update the properties in one of the property files, for example in mule.prod.properties, and run your app with a corresponding environment variable. In this example, use `mule.env=prod`. 


## Running on CloudHub
When creating your application in CloudHub, go to Runtime Manager > Manage Application > Properties to set the environment variables listed in "Properties to Configure" as well as the mule.env value.
<!-- Running on Cloudhub (start) -->
Once your app is all set and started, if you choose as domain name `workdayworkersaggregation` to trigger the use case you just need to browse to `http://workdayworkersaggregation.cloudhub.io/generatereport`. The final report in CSV format is sent to the emails you configured in the  `mule.*.properties` file.
<!-- Running on Cloudhub (end) -->

### Deploying a Template in CloudHub
In Studio, right click your project name in Package Explorer and select Anypoint Platform > Deploy on CloudHub.
<!-- Deploying on Cloudhub (start) -->

<!-- Deploying on Cloudhub (end) -->

## Properties to Configure
To use this template, configure properties such as credentials, configurations, etc.) in the properties file or in CloudHub from Runtime Manager > Manage Application > Properties. The sections that follow list example values.

### Application Configuration
<!-- Application Configuration (start) -->

- http.port `9090` 

#### Workday Connector Configuration for Company A

- wday.user `joan`
- wday.tenant `acme_pt1`
- wday.password `joanPass123`
- wday.hostname `your_impl-cc.workday.com`

#### SalesForce Connector Configuration for Company B

- sfdc.username `joan.baez@orgb`
- sfdc.password `JoanBaez456`
- sfdc.securityToken `ces56arl7apQs56XTddf34X`
- sfdc.url `https://login.salesforce.com/services/Soap/u/32.0`

#### SMTP Services Configuration

- smtp.host `smtp.gmail.com`
- smtp.port `587`
- smtp.user `exampleuser@gmail.com`
- smtp.password `ExamplePassword456`

#### Email Details

- mail.from `exampleuser@gmail.com`
- mail.to `woody.guthrie@gmail.com`
- mail.subject `Salesforce Users Report`
- mail.body `Users report comparing users from Salesforce Accounts`
- attachment.name `OrderedReport.csv`
<!-- Application Configuration (end) -->

# API Calls
<!-- API Calls (start) -->
SalesForce imposes limits on the number of API calls that can be made. In this template there is only one call made so this is not something to worry about.
<!-- API Calls (end) -->

# Customize It!
This brief guide provides a high level understanding of how this template is built and how you can change it according to your needs. As Mule applications are based on XML files, this page describes the XML files used with this template. More files are available such as test classes and Mule application files, but to keep it simple, we focus on these XML files:

* config.xml
* businessLogic.xml
* endpoints.xml
* errorHandling.xml
<!-- Customize it (start) -->

<!-- Customize it (end) -->

## config.xml
<!-- Default Config XML (start) -->
This file provides the configuration for connectors and configuration properties. Only change this file to make core changes to the connector processing logic. Otherwise, all parameters that can be modified should instead be in a properties file, which is the recommended place to make changes.
<!-- Default Config XML (end) -->

<!-- Config XML (start) -->

<!-- Config XML (end) -->

## businessLogic.xml
<!-- Default Business Logic XML (start) -->
The functional aspects of this template are implemented in this XML file, directed by a flow responsible for aggregating  data, comparing records, and finally formatting the output. The final report in CSV format is sent to the emails you configured in the  `mule.*.properties` file.

This template uses the Scatter-Gather component to query the data in different systems. After that the aggregation is implemented in DataWeave 2 script using Transform component.

Aggregated results are sorted by source of existence:

1. Workers only in Workday.
2. Users only in Salesforce.
3. Workers and users in both Workday and Salesforce.

These are transformed to CSV format. The final report in CSV format is sent to the emails you configured in the  `mule.*.properties` file.
<!-- Default Business Logic XML (end) -->

<!-- Business Logic XML (start) -->

<!-- Business Logic XML (end) -->

## endpoints.xml
<!-- Default Endpoints XML (start) -->
This is the file where you find the inbound side of your integration app. This template has an HTTP connector as the way to trigger the use case.

### Trigger Flow

**HTTP Listener Connector** - Start Report Generation

- `${http.port}` is a property to be defined either in a property file or in CloudHub environment variables.
- The path configured by default is `generatereport` and you are free to change for the one you prefer.
- The host name for all endpoints in your CloudHub configuration should be defined as `localhost`. CloudHub routes requests from your application domain URL to the endpoint.
<!-- Default Endpoints XML (end) -->

<!-- Endpoints XML (start) -->

<!-- Endpoints XML (end) -->

## errorHandling.xml
<!-- Default Error Handling XML (start) -->
This file handles how your integration reacts depending on the different exceptions. This file provides error handling that is referenced by the main flow in the business logic.
<!-- Default Error Handling XML (end) -->

<!-- Error Handling XML (start) -->

<!-- Error Handling XML (end) -->

<!-- Extras (start) -->

<!-- Extras (end) -->
