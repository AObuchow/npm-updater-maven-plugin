# npm-updater-maven-plugin
Example usage with commandline:\
 `mvn com.aobuchow:npm-updater-maven-plugin:0.0.1-SNAPSHOT:checkOutdated "-DextraModules=typescript-language-server@0.3.0"`
 
 Output:
 ```
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------< com.aobuchow:npm-updater-maven-plugin >----------------
[INFO] Building NPM module updater maven plugin 0.0.1-SNAPSHOT
[INFO] ----------------------------[ maven-plugin ]----------------------------
[INFO] 
[INFO] --- npm-updater-maven-plugin:0.0.1-SNAPSHOT:checkOutdated (default-cli) @ npm-updater-maven-plugin ---
[WARNING] typescript-language-server is outdated!
[WARNING] current version: 0.3.0, latest version: 0.4.0
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.992 s
[INFO] Finished at: 2020-02-26T16:44:06-05:00
[INFO] ------------------------------------------------------------------------
```
