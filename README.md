# Expert-Build-Doc
Minecraft build plugin, Expert-Build Documentation

* Latest Version : 1.18.2-Alpha
* Author : Marodeur
* Depend : FAWE

[![Discord](https://img.shields.io/discord/268444645527126017.svg?label=&logo=discord&logoColor=ffffff&color=7389D8&labelColor=122a7a)](https://discord.gg/wNxF7ud)
[![GitHub](https://img.shields.io/github/watchers/Marodeurun/Expert-Build-Doc?style=social)](https://github.com/Marodeurun/Expert-Build-Doc)
[![Spigot](https://img.shields.io/badge/Download-Expert_Build-blue)](https://www.spigotmc.org/resources/expert-build.110059/)
![LatestVersion](https://img.shields.io/badge/Latest_Version-1.18.2.Alpha-blue)

------------------
<p align="center">
<img src="https://imgur.com/h5NrDR2.png" title="Image_1" alt="deco">
</p>

------------------
# Installation

``` 
Insert plugin in folder plugins,

Use FAWE plugin : 
```

[![FAWE](https://img.shields.io/badge/Download-FAWE-red)](https://ci.athion.net/job/FastAsyncWorldEdit/)

## Supported Versions

| Mc Version | Supported          |
| -------    | ------------------ |
| 1.18.x     | :white_check_mark: |


------------------
# Usage

###All commands and brush using Fawe editSession, All commands and brush can be //undo, //redo !!!


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
`/organic` or `orga`:

human organic generator, use honeycomb menu

pregenerate particle using : /organic pregen

generate block using : /organic generate

*permission : exporga.use*

<img src="https://imgur.com/4Ff6Fxj.png" title="Image_1" alt="deco">

<img src="https://imgur.com/iUUfUkz.png" title="Image_1" alt="deco">

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
`/flower` aliases `fw`:

argument : `melt`, `lift`, `fill`, `smooth`, `floatclean`, `blendball`, `cliboards`, `cube`, `sphere`, `2dCube`, `drain`, `line`, `none`, `overlay`, `spike`, `biome`

General brush command,

- material : set pattern

- radius : set radius


*permission : expflower.use*


- melt, fill, fill, smooth, floatclean : voxel brush,
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


- biome : biome brush

  *permission : expbiome.use*


- spike : generates a spike from your position to the brush point

  *permission : expspike.use*

<img src="https://imgur.com/zAzYBG5.png" title="Image_1" alt="deco">

- blendball : blend terrain

  *permission : expblendball.use*

- updatechunk : update chunk

  *permission : expupdatechunk.use*

- flower : flower brush using flower gui

  *permission : expflower.use*

<img src="https://imgur.com/i350PM5.png" title="Image_1" alt="deco">

- none : disable brush

  *permission : expnone.use*


------------------
`/expbuild` aliases `/exp` :

general command for reload config, need help, and more

*permission : expbuild.use*

------------------

If you are an operator on the server, you will still be able to execute all commands

------------------
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
  sihft_click_with_wand: true

  #Max point number save for Brush line
  #Default : 20
  #use integer value
  max_point_saved: 20

  #Terraforming Tool 1
  #Default : ARROW
  #Use item name
  terraforming_tool_1: ARROW

  #Terraforming Tool 2
  #Default : GUNPOWDER
  #Use item name
  terraforming_tool_2: GUNPOWDER


  GOHA:
    #Arm length correction factor
    arm_correction_factor: 1.5

    #Material defined by default, modifiable with the command /goha material <block>
    default_material: STONE

    #Default organic height
    default_orga_height: 50
````