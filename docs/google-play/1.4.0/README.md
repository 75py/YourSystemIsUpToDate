# Google Play listing — 1.4.0

Copy each title and description block into its matching main store listing field.
Update both Japanese and English (United States). For release notes, use the
combined language-tagged block below in the release editor; retain its tags.

This release raises the minimum Android version to 12 (API 31). The descriptions
drop the “Android 10 and later” qualifiers from 1.3.0, because every supported
device now has the Play update shortcut and widget. Everything else is unchanged.

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
・アプリアイコンを長押しして「Play更新」を選ぶと、Google Playシステム更新画面を開けます。
・対応するホームアプリでは、「Play更新」を長押ししてホーム画面にドラッグすると、専用のショートカットとして配置できます。

Androidバージョンウィジェット
・端末のAndroidバージョンを表示します。
・スペースに応じて、セキュリティパッチレベル、ビルドID、APIレベルも表示します。
・1×1のコンパクト表示から、横長・縦長のサイズまで表示内容を調整します。
・タップすると、端末のシステムアップデート設定を開きます。

Google Play更新ウィジェット
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
• Long-press the app icon and choose “Play update” to open Google Play system update settings.
• On supported launchers, long-press “Play update” and drag it to your home screen to keep it as a separate shortcut.

Android version widget
• Shows your device's Android version.
• Adds the security patch level, build ID, and API level when space allows.
• Adapts from a compact 1×1 layout to wider and taller layouts.
• Opens your device's system update settings when tapped.

Google Play update widget
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
・動作環境をAndroid 12以降に変更しました。Android 10・11の端末では、インストール済みのバージョン1.3.0をそのまま利用できます。
・アプリの内部構成を改善しました。
</ja-JP>
<en-US>
• Now requires Android 12 or later. Devices on Android 10 or 11 can keep using their installed version 1.3.0.
• Internal improvements.
</en-US>
```

## Screenshots and feature graphic

The app's screens are unchanged, so keep the 1.3.0 images uploaded in Play Console.
They are in [1.3.0/screenshots](../1.3.0/README.md#screenshots) and
[1.3.0/feature-graphic](../1.3.0/README.md#feature-graphic), with their order and
alternative text. They were captured on Android 17, which 1.4.0 still supports.

## Publishing notes

- App: version 1.4.0 (version code 7), `minSdk` 31, `targetSdk` 36.
- Devices below Android 12 don't receive 1.4.0 and keep their installed 1.3.0.
  When reviewing the release, Play Console warns that it drops support for some
  devices. This is expected.
- New installs on Android 10–11 need the 1.3.0 bundle (version code 6) retained in
  the 1.4.0 production release. Without it, Play shows the app as incompatible on
  those devices.
- Store listing changes are shared across tracks. Publish the description update
  with the production release of 1.4.0.
- Verify Google Play update navigation on a supported, Google Play-enabled device
  using the [release checks](../../../README.md#development-and-verification).
  Emulators don't have the Google Play system update screen.

## References

- [1.3.0 listing](../1.3.0/README.md)
- [Store listing fields and localization](https://support.google.com/googleplay/android-developer/answer/9859152?hl=en)
- [Release notes format and limits](https://support.google.com/googleplay/android-developer/answer/9859348?hl=en)
