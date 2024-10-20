# Health App
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)

The HealthApp is a comprehensive health and fitness application that empowers users to take control of their well-being and achieve their fitness goals.

## Features
- **<u>Health Page</u>**
  - Incorporates a **Step Counter**, **Heart Rate Measurement**, **Water**, and **Calorie Tracker** functionalities.
  - Automatically resets daily at midnight.
- **<u>GPS Activity Tracking</u>**
  - Integrated **map** that precisely tracks various **sport activities**.
  - **Records** the distance covered during the activity and **visualizes** it on the map.
  - **Saves** relevant activity **data** and a **screenshot** of the mapped route upon activity completion.
  - Track also **runs** in **background** when app is closed but a track started
- **<u>Compass</u>**
  - Utilizes a built-in **compass feature** to determine **direction** and **orientation**, proving particularly valuable for outdoor activities like hiking and route exploration.
  - Integration of a **light sensor** measurement enhances user experience.
- **<u>Activity History</u>**
  - Provides a comprehensive display of saved activity tracks, including corresponding map screenshots and specific data.
  - Possibility to delete tracks completely by holding on an entry.
- **Data Persistency** - Implements data handling through a robust backend **Room database**.
- **Code Quality** - Ensures high code quality standards with the integration of **Sonarcloud** to measure code quality metrics.
- **Sensors** - Utilizes a variety of sensors, such as **Accelerometer** and **MagneticField** Sensor, to enhance application functionality.
- **Supports Internationalization** - All locales (also Toasts) are translatable.
- **Uses ViewBinding for improved performance**
- **Supports Darkmode**

## Getting Started
### Dependencies
- **Java Source/Target Compatibility Version:** 17
- **Gradle Version:** 8.7
- **Android Gradle Plugin Version:** 8.6.0
- **Android Target/Compile SDK:** 34
- **Android Min SDK:** 30
### Installation
To successfully install and utilize the Health App, follow these steps:
- Obtain a **Google Maps API Key** by creating one at: [console.cloud.google.com](https://console.cloud.google.com/google/maps-apis/)
  - After obtaining the API Key, create on top-level the file **secrets.properties**. Add the following value: ``MAPS_API_KEY=<YOUR KEY>``
- Following **permissions** need to be granted for smooth functioning of the Health App
  - **``ACCESS_COARSE_LOCATION``** & **``ACCESS_FINE_LOCATION``** - Used to determine location for the GPS Tracking System & Compass functionalities
  - **``FOREGROUND_SERVICE``** - Enables sending notifications when a track runs in the background
  - **``BODY_SENSORS``** - Allows access to pulse frequency for relevant health measurements
  - **``ACTIVITY_RECOGNITION``** - Used for the step counter feature to count the steps


#### Privacy and Security
We take your privacy seriously. HealthApp collects and stores data only for the purpose of enhancing your health and fitness experience. The data will **only** be stored on the **internal storage** of the mobile phone.

## Project Metrics
The following metrics were generated by SonarCloud. They will be updated by SonarCloud when the master branch receives an update

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=bugs)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=djamn-org_health-app-v2&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=djamn-org_health-app-v2)

## Assets
Some images that represent the overall structure of the health app.

<p align="center">
  <img src="/assets/health2.png" alt="Health Page">
  <img src="/assets/gps2.png" alt="GPS Tracking">
  <br>
  <img src="/assets/compass2.png" alt="Compass">
  <img src="/assets/history2.png" alt="Track History">
</p>
