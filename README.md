
# Cypherfy: Interactive Encryption Sandbox

## Project Overview

Cypherfy is a Java-based interactive encryption sandbox that allows users to explore cryptographic algorithms via a user-friendly JavaFX graphical interface. Currently, it supports the Caesar Cipher with plans to expand to other algorithms like Atbash and Vigenère in the future.

Cypherfy is designed with a smooth, visually appealing UI and intuitive interactions, making it both educational and practical for exploring encryption logic. Users can input text, view the transformation in real-time, and enjoy polished transitions between components.

---

## Key Features

### ✅ Implemented Cryptographic Algorithm

- **Caesar Cipher**: Shifts each letter in the plaintext by a fixed number of positions in the alphabet.

### 🎨 Graphical Interface

- **Title Screen**: Custom-styled entry screen with branding and logo.
- **Menu Screen**: Navigation to select available ciphers.
- **Encryption Screens**:
  - Live encryption with visual feedback.
  - Fade transitions between screens.
  - Fully resizable UI with responsive design.
- **Custom Buttons & Shapes**: CSS-styled parallelogram buttons with unique shape customization.
- **Custom Fonts & Icons**: Stylish embedded fonts and project-themed assets.
  
### 🖌️ Theme Selection with CSS

- **Dynamic Theme Support**: Users can choose different UI themes that can be customized via CSS.
  - Multiple themes available for a personalized look and feel.
  - Easily adjustable color schemes, button styles, and font options.
  - Ability to implement additional themes by modifying CSS files.

### 🧠 Smart Input Handling

- **Text Input Validation**:
  - Only allows alphabetic characters (A–Z, a–z).
  - Character limit set to 15 for visualization clarity.
- **Real-time Feedback**:
  - Input field gently flashes red on invalid entry (e.g., pasting non-letter text or exceeding limit).
  - Smooth fade-in/fade-out animation using JavaFX `Timeline`.
- **Keyboard Smart Filtering**:
  - System commands (e.g., Ctrl+A) and Enter key are ignored by the flash mechanism.
  - Flash only triggers on truly invalid input, not UI shortcuts.

### 🧱 Modular Codebase

- **ScreenManager**: Handles smooth scene creation and transitions.
- **Separation of Concerns**: Cipher logic separated from UI logic for clarity and testability.
- **CSS Customization**: Styles are easily overridden using JavaFX’s built-in theming support.

---

## Current Status

### ✅ Completed Features

- **Caesar Cipher** fully functional.
- Full JavaFX integration with:
  - Title screen and menu system.
  - Custom-styled buttons and animations.
- Text input filtering and flash effect.
- Template scene system via `ScreenManager`.
- Basic theme selection with CSS support.

### 🔜 Planned Features

- **Atbash Cipher**: Implement the classic substitution cipher.
- **Vigenère Cipher**: Fully functional cipher with custom key support.
- **Encryption History**: Track and display past operations.
- **File Import/Export**: Load input from files and save encrypted output.
- **Advanced GUI Features**:
  - Font/theme customization.
  - Expanded visual feedback for encryption steps.

---

## Getting Started

### 📦 Prerequisites

- **JDK 17 or later**
- **JavaFX SDK** (e.g., from [OpenJFX](https://openjfx.io/))

### 🚀 Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/cypherfy.git
   cd cypherfy
   ```

2. **Set Up Dependencies** (for Maven projects):
   ```xml
   <dependency>
       <groupId>org.openjfx</groupId>
       <artifactId>javafx-controls</artifactId>
       <version>17.0.1</version>
   </dependency>
   ```

3. **Run the Application**:
   - Open in IntelliJ IDEA, Eclipse, or your IDE of choice.
   - Run `EntryPoint.java`.

   Or use Maven:
   ```bash
   mvn javafx:run
   ```

4. **Run Unit Tests**:
   ```bash
   mvn test
   ```

---

## 🧪 Testing

- **JUnit** tests included for Caesar cipher.
- More tests planned as additional ciphers and features are completed.

---

## 🤝 Contributing

I welcome contributions! Fork the repo, create a new branch, and submit a pull request.

### Contribution Guidelines

- Follow Java coding conventions.
- Include unit tests for new features.
- Update this README and relevant documentation if needed.

---

## 📄 License

Licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 🙌 Acknowledgments

- **JavaFX** – UI Framework.
- **JUnit** – Testing framework.
- **OpenJFX Community** – For maintaining JavaFX.
- **OpenAI ChatGPT** – Assistance with documentation structuring and writing.
