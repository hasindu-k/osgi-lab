# Apache Felix OSGi Tutorial - Example2 and Example5

This guide shows how to compile and package the `example2` and `example5` OSGi bundles using both **Windows** and **Linux** environments. It includes details for classpath setup, file structure, and JAR creation with a manifest.

## 📥 Download Apache Felix

You can download Apache Felix from the official website:

https://felix.apache.org/documentation/downloads.html

Make sure to download the felix-framework-7.0.5/ distribution and extract it to a convenient location.

---

## 📁 Project Structure

```
tutorial/
├── example2/
│   ├── Activator.java
│   └── service/
│       └── DictionaryService.java
├── example5/
│   └── Activator.java
├── manifest_example2.mf
├── manifest_example5.mf
```

---

## 🔧 Compilation (Windows)

### ✅ Example2

From the `tutorial` directory, use:

```cmd
javac -cp C:\Udara\felix-framework-7.0.5\bin\felix.jar -d example2\bin example2\*.java example2\service\*.java

```

### ✅ Creating the JAR (Example2 - Windows)

```cmd
jar cfm example2.jar manifest_example2.mf -C example2\bin .
```

### ✅ Example5

Compile using both Felix and Example2 JARs in classpath:

```cmd
javac -cp C:\Udara\felix-framework-7.0.5\bin\felix.jar;example2.jar -d example5\bin example5\*.java
```

### ✅ Creating the JAR (Example5 - Windows)

```cmd
jar cfm example5.jar manifest_example5.mf -C example5\bin .
```

---

## 🔧 Compilation (Linux)

### ✅ Example2

Navigate to the `example2` folder:

```bash
cd /osgi-lab/Source_code/tutorial/example2
```

Compile and create bin folder:

```bash
mkdir -p bin
javac -d bin -cp /osgi-lab/Framework/felix-framework-7.0.5/bin/felix.jar *.java service/*.java
```

Create the JAR:

```bash
jar cfm example2.jar ../manifest_example2.mf -C bin .
```

### ✅ Example5

Navigate to the `example5` folder:

```bash
cd /osgi-lab/Source_code/tutorial/example5
```

Compile using both Felix and example2 JARs:

```bash
mkdir -p bin
javac -d bin -cp "/osgi-lab/Framework/felix-framework-7.0.5/bin/felix.jar:/path/to/example2.jar" Activator.java
```

Create the JAR:

```bash
jar cfm example5.jar ../manifest_example5.mf -C bin .
```

---

## 📄 Sample Manifest (manifest_example2.mf)

```properties
Manifest-Version: 1.0
Bundle-ManifestVersion: 2
Bundle-Name: Example2 Bundle
Bundle-SymbolicName: tutorial.example2
Bundle-Version: 1.0.0
Bundle-Activator: tutorial.example2.Activator
Import-Package: org.osgi.framework
```

## 📄 Sample Manifest (manifest_example5.mf)

```properties
Manifest-Version: 1.0
Bundle-ManifestVersion: 2
Bundle-Name: Example5 Bundle
Bundle-SymbolicName: tutorial.example5
Bundle-Version: 1.0.0
Bundle-Activator: tutorial.example5.Activator
Import-Package: org.osgi.framework, tutorial.example2.service
```

---

## ▶️ Run with Felix

```bash
cd /path/to/felix-framework-7.0.5
java -jar bin/felix.jar
```

If that doesn't work (e.g. on newer Java), try:

```bash
java \
--add-opens java.base/java.net=ALL-UNNAMED \
--add-opens java.base/java.security=ALL-UNNAMED \
-jar bin/felix.jar
```

### 🧪 In Felix Console

```bash
g! install file:/full/path/to/example2.jar
g! start <bundle-id>

# After example2 is running

g! install file:/full/path/to/example5.jar
g! start <bundle-id>
```

### 🧾 Example Output

```
g! install /osgi-lab/Source_code/tutorial/example2/example2.jar
Bundle ID: 7
g! start 7
Dictionary service registered and started successfully

g! install /osgi-lab/Source_code/tutorial/example5/example5.jar
Bundle ID: 8
g! start 8
Enter a blank line to exit.
Enter word: welcome
Correct.
Enter word: invalid
Incorrect.
```

---

## ✅ Notes

- Always ensure the classpath is correct depending on your OS.
- Use forward slashes `/` on Linux and backslashes `\` on Windows.
- JAR manifest must be formatted properly with a newline at the end.
- Example5 requires Example2 to be installed and started first.
