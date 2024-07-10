// import { getPackages, type Package } from '@manypkg/get-packages';
// import { GitHub, getOctokitOptions } from '@actions/github/lib/utils';
import { Octokit } from 'octokit';
// import { throttling } from '@octokit/plugin-throttling';
// import * as semver from 'semver';
import path from 'node:path';

// this code is adapted from `@changesets/action` and `nx release` to be able to create a release
// even without using `changesets publish` command
//
// It will create a release on GitHub with the changelog entry every package
// tagged with a new version and will allow adding assets to the release.
//
// it must be used run in action after the build step to have the packages
// built.
//
// e.g. .war files for java apps, .jar for java libs,  angular dist .zip files
//
// requires GITHUB_TOKEN to be set as an environment variable to be able to create the release

// GitHub Issues/PRs messages have a max size limit on the
// message body payload.
// `body is too long (maximum is 65536 characters)`.
// To avoid that, we ensure to cap the message to 60k chars.
const MAX_CHARACTERS_PER_MESSAGE = 60000;

// const setupOctokit = (githubToken: string) => {
//   return new (GitHub.plugin(throttling))(
//     getOctokitOptions(githubToken, {
//       throttle: {
//         onRateLimit: (retryAfter, options: any, octokit, retryCount) => {
//           core.warning(
//             `Request quota exhausted for request ${options.method} ${options.url}`,
//           );
//
//           if (retryCount <= 2) {
//             core.info(`Retrying after ${retryAfter} seconds!`);
//             return true;
//           }
//         },
//         onSecondaryRateLimit: (
//           retryAfter,
//           options: any,
//           octokit,
//           retryCount,
//         ) => {
//           core.warning(
//             `SecondaryRateLimit detected for request ${options.method} ${options.url}`,
//           );
//
//           if (retryCount <= 2) {
//             core.info(`Retrying after ${retryAfter} seconds!`);
//             return true;
//           }
//         },
//       },
//     }),
//   );
// };
const setupOctokit = (githubToken: string) => {
  return new Octokit({
    auth: githubToken,
    // throttle: {
    //   onRateLimit: (retryAfter, options: any, octokit, retryCount) => {
    //     console.warn(
    //       `Request quota exhausted for request ${options.method} ${options.url}`,
    //     );
    //
    //     if (retryCount <= 2) {
    //       console.info(`Retrying after ${retryAfter} seconds!`);
    //       return true;
    //     }
    //   },
    //   onAbuseLimit: (retryAfter, options: any, octokit) => {
    //     console.warn(
    //       `Abuse detected for request ${options.method} ${options.url}`,
    //     );
    //   },
    // },
  });
};

const createRelease = async (
  octokit: ReturnType<typeof setupOctokit>,
  { pkg, tagName }: { pkg: Package; tagName: string },
) => {
  try {
    let changelogFileName = path.join(pkg.dir, 'CHANGELOG.md');

    let changelog = await fs.readFile(changelogFileName, 'utf8');

    let changelogEntry = getChangelogEntry(changelog, pkg.packageJson.version);
    if (!changelogEntry) {
      // we can find a changelog but not the entry for this version
      // if this is true, something has probably gone wrong
      throw new Error(
        `Could not find changelog entry for ${pkg.packageJson.name}@${pkg.packageJson.version}`,
      );
    }

    await octokit.rest.repos.createRelease({
      name: tagName,
      tag_name: tagName,
      body: changelogEntry.content,
      prerelease: pkg.packageJson.version.includes('-'),
      ...github.context.repo,
    });
  } catch (err) {
    // if we can't find a changelog, the user has probably disabled changelogs
    if (
      err &&
      typeof err === 'object' &&
      'code' in err &&
      err.code !== 'ENOENT'
    ) {
      throw err;
    }
  }
};
