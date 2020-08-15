# Tower Defence
A basic 2D tower defence game written in Java using the LibGDX framework.

## Goal
The goal of this project was to write a basic framework for the Java LibGDX framework. It includes features such as rendering, game events, camera controlling, projectiles and much more! The tower defence game was created to drive the force of the framework development in a fun way. The idea is that the game could be transfered to something completely else, while still retaining basic game functionality. 

## Setup
Clone and open the complete repository in an IDE. Compile and run the DesktopLauncher in the 'desktop' source.

## Features
A short summary of all the features can be found below.

### Core
* Rendering engine
* TMX map loader with tile based logic
* Camera controller
* Data repository, deserialized automaticly from JSON files using GSON
* Thread pooling for things such as projectiles
* Game event system with priority (ex: player death)

### Game
* Entity order system, force certain entities to follow game orders (ex: walk, find and kill) 
* Following projectiles
* Game HUD

## Images
![Tower Defence](images/demo0.png)

![Tower Defence](images/demo1.png)

![Tower Defence](images/demo2.png)

![Tower Defence](images/demo3.png)



