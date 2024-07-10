import { AutoRc } from "auto";

import { INpmConfig } from "@auto-it/npm";
import { IAllContributorsPluginOptions } from "@auto-it/all-contributors";

const npmOptions: INpmConfig = {
  exact: true,
  canaryScope: "@auto-canary",
};

const allContributorsOptions: IAllContributorsPluginOptions = {
  types: {
    plugin: "**/plugin/**/*",
    code: ["**/src/**/*", "**/package.json", "**/tsconfig.json"],
  },
};

/** Auto configuration */
export default function rc(): AutoRc {
  return {
    plugins: [
      "released",
      ["npm", npmOptions],
      ["all-contributors", allContributorsOptions],
    ],
    onlyPublishWithReleaseLabel: true,
    "baseBranch": "dev",
    // "release": {
    //   // only publish prerelease
    //   "prerelease": true
    // },
    // Create and manage old major releases.
    "versionBranches": true,
    // labels: [
    //   // defaults label
    //   {
    //     "name": "major",
    //     "changelogTitle": "💥 Breaking Change",
    //     "description": "Increment the major version when merged",
    //     releaseType: "major",
    //     "color": "#C5000B"
    //   },
    //   {
    //     "name": "minor",
    //     "changelogTitle": "🚀 Enhancement",
    //     "description": "Increment the minor version when merged",
    //     "releaseType": "minor",
    //     "color": "#F1A60E"
    //   },
    //   {
    //     "name": "patch",
    //     "changelogTitle": "🐛 Bug Fix",
    //     "description": "Increment the patch version when merged",
    //     "releaseType": "patch",
    //     "color": "#870048"
    //   },
    //   {
    //     "name": "skip-release",
    //     "description": "Preserve the current version when merged",
    //     "releaseType": "skip",
    //     "color": "#bf5416"
    //   },
    //   {
    //     "name": "release",
    //     "description": "Create a release when this pr is merged",
    //     "releaseType": "release",
    //     "color": "#007f70"
    //   },
    //   {
    //     "name": "internal",
    //     "changelogTitle": "🏠 Internal",
    //     "description": "Changes only affect the internal API",
    //     "releaseType": "none",
    //     "color": "#696969"
    //   },
    //   {
    //     "name": "documentation",
    //     "changelogTitle": "📝 Documentation",
    //     "description": "Changes only affect the documentation",
    //     "releaseType": "none",
    //     "color": "#cfd3d7"
    //   },
    //   {
    //     "name": "tests",
    //     "changelogTitle": "🧪 Tests",
    //     "description": "Add or improve existing tests",
    //     "releaseType": "none",
    //     "color": "#ffd3cc"
    //   },
    //   {
    //     "name": "dependencies",
    //     "changelogTitle": "🔩 Dependency Updates",
    //     "description": "Update one or more dependencies version",
    //     "releaseType": "none",
    //     "color": "#8732bc"
    //   },
    //   {
    //     "name": "performance",
    //     "changelogTitle": "🏎 Performance",
    //     "description": "Improve performance of an existing feature",
    //     "releaseType": "patch",
    //     "color": "#f4b2d8"
    //   },
    //   // {
    //   //   name: "blog-post",
    //   //   changelogTitle: "📚 Blog Post",
    //   //   releaseType: "none",
    //   // },
    // ]
  };
}
