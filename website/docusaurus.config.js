// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Snake Workshop',
  tagline: 'Some fun with Scala',
  url: 'https://scalacenter.github.io/',
  baseUrl: '/snake-workshop/',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  favicon: 'img/favicon.ico',
  organizationName: 'scalacenter',
  projectName: 'snake-workshop',

  presets: [
    [
      '@docusaurus/preset-classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          editUrl: 'https://github.com/scalacenter/snake-workshop/edit/website/website',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      navbar: {
        title: 'Snake Workshop',
        logo: {
          alt: 'Snake',
          src: 'img/logo.svg',
        },
        items: [
          {
            type: 'doc',
            docId: 'installation/intro',
            position: 'left',
            label: 'Install Party',
          },
          {
            href: 'https://github.com/scalacenter/snake-workshop',
            label: 'GitHub',
            position: 'right',
          },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'Docs',
            items: [
              {
                label: 'Install Party',
                to: '/docs/installation/intro',
              },
            ],
          },
          {
            title: 'Community',
            items: [
              {
                label: 'Stack Overflow',
                href: 'https://stackoverflow.com/questions/tagged/scala',
              },
              {
                label: 'Gitter',
                href: 'https://gitter.im/scala/center',
              },
              {
                label: 'Twitter',
                href: 'https://twitter.com/scala_lang',
              },
            ],
          },
          {
            title: 'More',
            items: [
              {
                label: 'Website',
                to: 'https://scala.epfl.ch/',
              },
              {
                label: 'GitHub',
                href: 'https://github.com/scalacenter',
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} Scala Center, Built with Docusaurus.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
        additionalLanguages : ['java', 'scala']
      },
    }),
};

module.exports = config;
