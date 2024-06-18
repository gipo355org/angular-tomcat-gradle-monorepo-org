const { FlatCompat } = require('@eslint/eslintrc');
const nxEslintPlugin = require('@nx/eslint-plugin');
const js = require('@eslint/js');

const eslintPluginImport = require('eslint-plugin-import');

const globals = require('globals');
const nodePlugin = require('eslint-plugin-n');
const tsSortKeys = require('eslint-plugin-typescript-sort-keys');
const eslintPluginSimpleImportSort = require('eslint-plugin-simple-import-sort');
const tseslint = require('typescript-eslint');
const eslintPluginUnicorn = require('eslint-plugin-unicorn');
const jsoncParser = require('jsonc-eslint-parser');
const tsParser = require('@typescript-eslint/parser');
const eslint = require('@eslint/js');

const compat = new FlatCompat({
  baseDirectory: __dirname,
  recommendedConfig: js.configs.recommended,
});

module.exports = tseslint.config(
  {
    // must be on its own for glob pattern to work
    ignores: [
      '**/*',
    ],
  },

  {
    // root level config
    languageOptions: {
      parser: tsParser,
      globals: {
        // ...globals.browser,
        ...globals.node,
        // ...globals.worker,
        ...globals.es2021,
        // ...eslintrc.Legacy.environments.get('es2024'),
        // ...globals.jest,
      },
    },

    plugins: {
      // provide the rules at the root level, activate the recommended config in express only
      // or they won't be available globally
      // this won't activate them
      // you either need to extend or enable rules manually
      '@nx': nxEslintPlugin,

      'simple-import-sort': eslintPluginSimpleImportSort,

      import: eslintPluginImport,

      'typescript-sort-keys': tsSortKeys,

      unicorn: eslintPluginUnicorn,

      n: nodePlugin,
    },
  },

    eslint.configs.recommended,



  { plugins: { '@nx': nxEslintPlugin } },

  {
    files: ['**/*.ts', '**/*.tsx', '**/*.js', '**/*.jsx'],
    rules: {
      '@nx/enforce-module-boundaries': [
        'error',
        {
          enforceBuildableLibDependency: true,
          allow: [],
          depConstraints: [
            {
              sourceTag: '*',
              onlyDependOnLibsWithTags: ['*'],
            },
          ],
        },
      ],
    },
  },

  ...compat.config({ extends: ['plugin:@nx/typescript'] }).map((config) => ({
    ...config,
    files: ['**/*.ts', '**/*.tsx'],
    rules: {
      ...config.rules,
    },
  })),

  ...compat.config({ extends: ['plugin:@nx/javascript'] }).map((config) => ({
    ...config,
    files: ['**/*.js', '**/*.jsx'],
    rules: {
      ...config.rules,
    },
  })),

  ...compat.config({ env: { jest: true } }).map((config) => ({
    ...config,
    files: ['**/*.spec.ts', '**/*.spec.tsx', '**/*.spec.js', '**/*.spec.jsx'],
    rules: {
      ...config.rules,
    },
  })),

);
