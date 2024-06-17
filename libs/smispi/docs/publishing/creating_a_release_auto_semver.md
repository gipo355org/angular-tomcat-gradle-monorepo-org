# automatic versioning

## links

<https://medium.com/@luca.motta1993/semantic-version-and-how-to-integrate-semantic-release-in-a-gitlab-ci-cd-pipeline-for-a-java-235b0f91dedb>
<https://github.com/dipien/semantic-version-gradle-plugin>
<https://semver.org/>
<https://github.com/semantic-release/semantic-release/blob/master/docs/usage/configuration.md#configuration>
<https://github.com/semantic-release/semantic-release>
<https://github.com/semantic-release/github>
<https://semantic-release.gitbook.io/semantic-release/support/faq#making-commits-during-the-release-process-adds-significant-complexity>

## notes

**major concept**
on a 1.0.0 version:

- MAJOR version when you make incompatible API changes
- MINOR version when you add functionality in a backwards compatible manner
- PATCH version when you make backwards compatible bug fixes
- can add alpha, beta, prerelease
- stay on 0.x.x until first stable build

---

- versions are specified:
  - in the build.gradle (v0.0.1-SNAPSHOT) what is this snapshot
  - in the github tag (v0.0.1-alpha)
  - in the github release (v0.0.1-alpha)
  - possibly in the github branch created for this release (eg releases/v0.0.1-alpha)
- steps required: create a release with its tag
- publish

- what does this command do?
- does it read the v from the gradle file?
- does it bump automatically?

- how to create a semver based on current project vers and automatically bump it?
- with changelogs?

```groovy
./gradlew -Pversion=${version} publish
```

<https://stackoverflow.com/questions/37073049/gradle-dynamic-calculate-version-for-publishing>

the Pversion overrides the property using the cli
if we get the version from the git tag, we can omit the version in the gradle file

<https://stackoverflow.com/questions/57323260/how-to-push-to-github-package-registry-with-gradle>

```yaml
name: Publish package to GitHub Packages
on:
  release:
    types: [created]
jobs:
  publish:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v1
        with:
          java-version: 1.8
      - name: Publish package
        run: gradle -Pversion=${{ github.event.release.tag_name }} build publish
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```
