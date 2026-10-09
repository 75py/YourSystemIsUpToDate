# Shortcut to System Update

## Description

The **Shortcut to System Update** app does exactly what its name suggests: it provides a quick and easy way to access your system update settings. This saves you time and effort by eliminating the need to navigate through various menus to find the system update option.

### Original Name

This app was originally named **YourSystemIsUpToDate**. We decided to rename it to **Shortcut to System Update** to better reflect its primary function of providing quick access to system update settings.

## Features

- **Simple and Intuitive**: One-tap access to system update settings.
- **Google Play System Updates**: Open Google Play system update settings using a dedicated shortcut.
- **Widgets**: Choose Android version or Google Play system update in the widget picker. Each widget opens its own update screen.
- **Two Launcher Icons**: The original icon opens OS update settings; a second Google Play system update icon opens Play update settings directly on Android 10 and later. Long-press the original icon and select **Update information** to view the installed Play update level.
- **Humorous Twist**: Most of the time, you'll be greeted with the message, "Your system is up to date!" 😉

## Google Play system update

On Android 10 and later, the app provides two launcher icons. The existing system
update icon and its home-screen placements keep their original behavior. The new
**Google Play system update** icon opens the Play update screen directly, without a
chooser or an initial setup step. Both icons belong to this single installed app.

The original icon’s long-press menu also provides **Play update**, **OS update**, and
**Update information**. On supported launchers, drag the Play shortcut to the home
screen, or use **Update information → Add Play update shortcut to Home screen**.

Add **Google Play system update** from the launcher’s widget picker to display the
installed update level. It can coexist with the existing Android version widget;
each widget always opens its respective update screen.
A Google Play system update takes effect when the device restarts, so the widget
refreshes after a restart and whenever the app or its settings are opened. Tap it to
check for updates.

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
- Check both launcher icons: the original opens OS update and the new icon opens
  Play update, with no chooser. Confirm both belong to the same installed app.
- Tap the icons alternately, pressing Home in between and after opening **Update
  information**: each icon must still open its own screen.
- Pin the Play shortcut and place both widget types; each opens its own destination.
- Compare the Play widget date with Settings, including after applying an update
  and restarting the device. Reopening this app should refresh the displayed value.
- Check the 2×1 widget at large font sizes, in light/dark mode, and with TalkBack.
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
