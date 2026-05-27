# Pomodoro Timer

A simple and user‑friendly Pomodoro technique timer to boost your productivity.

![Java](https://img.shields.io/badge/Java-17-007396?logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-17-2a5c9b?logo=javafx)
[![CI](https://github.com/apache/kafka/actions/workflows/ci.yml/badge.svg?branch=trunk&event=push)](https://github.com/L41K0N1/StudyWithMe/actions/workflows/maven.yml?query=event%3Apush+branch%3Atrunk)

## Description

Pomodoro Timer helps you organize your workflow using work and rest intervals. You can choose from three preset modes, enable background focus music, and easily switch between sessions.

## Key Features

- **Three preset sessions:**
  - Classic: 25 min work / 5 min break
  - Extended: 50 min work / 10 min break
  - Deep Work: 120 min work / 20 min break
- Toggle background focus music on/off
- Switch sessions at any time
- Visual and audio notifications for session start/end

## Installation

You need to have [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed.

You can run the application in one of two ways:

### Option 1. Clone the repository and build

1. Clone the repository:
```bash
git clone https://github.com/L41K0N1/StudyWithMe.git
```
2. Navigate to the project folder:
```bash
cd Pomodoro-Timer
```
3. Build the project using Maven (make sure you have Java 17 and Maven installed):
```bash
mvn clean package
```
4. Run the application:
```bash
cd \target

java -jar SWM-1.0-SNAPSHOT.jar
```

### Option 2. Download a pre‑built release
 1. Go to the releases tab.
 2. Download the latest version archive (e.g., dist.zip).
 3. Extract the archive.
 4. Launch the executable:
    - On Windows: run StudyWithMe.exe
    - The app includes a bundled Java Runtime Environment (JRE), so no additional installation is required.
   
## Usage
 1. Launch the application.
 2. Select your desired session from the dropdown menu (25/5, 50/10, or 120/20).
 3. Click the Start button to begin the timer.
 4. Use the Stop button to halt the timer.
 5. Toggle background focus music using the Start Music switch.
 6. Change sessions at any time — the timer will automatically adjust to the new settings.

## Requirements:
  - To build from source: Java 17+ and Maven 3.6+.
  - To run the pre‑built release: Windows operating system (the executable is packaged with Launch4j).

   
## Contributing
If you’d like to contribute to Pomodoro Timer, please submit a Pull Request or open an Issue to report a bug or suggest an enhancement.
