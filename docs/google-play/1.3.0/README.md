# Google Play listing — 1.3.0

Copy each title and description block into its matching main store listing field.
Update both Japanese and English (United States). For release notes, use the
combined language-tagged block below in the release editor; retain its tags.

## 日本語（ja-JP）

### アプリ名

```text
システムアップデートへのショートカット
```

### 簡単な説明

```text
Androidバージョンを表示し、OSとGoogle Playのシステム更新画面へ
```

### 詳しい説明

```text
システムアップデート設定へのショートカットと、AndroidバージョンやGoogle Playシステム更新の更新レベルを表示するホーム画面ウィジェットを組み合わせたシンプルなツールです。

システムアップデートへのショートカット
・アプリアイコンをタップすると、端末のシステムアップデート設定を開きます。
・Android 10以降では、アプリアイコンを長押しして「Play更新」を選ぶと、Google Playシステム更新画面を開けます。
・対応するホームアプリでは、「Play更新」を長押ししてホーム画面にドラッグすると、専用のショートカットとして配置できます。

Androidバージョンウィジェット
・端末のAndroidバージョンを表示します。
・スペースに応じて、セキュリティパッチレベル、ビルドID、APIレベルも表示します。
・1×1のコンパクト表示から、横長・縦長のサイズまで表示内容を調整します。
・タップすると、端末のシステムアップデート設定を開きます。

Google Play更新ウィジェット（Android 10以降）
・導入済みのGoogle Playシステム更新の更新レベルを表示します。
・タップすると、Google Playシステム更新画面を開きます。
・Androidバージョンウィジェットと一緒に配置できます。
・更新は端末の再起動後に反映されます。ウィジェットは再起動後やアプリを開いたときに表示を更新します。

このアプリ自体がアップデートの有無を判定したり、更新を実行したりするものではありません。端末が提供する更新画面を開きます。Google Playウィジェットの表示は導入済みの更新のレベルを示し、インストールした日時や更新の有無を示すものではありません。

更新画面の名称や利用可否は、端末メーカー、Androidバージョン、Google Playのバージョンによって異なります。更新情報を取得できない場合は「取得できません」と表示します。

ソースコード
https://github.com/75py/YourSystemIsUpToDate
```

## English (United States, en-US)

### App name

```text
Shortcut to System Update
```

### Short description

```text
View your Android version and open OS or Google Play system update settings.
```

### Full description

```text
Quick access to system update settings, with home screen widgets for your Android version and installed Google Play system update level.

Update shortcuts
• Tap the app icon to open your device's system update settings.
• On Android 10 and later, long-press the app icon and choose “Play update” to open Google Play system update settings.
• On supported launchers, touch and hold “Play update” and drag it to your home screen to keep it as a separate shortcut.

Android version widget
• Shows your device's Android version.
• Adds the security patch level, build ID, and API level when space allows.
• Adapts from a compact 1×1 layout to wider and taller layouts.
• Opens your device's system update settings when tapped.

Google Play update widget (Android 10 and later)
• Shows the installed Google Play system update level.
• Opens Google Play system update settings when tapped.
• Can be placed alongside the Android version widget.
• Google Play system updates take effect after a restart. The widget refreshes after a restart and whenever you open this app.

This app does not determine whether an update is available or install updates itself. It opens the update screens provided by your device. The value in the Google Play widget identifies the installed update level, not when it was installed or whether an update is available.

Screen names and availability may vary by device manufacturer, Android version, and Google Play version. If the installed update level cannot be read, the widget shows “Unavailable”.

Source code
https://github.com/75py/YourSystemIsUpToDate
```

## Release notes — paste into the release editor

Copy the entire block, including the language tags, into the release notes field.
Keep each opening and closing tag on its own line. Each language is limited to
500 Unicode characters. If editing existing notes, replace the matching language
sections instead of adding duplicate tags.

```text
<ja-JP>
・Android 10以降で、アプリアイコンの長押しから「Play更新」を開けるようになりました。対応するホームアプリでは、ホーム画面にも配置できます。
・導入済みのGoogle Playシステム更新の更新レベルを表示するウィジェットを追加しました。タップすると更新画面を開けます。
・既存のAndroidバージョンウィジェットと並べて利用できます。
</ja-JP>
<en-US>
• On Android 10 and later, long-press the app icon and choose “Play update” to open Google Play system update settings. On supported launchers, you can also drag the shortcut to your home screen.
• Added a widget showing the installed Google Play system update level. Tap it to open update settings.
• Use it alongside the existing Android version widget.
</en-US>
```

## Screenshots

Upload the versioned PNG files in `screenshots/<locale>/` in the order below under phone screenshots for
each language. The images are 1080×1920 and show the running app's widgets and
launcher shortcut. Home screen dates and version values come from the capture
emulator. The widget picker image shows the preview generated from the device's own
values, which Android 15 and later use once the app or a widget has run; before
that, and on Android 12–14, the picker shows the app's built-in sample preview.
These values are examples, not claims that a device is up to date.

