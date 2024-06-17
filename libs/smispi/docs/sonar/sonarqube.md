sonarqube is a server analyzing the static code

### server

it requires a server to run the analysis
it can be installed with docker locally

```bash
docker pull sonarqube:lts-community
```

### plugin

and a plugin to be installed in build.gradle

<https://plugins.gradle.org/plugin/org.sonarqube>
<https://github.com/SonarSource/sonar-scanner-gradle>
<https://docs.sonarsource.com/sonarqube/latest/analyzing-source-code/scanners/sonarscanner-for-gradle/>

### action

<https://github.com/SonarSource/sonarqube-scan-action>

### possibilities

- host a server
- use free sonarqube server
- use sonarcloud free for open source projects
- create a github app to host the sonarqube server?

_note_

- can be hosted locally with docker for devs like an lsp

## useful links

<https://www.linkedin.com/pulse/integrating-sonarqube-github-ajay-ghosh-ruyrc/>
<https://medium.com/swlh/building-the-first-github-app-3ea67a76c19a>
<https://www.baeldung.com/sonar-qube>
<https://medium.com/@s.mehrotrasahil/integrate-sonarqube-in-github-actions-d89eafc7fd69>
<https://www.youtube.com/watch?v=31igoWxauEQ>
