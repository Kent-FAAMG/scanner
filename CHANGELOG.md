# Changelog

All notable changes to the Bureau QR Scanner SDK will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.0.0] - 2024-12-15

### Added
- 🎉 Initial release of Bureau QR Scanner SDK
- 📱 White-label QR scanning functionality for government IDs
- 🆔 Support for Aadhaar, PAN, and Voter ID documents
- 🎨 Customizable branding with `BrandingConfig`
  - Primary and secondary color customization
  - Optional custom fonts and logos
  - Organization name branding
- 📷 Real-time QR code scanning with CameraX and ML Kit
- 🔄 Complete user flow implementation:
  - Document type selection screen
  - Camera scanning with frame overlay
  - Loading screen with API submission
  - Success/failure status screens
- ⏱️ 130-second scan timeout with visual countdown
- 🔒 Proper camera permission handling
- 🌐 Bureau API integration for document verification
- 🏗️ Multi-module architecture:
  - `core:ui` - Shared UI components and theming
  - `core:network` - Retrofit client and API models
  - `sdk-qrscanner` - Main SDK library
  - `host-app` - Demo integration application
- 🧪 Comprehensive testing:
  - Unit tests with Turbine for flow testing
  - Instrumentation tests for UI
  - MockWebServer for API testing
- 📚 Complete documentation and README
- 🚀 CI/CD pipeline with GitHub Actions:
  - Static analysis (Detekt, Spotless, Ktlint)
  - Unit and instrumentation tests
  - Automated publishing to GitHub Packages
  - Release creation with artifacts
- 📦 Maven publishing with AAR distribution
- 🎯 Android API 24+ support (Android 7.0+)
- 📱 Modern Jetpack Compose UI with Material 3

### Technical Details
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Kotlin**: 1.9.23
- **Compose BOM**: 2024.04.00
- **CameraX**: 1.4.0-alpha05
- **ML Kit**: 17.2.0
- **Retrofit**: 2.9.0
- **Hilt**: 2.48
- **Architecture**: MVVM with StateFlow

### API Endpoints
- `POST /v2/services/aadhaar-qr` - Aadhaar ID verification
- `POST /v2/services/pan-qr` - PAN card verification  
- `POST /v2/services/voter-qr` - Voter ID verification

### Dependencies
- Jetpack Compose for modern UI
- CameraX for camera functionality
- ML Kit for QR code detection
- Retrofit + Moshi for networking
- Hilt for dependency injection
- Accompanist for permissions

[Unreleased]: https://github.com/bureau-id/qrscanner-sdk/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/bureau-id/qrscanner-sdk/releases/tag/v1.0.0 