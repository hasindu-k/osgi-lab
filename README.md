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
javac -cp /osgi-lab/Framework/org.apache.felix.main.distribution-4.0.3/felix-framework-4.0.3/bin/felix.jar *.java service/*.java
```

#### Option 2: Using environment variable

```bash
FELIX_JAR=/osgi-lab/Framework/org.apache.felix.main.distribution-4.0.3/felix-framework-4.0.3/bin/felix.jar
javac -cp $FELIX_JAR *.java service/*.java
```

### ✅ Windows

From the `tutorial` directory, use:

```cmd
javac -cp C:\Udara\felix-framework-4.0.3\bin\felix.jar example2\*.java example2\service\*.java
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
jar cfm example2.jar ../manifest_example2.mf -C . .
```

### Windows

```cmd
jar cfm example2.jar ..\manifest_example2.mf -C example2 .
```

> Ensure your `manifest_example2.mf` ends with a newline and includes required OSGi headers.

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
cd /path/to/felix-framework-4.0.3
java -jar bin/felix.jar

# In Felix console:
install file:/full/path/to/example2.jar
start <bundle-id>
```

---

## ✅ Notes

- Always ensure the classpath is correct depending on your OS.
- Use forward slashes `/` on Linux and backslashes `\` on Windows.
- JAR manifest must be formatted properly with a newline at the end.