| Order | Japanese | English | Content |
| --- | --- | --- | --- |
| 1 | [ja-JP/01-overview.png](screenshots/ja-JP/01-overview.png) | [en-US/01-overview.png](screenshots/en-US/01-overview.png) | Android and Google Play widgets together |
| 2 | [ja-JP/02-play-shortcut.png](screenshots/ja-JP/02-play-shortcut.png) | [en-US/02-play-shortcut.png](screenshots/en-US/02-play-shortcut.png) | Long-press menu with Play update |
| 3 | [ja-JP/03-android-details.png](screenshots/ja-JP/03-android-details.png) | [en-US/03-android-details.png](screenshots/en-US/03-android-details.png) | Expanded Android version widget |
| 4 | [ja-JP/04-system-update.png](screenshots/ja-JP/04-system-update.png) | [en-US/04-system-update.png](screenshots/en-US/04-system-update.png) | System update screen opened by the app |
| 5 | [ja-JP/05-widget-picker.png](screenshots/ja-JP/05-widget-picker.png) | [en-US/05-widget-picker.png](screenshots/en-US/05-widget-picker.png) | Widget picker showing both widget types |

### Screenshot alternative text

| Image | 日本語 | English |
| --- | --- | --- |
| 1 | ホーム画面に並べたAndroidバージョンとGoogle Playシステム更新のウィジェット | Android version and Google Play system update widgets together on the home screen. |
| 2 | アプリアイコンを長押しすると表示される「Play更新」ショートカット | The Play update shortcut in the app icon's long-press menu. |
| 3 | Androidバージョン、セキュリティパッチ、ビルドID、APIレベルを表示するウィジェット | An expanded widget showing the Android version, security patch, build ID, and API level. |
| 4 | アプリアイコンから開いた端末のシステムアップデート設定 | The device system update settings opened from the app icon. |
| 5 | 「Android バージョン」と「Google Play更新」を選べるウィジェット一覧 | The widget picker with Android version and Google Play update widgets. |

## Publishing notes

### Capture setup

- App: debug build of version 1.3.0 (version code 6), with the widget resources
  prepared in [PR #6](https://github.com/75py/YourSystemIsUpToDate/pull/6).
  All ten images were captured in one pass on 2026-10-12, after the app had been
  opened once so the picker shows its generated preview.
- Device: Pixel 2 profile, Android 17 / API 37 Google Play emulator, Pixel Launcher.
  SDK image: `system-images;android-37.1;google_apis_playstore_ps16k;arm64-v8a`
  revision 9, build `CP31.260623.012`.
- Actual device values: security patch `2026-07-05`; Google Play module metadata
  version `2026-05`. The sample picker preview shows the Android release for the
  device's API level (17 here) and a fixed example Play level, `2026-05`, so it
  matches the device values that replace it after the app has run.
- In Home settings → Suggestions, turn off suggestions on the Home screen, so the
  dock shows no suggested app.
- All images: 1080×1920, default 420 dpi and font scale 1.0. The overview uses
  2×1 widgets; the details image uses a 4×2 Android widget so API level is visible.
- Switch the system language between Japanese and English (United States).
- Clear notifications, then use System UI demo mode to show a consistent 10:00 clock
  with Wi-Fi and battery icons only.
  The update screen's last-check time is the device's actual time.
- Capture with `adb exec-out screencap -p`. Export as 24-bit RGB PNG without
  changing the captured screen content. Image 4 shows the system update screen
  reached by tapping the app icon. The Japanese app title is abbreviated by the
  launcher's fixed-width picker header; widget names and descriptions fit at
  the default settings.

### Console update

- Keep the existing localized app names. The English launcher uses
  “System Update Shortcut”; the store title remains “Shortcut to System Update”.
  The capture device’s Pixel Launcher picker shows the application/store name;
  other launchers may show the activity name. Either header is acceptable.
  This release adds Google Play system
  update access without adding another launcher icon automatically.
- For titles and descriptions, omit Markdown headings and fences. For release
  notes, retain the language tags from the combined block above.
- Store listing changes are shared across tracks. Coordinate their publication
  with the production release of 1.3.0.
- Verify Google Play update navigation on a supported, Google Play-enabled device
  using the [release checks](../../../README.md#development-and-verification).

## References

- [Current Japanese listing](https://play.google.com/store/apps/details?id=com.nagopy.android.yoursystemisuptodate&hl=ja)
- [Current English listing](https://play.google.com/store/apps/details?id=com.nagopy.android.yoursystemisuptodate&hl=en)
- [Store listing fields and localization](https://support.google.com/googleplay/android-developer/answer/9859152?hl=en)
- [Release notes format and limits](https://support.google.com/googleplay/android-developer/answer/9859348?hl=en)
- [Screenshot requirements](https://support.google.com/googleplay/android-developer/answer/9866151?hl=en)
