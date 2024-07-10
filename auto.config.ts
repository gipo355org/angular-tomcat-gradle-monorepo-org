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
    labels: [
      {
        name: "blog-post",
        changelogTitle: "📚 Blog Post",
        releaseType: "none",
      },
    ],
  };
}
