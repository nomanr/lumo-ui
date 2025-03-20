# Contributing Guidelines

Thank you for considering contributing to lumo-ui! We are happy to have you here.

## About this repository

This repository holds the UI components, the Gradle plugin, Android and Compose Multiplatform samples.

- `lumo-ui` holds the plugin and components
- `sample-android` android catalogue app
- `sample-multiplatform` Compose Multiplatform Catalogue app. (Supports, Android, iOS, Desktop and Web)

## Structure

```
├── gradle/
│   ├── libs.versions.toml      # Dependency version management
├── lumo-ui/
│   ├── components-lab/         # UI components library
│   ├── plugin/                 # Gradle plugin implementation
│   └── scripts/                # Build & release scripts
│       ├── generate_templates.sh  # Regenerates UI templates
├── sample-android/             # Android sample app
│   ├── catalogue/              # Sample components showcase
│   ├── ui-components/          # Sample UI component implementations
├── sample-multiplatform/       # Multiplatform sample app
│   ├── android/                # Android runner module
│   ├── common/                 # Multiplatform catalogue module (contains all platform source sets)
│   ├── desktop/                # Desktop runner module
│   ├── ios/                    # iOS runner module
│   ├── web/                    # Web runner module
```

## Development

To contribute to this project, follow these steps:

#### Fork the repository

Click on the 'Fork' button at the top right corner of the repository page to create a copy of the repository.

#### Clone your fork:

```
git clone https://github.com/your-username/lumo-ui.git
```

#### Create a new branch:

```
git checkout -b feature/your-feature-name
```

#### Make your changes:

Make the necessary changes to the codebase. Ensure that your code follows the project's coding standards and guidelines.

**_Note:_** You don't need to generate templates for your changes, they are re-generated when a PR is merged.

#### Create a pull request:

Create a pull request, ensure checks pass, and allow owner edits.

## Request for new components

Create an issue for new components request, and we'd be happy to help

**Happy coding!** 🚀
