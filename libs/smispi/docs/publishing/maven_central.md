# Requirements

<https://maven.apache.org/repository/guide-central-repository-upload.html>

> Guide to uploading artifacts to the Central Repository
> In order for users of Maven to utilize artifacts produced by your project, you must deploy them to a remote repository. Many open source projects want to allow users of their projects who build with Maven to have transparent access to their project's artifacts. In order to allow for this, a project should deploy their artifacts to the Central Repository.
>
> Requirements
>
> - releases: Only releases can be uploaded to the Central Repository, that means files that won't change and that only depend on other files already released and available in the repository,
> - javadoc and sources for IDE lookup, PGP signature,
> - minimum POM information: There are some requirements for the minimal information in the POMs that are in the Central Repository, see here,
> - coordinates: Picking the appropriate coordinates for your project is important. See the guidelines here, particularly on groupId and domain ownership.
>   The updated list of requirements can be found here.

<https://h4pehl.medium.com/publish-your-gradle-artifacts-to-maven-central-f74a0af085b1>

> One Time Setup
> If you publish to Maven Central for the first time, you have to do some initial setup. This mainly drills down to three steps:
>
> - Create an account:
>   Sonatype uses JIRA to track requests for publishing artifacts to Maven Central. So you need an account for JIRA. This account is also reused later when you release you artifacts with OSSRH.
> - Create a ticket:
>   This is the kickoff to get something published. You need to provide details about the planned publication. This includes the Maven coordinates (GAV) and URLs such as project and SCM URLs.
> - Prove ownership:
>   You must prove ownership for the domain that matches your group ID. In case of a GitHub group ID (io.github.username), this will be verified if the project URL matches the requested group ID. In any other cases you have to use other methods such as adding a TXT record to the DNS or setting up a redirect. See the 3rd bullet point in the OSSRH guide.
>
> Mandatory Setup
> Every artifact in Maven Central must meet certain quality standards. This allows users to find all the relevant details about the artifacts from the metadata provided in the Central Repository.
>
> - Source & Javadoc
>   You have to supply JAR files that contain sources and Javadoc of your library.
> - Signed Artifacts
> - All artifacts published to Maven Central need to be signed with GPG/PGP.
> - Metadata
> - As part of your publication, you are required to submit a pom file. This file defines the Project Object Model used by Apache Maven. It must include specific metadata such as GAV coordinates, the license and information about the developer(s).

# other sources

<https://www.jetbrains.com/help/space/publish-artifacts-to-maven-central.html>

<https://medium.com/@efthymiou.dimitrios1/how-to-publish-your-library-to-maven-central-3923139967e1>

<https://central.sonatype.org/publish/publish-maven/>

<https://central.sonatype.org/publish/publish-guide/>

<https://medium.com/@nowshadapu/how-to-create-a-java-library-and-publish-it-to-maven-with-gradle-7-e952837a7fc9>

# there is also bintray

<https://medium.com/@rosolko/publish-java-library-to-jfrog-bintray-and-sonatype-with-gradle-1a3ebd5b8be8>

<https://hemanthsridhar.medium.com/6-steps-to-upload-your-java-and-android-libraries-to-bintray-jcenter-using-gradle-adc033f59ba1>
