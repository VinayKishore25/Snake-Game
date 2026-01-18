# 🐍 Snake Game - Browser Edition

A classic Snake game written in **Java**, deployed to the web using **CheerpJ 3.0** and hosted on **GitHub Pages** for free, permanent availability.

## 🎯 Quick Links

- **Play Online**: Deploy following [GITHUB_SETUP.md](GITHUB_SETUP.md)
- **Full Documentation**: See [DEPLOYMENT.md](DEPLOYMENT.md)
- **Local Testing**: Run locally with Java
- **Source Code**: Clean, modular Java architecture

## 🚀 One-Command Deployment

After following [GITHUB_SETUP.md](GITHUB_SETUP.md):

```powershell
# Your game will be live at:
https://YOUR_USERNAME.github.io/snake-game
```

## 📁 Deployment Package Contents

Everything needed is in the `docs/` folder:

```
docs/
├── index.html        (Beautiful web interface with CheerpJ)
└── SnakeGame.jar     (Compiled Java game in single JAR)
```

**That's it!** GitHub Pages serves these files automatically.

## 🎮 How to Play

| Control        | Action     |
| -------------- | ---------- |
| ⬆️ UP Arrow    | Move Up    |
| ⬇️ DOWN Arrow  | Move Down  |
| ⬅️ LEFT Arrow  | Move Left  |
| ➡️ RIGHT Arrow | Move Right |

**Objective**: Eat apples 🍎 to grow longer and achieve the highest score!

## 💻 Technical Stack

| Component   | Technology            |
| ----------- | --------------------- |
| Game Logic  | Java 8+               |
| Compilation | Java Compiler (javac) |
| Packaging   | JAR (Java Archive)    |
| Web Runtime | CheerpJ 3.0           |
| Frontend    | HTML5 + CSS3          |
| Hosting     | GitHub Pages (Free)   |

## 📚 Project Structure

```
Snake Game/
│
├── 📂 src/main/java/           ← Source code (5 Java files)
│   ├── App.java                (Entry point)
│   └── game/
│       ├── Start.java          (Window setup)
│       ├── Board.java          (Game canvas)
│       ├── Snake.java          (Snake logic)
│       └── Apple.java          (Apple logic)
│
├── 📂 build/                    ← Build artifacts
│   ├── classes/                (Compiled .class files)
│   ├── SnakeGame.jar           (Executable JAR)
│   └── manifest.txt            (JAR manifest)
│
├── 📂 docs/                     ← GitHub Pages deployment
│   ├── index.html              (Web interface)
│   └── SnakeGame.jar           (Game executable)
│
├── GITHUB_SETUP.md             ← Deploy to GitHub Pages ⭐
├── DEPLOYMENT.md               ← Complete guide
└── README.md                    ← This file
```

## 🛠️ Build Instructions

### Prerequisites

- Java Development Kit (JDK 8+)
- PowerShell or Command Prompt

### Compile & Package

```powershell
cd "Snake Game"

# Compile source code
javac -d build/classes src/main/java/App.java src/main/java/game/*.java

# Create JAR file
jar cfm build/SnakeGame.jar build/manifest.txt -C build/classes .

# Copy to deployment folder
cp build/SnakeGame.jar docs/SnakeGame.jar
```

## ▶️ Running Locally

### Desktop Version

```powershell
java -cp build/SnakeGame.jar App
```

### Browser Version

1. Open `docs/index.html` in any web browser
2. Game will load via CheerpJ
3. Start playing!

## 🌐 Why CheerpJ for Deployment?

| Feature              | Benefit                             |
| -------------------- | ----------------------------------- |
| **No Server Needed** | Static file hosting on GitHub Pages |
| **Fast Loading**     | JAR runs directly in browser        |
| **Cross-Platform**   | Works on Windows, Mac, Linux        |
| **Free Forever**     | GitHub Pages costs nothing          |
| **Permanent URL**    | Your game stays accessible          |
| **Portfolio Ready**  | Shows modern deployment skills      |

## 📋 Deployment Checklist

- ✅ Source code organized in `src/main/java/`
- ✅ All classes compiled into single `SnakeGame.jar`
- ✅ JAR copied to `docs/` folder
- ✅ `index.html` with CheerpJ integration ready
- ✅ `.gitignore` configured
- ✅ Documentation complete
- ✅ Ready for GitHub Pages

## 🎓 Portfolio Value

This project demonstrates:

- ✓ Object-Oriented Java Design
- ✓ Game Development (Logic, Collision Detection)
- ✓ GUI Programming (Swing Framework)
- ✓ Build Tools (JAR, Compilation)
- ✓ Modern Web Deployment
- ✓ Cross-Platform Development
- ✓ Professional Documentation
- ✓ Git Version Control
- ✓ Cloud Hosting (GitHub Pages)

## 🔒 Security Notes

- No server-side processing needed
- Game runs entirely in browser
- JAR is signed by CheerpJ
- No personal data collected
- Safe for any audience

## 🐛 Troubleshooting

| Issue            | Solution                                                |
| ---------------- | ------------------------------------------------------- |
| Game won't load  | Check browser console (F12), ensure JAR exists in docs/ |
| Keys not working | Click game area first, ensure focus                     |
| Slow performance | This is expected for web Java, normal behavior          |
| Build fails      | Ensure JDK is installed and in PATH                     |

## 📖 Next Steps

1. **Deploy to GitHub**:
   - Follow [GITHUB_SETUP.md](GITHUB_SETUP.md)
   - Takes 5-10 minutes

2. **Customize**:
   - Modify colors in `index.html`
   - Change game speed in `Board.java` (DELAY constant)
   - Add features to game logic

3. **Share**:
   - Post your GitHub Pages URL
   - Add to portfolio
   - Share on social media

## 📞 Support

For detailed guides, see:

- 📄 [GITHUB_SETUP.md](GITHUB_SETUP.md) - Step-by-step GitHub Pages setup
- 📄 [DEPLOYMENT.md](DEPLOYMENT.md) - Technical deployment details

## 📄 License

This project is open source. Feel free to fork, modify, and share!

---

**Status**: ✅ Ready for Deployment

## Technologies

This project was made with the following core libraries:

- java.awt
  Provides classes for graphics, colors, fonts, and GUI elements.Used for rendering the game graphics and handling dimensions, fonts, and colors.
  1.Color
  2.Dimension
  3.Font
  4.FontMetrics
  5.Graphics
  6.Image
  7.Toolkit
- javax.swing
  Provides classes for building GUI applications in Java.Used for creating the game window and components.
  1.JFrame
  2.JPanel
  3.Timer
  4.ImageIcon
- java.awt.event
  Provides classes for handling events like key presses and timer actions.Used for game controls and timer-based movement.
  1.ActionEvent
  2.ActionListener
  3.KeyAdapter
  4.KeyEvent

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

![Start game](public/start-game.png)

![preview game](public/middle-game.png)

![Game Over](public/game-over.png)
