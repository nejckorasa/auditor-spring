# auditor-spring

Spring boot application used to audit (trace) user requests. 

- Listener for JMS queue reads data and stores it to database/file - this option is configurable via spring profiles (**db-audit**, **file-audit**)
- Batch saving to permanent storage location


## REST API

Two API edpoints are available:

- **/audit/mq** - Receives single audit info that is pushed into JMS
- **/audit/direct** - Receives a batch of audit info elements and saves them directly to permanent location (this can either be file or database)

## Recover option 

In case of failed saving after certain number of attempts. Failed requests are logged locally and retried again on scheduled bases

## Database auditing
## File auditing

## Auto schema generation 

Auditor uses only one table (audit_info) that is automatically created if it does not yet exist

## COMMING soon

- **Mail notifications**
