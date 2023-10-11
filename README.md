# League of Friendship

League of Friendship is a comprehensive application designed to enhance your gaming experience with friends. It leverages the Riot API to gather player and match data for a select group of players, allowing you to dive deep into your gaming statistics. The project was born out of a passion for front-end and back-end software engineering, as well as a curiosity to explore the possibilities of the Riot API.
## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Acknowledgements](#acknowledgements)
- [Screenshots](#screenshots)

## Introduction
This project serves as a hub for post-game analysis, providing valuable insights into your League of Legends matches. It focuses on a specific set of players and their matches, efficiently storing and tracking valuable data. The core functionalities include importing player and match data from the Riot API, categorizing and sorting matches, and offering in-depth analysis of individual games.

## Features

Features
1. Match Data Retrieval
   - The application fetches player and match data from the Riot API for each friend in your database.
   - It identifies unique matches and records the friends who participated in each match, saving valuable storage space.
2. Home Page
   - On the homepage, you can access a list of the last 100 matches played by your group.
   - Matches are categorized by type, and you can filter to view specific match types or any unrecorded matches.
   - Sort matches by individual friends or multiple friends to see when they played together.
   - Refresh the database to update match data and capture any recent game statistics.
3. Match Page
   - Overview Section: Displays relevant statistics about the teams and individual players.
   - Timeline Section: Visualizes player kills, deaths, and their locations on the map, with hover-over functionality.
   - Graph Section: Utilizes Apex Charts to showcase team advantage, team comparison, and individual player statistics.
   - Stat Section: Offers various categories with toggleable information and visual representation of team comparisons.
   - Participant Section: Provides in-depth player stats, build paths, and graphs for analysis.
4. Participant Stats
   - End-game statistics for each player, allowing for insights and comparisons.
   - Rune and item data, including purchase times and skill leveling paths.
   - Graphs to visualize individual player performance over time.

## Acknowledgements

I would like to express my gratitude to the following individuals, organizations, and resources that have contributed to the development of this project:

- Riot Games for providing the Riot API, enabling us to access valuable game data.
- Apex Charts for their powerful charting library, enhancing the visualization of statistics.
- My friends who inspired this project and have been enthusiastic beta testers.
- Everyone else taking a look at this project

## Screenshots

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\01_Home.png)
Home Page

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\03_Sort2.png)
Sort matches by selecting friends from sidebar.

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\06_LoadGames2.png)
Download new games and load them into the database.

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\07_Match_Overview.png)
Overview of the match played

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\08_Match_Timeline1.png)
![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\09_Match_TimeLine2.png)
A heat map of player kills and deaths. Selecting a player will showcase only their kills and deaths

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\12_Match_Graphs3.png)
![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\13_Match_Graphs4.png)
Match graphs to see how well teams and individuals did over the duration of the match.

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\14_Match_Stats1.png)
![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\15_Match_Stats2.png)
Toggleable match stats to compare how players compare to team and the other team.

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\16_Match_Participants1.png)
Individual player stats for the match.

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\17_Match_Participants2.png)
The build path for the player during a match.

![Project Image](C:\Users\wgage\IdeaProjects\lof\README_IMGS\18_Match_Participants3.png)
Graphs for player stat breakdowns over time.