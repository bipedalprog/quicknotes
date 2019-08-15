# quicknotes

A simple Spring Boot application that I am using to compare the easy of deployment to GCP and AWS.

I wanted an application that would represent an actual application 
that would be deployed as a monolith with a multi-page front end and
authentication and authorization.

Local development uses H2 for a data store. AWS and GCP deployments
can use the native relational store.

## Building the Container

```bash
./gradlew jib --image <userid>.dkr.ecr.region.amazonaws.com/personal:quicknotes
```

# AWS Deployments

## Elastic Beanstalk

You must use JDK 8 to compile this project. AWS ElasticBeanstalk 
did not like the JDK 11 classfile format.

Add an environment variable for SERVER_PORT=5000. That is the default port 
for EB deployments so we need to let Spring know.

There is a buildspec.yml that you can use with CodeBuild to produce a zip
that EB can deploy. I have also created a CodePipeline with this that
monitors my GitHub repository and automatically deploys the latest build
to my public EB instance.

## ECS

The application runs under Elastic Container Service without additional 
configuration. I used Farscape to avoid creating an EC2 cluster by hand.
