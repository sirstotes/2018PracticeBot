# 2018 Team 4585 Offseason Code
Team 4585's code for the 2018 Offseason event at AMES in Cottonwood High School.

This README explains setup instructions.

## Setup Instructions

# General

- Clone the repository
- Run `./gradlew` to download gradle and required libraries
- Run `./gradlew tasks` to see available options

# Eclipse

- Run `./gradlew eclipse`
- Select the `2018PracticeBot` folder as import source in "File > Open Projects"
# IntelliJ

- Run `./gradlew idea`
- Open `2018PracticeBot.ipr` file with IntelliJ

# Building/Deploying to the Robot

- Run `./gradlew build` to build the code. Use the `--info` flag for more details
- Run `./gradlew deploy` when connected to Driver Station to deploy to the robot using Powershell
