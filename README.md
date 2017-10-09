# auditor-spring

Spring boot application used to audit (trace) user requests. 

- Listener for JMS queue reads data and stores it to database/file - this option is configurable via spring profiles (`db-audit`, `file-audit`)
- Direct saving to permanent storage location


## REST API

Two API edpoints are available:

- **/audit/mq** - Receives single audit info that is pushed into JMS
- **/audit/direct** - Receives multiple audit info elements and saves them directly to permanent location (this can either be file or database)

## Recover option 

In case of failed saving after certain number of attempts. Failed requests are logged locally and retried again on scheduled bases

## Database auditing
## File auditing

## Auto schema generation 

Auditor uses only one table (audit_info) that is automatically created if it does not yet exist



# Setup

- Download
- Create `application.properties` file (template is prepeared as `application.properties-TEMPLATE`)
- Build project via Maven (`mvn install`)
- Run created jar via (for instance) `java -jar auditor-0.0.1-SNAPSHOT-2017-10-09-10-48.jar`


