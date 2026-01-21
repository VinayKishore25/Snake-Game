# Snake Game

A classic Snake game implementation in Java using Swing for GUI. Control the snake, eat apples to grow, avoid collisions, and try to achieve the highest score!

## Features

- **Simple Controls**: Use arrow keys to control the snake's direction
- **Score Tracking**: Score increases with each apple eaten
- **Visual Design**: Red head, white body segments with borders, and green apples
- **Restart Functionality**: Press SPACE to restart after game over
- **Collision Detection**: Game ends if snake hits walls or itself

## How to Run

### Step 1: Compile the Code

Navigate to the project directory and compile all Java files:

```bash
cd "d:\Programming\Java Projects\Snake Game"
javac -d bin src\App.java src\game\*.java
```

### Step 2: Run the Game

Execute the compiled application:

```bash
java -cp bin App
```

### Step 3: Play!

- Use **Arrow Keys** (←↑→↓) to control the snake
- Eat the **green apples** to grow and increase your score
- Avoid hitting the **walls** or your **own body**
- Press **SPACE** to restart after game over

## How the Code Works

### Architecture Overview

The game follows an object-oriented design with four main components:

#### 1. **App.java** (Main Entry Point)

- Creates the main JFrame window using EventQueue
- Initializes and displays the Start frame
- Entry point: `public static void main(String[] args)`

#### 2. **Start.java** (Game Window Setup)

- Extends JFrame to create the main game window
- Sets window properties: title, size, close operation
- Adds the Board (game panel) to the frame
- Configures window to be non-resizable and centered on screen

#### 3. **Board.java** (Game Logic & Rendering)

The core game controller that manages:

**Game State:**

- Board dimensions: 400x400 pixels
- Dot size: 10x10 pixels (each snake segment and apple)
- Game speed: Timer with 140ms delay between updates
- Direction flags: leftDirection, rightDirection, upDirection, downDirection
- Game status: inGame flag and score counter

**Key Methods:**

- `initBoard()`: Initializes game components, sets up key listener, creates Snake and Apple
- `paintComponent(Graphics g)`: Renders all game elements each frame
- `drawObjects(Graphics g)`: Draws snake (red head, white body with borders) and apple (green with border)
- `actionPerformed(ActionEvent e)`: Called by Timer every 140ms to:
  - Check apple collision → grow snake and increase score
  - Check wall/self collision → end game
  - Move snake in current direction
- `showGameOver(Graphics g)`: Displays game over screen with score and restart instructions
- `restartGame()`: Resets all game state and creates new Snake/Apple objects
- `TAdapter` (inner class): KeyListener that handles arrow key inputs and SPACE for restart

**Visual Design:**

- Each square is drawn with a 1-pixel border for separation
- Snake head: Red fill with dark red border
- Snake body: White fill with gray border
- Apple: Green fill with dark gray border

#### 4. **Snake.java** (Snake Entity)

Manages the snake's state and behavior:

**Properties:**

- `dots`: Current length of snake
- `x[]`, `y[]`: Arrays storing coordinates of each segment
- `DOT_SIZE`: Size of each segment (10 pixels)
- `ALL_DOTS`: Maximum possible segments

**Methods:**

- `initSnake()`: Creates initial 3-segment snake at position (50, 50)
- `move()`: Updates all segment positions:
  - Shifts each segment to the position of the segment in front
  - Moves head based on direction flags
- `checkCollision()`: Returns true if:
  - Snake hits itself (head collides with body after 4 segments)
  - Snake hits wall (coordinates outside board boundaries)
- `checkAppleCollision()`: Checks if head position matches apple position
- `grow()`: Increases snake length by 1

#### 5. **Apple.java** (Apple Entity)

Manages apple spawning:

**Properties:**

- `appleX`, `appleY`: Current apple coordinates
- `RAND_POS`: Grid size (29) for random positioning

**Methods:**

- `locateApple()`: Generates random position on 10-pixel grid
  - Random x: 0 to 290 (29 positions × 10 pixels)
  - Random y: 0 to 290 (29 positions × 10 pixels)

### Game Loop Flow

1. Timer fires every 140ms
2. `actionPerformed()` is called
3. Check if snake ate apple → grow and relocate apple
4. Check collisions → end game if collision detected
5. Move snake one step in current direction
6. Call `repaint()` to render updated state
7. `paintComponent()` draws all elements
8. Repeat from step 1

### Collision Detection Logic

- **Wall Collision**: Head x/y coordinates are outside 0-400 range
- **Self Collision**: Head coordinates match any body segment coordinates (after segment 4)
- **Apple Collision**: Head coordinates exactly match apple coordinates

### Coordinate System

- Origin (0,0) is at top-left corner
- X increases rightward (0 to 400)
- Y increases downward (0 to 400)
- All positions are multiples of 10 (grid-aligned)

## Requirements

- Java Development Kit (JDK) 8 or higher
- No external dependencies required - uses standard Java libraries (java.awt, javax.swing)

## Project Structure

```
Snake Game/
├── src/
│   ├── App.java          # Main entry point
│   └── game/
│       ├── Start.java    # Game window setup
│       ├── Board.java    # Game logic and rendering
│       ├── Snake.java    # Snake entity
│       └── Apple.java    # Apple entity
├── bin/                  # Compiled .class files
└── README.md
```
