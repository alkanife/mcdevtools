<h1 align="center">
  Minecraft devtools
  <br>
  <a href="https://github.com/alkanife/alkabot/blob/main/pom.xml">
    <img src="https://img.shields.io/badge/Open%20JDK-17-green" alt="JDK 17">
  </a>
  <a href="https://github.com/alkanife/mcdevtools/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/alkanife/mcdevtools" alt="LICENSE">
  </a>
  <a href="https://github.com/alkanife/mcdevtools/releases/tag/2.0.0">
    <img src="https://img.shields.io/badge/version-2.0.0-blue" alt="Version 2.0.0">
  </a>
  <img src="https://img.shields.io/badge/Spigot-1.8.8-orange" alt="Spigot 1.8.8">
  <img src="https://img.shields.io/badge/PaperMC-1.20-orange" alt="PaperMC 1.20">
</h1>

<p align="center">
  <a href="#overview">Overview</a>
  •
  <a href="#commands">Commands</a>
  •
  <a href="#license">License</a>
</p>

## Overview
This plugin mostly helps me to see how colored messages and items would render in Minecraft. It's particularly useful when creating custom inventories and Minecraft minigames. Over time I added a few other commands, see the complete list below.

A version for Minecraft 1.8 is still available under the name **mcdevtools-legacy**, but will not receive the 2.0.0 update. <a href="https://github.com/alkanife/mcdevtools/releases/tag/1.0.0">Latest 1.8-compatible version here</a>.

### Preview
*Echo:*
![echo command](./img/echo.png)

*Item name & lore:*
![item name and lore](./img/item.png)

## Commands
If you are not familiar with the MiniMessage format, go to the documentation by <a href="https://docs.advntr.dev/minimessage/format.html">clicking here</a>.

Chat:
- `echo <value>`: Broadcasts input

Tablist:
- `tablist_header <value>`: Changes the player list header
- `tablist_footer <value>`: Changes the player list footer

Player:
- `player_fly [player]`: Toggle player flight

Player's name:
- `player_displayname <player> <value>`: Changes the player's display name
- `player_customname <player> <value>`: Changes the player's custom name
- `player_listname <player> <value>`: Changes the player's list name

Player's team:
- `player_team_prefix <player> <value>`: Changes the player's team prefix
- `player_team_suffix <player> <value>`: Changes the player's team suffix
- `player_team_color <player> <value>`: Changes the player's team color

If the player does not have a team, the command will create one.

Items:
- `item_name <player> <value>`: Changes the name of the item the player is holding
- `item_lore <player> <value>`: Changes the lore of the item the player is holding
- `item_lore_at <player> <line> <value>`: Changes the lore of the item the player is holding, specifying which line to change

For the lore, use `<newline>` to skip a line.

## License
[The Unlicense](https://unlicense.org/)