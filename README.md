# auditor-spring

Spring boot application used to audit (trace) user requests. 

Listener for JMS queue reads data and stores it to database/file - this option is configurable (or it will be, if not yet :))

## REST API

It provides simple API ednpoint **/audit** to add audit request into JMS queue.

## Recover option 

In case of failed saving after certain number of attempts. Failed requests are logged locally and retried again on scheduled bases

## Database auditing

## Auto schema generation 

Auditor uses only one table (audit_info) that is automatically created if it does not yet exist

## COMMING soon

- **Mail notifications**
