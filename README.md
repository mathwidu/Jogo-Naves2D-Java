# 2D Space Shooter Game

This project is a simple 2D space shooter written in Java using the Swing
API. You control a spaceship, dodge enemies and shoot them down while flying
through space.

## Building

The project does not require any external dependencies. You can compile it
using the `javac` command included with the JDK. From the repository root run:

```bash
mkdir -p bin
javac -d bin NavesJogo/Meujogo/Container.java NavesJogo/Meujogo/modelo/*.java
cp -r NavesJogo/res bin/res
```

This will place compiled classes in the `bin` directory and copy resource files
required at runtime.

## Running

Run the game with:

```bash
java -cp bin Meujogo.Container
```

(The game uses Swing, so it must be run in an environment with a graphical
interface.)

## Controls

- **Arrow keys** or **WASD** – Move the spaceship
- **Space** – Shoot
- **T** – Activate turbo speed for a short time

Enjoy blasting enemy ships!
