# Auto Semantic Release

We are using automated semantic release in the CI pipeline

<https://github.com/semantic-release/semantic-release>

## How it works

Based on the commit messages, semantic release will determine the next version number and create a new release in GitHub.

## The flow

- There will be a new branch created for each release, named `release/vX.Y.Z`, where `X.Y.Z` is the version number.
- It will trigger a github release
- It will trigger a build and a push on the maven repository

## The commit format

It's based on commits following the standardized format:

```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

Where:

- `type` is one of the following: `feat`, `fix`, `docs`, `style`, `refactor`, `perf`, `test`, `build`, `ci`, `chore`, `revert`
- `scope` is optional, can be set up in commitlint configuration
- `subject` is a short description of the change
- `body` is a more detailed description of the change
- `footer` is optional and can be used to reference issues or PRs
- breaking changes can be indicated in the `body` or `footer` with the `BREAKING CHANGE` keyword and they will be highlighted in the release notes
- breaking changes are required for bumping the major version
