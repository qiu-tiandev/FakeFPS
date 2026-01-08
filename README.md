# FakeFps

**Mod ID:** `fake-fps`  
**Latest Version:** v1.2.1  
**Minecraft:** 1.21.10  
**Fabric Loader:** 0.17.3+  

---

## Description

FakeFPS is a fabric mod that displays fake fps values on screen. Use it to flex on your friends!
**NOTE: This mod does not provide actual performance boost in any way.**
---

## Installation

1. Install **Fabric Loader 0.17.3** or newer for Minecraft 1.21.1x
2. Place `fake-fps-v.x.x.jar` in your `mods` folder  
3. Start Minecraft with the Fabric profile
5. **Make sure you have the fabric api mod**

---
### Commands
#### `/fpsspoof`
##### `enable`: `/fpsspoof enable` enables fake fps
##### `disable`: `/fpsspoof disable` disables fake fps
##### `toggle`: `/fpsspoof toggle` toggles fake fps status
##### `disable`: `/fpsspoof disable` disables fake fps
##### `multiplier`: `/fpsspoof multiplier <value>` changes the value the real fps is multiplied by
##### `fluctuation`: `/fpsspoof fluctuation <value>` changes how much the fps fluctuates (to make the fps display look more 'real')
##### `reset`: `/fpsspoof reset` resets to default settings
##### `status`: `/fpsspoof disable` shows the current configuration
---

## Building from source
**Requires Java 21 or later**
1. Clone this repo with `git clone https://github.com/qiu-tiandev/FakeFPS`
### Windows
2. Go to the repo directory and run `gradlew.bat build`
3. Check `build/libs` for compiled jar file
### MacOS/Linux
2. Go to repo directory and run `./gradlew build`
3. Check `build/libs` for compiled jar file
