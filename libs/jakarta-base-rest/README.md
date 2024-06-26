problem with war name and versioning

<https://tomcat.apache.org/tomcat-9.0-doc/config/context.html#Parallel_deployment>

when version is given, war is built with the version in it

which makes it so tomcat on autodeploy requires the version in the path

we want to publish the versioned war but deploy the unversioned war
