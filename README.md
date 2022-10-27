# mcdevtools
> Minecraft Plugin (1.8.8, 1.19)

This plugin helps me to see how colored messages and items would render in Minecraft. Particularly useful when creating custom inventories and Minecraft minigames.

## Commands
`/echo` - Broadcast the input

![Echo command image 1](https://share.alkanife.fr/github/mcdevtools/echo1.png)
![Echo command image 2](https://share.alkanife.fr/github/mcdevtools/echo2.png)

`/rename` - Quickly rename an item (requires to be a player)

![Rename command image 1](https://share.alkanife.fr/github/mcdevtools/rename1.png)
![Rename command image 2](https://share.alkanife.fr/github/mcdevtools/rename2.png)

`/lore` - Quickly set a lore to an item (`-n` to skip line). If executed by console, this applies to the first connected player

![Lore command image 1](https://share.alkanife.fr/github/mcdevtools/lore1.png)
![Lore command image 2](https://share.alkanife.fr/github/mcdevtools/lore2.png)

## Changes in 1.19+
- Requires [CommandAPI](https://github.com/JorelAli/CommandAPI).
- Inputs are [MiniMessages](https://docs.adventure.kyori.net/minimessage/format.html) for item names and lore. 
- Rename and lore commands now requires the player to be specified. 
- The `/echo` command works the same way, but a new command (`/echominimessage`) has been added for minimessages.

![Changes in 1.19+](https://share.alkanife.fr/github/mcdevtools/1.19.png)

## License
[The Unlicense](https://unlicense.org/)
