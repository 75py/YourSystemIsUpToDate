# Shortcut to System Update

## Description

The **Shortcut to System Update** app does exactly what its name suggests: it provides a quick and easy way to access your system update settings. This saves you time and effort by eliminating the need to navigate through various menus to find the system update option.

### Original Name

This app was originally named **YourSystemIsUpToDate**. We decided to rename it to **Shortcut to System Update** to better reflect its primary function of providing quick access to system update settings.

## Features

- **Simple and Intuitive**: One-tap access to system update settings.
- **Google Play System Updates**: On Android 10 and later, long-press the app icon and choose **Play update** to open Google Play system update settings. Drag the shortcut to the home screen to keep it there.
- **Widgets**: Choose Android version or Google Play system update in the widget picker. Each widget opens its own update screen.
- **Humorous Twist**: Most of the time, you'll be greeted with the message, "Your system is up to date!" 😉

## Google Play system update

The app keeps its single launcher icon, which opens OS update settings as before.
Google Play system update is opt-in, so updating the app adds nothing to the app list
or the home screen.

On Android 10 and later, long-press the app icon and choose **Play update** to open the
Play update screen directly. On supported launchers, drag that shortcut to the home
screen to place it as its own icon.

Or add **Google Play system update** from the launcher’s widget picker to display the
installed update level. It can coexist with the existing Android version widget;
each widget always opens its respective update screen.
A Google Play system update takes effect when the device restarts, so the widget
refreshes after a restart and whenever the app is opened. Tap it to check for updates.

The date is read from `com.google.android.modulemetadata`’s `versionName`, following
[Google’s documented Mainline version lookup](https://developers.google.com/android/work/security-posture-signals#retrieve_mainline_version).
Devices that declare AOSP’s `com.android.modulemetadata` as the provider are read from
that package instead.
It identifies the installed update level, not the installation time or whether an
update is available. The version name is shown as it is; missing metadata is shown as
“Unavailable”.

The shortcut tries `MODULE_UPDATE_VERSIONS`, then `MODULE_UPDATE_SETTINGS`, limited
to the Google Play Store package. These are not guaranteed public SDK actions, so
support depends on the device and Google Play version. An unavailable/restricted
screen shows an error message without crashing. No root, device administrator,
network permission, or broad package-query permission is needed.

## Development and verification

Use JDK 17 and Android SDK 36:

```sh
./gradlew :app:testDebugUnitTest :app:assembleDebug :app:lintDebug
```

Before release, check on a Google Play-enabled device:

- Upgrade with an existing Android widget: it still opens OS update settings.
- Check that the app list still shows a single icon, and that it opens OS update.
- Long-press the icon and choose **Play update**: it opens Play update, with no chooser.
  Drag the shortcut to the home screen and check the pinned icon as well.
- Tap the icon and the pinned shortcut alternately, pressing Home in between: each
  must still open its own screen.
- Place both widget types; each opens its own destination.
- Compare the Play widget date with Settings, including after applying an update
  and restarting the device. Reopening this app should refresh the displayed value.
- Check the Play widget at 2×1 and resized down to 1×1, at large font sizes, in
  light/dark mode, and with TalkBack.
- On an unsupported device, verify the unavailable value and launch error message.

## Download

https://play.google.com/store/apps/details?id=com.nagopy.android.yoursystemisuptodate

## License

```
Copyright 2015 75py

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
