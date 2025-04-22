
# Cypherfy - Interactive Encryption Sandbox

## Overview

**Cypherfy** is an interactive encryption sandbox built using JavaFX, designed to provide users with an intuitive interface to explore encryption algorithms in a playful, educational way. The application allows users to experiment with various encryption methods and visualize their effects on plaintext.

## Features

- **Custom Window Title Bar**: A custom title bar with buttons for minimizing, maximizing, and closing the window.
- **Smooth Window Animations**: 
  - A smooth minimize and maximize effect, where the window shrinks and moves towards the taskbar when minimized, and returns smoothly when restored.
  - Resizable animations with fade transitions to enhance user experience.
- **Sharp, Modern UI**: The interface features a modern and visually sharp design with angled borders and sleek animations.
- **Encryption Sandbox**: Explore encryption algorithms (under development), with the ability to dynamically interact with and visualize encryption operations.

## Requirements

- Java 11 or higher
- JavaFX SDK

### Clone the Repository

```bash
git clone https://github.com/your-username/cypherfy.git
cd cypherfy
```

### Build and Run the Application

You can build and run the application by using the following steps:

1. **Using IntelliJ IDEA** (or any other Java IDE):
    - Import the project
    - Link JavaFX if needed in your IDE settings.
    - Run the main class: `EntryPoint.java`.

### Key Components

- **CustomTitleBar.java**: Defines the custom title bar for the window, handling window controls like minimize, maximize, and close with smooth animations.
- **Window Animations**: 
    - **Minimize Animation**: Shrinks and moves the window towards the taskbar.
    - **Maximize/Restore Animation**: Scales the window up and restores it to its original size and position.
- **GUI Styles**: Modern, sharp design with a gradient background and dynamic title bar.

## Known Issues

- After multiple minimize and maximize actions, the window may not restore to the correct position due to scaling issues. This will be addressed in future updates.
- The maximize animation may misposition the window if not handled correctly on the first try after restoring from minimize.

## Future Improvements

- Integrate encryption algorithms for the sandbox (e.g., Caesar Cipher, AES).
- Add more polished animations and UI enhancements.
- Handle window state and positioning more robustly to fix maximize issues after several minimize/maximize cycles.
- Package into Java exacutable

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

Note - README written with assistance from generative AI.

