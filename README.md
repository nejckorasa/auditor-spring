# auditor-spring

- Spring boot application used to audit (trace) user requests. 
- It provides simple API ednpoint **/audit** to add audit request into JMS queue.
- Listener for JMS queue reads data and stores it to database/file - this option is configurable (or it will be, if not yet :))

- Recover option is provided in case saving failed after certain number of attempts. Failed requests are logged locally and retried again on scheduled bases

- **Mail notifications** are comming soon...
