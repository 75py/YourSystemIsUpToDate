# Google Play store listing

The current main store listing for Japanese and English (United States). Copy
each block into its matching Play Console field, without the Markdown headings
and fences.

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

## Screenshots

Upload the PNG files in `screenshots/<locale>/` in the order below under phone
screenshots for each language. The images are 1080×1920 and show the running
app's widgets and launcher shortcut. Home screen dates and version values come
from the capture emulator. The widget picker shows the app's built-in sample
previews, a fixed example Android release and Play level. These values are
examples, not claims that a device is up to date.

| Order | Japanese | English | Content |
| --- | --- | --- | --- |
| 1 | [ja-JP/01-android-details.png](screenshots/ja-JP/01-android-details.png) | [en-US/01-android-details.png](screenshots/en-US/01-android-details.png) | Expanded Android version widget with the Google Play widget |
| 2 | [ja-JP/02-play-shortcut.png](screenshots/ja-JP/02-play-shortcut.png) | [en-US/02-play-shortcut.png](screenshots/en-US/02-play-shortcut.png) | Long-press menu with Play update |
| 3 | [ja-JP/03-system-update.png](screenshots/ja-JP/03-system-update.png) | [en-US/03-system-update.png](screenshots/en-US/03-system-update.png) | System update screen opened by the app |
| 4 | [ja-JP/04-widget-picker.png](screenshots/ja-JP/04-widget-picker.png) | [en-US/04-widget-picker.png](screenshots/en-US/04-widget-picker.png) | Widget picker showing both widget types |

### Screenshot alternative text

| Image | 日本語 | English |
| --- | --- | --- |
| 1 | Androidバージョン、セキュリティパッチ、ビルドID、APIレベルを表示するウィジェットと、Google Playシステム更新のウィジェット | An expanded widget showing the Android version, security patch, build ID, and API level, with the Google Play system update widget. |
| 2 | アプリアイコンを長押しすると表示される「Play更新」ショートカット | The Play update shortcut in the app icon's long-press menu. |
| 3 | アプリアイコンから開いた端末のシステムアップデート設定 | The device system update settings opened from the app icon. |
| 4 | 「Android バージョン」と「Google Play更新」を選べるウィジェット一覧 | The widget picker with Android version and Google Play update widgets. |

## Feature graphic

Upload the matching file as each language's feature graphic. Both are 1024×500,
24-bit RGB PNG.

| Japanese | English |
| --- | --- |
| [ja-JP.png](feature-graphic/ja-JP.png) | [en-US.png](feature-graphic/en-US.png) |

The widgets are cropped from screenshot 1 of the same language, with the widget's
rounded corners kept, so they match the app. The icons are the app's launcher and
Play update icons. Text is set in Hiragino Sans and avoids saying a device is up
to date.
