# Google Play release assets

Each release keeps its listing copy and screenshots together. Choose a release
below for localized titles, descriptions, release notes, screenshot order, and
capture details.

- [1.4.0](1.4.0/README.md)
- [1.3.0](1.3.0/README.md)

## Layout

```text
<version>/
  README.md
  feature-graphic/
    ja-JP.png
    en-US.png
  screenshots/
    ja-JP/
      01-android-details.png
      02-play-shortcut.png
      03-system-update.png
      04-widget-picker.png
    en-US/
      ...
```

Use the numeric prefix as the upload order. Keep filenames descriptive and use
Play Console locale codes for language directories. For a new release, create a
new version directory and add it to this index instead of overwriting a previous
release's images or copy.

Publish the localized listing changes alongside the matching app release. The
version document includes one combined language-tagged release-notes block for
the release editor; the other text blocks are individual store listing fields.
