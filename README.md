# Steps to Run
- Open the project in IntelliJ
- Run MainApp.java
- If the JavaFX screen looks bigger than the laptop screen, adjust your laptop's display scale in Display Settings

# Gameplay
- Long press space bar to extend stick
- Press space bar during stick traversal to flip upside down
- Collect coins
- Coins can be exchanged for revival after crash or for buying sprites
- If the player collides with a platform while upside down it counts as a crash
- Perfect landing gets you one bonus point
- Press ESC key to pause the game
- Sprites can be bought from pause menu
- If the game seems stuck, check console to know what has happened
- If you're trying to revive/buy sprites and the game seems stuck, you have insufficient cherries and that will be printed on the console

# Demo Video
- StickHero - Demo Video is enclosed in the repository

# Design Patterns
- Singleton: Player
- Modified Singleton: Cherry
- Strategy: traversestick() method in Player decides at runtime whether game will continue or player dies
  
# JUnit Tests
- PlayerTest.java
- CherryTest.java
- PlatformTest.java

# Bonus
- Buy Sprites with coins
- Background music

# Contibutors
- [Riya Sachdeva - 2022411](https://github.com/riyasach189)
- [Aarav Mathur - 2022005](https://github.com/13100D)
- Group No. 131

# Citations
All graphics and media are copyrights of their creators, we are just borrowing them.
