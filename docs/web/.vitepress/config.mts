import { defineConfig } from 'vitepress';

// https://vitepress.dev/reference/site-config
export default defineConfig({
  base: '/angular-tomcat-gradle-monorepo/',
  title: 'Angular Gradle Monorepo',
  description:
    'A proof of concept monorepo using NX to orchestrate angular and java projects',
  themeConfig: {
    search: {
      provider: 'local',
    },
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' },
      { text: 'Docs', link: '/docs/intro' },
      { text: 'Reports', link: '/docs/reports/' },
      // { text: 'Examples', link: '/markdown-examples' },
      // { text: 'Examples', link: '/examples/markdown-examples' },
    ],

    sidebar: [
      {
        text: 'Intro',
        items: [
          { text: 'Getting Started', link: '/intro/getting-started' },
          { text: 'Features', link: '/intro/features' },
        ],
      },
      {
        text: 'Docs',
        items: [
          // example of nested items
          // {
          //   text: 'Nx',
          //   items: [{ text: 'Start', link: '/docs/nx' }],
          // },
          {
            text: 'Nx',
            link: '/docs/nx',
          },
          {
            text: 'Gradle',
            link: '/docs/gradle',
          },
          {
            text: 'Releases',
            link: '/docs/releases',
          },
          {
            text: 'Actions',
            link: '/docs/actions',
          },
          {
            text: 'Reports',
            link: '/docs/reports',
          },
          {
            text: 'Security',
            link: '/docs/security',
          },
          {
            text: 'Other tools',
            link: '/docs/other-tools',
          },
        ],
      },
      {
        text: 'Examples',
        items: [
          { text: 'Markdown Examples', link: '/examples/markdown-examples' },
          // { text: 'Runtime API Examples', link: '/api-examples' },
          { text: 'Runtime API Examples', link: '/examples/api-examples' },
        ],
      },
    ],

    footer: {
      message: 'Released under the MIT License. Created with vitepress.',
    },

    socialLinks: [
      {
        icon: 'github',
        link: 'https://github.com/gipo355/angular-tomcat-gradle-monorepo',
      },
    ],
  },
});
