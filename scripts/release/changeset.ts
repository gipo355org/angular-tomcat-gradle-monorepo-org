// this script handles changesets modifications for handling stable releases
const fs = require('node:fs');

interface Config {
  $schema: string;
  changelog: [string, { repo: string }];
  commit: boolean;
  fixed: string[][];
  linked: string[];
  access: string;
  baseBranch: string;
  updateInternalDependencies: string;
  ignore: string[];
  privatePackages: {
    tag: boolean;
  };
}

// change target branch for pr

const changesetConfig: Config = JSON.parse(
  fs.readFileSync('./.changeset/config.json', 'utf8'),
);

changesetConfig.baseBranch = 'main';

fs.writeFileSync(
  './.changeset/config.json',
  JSON.stringify(changesetConfig, null, 2),
);
