# Expert-Build
Minecraft build plugin, Expert-Build Documentation

* Latest Version : 1.18.1.5
* Author : Marodeur
* Contributor : Lison, Cancri
* Depend : FAWE

![LatestVersion](https://img.shields.io/badge/Discord_:-marodeur-purple)
[![Spigot](https://img.shields.io/badge/Download-Expert_Build-purple)](https://www.spigotmc.org/resources/expert-build.110059/)
![LatestVersion](https://img.shields.io/badge/Latest_Version-1.18.1.5-purple)

------------------
<p align="center">
<img src="https://imgur.com/h5NrDR2.png" title="Image_1" alt="deco">
</p>


------------------
# News

-> * [NewBrush, click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Brush)

-> * [New inventory, open with left click using honeycomb ! click here](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Inventory)

------------------

# Supported Versions

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

* [**CONFIGURATION**](https://github.com/LucasT-dev/Expert-Build-Plugin/tree/master#Configuration)

------------------

# Installation

``` 
Insert plugin in folder plugins,

Use FAWE plugin : 

Java version : 16 minimum
```

[![FAWE](https://img.shields.io/badge/Download-FAWE-red)](https://ci.athion.net/job/FastAsyncWorldEdit/)

------------------

# Usage

## Commands

- **All commands and brush using Fawe editSession, All commands and brush can be //undo, //redo !!!**


`/1` or `right click using wand` :

Execute //pos1

*permission : exptool.use*

------------------
`/2` or `left click using wand` :

Execute //pos2

*permission : exptool.use*

------------------
`shift click` :

Execute //sel

*permission : expsel.use*

------------------
`/terra` aliases `t` :

Give terraforming tools

*permission : exptool.use*

------------------
`/vox` :

Give voxel tools

*permission : exptool.use*

------------------
`/plume` :

Give GoPaint tool

*permission : exptool.use*

------------------
`/silex` :

Give GoBrush tool

*permission : exptool.use*

------------------
`/pos` :

get position location

*permission : exptool.use*

------------------
`/getcommand` :

displays the command of a Block
command by looking at it from a distance

*permission : exptool.use*

<img src="https://imgur.com/eraXDb9.png" title="Image_1" alt="deco">


------------------
`/c` :

shortcut for execute //copy

*permission : exptool.use*

------------------
`/cube` :

set cuboid selection mode

*permission : exptool.use*

------------------
`/poly` :

set polygone selection mode

*permission : exptool.use*

------------------
`/convex` aliases `/conv`:

set convex selection mode

*permission : exptool.use*

------------------
`/repeater` or `/rt` :

preset a tick to the repeater when you set it

*permission : exptool.use*

------------------
`/schemtrans` :

transfer schematic on other sftp server

*permission : expschemtrans.use*

------------------
`/timelapse` :

launch automatic timelapse

*permission : exptimelapse.use*

------------------
`/perlin` :

generate perlin, predefined for the hair

*permission : expperlin.use*

------------------
`/autocb` :

pastes all commands separated by a semicolon into a command block. Then execute the command by looking at the command block
this will allow when the cb to automatically include the command

*permission : expautocb.use*

------------------
`/autoflip` :

Use your selection as a plane of symmetry

*permission : expautoflip.use*

------------------
`/expbuild` aliases `/exp` :

general command for reload config, need help, and more

*permission : expbuild.use*


## Brush

------------------
`/flower` aliases `fw`:

argument : `melt`, `lift`, `fill`, `smooth`, `floatclean`, `blendball` (`bb`) `cliboards`, `cube`, `sphere`, `2dCube`, `drain`, `line`, `none`, `overlay`, `spike`, `biome`, `erodeblend` (`eb`)

Defaut tool : CLAY_BALL, SPECTRAL_ARROW and HONEYCOMB

General brush command,

- material : set pattern

- radius : set radius


*permission : expflower.use*


- melt, fill, fill, smooth, floatclean, erodeblend : voxel brush,
  *permission : experode.use*


- clipboards : save several selections and place them randomly,
  *permission : expclipboards.use*


- cube : cube brush

  *permission : expcube.use*


- 2dCube : place a cube with an angle corresponding to the angle that the player has

  *permission : exp2dcube.use.use*

<img src="https://imgur.com/EqbP1cz.png" title="Image_1" alt="deco">

- sphere : sphere brush
  *permission : expsphere.use*


- line : line brush

  *permission : expline.use*


- overlay : overlay brush

  *permission : expoverlay.use*


- drain : drain brush

  *permission : expdrain.use*

- drain : drain brush

  *permission : experaser.use*


- biome : biome brush

  *permission : expbiome.use*


- spike : generates a spike from your position to the brush point

  *permission : expspike.use*

<img src="https://imgur.com/zAzYBG5.png" title="Image_1" alt="deco">

- blendball : blend terrain

  *permission : expblendball.use*


- updatechunk : update chunk

  *permission : expupdatechunk.use*

- none : disable brush

  *permission : expnone.use*

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


# Configuration

execute /expbuild reload ; to reload the config file.

default file config :

````yaml
# EXP-Build

build:
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

  #Player can wand click in air
  #Default : true
  #Use boolean value (true / false)
  wand_click_in_air: true

  #Player can clear selection using shift click with wand
  #Default : true
  #Use boolean value (true / false)
  sihft_click_with_wand: false

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