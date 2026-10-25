# Google Play release assets

```text
README.md            This file: release steps and capture setup
listing.md           Current store listing text, screenshot order and alt text
release-notes/
  <version>.txt      Release notes for each release, language-tagged
screenshots/
  ja-JP/
    01-android-details.png
    02-play-shortcut.png
    03-system-update.png
    04-widget-picker.png
  en-US/
    ...
feature-graphic/
  ja-JP.png
  en-US.png
```

`listing.md`, `screenshots/` and `feature-graphic/` hold only what the store
shows now. Edit them in place when the listing changes; earlier versions are in
the git history. Only release notes are kept per release. Use Play Console locale
codes for language directories and the numeric prefix as the upload order.

## Releasing

1. Add `release-notes/<versionName>.txt`. Copy the whole file into the release
   notes field. Keep each opening and closing language tag on its own line. Each
   language is limited to 500 Unicode characters.
2. If `listing.md` or the images changed since the last release, update the
   store listing. Store listing changes are shared across tracks, so publish
   them with the production release.
3. Verify Google Play update navigation on a supported, Google Play-enabled
   device using the [release checks](../../README.md#development-and-verification).
   Emulators don't have the Google Play system update screen.

### 1.4.0 notes

- 1.4.0 raises `minSdk` to 31. Devices on Android 10–11 don't receive it and keep
  their installed 1.3.0. Play Console warns that the release drops support for
  some devices; this is expected.
- To keep new installs possible on Android 10–11, retain the 1.3.0 bundle
  (version code 6) in the 1.4.0 production release. Without it, Play shows the
  app as incompatible on those devices.
- `listing.md` drops the “Android 10 and later” qualifiers. The old wording is
  still true on Android 12, so updating the store listing is optional.

## Store listing notes

- Keep the existing localized app names. The English launcher uses
  “System Update Shortcut”; the store title remains “Shortcut to System Update”.
  The capture device’s Pixel Launcher picker shows the application/store name;
  other launchers may show the activity name. Either header is acceptable.

## Capture setup

The screenshots were captured for 1.3.0 and still match the app.

- App: debug build of version 1.3.0 (version code 6), with the widget resources
  prepared in [PR #6](https://github.com/75py/YourSystemIsUpToDate/pull/6).
  Images 1–3 were captured on 2026-10-12. The widget picker, image 4, was
  recaptured on 2026-10-17 with the final picker previews and descriptions.
- Device: Pixel 2 profile, Android 17 / API 37 Google Play emulator, Pixel Launcher.
  SDK image: `system-images;android-37.1;google_apis_playstore_ps16k;arm64-v8a`
  revision 9, build `CP31.260623.012`.
- Actual device values: security patch `2026-07-05`; Google Play module metadata
  version `2026-05`. The picker's sample previews show fixed examples, Android 17
  and Play level `2026-05`, which match this device.
- In Home settings → Suggestions, turn off suggestions on the Home screen, so the
  dock shows no suggested app.
- All images: 1080×1920, default 420 dpi and font scale 1.0. Image 1 uses a
  4×2 Android widget so API level is visible, beside a 2×1 Google Play widget.
- Switch the system language between Japanese and English (United States).
- Clear notifications, then use System UI demo mode to show a consistent 10:00 clock
  with Wi-Fi and battery icons only.
  The update screen's last-check time is the device's actual time.
- Capture with `adb exec-out screencap -p`. Export as 24-bit RGB PNG without
  changing the captured screen content. Image 3 shows the system update screen
  reached by tapping the app icon. The Japanese app title is abbreviated by the
  launcher's fixed-width picker header; widget names and descriptions fit at
  the default settings.

## References

- [Current Japanese listing](https://play.google.com/store/apps/details?id=com.nagopy.android.yoursystemisuptodate&hl=ja)
- [Current English listing](https://play.google.com/store/apps/details?id=com.nagopy.android.yoursystemisuptodate&hl=en)
- [Store listing fields and localization](https://support.google.com/googleplay/android-developer/answer/9859152?hl=en)
- [Release notes format and limits](https://support.google.com/googleplay/android-developer/answer/9859348?hl=en)
- [Screenshot requirements](https://support.google.com/googleplay/android-developer/answer/9866151?hl=en)
