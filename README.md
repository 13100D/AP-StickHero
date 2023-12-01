# Steps to Run
- Open the project in IntelliJ
- Run MainApp
- If the JavaFX screen looks bigger than the laptop screen, adjust your laptop's display scale in Display Settings

# Contibutors
- [Riya Sachdeva - 2022411](https://github.com/riyasach189)
- [Aarav Mathur - 2022005](https://github.com/13100D)
- Group No. 131

# Citations
All graphics are copyrights of their creators, we are just borrowing them.






# to understand:

sprites and animations
- needed to implement


- - player & stick
    - needed for sprites
- - cherries
  - colision mechanics during animation to collect cherry



Add flags so that keypresses during animation runtime flip player
Cherry...
Pillars spawning
Same collision logic for pillars...
Pillars spawn 3
2 visible one outside frame
Move back
Once movement animation done instantly teleport the leftmost pillar to outside the screen
Move to left once stick animation and movement has succeeded ( if stick length ends up within margin of acceptable values
Currently stick is spawned / initialised when key pressed
Need to use better approach
Can't leave pillars and player unspawned
Possible solution spawn static entries from fxml
Use fx: Id in code

# to implement:
- spawn in towers
- spawn in cherries


https://www.javatpoint.com/javafx-playing-audio.

audioplayer use the volume mute and unmute functions and call the same