
This is a draft for the next scalatra-sbt.g8 template.

Changes:
  - Uses scalatra-sbt which includes xsbt-web-plugin 1.0
  - sbt-web project layout by default (src/main/assets, src/main/public instead src/main/webapp)
  - Templates are in WEB-INF/templates, default in new Scalatra 2.3.x/2.4.x
  - xsbt-web-plugin for servlet container and WAR web archive
  - Integrates sbt-web and xsbt-web-plugin
  - DistPlugin for standalone distribution
  - scalate-generator for precompiling templates
  - Does not use the Stack trait approach
  - Includes a Jetty 9 launcher

Tasks:
  - `prepare` prepares the web archive in `target/web/stage`
  - `web-stage` runs the sbt-web asset pipeline, result is in `target/web/stage`
  - `dist` creates a standalone distribution
  - `package` creates a web archive
