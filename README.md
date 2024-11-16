# Expert-Build
Minecraft build plugin, Expert-Build Documentation

* Latest plugin version : 1.18.1.23
* Author : Marodeur
* Contributor : Lison, Cancri
* Depend : FAWE
* Compatibility : PlotSquared
* [Supported Minecraft version : 1.18, 1.19, 1.20, 1.21 ](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Supported_Versions)


![LatestVersion](https://img.shields.io/badge/Discord_:-marodeur-purple)  
[![Spigot](https://img.shields.io/badge/Download-Expert_Build-purple)](https://www.spigotmc.org/resources/expert-build.110059/)
![LatestVersion](https://img.shields.io/badge/Latest_Version-1.18.1.23-purple)

------------------
<p align="center">
<img src="https://imgur.com/h5NrDR2.png" title="Image_1" alt="deco">
</p>

------------------
# News

-> * [New Patter, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Pattern)

-> * [New command /areatimer, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Brush)

-> * [New brush Clipboard3D, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Brush)

-> * [New command /painting, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Commands)

-> * [Update the clipboard brush, with new functionality, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Brush)

-> * [New inventory, open with left click using honeycomb ! click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Inventory)

------------------

# Supported_Versions

| Mc Version | Supported          |
|------------|--------------------|
| 1.18.x     | :white_check_mark: |
| 1.19.x     | :white_check_mark: |
| 1.20.x     | :white_check_mark: |
| 1.21.x     | :white_check_mark: |

------------------
# Summary

* [**INSTALLATION**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Installation)

* [**COMMANDS**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Commands)

* [**BRUSH**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Brush)

* [**INVENTORY**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Inventory)

* [**MASK**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Mask)

* [**CONFIGURATION**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Configuration)

* [**PERMISSION**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Permission)

------------------

# Installation

``` 
1 / Insert plugin in folder plugins,

2 / Use FAWE plugin,

3 / Use java version : 16 minimum,
```

[![FAWE](https://img.shields.io/badge/Download-FAWE-red)](https://ci.athion.net/job/FastAsyncWorldEdit/)

------------------

# Usage

## Commands

- **All commands and brush using Fawe editSession, All commands and brush can be //undo, //redo !!!**

------------------
`/painting` :

This command allows you to create a painting based on the light of the blocks

*permission : exp.command.painting*

Command : /painting <block> <block> [length] [ block ]

Example : ```/painting white_concrete stone```


<img src="https://imgur.com/gywYZSS.png" width="auto"  alt="deco"/>

The first 2 server arguments define the start and finish block of the gradient. The 3rd argument (optional) indicates the number of blocks to place in the gradient (between 0 and 15; by default 15).
The 4th argument (optional) will force the gradient to pass through the block defined in the middle

------------------

`/1` or `right click using wand` :

Execute //pos1

*permission : exp.selection.airpos*

------------------
`/2` or `left click using wand` :

Execute //pos2

*permission : exp.command.tool* and *permission : worldedit.selection.pos*

<img src="https://imgur.com/RV9tyb8.gif" width="auto"  alt="deco"/>

------------------
`shift click` :

Execute //sel

*permission : exp.command.sel* and *permission : worldedit.selection.pos*

------------------
`/terra` aliases `t` :

Give terraforming tools

*permission : exp.command.tool*

------------------
`/vox` :

Give voxel tools

*permission : exp.command.tool*

------------------
`/plume` :

Give GoPaint tool

*permission : exp.command.tool*

------------------
`/silex` :

Give GoBrush tool

*permission : exp.command.tool*

------------------
`/pos` :

get position location

*permission : exp.command.tool*

------------------
`/getcommand` :

displays the command of a Block
command by looking at it from a distance

*permission : exp.command.tool*

<img src="https://imgur.com/eraXDb9.png" title="Image_1" alt="deco">


------------------
`/c` :

shortcut for execute //copy

*permission : exp.command.tool* *permission : worldedit.clipboard.copy*

------------------
`/cube` :

set cuboid selection mode

*permission : exp.command.tool*

------------------
`/poly` :

set polygone selection mode

*permission : exp.command.tool*

------------------
`/convex` aliases `/conv`:

set convex selection mode

*permission : exp.command.tool*

------------------
`/repeater` or `/rt` :

preset a tick to the repeater when you set it

*permission : exp.command.tool*

------------------
`/schemtrans` :

deactivate to be reworked

transfer schematic on other sftp server

*permission : exp.command.schemtrans*

------------------
`/timelapse` :

This allows timelapses to automatically deconstruct a selection layer by layer according to our desires.

WARNING, this command is not compatible with //undo and //redo. Your selection is copied to your clipboard ! 

Command : /timelapse <start-stop> [ block per tick = 1 ] [ shape destroy block = fawe saving ] [!air]

Example : ```/timelapse start 5```, ```/timelapse stop```

*permission : exp.command.timelapse*

<img src="https://i.imgur.com/62yXy0d.gif" title="Image_1" alt="deco">

------------------
`/areatimer` :

This command allows you to define a zone using a WE selection.
Then the plugin will count the time you spend in this zone.

This can be very practical when you want to estimate the time spent on a project,
Or charge a commission for the time spent on it !

Command : /areatimer <create/delete/info/stop/resume> [ area-name ]

Example : ```/areatimer create my_new_project```, ```/areatimer info my_new_project```

*permission : exp.command.areatimer*

<img src="https://imgur.com/e3KSKvK.png" title="Image_1" alt="deco">

------------------

`/perlin` :

deactivate to be reworked

generate perlin, predefined for the hair

*permission : exp.command.perlin*

------------------
`/autocb` :

pastes all commands separated by a semicolon into a command block. Then execute the command by looking at the command block
this will allow when the cb to automatically include the command

*permission : exp.command.autocb*

------------------
`/expbuild` aliases `/exp` :

general command for reload config, need help, and more

*permission : exp.command.build*


## Brush

------------------
`/flower` aliases `fw`:

argument : `melt`, `lift`, `fill`, `smooth`, `floatclean`, `blendball`, `clipboard`, `cube`, `sphere`, `2dCube`, `drain`, `line`, `none`, `overlay`, `spike`, `biome`, `erodeblend`

Defaut tool : CLAY_BALL, SPECTRAL_ARROW and HONEYCOMB

General brush command,

- material : set pattern

  *permission : exp.command.flower*

- radius : set radius

  *permission : exp.command.flower*

- clipboard3D : Paste your clipboard according to the angle of your view on the block you clicked

  *permission : exp.brush.clipboard3d*



- melt, fill, fill, smooth, floatclean, erodeblend : voxel brush,
  *permission : exp.brush.erode and exp.brush.erodeblend*

Change the radius : /fw radius <radius>

Change the shape : /fw shape <shape (sphere, cylinder)>



- clipboard3D : Place a clipboard depending on the direction of the head

  *permission : exp.brush.clipboard3d*

<img src="https://imgur.com/JWKl1zB.gif" title="Image_1" alt="deco">


- clipboards : 
  - < add > : Add a clipboard to the brush (flag : -a, -e, -c -b)
  - < remove > : Remove a clipboard from the brush
  - < removeAll > : Remove all clipboards from the brush
  - < autoRotation > : Paste the clipboard with a random rotation of 0, 90, 180 or 270
  
  *permission : exp.brush.clipboard*


<img src="https://imgur.com/olGd83K.png" title="Image_1" alt="deco">


<img src="https://imgur.com/1NEZOjY.gif" width="auto"  alt="deco"/>


- cube : cube brush

  *permission : exp.brush.cube*


- 2dCube : place a cube with an angle corresponding to the angle that the player has

  *permission : exp.brush.2dcube*

<img src="https://imgur.com/EqbP1cz.png" title="Image_1" alt="deco">

- sphere : sphere brush
  *permission : exp.brush.sphere*


- line : line brush

  *permission : exp.brush.line*


- overlay : overlay brush

  *permission : exp.brush.overlay*


- drain : drain brush

  *permission : exp.brush.drain*

- drain : drain brush

  *permission : exp.brush.eraser*


- biome : biome brush

  *permission : exp.brush.biome*


- spike : generates a spike from your position to the brush point

  *permission : exp.brush.spike*

<img src="https://imgur.com/zAzYBG5.png" title="Image_1" alt="deco">

- blendball : blend terrain

  *permission : exp.brush.blendball*


- updatechunk : update chunk

  *permission : exp.brush.updatechunk*

- none : disable brush

  *permission : exp.brush.none*

------------------

If you are an operator on the server, you will still be able to execute all commands

------------------

## Inventory

Open the inventory with a left click on the honeycomb

- By selecting the bone you open the configuration interface of humanoid organics

*permission : exporga.use*


By default the switch is on the BARRIER_BLOCK > in mode: Activation / deactivation of members

<img src="https://imgur.com/X72XXhK.png" title="Image_1" alt="deco">

This allows you to choose to deactivate / activate certain body parts (by default all parts are activated)

By clicking on the X head (Switch to X) you can modify the pitch of each member of the organic,
and you can do the same thing by clicking on the Y head, which will change the yaw of the members of the organic

<img src="https://imgur.com/sQmFi4w.png" title="Image_1" alt="deco">

Now you are ready to pregenerate your organic, by clicking on the first ARMOR_STAND.
Of course you can modify the direction of the members at any time and see the modification live!!!


<img src="https://imgur.com/4Ff6Fxj.png" title="Image_1" alt="deco">

Finally by clicking on the second ARMOR_STAND you can generate your organic!!! Congratulations

<img src="https://imgur.com/iUUfUkz.png" title="Image_1" alt="deco">

------------------

- By selecting the leather chest-plate you open the configuration interface of custom leather

<img src="https://imgur.com/bU2Ht53.png" title="Image_1" alt="deco">

Click or shift click on the dyes to change the colors of the tonics,

Click on the tunic to be able to put them in your inventory

------------------

- By selecting the sunflower you open the configuration interface of flower brush

Place the items present in your inventory in place of the barrier blocks

By clicking on the yellow flags, you can modify the properties of the block or the item

For example if you want to put the upper part of the rose_bush, or modify (upper / lower / double) the slabs, or put persistent leaves, or choose the number of candles to put...

<img src="https://imgur.com/Tgdu8dy.png" title="Image_1" alt="deco">

By clicking on the black_glass present above, you can modify the quantity of the item in the brush

<img src="https://imgur.com/XqYI3kj.png" title="Image_1" alt="deco">

Clicking on the bottle modifies the amount of air present in the brush,

<img src="https://imgur.com/wyez9qw.png" title="Image_1" alt="deco">

Finally, don't forget to click on the honeycomb to activate the brush;
You can now apply your brush by right-clicking with the honeycomb

And press a second time on the honeycomb to deactivate the brush

<img src="https://imgur.com/i350PM5.png" title="Image_1" alt="deco">


# Pattern

A new Pattern has appeared,
```#brick[block,block][length][width][border thickness][offset], ```
This pattern was designed to facilitate the creation of brick grace with different parameters allowing a personalized result.

Example : ```//set #square[10][3][1][5]```

<img src="https://imgur.com/O0HV8vP.gif" title="Image_1" alt="deco">

# Mask

A new Mask has appeared,
```#onground[][]```
It was designed to easily place vegetation.

Example : ```//gmask onground[grass_block][air]```

<img src="https://imgur.com/6NRQElK.png" title="Image_1" alt="deco">

------------------

# Configuration

execute /expbuild reload ; to reload the config file.

default file config :


* [Configuration](https://github.com/LucasT-dev/Expert-Build-Plugin/blob/master/src/main/resources/config.yml)

------------------

# Permission

For the player to use the plugin tools, it is necessary to give him permission: exp.register
This will allow him to create a “BrushBuilder” profile for him.

Depending on the commands you wish to authorize it to execute, the permission is of type: exp.command.<Command Name>
([**see permission command**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Installation))

Same for brushes, the player cannot apply his brush if permission is not assigned to him,
([**see permission brush**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Brush))

For the use of gui, the operation is identical: exp.gui.<gui name>

You have 4 associated permissions:
- exp.gui.main (for opening the main menu)
- exp.gui.leather (for opening gui leather)
- exp.gui.orga (for opening the organic generation gui)
- exp.gui.flower (for opening flower brush mistletoe)

For any information or difficulty raised, do not hesitate to contact me x)
