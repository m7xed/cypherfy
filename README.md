
# Cypherfy: Interactive Encryption Sandbox

## Project Overview

Cypherfy is an advanced Java-based interactive encryption sandbox that allows users to explore and experiment with a variety of cryptographic algorithms through a user-friendly JavaFX graphical interface. The application currently supports several classical ciphers including Caesar Cipher, Atbash Cipher, and ROT13. It is designed for both learning and practical use, enabling users to apply different encryption techniques to text input and view the corresponding transformations.

The project emphasizes a smooth, aesthetically pleasing user experience with an intuitive interface and seamless transitions between different encryption methods. In the future, Cypherfy will incorporate additional cipher algorithms such as the Vigenère Cipher, and will expand on the core functionality with features like encryption history and file import/export capabilities.

## Key Features

### Implemented Cryptographic Algorithms:
- **Caesar Cipher**: This cipher shifts each letter in the plaintext by a fixed number of positions in the alphabet. It is one of the simplest and oldest ciphers.
- **Atbash Cipher**: A substitution cipher where each letter of the alphabet is mapped to its reverse counterpart (e.g., 'A' becomes 'Z', 'B' becomes 'Y').
- **ROT13**: A special case of the Caesar Cipher, shifting letters by 13 positions, frequently used in obscuring text in casual settings.

### GUI Screens:
- **Title Screen**: The entry point to the application, displaying the project logo and providing access to other parts of the program.
- **Menu Screen**: Provides navigation buttons for the user to select which cipher they wish to interact with, including the Atbash Cipher, Caesar Cipher, and Vigenère Cipher (forthcoming).
- **Encryption Screen**: A work-in-progress screen that will allow users to input text, apply the selected cipher, and view the encrypted output.
- **Responsive Design**: The interface is fully resizable and adapts to different screen sizes, with minimum and maximum width/height constraints set for a consistent experience.

### Core Functionalities:
- **Cipher Application**: The user can enter text and select one of the available ciphers to encrypt the input.
- **Smooth Scene Transitions**: JavaFX's `FadeTransition` provides seamless scene changes, enhancing the user experience as they navigate between the title screen, menu, and cipher screens.
- **Custom Fonts & Icons**: The application features custom fonts (CypherfyFont.otf) and logos to align with the branding and aesthetic goals of the project.
- **Extensible GUI**: The core GUI framework allows for easy expansion with additional ciphers and features in the future.

### Algorithm Implementations:
- **CaesarCipher**: Implements the Caesar cipher, allowing users to specify a key (shift value) for text encryption.
- **AtbashCipher**: Implements the Atbash cipher, reversing the alphabet for substitution-based encryption.
- **ROT13**: Implements ROT13 encryption, which shifts each letter of the alphabet by 13 positions.
- **Screen Management**: `ScreenManager` class handles the creation of scenes and application of CSS styles, ensuring a cohesive and professional user interface design.

### Testing:
- **JUnit Tests**: Unit tests for the Caesar and Atbash ciphers have been developed to validate their correctness. These tests ensure that the encryption and decryption functions work as expected under various input conditions.

## Current Status

### Completed Features:
- **Cipher Implementations**:
  - **Caesar Cipher**: Fully implemented and operational.
  - **Atbash Cipher**: Fully implemented and operational.
  - **ROT13**: Semi implimented and non-funcitonal.
- **Graphical User Interface**:
  - Title screen created, including a custom font and project logo.
  - Menu screen with functional buttons for selecting different ciphers.
  - Smooth scene transitions with fade-in/fade-out animations.
  - Custom styling for buttons and labels using CSS.
- **Scene Management**:
  - `ScreenManager` handles transitions between scenes with smooth animations.
  - Template scenes for different UI components (title screen, menu screen, etc.).
- **Test Coverage**:
  - Basic unit tests written for Caesar and Atbash ciphers using JUnit.

### Features to Be Implemented:
- **Vigenère Cipher**: The Vigenère cipher is planned for implementation, which will require handling dynamic keys of varying lengths.
- **ROT13**: The ROT13 cipher is partially complete, but requires finishing development
- **Encryption Screen**: Development of the encryption screen where users can input text and apply the selected cipher. This will include handling the input, processing the encryption, and displaying the output.
- **Advanced Features**:
  - **Encryption History**: Implement a feature that allows users to track their encryption operations and results.
  - **File Import/Export**: Users will be able to import text files for encryption and export the encrypted text.
  - **Customization Options**: Users may have the ability to adjust font sizes, themes, or select from multiple fonts.
  - **Key Management**: Consider adding functionality for custom keys, especially for ciphers that require them (e.g., Vigenère).

### Known Issues:
- **Encryption Screen**: The screen for user input and cipher selection is currently a placeholder.
- **Vigenère Cipher**: The Vigenère cipher has not yet been implemented, but its structure is planned and will be integrated once the encryption screen is functional.

## Getting Started

### Prerequisites:
- **Java Development Kit (JDK)**: The project requires JDK 17 or higher.
- **JavaFX**: JavaFX is required for the graphical interface. It can be downloaded and configured from [OpenJFX](https://openjfx.io/).

### Installation:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/cypherfy.git
   cd cypherfy
   ```

2. **Install Dependencies**:
   Ensure that JavaFX is set up correctly in your project. If you are using Maven, you can include the JavaFX dependencies as follows:
   ```xml
   <dependency>
       <groupId>org.openjfx</groupId>
       <artifactId>javafx-controls</artifactId>
       <version>17.0.1</version>
   </dependency>
   ```

3. **Run the Application**:
   Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, etc.), and run the `EntryPoint.java` class. This will launch the JavaFX application window.

   Alternatively, if you're using Maven, run the following command:
   ```bash
   mvn javafx:run
   ```

4. **Running Unit Tests**:
   To ensure the correctness of the encryption algorithms, run the unit tests using JUnit:
   ```bash
   mvn test
   ```

## Contributing

We welcome contributions to the Cypherfy project! If you'd like to contribute, please fork the repository and submit a pull request. Ensure that your changes follow the project's coding standards and include unit tests for any new features or bug fixes.

### Guidelines for Contributions:
- **Code Style**: Follow the existing coding conventions, including naming conventions and indentation style.
- **Unit Tests**: If you're adding a new cipher or feature, please write corresponding unit tests to ensure the functionality works as expected.
- **Documentation**: Update the README or other relevant documentation to reflect any new changes or features.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

## Acknowledgments

- **JavaFX**: For providing the framework to create rich client applications with a smooth and responsive UI.
- **JUnit**: For providing the testing framework to ensure correctness and stability.
- **OpenJFX Community**: For maintaining the JavaFX libraries.
- **OpenAI's ChatGPT**: For assistance in writing and proof-checking this README markdown document.
  
## Credits

- **GitHub Logo**: Icon created by [riajulislam](https://www.flaticon.com/free-icons/github) - Flaticon
- **Discord Logo**: Icon created by [riajulislam](https://www.flaticon.com/free-icons/discord) - Flaticon
- **Author**: [riajulislam](https://www.flaticon.com/authors/riajulislam) - Flaticon


