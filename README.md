# Expert-Build-Doc
Minecraft build plugin, Expert-Build Documentation

* Latest Version : 1.18.4
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

| Mc Version | Supported         |
|-------|----------------------  |
| 1.18  | :white_check_mark:     |
| 1.19  | :white_check_mark:     |
| 1.20  | :white_check_mark:     |  


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

  #lang
  #Default : en (english)
  #possibilité (fr / en )

  lang: en

  message:
    en:
      main:
        plugin_enable: Plugin enable
        plugin_disable: Plugin disable

        listeners_load: Listeners load
        commands_load: Commands load
        config_load: Config load
        gui_load: Gui registered
        brush_load: brush registered
        schematic_transfert_file: Transfert Schematic file Server'num'.yml create

        checking_update: Checking for updates...
        not_new_update: There is not a new update available
        new_update_available: There is a new update available, you are running on version 'v1' , version 'v2' is available
        unable_check_update: Unable to check for updates 'error'

      brush:
        brush_enable: Brush 'brush' enable
        brush_enable_with_radius: Brush 'brush' enable, radius = 'radius'
        brush_enable_with_material: Brush 'brush' enable, pattern = 'pattern'
        brush_enable_with_radius_pattern: Brush 'brush' enable, radius = 'radius', pattern = 'pattern'
        brush_disable: Brush disable
        brush_registered: Brush 'brush' registered
        material_set: Material set
        radius_set: Radius set

        player_registered: Player 'player' registered
        builder_profile_registered: Builder profile registered
        player_already_registered: You have already registered

        point_add: Point add at ('point')
        point_not_save: Limite size, point not save

      permission:
        dont_perm: You don't have the permission
        console_not_execute_cmd: Console cannot use this command

      selection:
        set_pos_1: Pos 1 set to ('pos')
        set_pos_1_with_area: Pos 1 set to ('pos') ('area')
        set_pos_2: Pos 2 set to ('pos')
        set_pos_2_with_area: Pos 2 set to ('pos') ('area')
        add_vertex_pos: Vertex add ('pos')
        selection_clear: Selection clear
        set_selection: Selection 'selection' set

        block_modified: a'num' block have been modified
        block_modified_with_time: a'num' block have ben modified ('num2' blocks/s)

      error:
        error_region: Region error n° 'num' ! 'error'
        error_selection: §4Error ! IncompleteRegionException; Use 'selection' selection and complete selection
        error_brushbuilder: §4Error ! 'player' has not been registered as a builder, for this he must be an operator or have the expertbuild.use permission
        error_jsch_exception: Error JSch-Exception, please see console
        error_sftp_exception: Error Sftp-Exception, please see console
        file_configuration_error: Configuration files in ExpertBuild folder is wrong or corrupt; Repair the file or delete all the files, so that the plugin recreates them
        incomplete_selection: Incomplete Selection; Particle Stopped, this is normal
        invalid_number: Invalid number format
        invalid_number_integer: Invalid number format, use integer
        invalid_number_integer_upper_0: Invalid number format, upper 0
        invalid_material: Invalid pattern
        invalid_material_set: Invalid pattern, pattern set 'pattern'
        invalid_biome: Invalid biome
        invalid_biome_set: Invalid biome, biome set 'biome'

      tools:
        give_tool: The 'tool' tools given

      commands:
        use: Use 'cmd'
        pregeneration: pregeneration

        file_not_exist: This file 'file' doesn't exist
        file_too_large: The file is too large, Increase the parameter to transfer larger files
        server_off: Server unregistered
        unknown_server: Unknown server, please try again with valid server name
        transfert: Tranfert running
        dont_restart: Connection to the server... please, don't reload ExpertBuild, don't restart the server
        succes_transfert: Transfer finish, successful upload file Imported-'file' on server 'server'
        transfert_log: The file 'file' Exported with succes by 'player' on 'server'

    fr:
      main:
        plugin_enable: Plugin on
        plugin_disable: Plugin off

        listeners_load: Listeners chargé
        commands_load: Commands chargé
        config_load: Config chargé
        gui_load: Gui enregistré
        brush_load: brush enregistré
        schematic_transfert_file: Transfert Schematic file Server'num'.yml create

        checking_update: Check updates...
        not_new_update: Il n'y a pas de nouvelle mise à jour disponible actuellement
        new_update_available: Une nouvelle mise a jour est disponible, vous utilisez la version 'v1' , version 'v2' disponible
        unable_check_update: Inpossible de check les versions disponible 'error'

      brush:
        brush_enable: Brush 'brush' activé
        brush_enable_with_radius: Brush 'brush' activé, rayon = 'radius'
        brush_enable_with_material: Brush 'brush' activé, pattern = 'pattern'
        brush_enable_with_radius_pattern: Brush 'brush' activé, rayon = 'radius', pattern = 'pattern'
        brush_disable: Brush désactivé
        brush_registered: Brush 'brush' enregistré
        material_set: Material défini
        radius_set: Radius défini

        player_registered: Joueur 'player' enregistré
        builder_profile_registered: Profile Builder enregistré
        player_already_registered: Vous êtes déjà enregistré

        point_add: Point ajouté à ('point')
        point_not_save: Taille limite, point non sauvegardé

      permission:
        dont_perm: Vous n'avez pas la permission
        console_not_execute_cmd: La console ne peux pas exécuter cette commande

      selection:
        set_pos_1: Pos 1 défini à ('pos')
        set_pos_1_with_area: Pos 1 défini à ('pos') ('area')
        set_pos_2: Pos 2 défini à ('pos')
        set_pos_2_with_area: Pos 2 défini à ('pos') ('area')
        add_vertex_pos: Vertex ajouté ('pos')
        selection_clear: Selection effacé
        set_selection: Selection 'selection' défini

        block_modified: a'num' block ont été modifé
        block_modified_with_time: a'num' block ont été modifé ('num2' blocks/s)

      error:
        error_region: Erreur région n° 'num' ! 'error'
        error_selection: §4Error ! IncompleteRegionException; Utilisez 'selection' selection et completez votre selection
        error_brushbuilder: §4Error ! 'player' n'eest pas enregistré en tant que Builder, il doit être opérateur ou avoir la permission expertbuild.use
        error_jsch_exception: Error JSch-Exception, voir console
        error_sftp_exception: Error Sftp-Exception, voir console
        file_configuration_error: Le fichier de configuration dans le dossier ExpertBuild est erroné ou corrompu; Réparez le fichier ou supprimez tous les fichier pour que le plugin les re-créer
        incomplete_selection: Selection incomplete; Particule stoppé, ceci est normal
        invalid_number: Format du nombre invalide
        invalid_number_integer: Format du nombre invalide, utilisé un chiffre
        invalid_number_integer_upper_0: Format du nombre invalide, le chiffre doit être suppérieur à 0
        invalid_material: Pattern invalide
        invalid_material_set: Pattern invalide, pattern défini 'pattern'
        invalid_biome: Biome invalide
        invalid_biome_set: Biome invalide, biome défini 'biome'

      tools:
        give_tool: L' 'tool' donné

      commands:
        use: Utilisez 'cmd'
        pregeneration: pré-génération

        file_not_exist: Le fichier 'file' n'existe pas
        file_too_large: Le fichier est trop volumineux, Augmentez le parametre de tranfert dans le fichier de configuration
        server_off: Serveur non activé
        unknown_server: Serveur inconnu, ré-essayé avec un nom de server valide
        transfert: Tranfert en cours
        dont_restart: Connection au serveur... svp, ne re-chargé pas ExpertBuild, ne re-démarré pas le serveur
        succes_transfert: Transfer fini, Fichier envoya avec succès Imported-'file' sur le serveur 'server'
        transfert_log: Le fichier 'file' exporté avec succes par 'player' sur le serveur 'server'
````