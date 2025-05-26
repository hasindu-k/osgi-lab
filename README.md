# Apache Felix OSGi Tutorial - Example2

This guide shows how to compile and package the `example2` OSGi bundle using both **Linux** and **Windows** environments. It includes details for classpath setup, file structure, and JAR creation with a manifest.

---

## 📁 Project Structure

```
tutorial/
├── example2/
│   ├── Activator.java
│   └── service/
│       └── DictionaryService.java
├── manifest_example2.mf
```

---

## 🔧 Compilation

### ✅ Linux

Navigate to the `example2` folder:

```bash
cd /osgi-lab/Source_code/tutorial/example2
```

#### Option 1: Direct classpath

```bash
mkdir -p bin
javac -d bin -cp /osgi-lab/Framework/org.apache.felix.main.distribution-7.0.5/felix-framework-7.0.5/bin/felix.jar *.java service/*.java
```

#### Option 2: Using environment variable

```bash
FELIX_JAR=/osgi-lab/Framework/org.apache.felix.main.distribution-4.0.3/felix-framework-4.0.3/bin/felix.jar
mkdir -p bin
javac -d bin -cp $FELIX_JAR *.java service/*.java

```

### ✅ Windows

From the `tutorial` directory, use:

```cmd
javac -cp C:\Udara\felix-framework-7.0.5\bin\felix.jar example2\*.java example2\service\*.java
```

### 🔍 Explanation

- `javac`: Compiles Java source files.
- `-cp`: Sets the classpath to include `felix.jar` (needed for OSGi classes).
- `*.java`: Compiles all Java files in the current directory (`example2`).
- `service/*.java` or `service\*.java`: Compiles all Java files in the `service` subfolder.

> **Purpose**: Compiles the `example2` OSGi bundle and its service interface implementation.

---

## 📦 Creating the JAR

### Linux

```bash
cd /osgi-lab/Source_code/tutorial/example2
jar cfm example2.jar manifest.mf -C bin .

```

### Windows

```cmd
jar cfm example2.jar manifest.mf -C bin .

```

> Ensure your `manifest.mf` ends with a newline and includes required OSGi headers. `refer manifest_example2.mf `

### 🔍 Explanation

- `jar`: Java Archive tool — used to create, view, or update .jar files.
- `c`: Create a new archive.

f Specify the archive filename (example2.jar)
m Include a manifest file (contains metadata like bundle name, activator, etc.)
example2.jar Name of the output JAR file
../manifest_example2.mf / ..\manifest_example2.mf Path to the custom manifest file
-C . . (Linux) / -C example2 . (Windows) Change into the specified directory and add all its contents (. means "current folder") to the JAR

> **Purpose**: Creating the JAR

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

---

## ▶️ Run with Felix

```bash
cd /path/to/felix-framework-7.0.5
java -jar bin/felix.jar

-----

if not

use
java \
--add-opens java.base/java.net=ALL-UNNAMED \
--add-opens java.base/java.security=ALL-UNNAMED \
-jar bin/felix.jar

# In Felix console:
install file:/full/path/to/example2.jar
start <bundle-id>
```

```bash
hasindu@hasindu-inspiron:/osgi-lab/Framework/org.apache.felix.main.distribution-7.0.5/felix-framework-7.0.5$ java -jar bin/felix.jar
____________________________
Welcome to Apache Felix Gogo

g!                                                                                                                                                                                    12:44:38
g! install /osgi-lab/Source_code/tutorial/example2/example2.jar                                                                                    12:44:38
Bundle ID: 7
g! lb                                                                                                                                                                                 12:45:16
START LEVEL 1
   ID|State      |Level|Name
    0|Active     |    0|System Bundle (7.0.5)|7.0.5
    1|Active     |    1|jansi (1.18.0)|1.18.0
    2|Active     |    1|JLine Bundle (3.13.2)|3.13.2
    3|Active     |    1|Apache Felix Bundle Repository (2.0.10)|2.0.10
    4|Active     |    1|Apache Felix Gogo Command (1.1.2)|1.1.2
    5|Active     |    1|Apache Felix Gogo JLine Shell (1.1.8)|1.1.8
    6|Active     |    1|Apache Felix Gogo Runtime (1.1.4)|1.1.4
    7|Installed  |    1|English dictionary (1.0.0)|1.0.0

g! start 7                                                                                                                                                                            12:45:20
Dictionary service registered and started successfully

g! stop 7                                                         12:45:28
Dictionary service stopped.

g!                                                                                                                                                                                    12:45:34


```

javac -d bin -cp "$FELIX_JAR:/media/hasindu/Disk_D/Coding_Ubuntu/osgi-lab/Source_code/tutorial/example2/example2.jar" Activator.java

---

## ✅ Notes

- Always ensure the classpath is correct depending on your OS.
- Use forward slashes `/` on Linux and backslashes `\` on Windows.
- JAR manifest must be formatted properly with a newline at the end.
