import { defineConfig } from 'vitepress';

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: 'Angular Gradle Monorepo',
  description:
    'A proof of concept monorepo using NX to orchestrate angular and java projects',
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' },
      { text: 'Docs', link: '/docs/start' },
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
        items: [{ text: 'Start', link: '/docs/start' }],
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

    socialLinks: [
      {
        icon: 'github',
        link: 'https://github.com/gipo355/angular-tomcat-gradle-monorepo',
      },
    ],
  },
});
