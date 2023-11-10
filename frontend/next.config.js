const path = require("path");
/** @type {import('next').NextConfig} */
const nextConfig = {
  sassOptions: {
    includePaths: [path.join(__dirname, "*")],
    prependData: `@import "src/styles/mixins.scss";`,
  },
};

module.exports = nextConfig;
