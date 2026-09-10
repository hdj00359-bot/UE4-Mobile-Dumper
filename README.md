# UE Mobile Dumper

UE Mobile Dumper is a lightweight tool for dumping Unreal Engine (UE4/UE5) mobile games on Android.

Unlike traditional dumpers, this tool **does NOT require root access**. It can be **directly injected into the APK** and accessed through an **in-game floating window UI**.

---

## ✨ Features

- 🚫 No root required
- 📦 Works via APK injection
- 🪟 Built-in floating window interface (in-game overlay)
- 🧠 Dump core UE structures:
    - GNames / FNamePool
    - GUObjectArray
    - GWorld
- 🧾 SDK generation (classes, structs, enums, functions)
- 📚 Dump strings and objects
- ⚡ Fast dumping mode
- 🛠 Supports 32-bit & 64-bit UE4/UE5 games
- 🔧 Dump and rebuild `libUE4.so`

---

## ⚙️ Requirements

- Android device (No root needed)
- APK editor / injector tool
- Android NDK (for building)
- Basic knowledge of UE offsets

---

## 🛠️ Build Instructions

1. Clone the repository:
```bash
git clone https://github.com/AscarreX/UE4-Mobile-Dumper.git
cd UE4-Mobile-Dumper
```

2. Build the standalone APK:
```bash
./gradlew :app:assembleRelease
```

The APK is generated at `app/build/outputs/apk/release/app-release.apk`. Install it
on a non-root device, grant the overlay permission, and start it before using the
floating menu.

To get an injection bundle containing every Dex file and both configured native
ABIs, run:
```bash
./gradlew :app:packageReleaseSoDex
```

The bundle is generated at
`app/build/outputs/so-dex/ue4-dumper-1.0-so-dex.zip`. Extract its `lib/<abi>/libDumper.so`
and `classes*.dex` files into the target APK according to the APK injector's
layout rules.

The target game must load the injected library and invoke the Android entry code;
an independently installed dumper APK cannot access another application's memory.

---

## 🚀 Usage (Injection Method)

1. Inject the release `classes*.dex` and the matching ABI `libDumper.so` into the target APK.
2. Add the dumper startup call to the target application's Activity, or use the injector's JNI/Dex entry hook.
3. Rebuild, sign, and install the modified game APK.
4. Grant overlay permission to the modified game and launch it.
5. Open the **floating window overlay** and use the UI to:
    - Dump SDK
    - Dump strings
    - Dump objects
    - Dump `libUE4.so`

---

## 🧾 Features via UI

- SDK Dump (GUObject / GWorld based)
- String Dump
- Object Dump
- Memory Dump

All actions are accessible directly from the in-game overlay.

---

## ⚠️ Notes

- Offsets must be obtained manually for each game
- Modified or protected UE builds may require adjustments
- Some games may block overlays or injections
- Use correct architecture (armv7 / arm64)

---

## 🐞 Troubleshooting

| Issue | Solution |
|------|---------|
| No data dumped | Check offsets |
| Crash on injection | Wrong architecture or bad hook |
| Overlay not visible | Game blocking draw-over apps |
| Invalid SDK | Incorrect GNames / GUObject |

---

## 📌 TODO

- [ ] Auto offset finder (pattern scanning)
- [ ] Better support for protected builds
- [ ] One-click injection tool

---

## ⚖️ License

Licensed under the GNU AGPL License.

---

## ⚠️ Disclaimer

This project is for **educational and research purposes only**.

Do NOT use it for:
- Cheating in online games
- Bypassing protections
- Any illegal activity

You are responsible for your own usage.

---

## 🙌 Credits

- UE4 reverse engineering community
- Inspired by various UE dumping tools
- Contributors
- kp7742
- maiyao1988

---

## ⭐ Support

If you find this project useful:

- Star the repo ⭐
- Share it
- Contribute improvements  