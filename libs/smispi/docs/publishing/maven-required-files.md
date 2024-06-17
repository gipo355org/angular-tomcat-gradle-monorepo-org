<https://central.sonatype.org/publish/publish-gradle/#deploying-to-ossrh-with-gradle-introduction>
<https://central.sonatype.org/publish/requirements/>
<https://stackoverflow.com/questions/61734609/how-do-i-include-a-gradle-auto-incremented-version-number-in-my-codes-repo>

to automatically increment version number in gradle we could:

- <https://github.com/KengoTODA/gradle-semantic-release-plugin>
- <https://github.com/semantic-release/exec>

# maven requires jira issue

# Maven Required Files

maven requires files from gradle tasks

```
./gradlew generatePomFileForGprPublication
./gradlew generateMetadataFileForGprPublication
```

located under build/publications/gpr

# Maven group id

maven requires group id to be associated with domain

for github: io.github.username

```
Provide groupID. If you have a GitHub-based repo to publish like “my-repo” and this repo is under the GitHub username “my-username” then the groupID will be “io.github.my-username”.
The artifactID of your library, like, “my-repo”.
```
