const fs = require('node:fs');
const path = require('node:path');
const { execSync } = require('node:child_process');

const apps = fs.readdirSync(path.resolve(__dirname, 'apps'));
const libs = fs.readdirSync(path.resolve(__dirname, 'libs'));

const issue = execSync('git rev-parse --abbrev-ref HEAD')
  .toString()
  .trim()
  .match(/\d+/)?.[0];

const definedScopes = [
  'global',
  'release',
  ...apps.map((app) => `${app}`),
  ...libs.map((lib) => `${lib}`),
];

// .commitlintrc.js
/** @type {import('cz-git').UserConfig} */
module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
    'scope-enum': [2, 'always', [...definedScopes]],
  },
  prompt: {
    useEmoji: true,
    customIssuePrefixAlign: !issue ? 'top' : 'bottom',
    defaultIssues: !issue ? '' : `#${issue}`,
    issuePrefixes: [
      {
        name: 'Close issue',
        value: 'closes',
      },
      {
        name: 'Fix issue',
        value: 'fixes',
      },
      {
        name: 'Link issue',
        value: 'links',
      },
      {
        name: 'Reference issue',
        value: 'refs',
      },
    ],
    // enableMultipleScopes: true,
    // scopeEnumSeparator: '/',
  },
};
