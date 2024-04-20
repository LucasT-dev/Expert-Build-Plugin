# Expert-Build
Minecraft build plugin, Expert-Build Documentation

* Latest plugin version : 1.18.1.17
* Author : Marodeur
* Contributor : Lison, Cancri
* Depend : FAWE
* [Supported Minecraft version : 1.18, 1.19, 1.20 +](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Supported_Versions)


![LatestVersion](https://img.shields.io/badge/Discord_:-marodeur-purple)
[![Spigot](https://img.shields.io/badge/Download-Expert_Build-purple)](https://www.spigotmc.org/resources/expert-build.110059/)
![LatestVersion](https://img.shields.io/badge/Latest_Version-1.18.1.17-purple)

------------------
<p align="center">
<img src="https://imgur.com/h5NrDR2.png" title="Image_1" alt="deco">
</p>

------------------
# News

-> * [New Brush Clipboard3D, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Brush)

-> * [New command /painting, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Commands)

-> * [Update the clipboard brush, with new functionality, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Brush)

-> * [New Mask, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Mask)


-> * [New inventory, open with left click using honeycomb ! click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Inventory)

------------------

# Supported_Versions

| Mc Version | Supported          |
|------------|--------------------|
| 1.18.x     | :white_check_mark: |
| 1.19.x     | :white_check_mark: |
| 1.20.x     | :white_check_mark: |

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


<img src="https://imgur.com/gywYZSS.png" width="auto" />

The first 2 server arguments define the start and finish block of the gradient. The 3rd argument (optional) indicates the number of blocks to place in the gradient (between 0 and 15; by default 15).
The 4th argument (optional) will force the gradient to pass through the block defined in the middle

------------------

`/1` or `right click using wand` :

Execute //pos1

*permission : exp.command.tool*

------------------
`/2` or `left click using wand` :

Execute //pos2

*permission : exp.command.tool* and *permission : worldedit.selection.pos*

<img src="https://imgur.com/RV9tyb8.gif" width="auto" />

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

WARNING, this command is not compatible with //undo and //redo

Command : /timelapse <start-stop> [ block per tick ]

Example : ```/timelapse start 5```, ```/timelapse stop```

*permission : exp.command.timelapse*

<img src="https://i.imgur.com/62yXy0d.gif" title="Image_1" alt="deco">

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


- clipboard3D : Place a clipboard depending on the direction of the head

  *permission : exp.brush.clipboard3d*

<img src="https://imgur.com/JWKl1zB.gif" title="Image_1" alt="deco">


- clipboards : 
  - < add > : Add a clipboard to the brush
  - < remove > : Remove a clipboard from the brush
  - < removeAll > : Remove all clipboards from the brush
  - < autoRotation > : Paste the clipboard with a random rotation of 0, 90, 180 or 270
  
  *permission : exp.brush.clipboard*


<img src="https://imgur.com/olGd83K.png" title="Image_1" alt="deco">


<img src="https://imgur.com/1NEZOjY.gif" width="auto" />


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


# Mask

A new Mask has appeared,
```#onground[][]```
It was designed to easily place vegetation.

Example : ```//gmask onground[grass_block][air]```

<img src="https://imgur.com/6NRQElK.png" title="Image_1" alt="deco">


# Configuration

execute /expbuild reload ; to reload the config file.

default file config :

````yaml
# EXP-Build

# EXP-Build

build:

  # Version of your configuration file, normally equivalent to the plugin version.
  # For reasons of safety and proper operation, do not modify this value !!!
  version: 1.18.1.17

  # Open or close the schematic transfer
  # Value : Boolean (true or false)
  server_1:
    statserver: false
    name: server_1
  server_2:
    statserver: false
    name: server_2
  server_3:
    statserver: false
    name: server_3
  server_4:
    statserver: false
    name: server_4

  #Max file size to transfer (ko)
  #Default : 500
  #-1 for by-pass
  max_file_size: 500

  #Maximum size brush
  #Default : 50
  #Use integer value
  max_brush_rayon: 50

  #default brush radius
  #Default : 10
  #Use integer value
  default_brush_rayon: 10

  #Default material brush
  #Default : STONE
  #Use block name
  default_material_brush: STONE

  #Default pattern brush
  #Default : 1
  #Id of block
  default_pattern_brush: 1

  #Default biomes brush
  #Default : PLAINS
  #Use Biome name
  default_biome_brush: PLAINS

  #Percentage of air in the flower brush
  #Default : 10
  #Use integer value
  default_air_brush: 10

  #Max distance to apply brush
  #Default : 100
  max_brush_distance: 100


  #Display bezier line on convex selection
  #Default : true
  #Use boolean value (true / false)
  display_bezier_curve: true

  #Type of particle to display
  #Default : FLAME
  #Use particle value (FLAME / SOUL / CLOUD ...)
  particle_bezier_curve_type: FLAME

  #Coefficient divide by the distance of the convex selection
  #Default : 3
  # Use integer value > 0
  coefficient_particle_number: 3


  #Display line on convex selection
  #Default : true
  #Use boolean value (true / false)
  display_convex_line: true

  #Type of particle to display
  #Default : FLAME
  #Use particle value (FLAME / SOUL / CLOUD ...)
  particle_convex_type_line: FLAME

  #Particle spacing
  #Default : 1
  # Use integer value > 0
  spacing_between_particles: 1


  #Repetition period of the asynchronous particle spawn loop 
  #Default : 10
  #Use integer value > 0
  period_particle: 10


  #Player can wand click in air
  #Default : true
  #Use boolean value (true / false)
  wand_click_in_air: true

  #Player can clear selection using shift click with wand
  #Default : false
  #Use boolean value (true / false)
  sihft_click_with_wand: false

  #Log shortcut action
  #Default : true
  #Use boolean value (true / false)
  logShortcut: true

  #Max point number save for Brush line
  #Default : 20
  #use integer value
  max_point_saved: 20

  #Terraforming Tool 1
  #Default : ARROW
  #Use item name
  terraforming_tool_1: SPECTRAL_ARROW

  #Terraforming Tool 2
  #Default : GUNPOWDER
  #Use item name
  terraforming_tool_2: CLAY_BALL


  GOHA:
    #Arm length correction factor
    arm_correction_factor: 1.5

    #Material defined by default, modifiable with the command /goha material <block>
    default_material: STONE

    #Default organic height
    default_orga_height: 50

  #lang
  #Default : en (english)
  #possibilité (fr / en )

  lang: en
````

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
