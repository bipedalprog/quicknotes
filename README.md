# quicknotes

A simple Spring Boot application that I am using to compare the easy of deployment to GCP and AWS.

I wanted an application that would represent an actual application 
that would be deployed as a monolith with a multi-page front end and
authentication and authorization.

Local development uses H2 for a data store. AWS and GCP deployments
can use the native relational store.

__NOTE__ You must use JDK 8 to compile this project. AWS ElasticBeanstalk 
did not like the JDK 11 classfile format.